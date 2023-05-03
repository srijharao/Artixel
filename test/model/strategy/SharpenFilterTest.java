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
 * This class represents the test suite for the SharpenFilter class.
 * It contains test cases to ensure the proper functionality of the class.
 */
public class SharpenFilterTest {

  @Test
  public void testSharpenFilterConstructorWithKernelMatrix() {
    double[][] kernelMatrix = {
            {0.0625, 0.125, 0.0625},
            {0.125, 0.25, 0.125},
            {0.0625, 0.125, 0.0625}
    };
    SharpenFilter sharpenFilter = new SharpenFilter(kernelMatrix);
    assertNotNull(sharpenFilter);
  }

  @Test
  public void testSharpenFilterConstructorWithKernelImpl() {
    double[][] kernelMatrix = {
            {0.0625, 0.125, 0.0625},
            {0.125, 0.25, 0.125},
            {0.0625, 0.125, 0.0625}
    };
    KernelImpl kernel = new KernelImpl(kernelMatrix);
    SharpenFilter sharpenFilter = new SharpenFilter(kernel);
    assertNotNull(sharpenFilter);
  }

  @Test
  public void testSharpenFilterDefaultConstructor() {
    SharpenFilter sharpenFilter = new SharpenFilter();
    assertNotNull(sharpenFilter);
  }


  @Test
  public void testModelSharpenImage() {
    try {
      ImageModelImpl image1 = new ImageModelImpl();
      int[][][] imgData = Utility.readImage("test/res/mat.ppm");
      image1.setImage("mat", imgData);
      SharpenFilter sharpenFilter = new SharpenFilter();
      // using the image1 that is used in the setup
      Image image2 = image1.getImage("mat");
      Image outputImage = sharpenFilter.applyOperator(image2);

      String dataString = "[[{x=0, y=0, Color[229, 255, 0]}, {x=0, y=1, Color[255, 255, 0]}, " +
              "{x=0, y=2, Color[255, 255, 14]}, {x=0, y=3, Color[255, 102, 0]}], " +
              "[{x=1, y=0, Color[255, 255, 1]}, {x=1, y=1, Color[255, 255, 63]}," +
              " {x=1, y=2, Color[255, 160, 54]}, {x=1, y=3, Color[255, 74, 63]}]," +
              " [{x=2, y=0, Color[186, 255, 127]}, {x=2, y=1, Color[156, 255, 255]}," +
              " {x=2, y=2, Color[89, 237, 255]}, {x=2, y=3, Color[118, 77, 141]}], " +
              "[{x=3, y=0, Color[17, 195, 255]}, {x=3, y=1, Color[29, 255, 216]}, " +
              "{x=3, y=2, Color[0, 196, 252]}, {x=3, y=3, Color[0, 153, 198]}]]";

      assertEquals(dataString, Arrays.deepToString(outputImage.getPixelArray()));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

}
