package model.strategy;

import model.image.Image;
import model.image.RgbImage;
import model.pixel.Pixel;
import model.pixel.PixelImpl;
import model.pixel.RgbChannel;
import model.pixel.RgbColor;

/**
 * Class for Dither Filter operations.
 */
public class DitherFilter implements Operator {

  /**
   * Applies dither filter on given image.
   *
   * @param image input image
   * @return updated image
   */
  private Image applyDitherFilter(Image image) {
    Pixel[][] pixelArray = image.getPixelArray();
    int width = image.getImageWidth();
    int height = image.getImageHeight();
    double[][] arr = new double[height][width];
    Pixel[][] newPixelArray = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int oldColor = pixelArray[i][j].getComponent(RgbChannel.RED.ordinal());
        int newColor = colorHelper(oldColor);
        double error = oldColor - newColor;
        arr[i][j] = newColor;
        if (checkBounds(i, j + 1, height, width)) {
          arr[i][j + 1] += (error * 7 / 16);
        }
        if (checkBounds(i + 1, j - 1, height, width)) {
          arr[i + 1][j - 1] += (error * 3 / 16);
        }
        if (checkBounds(i + 1, j, height, width)) {
          arr[i + 1][j] += (error * 5 / 16);
        }
        if (checkBounds(i + 1, j + 1, height, width)) {
          arr[i + 1][j + 1] += (error * 1 / 16);
        }
        newPixelArray[i][j] = new PixelImpl(i, j,
                new RgbColor((int) arr[i][j], (int) arr[i][j], (int) arr[i][j]));
      }
    }
    return new RgbImage(newPixelArray);
  }

  private int colorHelper(int color) {
    if (255 - color > color) {
      return 0;
    }
    return 255;
  }

  /**
   * verify whether pixel is in bounds.
   *
   * @param x      x position
   * @param y      y position
   * @param height image height
   * @param width  image width
   * @return true if image is in bounds/ false if not
   */
  private boolean checkBounds(int x, int y, int height, int width) {
    return x >= 0 && y >= 0 && x < height && y < width;
  }

  @Override
  public Image applyOperator(Image image) {
    return applyDitherFilter(image);
  }
}
