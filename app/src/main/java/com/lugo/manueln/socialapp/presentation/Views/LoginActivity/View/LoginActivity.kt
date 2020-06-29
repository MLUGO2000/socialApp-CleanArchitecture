package com.lugo.manueln.socialapp.presentation.Views.LoginActivity.View

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.lugo.manueln.socialapp.BaseApplication
import com.lugo.manueln.socialapp.R
import com.lugo.manueln.socialapp.presentation.Views.ActivityMain.MainActivity
import com.lugo.manueln.socialapp.presentation.Views.LoginActivity.LoginContract
import com.lugo.manueln.socialapp.presentation.Views.LoginActivity.Presenter.PresenterLogin
import com.lugo.manueln.socialapp.usecases.Login.GetLoginUser
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity(),LoginContract.View{

    @Inject
    lateinit var getLoginUser: GetLoginUser

    private lateinit var myPresenter:LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        (application as BaseApplication).componentApi.inject(this)

        myPresenter=PresenterLogin(this,getLoginUser)

        BLogin.setOnClickListener { getDataLoginUser() }

    }



    override fun getDataLoginUser() {

        val email=editEmail.text.toString().trim()
        val pass=editPass.text.toString().trim()

        myPresenter.sendLoginUser(email,pass)



    }

    override fun showProgressBar() {
      progressBarLogin.visibility=View.VISIBLE
    }

    override fun hideProgresBar() {
       progressBarLogin.visibility=View.VISIBLE
    }

    override fun showErrorLogin(error: String?) {
     Toast.makeText(this,error,Toast.LENGTH_LONG).show()
    }

    override fun navigateToMain() {
        var intent=Intent(this,MainActivity::class.java)

        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        myPresenter.detachView()
    }
}
