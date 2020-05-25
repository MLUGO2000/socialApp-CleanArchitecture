package com.lugo.manueln.socialapp.Interactors.di;

import android.app.Application;

public class  BaseApplication extends Application {

    static componentApi apiComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        apiComponent=DaggercomponentApi.builder().moduleApi(new moduleApi()).build();
    }

    public componentApi getComponentApi(){
       return apiComponent;
    }
}
