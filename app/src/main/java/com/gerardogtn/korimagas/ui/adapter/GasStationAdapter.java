package com.gerardogtn.korimagas.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindColor;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.gerardogtn.korimagas.R;
import com.gerardogtn.korimagas.contract.GasStationsContract;
import com.gerardogtn.korimagas.data.GasStation;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by gerardogtn on 6/24/16.
 */
public class GasStationAdapter extends RecyclerView.Adapter<GasStationAdapter.GasStationViewHolder> {

  private List<GasStation> mGasStations;
  private LayoutInflater mInflater;
  private GasStationsContract.Presenter mPresenter;

  public GasStationAdapter(List<GasStation> mGasStations, Context context,
      GasStationsContract.Presenter presenter) {
    this.mGasStations = mGasStations;
    this.mInflater = LayoutInflater.from(context);
    this.mPresenter = presenter;
  }

  @Override public GasStationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = mInflater.inflate(R.layout.item_gas_station, parent, false);
    return new GasStationViewHolder(itemView);
  }

  @Override public void onBindViewHolder(GasStationViewHolder holder, int position) {
    holder.bindGasStation(mGasStations.get(position));
  }

  @Override public int getItemCount() {
    return mGasStations.size();
  }

  public void replaceData(@NonNull ArrayList<GasStation> gasStations) {
    this.mGasStations = checkNotNull(gasStations);
    notifyDataSetChanged();
  }

  public class GasStationViewHolder extends RecyclerView.ViewHolder
      implements View.OnClickListener {

    private GasStation mGasStation;

    @BindColor(R.color.green) int mGreen;
    @BindColor(R.color.red) int mRed;

    @BindString(R.string.gas_available) String mGasAvailableString;
    @BindString(R.string.gas_not_available) String mGasNotAvailableString;

    @BindView(R.id.txt_station_name) TextView mGasStationName;
    @BindView(R.id.txt_station_address) TextView mGasStationAddress;
    @BindView(R.id.txt_gas_available) TextView mGasAvailable;
    @BindView(R.id.txt_last_message) TextView mLastMessage;


    public GasStationViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      itemView.setOnClickListener(this);
    }

    public void bindGasStation(GasStation gasStation) {
      mGasStation = gasStation;
      mGasStationName.setText(gasStation.getName());
      mGasStationAddress.setText(gasStation.getDirections());
      mLastMessage.setText(gasStation.getLastMessage());
      setGasAvailableText(gasStation.hasGas(), gasStation.getLastUpdate());
    }

    private void setGasAvailableText(boolean hasGas, Date lastUpdate) {
      int color = hasGas? mGreen : mRed;
      String message = hasGas? mGasAvailableString: mGasNotAvailableString;

      mGasAvailable.setTextColor(color);
      mGasAvailable.setText(message + " " + SimpleDateFormat.getTimeInstance().format(lastUpdate));

    }

    @Override public void onClick(View v) {
      mPresenter.openGasStationDetails(mGasStation);
    }
  }
}
