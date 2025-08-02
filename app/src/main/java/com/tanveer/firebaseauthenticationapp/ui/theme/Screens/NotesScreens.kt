package com.tanveer.firebaseauthenticationapp.ui.theme.Screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tanveer.firebaseauthenticationapp.ViewModel.AuthViewModel
import com.tanveer.firebaseauthenticationapp.ViewModel.NotesViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tanveer.firebaseauthenticationapp.data.model.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(navController: NavController, viewModel: NotesViewModel = viewModel()) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var isEditing by remember { mutableStateOf(false) }
    var editingNoteId by remember { mutableStateOf<String?>(null) }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp), verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text(text = "Title") })
        OutlinedTextField(
            value = content,
            onValueChange = { content = it },
            label = { Text(text = "Content") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            if (isEditing && editingNoteId != null) {
                viewModel.upadteNote(
                    Note(
                        id = editingNoteId!!,
                        title = title, content = (content)
                    )
                )
                isEditing = false
                editingNoteId = null
            } else {
                viewModel.addNote(Note(title = title, content = content))
            }
            title = ""
            content = ""
        }) {
            Text(if (isEditing) "Update Note" else "Add Note")
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(viewModel.notes) { note ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Column(Modifier.padding(8.dp)) {
                        Text(note.title, style = MaterialTheme.typography.titleLarge)
                        Text(note.content, style = MaterialTheme.typography.bodyMedium)
                        Row {
                            TextButton(
                                onClick = {
                                    title = note.title
                                    content = note.content
                                    isEditing = true
                                    editingNoteId = note.id
                                }
                            ) {
                                Text("Edit")
                            }
                            TextButton(
                                onClick = {
                                    viewModel.deleteNote(note)
                                }
                            ) {
                                Text("Delete", color = MaterialTheme.colorScheme.error)
                            }
                        }
                    }
                }
            }
        }
    }
}

