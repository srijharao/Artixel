package model.image;

import model.pixel.Pixel;

/**
 * Interface represents image that stores pixel values of a given image. Facilitates retrieval of
 * image width, height, and actions vFlip, hFlip, convert to greyScale and rgb combine.
 */
public interface Image {

  /**
   * Method to get a 2D array of pixels that represents the image.
   *
   * @return a 2D array of pixels that represents the image
   */
  Pixel[][] getPixelArray();

  /**
   * This method is used to retrieve Width of the image.
   *
   * @return Height of image
   */
  int getImageWidth();


  /**
   * This method is used to retrieve Height of the image.
   *
   * @return Height of image
   */
  int getImageHeight();

}
