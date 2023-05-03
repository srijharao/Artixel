package model.strategy;

import model.KernelImpl;
import model.image.Image;

import static model.util.Constants.SHARPEN_FILTER_KERNEL;

/**
 * Class for Sharpen Filter operations.
 */
public class SharpenFilter extends AbstractFilterStrategy {

  /**
   * Constructor to pass received kernelMatrix into AbstractFilterStrategy.
   *
   * @param kernelMatrix kernelMatrix
   */
  public SharpenFilter(double[][] kernelMatrix) {
    super(kernelMatrix);
  }

  /**
   * Constructor to pass received kernelImpl object into AbstractFilterStrategy.
   *
   * @param kernel kernelImpl
   */
  public SharpenFilter(KernelImpl kernel) {
    super(kernel);
  }

  /**
   * Default constructor to send sharpen filter constant into AbstractFilterStrategy.
   */
  public SharpenFilter() {
    super(SHARPEN_FILTER_KERNEL);
  }

  @Override
  public Image applyOperator(Image image) {
    return doFilter(image);
  }
}
