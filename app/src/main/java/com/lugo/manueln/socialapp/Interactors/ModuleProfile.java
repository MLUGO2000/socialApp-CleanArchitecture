package com.lugo.manueln.socialapp.Interactors;

import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.lugo.manueln.socialapp.Interactors.WebService.JsonPostApi;
import com.lugo.manueln.socialapp.Interactors.di.BaseApplication;
import com.lugo.manueln.socialapp.Interfaces.InterProfile;
import com.lugo.manueln.socialapp.objects.Post;
import com.lugo.manueln.socialapp.objects.Profile;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ModuleProfile implements InterProfile.interactor {

    InterProfile.presenter presenter;
    @Inject
    JsonPostApi jsonPostApi;

    public ModuleProfile(InterProfile.presenter presenter) {

        this.presenter=presenter;
    }


    public void getPostListUser(final String user, FragmentActivity fragmentActivity){

        setupDagger(fragmentActivity);

        jsonPostApi.getListPostUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Post>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Post> posts) {

                        sendPostListUser(user,posts);
                    }

                    @Override
                    public void onError(Throwable e) {

                        sendError(e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                       Log.e("onComplete",Thread.currentThread().getName());
                    }
                });


    }

    private void sendError(String message) {

        Log.e("error",message);
    }

    private void sendPostListUser(String user, final List<Post> posts) {

        jsonPostApi.getProfileUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Profile>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Profile> profiles) {

                        if(profiles.size()<2){
                            presenter.showProfilePresenter(profiles.get(0),posts);
                        }
                        Log.i("PROFILE", "Profile: " + profiles.get(0).getName());
                    }


                    @Override
                    public void onError(Throwable e) {

                        sendError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    private void setupDagger(FragmentActivity fragmentActivity) {

        ((BaseApplication)fragmentActivity.getApplication()).getComponentApi().inject(this);
    }

}
