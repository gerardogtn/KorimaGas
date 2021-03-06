package com.gerardogtn.korimagas.data;

import org.parceler.Parcel;

/**
 * Created by gerardogtn on 6/23/16.
 */

@Parcel
public class UpdateGasStation {

  int mId = -1;
  boolean mHasGas = false;
  String mMessage = "";

  public UpdateGasStation() {

  }

  public UpdateGasStation(int id) {
    this.mId = id;
  }

  public int getId() {
    return mId;
  }

  public boolean hasGas() {
    return mHasGas;
  }

  public void hasGas(boolean mHasGas) {
    this.mHasGas = mHasGas;
  }

  public String getMessage() {
    return mMessage;
  }

  public void setMessage(String mMessage) {
    this.mMessage = mMessage;
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    UpdateGasStation that = (UpdateGasStation) o;

    if (mId != that.mId) return false;
    if (mHasGas != that.mHasGas) return false;
    return mMessage.equals(that.mMessage);
  }

  @Override public int hashCode() {
    int result = mId;
    result = 31 * result + (mHasGas ? 1 : 0);
    result = 31 * result + mMessage.hashCode();
    return result;
  }

  @Override public String toString() {
    return "UpdateGasStation{" +
        "mId=" + mId +
        ", mHasGas=" + mHasGas +
        ", mMessage='" + mMessage + '\'' +
        '}';
  }
}
