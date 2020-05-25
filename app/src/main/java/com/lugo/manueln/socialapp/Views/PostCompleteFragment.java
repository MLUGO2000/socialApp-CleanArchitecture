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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lugo.manueln.socialapp.Interfaces.interPostComplete;
import com.lugo.manueln.socialapp.Adapters.AdapterComments;
import com.lugo.manueln.socialapp.objects.Comments;
import com.lugo.manueln.socialapp.objects.Post;
import com.lugo.manueln.socialapp.Presenters.PresenterPostComplete;
import com.lugo.manueln.socialapp.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PostCompleteFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PostCompleteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PostCompleteFragment extends Fragment implements interPostComplete.view {

    interPostComplete.presenter myPresenter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_IDPOST = "idPost";


    // TODO: Rename and change types of parameters
    private int idPost;


    private OnFragmentInteractionListener mListener;

    public PostCompleteFragment() {
        myPresenter=new PresenterPostComplete(this);
    }


    // TODO: Rename and change types and number of parameters
    public static PostCompleteFragment newInstance(int paramIdPost) {
        PostCompleteFragment fragment = new PostCompleteFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_IDPOST, paramIdPost);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idPost = getArguments().getInt(ARG_IDPOST);
            }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vistaPostComplete= inflater.inflate(R.layout.fragment_post_complete, container, false);

        cargarViews(vistaPostComplete);


        loadPostComplete();


        return vistaPostComplete;
    }

    private void cargarViews(View vistaPostComplete) {

        progressBarPostComplete=vistaPostComplete.findViewById(R.id.progressBarPostComplete);
        titlePost=vistaPostComplete.findViewById(R.id.textTitlePost);
        textPost=vistaPostComplete.findViewById(R.id.textPost);
        commentUser=vistaPostComplete.findViewById(R.id.userComment);
        bSendComment=vistaPostComplete.findViewById(R.id.userCommentButton);
        recyclerComments=vistaPostComplete.findViewById(R.id.recyclerComments);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerComments.setLayoutManager(layoutManager);

        bSendComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendComment();
            }
        });

    }

    private void sendComment() {

        String comment=commentUser.getText().toString();

        Comments myComment=new Comments();

        myComment.setPostId(idPost);
        myComment.setBody(comment);
        myComment.setUserName("Manuel");

        myPresenter.saveComment(myComment);


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
    public void loadPostComplete() {

        if(myPresenter!=null){

            progressBarPostComplete.setVisibility(ProgressBar.VISIBLE);

            myPresenter.loadPostCompleteWithComments(idPost,this.getActivity());
        }
    }

    @Override
    public void showPostComplete(Post miPost) {

        titlePost.setText(miPost.getTitle());
        textPost.setText(miPost.getBody());

    }

    @Override
    public void showCommentsPost(List<Comments> commentsList) {

        AdapterComments myAdapterComment=new AdapterComments(commentsList);

        recyclerComments.setAdapter(myAdapterComment);

        progressBarPostComplete.setVisibility(ProgressBar.GONE);
    }

    @Override
    public void showErrorLoadPost(String error) {

    }

    @Override
    public void updatePostComplete() {
        loadPostComplete();
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


    TextView titlePost,textPost;
    EditText commentUser;
    ImageButton bSendComment;
    RecyclerView recyclerComments;
    ProgressBar progressBarPostComplete;
}
