package controller.util;


import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.awt.Color;

import javax.imageio.ImageIO;

import exception.ValidationException;
import model.image.Image;
import model.pixel.Channel;
import model.pixel.Pixel;

/**
 * Reads an image file in the PPM format and returns a 3D array of integers representing the color
 * values of each pixel.
 */
public final class Utility {

  /**
   * Reads an image file and returns a three-dimensional integer array representing the pixel values
   * of the image.
   *
   * @param filename the filename of the image file to read
   * @return a three-dimensional integer array representing the pixel values of the image
   * @throws IOException if there is an error reading the file
   */
  public static int[][][] readImage(String filename) throws IOException {
    String extension = filename.substring(filename.lastIndexOf(".") + 1);
    if (Arrays.asList(ImageIO.getReaderFileSuffixes()).contains(extension)) {
      return readFromImage(filename);
    } else if (extension.equalsIgnoreCase("ppm")) {
      return readFromPPM(filename);
    } else {
      throw new IOException("invalid file, accepted formats are ppm, jpg, jpeg, png, bmp");
    }
  }

  /**
   * Writes the given Image object to a file with the specified filename.
   *
   * @param image    the Image object to be written to file
   * @param filename the filename of the file to write the Image object to
   * @throws IOException         if there is an error writing the Image object to file
   * @throws ValidationException if the file extension is not valid or if not from accepted format.
   */
  public static void writeImage(Image image, String filename)
          throws IOException, ValidationException {
    String extension = filename.substring(filename.lastIndexOf(".") + 1);
    if (extension.equalsIgnoreCase("ppm")) {
      writeToPPM(filename, image);
    } else if (Arrays.asList(ImageIO.getWriterFileSuffixes()).contains(extension)) {
      writeToImage(image, filename);
    } else {
      throw new ValidationException("invalid file format, accepted formats "
              + "are ppm, jpg, jpeg, png, bmp");
    }
  }

  /**
   * Reads the contents of a PPM file and returns a 3D array that contains the RGB values of each
   * pixel in the image.
   *
   * @param fileName the name of the PPM file to read
   * @return a 3D integer array representing the RGB values of each pixel in the image
   * @throws IOException      if there was an error reading from the file
   * @throws RuntimeException if the file is not a valid PPM file
   */
  private static int[][][] readFromPPM(String fileName)
          throws IOException, RuntimeException {
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(fileName));
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("File " + fileName + " not found!");
    }
    try {
      StringBuilder builder = new StringBuilder();
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        if (s.charAt(0) != '#') {
          builder.append(s).append(System.lineSeparator());
        }
      }

      sc = new Scanner(builder.toString());
      String token;
      token = sc.next();
      if (!token.equals("P3")) {
        System.out.println("Invalid PPM file: plain RAW file should begin with P3");
      }
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException();
    }
    int width = sc.nextInt(); // store the width
    int height = sc.nextInt(); // store the height
    int maxValue = sc.nextInt(); // store the max value
    int[][][] image = new int[height][width][3]; // 3D array to store image data
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        image[i][j][0] = sc.nextInt(); // red component
        image[i][j][1] = sc.nextInt(); // green component
        image[i][j][2] = sc.nextInt(); // blue component
      }
    }

    return image;
  }


  /**
   * Writes the given Image object to a PPM file.
   *
   * @param destination the path of the file to be written
   * @param image       the Image object to be written to the file
   * @throws IOException if there is an error writing to the file
   */
  private static void writeToPPM(String destination, Image image) throws IOException {
    try {
      FileWriter fileWriter = new FileWriter(destination);
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

      int height = image.getImageHeight();
      int width = image.getImageWidth();

      // Write the PPM header
      bufferedWriter.write("P3\n");
      bufferedWriter.write(width + " " + height + "\n");
      bufferedWriter.write("255\n");

      Pixel[][] imageArray = image.getPixelArray();
      // Write the pixel values
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          bufferedWriter.write(
                  imageArray[i][j].getComponent(Channel.RED.ordinal()) + " "
                          + imageArray[i][j].getComponent(Channel.GREEN.ordinal()) + " "
                          + imageArray[i][j].getComponent(Channel.BLUE.ordinal()) + "\n");
        }
      }
      bufferedWriter.close();

    } catch (IOException e) {
      throw new IOException("Error writing PPM file: " + e.getMessage());
    }
  }

  /**
   * Reads an image file and returns a three-dimensional integer array representing the pixel values
   * of the image.
   *
   * @param filename the filename of the image file to read
   * @return a three-dimensional integer array representing the pixel values of the image
   * @throws IOException if there is an error reading the file
   */
  private static int[][][] readFromImage(String filename) throws IOException {
    // read image using ImageIO library
    BufferedImage input = ImageIO.read(new FileInputStream(filename));

    // get image height and width
    int height = input.getHeight();
    int width = input.getWidth();

    // initialize 3-dimensional array to store pixel values
    int[][][] imageArray = new int[height][width][3];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int color = input.getRGB(x, y);
        Color col = new Color(color);
        imageArray[y][x][0] = col.getRed();
        imageArray[y][x][1] = col.getGreen();
        imageArray[y][x][2] = col.getBlue();
      }
    }
    return imageArray;
  }

  /**
   * Writes the given Image object to a file with the specified filename.
   *
   * @param image    the Image object to be written to file
   * @param filename the filename of the file to write the Image object to
   * @throws IOException if there is an error writing the Image object to file
   */
  private static void writeToImage(Image image, String filename) throws IOException {
    // get image height and width
    int height = image.getImageHeight();
    int width = image.getImageWidth();

    // Create a new BufferedImage object with the same dimensions as the image
    BufferedImage imageOP = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    // Get the pixel array from the image
    Pixel[][] imageArray = image.getPixelArray();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int red = imageArray[i][j].getComponent(Channel.RED.ordinal());
        int green = imageArray[i][j].getComponent(Channel.GREEN.ordinal());
        int blue = imageArray[i][j].getComponent(Channel.BLUE.ordinal());

        // Combine the color components into a single integer value
        int color = (red << 16) + (green << 8) + blue;

        // Set the color of the corresponding pixel in the BufferedImage object
        imageOP.setRGB(j, i, color);
      }
    }

    // Determine the file extension from the filename and write the image
    String extension = filename.substring(filename.indexOf(".") + 1);
    ImageIO.write(imageOP, extension, new File(filename));
  }
}
