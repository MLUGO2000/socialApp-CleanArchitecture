package com.lugo.manueln.socialapp.presentation.Views.LoginActivity.Presenter

import com.lugo.manueln.socialapp.framework.Login.FirebaseLoginException
import com.lugo.manueln.socialapp.presentation.Views.LoginActivity.LoginContract
import com.lugo.manueln.socialapp.usecases.Login.GetLoginUser
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class PresenterLogin(private var view: LoginContract.View?, private val getLoginUser: GetLoginUser) : LoginContract.Presenter, CoroutineScope {

    var job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun sendLoginUser(email: String, password: String) {

        if(checkEmptyFields(email,password)){

            view?.showErrorLogin("Por Favor Introducir datos")
        }else {

            launch {


                view?.showProgressBar()
                try {
                    withContext(Dispatchers.IO) {
                        getLoginUser.invoke(email, password)
                    }

                    if (isViewAttach()) view?.navigateToMain()

                } catch (e: FirebaseLoginException) {
                    view?.showErrorLogin(e.message)
                }
            }
        }

    }

    private fun checkEmptyFields(email: String, password: String): Boolean {

        return email.isEmpty()||password.isEmpty()
    }


    override fun detachView() {
        job.cancel()
        view = null
    }

    override fun isViewAttach(): Boolean = view != null

}