package com.lugo.manueln.socialapp.Interactors.di;

import com.lugo.manueln.socialapp.Interactors.ModuleAlbums;
import com.lugo.manueln.socialapp.Interactors.ModulePhotos;
import com.lugo.manueln.socialapp.Interactors.ModulePostComplete;
import com.lugo.manueln.socialapp.Interactors.ModulePosts;
import com.lugo.manueln.socialapp.Interactors.ModuleProfile;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = moduleApi.class)
public interface componentApi {

    void inject(ModulePosts modulePosts);
    void inject(ModulePostComplete modulePostComplete);
    void inject(ModuleAlbums moduleAlbums);
    void inject(ModulePhotos modulePhotos);
    void inject(ModuleProfile moduleProfile);

}
