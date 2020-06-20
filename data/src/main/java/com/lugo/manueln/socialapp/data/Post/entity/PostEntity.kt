package com.lugo.manueln.socialapp.data.Post.entity

import com.lugo.manueln.socialapp.domain.Post

data class PostEntity(val userName: String, val id: Int, val title: String, val body: String, val image: String)

 fun PostEntity.toPostDomain():Post{
     return Post(userName, id, title, body, image)
 }

