package com.lugo.manueln.socialapp.presentation.PostComplete.Presenter

import android.support.v4.app.FragmentActivity

import com.lugo.manueln.socialapp.domain.interactors.postCompleteInteractor.InteractorPostCompleteImpl
import com.lugo.manueln.socialapp.domain.interactors.postCompleteInteractor.InteractorPostComplete
import com.lugo.manueln.socialapp.domain.Comments
import com.lugo.manueln.socialapp.domain.Post
import com.lugo.manueln.socialapp.presentation.PostComplete.PostCompleteContract
import com.lugo.manueln.socialapp.usecases.PostComplete.GetCommentsPost
import com.lugo.manueln.socialapp.usecases.PostComplete.GetPostComplete
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PresenterPostComplete(private var view: PostCompleteContract.view?,private val getPostComplete: GetPostComplete,private val getCommentsPost:GetCommentsPost) : PostCompleteContract.presenter {


    override fun loadPostCompleteWithComments(id: Int) {

        view?.showProgressBar()

        getPostComplete.invoke(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object :Observer<Post>{
            override fun onComplete() {

            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(post: Post) {

                if(view!=null){
                    view?.showPostComplete(post)
                }


            }

            override fun onError(e: Throwable) {

                if(view!=null){
                    view?.showErrorLoadPost(e.message)
                }
            }
        })


        getCommentsPost.invoke(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object :Observer<List<Comments>>{
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(listComments: List<Comments>) {

                        if(view!=null){
                            view?.showCommentsPost(listComments)

                            view?.hideProgressbar()
                        }

                    }

                    override fun onError(e: Throwable) {

                        if(view!=null){
                         view?.showErrorLoadPost(e.message)
                        }
                    }
                })



    }

    override fun PostCompleteWithCommentsPresenter(miPost: Post, commentsList: List<Comments>) {

        if (view != null) {
            view!!.showPostComplete(miPost)
            view!!.showCommentsPost(commentsList)
        }
    }

    override fun sendErrorLoadPost(error: String?) {

        if (view != null) {

            view!!.showErrorLoadPost(error)
        }

    }

    override fun saveComment(comment: Comments) {



    }

    override fun updatePostComplete() {

    }
}
