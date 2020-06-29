package com.lugo.manueln.socialapp.data.Post.repository.dataSource


import com.lugo.manueln.socialapp.domain.Post
import io.reactivex.Observable


interface ListPostRemoteDataSource {

    fun getPosts(): Observable<List<Post>>


}