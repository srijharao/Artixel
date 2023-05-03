package model.strategy;

import model.KernelImpl;
import model.image.Image;

import static model.util.Constants.BLUR_FILTER_KERNEL;


/**
 * Class for Blur Filter operations.
 */
public class BlurFilter extends AbstractFilterStrategy {

  /**
   * Constructor to pass received kernelMatrix into AbstractFilterStrategy.
   *
   * @param kernelMatrix kernelMatrix
   */
  public BlurFilter(double[][] kernelMatrix) {
    super(kernelMatrix);
  }

  /**
   * Constructor to pass received kernelImpl object into AbstractFilterStrategy.
   *
   * @param kernel kernelImpl
   */
  public BlurFilter(KernelImpl kernel) {
    super(kernel);
  }

  /**
   * Constructor to send Blur filter constant into AbstractFilterStrategy.
   */
  public BlurFilter() {
    super(BLUR_FILTER_KERNEL);
  }

  @Override
  public Image applyOperator(Image image) {
    return doFilter(image);
  }
}
