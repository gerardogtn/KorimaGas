package com.gerardogtn.korimagas.api.model;

import com.google.gson.annotations.SerializedName;
import java.util.Date;

/**
 * Created by gerardogtn on 6/23/16.
 */
public class GasStationResponse {

  @SerializedName("gas")
  private boolean mIsGasAvailable = false;

  @SerializedName("id")
  private int mId = -1;

  @SerializedName("name")
  private String mName = "";

  @SerializedName("street")
  private String mAddress = "";

  @SerializedName("message")
  private String mLastMessage = "";

  @SerializedName("lat")
  private String mLat = "";

  @SerializedName("lon")
  private String mLon = "";

  @SerializedName("phone")
  private String mPhone = "";

  @SerializedName("updated_at")
  private Date mLastUpdate;

  public GasStationResponse() {

  }

  public boolean isGasAvailable() {
    return mIsGasAvailable;
  }

  public int getId() {
    return mId;
  }

  public String getName() {
    return mName;
  }

  public String getAddress() {
    return mAddress;
  }

  public String getLastMessage() {
    return mLastMessage;
  }

  public String getLat() {
    return mLat;
  }

  public String getLon() {
    return mLon;
  }

  public String getPhone() {
    return mPhone;
  }

  public Date getLastUpdate() {
    return mLastUpdate;
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    GasStationResponse that = (GasStationResponse) o;

    return mId == that.mId;
  }

  @Override public int hashCode() {
    return mId;
  }

  @Override public String toString() {
    return "GasStationResponse{" +
        "mId=" + mId +
        ", mName='" + mName + '\'' +
        '}';
  }
}
