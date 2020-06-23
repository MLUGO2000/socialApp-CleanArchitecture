package com.lugo.manueln.socialapp.di

import android.content.Context
import com.lugo.manueln.socialapp.BaseApplication


import com.lugo.manueln.socialapp.presentation.Views.PostComplete.View.PostCompleteFragment
import com.lugo.manueln.socialapp.presentation.Views.Posts.View.PostsFragment
import com.lugo.manueln.socialapp.presentation.Views.Profile.View.ProfileFragment


import javax.inject.Singleton

import dagger.Component

@Singleton
@Component(modules = [moduleApi::class])
interface mainComponent {

    fun inject(baseApplication:BaseApplication)
    fun inject(postsFragment: PostsFragment)
    fun inject(postCompleteFragment: PostCompleteFragment)
    fun inject(profileFragment: ProfileFragment)


    fun context(): Context


}
