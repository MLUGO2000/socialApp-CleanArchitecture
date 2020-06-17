package com.lugo.manueln.socialapp.presentation.PostComplete.Presenter

import android.support.v4.app.FragmentActivity

import com.lugo.manueln.socialapp.domain.interactors.postCompleteInteractor.InteractorPostCompleteImpl
import com.lugo.manueln.socialapp.domain.interactors.postCompleteInteractor.InteractorPostComplete
import com.lugo.manueln.socialapp.domain.Comments
import com.lugo.manueln.socialapp.presentation.PostComplete.PostCompleteContract

class PresenterPostComplete(private var view: PostCompleteContract.view) : PostCompleteContract.presenter {
     var interactor: InteractorPostComplete

    init {
        interactor = InteractorPostCompleteImpl(this)
    }

    override fun loadPostCompleteWithComments(id: Int, fragmentActivity: FragmentActivity?) {

        interactor.retrofitGetPostCompleteWithComments(id, fragmentActivity)
    }

    override fun PostCompleteWithCommentsPresenter(miPost: Post, commentsList: List<Comments>) {

        if (view != null) {
            view!!.showPostComplete(miPost)
            view!!.showCommentsPost(commentsList)
        }
    }

    override fun sendErrorLoadPost(error: String?) {

        if (view != null) {

            view!!.showErrorLoadPost(error)
        }

    }

    override fun saveComment(comment: Comments) {

        interactor.saveCommentPost(comment)

    }

    override fun updatePostComplete() {


    }
}
