package com.lugo.manueln.socialapp.presentation.Profile

import android.support.v4.app.FragmentActivity
import com.lugo.manueln.socialapp.domain.Post
import com.lugo.manueln.socialapp.domain.Profile

interface ProfileContract{

    interface presenter {

        fun loadProfilePresenter(user: String, activity: FragmentActivity)
        fun showProfilePresenter(profile: Profile, postList: List<Post>)

    }


    interface view {
        fun showProfile(profile: Profile, postList: List<Post>)

    }

}