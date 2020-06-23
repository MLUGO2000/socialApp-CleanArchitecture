package com.lugo.manueln.socialapp.usecases.Profile

import com.lugo.manueln.socialapp.data.Profile.repository.ProfileRepository
import com.lugo.manueln.socialapp.domain.Post
import com.lugo.manueln.socialapp.domain.Profile
import io.reactivex.Observable

class GetProfileUser(private val repository: ProfileRepository) {

    fun invoke(userName:String): Observable<Profile> {
        return repository.getProfileUser(userName)
    }
}