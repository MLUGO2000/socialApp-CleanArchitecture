package com.lugo.manueln.socialapp.data.PostComplete.repository


import com.lugo.manueln.socialapp.data.PostComplete.repository.dataSource.PostCommentsDataSource
import com.lugo.manueln.socialapp.data.PostComplete.repository.dataSource.PostRemoteDataSource
import com.lugo.manueln.socialapp.domain.Comments
import com.lugo.manueln.socialapp.domain.Post
import io.reactivex.Observable

class PostCompleteRepository(private val dataSource: PostRemoteDataSource,private val commentsData:PostCommentsDataSource ) {

    fun getPostComplete(idPost: Int): Observable<Post> {
        return dataSource.getPostComplete(idPost)
    }
    fun getCommentsPost(idPost:Int):Observable<List<Comments>>{

        return commentsData.getCommentsPost(idPost)
    }
}