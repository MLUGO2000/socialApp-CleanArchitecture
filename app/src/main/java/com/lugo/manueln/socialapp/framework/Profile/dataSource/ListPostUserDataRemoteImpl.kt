package com.lugo.manueln.socialapp.framework.Profile.dataSource

import com.lugo.manueln.socialapp.data.Profile.repository.dataSource.ListPostUserDataRemote
import com.lugo.manueln.socialapp.framework.Retrofit.JsonPostKotlinApi
import com.lugo.manueln.socialapp.domain.Post
import io.reactivex.Observable

class ListPostUserDataRemoteImpl(private val api: JsonPostKotlinApi) : ListPostUserDataRemote {

    override fun getListPost(userName: String): Observable<List<Post>> {
        return api.getListPostUser(userName)
    }
}