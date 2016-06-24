package com.gerardogtn.korimagas.data;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by gerardogtn on 6/23/16.
 */
public class GasStation {

    private boolean mHasGas = false;
    private Date mLastUpdate = Calendar.getInstance().getTime();
    private String mName = "";
    private String mDirections = "";
    private String mLastMessage = "";

    public GasStation() {

    }

    public GasStation(boolean mHasGas, Date mLastUpdate, String mName, String mDirections, String mLastMessage) {
        this.mHasGas = mHasGas;
        this.mLastUpdate = mLastUpdate;
        this.mName = mName;
        this.mDirections = mDirections;
        this.mLastMessage = mLastMessage;
    }

    public void setmHasGas(boolean mHasGas) {
        this.mHasGas = mHasGas;
    }

    public void setmLastUpdate(Date mLastUpdate) {
        this.mLastUpdate = mLastUpdate;
    }

    public void setmLastMessage(String mLastMessage) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GasStation that = (GasStation) o;

        if (!mName.equals(that.mName)) return false;
        return mDirections.equals(that.mDirections);

    }

    @Override
    public int hashCode() {
        int result = mName.hashCode();
        result = 31 * result + mDirections.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "GasStation with name: " + mName;
    }
}
