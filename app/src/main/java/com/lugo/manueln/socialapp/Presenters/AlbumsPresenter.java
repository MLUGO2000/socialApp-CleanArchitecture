package com.lugo.manueln.socialapp.Presenters;

import android.support.v4.app.FragmentActivity;

import com.lugo.manueln.socialapp.Interactors.ModuleAlbums;
import com.lugo.manueln.socialapp.Interfaces.InterAlbums;
import com.lugo.manueln.socialapp.objects.Album;

import java.util.List;

public class AlbumsPresenter implements InterAlbums.Presenter {
    InterAlbums.View myView;
    InterAlbums.Interactor myInteractor;

    public AlbumsPresenter(InterAlbums.View view){

        myView=view;
        myInteractor=new ModuleAlbums(this);

    }
    @Override
    public void loadRecyclerAlbumsPresenter(FragmentActivity activity) {

        if(myInteractor!=null){

            myInteractor.loadRecyclerAlbumsInteractor(activity);

        }

    }

    @Override
    public void resultRecyclerAlbumsPresenter(List<Album> albumList) {

        if(myView!=null){

            myView.showRecyclerAlbums(albumList);
        }
    }

    @Override
    public void newPhotosFragmentPresenter(int idAlbum, FragmentActivity fragmentActivity) {
        if(myInteractor!=null){

            myInteractor.newPhotosFragmentInteractor(idAlbum,fragmentActivity);
        }
    }

    @Override
    public void sendErrorLoadAlbumPresenter(String error) {

        if(myView!=null){

            myView.showErrorLoadAlbum(error);
        }
    }
}
