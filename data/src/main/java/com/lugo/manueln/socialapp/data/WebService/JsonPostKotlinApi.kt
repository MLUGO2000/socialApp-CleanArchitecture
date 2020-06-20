package com.lugo.manueln.socialapp.data.WebService

import com.lugo.manueln.socialapp.data.Post.entity.PostEntity
import com.lugo.manueln.socialapp.domain.Comments
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface JsonPostKotlinApi {


    @GET("posts")
    fun getPostsObserver():Observable<List<PostEntity>>

    @GET("posts/{id}")
    fun getPostCompleteObserver(@Path("id")postId: Int):Observable<PostEntity>


    @GET("posts/{id}/comments")
    fun getCommentsObservable(@Path("id") postId: Int): Observable<List<Comments>>
}