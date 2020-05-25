package com.lugo.manueln.socialapp.Views;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.lugo.manueln.socialapp.Adapters.AdapterAlbums;
import com.lugo.manueln.socialapp.Interfaces.InterAlbums;
import com.lugo.manueln.socialapp.objects.Album;
import com.lugo.manueln.socialapp.Presenters.AlbumsPresenter;
import com.lugo.manueln.socialapp.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AlbumsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class AlbumsFragment extends Fragment implements InterAlbums.View {

    InterAlbums.Presenter myPresenterView;
    private OnFragmentInteractionListener mListener;

    public AlbumsFragment() {
        myPresenterView=new AlbumsPresenter(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.fragment_albums, container, false);

        loadViewComponents(vista);

        loadRecyclerAlbums();

        return vista;

    }

    private void loadViewComponents(View view) {

        progressBarAlbum=view.findViewById(R.id.progressBarAlbum);

        recyclerAlbums=view.findViewById(R.id.recyclerAlbums);


        GridLayoutManager manager=new GridLayoutManager(this.getContext(),2);

        manager.setOrientation(GridLayoutManager.VERTICAL);

        recyclerAlbums.setLayoutManager(manager);




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
    public void loadRecyclerAlbums() {

        if(myPresenterView!=null){

            progressBarAlbum.setVisibility(ProgressBar.VISIBLE);

            myPresenterView.loadRecyclerAlbumsPresenter(this.getActivity());
        }
    }

    @Override
    public void showRecyclerAlbums(List<Album> albumList) {

        AdapterAlbums adapterAlbums=new AdapterAlbums(albumList,this);

        recyclerAlbums.setAdapter(adapterAlbums);


        progressBarAlbum.setVisibility(ProgressBar.GONE);
    }

    @Override
    public void showErrorLoadAlbum(String error) {

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

    RecyclerView recyclerAlbums;

    ProgressBar progressBarAlbum;
}
