package com.lugo.manueln.socialapp.Interfaces;

import com.lugo.manueln.socialapp.Views.AlbumsFragment;
import com.lugo.manueln.socialapp.Views.MenuFragment;
import com.lugo.manueln.socialapp.Views.PhotosFragment;
import com.lugo.manueln.socialapp.Views.PostCompleteFragment;
import com.lugo.manueln.socialapp.Views.PostsFragment;

public interface loadfragments extends PostsFragment.OnFragmentInteractionListener,PostCompleteFragment.OnFragmentInteractionListener,AlbumsFragment.OnFragmentInteractionListener,PhotosFragment.OnFragmentInteractionListener, MenuFragment.OnFragmentInteractionListener {
}
