package com.lugo.manueln.socialapp.domain.interactors.postCompleteInteractor

import android.support.v4.app.FragmentActivity
import com.lugo.manueln.socialapp.domain.models.Comments

interface InteractorPostComplete {

     fun retrofitGetPostCompleteWithComments(idPost: Int, fragmentActivity: FragmentActivity?)
     fun sendErrorLoadPost(error: String?)
     fun saveCommentPost(comment: Comments)
}