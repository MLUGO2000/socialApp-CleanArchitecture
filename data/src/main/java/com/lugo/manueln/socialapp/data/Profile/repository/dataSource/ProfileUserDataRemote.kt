package com.lugo.manueln.socialapp.data.Profile.repository.dataSource

import com.lugo.manueln.socialapp.data.WebService.JsonPostKotlinApi
import com.lugo.manueln.socialapp.domain.Profile
import io.reactivex.Observable

class ProfileUserDataRemote(private val api:JsonPostKotlinApi) {

    fun getProfileUserRemote(userName:String): Observable<Profile> {

       val profile=Observable.create<Profile> {  }

        return profile
    }
}