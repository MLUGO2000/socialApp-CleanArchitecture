package com.lugo.manueln.socialapp.usecases.PostComplete

import com.lugo.manueln.socialapp.data.PostComplete.repository.PostCompleteRepository
import com.lugo.manueln.socialapp.domain.Post
import io.reactivex.Observable

class GetPostComplete(private val repository: PostCompleteRepository) {

    operator fun invoke(idPost:Int): Observable<Post> {
        return repository.getPostComplete(idPost)
    }
}