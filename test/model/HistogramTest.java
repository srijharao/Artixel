package model;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import controller.util.Utility;
import exception.ValidationException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * This class represents a JUnit test case for the Histogram class in the model package.
 * It tests the functionality of the Histogram class.
 */

public class HistogramTest {

  private ImageModelImpl image1;

  @Before
  public void setUp() throws IOException {
    image1 = new ImageModelImpl();
    int[][][] imgData = Utility.readImage("test/res/mat.ppm");
    image1.setImage("mat", imgData);
  }

  @Test
  public void testConstructorHistogram() throws ValidationException {
    Histogram hist = new Histogram(image1.getImage("mat"));
    int[][] output = hist.histogram();
    assertNotNull(output);
  }


  @Test
  public void testValidHistogramFist20Values() {
    try {
      Histogram hist = new Histogram(image1.getImage("mat"));
      int[][] output = hist.histogram();
      int[][] test = new int[20][output[0].length];
      for (int i = 0; i < 20; i++) {
        test[i] = Arrays.copyOf(output[i], output[i].length);
      }
      String dataString = "[[5, 2, 7, 0], [0, 0, 0, 0], [0, 0, 0, 0], " +
              "[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], " +
              "[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], " +
              "[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], " +
              "[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0]]";
      assertEquals(dataString, Arrays.deepToString(test));
    } catch (ValidationException e) {
      throw new RuntimeException(e);
    }
  }


}
