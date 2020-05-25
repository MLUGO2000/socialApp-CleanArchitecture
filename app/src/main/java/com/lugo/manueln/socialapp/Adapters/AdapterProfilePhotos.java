package com.lugo.manueln.socialapp.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lugo.manueln.socialapp.R;
import com.lugo.manueln.socialapp.objects.Post;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterProfilePhotos extends RecyclerView.Adapter<AdapterProfilePhotos.ViewProfilePhotos> {

    List<Post> listPreviewPhoto;

    public AdapterProfilePhotos(List<Post> listPreviewPhoto) {
        this.listPreviewPhoto = listPreviewPhoto;
    }

    @NonNull
    @Override
    public ViewProfilePhotos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View inflate=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.preview_photo_item,viewGroup,false);

        return new ViewProfilePhotos(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewProfilePhotos viewProfilePhotos, int i) {


        Picasso.get()
                .load(listPreviewPhoto.get(i).getImage()).resize(960,960)
                .into(viewProfilePhotos.previewImagePost);



    }

    @Override
    public int getItemCount() {
        return listPreviewPhoto.size();
    }

    public class ViewProfilePhotos extends RecyclerView.ViewHolder {
        ImageView previewImagePost;
        public ViewProfilePhotos(@NonNull View itemView) {
            super(itemView);

            previewImagePost=itemView.findViewById(R.id.imagePreview);


        }
    }
}
