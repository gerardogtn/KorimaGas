package com.gerardogtn.korimagas.api.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/**
 * Created by gerardogtn on 6/23/16.
 */
public class GasStationsResponse {

  @SerializedName("success")
  private boolean mIsSuccess = false;

  @SerializedName("stations")
  private ArrayList<GasStationsResponse> mGasStations;

  public GasStationsResponse() {

  }

  public boolean isSuccess() {
    return mIsSuccess;
  }

  public ArrayList<GasStationsResponse> getGasStations() {
    return mGasStations;
  }
}
