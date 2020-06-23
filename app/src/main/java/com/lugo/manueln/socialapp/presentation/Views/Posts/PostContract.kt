package com.lugo.manueln.socialapp.presentation.Views.Posts

import com.lugo.manueln.socialapp.domain.Post

interface PostContract {

    interface view {

        fun loadRecyclerPost()
        fun showRecycler(miListaPost: List<Post>)
        fun errorLoadRecyclerPost(error: String?)

        fun newPostCompleteFragment(idPost: Int)
        fun newProfileFragment(userName: String)


    }

    interface presenter {

        fun loadRecyclerPostPresenter()
        fun sendPostPresenter(miListaPost: List<Post>)
        fun sendErrorPostPresenter(error: String?)
    }
}