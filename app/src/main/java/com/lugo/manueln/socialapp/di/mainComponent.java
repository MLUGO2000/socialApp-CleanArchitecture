package com.lugo.manueln.socialapp.di;

import android.content.Context;

import com.lugo.manueln.socialapp.domain.interactors.postCompleteInteractor.InteractorPostCompleteImpl;
import com.lugo.manueln.socialapp.presentation.PostComplete.View.PostCompleteFragment;
import com.lugo.manueln.socialapp.presentation.Posts.View.PostsFragment;
import com.lugo.manueln.socialapp.domain.interactors.profileInteractor.ProfileInteractorImpl;

import org.jetbrains.annotations.NotNull;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = moduleApi.class)
public interface mainComponent {

    void inject(PostsFragment postsFragment);
    void inject(PostCompleteFragment postCompleteFragment);
   // void inject(InteractorPostCompleteImpl modulePostComplete);
    void inject(ProfileInteractorImpl profileInteractorImpl);

    Context context();


}
