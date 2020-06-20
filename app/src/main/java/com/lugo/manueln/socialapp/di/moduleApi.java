package com.lugo.manueln.socialapp.di;


import android.content.Context;

import com.lugo.manueln.socialapp.BaseApplication;
import com.lugo.manueln.socialapp.data.PostComplete.repository.PostCompleteRepository;
import com.lugo.manueln.socialapp.data.PostComplete.repository.dataSource.PostCommentsDataSource;
import com.lugo.manueln.socialapp.data.PostComplete.repository.dataSource.PostRemoteDataSource;
import com.lugo.manueln.socialapp.data.WebService.JsonPostKotlinApi;
import com.lugo.manueln.socialapp.data.Post.repository.PostRepository;
import com.lugo.manueln.socialapp.data.Post.repository.dataSource.ListPostRemoteDataSource;
import com.lugo.manueln.socialapp.usecases.PostComplete.GetCommentsPost;
import com.lugo.manueln.socialapp.usecases.PostComplete.GetPostComplete;
import com.lugo.manueln.socialapp.usecases.Posts.GetListPosts;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class moduleApi {

    private final BaseApplication baseApplication;

    public moduleApi(BaseApplication baseApplication) {
        this.baseApplication = baseApplication;
    }

    @Provides
    @Singleton
    Context providesAplicationContext(){
        return baseApplication;
    }

    @Provides
    @Singleton
    GsonConverterFactory provideGsonConverterFactory(){
       return GsonConverterFactory.create();
    }


    @Provides
    @Singleton
    RxJava2CallAdapterFactory providesRxJava2CallAdapterFactory(){
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton

    Retrofit providesRetrofit(GsonConverterFactory gson,RxJava2CallAdapterFactory factory){
        return new Retrofit.Builder()
                .baseUrl("http://192.168.0.107:4000/")
                .addConverterFactory(gson)
                .addCallAdapterFactory(factory)
                .build();
    }

    @Provides
    @Singleton
    JsonPostKotlinApi providesJsonPostApi( Retrofit retrofit){

        return retrofit.create(JsonPostKotlinApi.class);
    }




    //Fragment Post
    @Provides
    @Singleton
    ListPostRemoteDataSource providesListPostRemoteDataSource(JsonPostKotlinApi api){

        return new ListPostRemoteDataSource(api);
    }

    @Provides
    @Singleton
    PostRepository providesPostRepository(ListPostRemoteDataSource listPostRemoteDataSource){

        return new PostRepository(listPostRemoteDataSource);
    }

    @Provides
    @Singleton
    GetListPosts providesUsesCaseGetPosts(PostRepository repository){
        return new GetListPosts(repository);
    }

    //Fragment Post Complete

    @Provides
    @Singleton
    PostRemoteDataSource providesPostRemoteDataSource(JsonPostKotlinApi api){

        return new PostRemoteDataSource(api);
    }

    @Provides
    @Singleton
    PostCommentsDataSource providesPostCommentsDataSource(JsonPostKotlinApi api){
        return new PostCommentsDataSource(api);
    }

    @Provides
    @Singleton
    PostCompleteRepository providesPostCompleteRepository(JsonPostKotlinApi api,PostRemoteDataSource post,PostCommentsDataSource comments){
        return new PostCompleteRepository(post,comments);
    }

    @Provides
    @Singleton
    GetPostComplete providesUsesCaseGetPostComplete(PostCompleteRepository repository){
        return new GetPostComplete(repository);
    }

    @Provides
    @Singleton
    GetCommentsPost providesUsesCaseGetCommentsPosts(PostCompleteRepository repository){
        return new GetCommentsPost(repository);
    }














}
