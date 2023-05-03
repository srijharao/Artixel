package model.image;

import java.util.Arrays;

import model.pixel.Color;
import model.pixel.Pixel;
import model.pixel.PixelImpl;
import model.pixel.RgbChannel;
import model.pixel.RgbColor;


/**
 * An implementation of the AbstractImage interface for RGB images.
 */
public class RgbImage extends AbstractImage {

  //A 2D array of pixel objects representing the image's pixels.
  private Pixel[][] pixelArray;

  /**
   * Constructs an RgbImage object from a 3D array of pixel values.
   *
   * @param imageArray a 3D array of pixel values representing the image
   */
  public RgbImage(int[][][] imageArray) {
    super.width = imageArray[0].length;
    super.height = imageArray.length;
    constructPixelArray(imageArray);
  }


  /**
   * Constructs an RgbImage object from a 2D array of Pixel objects.
   *
   * @param pixelArray a 2D array of Pixel objects representing the image's pixels
   */
  public RgbImage(Pixel[][] pixelArray) {
    super.width = pixelArray[0].length;
    super.height = pixelArray.length;
    this.pixelArray = pixelArray;
  }


  /**
   * Constructs the 2D array of Pixel objects that represents the image.
   *
   * @param imageArray a 3D array of pixel values representing the image
   */
  private void constructPixelArray(int[][][] imageArray) {
    this.pixelArray = new PixelImpl[getImageHeight()][getImageWidth()];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Color color = new RgbColor(imageArray[i][j][RgbChannel.RED.ordinal()],
                imageArray[i][j][RgbChannel.GREEN.ordinal()],
                imageArray[i][j][RgbChannel.BLUE.ordinal()]);
        Pixel pixelNew = new PixelImpl(i, j, color);
        pixelArray[i][j] = pixelNew;
      }
    }
  }

  @Override
  public Pixel[][] getPixelArray() {
    Pixel[][] clonePixelArray = new PixelImpl[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Color color = new RgbColor(pixelArray[i][j].getComponent(RgbChannel.RED.ordinal()),
                pixelArray[i][j].getComponent(RgbChannel.GREEN.ordinal()),
                pixelArray[i][j].getComponent(RgbChannel.BLUE.ordinal()));
        Pixel pixel = new PixelImpl(i, j, color);
        clonePixelArray[i][j] = pixel;
      }
    }
    return clonePixelArray;
  }

  @Override
  public String toString() {
    return "RgbImage{"
            + "width=" + width
            + ", height=" + height
            + "pixelArray=" + Arrays.deepToString(pixelArray)
            + '}';
  }
}
