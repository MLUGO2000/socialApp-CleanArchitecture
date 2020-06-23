package com.lugo.manueln.socialapp.usecases.Profile

import com.lugo.manueln.socialapp.data.Profile.repository.ProfileRepository
import com.lugo.manueln.socialapp.domain.Post
import io.reactivex.Observable

class GetListPostUser(private val repository:ProfileRepository) {

    fun invoke(userName:String): Observable<List<Post>> {
        return repository.getPostListUser(userName)
    }
}