package com.android.example.mydiary.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.example.mydiary.database.DiaryDatabaseDao
import com.android.example.mydiary.database.DiaryEntry

class PageViewModelFactory( private val dataSource: DiaryDatabaseDao ): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PageViewModel::class.java)) {
            return PageViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}