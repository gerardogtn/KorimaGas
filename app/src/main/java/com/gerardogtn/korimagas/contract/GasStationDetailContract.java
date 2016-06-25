package com.gerardogtn.korimagas.contract;

import com.gerardogtn.korimagas.BasePresenter;
import com.gerardogtn.korimagas.BaseView;
import com.gerardogtn.korimagas.data.GasStation;
import com.gerardogtn.korimagas.data.UpdateGasStation;

/**
 * Created by gerardogtn on 6/24/16.
 */
public interface GasStationDetailContract {

  interface View extends BaseView<Presenter> {

    void showGasStation(GasStation gasStation);
    void closeGasStation();
    void enableSendButton();
    void disableSendButton();
    void updateCharacterCounter(int characterCount);

  }

  interface Presenter extends BasePresenter {
    void updateGasStation(UpdateGasStation updateGasStation);
    void showGasStation();
  }
}
