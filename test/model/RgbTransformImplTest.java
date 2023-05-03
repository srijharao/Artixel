package model;

import org.junit.Test;

import java.util.Arrays;

import model.util.Constants;

import static org.junit.Assert.assertEquals;


/**
 * This class represents the test suite for the RgbTransformImpl class.
 * It contains test cases to ensure the proper functionality of the class.
 */
public class RgbTransformImplTest {

  RgbTransformImpl transformMatrix = new RgbTransformImpl(Constants.SEPIA_COLOR_TRANSFORM_MATRIX);

  @Test(expected = IllegalArgumentException.class)
  public void testIncorrectRgbTransformKernelLength() {
    //Kernel should be of size 3*3 for RGB Transform
    double[][] sepiaTransform = {
            {0.393, 0.769, 0.189, 0.168, 0.168},
            {0.349, 0.686, 0.168, 0.168, 0.168},
            {0.272, 0.534, 0.131, 0.168, 0.168},
            {0.272, 0.534, 0.131, 0.168, 0.168},
            {0.272, 0.534, 0.131, 0.168, 0.168}
    };
    RgbTransformImpl transformMatrix = new RgbTransformImpl(sepiaTransform);
    assertEquals(3, transformMatrix.getDimension());
  }

  @Test
  public void testExpectedMatrixRgbTransformReturnsDimensionKernel() {
    double[][] sepiaTransform = {
            {0.393, 0.769, 0.189},
            {0.349, 0.686, 0.168},
            {0.272, 0.534, 0.131}
    };
    RgbTransformImpl transformMatrix = new RgbTransformImpl(sepiaTransform);
    assertEquals(3, transformMatrix.getDimension());
    String expectedKernel = "[[0.393, 0.769, 0.189], [0.349, 0.686, 0.168], [0.272, 0.534, 0.131]]";
    assertEquals(expectedKernel, Arrays.deepToString(transformMatrix.getKernel()));
  }

}