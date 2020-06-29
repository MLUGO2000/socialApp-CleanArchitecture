package com.lugo.manueln.socialapp.framework.PostComplete.dataSource


import com.lugo.manueln.socialapp.data.Post.entity.toPostDomain
import com.lugo.manueln.socialapp.data.PostComplete.repository.dataSource.PostRemoteDataSource
import com.lugo.manueln.socialapp.framework.Retrofit.JsonPostKotlinApi
import com.lugo.manueln.socialapp.domain.Post
import io.reactivex.Observable


class PostRemoteDataSourceImpl (private val postKotlinApi: JsonPostKotlinApi):PostRemoteDataSource{

    override fun getPostComplete(idPost: Int):Observable<Post>{
        return postKotlinApi.getPostCompleteObserver(idPost).map { it.toPostDomain() }
    }
}