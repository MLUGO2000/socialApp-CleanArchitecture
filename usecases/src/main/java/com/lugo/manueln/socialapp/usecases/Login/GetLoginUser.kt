package com.lugo.manueln.socialapp.usecases.Login

import com.lugo.manueln.socialapp.data.Login.repository.LoginRepository

class GetLoginUser(private val repository:LoginRepository) {

   suspend fun invoke(email:String, password:String){

       repository.LoginUser(email,password)
    }

}