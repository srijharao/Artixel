package model.pixel;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * Junit tests to verify public methods of PixelImpl class.
 */
public class PixelImplTest {

  @Test
  public void testConstructorThrowsIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> {
      new PixelImpl(-1, 0, new RgbColor(255, 255, 255));
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new PixelImpl(0, -1, new RgbColor(255, 255, 255));
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new PixelImpl(-1, -1, new RgbColor(255, 255, 255));
    });
  }

  @Test
  public void testGetXCoordinate() {
    Pixel pixel = new PixelImpl(10, 20, new RgbColor(255, 255, 255));
    assertEquals(10, pixel.getXCoordinate());
  }

  @Test
  public void testGetYCoordinate() {
    Pixel pixel = new PixelImpl(10, 20, new RgbColor(255, 255, 255));
    assertEquals(20, pixel.getYCoordinate());
  }

  @Test
  public void testBrighten() {
    Pixel pixel = new PixelImpl(10, 20, new RgbColor(50, 100, 150));
    pixel.brighten(50);
    assertEquals(100, pixel.getComponent(0));
    assertEquals(150, pixel.getComponent(1));
    assertEquals(200, pixel.getComponent(2));
  }

  @Test
  public void testGetValue() {
    Pixel pixel = new PixelImpl(10, 20, new RgbColor(50, 100, 150));
    assertEquals(150, pixel.getValue(), 0.0);
  }

  @Test
  public void testGetIntensity() {
    Pixel pixel = new PixelImpl(10, 20, new RgbColor(50, 100, 150));
    assertEquals(100, pixel.getIntensity(), 0.0);
  }

  @Test
  public void testGetLuma() {
    Pixel pixel = new PixelImpl(10, 20, new RgbColor(50, 100, 150));
    assertEquals(92.97999999, pixel.getLuma(), 0.01);
  }

  @Test
  public void testGetComponent() {
    Pixel pixel = new PixelImpl(10, 20, new RgbColor(50, 100, 150));
    assertEquals(50, pixel.getComponent(Channel.RED.ordinal()));
    assertEquals(100, pixel.getComponent(Channel.GREEN.ordinal()));
    assertEquals(150, pixel.getComponent(Channel.BLUE.ordinal()));
  }
}
