package com.gerardogtn.korimagas.data;

import com.gerardogtn.korimagas.data.source.remote.model.GasStationResponse;
import com.gerardogtn.korimagas.data.source.remote.model.GasStationsResponse;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.parceler.Parcel;

/**
 * Created by gerardogtn on 6/23/16.
 */
@Parcel public class GasStation {

  private int mId = -1;
  private boolean mHasGas = false;
  private Date mLastUpdate = Calendar.getInstance().getTime();
  private String mName = "";
  private String mDirections = "";
  private String mLastMessage = "";

  public GasStation() {}

  public static List<GasStation> mapGasStationsResponse(GasStationsResponse gasStationsResponse) {
    ArrayList<GasStation> gasStations = new ArrayList<GasStation>();
    for(GasStationResponse response: gasStationsResponse.getGasStations()) {
      gasStations.add(new GasStation(response));
    }
    return gasStations;
  }

  public GasStation(GasStationResponse gasStationResponse) {
    this.mId = gasStationResponse.getId();
    this.mHasGas = gasStationResponse.isGasAvailable();

    long offset = TimeZone.getDefault().getOffset(gasStationResponse.getLastUpdate().getTime());
    this.mLastUpdate = new Date(gasStationResponse.getLastUpdate().getTime() + offset);

    this.mName = gasStationResponse.getName();
    this.mDirections = gasStationResponse.getAddress();
    this.mLastMessage = gasStationResponse.getLastMessage();
  }

  public int getId() {
    return mId;
  }

  public void setHasGas(boolean mHasGas) {
    this.mHasGas = mHasGas;
  }

  public void setLastUpdate(Date mLastUpdate) {
    this.mLastUpdate = mLastUpdate;
  }

  public void setLastMessage(String mLastMessage) {
    this.mLastMessage = mLastMessage;
  }

  public boolean hasGas() {
    return mHasGas;
  }

  public Date getLastUpdate() {
    return mLastUpdate;
  }

  public String getName() {
    return mName;
  }

  public String getDirections() {
    return mDirections;
  }

  public String getLastMessage() {
    return mLastMessage;
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    GasStation that = (GasStation) o;

    if (!mName.equals(that.mName)) return false;
    return mDirections.equals(that.mDirections);
  }

  @Override public int hashCode() {
    int result = mName.hashCode();
    result = 31 * result + mDirections.hashCode();
    return result;
  }

  @Override public String toString() {
    return "GasStation with name: " + mName;
  }
}
