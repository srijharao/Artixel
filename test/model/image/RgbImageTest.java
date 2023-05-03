package model.image;

import org.junit.Test;

import java.util.Arrays;

import model.pixel.Pixel;

import static org.junit.Assert.assertEquals;


/**
 * Junit tests to verify methods of RgbImageTest class.
 */
public class RgbImageTest {

  private static final String FILENAME = "res/mat.ppm";
  private static final int[][][] IMAGE_ARRAY = {
          {{0, 0, 0}, {255, 255, 255}},
          {{255, 0, 0}, {0, 255, 0}}
  };


  @Test
  public void testRgbImageFromArray() {
    Image image = new RgbImage(IMAGE_ARRAY);
    assertEquals(2, image.getImageHeight());
    assertEquals(2, image.getImageWidth());
  }

  @Test
  public void testGetPixelArray() {
    Image image = new RgbImage(IMAGE_ARRAY);
    Pixel[][] pixelArray = image.getPixelArray();
    String expectedString = "[[{x=0, y=0, Color[0, 0, 0]}, {x=0, y=1, Color[255, 255, 255]}], "
            + "[{x=1, y=0, Color[255, 0, 0]}, {x=1, y=1, Color[0, 255, 0]}]]";
    assertEquals(expectedString, Arrays.deepToString(pixelArray));
  }

}