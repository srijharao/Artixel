package model.strategy;

import model.IKernel;
import model.KernelImpl;
import model.image.Image;
import model.image.RgbImage;
import model.pixel.Pixel;
import model.pixel.PixelImpl;
import model.pixel.RgbChannel;
import model.pixel.RgbColor;

/**
 * The AbstractFilterStrategy class provides an abstract base implementation
 * for filter strategies that operate on image data using a kernel.
 */
public abstract class AbstractFilterStrategy implements Operator {
  private final int dimension;
  private final IKernel kernel;

  /**
   * Constructs a new AbstractFilterStrategy object with the specified kernel.
   *
   * @param kernel the kernel to use for filtering.
   */
  AbstractFilterStrategy(IKernel kernel) {
    this.kernel = kernel;
    this.dimension = kernel.getDimension();
  }

  /**
   * Constructs a new AbstractFilterStrategy object with the specified kernel matrix.
   *
   * @param kernelMatrix the matrix representing the kernel to use for filtering.
   */
  AbstractFilterStrategy(double[][] kernelMatrix) {
    this.kernel = new KernelImpl(kernelMatrix);
    this.dimension = kernel.getDimension();
  }


  protected Image doFilter(Image image) {
    double[][] filter = kernel.getKernel();
    int width = image.getImageWidth();
    int height = image.getImageHeight();
    Pixel[][] pixelArray = image.getPixelArray();
    Pixel[][] newPixelArray = new PixelImpl[height][width];
    int padLength = filter.length / 2;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Pixel newPixel = getPixel(i, j, width, height, padLength, filter, pixelArray);
        newPixelArray[i][j] = newPixel;
        newPixelArray[i][j].clamp();
      }
    }
    return new RgbImage(newPixelArray);
  }

  private Pixel getPixel(int i, int j, int width, int height, int padLength, double[][] filter,
                         Pixel[][] pixelArray) {
    double red = 0.0;
    double green = 0.0;
    double blue = 0.0;
    for (int x = -padLength; x <= padLength; x++) {
      for (int y = -padLength; y <= padLength; y++) {
        if (checkBounds(i + x, j + y, height, width)) {
          red += pixelArray[i + x][j + y].getComponent(RgbChannel.RED.ordinal()) * filter[x
                  + padLength][y + padLength];
          green += pixelArray[i + x][j + y].getComponent(RgbChannel.GREEN.ordinal()) * filter[x
                  + padLength][y + padLength];
          blue += pixelArray[i + x][j + y].getComponent(RgbChannel.BLUE.ordinal()) * filter[x
                  + padLength][y + padLength];
        }
      }
    }
    return new PixelImpl(i, j,
            new RgbColor((int) Math.round(red), (int) Math.round(green), (int) Math.round(blue)));
  }

  private boolean checkBounds(int x, int y, int height, int width) {
    return x >= 0 && y >= 0 && x < height && y < width;
  }

}
