package com.gerardogtn.korimagas.presenter;

import android.support.annotation.NonNull;
import com.gerardogtn.korimagas.contract.GasStationsContract;
import com.gerardogtn.korimagas.data.GasStation;

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
    // TODO: Make api request to obtain gas stations.
  }

  @Override public void openGasStationDetails(@NonNull GasStation gasStation) {
    checkNotNull(gasStation, "Selected gas station cannot be null");
    mGasStationsView.showGasStationDetail(gasStation);
  }

}
