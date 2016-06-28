package com.gerardogtn.korimagas.data.source.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gerardogtn on 6/28/16.
 */
public class KorimaGasClient {

  private static KorimaGasService sServiceInstance;

  private KorimaGasClient() {
  }

  public static KorimaGasService getServiceInstance() {
    if (sServiceInstance == null) {
      Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();

      Retrofit retrofit = new Retrofit.Builder().baseUrl(KorimaGasConstants.BASE_URL)
          .addConverterFactory(GsonConverterFactory.create(gson))
          .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
          .build();

      sServiceInstance = retrofit.create(KorimaGasService.class);
    }
    return sServiceInstance;
  }
}
