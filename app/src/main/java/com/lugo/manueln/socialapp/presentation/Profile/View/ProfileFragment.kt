package com.lugo.manueln.socialapp.presentation.Profile.View


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.lugo.manueln.socialapp.presentation.Adapters.AdapterProfilePhotos
import com.lugo.manueln.socialapp.presentation.Profile.Presenter.ProfilePresenter
import com.lugo.manueln.socialapp.R
import com.lugo.manueln.socialapp.domain.Profile
import com.lugo.manueln.socialapp.presentation.Profile.ProfileContract
import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment(), ProfileContract.view {

    private lateinit var presenter: ProfileContract.presenter


    private var userName: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            userName = arguments!!.getString(ARG_USERNAME)
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = ProfilePresenter(this)

        loadViews(view)

        loadProfile()
    }

    private fun loadViews(view: View) {

        val layoutManager = GridLayoutManager(this.context, 3, GridLayoutManager.VERTICAL, false)

        rvProfilePost.layoutManager = layoutManager


    }

    private fun loadProfile() {

        if (presenter != null) {

            presenter!!.loadProfilePresenter(userName!!, this.activity!!)
        }


    }


    override fun showProfile(profile: Profile, postList: List<Post>) {

        showProfile(profile)

        val myAdapter = AdapterProfilePhotos(postList)

        rvProfilePost.adapter = myAdapter


    }

    private fun showProfile(profile: Profile) {

        txtUserNameProfile.text = profile.name
        txtNumberPosts.text = profile.numberPosts.toString()

        loadImageProfile(profile.imageProfile)

    }

    private fun loadImageProfile(url: String) {


    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    companion object {

        private val ARG_USERNAME = "userName"

        // TODO: Rename and change types and number of parameters
        fun newInstance(paramUserName: String): ProfileFragment {
            val fragment = ProfileFragment()
            val args = Bundle()
            args.putString(ARG_USERNAME, paramUserName)
            fragment.arguments = args
            return fragment
        }
    }
}
