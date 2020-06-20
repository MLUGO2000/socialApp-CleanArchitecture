package com.lugo.manueln.socialapp.presentation.Posts.Presenter

import com.lugo.manueln.socialapp.domain.Post
import com.lugo.manueln.socialapp.presentation.Posts.PostContract
import com.lugo.manueln.socialapp.usecases.Posts.GetListPosts
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PresenterPosts(private var view: PostContract.view,private val getListPosts: GetListPosts) : PostContract.presenter {


    override fun loadRecyclerPostPresenter() {

        getListPosts.invoke().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<List<Post>> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable?) {

                    }

                    override fun onNext(listPost: List<Post>) {

                        view.showRecycler(listPost)
                    }

                    override fun onError(e: Throwable) {


                        view.errorLoadRecyclerPost(e.message)
                    }
                })


    }


    override fun sendPostPresenter(miListaPost: List<Post>) {

        if (view != null) {

            view!!.showRecycler(miListaPost)
        }

    }

    override fun sendErrorPostPresenter(error: String?) {
        if (view != null) {

            view!!.errorLoadRecyclerPost(error)
        }

    }

}
