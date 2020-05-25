package com.lugo.manueln.socialapp.Views;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lugo.manueln.socialapp.Adapters.AdapterProfilePhotos;
import com.lugo.manueln.socialapp.Interfaces.InterProfile;
import com.lugo.manueln.socialapp.Presenters.ProfilePresenter;
import com.lugo.manueln.socialapp.R;
import com.lugo.manueln.socialapp.objects.Post;
import com.lugo.manueln.socialapp.objects.Profile;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements InterProfile.view {

    InterProfile.presenter presenter;

    private static final String ARG_USERNAME = "userName";



    private String userName;

    public ProfileFragment() {

        presenter=new ProfilePresenter(this);
    }

    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String paramUserName) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USERNAME, paramUserName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userName = getArguments().getString(ARG_USERNAME);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_profile, container, false);

       loadViews(view);

       loadProfile();
       return view;
    }

    private void loadViews(View view) {

        txtUserName=view.findViewById(R.id.txtUserNameProfile);
        txtNumberPosts=view.findViewById(R.id.txtNumberPost);
        imageProfile=view.findViewById(R.id.imageProfile);

        myRecyclerPostProfile=view.findViewById(R.id.recyclerProfilePost);

        GridLayoutManager layoutManager=new GridLayoutManager(this.getContext(),3,GridLayoutManager.VERTICAL,false);

        myRecyclerPostProfile.setLayoutManager(layoutManager);




    }

    private void loadProfile() {

        if(presenter!=null){

            presenter.loadProfilePresenter(userName,this.getActivity());
        }


    }


    @Override
    public void showProfile(Profile profile, List<Post> postList) {

        showProfile(profile);

        AdapterProfilePhotos myAdapter=new AdapterProfilePhotos(postList);

        myRecyclerPostProfile.setAdapter(myAdapter);


    }

    private void showProfile(Profile profile) {

        txtUserName.setText(profile.getName());
        txtNumberPosts.setText(String.valueOf(profile.getNumberPosts()));

        loadImageProfile(profile.getImageProfile());

    }

    private void loadImageProfile(String url) {



    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    RecyclerView myRecyclerPostProfile;
    TextView txtUserName,txtNumberPosts;
    ImageView imageProfile;
}
