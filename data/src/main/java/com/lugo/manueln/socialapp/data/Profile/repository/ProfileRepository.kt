package com.lugo.manueln.socialapp.data.Profile.repository

import com.lugo.manueln.socialapp.data.Profile.repository.dataSource.ListPostUserDataRemote
import com.lugo.manueln.socialapp.data.Profile.repository.dataSource.ProfileUserDataRemote
import com.lugo.manueln.socialapp.domain.Post
import com.lugo.manueln.socialapp.domain.Profile
import io.reactivex.Observable

class ProfileRepository(private val listPostUserDataRemote:ListPostUserDataRemote,private val profileUserDataRemote: ProfileUserDataRemote) {

    fun getPostListUser(userName:String):Observable<List<Post>>{
        return listPostUserDataRemote.getListPost(userName)
    }
    fun getProfileUser(userName: String):Observable<Profile>{
        return profileUserDataRemote.getProfileUserRemote(userName)
    }
}