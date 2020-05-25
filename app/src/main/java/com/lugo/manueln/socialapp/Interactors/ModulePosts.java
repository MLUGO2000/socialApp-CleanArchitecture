package com.lugo.manueln.socialapp.Interactors;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;

import com.lugo.manueln.socialapp.Interfaces.InterPost;
import com.lugo.manueln.socialapp.Interactors.WebService.JsonPostApi;
import com.lugo.manueln.socialapp.Views.ProfileFragment;
import com.lugo.manueln.socialapp.objects.Post;
import com.lugo.manueln.socialapp.Interactors.di.BaseApplication;
import com.lugo.manueln.socialapp.R;
import com.lugo.manueln.socialapp.Views.PostCompleteFragment;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModulePosts implements InterPost.interactor {


    @Inject
    JsonPostApi jsonPostApi;



    InterPost.presenter miPresenter;


    public ModulePosts(InterPost.presenter miPresenter){
        this.miPresenter=miPresenter;
    }




    @Override
    public void getPosts(final FragmentActivity main) {


        setupDagger(main);

        jsonPostApi.getPostsObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Post>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Post> posts) {

                        sendPosts(posts);
                    }

                    @Override
                    public void onError(Throwable e) {

                        sendErrorPost(e.getMessage());
                    }

                    @Override
                    public void onComplete() {


                    }
                });


            /*Call<List<Post>> call=jsonPostApi.getPosts();

            call.enqueue(new Callback<List<Post>>() {
                public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                    if(!response.isSuccessful()){
                        Log.e("Error","Error Code " + response.code());

                        sendErrorPost("Error Code : " + response.code());
                    }else {

                        List<Post> postList = response.body();



                        sendPosts(postList);


                    }

                }
                public void onFailure(Call<List<Post>> call, Throwable t) {

                    Log.e("Falla",t.getMessage());

                    sendErrorPost(t.getMessage());
                }
            });*/
        }


    @Override
    public void sendPosts(List<Post> miListaPost) {

        miPresenter.sendPostPresenter(miListaPost);
    }

    @Override
    public void sendErrorPost(String error) {
        if(miPresenter!=null){

            miPresenter.sendErrorPostPresenter(error);
        }
    }

    @Override
    public void newPostCompleteFragment(int idPost,FragmentActivity miActivity) {
               PostCompleteFragment fragment=PostCompleteFragment.newInstance(idPost);

                miActivity.getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.contenedorFragment,fragment).commit();

    }

    @Override
    public void newProfileFragment(String userName, FragmentActivity fragmentActivity) {

        ProfileFragment profileFragment=ProfileFragment.newInstance(userName);

        fragmentActivity.getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.contenedorFragment,profileFragment).commit();
    }

    void setupDagger(Activity activity){

         ((BaseApplication)activity.getApplication()).getComponentApi().inject(this);


    }
}
