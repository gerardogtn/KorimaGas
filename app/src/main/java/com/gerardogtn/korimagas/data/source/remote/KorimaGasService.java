package com.gerardogtn.korimagas.data.source.remote;

import com.gerardogtn.korimagas.data.source.remote.model.GasStationsResponse;
import com.gerardogtn.korimagas.data.source.remote.model.UpdateGasStationRequest;
import com.gerardogtn.korimagas.data.source.remote.model.UpdateGasStationResponse;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by gerardogtn on 6/24/16.
 */
public interface KorimaGasService {

  @GET(KorimaGasConstants.STATIONS_URL) Observable<GasStationsResponse> getGasStations();

  @POST(KorimaGasConstants.STATION_URL)
  Observable<UpdateGasStationResponse> updateGasStation(@Body UpdateGasStationRequest request);

}
