package com.lugo.manueln.socialapp.Presenters;

import android.support.v4.app.FragmentActivity;

import com.lugo.manueln.socialapp.Interactors.ModulePhotos;
import com.lugo.manueln.socialapp.Interfaces.InterPhotos;
import com.lugo.manueln.socialapp.objects.Photo;

import java.util.List;

public class PhotosPresenter implements InterPhotos.Presenter {

    InterPhotos.View view;
    InterPhotos.Interactor interactor;

    public PhotosPresenter(InterPhotos.View view){
        this.view=view;
        interactor=new ModulePhotos(this);
    }

    @Override
    public void loadPhotosRecyclerPresenter(int idAlbum ,FragmentActivity activity) {

        if(interactor!=null){

            interactor.loadPhotosRecyclerInteractor(idAlbum,activity);
        }

    }

    @Override
    public void showPhotosRecycelerPresenter(List<Photo> list) {

        if(view!=null){

            view.showPhotosRecycler(list);
        }
    }
}
