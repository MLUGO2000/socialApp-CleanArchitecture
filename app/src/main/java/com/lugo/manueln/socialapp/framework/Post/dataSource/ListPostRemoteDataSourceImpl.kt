package com.lugo.manueln.socialapp.framework.Post.dataSource

import com.lugo.manueln.socialapp.data.Post.entity.toPostDomain
import com.lugo.manueln.socialapp.data.Post.repository.dataSource.ListPostRemoteDataSource
import com.lugo.manueln.socialapp.domain.Post
import com.lugo.manueln.socialapp.framework.Retrofit.JsonPostKotlinApi
import io.reactivex.Observable

class ListPostRemoteDataSourceImpl(val postKotlinApi: JsonPostKotlinApi):ListPostRemoteDataSource {

    override fun getPosts(): Observable<List<Post>> {


        return postKotlinApi.getPostsObserver().map { it.map { postEntity ->  postEntity.toPostDomain() } }


    }
}