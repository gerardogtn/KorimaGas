package com.gerardogtn.korimagas.presenter;

import android.support.annotation.NonNull;
import com.gerardogtn.korimagas.data.source.GasStationsRepository;
import com.gerardogtn.korimagas.data.source.remote.GasStationsRemoteDataSource;
import com.gerardogtn.korimagas.data.source.remote.model.GasStationResponse;
import com.gerardogtn.korimagas.contract.GasStationsContract;
import com.gerardogtn.korimagas.data.GasStation;
import com.gerardogtn.korimagas.data.source.remote.model.GasStationsResponse;
import java.util.ArrayList;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by gerardogtn on 6/24/16.
 */
public class GasStationsPresenter implements GasStationsContract.Presenter {

  private GasStationsContract.View mGasStationsView;
  private GasStationsRepository mGasStationsRepository;

  public GasStationsPresenter(@NonNull GasStationsContract.View gasStationsView,
      @NonNull GasStationsRepository gasStationsRepository) {
    this.mGasStationsView = checkNotNull(gasStationsView);
    this.mGasStationsRepository = checkNotNull(gasStationsRepository);
    mGasStationsView.setPresenter(this);
  }

  @Override public void start() {
    mGasStationsView.showLoadingIndicator();
    loadGasStations();
  }

  @Override public void forceLoadGasStations() {

  }

  @Override public void loadGasStations() {
    mGasStationsRepository.getGasStations()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(gasStations -> {
          if (gasStations.isEmpty()) {
            mGasStationsView.showNoGasStations();
          } else {
            mGasStationsView.showGasStations(gasStations);
          }
        }, throwable -> mGasStationsView.showNoGasStations());
  }

  @Override public void openGasStationDetails(@NonNull GasStation gasStation) {
    checkNotNull(gasStation, "Selected gas station cannot be null");
    mGasStationsView.showGasStationDetail(gasStation);
  }

}
