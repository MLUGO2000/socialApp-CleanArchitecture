package com.lugo.manueln.socialapp.presentation.Views.PostComplete

import com.lugo.manueln.socialapp.domain.Comments
import com.lugo.manueln.socialapp.domain.Post

interface PostCompleteContract {

    interface view {

        fun loadPostComplete()
        fun showPostComplete(miPost: Post)
        fun showCommentsPost(commentsList: List<Comments>)
        fun showErrorLoadPost(error: String?)
        fun showErrorSaveComment(error: String?)
        fun updatePostComplete()
        fun showProgressBar()
        fun hideProgressbar()

        fun cleanFieldComment()




    }

    interface presenter {

        fun loadPostCompleteWithComments(id: Int)
        fun PostCompleteWithCommentsPresenter(miPost: Post, commentsList: List<Comments>)
        fun sendErrorLoadPost(error: String?)
        fun saveComment(comment: Comments)

        fun detachView()
        fun isViewAttach():Boolean



    }
}