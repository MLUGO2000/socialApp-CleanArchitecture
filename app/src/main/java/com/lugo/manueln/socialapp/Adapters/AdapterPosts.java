package com.lugo.manueln.socialapp.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lugo.manueln.socialapp.Interfaces.InterPost;
import com.lugo.manueln.socialapp.objects.Post;
import com.lugo.manueln.socialapp.Presenters.PresenterPosts;
import com.lugo.manueln.socialapp.R;
import com.lugo.manueln.socialapp.Views.PostsFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AdapterPosts extends RecyclerView.Adapter<AdapterPosts.ViewHolderPost>{


    List<Post>miListPosts;
    PostsFragment miActividadPost;
    InterPost.presenter myPresenter;



    public AdapterPosts(List<Post> miListPosts, PostsFragment activity) {

        myPresenter=new PresenterPosts(activity);
        this.miListPosts = miListPosts;
        miActividadPost=activity;



    }

    @NonNull
    @Override
    public ViewHolderPost onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View vista=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.post_item,viewGroup,false);

        return new ViewHolderPost(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPost holderPost, final int i) {

        holderPost.txtUserName.setText(miListPosts.get(i).getUserName().toString());
        holderPost.txtTitle.setText(miListPosts.get(i).getTitle().toString());
        holderPost.txtBody.setText(miListPosts.get(i).getBody().toString());

        loadImage(miListPosts.get(i).getImage(),holderPost.image);


        holderPost.bMas.setId(miListPosts.get(i).getId());
        holderPost.bMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myPresenter.newPostCompleteFragmentPresenter(miListPosts.get(i).getId(),miActividadPost.getActivity());

            }
        });

        holderPost.txtUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myPresenter.newProfileFragmentPresenter(miListPosts.get(i).getUserName(),miActividadPost.getActivity());

            }
        });

    }


    private void loadImage(final String url, final ImageView image) {

        Picasso.get().load(url)
                        .resize(960,960)
                        .into(image);

    }

    @Override
    public int getItemCount() {
        return miListPosts.size();
    }




    public class ViewHolderPost extends RecyclerView.ViewHolder{
        TextView txtUserName,txtTitle,txtBody;
        ImageView image;
        Button bMas;
        public ViewHolderPost(@NonNull View itemView) {
            super(itemView);

            image=itemView.findViewById(R.id.imagePost);
            txtUserName=itemView.findViewById(R.id.textUserName);
            txtTitle=itemView.findViewById(R.id.textTitle);
            txtBody=itemView.findViewById(R.id.textBody);
            bMas=itemView.findViewById(R.id.bMas);

        }
    }
}
