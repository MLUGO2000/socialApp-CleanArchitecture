package com.lugo.manueln.socialapp.framework.Retrofit

import com.lugo.manueln.socialapp.data.Post.entity.PostEntity
import com.lugo.manueln.socialapp.domain.Comments
import com.lugo.manueln.socialapp.domain.Post
import com.lugo.manueln.socialapp.domain.Profile
import io.reactivex.Observable
import retrofit2.http.*

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

    @POST("comments")
    fun saveComment(@Body comment: Comments): Observable<Comments>
}