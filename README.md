# Notes App

This is a simple and efficient Notes application built using Kotlin. It leverages the MVVM architecture along with Room Database for seamless local data management. The app allows users to create, edit, delete, and view notes in a user-friendly interface.

## Overview of the Project
The Notes app is structured to follow best practices in Android development. It ensures a clean separation of concerns by using the MVVM (Model-View-ViewModel) architecture. The Room Database handles local storage, and LiveData ensures the app reacts to data changes efficiently.

## Folder Structure Explained
The project folder is organized for readability, scalability, and maintainability:

### model
This folder contains all the data-related logic, including the entity, DAO, database, and repository.

1. data/dao

- NoteDao: Defines the operations you can perform on the notes database, such as insert, update, delete, and query.

2. data/database

- NoteDatabase: Implements the Room database. It acts as the main access point for the underlying database connection.

3. data/repository

- NoteRepository: Serves as a single source of truth for fetching and storing data. It bridges the ViewModel and Room DAO.

4. Note
- Represents the data entity for a note. It is annotated with @Entity to map it to the notes table in the Room database.





### ui
This folder handles all the user interface (UI) logic, such as Activities and Adapters.

1. MainActivity

- Displays all notes in a RecyclerView.
- Uses NoteViewModel to observe and update the list of notes.
- Includes a FloatingActionButton to add new notes.

2. AddEditNoteActivity

- Manages the creation and editing of notes.
- Uses NoteViewModel to save or update notes in the database.

3. NoteAdapter

- RecyclerView adapter that binds the list of notes to the RecyclerView.
- Handles interactions like clicking to edit and long-pressing to delete.

### viewModel
This folder contains the logic for managing UI-related data in a lifecycle-aware manner.

1. NoteViewModel
- Exposes notes data as LiveData to the UI.
- Uses coroutines to perform database operations in the background.
- Ensures the UI remains responsive.

2. NoteViewModelFactory

- Supplies the NoteRepository to NoteViewModel.

## Features

1. Add Notes: Easily create new notes with a title and description.
2. Edit Notes: Update existing notes directly.
3. Delete Notes: Long-press a note to delete it (with confirmation dialog).
4. View Notes: Display all saved notes in a clean and responsive RecyclerView.


## Technologies Used
**Kotlin**: The language used for the entire project.

**Room Database**: For efficient local storage of notes.

**MVVM Architecture**: Ensures separation of concerns and scalability.

**LiveData**: Observes and reacts to data changes in real-time.

**ViewModel**: Manages UI-related data efficiently and prevents configuration changes from affecting the data.

**RecyclerView**: Displays a dynamic list of notes.
