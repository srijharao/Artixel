package model.pixel;

import model.RgbTransformImpl;

/**
 * A class that represents a single pixel in an image.
 */
public class PixelImpl implements Pixel {

  private final int x;
  private final int y;
  private final Color color;

  /**
   * Constructs a new {@code PixelImpl} with the given indices and color.
   *
   * @param x     the x-coordinate of the pixel.
   * @param y     the y-coordinate of the pixel.
   * @param color the color of the pixel.
   * @throws IllegalArgumentException if the x or y coordinate is negative.
   */
  public PixelImpl(int x, int y, Color color) {
    if (x < 0 || y < 0) {
      throw new IllegalArgumentException("Indices can't be negative");
    }
    this.x = x;
    this.y = y;
    this.color = color;
  }

  @Override
  public int getXCoordinate() {
    return x;
  }

  @Override
  public int getYCoordinate() {
    return y;
  }

  @Override
  public void brighten(int value) {
    color.brighten(value);
  }

  @Override
  public Color colorTransform(RgbTransformImpl transformMatrix) {
    return color.colorTransform(transformMatrix);
  }

  @Override
  public void clamp() {
    color.clamp();
  }

  @Override
  public double getValue() {
    return color.getValue();
  }

  @Override
  public double getIntensity() {
    return color.getIntensity();
  }

  @Override
  public double getLuma() {
    return color.getLuma();
  }

  @Override
  public int getComponent(int dim) {
    return color.getComponent(dim);
  }

  @Override
  public String toString() {
    return "{"
            + "x=" + x
            + ", y=" + y
            + ", " + color
            + '}';
  }
}
