package com.lugo.manueln.socialapp.Interactors;

import android.support.v4.app.FragmentActivity;
import android.util.Log;


import com.lugo.manueln.socialapp.Interactors.WebService.JsonPostApi;
import com.lugo.manueln.socialapp.R;
import com.lugo.manueln.socialapp.Views.PhotosFragment;
import com.lugo.manueln.socialapp.objects.Album;
import com.lugo.manueln.socialapp.Interactors.di.BaseApplication;
import com.lugo.manueln.socialapp.Interfaces.InterAlbums;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModuleAlbums implements InterAlbums.Interactor {

    InterAlbums.Presenter myPresenter;

    @Inject
    JsonPostApi jsonPostApi;

    public ModuleAlbums(InterAlbums.Presenter presenter){

        myPresenter=presenter;

    }

    @Override
    public void loadRecyclerAlbumsInteractor(FragmentActivity activity) {

        setugDagger(activity);

        final Call<List<Album>> listCallAlbum=jsonPostApi.getAlbums();

        listCallAlbum.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                if(!response.isSuccessful()){

                    Log.e("Error" ,"Error codigo: " + response.code());

                    sendErrorLoadAlbum("Error codigo " + response.code());
                }else {

                    List<Album> albumList=response.body();

                    myPresenter.resultRecyclerAlbumsPresenter(albumList);
                }
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

                Log.e("Failure" ,"Error Failure : " + t.getMessage());

                sendErrorLoadAlbum("Error Failure : " + t.getMessage());
                
            }
        });


    }

    @Override
    public void newPhotosFragmentInteractor(int albumId, FragmentActivity activity) {

        PhotosFragment photosFragment=PhotosFragment.newInstance(albumId);
        activity.getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.contenedorFragment,photosFragment).commit();
    }

    @Override
    public void sendErrorLoadAlbum(String error) {

        if(myPresenter!=null){

            myPresenter.sendErrorLoadAlbumPresenter(error);
        }

    }


    private void setugDagger(FragmentActivity activity) {
        ((BaseApplication)activity.getApplication()).getComponentApi().inject(this);
    }

}
