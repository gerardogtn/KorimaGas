package com.gerardogtn.korimagas.data.source.remote.model;

import com.gerardogtn.korimagas.data.UpdateGasStation;
import com.google.gson.annotations.SerializedName;

/**
 * Created by gerardogtn on 6/23/16.
 */
public class UpdateGasStationRequest {

  @SerializedName("id")
  private int mId = -1;

  @SerializedName("gas")
  private boolean mHasGas = false;

  @SerializedName("message")
  private String mMessage = "";

  public UpdateGasStationRequest(UpdateGasStation updateGasStation) {
    this.mId = updateGasStation.getId();
    this.mHasGas = updateGasStation.hasGas();
    this.mMessage = updateGasStation.getMessage();
  }

  public int getId() {
    return mId;
  }

  public boolean hasGas() {
    return mHasGas;
  }

  public String getMessage() {
    return mMessage;
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    UpdateGasStationRequest that = (UpdateGasStationRequest) o;

    return mId == that.mId;
  }

  @Override public int hashCode() {
    return mId;
  }

  @Override public String toString() {
    return "UpdateGasStationRequest{" +
        "mId=" + mId +
        ", mHasGas=" + mHasGas +
        ", mMessage='" + mMessage + '\'' +
        '}';
  }
}
