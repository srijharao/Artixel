package model;

/**
 * RgbTransformImpl provides kernel for color transform operation and
 * verifies it is of sixe 3*3 for rgb.
 */
public class RgbTransformImpl extends KernelImpl {

  /**
   * Constructor passes Kernel to KernelImpl class and verifies if it is
   * of size 3*3 as required for rgb.
   *
   * @param kernel a 2D array of numbers, having odd dimensions.
   */
  public RgbTransformImpl(double[][] kernel) {
    super(kernel);
    if (kernel.length != 3 && kernel[0].length != 3) {
      throw new IllegalArgumentException("Kernel should be of size 3*3 for RGB Transform");
    }
  }


}
