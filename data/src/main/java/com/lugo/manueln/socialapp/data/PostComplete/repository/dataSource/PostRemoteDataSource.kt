package com.lugo.manueln.socialapp.data.PostComplete.repository.dataSource



import com.lugo.manueln.socialapp.domain.Post
import io.reactivex.Observable


interface PostRemoteDataSource{

    fun getPostComplete(idPost: Int):Observable<Post>


}