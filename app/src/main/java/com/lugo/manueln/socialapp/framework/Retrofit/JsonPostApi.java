package com.lugo.manueln.socialapp.framework.Retrofit;

import com.lugo.manueln.socialapp.domain.Comments;
import com.lugo.manueln.socialapp.domain.Post;
import com.lugo.manueln.socialapp.domain.Profile;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPostApi {

    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("posts")
    Observable<List<Post>> getPostsObservable();

    @GET("posts/{id}")
    Call<Post> getPostComplete(@Path("id") int postId);

    @GET("posts/{id}")
    Observable<Post> getPostCompleteObservable(@Path("id") int postId);

    @GET("posts/{id}/comments")
    Call<List<Comments>> getCommentsFilter(@Path("id") int postId);


    @GET("posts/{id}/comments")
    Observable<List<Comments>> getCommentsFilterObservable(@Path("id") int postId);

    @POST("comments")
    Call<Comments> saveComment(@Body Comments comment);


    @GET("posts?")
    Observable<List<Post>> getListPostUser(@Query("userName") String userName);

    @GET("profile?")
    Observable<List<Profile>> getProfileUser(@Query("userName") String userName);
}
