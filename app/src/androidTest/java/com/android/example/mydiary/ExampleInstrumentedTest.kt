package com.android.example.mydiary

import androidx.room.Dao
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

import androidx.room.Room
import com.android.example.mydiary.database.DiaryDatabase
import com.android.example.mydiary.database.DiaryDatabaseDao
import com.android.example.mydiary.database.DiaryEntry
import org.junit.Assert.assertEquals
import org.junit.After
import org.junit.Before
import java.io.IOException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private lateinit var diaryDao: DiaryDatabaseDao
    private lateinit var db: DiaryDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, DiaryDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        diaryDao = db.diaryDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetEntry() {
        val night = DiaryEntry()
        diaryDao.insert(night)
        val entry = diaryDao.getLatest()
        assertEquals(entry?.Title, "")
    }
}
