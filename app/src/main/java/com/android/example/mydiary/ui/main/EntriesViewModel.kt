package com.android.example.mydiary.ui.main

import androidx.lifecycle.ViewModel
import com.android.example.mydiary.database.DiaryDatabaseDao

class EntriesViewModel(val database: DiaryDatabaseDao) : ViewModel() {
    val entries = database.getAllEntries()

}
