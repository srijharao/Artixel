package model.pixel;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Junit tests to verify public methods of RgbColor class.
 */
public class RgbColorTest {

  @Test
  public void testRgbColorComponents() {
    Color color = new RgbColor(255, 0, 0);
    assertEquals(color.getComponent(Channel.RED.ordinal()), 255);
    assertEquals(color.getComponent(Channel.GREEN.ordinal()), 0);
    assertEquals(color.getComponent(Channel.BLUE.ordinal()), 0);
  }

  @Test
  public void testRgbColorValue() {
    Color color = new RgbColor(100, 200, 50);
    assertEquals(color.getValue(), 200.0, 0.0);
  }

  @Test
  public void testRgbColorIntensity() {
    Color color = new RgbColor(100, 200, 50);
    assertEquals(color.getIntensity(), 116.66666666666667, 0.0);
  }

  @Test
  public void testRgbColorLuma() {
    Color color = new RgbColor(100, 200, 50);
    assertEquals(color.getLuma(), 167.91, 0.001);
  }

}
