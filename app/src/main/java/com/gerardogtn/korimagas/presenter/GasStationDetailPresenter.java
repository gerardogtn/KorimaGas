package com.gerardogtn.korimagas.presenter;

import android.support.annotation.NonNull;
import com.gerardogtn.korimagas.data.source.remote.GasStationsRemoteDataSource;
import com.gerardogtn.korimagas.data.source.remote.KorimaGasClient;
import com.gerardogtn.korimagas.data.source.remote.model.UpdateGasStationRequest;
import com.gerardogtn.korimagas.contract.GasStationDetailContract;
import com.gerardogtn.korimagas.data.GasStation;
import com.gerardogtn.korimagas.data.UpdateGasStation;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by gerardogtn on 6/24/16.
 */
public class GasStationDetailPresenter implements GasStationDetailContract.Presenter {

  private GasStationDetailContract.View mView;
  private GasStation mGasStation;

  public GasStationDetailPresenter(@NonNull GasStationDetailContract.View view, GasStation gasStation) {
    this.mView = checkNotNull(view);
    this.mView.setPresenter(this);
    this.mGasStation = gasStation;
  }

  @Override public void start() {
    mView.disableSendButton();
    mView.updateCharacterCounter(140);
    mView.showGasStation(mGasStation);
  }

  @Override public void updateGasStation(UpdateGasStation updateGasStation) {
    KorimaGasClient
        .getServiceInstance()
        .updateGasStation(new UpdateGasStationRequest(updateGasStation))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(response -> {}, throwable -> {});
    mView.closeGasStation();
  }

  @Override public void showGasStation() {
    mView.showGasStation(mGasStation);
  }
}
