package com.lugo.manueln.socialapp.data.Post.repository

import com.lugo.manueln.socialapp.data.Post.repository.dataSource.ListPostRemoteDataSource
import com.lugo.manueln.socialapp.domain.Post
import io.reactivex.Observable

class PostRepository(private val listPostRemoteDataSource: ListPostRemoteDataSource){


    fun getPostsLists(): Observable<List<Post>> {
      return listPostRemoteDataSource.getPosts()
    }




}
