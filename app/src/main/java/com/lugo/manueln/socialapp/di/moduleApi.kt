package com.lugo.manueln.socialapp.di


import android.content.Context

import com.lugo.manueln.socialapp.BaseApplication
import com.lugo.manueln.socialapp.data.Login.repository.LoginRepository
import com.lugo.manueln.socialapp.data.Login.repository.source.FirebaseLogin
import com.lugo.manueln.socialapp.data.PostComplete.repository.PostCompleteRepository
import com.lugo.manueln.socialapp.data.PostComplete.repository.dataSource.PostCommentsDataSource
import com.lugo.manueln.socialapp.data.PostComplete.repository.dataSource.PostRemoteDataSource
import com.lugo.manueln.socialapp.data.Profile.repository.ProfileRepository
import com.lugo.manueln.socialapp.data.Profile.repository.dataSource.ListPostUserDataRemote
import com.lugo.manueln.socialapp.data.Profile.repository.dataSource.ProfileUserDataRemote
import com.lugo.manueln.socialapp.framework.Retrofit.JsonPostKotlinApi
import com.lugo.manueln.socialapp.data.Post.repository.PostRepository
import com.lugo.manueln.socialapp.data.Post.repository.dataSource.ListPostRemoteDataSource
import com.lugo.manueln.socialapp.data.PostComplete.repository.dataSource.PostSaveComment
import com.lugo.manueln.socialapp.framework.Post.dataSource.ListPostRemoteDataSourceImpl
import com.lugo.manueln.socialapp.framework.PostComplete.dataSource.PostCommentsDataSourceImpl
import com.lugo.manueln.socialapp.framework.PostComplete.dataSource.PostRemoteDataSourceImpl
import com.lugo.manueln.socialapp.framework.PostComplete.dataSource.PostSaveCommentImpl
import com.lugo.manueln.socialapp.framework.Profile.dataSource.ListPostUserDataRemoteImpl
import com.lugo.manueln.socialapp.framework.Profile.dataSource.ProfileUserDataRemoteImpl
import com.lugo.manueln.socialapp.framework.Login.FirebaseLoginImpl
import com.lugo.manueln.socialapp.usecases.Login.GetLoginUser
import com.lugo.manueln.socialapp.usecases.PostComplete.GetCommentsPost
import com.lugo.manueln.socialapp.usecases.PostComplete.GetPostComplete
import com.lugo.manueln.socialapp.usecases.PostComplete.SaveCommentPost
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
    fun providesAplicationContext(): Context = baseApplication

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()


    @Provides
    @Singleton
    fun providesRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    @Provides
    @Singleton
    fun providesRetrofit(gson: GsonConverterFactory, factory: RxJava2CallAdapterFactory): Retrofit {
        return Retrofit.Builder()
                .baseUrl("http://192.168.0.107:3000/")
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
    fun providesListPostRemoteDataSource(api: JsonPostKotlinApi): ListPostRemoteDataSource = ListPostRemoteDataSourceImpl(api)

    @Provides
    @Singleton
    fun providesPostRepository(listPostRemoteDataSource: ListPostRemoteDataSource): PostRepository = PostRepository(listPostRemoteDataSource)

    @Provides
    @Singleton
    fun providesUsesCaseGetPosts(repository: PostRepository): GetListPosts = GetListPosts(repository)

    //Fragment Post Complete

    @Provides
    @Singleton
    fun providesPostRemoteDataSource(api: JsonPostKotlinApi): PostRemoteDataSource = PostRemoteDataSourceImpl(api)

    @Provides
    @Singleton
    fun providesPostCommentsDataSource(api: JsonPostKotlinApi): PostCommentsDataSource {
        return PostCommentsDataSourceImpl(api)
    }

    @Provides
    @Singleton
    fun providesPostSaveComment(api: JsonPostKotlinApi): PostSaveComment {
        return PostSaveCommentImpl(api)
    }

    @Provides
    @Singleton
    fun providesPostCompleteRepository(api: JsonPostKotlinApi, post: PostRemoteDataSource, comments: PostCommentsDataSource, saveComment: PostSaveComment): PostCompleteRepository {
        return PostCompleteRepository(post, comments, saveComment)
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

    @Provides
    @Singleton
    fun providesUsesCaseSaveCommentPost(repository: PostCompleteRepository): SaveCommentPost {
        return SaveCommentPost(repository)
    }

    //Profile Fragment

    @Provides
    @Singleton
    fun providesListPostUserDataRemote(api: JsonPostKotlinApi): ListPostUserDataRemote {
        return ListPostUserDataRemoteImpl(api)
    }

    @Provides
    @Singleton
    fun providesProfileUserDataRemote(api: JsonPostKotlinApi): ProfileUserDataRemote {
        return ProfileUserDataRemoteImpl(api)
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

    //ActivityLogin

    @Provides
    @Singleton
    fun providesFirebaseLogin(): FirebaseLogin {
        return FirebaseLoginImpl()
    }

    @Provides
    @Singleton
    fun providesRepositoryLoginUser(firebaseLogin: FirebaseLogin): LoginRepository {
        return LoginRepository(firebaseLogin)
    }

    @Provides
    @Singleton
    fun providessGetLoginUser(repository: LoginRepository):GetLoginUser{
        return GetLoginUser(repository)
    }



}
