package model.strategy;

import org.junit.Before;
import org.junit.Test;

import model.image.Image;
import model.image.RgbImage;
import model.pixel.Pixel;
import model.pixel.PixelImpl;
import model.pixel.RgbColor;

import static org.junit.Assert.assertEquals;

/**
 * This class represents the test suite for the DitherFilter class.
 * It contains test cases to ensure the proper functionality of the class.
 */
public class DitherFilterTest {

  private Image testImage;
  private Image expectedImage;
  private DitherFilter ditherFilter;

  @Before
  public void setUp() {
    // create a test image
    Pixel[][] pixelArray = new Pixel[][]{
            {new PixelImpl(0, 0, new RgbColor(10, 20, 30)), new PixelImpl(0, 1,
                    new RgbColor(40, 50, 60))},
            {new PixelImpl(1, 0, new RgbColor(255, 190, 255)), new PixelImpl(1, 1,
                    new RgbColor(100, 110, 120))}
    };
    testImage = new RgbImage(pixelArray);

    // create the expected image after applying the dither filter
    Pixel[][] expectedPixelArray = new Pixel[][]{
            {new PixelImpl(0, 0, new RgbColor(0, 0, 0)), new PixelImpl(0, 1,
                    new RgbColor(0, 0, 0))},
            {new PixelImpl(1, 0, new RgbColor(255, 255, 255)), new PixelImpl(1, 1,
                    new RgbColor(0, 0, 0))}
    };
    expectedImage = new RgbImage(expectedPixelArray);

    // create the dither filter object
    ditherFilter = new DitherFilter();
  }

  @Test
  public void testDitherFilterApplyOperator() {
    Image resultImage = ditherFilter.applyOperator(testImage);
    assertEquals(expectedImage.toString(), resultImage.toString());
  }
}
