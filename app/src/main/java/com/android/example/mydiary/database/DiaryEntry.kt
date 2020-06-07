package com.android.example.mydiary.database

import java.util.*
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diary_entries")
data class DiaryEntry(
    @PrimaryKey(autoGenerate = true)
    var entryID: Long= 0L,
    @ColumnInfo(name = "date_of_entry")
    var dateOfEntry: String = Date().toString(),
    @ColumnInfo(name = "title")
    var Title: String = "",
    @ColumnInfo(name = "entry_text")
    var Entry: String = ""
)