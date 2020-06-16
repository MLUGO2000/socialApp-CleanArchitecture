package com.lugo.manueln.socialapp.presentation.PostComplete.View

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

import com.lugo.manueln.socialapp.presentation.Adapters.AdapterComments
import com.lugo.manueln.socialapp.domain.models.Comments
import com.lugo.manueln.socialapp.domain.models.Post
import com.lugo.manueln.socialapp.presentation.PostComplete.Presenter.PresenterPostComplete
import com.lugo.manueln.socialapp.R
import com.lugo.manueln.socialapp.presentation.PostComplete.PostCompleteContract
import kotlinx.android.synthetic.main.fragment_post_complete.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PostCompleteFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [PostCompleteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PostCompleteFragment : Fragment(), PostCompleteContract.view {

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
        return inflater.inflate(R.layout.fragment_post_complete, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        myPresenter = PresenterPostComplete(this)

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

        val myComment = Comments()

        myComment.postId = idPost
        myComment.body = comment
        myComment.userName = "Manuel"

        myPresenter!!.saveComment(myComment)


    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
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
        mListener = null
    }


    override fun loadPostComplete() {

        if (myPresenter != null) {

            progressBarPostComplete.visibility = ProgressBar.VISIBLE

            myPresenter!!.loadPostCompleteWithComments(idPost, this.activity)
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

    override fun updatePostComplete() {
        loadPostComplete()
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