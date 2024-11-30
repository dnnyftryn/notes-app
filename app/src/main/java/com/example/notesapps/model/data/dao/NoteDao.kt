package com.example.notesapps.model.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notesapps.model.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note): Long // `note` must be an @Entity

    // Update a Note
    @Update
    fun update(note: Note): Int

    // Delete a Note
    @Delete
    fun delete(note: Note): Int

    @Query("SELECT * FROM note_table")
    fun getAllNotes(): LiveData<List<Note>>
}
