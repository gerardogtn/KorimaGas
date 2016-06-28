package com.gerardogtn.korimagas.data.source.remote.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gerardogtn on 6/24/16.
 */
public class UpdateGasStationResponse {

  @SerializedName("success")
  private boolean isSuccess = false;

  public UpdateGasStationResponse() {

  }

  public boolean isSuccess() {
    return isSuccess;
  }
}
