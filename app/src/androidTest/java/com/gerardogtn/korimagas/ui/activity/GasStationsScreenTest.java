package com.gerardogtn.korimagas.ui.activity;

import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.UiThreadTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import com.gerardogtn.korimagas.R;
import com.gerardogtn.korimagas.presenter.GasStationsPresenter;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Created by gerardogtn on 6/24/16.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class GasStationsScreenTest {

  @Rule public ActivityTestRule<GasStationsActivity> activityRule =
      new ActivityTestRule<GasStationsActivity>(GasStationsActivity.class) {
        @Override protected void afterActivityLaunched() {
          super.afterActivityLaunched();
        }
      };

  @Rule public UiThreadTestRule threadrule = new UiThreadTestRule();

  @Test public void reloadGasStations() {
    GasStationsActivity activity = spy(activityRule.getActivity());
    GasStationsPresenter presenter = mock(GasStationsPresenter.class);
    activity.setPresenter(presenter);
    activity.showNoGasStations();
    onView(withId(R.id.txt_no_gas_stations)).perform(click());
    onView(withId(R.id.container_progress_bar)).check(matches(isDisplayed()));
    verify(activity).showLoadingIndicator();
    verify(presenter).loadGasStations();
  }

  @Test public void goToGasStationDetails() {
    fail("Add implementation to go to gas station details");
  }
}