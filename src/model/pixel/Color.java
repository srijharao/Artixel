package model.pixel;

import model.RgbTransformImpl;

/**
 * Color interface specific to actions that correspond to the color component of a pixel. contains
 * methods to retrieve value, intensity, luma, component and brightens the image
 */
public interface Color {
  /**
   * Returns the value of the color.
   *
   * @return the value of the color as a double value.
   */
  double getValue();

  /**
   * Returns the intensity of the color.
   *
   * @return the intensity of the color as a double value.
   */
  double getIntensity();

  /**
   * Returns the luma of the color.
   *
   * @return the luma of the color as a double value.
   */
  double getLuma();

  /**
   * Returns the value of the specified color component in the given dimension.
   *
   * @param dim the dimension of the component to retrieve.
   * @return the value of the component in the specified dimension as an integer.
   */
  int getComponent(int dim);

  /**
   * Increases the brightness of the color by the specified value.
   *
   * @param value the value by which to increase the brightness of the color.
   */
  void brighten(int value);

  /**
   * Applies the specified color transform to the color.
   *
   * @param transformMatrix the color transform matrix to apply to the color.
   * @return a new Color object representing the transformed color.
   */
  Color colorTransform(RgbTransformImpl transformMatrix);

  /**
   * Ensures that the color values are within the valid range.
   */
  void clamp();
}
