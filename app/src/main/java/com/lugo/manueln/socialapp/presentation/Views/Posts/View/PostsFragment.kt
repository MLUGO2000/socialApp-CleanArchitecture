package com.lugo.manueln.socialapp.presentation.Views.Posts.View

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.lugo.manueln.socialapp.BaseApplication

import com.lugo.manueln.socialapp.presentation.Adapters.AdapterPosts
import com.lugo.manueln.socialapp.presentation.Views.Posts.Presenter.PresenterPosts
import com.lugo.manueln.socialapp.R
import com.lugo.manueln.socialapp.domain.Post
import com.lugo.manueln.socialapp.presentation.Views.PostComplete.View.PostCompleteFragment
import com.lugo.manueln.socialapp.presentation.Views.Posts.PostContract
import com.lugo.manueln.socialapp.presentation.Views.Profile.View.ProfileFragment
import com.lugo.manueln.socialapp.usecases.Posts.GetListPosts
import kotlinx.android.synthetic.main.fragment_posts.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PostsFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [PostsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PostsFragment : Fragment(), PostContract.view ,AdapterPosts.OnPostListener{


    @Inject
    lateinit var getPosts: GetListPosts


    private lateinit var presenter: PostContract.presenter


    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null

    private var mListener: OnFragmentInteractionListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        (activity?.application as BaseApplication).componentApi.inject(this)

        return inflater.inflate(R.layout.fragment_posts, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        presenter = PresenterPosts(this, getPosts)

        rvPosts.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        loadRecyclerPost()
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

    override fun loadRecyclerPost() {

        if (presenter != null) {
            progressBarRecycler.visibility = ProgressBar.VISIBLE
            presenter.loadRecyclerPostPresenter()
        }
    }

    override fun showRecycler(miListaPost: List<Post>) {

        val miAdapter = AdapterPosts(miListaPost, this)
        rvPosts.adapter = miAdapter

        progressBarRecycler.visibility = ProgressBar.GONE


    }

    override fun errorLoadRecyclerPost(error: String?) {
        Toast.makeText(context, "Se Produjo un Error de Tipo $error", Toast.LENGTH_LONG).show()
    }

    override fun newPostCompleteFragment(idPost: Int) {



        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.contenedorFragment,PostCompleteFragment.newInstance(idPost))?.addToBackStack(null)?.commit()

    }

    override fun newProfileFragment(userName: String) {
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.contenedorFragment,ProfileFragment.newInstance(userName))?.addToBackStack(null)?.commit()
    }

    override fun onPostClick(idPost: Int) {
        newPostCompleteFragment(idPost)
    }

    override fun onProfileClick(userName: String) {
        newProfileFragment(userName)
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
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PostsFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): PostsFragment {
            val fragment = PostsFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}
