package com.lugo.manueln.socialapp.presentation.Views.Profile.View


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.lugo.manueln.socialapp.BaseApplication

import com.lugo.manueln.socialapp.presentation.Adapters.AdapterProfilePhotos
import com.lugo.manueln.socialapp.presentation.Views.Profile.Presenter.ProfilePresenter
import com.lugo.manueln.socialapp.R
import com.lugo.manueln.socialapp.domain.Post
import com.lugo.manueln.socialapp.domain.Profile
import com.lugo.manueln.socialapp.presentation.Views.Profile.ProfileContract
import com.lugo.manueln.socialapp.usecases.Profile.GetListPostUser
import com.lugo.manueln.socialapp.usecases.Profile.GetProfileUser
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment(), ProfileContract.view {

    private lateinit var presenter: ProfileContract.presenter

    @Inject
    lateinit var getListPostUser:GetListPostUser

    @Inject
    lateinit var getProfileUser:GetProfileUser

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

        (activity?.application as BaseApplication).componentApi.inject(this)

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = ProfilePresenter(this,getListPostUser,getProfileUser)

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


    override fun showListPostsProfile(postList: List<Post>) {


        val myAdapter = AdapterProfilePhotos(postList)

        rvProfilePost.adapter = myAdapter


    }

    override fun showProfile(profile: Profile?) {

        txtUserNameProfile.text = profile?.name
        txtNumberPosts.text = profile?.numberPosts.toString()

        loadImageProfile(profile?.imageProfile)

    }

    override fun showError(error: String?) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    private fun loadImageProfile(url: String?) {


    }



    override fun onDetach() {
        super.onDetach()
        presenter.detachView()
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
