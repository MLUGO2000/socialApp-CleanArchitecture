package com.lugo.manueln.socialapp.data.Post.repository.dataSource

import com.lugo.manueln.socialapp.data.WebService.JsonPostKotlinApi
import com.lugo.manueln.socialapp.data.Post.entity.toPostDomain
import com.lugo.manueln.socialapp.domain.Post
import io.reactivex.Observable


class ListPostRemoteDataSource(val postKotlinApi: JsonPostKotlinApi) {

   fun getPosts(): Observable<List<Post>> {


       return postKotlinApi.getPostsObserver().map { it.map { postEntity ->  postEntity.toPostDomain() } }


   }
}