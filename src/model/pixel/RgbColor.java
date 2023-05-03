package model.pixel;

import model.RgbTransformImpl;

/**
 * RgbColor represents a color in the RGB color model. It extends AbstractColor class and implements
 * methods to get red, green and blue components of the color.
 */
public class RgbColor extends AbstractColor {

  /**
   * Constructs a RgbColor object with the specified red, green, and blue components.
   *
   * @param red   the red component of the color
   * @param green the green component of the color
   * @param blue  the blue component of the color
   */
  public RgbColor(int red, int green, int blue) {
    super(3);
    colorComponents[RgbChannel.RED.ordinal()] = red;
    colorComponents[RgbChannel.GREEN.ordinal()] = green;
    colorComponents[RgbChannel.BLUE.ordinal()] = blue;
  }

  /**
   * Returns the red component of the color.
   *
   * @return the red component of the color
   */
  private int getRedComponent() {
    return colorComponents[RgbChannel.RED.ordinal()];
  }

  /**
   * Returns the green component of the color.
   *
   * @return the green component of the color
   */
  private int getGreenComponent() {
    return colorComponents[RgbChannel.GREEN.ordinal()];
  }

  /**
   * Returns the blue component of the color.
   *
   * @return the blue component of the color
   */
  private int getBlueComponent() {
    return colorComponents[RgbChannel.BLUE.ordinal()];
  }

  @Override
  public double getIntensity() {
    return (getRedComponent() + getGreenComponent() + getBlueComponent() + 0.0) / 3;
  }

  @Override
  public double getLuma() {
    return 0.2126 * getRedComponent() + 0.7152 * getGreenComponent() + 0.0722 * getBlueComponent();
  }

  @Override
  public Color colorTransform(RgbTransformImpl transformMatrix) {
    double[][] kernel = transformMatrix.getKernel();
    double[] newColorComponent = new double[3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        newColorComponent[i] += this.colorComponents[j] * kernel[i][j];
      }
    }
    return new RgbColor((int) newColorComponent[0], (int) newColorComponent[1],
            (int) newColorComponent[2]);
  }

  @Override
  public String toString() {
    return "Color[" + colorComponents[RgbChannel.RED.ordinal()] + ", "
        + colorComponents[RgbChannel.GREEN.ordinal()] + ", "
        + colorComponents[RgbChannel.BLUE.ordinal()]
        + "]";
  }
}
