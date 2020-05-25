package com.lugo.manueln.socialapp.Interactors;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;


import com.lugo.manueln.socialapp.Interfaces.interPostComplete;
import com.lugo.manueln.socialapp.objects.Comments;
import com.lugo.manueln.socialapp.Interactors.WebService.JsonPostApi;
import com.lugo.manueln.socialapp.objects.Post;
import com.lugo.manueln.socialapp.Interactors.di.BaseApplication;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModulePostComplete implements interPostComplete.interactor {

    @Inject
    JsonPostApi jsonPostApi;

    interPostComplete.presenter myPresenter;

    public ModulePostComplete(interPostComplete.presenter myPresenter) {
        this.myPresenter = myPresenter;
    }

    @Override
    public void retrofitGetPostCompleteWithComments(int idPost, FragmentActivity fragmentActivity) {

        setupDagger(fragmentActivity);

        getPostComplete(idPost);


    }



    private void getPostComplete(int id) {



        jsonPostApi.getPostCompleteObservable(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Post>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Post post) {

                        getComments(post);
                    }

                    @Override
                    public void onError(Throwable e) {

                        sendErrorLoadPost(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        ////////////////////

       /* Call<Post> callPost=jsonPostApi.getPostComplete(id);

        callPost.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){

                    Log.e("Error","Error Code " + response.code());

                    sendErrorLoadPost("Error Code " + response.code());
                }else{

                   Post miPost=response.body();

                   getComments(miPost);

                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

                Log.e("Falla",t.getMessage());

                sendErrorLoadPost("Falla: " + t.getMessage());

            }
        });*/


    }

    private void getComments(@NonNull final Post miPostComplete) {


        jsonPostApi.getCommentsFilterObservable(miPostComplete.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Comments>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Comments> comments) {

                        myPresenter.PostCompleteWithCommentsPresenter(miPostComplete,comments);
                    }

                    @Override
                    public void onError(Throwable e) {

                        sendErrorLoadPost("No cargaron Comentarios por error :" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        ///////////////////////
/*
        Call<List<Comments>> listCallComments=jsonPostApi.getCommentsFilter(miPostComplete.getId());

        listCallComments.enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {
                if(!response.isSuccessful()){

                    Log.e("Error","Error Code " + response.code());

                    sendErrorLoadPost("Error Code " + response.code());

                }else{

                    List<Comments> commentsList=response.body();

                    myPresenter.PostCompleteWithCommentsPresenter(miPostComplete,commentsList);


                }
            }

            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {

                Log.e("ErrorOnFailure","Error: " + t.getMessage());

                sendErrorLoadPost("Error: " + t.getMessage());
            }
        });
*/
    }

    @Override
    public void sendErrorLoadPost(String error) {

        if(myPresenter!=null){

            myPresenter.sendErrorLoadPost(error);
        }

    }

    @Override
    public void saveCommentPost(Comments comment) {

        jsonPostApi.saveComment(comment).enqueue(new Callback<Comments>() {
            @Override
            public void onResponse(Call<Comments> call, Response<Comments> response) {
                if(!response.isSuccessful()){

                    sendErrorLoadPost(String.valueOf(response.code()));

                }else {

                    myPresenter.updatePostComplete();
                }

            }

            @Override
            public void onFailure(Call<Comments> call, Throwable t) {

                sendErrorLoadPost(t.getMessage());
            }
        });
        
    }

    private void setupDagger(FragmentActivity activity) {
        ((BaseApplication)activity.getApplication()).getComponentApi().inject(this);
    }
}
