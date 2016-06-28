package com.gerardogtn.korimagas.data.source;

import android.support.annotation.NonNull;
import com.gerardogtn.korimagas.data.GasStation;
import java.util.List;
import rx.Observable;

/**
 * Created by gerardogtn on 6/28/16.
 */
public interface GasStationsDataSource {

  Observable<List<GasStation>> getGasStations();
  void saveGasStations(@NonNull List<GasStation> gasStation);
  void refreshGasStations();

}
