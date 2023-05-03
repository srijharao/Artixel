package model.pixel;

/**
 * An abstract class representing a color in an image. Provides methods for accessing and
 * manipulating color components.
 */
public abstract class AbstractColor implements Color {

  //An array containing the color components.
  protected Integer[] colorComponents;

  /**
   * Constructs an AbstractColor object with the specified number of color components.
   *
   * @param dimension the number of color components
   */
  public AbstractColor(int dimension) {
    colorComponents = new Integer[dimension];
  }

  @Override
  public int getComponent(int dim) {
    if (dim >= colorComponents.length) {
      throw new IllegalArgumentException("Invalid component");
    }
    return colorComponents[dim];
  }

  @Override
  public double getValue() {
    int max = 0;
    for (int i = 0; i < colorComponents.length; i++) {
      max = Math.max(max, colorComponents[i]);
    }
    return max;
  }

  @Override
  public void brighten(int value) {
    for (int i = 0; i < colorComponents.length; i++) {
      colorComponents[i] += value;
    }
    clamp();
  }

  @Override
  public void clamp() {
    for (int i = 0; i < colorComponents.length; i++) {
      if (colorComponents[i] > 0) {
        colorComponents[i] = Math.min(colorComponents[i], 255);
      } else {
        colorComponents[i] = 0;
      }
    }
  }
}
