package com.tanveer.firebaseauthenticationapp.ViewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.tanveer.firebaseauthenticationapp.data.model.Note
import com.tanveer.firebaseauthenticationapp.data.repository.NoteRepository

//connect repository to ui::handles ui triggered actions

class NotesViewModel : ViewModel(){
    private val repository = NoteRepository()
    val notes = mutableStateListOf<Note>()

    fun loadNotes(){
        repository.getNotes{
            notes.clear()
            notes.addAll(it)
        }
    }
    fun addNote(note:Note){
        repository.addNote(note){ success -> if(success) loadNotes()
        }
    }
    fun deleteNote(note:Note){
        repository.deleteNote(note){success -> if(success) loadNotes()
        }
    }
    fun upadteNote(note:Note){
        repository.updateNote(note){success -> if(success) loadNotes()
        }
    }
}