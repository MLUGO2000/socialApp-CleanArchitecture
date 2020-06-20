package com.lugo.manueln.socialapp.data.PostComplete.repository.dataSource

import com.lugo.manueln.socialapp.data.WebService.JsonPostKotlinApi
import com.lugo.manueln.socialapp.domain.Comments
import io.reactivex.Observable

class PostCommentsDataSource (private val postKotlinApi: JsonPostKotlinApi){

   fun getCommentsPost(idPost: Int):Observable<List<Comments>>{
       return postKotlinApi.getCommentsObservable(idPost)
   }
}