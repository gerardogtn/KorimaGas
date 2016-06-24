package com.gerardogtn.korimagas.presenter;

import android.support.annotation.NonNull;
import com.gerardogtn.korimagas.api.client.KorimaGasClientFactory;
import com.gerardogtn.korimagas.api.model.GasStationResponse;
import com.gerardogtn.korimagas.api.model.GasStationsResponse;
import com.gerardogtn.korimagas.contract.GasStationsContract;
import com.gerardogtn.korimagas.data.GasStation;
import java.util.ArrayList;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by gerardogtn on 6/24/16.
 */
public class GasStationsPresenter implements GasStationsContract.Presenter {

  private GasStationsContract.View mGasStationsView;

  public GasStationsPresenter(GasStationsContract.View gasStationsView) {
    this.mGasStationsView = checkNotNull(gasStationsView);
    mGasStationsView.setPresenter(this);
  }

  @Override public void start() {
    mGasStationsView.showLoadingIndicator();
    loadGasStations();
  }

  @Override public void loadGasStations() {
    KorimaGasClientFactory.create()
        .getGasStations()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnError(error -> mGasStationsView.showNoGasStations())
        .subscribe(gasStationsResponse -> {
          if (!gasStationsResponse.isSuccess()) {
            mGasStationsView.showNoGasStations();
            return;
          }
          ArrayList<GasStation> gasStations = new ArrayList<GasStation>();

          for (GasStationResponse gasStation: gasStationsResponse.getGasStations()) {
            gasStations.add(new GasStation(gasStation));
          }

          mGasStationsView.showGasStations(gasStations);
        }, throwable -> mGasStationsView.showNoGasStations());
  }

  @Override public void openGasStationDetails(@NonNull GasStation gasStation) {
    checkNotNull(gasStation, "Selected gas station cannot be null");
    mGasStationsView.showGasStationDetail(gasStation);
  }

}
