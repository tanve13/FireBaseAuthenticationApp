package com.tanveer.firebaseauthenticationapp.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tanveer.firebaseauthenticationapp.data.model.Note

///contains all firestore logic to fetch,add,delete,update notes

class NoteRepository {
   private val db = FirebaseFirestore.getInstance()
   private val userId = FirebaseAuth.getInstance().currentUser?.uid

    private fun notesRef()=db.collection("users")
        .document(userId?:"").collection("notes")

    fun getNotes(onResult: (List<Note>) -> Unit){
        notesRef().get().addOnSuccessListener { snapShot ->
            val notes = snapShot.map{
                it.toObject(Note::class.java).copy(id = it.id)
            }
            onResult(notes)
        }
    }
    //for adding note
    fun addNote(note:Note,onComplete:(Boolean) -> Unit){
        notesRef().add(note).addOnCompleteListener{
            onComplete(it.isSuccessful)
        }
    }
    //function for deleting note
    fun deleteNote(note:Note,onComplete:(Boolean) -> Unit){
        notesRef().document(note.id).delete().addOnCompleteListener{
            onComplete(it.isSuccessful)
        }
    }
    //function for updating note
    fun updateNote(note:Note,onComplete:(Boolean) -> Unit){
        notesRef().document(note.id).set(note).addOnCompleteListener{
            onComplete(it.isSuccessful)
        }
    }

}
