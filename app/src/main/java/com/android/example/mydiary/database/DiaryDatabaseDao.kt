package com.android.example.mydiary.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DiaryDatabaseDao{
    @Insert
    fun insert(entry: DiaryEntry)

    @Update
    fun update(entry: DiaryEntry)

    @Query("SELECT * from diary_entries WHERE entryID = :key")
    fun get(key: Long): DiaryEntry?

    @Query("SELECT * from diary_entries ORDER BY entryID DESC LIMIT 1")
    fun getLatest(): DiaryEntry?

    @Query("SELECT * from diary_entries ORDER BY entryID DESC")
    fun getAllEntries(): LiveData<List<DiaryEntry>>
}