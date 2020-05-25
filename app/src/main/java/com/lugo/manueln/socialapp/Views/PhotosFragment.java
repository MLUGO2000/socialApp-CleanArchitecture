package com.lugo.manueln.socialapp.Views;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lugo.manueln.socialapp.Adapters.AdapterPhotos;
import com.lugo.manueln.socialapp.Interfaces.InterPhotos;
import com.lugo.manueln.socialapp.Presenters.PhotosPresenter;
import com.lugo.manueln.socialapp.R;
import com.lugo.manueln.socialapp.objects.Photo;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PhotosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class PhotosFragment extends Fragment implements InterPhotos.View {

    InterPhotos.Presenter presenter;

    private OnFragmentInteractionListener mListener;

    private static final String ARG_IDALBUM = "idAlbum";

    private int albumId;



    public PhotosFragment() {
       presenter=new PhotosPresenter(this);
    }



    public static PhotosFragment newInstance(int paramIdAlbum) {
        PhotosFragment fragment = new PhotosFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_IDALBUM,paramIdAlbum);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            albumId = getArguments().getInt(ARG_IDALBUM);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewPhoto= inflater.inflate(R.layout.fragment_photos, container, false);

        loadViewCompoenents(viewPhoto);

        loadPhotosRecycler();


        return viewPhoto;

    }

    private void loadViewCompoenents(View viewPhoto) {

        progressBarPhoto=viewPhoto.findViewById(R.id.progressBarPhoto);

        albumTitle=viewPhoto.findViewById(R.id.viewTitleAlbum);
        recyclerPhotos=viewPhoto.findViewById(R.id.recyclerPhotos);

        albumTitle.setText("Album " + albumId);

        GridLayoutManager layoutManager=new GridLayoutManager(this.getContext(),2,GridLayoutManager.VERTICAL,false);

        recyclerPhotos.setLayoutManager(layoutManager);


    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void loadPhotosRecycler() {
        if(presenter!=null){

            progressBarPhoto.setVisibility(ProgressBar.VISIBLE);

            presenter.loadPhotosRecyclerPresenter(albumId,this.getActivity());
        }
    }

    @Override
    public void showPhotosRecycler(List<Photo>photoList) {

        AdapterPhotos myAdapterPhotos=new AdapterPhotos(photoList);

        recyclerPhotos.setAdapter(myAdapterPhotos);

        progressBarPhoto.setVisibility(ProgressBar.GONE);


    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    TextView albumTitle;
    RecyclerView recyclerPhotos;
    ProgressBar progressBarPhoto;
}
