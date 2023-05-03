package model.strategy;

import model.image.Image;
import model.image.RgbImage;
import model.pixel.Pixel;
import model.pixel.PixelImpl;
import model.pixel.RgbColor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * This class represents the test suite for the SepiaTransform class.
 * It contains test cases to ensure the proper functionality of the class.
 */
public class SepiaTransformTest {

  @Test
  public void testConstructor() {
    SepiaTransform st = new SepiaTransform();
    assertNotNull(st);
  }

  @Test
  public void testApplyOperator() {
    // create a sample image with 2x2 pixels
    Pixel[][] pixels = new Pixel[2][2];
    pixels[0][0] = new PixelImpl(0, 0, new RgbColor(255, 255, 255));
    pixels[0][1] = new PixelImpl(0, 1, new RgbColor(0, 0, 0));
    pixels[1][0] = new PixelImpl(1, 0, new RgbColor(128, 128, 128));
    pixels[1][1] = new PixelImpl(1, 1, new RgbColor(100, 150, 200));
    Image inputImage = new RgbImage(pixels);

    // create expected output image with 2x2 pixels
    Pixel[][] expectedPixels = new Pixel[2][2];
    expectedPixels[0][0] = new PixelImpl(0, 0, new RgbColor(255, 255, 238));
    expectedPixels[0][1] = new PixelImpl(0, 1, new RgbColor(0, 0, 0));
    expectedPixels[1][0] = new PixelImpl(1, 0, new RgbColor(172, 153, 119));
    expectedPixels[1][1] = new PixelImpl(1, 1, new RgbColor(192, 171, 133));
    Image expectedImage = new RgbImage(expectedPixels);

    // apply sepia transform to the input image
    SepiaTransform sepiaTransform = new SepiaTransform();
    Image outputImage = sepiaTransform.applyOperator(inputImage);

    // compare output image with expected image
    assertEquals(expectedImage.toString(), outputImage.toString());
  }

}
