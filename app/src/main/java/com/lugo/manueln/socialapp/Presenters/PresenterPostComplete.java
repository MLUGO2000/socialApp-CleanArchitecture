package com.lugo.manueln.socialapp.Presenters;

import android.support.v4.app.FragmentActivity;

import com.lugo.manueln.socialapp.Interactors.ModulePostComplete;
import com.lugo.manueln.socialapp.Interfaces.interPostComplete;
import com.lugo.manueln.socialapp.objects.Comments;
import com.lugo.manueln.socialapp.objects.Post;

import java.util.List;

public class PresenterPostComplete implements interPostComplete.presenter{

    interPostComplete.view view;
    interPostComplete.interactor interactor;
    public PresenterPostComplete(interPostComplete.view view) {

        this.view=view;
        interactor=new ModulePostComplete(this);
    }

    @Override
    public void loadPostCompleteWithComments(int id, FragmentActivity fragmentActivity) {

        interactor.retrofitGetPostCompleteWithComments(id,fragmentActivity);
    }

    @Override
    public void PostCompleteWithCommentsPresenter(Post miPost, List<Comments> commentsList) {

        if(view!=null){
            view.showPostComplete(miPost);
            view.showCommentsPost(commentsList);
        }
    }

    @Override
    public void sendErrorLoadPost(String error) {

        if(view!=null){

            view.showErrorLoadPost(error);
        }

    }

    @Override
    public void saveComment(Comments comment) {

        interactor.saveCommentPost(comment);

    }

    @Override
    public void updatePostComplete() {


    }
}
