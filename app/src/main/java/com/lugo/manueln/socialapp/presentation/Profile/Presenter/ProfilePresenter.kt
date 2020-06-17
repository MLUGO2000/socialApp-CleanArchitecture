package com.lugo.manueln.socialapp.presentation.Profile.Presenter

import android.support.v4.app.FragmentActivity
import com.lugo.manueln.socialapp.domain.interactors.profileInteractor.ProfileInteractor

import com.lugo.manueln.socialapp.domain.interactors.profileInteractor.ProfileInteractorImpl
import com.lugo.manueln.socialapp.domain.Profile
import com.lugo.manueln.socialapp.presentation.Profile.ProfileContract

class ProfilePresenter(private val view: ProfileContract.view) : ProfileContract.presenter {

    private var interactor:ProfileInteractor

    init {
        interactor = ProfileInteractorImpl(this)
    }

    override fun loadProfilePresenter(user: String, activity: FragmentActivity) {

        if (interactor != null) {
            interactor!!.getPostListUser(user, activity)
        }
    }

    override fun showProfilePresenter(profile: Profile, postList: List<Post>) {

        if (view != null) {
            view!!.showProfile(profile, postList)
        }
    }
}
