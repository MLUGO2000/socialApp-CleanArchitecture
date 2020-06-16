package com.lugo.manueln.socialapp.domain.interactors.profileInteractor

import android.support.v4.app.FragmentActivity

interface ProfileInteractor {

    fun getPostListUser(user: String, activity: FragmentActivity)
}