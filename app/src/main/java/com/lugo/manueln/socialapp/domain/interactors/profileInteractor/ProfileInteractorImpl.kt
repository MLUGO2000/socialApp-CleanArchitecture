package com.lugo.manueln.socialapp.domain.interactors.profileInteractor

import android.support.v4.app.FragmentActivity
import android.util.Log

import com.lugo.manueln.socialapp.data.WebService.JsonPostApi
import com.lugo.manueln.socialapp.domain.Post
import com.lugo.manueln.socialapp.BaseApplication
import com.lugo.manueln.socialapp.domain.Profile
import com.lugo.manueln.socialapp.presentation.Profile.ProfileContract

import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ProfileInteractorImpl(internal var presenter: ProfileContract.presenter) : ProfileInteractor {
    //@Inject
    lateinit var jsonPostApi: JsonPostApi


    override fun getPostListUser(user: String, fragmentActivity: FragmentActivity) {

        setupDagger(fragmentActivity)

        jsonPostApi!!.getListPostUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<List<Post>> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(posts: List<Post>) {

                        sendPostListUser(user, posts)
                    }

                    override fun onError(e: Throwable) {

                        sendError(e.message)

                    }

                    override fun onComplete() {

                        Log.e("onComplete", Thread.currentThread().name)
                    }
                })


    }

    private fun sendError(message: String?) {

        Log.e("error", message)
    }

    private fun sendPostListUser(user: String, posts: List<Post>) {

        jsonPostApi!!.getProfileUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<List<Profile>> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(profiles: List<Profile>) {

                        if (profiles.size < 2) {
                            presenter.showProfilePresenter(profiles[0], posts)
                        }
                        Log.i("PROFILE", "Profile: " + profiles[0].name)
                    }


                    override fun onError(e: Throwable) {

                        sendError(e.message)
                    }

                    override fun onComplete() {

                    }
                })


    }

    private fun setupDagger(fragmentActivity: FragmentActivity) {

        (fragmentActivity.application as BaseApplication).componentApi.inject(this)
    }

}
