package model;

import model.image.Image;
import model.pixel.Channel;
import model.pixel.Pixel;

/**
 * The Histogram class represents a 2-dimensional array of integer values that
 * can be used to visualize the distribution of pixel values in an image. This class
 * takes an Image object as input and generates a histogram of its pixel values.
 */
public class Histogram {
  //2-dimensional array that represents the histogram.
  int[][] hist;

  //The Image object for which the histogram is generated.
  Image image;

  /**
   * Constructor that takes an Image object as input.
   *
   * @param image The Image object for which the histogram is generated.
   */
  public Histogram(Image image) {
    this.image = image;
  }

  /**
   * Method that generates the histogram of the input image.
   *
   * @return A 2-dimensional array representing the histogram.
   */
  public int[][] histogram() {
    int width = image.getImageWidth();
    int height = image.getImageHeight();
    Pixel[][] imageArray = image.getPixelArray();
    hist = new int[256][4]; // 2D array to store the histogram values
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int r = imageArray[y][x].getComponent(Channel.RED.ordinal());
        int g = imageArray[y][x].getComponent(Channel.GREEN.ordinal());
        int b = imageArray[y][x].getComponent(Channel.BLUE.ordinal());
        int i = (int) imageArray[y][x].getIntensity();
        hist[r][0]++;
        hist[g][1]++;
        hist[b][2]++;
        hist[i][3]++;
      }
    }
    return hist;
  }
}
