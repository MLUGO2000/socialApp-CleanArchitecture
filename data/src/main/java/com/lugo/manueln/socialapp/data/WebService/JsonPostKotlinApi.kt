package com.lugo.manueln.socialapp.data.WebService

import com.lugo.manueln.socialapp.data.Post.entity.PostEntity
import com.lugo.manueln.socialapp.domain.Comments
import com.lugo.manueln.socialapp.domain.Post
import com.lugo.manueln.socialapp.domain.Profile
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JsonPostKotlinApi {


    @GET("posts")
    fun getPostsObserver(): Observable<List<PostEntity>>

    @GET("posts/{id}")
    fun getPostCompleteObserver(@Path("id") postId: Int): Observable<PostEntity>


    @GET("posts/{id}/comments")
    fun getCommentsObservable(@Path("id") postId: Int): Observable<List<Comments>>

    @GET("posts?")
    fun getListPostUser(@Query("userName") userName: String): Observable<List<Post>>

    @GET("profile?")
    fun getProfileUser(@Query("userName") userName: String): Observable<List<Profile>>
}