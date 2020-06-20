package com.lugo.manueln.socialapp.usecases.PostComplete

import com.lugo.manueln.socialapp.data.PostComplete.repository.PostCompleteRepository
import com.lugo.manueln.socialapp.domain.Comments
import io.reactivex.Observable

class GetCommentsPost(private val repository: PostCompleteRepository) {

    operator fun invoke(idPost:Int): Observable<List<Comments>> {

        return repository.getCommentsPost(idPost)

    }
}