package model.strategy;

import model.RgbTransformImpl;
import model.image.Image;
import model.image.RgbImage;
import model.pixel.Pixel;
import model.pixel.PixelImpl;

/**
 * Transform strategy for color operations.
 */
public abstract class AbstractTransformStrategy implements Operator {

  protected RgbTransformImpl transformMatrix;

  /**
   * Constructor to verify that kernel passed is of size 3, when it is passed as Kernel object.
   *
   * @param transformMatrix kernel
   */
  public AbstractTransformStrategy(RgbTransformImpl transformMatrix) {
    if (transformMatrix.getDimension() != 3) {
      throw new IllegalArgumentException("Kernel should be of size 3*3 for RGB Transform");
    }
  }

  /**
   * Constructor initializes a kernel into transformMatrix when colorMatrix is passed.
   *
   * @param colorMatrix colorMatrix
   */
  public AbstractTransformStrategy(double[][] colorMatrix) {
    if (colorMatrix.length != 3 && colorMatrix[0].length != 3) {
      throw new IllegalArgumentException("Kernel should be of size 3*3 for RGB Transform");
    }
    this.transformMatrix = new RgbTransformImpl(colorMatrix);
  }

  /**
   * Carries out color transformation operation on an image and returns an updated image.
   *
   * @param image image to operate on
   * @return updated image
   */
  protected Image doTransform(Image image) {

    Pixel[][] newPixelArray = image.getPixelArray();

    int width = image.getImageWidth();
    int height = image.getImageHeight();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        newPixelArray[i][j] = new PixelImpl(i, j,
                newPixelArray[i][j].colorTransform(transformMatrix));
        newPixelArray[i][j].clamp();
      }
    }
    return new RgbImage(newPixelArray);
  }

}
