package com.android.example.mydiary.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.example.mydiary.database.DiaryDatabaseDao
import com.android.example.mydiary.database.DiaryEntry
import kotlinx.coroutines.*

class PageViewModel( val database: DiaryDatabaseDao) : ViewModel() {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _showSnackbar = MutableLiveData<Boolean>()
    val showSnackbar: LiveData<Boolean>
        get() = _showSnackbar

    fun doneShowingSnackbar() {
        _showSnackbar.value = false
    }

    fun onCLickRecord(title: String, entry: String) {
        uiScope.launch {
                val newEntry = DiaryEntry(Title = title,Entry = entry)
//                newEntry.Title = title
//                newEntry.Entry = entry
                insert(newEntry)
            _showSnackbar.value = true
        }
        Log.i("Database",database.getAllEntries().value.toString())
    }

    private suspend fun insert(newEntry: DiaryEntry) {
        withContext(Dispatchers.IO) {
            database.insert(newEntry)
        }
    }
}