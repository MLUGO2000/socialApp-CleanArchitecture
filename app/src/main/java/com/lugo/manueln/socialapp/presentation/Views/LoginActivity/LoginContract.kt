package com.lugo.manueln.socialapp.presentation.Views.LoginActivity

interface LoginContract {

    interface View{

        fun getDataLoginUser()
        fun showProgressBar()
        fun hideProgresBar()
        fun showErrorLogin(error: String?)
        fun navigateToMain();
    }

    interface Presenter{

        fun sendLoginUser(email:String,password:String)
        fun detachView()
        fun isViewAttach():Boolean

    }
}