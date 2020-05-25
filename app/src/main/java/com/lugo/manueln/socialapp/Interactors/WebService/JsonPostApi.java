package com.lugo.manueln.socialapp.Interactors.WebService;

import com.lugo.manueln.socialapp.objects.Album;
import com.lugo.manueln.socialapp.objects.Comments;
import com.lugo.manueln.socialapp.objects.Photo;
import com.lugo.manueln.socialapp.objects.Post;
import com.lugo.manueln.socialapp.objects.Profile;

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

    @GET("albums")
    Call<List<Album>> getAlbums();

    @GET("albums/{idAlbum}/photos")
    Call<List<Photo>> getPhotosAlbum(@Path("idAlbum") int albumId);

    @POST("comments")
    Call<Comments> saveComment(@Body Comments comment);


    @GET("posts?")
    Observable<List<Post>> getListPostUser(@Query("userName") String userName);

    @GET("profile?")
    Observable<List<Profile>> getProfileUser(@Query("userName") String userName);
}
