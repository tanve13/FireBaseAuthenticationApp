package com.tanveer.firebaseauthenticationapp.Utils

sealed class Screen(val route:String){
    object Login : Screen("Login")
    object Register: Screen("Register")
    object Notes: Screen("Notes")
}