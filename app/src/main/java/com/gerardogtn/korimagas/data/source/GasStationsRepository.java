package com.gerardogtn.korimagas.data.source;

import android.support.annotation.NonNull;
import com.gerardogtn.korimagas.data.GasStation;
import java.util.List;
import rx.Observable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by gerardogtn on 6/28/16.
 */
public class GasStationsRepository implements GasStationsDataSource {

  private static GasStationsRepository sInstance;
  private static List<GasStation> sCachedGasStations;

  private final GasStationsDataSource mRemoteDataSource;

  private boolean mIsCacheDirty = false;

  private GasStationsRepository(@NonNull GasStationsDataSource remoteDataSource) {
    this.mRemoteDataSource = checkNotNull(remoteDataSource);
  }

  public static GasStationsRepository getInstance(@NonNull GasStationsDataSource remoteDataSource) {
    if (sInstance == null) {
      sInstance = new GasStationsRepository(checkNotNull(remoteDataSource));
    }
    return sInstance;
  }

  @Override public Observable<List<GasStation>> getGasStations() {
    if (sCachedGasStations != null && !mIsCacheDirty) {
      return Observable.from(sCachedGasStations).toList();
    } else {
      return mRemoteDataSource.getGasStations();
    }
  }

  @Override public void saveGasStations(@NonNull List<GasStation> gasStation) {

  }

  @Override public void refreshGasStations() {
    mIsCacheDirty = true;
  }
}
