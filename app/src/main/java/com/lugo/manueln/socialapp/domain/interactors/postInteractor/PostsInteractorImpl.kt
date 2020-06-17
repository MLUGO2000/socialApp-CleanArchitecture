package com.lugo.manueln.socialapp.domain.interactors.postInteractor

import android.app.Activity
import android.support.v4.app.FragmentActivity

import com.lugo.manueln.socialapp.data.WebService.JsonPostApi
import com.lugo.manueln.socialapp.presentation.Profile.View.ProfileFragment
import com.lugo.manueln.socialapp.BaseApplication
import com.lugo.manueln.socialapp.R
import com.lugo.manueln.socialapp.domain.Post
import com.lugo.manueln.socialapp.presentation.PostComplete.View.PostCompleteFragment
import com.lugo.manueln.socialapp.presentation.Posts.PostContract
import io.reactivex.Observer



import javax.inject.Inject


import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PostsInteractorImpl( var miPresenter: PostContract.presenter?) : PostsInteractor {


    @Inject
    lateinit var jsonPostApi: JsonPostApi


    override fun getPosts(main: FragmentActivity?) {



        setupDagger(main)

        jsonPostApi!!.postsObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object :Observer<List<Post>> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(posts: List<Post>) {

                        sendPosts(posts)
                    }

                    override fun onError(e: Throwable) {

                        sendErrorPost(e.message)
                    }

                    override fun onComplete() {


                    }
                })


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


    override fun sendPosts(miListaPost: List<Post>) {

        miPresenter!!.sendPostPresenter(miListaPost)
    }

    override fun sendErrorPost(error: String?) {
        if (miPresenter != null) {

            miPresenter!!.sendErrorPostPresenter(error)
        }
    }

    override fun newPostCompleteFragment(idPost: Int, miActivity: FragmentActivity) {
        val fragment = PostCompleteFragment.newInstance(idPost)

        miActivity.supportFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.contenedorFragment, fragment).commit()

    }

    override fun newProfileFragment(userName: String, fragmentActivity: FragmentActivity) {

        val profileFragment = ProfileFragment.newInstance(userName)

        fragmentActivity.supportFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.contenedorFragment, profileFragment).commit()
    }

    private fun setupDagger(activity: Activity?) {

        (activity?.application as BaseApplication).componentApi.inject(this)


    }
}




