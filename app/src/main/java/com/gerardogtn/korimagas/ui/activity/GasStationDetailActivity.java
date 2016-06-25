package com.gerardogtn.korimagas.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.gerardogtn.korimagas.R;
import com.gerardogtn.korimagas.api.model.UpdateGasStationRequest;
import com.gerardogtn.korimagas.contract.GasStationDetailContract;
import com.gerardogtn.korimagas.data.GasStation;
import com.gerardogtn.korimagas.data.UpdateGasStation;
import com.gerardogtn.korimagas.presenter.GasStationDetailPresenter;
import com.gerardogtn.korimagas.presenter.GasStationsPresenter;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewAfterTextChangeEvent;
import java.util.concurrent.TimeUnit;
import org.parceler.Parcels;
import org.w3c.dom.Text;
import rx.Subscription;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static com.google.common.base.Preconditions.checkNotNull;

public class GasStationDetailActivity extends AppCompatActivity implements
    GasStationDetailContract.View {

  private GasStationDetailContract.Presenter mPresenter;
  private UpdateGasStation mUpdateGasStation;
  private int currentCharacterCount = 0;

  private static final String KEY_GAS_STATION = "gasStation";

  @BindView(R.id.btn_make_update) Button mUpdateButton;
  @BindView(R.id.toolbar_layout) Toolbar mToolbar;
  @BindView(R.id.checkbox_is_gas) CheckBox mIsGasAvailable;
  @BindView(R.id.txt_counter) TextView mCounter;
  @BindView(R.id.etxt_message) EditText mUserMessage;

  public static Intent newIntent(Context context, GasStation gasStation) {
    Intent intent = new Intent(context, GasStationDetailActivity.class);
    intent.putExtra(KEY_GAS_STATION, Parcels.wrap(gasStation));
    return intent;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_gas_station_detail);
    ButterKnife.bind(this);
    mPresenter = new GasStationDetailPresenter(this, Parcels.unwrap(getIntent().getParcelableExtra(KEY_GAS_STATION)));
    setUpToolbar();
    setUpCharacterUpdater();
  }

  private void setUpToolbar() {
    setSupportActionBar(mToolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  private void setUpCharacterUpdater() {
    RxTextView.textChanges(mUserMessage)
        .subscribeOn(Schedulers.trampoline())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(textViewAfterTextChangeEvent -> {
            updateCharacterCounter(140 - mUserMessage.getText().length());
          }, throwable -> {});
  }

  @Override protected void onResume() {
    super.onResume();
    mPresenter.start();
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      finish();
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override public void setPresenter(@NonNull GasStationDetailContract.Presenter presenter) {
    mPresenter = checkNotNull(presenter);
  }

  @Override public void showGasStation(@NonNull GasStation gasStation) {
    mToolbar.setTitle(gasStation.getName());
    mUpdateGasStation = new UpdateGasStation(gasStation.getId());
  }

  @Override public void closeGasStation() {
    finish();
  }

  @Override public void enableSendButton() {
    mUpdateButton.setEnabled(true);
  }

  @Override public void disableSendButton() {
    mUpdateButton.setEnabled(false);
  }

  @Override public void updateCharacterCounter(int characterCount) {
    mUpdateButton.setEnabled(characterCount >= 0 && characterCount < 140);
    mCounter.setText(String.valueOf(characterCount));
  }

  @OnClick(R.id.btn_make_update) public void onSendUpdate() {
    if (mUpdateButton.isEnabled()) {
      mUpdateGasStation.setMessage(mUserMessage.getText().toString());
      mUpdateGasStation.hasGas(mIsGasAvailable.isChecked());
      mPresenter.updateGasStation(mUpdateGasStation);
    }
  }

}
