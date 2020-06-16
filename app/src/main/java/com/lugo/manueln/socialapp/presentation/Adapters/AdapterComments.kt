package com.lugo.manueln.socialapp.presentation.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.lugo.manueln.socialapp.domain.models.Comments
import com.lugo.manueln.socialapp.R

class AdapterComments(internal var comments: List<Comments>) : RecyclerView.Adapter<AdapterComments.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {

        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.coment_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {

        viewHolder.nameUser.text = comments[i].userName
        viewHolder.bodyComment.text = comments[i].body
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var nameUser: TextView
        internal var bodyComment: TextView

        init {

            nameUser = itemView.findViewById(R.id.userName)
            bodyComment = itemView.findViewById(R.id.bodyComment)
        }
    }
}
