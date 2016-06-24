package com.gerardogtn.korimagas.api.client;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gerardogtn on 6/23/16.
 */
public class KorimaGasClientFactory {

  public static KorimaGasService create() {
    Retrofit retrofit = new Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build();

    return retrofit.create(KorimaGasService.class);
  }

}
