package com.lugo.manueln.socialapp.Interactors.di;


import com.lugo.manueln.socialapp.Interactors.WebService.JsonPostApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Call;
import retrofit2.Retrofit;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class moduleApi {

    @Provides
    @Singleton
    GsonConverterFactory provideGsonConverterFactory(){
       return GsonConverterFactory.create();
    }


    @Provides
    @Singleton
    RxJava2CallAdapterFactory providesRxJava2CallAdapterFactory(){
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    Retrofit providesRetrofit(GsonConverterFactory gson,RxJava2CallAdapterFactory factory){

        return new Retrofit.Builder()
                .baseUrl("http://192.168.0.108:4000/")
                .addConverterFactory(gson)
                .addCallAdapterFactory(factory)
                .build();
    }

    @Provides
    @Singleton
    JsonPostApi providesJsonPostApi(Retrofit retrofit){

        return retrofit.create(JsonPostApi.class);
    }







}
