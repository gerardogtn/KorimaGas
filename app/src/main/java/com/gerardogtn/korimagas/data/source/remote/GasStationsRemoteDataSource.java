package com.gerardogtn.korimagas.data.source.remote;

import android.support.annotation.NonNull;
import com.gerardogtn.korimagas.data.GasStation;
import com.gerardogtn.korimagas.data.source.GasStationsDataSource;
import com.gerardogtn.korimagas.data.source.GasStationsRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by gerardogtn on 6/23/16.
 */
public class GasStationsRemoteDataSource implements GasStationsDataSource {

  private static GasStationsRemoteDataSource sInstance;

  private GasStationsRemoteDataSource() {}

  public static GasStationsRemoteDataSource getInstance() {
    if (sInstance == null) {
      sInstance = new GasStationsRemoteDataSource();
    }
    return sInstance;
  }

  @Override public Observable<List<GasStation>> getGasStations() {
    return KorimaGasClient.getServiceInstance()
        .getGasStations()
        .map(GasStation::mapGasStationsResponse);
  }

  /**
   * Method is not required, the api doesn't allow gas stations to be saved.
   */
  @Override public void saveGasStations(@NonNull List<GasStation> gasStation) {

  }

  /**
   * Method is not required, {@link GasStationsRepository} handles refreshing logic.
   */
  @Override public void refreshGasStations() {

  }
}
