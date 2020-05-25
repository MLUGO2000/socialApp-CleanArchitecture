package com.lugo.manueln.socialapp.Presenters;

import android.support.v4.app.FragmentActivity;

import com.lugo.manueln.socialapp.Interactors.ModuleProfile;
import com.lugo.manueln.socialapp.Interfaces.InterProfile;
import com.lugo.manueln.socialapp.objects.Post;
import com.lugo.manueln.socialapp.objects.Profile;

import java.util.List;

public class ProfilePresenter implements InterProfile.presenter {
    InterProfile.view view;
    InterProfile.interactor module;

    public ProfilePresenter(InterProfile.view view) {
        this.view=view;
        module=new ModuleProfile(this);
    }

    @Override
    public void loadProfilePresenter(String user,FragmentActivity activity) {

        if (module!=null){
            module.getPostListUser(user,activity);
        }
    }

    @Override
    public void showProfilePresenter(Profile profile, List<Post> postList) {

        if(view!=null){
            view.showProfile(profile,postList);
        }
    }
}
