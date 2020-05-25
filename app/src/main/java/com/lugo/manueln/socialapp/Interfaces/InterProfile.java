package com.lugo.manueln.socialapp.Interfaces;

import android.support.v4.app.FragmentActivity;

import com.lugo.manueln.socialapp.objects.Post;
import com.lugo.manueln.socialapp.objects.Profile;

import java.util.List;

public interface InterProfile {

    interface view{
        void showProfile(Profile profile, List<Post>postList);

    }

    interface presenter{

        void loadProfilePresenter(String user,FragmentActivity activity);
        void showProfilePresenter(Profile profile, List<Post>postList);

    }

    interface interactor{

        void getPostListUser(String user,FragmentActivity activity);
        
    }
}
