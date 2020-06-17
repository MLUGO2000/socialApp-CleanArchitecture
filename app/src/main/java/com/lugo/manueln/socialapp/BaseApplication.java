package com.lugo.manueln.socialapp;

import android.app.Application;

import com.lugo.manueln.socialapp.di.DaggermainComponent;
import com.lugo.manueln.socialapp.di.mainComponent;
import com.lugo.manueln.socialapp.di.moduleApi;

public class  BaseApplication extends Application {

    private mainComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

       component=DaggermainComponent.builder().moduleApi(new moduleApi(this)).build();
    }

    public mainComponent getComponentApi(){
       return component;
    }
}
