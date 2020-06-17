package com.lugo.manueln.socialapp.domain.interactors.postInteractor

import android.support.v4.app.FragmentActivity

interface PostsInteractor {


     fun getPosts(main: FragmentActivity?)

     fun sendPosts(miListaPost: List<Post>)

     fun sendErrorPost(error: String?)

     fun newPostCompleteFragment(idPost: Int, fragmentActivity: FragmentActivity)

     fun newProfileFragment(userName: String, fragmentActivity: FragmentActivity)

}