package com.lugo.manueln.socialapp.framework.Login

import com.google.firebase.auth.FirebaseAuth
import com.lugo.manueln.socialapp.data.Login.repository.source.FirebaseLogin
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class FirebaseLoginImpl: FirebaseLogin {


    override suspend fun loginAuth(email: String, password: String) {
        suspendCancellableCoroutine<Unit> {

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener {task ->

                if(task.isSuccessful){
                    it.resume(Unit)
                }else{
                    it.resumeWithException(FirebaseLoginException(task.exception?.message))
                }
            }
        }
    }
}