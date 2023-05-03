package model;

import exception.ValidationException;
import model.image.Image;

/**
 * This interface represents an image processing model. It provides methods to load an image, apply
 * various image processing operations like brightness adjustment, flipping, splitting into RGB
 * components, etc., and save the processed image.
 */
public interface ImageModel {

  /**
   * Loads an image file in the PPM format and assigns it an alias.
   *
   * @param alias   a string to identify the loaded image.
   * @param imgData is the image data.
   * @throws ValidationException if the file format is invalid.
   */
  void setImage(String alias, int[][][] imgData)
          throws ValidationException;

  /**
   * Adjusts the brightness of an image by a given value.
   *
   * @param value       the value by which to adjust the brightness.
   * @param filename    the name of the original image file.
   * @param newFilename the name of the new image file after applying the brightness adjustment.
   */
  void brighten(int value, String filename, String newFilename) throws ValidationException;

  /**
   * Flips an image vertically.
   *
   * @param filename    the name of the original image file.
   * @param newFilename the name of the new image file after applying the vertical flip.
   */
  void vFlip(String filename, String newFilename) throws ValidationException;

  /**
   * Flips an image horizontally.
   *
   * @param filename    the name of the original image file.
   * @param newFilename the name of the new image file after applying the horizontal flip.
   */
  void hFlip(String filename, String newFilename) throws ValidationException;

  /**
   * Splits an image into its RGB components.
   *
   * @param filename   the name of the original image file.
   * @param redImage   the name of the file to store the red component of the image.
   * @param greenImage the name of the file to store the green component of the image.
   * @param blueImage  the name of the file to store the blue component of the image.
   * @throws ValidationException if any of the specified file names are invalid.
   */
  void imgSplitRGB(String filename, String redImage, String greenImage, String blueImage)
          throws ValidationException;

  /**
   * Combines the RGB components of an image.
   *
   * @param newFilename the name of the new image file after combining the RGB components.
   * @param redImage    the name of the file containing the red component of the image.
   * @param greenImage  the name of the file containing the green component of the image.
   * @param blueImage   the name of the file containing the blue component of the image.
   */
  void imgCombineRGB(String newFilename, String redImage, String greenImage, String blueImage)
          throws ValidationException;

  /**
   * Converts an image to grayscale using a specified color component.
   *
   * @param component   the color component to use for the grayscale conversion.
   * @param filename    the name of the original image file.
   * @param newFilename the name of the new image file after applying the grayscale conversion.
   * @throws ValidationException if the specified color component is invalid.
   */
  void imgConvertGreyScale(String component, String filename, String newFilename)
          throws ValidationException;

  /**
   * Saves an image in the PPM format.
   *
   * @param filename the name of the image file
   */
  Image getImage(String filename) throws ValidationException;

  /**
   * Converts an image from color to grayscale.
   *
   * @param source      the path of the source image file
   * @param destination the path of the destination image file
   * @throws ValidationException if the source image file is invalid or cannot be read.
   */
  void grayscale(String source, String destination) throws ValidationException;

  /**
   * Generates a histogram of the pixel intensity values of an image.
   *
   * @param source      the path of the source image file
   * @param destination the path of the destination text file where the histogram will be saved
   * @throws ValidationException if the source image file is invalid or cannot be read.
   */
  void histogram(String source, String destination) throws ValidationException;

  /**
   * Returns the histogram data of an image as a 2D array of integers.
   *
   * @param source the path of the source image file.
   * @return the histogram data as a 2D array of integers, where each row represents color channel.
   * @throws ValidationException if the source image file is invalid or cannot be read.
   */
  int[][] getHist(String source) throws ValidationException;

  /**
   * Applies a filter transformation to an image.
   *
   * @param source      the path of the source image file.
   * @param destination the path of the destination image file.
   * @param command     the command string specifying the filter transformation to apply.
   * @throws ValidationException if the source image file is invalid or cannot be read.
   */
  void filterTransformation(String source, String destination, String command)
          throws ValidationException;

  /**
   * Applies a color transformation to an image.
   *
   * @param source      the path of the source image file
   * @param destination the path of the destination image file
   * @param command     the command string specifying the color transformation to apply.
   * @throws ValidationException if the source image file is invalid.
   */
  void colorTransformation(String source, String destination, String command)
          throws ValidationException;
}
