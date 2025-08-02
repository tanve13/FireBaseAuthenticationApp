package com.tanveer.firebaseauthenticationapp.ui.theme.Screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tanveer.firebaseauthenticationapp.Utils.Screen
import com.tanveer.firebaseauthenticationapp.ViewModel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController,viewModel: AuthViewModel){
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp), verticalArrangement = Arrangement.Center){
        OutlinedTextField(value = email, onValueChange ={email = it}, label = { Text(text = "Email")} )
        OutlinedTextField(value = password, onValueChange ={password = it}, label = { Text(text = "Password")},
        visualTransformation = PasswordVisualTransformation())
       Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            viewModel.register(email,password){
                success,message ->
                Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
                if (success)
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Register.route) {
                            inclusive = true
                        }
                    }
            }
        }) {
            Text(text = "Register")
        }
        TextButton(onClick = {navController.navigate(Screen.Login.route)}) {
            Text(text = "Already have an account?Login")
        }
    }
}