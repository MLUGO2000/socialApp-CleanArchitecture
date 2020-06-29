package com.lugo.manueln.socialapp.data.Profile.repository.dataSource

import com.lugo.manueln.socialapp.domain.Profile
import io.reactivex.Observable

interface ProfileUserDataRemote{

    fun getProfileUserRemote(userName:String): Observable<Profile>
}