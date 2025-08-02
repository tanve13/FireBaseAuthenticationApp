package com.tanveer.firebaseauthenticationapp.Utils

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tanveer.firebaseauthenticationapp.ui.theme.Screens.LoginScreen
import com.tanveer.firebaseauthenticationapp.ui.theme.Screens.NotesScreen
import com.tanveer.firebaseauthenticationapp.ui.theme.Screens.RegisterScreen

///it define app's navigation route(login,register,notes)

@Composable
fun NavigationGraph(navController:NavHostController = rememberNavController()){
    val authViewModel = androidx.lifecycle.viewmodel.compose.viewModel<com.tanveer.firebaseauthenticationapp.ViewModel.AuthViewModel>()
    val notesViewModel = androidx.lifecycle.viewmodel.compose.viewModel<com.tanveer.firebaseauthenticationapp.ViewModel.NotesViewModel>()
   NavHost(navController = navController, startDestination = Screen.Login.route ){
       composable(Screen.Login.route){
           LoginScreen(navController = navController, viewModel = authViewModel)
       }
       composable(Screen.Register.route){
           RegisterScreen(navController = navController, viewModel = authViewModel)
       }
       composable(Screen.Notes.route){
           NotesScreen(navController = navController)
       }
   }
}