package com.lugo.manueln.socialapp.presentation.Views.Profile.Presenter

import android.support.v4.app.FragmentActivity
import com.lugo.manueln.socialapp.domain.Post

import com.lugo.manueln.socialapp.domain.Profile
import com.lugo.manueln.socialapp.presentation.Views.Profile.ProfileContract
import com.lugo.manueln.socialapp.usecases.Profile.GetListPostUser
import com.lugo.manueln.socialapp.usecases.Profile.GetProfileUser
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ProfilePresenter(private var view: ProfileContract.view,
                       private val getListPostUser: GetListPostUser,
                       private val getProfileUser: GetProfileUser) : ProfileContract.presenter {


    override fun loadProfilePresenter(user: String, activity: FragmentActivity) {


        getListPostUser.invoke(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<List<Post>> {

                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(listPosts: List<Post>) {

                        if(view!=null){
                            view.showListPostsProfile(listPosts)
                            view.showProfile(Profile(user,user,2,""))
                        }

                    }

                    override fun onError(e: Throwable) {

                        if(view!=null){
                            view?.showError(e.message)
                        }
                    }
                })

    }

    override fun showProfilePresenter(profile: Profile, postList: List<Post>) {

    }
}
