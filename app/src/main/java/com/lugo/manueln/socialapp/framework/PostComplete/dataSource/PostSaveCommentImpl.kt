package com.lugo.manueln.socialapp.framework.PostComplete.dataSource

import com.lugo.manueln.socialapp.data.PostComplete.repository.dataSource.PostSaveComment
import com.lugo.manueln.socialapp.framework.Retrofit.JsonPostKotlinApi
import com.lugo.manueln.socialapp.domain.Comments
import io.reactivex.Observable

class PostSaveCommentImpl(private val api: JsonPostKotlinApi) : PostSaveComment {

    override fun saveCommentRemote(comment: Comments): Observable<Comments> {

        return api.saveComment(comment)
    }
}