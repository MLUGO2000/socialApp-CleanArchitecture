package com.lugo.manueln.socialapp.framework.Profile.dataSource

import com.lugo.manueln.socialapp.data.Profile.repository.dataSource.ProfileUserDataRemote
import com.lugo.manueln.socialapp.framework.Retrofit.JsonPostKotlinApi
import com.lugo.manueln.socialapp.domain.Profile
import io.reactivex.Observable

class ProfileUserDataRemoteImpl(private val api: JsonPostKotlinApi) : ProfileUserDataRemote {

    override fun getProfileUserRemote(userName: String): Observable<Profile> {

        val profile = Observable.create<Profile> { }

        return profile
    }
}