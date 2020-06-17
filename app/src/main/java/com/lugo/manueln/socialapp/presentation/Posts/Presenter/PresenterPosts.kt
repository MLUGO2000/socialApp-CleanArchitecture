package com.lugo.manueln.socialapp.presentation.Posts.Presenter

import android.support.v4.app.FragmentActivity
import com.lugo.manueln.socialapp.domain.Post
import com.lugo.manueln.socialapp.domain.interactors.postInteractor.PostsInteractor

import com.lugo.manueln.socialapp.domain.interactors.postInteractor.PostsInteractorImpl
import com.lugo.manueln.socialapp.presentation.Posts.PostContract

class PresenterPosts(private val view: PostContract.view) : PostContract.presenter {
    private val model: PostsInteractor?

    init {
        model = PostsInteractorImpl(this)
    }


    override fun loadRecyclerPostPresenter(activity: FragmentActivity?) {

        model?.getPosts(activity)
    }


    override fun sendPostPresenter(miListaPost: List<Post>) {

        if (view != null) {

            view!!.showRecycler(miListaPost)
        }

    }

    override fun sendErrorPostPresenter(error: String?) {
        if (view != null) {

            view!!.errorLoadRecyclerPost(error)
        }

    }

    override fun newPostCompleteFragmentPresenter(idPost: Int, fragmentActivity: FragmentActivity) {

        model?.newPostCompleteFragment(idPost, fragmentActivity)

    }

    override fun newProfileFragmentPresenter(userName: String, fragmentActivity: FragmentActivity) {

        model?.newProfileFragment(userName, fragmentActivity)
    }
}
