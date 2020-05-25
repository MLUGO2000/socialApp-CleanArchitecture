package com.lugo.manueln.socialapp.Interfaces;

import android.support.v4.app.FragmentActivity;

import com.lugo.manueln.socialapp.objects.Comments;
import com.lugo.manueln.socialapp.objects.Post;

import java.util.List;

public interface interPostComplete {

    interface view{

        void loadPostComplete();
        void showPostComplete(Post miPost);
        void showCommentsPost(List<Comments>commentsList);
        void showErrorLoadPost(String error);
        void updatePostComplete();


    }

    interface presenter{

        void loadPostCompleteWithComments(int id, FragmentActivity fragmentActivity);
        void PostCompleteWithCommentsPresenter(Post miPost, List<Comments> commentsList);
        void sendErrorLoadPost(String error);
        void saveComment(Comments comment);
        void updatePostComplete();



    }

    interface interactor{

        void retrofitGetPostCompleteWithComments(int idPost,FragmentActivity fragmentActivity);
        void sendErrorLoadPost(String error);
        void saveCommentPost(Comments comment);

    }
}
