package com.lugo.manueln.socialapp.usecases.Posts

import com.lugo.manueln.socialapp.data.Post.repository.PostRepository
import com.lugo.manueln.socialapp.domain.Post
import io.reactivex.Observable


class GetListPosts(private val postRepository: PostRepository){

    operator fun invoke(): Observable<List<Post>> {
        return postRepository.getPostsLists()
    }
}
