package com.lugo.manueln.socialapp.presentation.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.lugo.manueln.socialapp.R
import com.lugo.manueln.socialapp.domain.Post
import com.squareup.picasso.Picasso

class AdapterProfilePhotos(internal var listPreviewPhoto: List<Post>) : RecyclerView.Adapter<AdapterProfilePhotos.ViewProfilePhotos>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewProfilePhotos {

        val inflate = LayoutInflater.from(viewGroup.context).inflate(R.layout.preview_photo_item, viewGroup, false)

        return ViewProfilePhotos(inflate)
    }

    override fun onBindViewHolder(viewProfilePhotos: ViewProfilePhotos, i: Int) {


        Picasso.get()
                .load(listPreviewPhoto[i].image).resize(960, 960)
                .into(viewProfilePhotos.previewImagePost)


    }

    override fun getItemCount(): Int {
        return listPreviewPhoto.size
    }

    inner class ViewProfilePhotos(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var previewImagePost: ImageView

        init {

            previewImagePost = itemView.findViewById(R.id.imagePreview)


        }
    }
}
