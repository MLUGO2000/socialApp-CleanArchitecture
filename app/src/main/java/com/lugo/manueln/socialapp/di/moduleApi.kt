package com.lugo.manueln.socialapp.di


import android.content.Context

import com.lugo.manueln.socialapp.BaseApplication
import com.lugo.manueln.socialapp.data.PostComplete.repository.PostCompleteRepository
import com.lugo.manueln.socialapp.data.PostComplete.repository.dataSource.PostCommentsDataSource
import com.lugo.manueln.socialapp.data.PostComplete.repository.dataSource.PostRemoteDataSource
import com.lugo.manueln.socialapp.data.Profile.repository.ProfileRepository
import com.lugo.manueln.socialapp.data.Profile.repository.dataSource.ListPostUserDataRemote
import com.lugo.manueln.socialapp.data.Profile.repository.dataSource.ProfileUserDataRemote
import com.lugo.manueln.socialapp.data.WebService.JsonPostKotlinApi
import com.lugo.manueln.socialapp.data.Post.repository.PostRepository
import com.lugo.manueln.socialapp.data.Post.repository.dataSource.ListPostRemoteDataSource
import com.lugo.manueln.socialapp.usecases.PostComplete.GetCommentsPost
import com.lugo.manueln.socialapp.usecases.PostComplete.GetPostComplete
import com.lugo.manueln.socialapp.usecases.Posts.GetListPosts
import com.lugo.manueln.socialapp.usecases.Profile.GetListPostUser
import com.lugo.manueln.socialapp.usecases.Profile.GetProfileUser

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class moduleApi(private val baseApplication: BaseApplication) {

    @Provides
    @Singleton
    fun providesAplicationContext(): Context =baseApplication

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()


    @Provides
    @Singleton
    fun providesRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory =RxJava2CallAdapterFactory.create()

    @Provides
    @Singleton
    fun providesRetrofit(gson: GsonConverterFactory, factory: RxJava2CallAdapterFactory): Retrofit {
        return Retrofit.Builder()
                .baseUrl("http://192.168.0.108:3000/")
                .addConverterFactory(gson)
                .addCallAdapterFactory(factory)
                .build()
    }

    @Provides
    @Singleton
    fun providesJsonPostApi(retrofit: Retrofit): JsonPostKotlinApi {

        return retrofit.create(JsonPostKotlinApi::class.java)
    }


    //Fragment Post
    @Provides
    @Singleton
    fun providesListPostRemoteDataSource(api: JsonPostKotlinApi): ListPostRemoteDataSource =ListPostRemoteDataSource(api)

    @Provides
    @Singleton
    fun providesPostRepository(listPostRemoteDataSource: ListPostRemoteDataSource): PostRepository =PostRepository(listPostRemoteDataSource)

    @Provides
    @Singleton
    fun providesUsesCaseGetPosts(repository: PostRepository): GetListPosts=GetListPosts(repository)

    //Fragment Post Complete

    @Provides
    @Singleton
    fun providesPostRemoteDataSource(api: JsonPostKotlinApi): PostRemoteDataSource =PostRemoteDataSource(api)

    @Provides
    @Singleton
    fun providesPostCommentsDataSource(api: JsonPostKotlinApi): PostCommentsDataSource {
        return PostCommentsDataSource(api)
    }

    @Provides
    @Singleton
    fun providesPostCompleteRepository(api: JsonPostKotlinApi, post: PostRemoteDataSource, comments: PostCommentsDataSource): PostCompleteRepository {
        return PostCompleteRepository(post, comments)
    }

    @Provides
    @Singleton
    fun providesUsesCaseGetPostComplete(repository: PostCompleteRepository): GetPostComplete {
        return GetPostComplete(repository)
    }

    @Provides
    @Singleton
    fun providesUsesCaseGetCommentsPosts(repository: PostCompleteRepository): GetCommentsPost {
        return GetCommentsPost(repository)
    }

    //Profile Fragment

    @Provides
    @Singleton
    fun providesListPostUserDataRemote(api: JsonPostKotlinApi): ListPostUserDataRemote {
        return ListPostUserDataRemote(api)
    }

    @Provides
    @Singleton
    fun providesProfileUserDataRemote(api: JsonPostKotlinApi): ProfileUserDataRemote {
        return ProfileUserDataRemote(api)
    }

    @Provides
    @Singleton
    fun providesProfileRepository(listPostUserDataRemote: ListPostUserDataRemote, profileUserDataRemote: ProfileUserDataRemote): ProfileRepository {

        return ProfileRepository(listPostUserDataRemote, profileUserDataRemote)
    }

    @Provides
    @Singleton
    fun providesGetListPostUser(repository: ProfileRepository): GetListPostUser {
        return GetListPostUser(repository)
    }

    @Provides
    @Singleton
    fun providesGetProfileUser(repository: ProfileRepository): GetProfileUser {
        return GetProfileUser(repository)
    }


}
