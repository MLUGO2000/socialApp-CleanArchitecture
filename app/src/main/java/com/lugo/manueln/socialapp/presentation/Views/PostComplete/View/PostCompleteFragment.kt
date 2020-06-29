package com.lugo.manueln.socialapp.presentation.Views.PostComplete.View

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.lugo.manueln.socialapp.BaseApplication

import com.lugo.manueln.socialapp.presentation.Adapters.AdapterComments
import com.lugo.manueln.socialapp.domain.Comments
import com.lugo.manueln.socialapp.presentation.Views.PostComplete.Presenter.PresenterPostComplete
import com.lugo.manueln.socialapp.R
import com.lugo.manueln.socialapp.domain.Post
import com.lugo.manueln.socialapp.presentation.Views.PostComplete.PostCompleteContract
import com.lugo.manueln.socialapp.usecases.PostComplete.GetCommentsPost
import com.lugo.manueln.socialapp.usecases.PostComplete.GetPostComplete
import com.lugo.manueln.socialapp.usecases.PostComplete.SaveCommentPost
import kotlinx.android.synthetic.main.fragment_post_complete.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PostCompleteFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [PostCompleteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PostCompleteFragment : Fragment(), PostCompleteContract.view {

    @Inject
    lateinit var getPostComplete: GetPostComplete

    @Inject
    lateinit var getCommentsPost: GetCommentsPost

    @Inject
    lateinit var saveCommentPost: SaveCommentPost

    private lateinit var myPresenter: PostCompleteContract.presenter


    // TODO: Rename and change types of parameters
    private var idPost: Int = 0


    private var mListener: OnFragmentInteractionListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            idPost = arguments!!.getInt(ARG_IDPOST)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        (activity?.application as BaseApplication).componentApi.inject(this)

        return inflater.inflate(R.layout.fragment_post_complete, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        myPresenter = PresenterPostComplete( this,getPostComplete,getCommentsPost,saveCommentPost)

        cargarViews()

        loadPostComplete()
    }

    private fun cargarViews() {

        val layoutManager = LinearLayoutManager(this.context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        rvComments.layoutManager = layoutManager

        bComment.setOnClickListener { sendComment() }

    }

    private fun sendComment() {

        val comment = userComment.text.toString()

        val myComment = Comments(idPost,0,"Manuel","",comment)

        myPresenter.saveComment(myComment)

    }

    override fun cleanFieldComment() {
        userComment.setText("")
    }


    override fun loadPostComplete() {

        if (myPresenter != null){
            myPresenter.loadPostCompleteWithComments(idPost)
        }
    }

    override fun showPostComplete(miPost: Post) {

        textTitlePost.text = miPost.title
        textPost.text = miPost.body

    }

    override fun showCommentsPost(commentsList: List<Comments>) {

        val myAdapterComment = AdapterComments(commentsList)

        rvComments.adapter = myAdapterComment

        progressBarPostComplete.visibility = ProgressBar.GONE
    }

    override fun showErrorLoadPost(error: String?) {

    }

    override fun showErrorSaveComment(error: String?) {

    }

    override fun updatePostComplete() {
        loadPostComplete()
    }

    override fun showProgressBar() {
        progressBarPostComplete.visibility = ProgressBar.VISIBLE
    }

    override fun hideProgressbar() {
        progressBarPostComplete.visibility = ProgressBar.GONE
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        myPresenter.detachView()
        mListener = null
    }




    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_IDPOST = "idPost"


        // TODO: Rename and change types and number of parameters
        fun newInstance(paramIdPost: Int): PostCompleteFragment {
            val fragment = PostCompleteFragment()
            val args = Bundle()
            args.putInt(ARG_IDPOST, paramIdPost)
            fragment.arguments = args
            return fragment
        }
    }
}
