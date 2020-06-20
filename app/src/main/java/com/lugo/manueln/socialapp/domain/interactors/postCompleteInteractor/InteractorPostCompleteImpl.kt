package com.lugo.manueln.socialapp.domain.interactors.postCompleteInteractor

import android.support.v4.app.FragmentActivity



import com.lugo.manueln.socialapp.data.WebService.JsonPostApi
import com.lugo.manueln.socialapp.domain.Comments
import com.lugo.manueln.socialapp.domain.Post
import com.lugo.manueln.socialapp.BaseApplication
import com.lugo.manueln.socialapp.presentation.PostComplete.PostCompleteContract

import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InteractorPostCompleteImpl(var myPresenter: PostCompleteContract.presenter) : InteractorPostComplete {

    //@Inject
    lateinit var jsonPostApi: JsonPostApi

    override fun retrofitGetPostCompleteWithComments(idPost: Int, fragmentActivity: FragmentActivity?) {


        getPostComplete(idPost)


    }


    private fun getPostComplete(id: Int) {


        jsonPostApi!!.getPostCompleteObservable(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Post> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(post: Post) {

                        getComments(post)
                    }

                    override fun onError(e: Throwable) {

                        sendErrorLoadPost(e.message)
                    }

                    override fun onComplete() {

                    }
                })
        ////////////////////

        /* Call<Post> callPost=jsonPostApi.GetPostComplete(id);

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

    private fun getComments(miPostComplete: Post) {


        jsonPostApi!!.getCommentsFilterObservable(miPostComplete.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<List<Comments>> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(comments: List<Comments>) {

                        myPresenter!!.PostCompleteWithCommentsPresenter(miPostComplete, comments)
                    }

                    override fun onError(e: Throwable) {

                        sendErrorLoadPost("No cargaron Comentarios por error :" + e.message)
                    }

                    override fun onComplete() {

                    }
                })
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

    override fun sendErrorLoadPost(error: String?) {

        if (myPresenter != null) {

            myPresenter!!.sendErrorLoadPost(error)
        }

    }

    override fun saveCommentPost(comment: Comments) {

        jsonPostApi!!.saveComment(comment).enqueue(object : Callback<Comments> {
            override fun onResponse(call: Call<Comments>, response: Response<Comments>) {
                if (!response.isSuccessful) {

                    sendErrorLoadPost(response.code().toString())

                } else {

                    myPresenter!!.updatePostComplete()
                }

            }

            override fun onFailure(call: Call<Comments>, t: Throwable) {

                sendErrorLoadPost(t.message)
            }
        })

    }

}
