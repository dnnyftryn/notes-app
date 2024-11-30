package com.example.notesapps.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.notesapps.databinding.ActivityAddEditNoteBinding
import com.example.notesapps.model.Note
import com.example.notesapps.model.data.database.NoteDatabase
import com.example.notesapps.model.data.repository.NoteRepository
import com.example.notesapps.viewModel.NoteViewModel
import com.example.notesapps.viewModel.NoteViewModelFactory
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddEditNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddEditNoteBinding
    private lateinit var noteViewModel: NoteViewModel
    private var noteId: Int? = null // Store the ID if editing an existing note

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set toolbar title
        val extras = intent.extras
        val data = extras?.getString("data") ?: "Add/Edit Note"
        binding.toolbar.title = data

        // Initialize NoteViewModel
        val noteDao = NoteDatabase.getDatabase(application).noteDao()
        val repository = NoteRepository(noteDao)
        val factory = NoteViewModelFactory(repository)
        noteViewModel = ViewModelProvider(this, factory).get(NoteViewModel::class.java)

        // Retrieve data from intent if in edit mode
        val intent = intent
        if (intent.hasExtra("note_id")) {
            binding.editTextTitle.setText(intent.getStringExtra("note_title"))
            binding.editTextDescription.setText(intent.getStringExtra("note_description"))
            noteId = intent.getIntExtra("note_id", -1) // Retrieve the note ID
        }

        // Handle Save Button Click
        binding.saveButton.setOnClickListener {
            val title = binding.editTextTitle.text.toString()
            val description = binding.editTextDescription.text.toString()

            if (title.isNotEmpty() && description.isNotEmpty()) {
                val note = Note(
                    id = noteId ?: 0, // Use existing ID if editing, else 0 for a new note
                    title = title,
                    description = description
                )
                GlobalScope.launch {
                    noteViewModel.insert(note)
                }
                finish()
            }
        }
    }
}
