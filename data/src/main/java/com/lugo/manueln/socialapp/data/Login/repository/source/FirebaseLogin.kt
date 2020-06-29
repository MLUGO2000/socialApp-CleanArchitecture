package com.lugo.manueln.socialapp.data.Login.repository.source

interface FirebaseLogin {

    suspend fun loginAuth(email:String,password:String)


}