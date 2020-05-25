package com.lugo.manueln.socialapp;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lugo.manueln.socialapp.Interfaces.loadfragments;
import com.lugo.manueln.socialapp.R;
import com.lugo.manueln.socialapp.Views.AlbumsFragment;
import com.lugo.manueln.socialapp.Views.PostsFragment;

public class MainActivity extends AppCompatActivity implements loadfragments {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       loadFragment(Utilidad.FRAGMENT_ALBUM);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void loadFragment(int i) {

        Fragment fragmentNew=null;
        boolean selectFragment=false;

        switch (i){

            case Utilidad.FRAGMENT_POST:

                fragmentNew=new PostsFragment();
                selectFragment=true;

                break;

            case Utilidad.FRAGMENT_ALBUM:

                fragmentNew=new AlbumsFragment();
                selectFragment=true;
                break;

        }

        if(selectFragment){
            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.contenedorFragment, fragmentNew).commit();
        }


    }
}
