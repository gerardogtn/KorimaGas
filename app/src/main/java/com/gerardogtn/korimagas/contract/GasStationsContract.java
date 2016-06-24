package com.gerardogtn.korimagas.contract;

import android.support.annotation.NonNull;
import com.gerardogtn.korimagas.BasePresenter;
import com.gerardogtn.korimagas.BaseView;
import com.gerardogtn.korimagas.data.GasStation;
import java.util.List;

/**
 * Created by gerardogtn on 6/24/16.
 */
public interface GasStationsContract {

  interface View extends BaseView<Presenter> {

    void showLoadingIndicator();
    void hideLoadingIndicator();

    void showGasStations(@NonNull List<GasStation> gasStations);
    void showGasStationDetail(@NonNull GasStation gasStation);

    void showNoGasStations();
  }

  interface Presenter extends BasePresenter {

    void loadGasStations();
    void openGasStationDetails(@NonNull GasStation gasStation);

  }
}
