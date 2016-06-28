package com.gerardogtn.korimagas.data.source.remote.model;

import com.gerardogtn.korimagas.data.UpdateGasStation;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Created by gerardogtn on 6/23/16.
 */
public class UpdateGasStationRequestTest {

  @Test
  public void testGasStationToGasStationRequest() {
    UpdateGasStation updateGasStation = mock(UpdateGasStation.class);
    when(updateGasStation.getId()).thenReturn(1);
    when(updateGasStation.getMessage()).thenReturn("Hello, message!");
    when(updateGasStation.hasGas()).thenReturn(true);

    UpdateGasStationRequest request = new UpdateGasStationRequest(updateGasStation);
    assertEquals(updateGasStation.getId(), request.getId());
    assertEquals(updateGasStation.getMessage(), request.getMessage());
    assertEquals(updateGasStation.hasGas(), request.hasGas());

    verify(updateGasStation, times(2)).getId();
    verify(updateGasStation, times(2)).getMessage();
    verify(updateGasStation, times(2)).hasGas();
  }
}