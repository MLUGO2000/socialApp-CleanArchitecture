package com.lugo.manueln.socialapp.framework.PostComplete.dataSource

import com.lugo.manueln.socialapp.data.PostComplete.repository.dataSource.PostCommentsDataSource
import com.lugo.manueln.socialapp.framework.Retrofit.JsonPostKotlinApi
import com.lugo.manueln.socialapp.domain.Comments
import io.reactivex.Observable

class PostCommentsDataSourceImpl (private val postKotlinApi: JsonPostKotlinApi):PostCommentsDataSource{

   override fun getCommentsPost(idPost: Int):Observable<List<Comments>>{
       return postKotlinApi.getCommentsObservable(idPost)
   }
}