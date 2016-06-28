package com.gerardogtn.korimagas;

import android.content.Context;
import android.support.annotation.NonNull;
import com.gerardogtn.korimagas.data.source.GasStationsRepository;
import com.gerardogtn.korimagas.data.source.remote.GasStationsRemoteDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by gerardogtn on 6/28/16.
 */
public class Injection {

  public static GasStationsRepository provideGasStationsRepository(@NonNull Context context) {
    checkNotNull(context);
    return GasStationsRepository.getInstance(GasStationsRemoteDataSource.getInstance());
  }
}
