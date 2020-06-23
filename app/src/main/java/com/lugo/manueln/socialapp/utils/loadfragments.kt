package com.lugo.manueln.socialapp.utils

import com.lugo.manueln.socialapp.presentation.MenuF.MenuFragment
import com.lugo.manueln.socialapp.presentation.Views.PostComplete.View.PostCompleteFragment
import com.lugo.manueln.socialapp.presentation.Views.Posts.View.PostsFragment

interface loadfragments : PostsFragment.OnFragmentInteractionListener, PostCompleteFragment.OnFragmentInteractionListener, MenuFragment.OnFragmentInteractionListener
