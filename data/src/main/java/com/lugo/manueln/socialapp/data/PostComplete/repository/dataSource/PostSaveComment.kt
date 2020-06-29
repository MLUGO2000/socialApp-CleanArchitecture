package com.lugo.manueln.socialapp.data.PostComplete.repository.dataSource


import com.lugo.manueln.socialapp.domain.Comments
import io.reactivex.Observable

interface PostSaveComment {

    fun saveCommentRemote(comment:Comments):Observable<Comments>
}