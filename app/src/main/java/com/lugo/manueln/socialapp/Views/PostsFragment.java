package com.lugo.manueln.socialapp.Views;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.lugo.manueln.socialapp.Interfaces.InterPost;
import com.lugo.manueln.socialapp.Adapters.AdapterPosts;
import com.lugo.manueln.socialapp.objects.Post;
import com.lugo.manueln.socialapp.Presenters.PresenterPosts;
import com.lugo.manueln.socialapp.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PostsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PostsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PostsFragment extends Fragment implements InterPost.view {

    InterPost.presenter presenter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PostsFragment() {
        presenter=new PresenterPosts(this);
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PostsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PostsFragment newInstance(String param1, String param2) {
        PostsFragment fragment = new PostsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.fragment_posts, container, false);

        cargarVistas(vista);

        loadRecyclerPost();

        return vista;
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
    public void loadRecyclerPost() {

        if(presenter!=null){
            progressRecyclerPost.setVisibility(ProgressBar.VISIBLE);
            presenter.loadRecyclerPostPresenter(this.getActivity());
        }
    }

    @Override
    public void showRecycler(List<Post> miListaPost) {

        AdapterPosts miAdapter=new AdapterPosts(miListaPost,this);
        recyclerPosts.setAdapter(miAdapter);

        progressRecyclerPost.setVisibility(ProgressBar.GONE);


        }

    @Override
    public void errorLoadRecyclerPost(String error) {
        Toast.makeText(getContext(),"Se Produjo un Error de Tipo " + error,Toast.LENGTH_LONG).show();
    }

    private void cargarVistas(View view){

        recyclerPosts=view.findViewById(R.id.recyclerPosts);

        LinearLayoutManager miManager=new LinearLayoutManager(getContext());
        miManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerPosts.setLayoutManager(miManager);

        progressRecyclerPost=view.findViewById(R.id.progressBarRecycler);

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




    RecyclerView recyclerPosts;
    ProgressBar progressRecyclerPost;
}
