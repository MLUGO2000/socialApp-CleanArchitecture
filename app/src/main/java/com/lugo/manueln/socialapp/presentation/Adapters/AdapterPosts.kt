package com.lugo.manueln.socialapp.presentation.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

import com.lugo.manueln.socialapp.presentation.Posts.PostContract
import com.lugo.manueln.socialapp.presentation.Posts.Presenter.PresenterPosts
import com.lugo.manueln.socialapp.R
import com.lugo.manueln.socialapp.domain.Post
import com.lugo.manueln.socialapp.presentation.Posts.View.PostsFragment
import com.squareup.picasso.Picasso

class AdapterPosts(var miListPosts: List<Post>, var miActividadPost: PostsFragment) : RecyclerView.Adapter<AdapterPosts.ViewHolderPost>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolderPost {

        val vista = LayoutInflater.from(viewGroup.context).inflate(R.layout.post_item, viewGroup, false)

        return ViewHolderPost(vista)
    }

    override fun onBindViewHolder(holderPost: ViewHolderPost, i: Int) {

        holderPost.txtUserName.text = miListPosts[i].userName
        holderPost.txtTitle.text = miListPosts[i].title
        holderPost.txtBody.text = miListPosts[i].body

        loadImage(miListPosts[i].image, holderPost.image)


        holderPost.bMas.id = miListPosts[i].id


        holderPost.bMas.setOnClickListener {
            miActividadPost.newPostCompleteFragment(miListPosts[i].id) }

       // holderPost.txtUserName.setOnClickListener { myPresenter.newProfileFragmentPresenter(miListPosts[i].userName, miActividadPost.activity!!) }

    }


    private fun loadImage(url: String, image: ImageView) {

        Picasso.get().load(url)
                .resize(960, 960)
                .into(image)

    }

    override fun getItemCount(): Int {
        return miListPosts.size
    }


    inner class ViewHolderPost(itemView: View) : RecyclerView.ViewHolder(itemView) {
         var txtUserName: TextView
         var txtTitle: TextView
         var txtBody: TextView
         var image: ImageView
         var bMas: Button

        init {

            image = itemView.findViewById(R.id.imagePost)
            txtUserName = itemView.findViewById(R.id.textUserName)
            txtTitle = itemView.findViewById(R.id.textTitle)
            txtBody = itemView.findViewById(R.id.textBody)
            bMas = itemView.findViewById(R.id.bMas)


        }
    }
}
