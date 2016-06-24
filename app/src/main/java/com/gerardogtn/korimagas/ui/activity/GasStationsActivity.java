package com.gerardogtn.korimagas.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.gerardogtn.korimagas.R;
import com.gerardogtn.korimagas.contract.GasStationsContract;
import com.gerardogtn.korimagas.data.GasStation;
import com.gerardogtn.korimagas.presenter.GasStationsPresenter;
import com.gerardogtn.korimagas.ui.adapter.GasStationAdapter;
import java.util.Collections;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by gerardogtn on 6/24/16.
 */
public class GasStationsActivity extends AppCompatActivity implements GasStationsContract.View {

  private GasStationsContract.Presenter mPresenter;
  private GasStationAdapter mAdapter;

  @BindView(R.id.toolbar) Toolbar mToolbar;
  @BindView(R.id.progress_bar) ProgressBar mProgressBar;

  @BindView(R.id.container_gas_stations) View mGasStationsView;
  @BindView(R.id.container_no_gas_stations) View mNoGasStationsView;
  @BindView(R.id.container_progress_bar) View mProgressBarView;

  @BindView(R.id.list_gas_stations) RecyclerView mGasStationsRecyclerView;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_gas_stations);
    ButterKnife.bind(this);
    mPresenter = new GasStationsPresenter(this);
    setUpToolbar();
    setUpGasStationsRecyclerView();
  }

  private void setUpToolbar() {
    setSupportActionBar(mToolbar);
  }

  private void setUpGasStationsRecyclerView() {
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    mAdapter = new GasStationAdapter(Collections.emptyList(), this, mPresenter);
    mGasStationsRecyclerView.setLayoutManager(layoutManager);
    mGasStationsRecyclerView.setAdapter(mAdapter);
  }

  @Override protected void onResume() {
    super.onResume();
    mPresenter.start();
  }

  @Override public void setPresenter(GasStationsContract.Presenter presenter) {
    mPresenter = checkNotNull(presenter);
  }

  @Override public void showLoadingIndicator() {
    mProgressBarView.setVisibility(View.VISIBLE);
    mNoGasStationsView.setVisibility(View.INVISIBLE);
    mGasStationsView.setVisibility(View.INVISIBLE);
  }

  @Override public void showNoGasStations() {
    mProgressBarView.setVisibility(View.INVISIBLE);
    mNoGasStationsView.setVisibility(View.VISIBLE);
    mGasStationsView.setVisibility(View.INVISIBLE);
  }

  @Override public void showGasStations(@NonNull List<GasStation> gasStations) {
    mProgressBarView.setVisibility(View.INVISIBLE);
    mNoGasStationsView.setVisibility(View.INVISIBLE);
    mGasStationsView.setVisibility(View.VISIBLE);

    mAdapter.replaceData(gasStations);
  }

  @Override public void showGasStationDetail(@NonNull GasStation gasStation) {
    Intent intent = new Intent(this, GasStationDetailActivity.class);
    startActivity(intent);
  }

  @OnClick(R.id.txt_no_gas_stations) public void onNoGasStationsTextClick() {
    showLoadingIndicator();
    mPresenter.loadGasStations();
  }
}
