package com.lugo.manueln.socialapp.usecases.PostComplete

import com.lugo.manueln.socialapp.data.PostComplete.repository.PostCompleteRepository
import com.lugo.manueln.socialapp.domain.Comments
import io.reactivex.Observable


class SaveCommentPost(private val repository: PostCompleteRepository) {

    fun invoke(comments: Comments): Observable<Comments>{

        return repository.sendCommentSave(comments)
    }
}