package com.example.notesapps.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapps.R
import com.example.notesapps.databinding.ActivityMainBinding
import com.example.notesapps.model.Note
import com.example.notesapps.model.data.database.NoteDatabase
import com.example.notesapps.model.data.repository.NoteRepository
import com.example.notesapps.viewModel.NoteViewModel
import com.example.notesapps.viewModel.NoteViewModelFactory


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var noteAdapter: NoteAdapter

    private val noteViewModel: NoteViewModel by viewModels {
        val noteDao = NoteDatabase.getDatabase(application).noteDao()
        val repository = NoteRepository(noteDao)
        NoteViewModelFactory(repository)
    }
    private lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // FloatingActionButton for adding notes
        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this, AddEditNoteActivity::class.java)
            intent.putExtra("data", "Add Note") // Extra for activity mode
            startActivity(intent)
        }

        // Setup RecyclerView and Adapter
        noteAdapter = NoteAdapter(
            onDeleteClick = { note -> noteViewModel.delete(note) },
            onEditClick = { note -> openEditNoteActivity(note) }
        )
        binding.recyclerViewNotes.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewNotes.adapter = noteAdapter

        // Observe LiveData from ViewModel
        noteViewModel.allNotes.observe(this) { notes ->
            noteAdapter.submitList(notes)
        }

        binding.toolbar.title = "Notes App"

    }

    // Function to open AddEditNoteActivity in Edit Mode
    private fun openEditNoteActivity(note: Note) {
        val intent = Intent(this, AddEditNoteActivity::class.java).apply {
            putExtra("data", "Edit Note") // Extra for activity mode
            putExtra("note_id", note.id)
            putExtra("note_title", note.title)
            putExtra("note_description", note.description)
        }
        startActivity(intent)
    }
}