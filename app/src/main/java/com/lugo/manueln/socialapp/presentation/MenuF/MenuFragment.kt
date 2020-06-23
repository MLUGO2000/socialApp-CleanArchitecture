package com.lugo.manueln.socialapp.presentation.MenuF

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup

import com.lugo.manueln.socialapp.R
import com.lugo.manueln.socialapp.utils.Utilidad

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [MenuFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 */
class MenuFragment : Fragment(), BottomNavigationView.OnNavigationItemSelectedListener {


    private var mListener: OnFragmentInteractionListener? = null

    lateinit var myNavigation: BottomNavigationView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu, container, false)

        myNavigation = view.findViewById(R.id.NavigationViewMenu)

        myNavigation.setOnNavigationItemSelectedListener(this)

        return view
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

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {

        var fragmentSelec = 0

        when (menuItem.itemId) {

            R.id.itemPost ->

                fragmentSelec = Utilidad.FRAGMENT_POST

            R.id.itemAlbum ->

                fragmentSelec = Utilidad.FRAGMENT_ALBUM
        }

        mListener!!.loadFragment(fragmentSelec)
        return false
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

        fun loadFragment(i: Int)
    }
}
