package com.lugo.manueln.socialapp.presentation.Views.PostComplete.Presenter


import com.lugo.manueln.socialapp.domain.Comments
import com.lugo.manueln.socialapp.domain.Post
import com.lugo.manueln.socialapp.presentation.Views.PostComplete.PostCompleteContract
import com.lugo.manueln.socialapp.usecases.PostComplete.GetCommentsPost
import com.lugo.manueln.socialapp.usecases.PostComplete.GetPostComplete
import com.lugo.manueln.socialapp.usecases.PostComplete.SaveCommentPost
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PresenterPostComplete(private var view: PostCompleteContract.view?, private val getPostComplete: GetPostComplete, private val getCommentsPost: GetCommentsPost, private val saveCommentPost: SaveCommentPost) : PostCompleteContract.presenter {


    override fun loadPostCompleteWithComments(id: Int) {

        view?.showProgressBar()

        loadPostComplete(id)
        loadComments(id)


    }

    private fun loadComments(id: Int) {

        getCommentsPost.invoke(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<List<Comments>> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(listComments: List<Comments>) {

                        if (isViewAttach()) {
                            view?.showCommentsPost(listComments)

                            view?.hideProgressbar()
                        }

                    }

                    override fun onError(e: Throwable) {

                        if (isViewAttach()) {
                            view?.showErrorLoadPost(e.message)
                        }
                    }
                })

    }

    private fun loadPostComplete(id: Int) {

        getPostComplete.invoke(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Post> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(post: Post) {

                        if (isViewAttach()) {
                            view?.showPostComplete(post)
                        }


                    }

                    override fun onError(e: Throwable) {

                        if (isViewAttach()) {
                            view?.showErrorLoadPost(e.message)
                        }
                    }
                })


    }

    override fun PostCompleteWithCommentsPresenter(miPost: Post, commentsList: List<Comments>) {

        if (isViewAttach()) {
            view?.showPostComplete(miPost)
            view?.showCommentsPost(commentsList)
        }
    }

    override fun sendErrorLoadPost(error: String?) {

        if (isViewAttach()) {

            view?.showErrorLoadPost(error)
        }

    }

    override fun saveComment(comment: Comments) {

        saveCommentPost.invoke(comment).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : Observer<Comments> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(t: Comments) {

                        if(isViewAttach()){
                            view?.cleanFieldComment()
                        }
                        loadComments(comment.postId)
                    }

                    override fun onError(e: Throwable) {

                        if (isViewAttach()) {

                            view?.hideProgressbar()

                            view?.showErrorSaveComment(e.message)
                        }


                    }
                })

    }

    override fun detachView(){
        view=null
    }



    override fun isViewAttach(): Boolean =view!=null

}
