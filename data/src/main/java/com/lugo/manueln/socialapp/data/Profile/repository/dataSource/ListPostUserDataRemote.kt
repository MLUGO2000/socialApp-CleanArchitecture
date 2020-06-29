package com.lugo.manueln.socialapp.data.Profile.repository.dataSource

import com.lugo.manueln.socialapp.domain.Post
import io.reactivex.Observable

interface ListPostUserDataRemote{

    fun getListPost(userName:String): Observable<List<Post>>
}