package model.strategy;

import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import controller.util.Utility;
import exception.ValidationException;
import model.ImageModelImpl;
import model.KernelImpl;
import model.image.Image;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * This class represents the test suite for the BlurFilter class.
 * It contains test cases to ensure the proper functionality of the class.
 */

public class BlurFilterTest {

  @Test
  public void testBlurFilterConstructorWithKernelMatrix() {
    double[][] kernelMatrix = {
            {0.0625, 0.125, 0.0625},
            {0.125, 0.25, 0.125},
            {0.0625, 0.125, 0.0625}
    };
    BlurFilter blurFilter = new BlurFilter(kernelMatrix);
    assertNotNull(blurFilter);
  }

  @Test
  public void testBlurFilterConstructorWithKernelImpl() {
    double[][] kernelMatrix = {
            {0.0625, 0.125, 0.0625},
            {0.125, 0.25, 0.125},
            {0.0625, 0.125, 0.0625}
    };
    KernelImpl kernel = new KernelImpl(kernelMatrix);
    BlurFilter blurFilter = new BlurFilter(kernel);
    assertNotNull(blurFilter);
  }

  @Test
  public void testBlurFilterDefaultConstructor() {
    BlurFilter blurFilter = new BlurFilter();
    assertNotNull(blurFilter);
  }


  @Test
  public void testModelBlurImage() {
    try {
      ImageModelImpl image1 = new ImageModelImpl();
      int[][][] imgData = Utility.readImage("test/res/mat.ppm");
      image1.setImage("mat", imgData);
      BlurFilter blurFilter = new BlurFilter();
      // using the image1 that is used in the setup
      Image image2 = image1.getImage("mat");
      Image outputImage = blurFilter.applyOperator(image2);

      String dataString = "[[{x=0, y=0, Color[113, 141, 6]}, {x=0, y=1, Color[138, 170, 13]}, " +
              "{x=0, y=2, Color[146, 128, 14]}, {x=0, y=3, Color[116, 69, 15]}], " +
              "[{x=1, y=0, Color[134, 175, 24]}, {x=1, y=1, Color[143, 191, 54]}, " +
              "{x=1, y=2, Color[152, 126, 54]}, {x=1, y=3, Color[126, 62, 42]}]," +
              " [{x=2, y=0, Color[98, 150, 65]}, {x=2, y=1, Color[97, 163, 108]}, " +
              "{x=2, y=2, Color[90, 130, 104]}, {x=2, y=3, Color[71, 74, 73]}], " +
              "[{x=3, y=0, Color[35, 100, 82]}, {x=3, y=1, Color[35, 115, 104]}, " +
              "{x=3, y=2, Color[28, 106, 101]}, {x=3, y=3, Color[20, 66, 79]}]]";

      assertEquals(dataString, Arrays.deepToString(outputImage.getPixelArray()));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

}
