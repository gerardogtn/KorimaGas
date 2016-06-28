package com.gerardogtn.korimagas.data;

import com.gerardogtn.korimagas.data.source.remote.model.GasStationResponse;
import java.util.Date;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by gerardogtn on 6/23/16.
 */
public class GasStationTest {

  @Test
  public void testGasStationResponseToGasStation() {
    GasStationResponse response = mock(GasStationResponse.class);
    when(response.getId()).thenReturn(1);
    when(response.getName()).thenReturn("Gasolinera feliz");
    when(response.getAddress()).thenReturn("Chihuahua esquina con Cdmx");
    when(response.isGasAvailable()).thenReturn(true);
    when(response.getLastUpdate()).thenReturn(new Date(100));

    GasStation actual = new GasStation(response);
    assertEquals(response.getId(), actual.getId());
    assertEquals(response.getName(), actual.getName());
    assertEquals(response.getAddress(), actual.getDirections());
    assertEquals(response.isGasAvailable(), actual.hasGas());
    assertEquals(response.getLastUpdate(), actual.getLastUpdate());

    verify(response, times(2)).getId();
    verify(response, times(2)).getName();
    verify(response, times(2)).getAddress();
    verify(response, times(2)).isGasAvailable();
    verify(response, times(2)).getLastUpdate();
  }


}