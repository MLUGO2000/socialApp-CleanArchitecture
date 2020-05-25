package com.lugo.manueln.socialapp.Presenters;

import android.support.v4.app.FragmentActivity;

import com.lugo.manueln.socialapp.Interfaces.InterPost;
import com.lugo.manueln.socialapp.Interactors.ModulePosts;
import com.lugo.manueln.socialapp.objects.Post;

import java.util.List;

public class PresenterPosts implements InterPost.presenter {

    private InterPost.view view;
    private InterPost.interactor model;

    public PresenterPosts(InterPost.view view){
        this.view=view;
        model=new ModulePosts(this);
    }
    @Override
    public void loadRecyclerPostPresenter(FragmentActivity activity) {

        if(model!=null){

            model.getPosts(activity);
        }
    }


    @Override
    public void sendPostPresenter(List<Post> miListaPost) {

        if(view!=null){

            view.showRecycler(miListaPost);
        }

    }

    @Override
    public void sendErrorPostPresenter(String error) {
        if(view!=null){

            view.errorLoadRecyclerPost(error);
        }

    }

    @Override
    public void newPostCompleteFragmentPresenter(int idPost,FragmentActivity fragmentActivity) {

        if(model!=null){

            model.newPostCompleteFragment(idPost,fragmentActivity);
        }

    }

    @Override
    public void newProfileFragmentPresenter(String userName, FragmentActivity fragmentActivity) {

        if(model!=null){

            model.newProfileFragment(userName,fragmentActivity);
        }
    }
}
