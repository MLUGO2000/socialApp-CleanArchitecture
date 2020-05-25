package com.lugo.manueln.socialapp.Interactors;

import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.lugo.manueln.socialapp.Interactors.WebService.JsonPostApi;
import com.lugo.manueln.socialapp.Interactors.di.BaseApplication;
import com.lugo.manueln.socialapp.Interfaces.InterPhotos;
import com.lugo.manueln.socialapp.objects.Photo;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModulePhotos implements InterPhotos.Interactor {

    InterPhotos.Presenter presenter;

    @Inject
    JsonPostApi jsonPostApi;

    public ModulePhotos(InterPhotos.Presenter presenter){

        this.presenter=presenter;
    }


    @Override
    public void loadPhotosRecyclerInteractor(int idAlbum,FragmentActivity activity) {

        setupDagger(activity);

        Call<List<Photo>> call=jsonPostApi.getPhotosAlbum(idAlbum);

        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {

                if(!response.isSuccessful()){

                    Log.e("Error","Error Code: " + response.code());
                }else {

                    List<Photo> photoList=response.body();

                    presenter.showPhotosRecycelerPresenter(photoList);

                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {

                Log.e("Failure","Failures :" + t.getMessage());

            }
        });


    }

    private void setupDagger(FragmentActivity activity) {

        ((BaseApplication)activity.getApplication()).getComponentApi().inject(this);

    }
}
