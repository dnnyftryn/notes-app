package com.example.notesapps.ui

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapps.databinding.ItemNoteBinding
import com.example.notesapps.model.Note
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NoteAdapter(
    private val onDeleteClick: (Note) -> Unit,
    private val onEditClick: (Note) -> Unit
) : ListAdapter<Note, NoteAdapter.NoteViewHolder>(NoteDiffCallback()) {

    inner class NoteViewHolder(private val binding: ItemNoteBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(note: Note) {
            binding.textViewTitle.text = note.title
            binding.textViewDescription.text = note.description

            // Long click for delete with confirmation dialog
            binding.root.setOnLongClickListener {
                showDeleteConfirmationDialog(context, note)
                true
            }


            // Short click for editing
            binding.root.setOnClickListener {
                onEditClick(note)
            }
        }
    }

    private fun showDeleteConfirmationDialog(context: Context, note: Note) {
        val dialog = AlertDialog.Builder(context)
            .setTitle("Delete Note")
            .setMessage("Are you sure you want to delete this note?")
            .setPositiveButton("Delete") { _, _ ->
                // Call onDeleteClick callback when user confirms
                GlobalScope.launch {
                    onDeleteClick(note)
                }
            }
            .setNegativeButton("Cancel", null)
            .create()
        dialog.show()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NoteViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }
}
