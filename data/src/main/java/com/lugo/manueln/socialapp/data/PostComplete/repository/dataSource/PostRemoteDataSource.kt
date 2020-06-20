package com.lugo.manueln.socialapp.data.PostComplete.repository.dataSource


import com.lugo.manueln.socialapp.data.Post.entity.toPostDomain
import com.lugo.manueln.socialapp.data.WebService.JsonPostKotlinApi
import com.lugo.manueln.socialapp.domain.Post
import io.reactivex.Observable


class PostRemoteDataSource (private val postKotlinApi: JsonPostKotlinApi){

    fun getPostComplete(idPost: Int):Observable<Post>{
        return postKotlinApi.getPostCompleteObserver(idPost).map { it.toPostDomain() }
    }
}