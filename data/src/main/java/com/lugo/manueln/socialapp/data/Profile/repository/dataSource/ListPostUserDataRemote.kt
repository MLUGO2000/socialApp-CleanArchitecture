package com.lugo.manueln.socialapp.data.Profile.repository.dataSource

import com.lugo.manueln.socialapp.data.WebService.JsonPostKotlinApi
import com.lugo.manueln.socialapp.domain.Post
import io.reactivex.Observable

class ListPostUserDataRemote(private val api:JsonPostKotlinApi) {

    fun getListPost(userName:String): Observable<List<Post>> {
       return api.getListPostUser(userName)
    }
}