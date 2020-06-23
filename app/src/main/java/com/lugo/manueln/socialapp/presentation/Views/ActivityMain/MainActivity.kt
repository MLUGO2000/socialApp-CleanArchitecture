package com.lugo.manueln.socialapp.presentation.Views.ActivityMain

import android.net.Uri
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.lugo.manueln.socialapp.R
import com.lugo.manueln.socialapp.utils.loadfragments
import com.lugo.manueln.socialapp.presentation.Views.Posts.View.PostsFragment
import com.lugo.manueln.socialapp.utils.Utilidad

class MainActivity : AppCompatActivity(), loadfragments {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(Utilidad.FRAGMENT_ALBUM)
    }

    override fun onFragmentInteraction(uri: Uri) {

    }

    override fun loadFragment(i: Int) {

        var fragmentNew: Fragment? = null
        var selectFragment = false

        when (i) {

            Utilidad.FRAGMENT_POST -> {

                fragmentNew = PostsFragment()
                selectFragment = true
            }
        }

        if (selectFragment) {
            supportFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.contenedorFragment, fragmentNew!!).commit()
        }


    }
}
