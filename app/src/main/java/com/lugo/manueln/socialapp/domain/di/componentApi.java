package com.lugo.manueln.socialapp.domain.di;

import com.lugo.manueln.socialapp.domain.interactors.postCompleteInteractor.InteractorPostCompleteImpl;
import com.lugo.manueln.socialapp.domain.interactors.postInteractor.PostsInteractorImpl;
import com.lugo.manueln.socialapp.domain.interactors.profileInteractor.ProfileInteractorImpl;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = moduleApi.class)
public interface componentApi {

    void inject(PostsInteractorImpl postsInteractorImpl);
    void inject(InteractorPostCompleteImpl modulePostComplete);
    void inject(ModuleAlbums moduleAlbums);
    void inject(ModulePhotos modulePhotos);
    void inject(ProfileInteractorImpl profileInteractorImpl);

}
