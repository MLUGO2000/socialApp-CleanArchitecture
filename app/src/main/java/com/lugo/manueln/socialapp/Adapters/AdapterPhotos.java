package com.lugo.manueln.socialapp.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lugo.manueln.socialapp.R;
import com.lugo.manueln.socialapp.objects.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterPhotos extends RecyclerView.Adapter<AdapterPhotos.ViewPhotos> {

    List<Photo>listPhoto;

    public AdapterPhotos(List<Photo>list){
        listPhoto=list;
    }
    @NonNull
    @Override
    public ViewPhotos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

       View viewInflate=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.photo_item,viewGroup,false);


        return new ViewPhotos(viewInflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPhotos holderPhotos, int i) {



        Picasso.get()
                .load(listPhoto.get(i).getThumbnailUrl())
                .into(holderPhotos.imageView);

    }

    @Override
    public int getItemCount() {
        return listPhoto.size();
    }

    public class ViewPhotos extends RecyclerView.ViewHolder {
        ImageView imageView;
        CardView cardViewPhoto;
        public ViewPhotos(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.photoView);
            cardViewPhoto=itemView.findViewById(R.id.cardPhoto);

        }
    }
}
