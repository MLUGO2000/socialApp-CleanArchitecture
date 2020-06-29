package com.lugo.manueln.socialapp.data.Login.repository

import com.lugo.manueln.socialapp.data.Login.repository.source.FirebaseLogin

class LoginRepository(val firebaseLogin: FirebaseLogin) {

    suspend fun LoginUser(email:String, password:String){

        firebaseLogin.loginAuth(email,password)

    }
}