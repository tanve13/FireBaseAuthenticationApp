package com.tanveer.firebaseauthenticationapp.ViewModel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

///logic for login and registration using firebase authentication

class AuthViewModel: ViewModel(){
    private val auth = FirebaseAuth.getInstance()

    fun login(email:String, password:String, onResult: (Boolean, String) -> Unit){
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
           if(it.isSuccessful)onResult(true,"Login Successful")
           else onResult(false,it.exception?.message?:"Login Failed")
        }
    }
    fun register(email:String,password:String,onResult:(Boolean,String) -> Unit){
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            if (it.isSuccessful) onResult(true,"Registration Succesful")
            else onResult(false,it.exception?.message?:"Registration Failed")
        }
    }
}
