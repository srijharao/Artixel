package model;

/**
 * KernelImpl gives kernel and dimension to caller.
 */
public class KernelImpl implements IKernel {

  private final int dimension;
  private final double[][] kernel;


  /**
   * Checks invalid cases for kernel and instantiates kernelImpl object.
   *
   * @param kernel is a 2D array of numbers, having odd dimensions.
   */
  public KernelImpl(double[][] kernel) {
    if (kernel.length != kernel[0].length) {
      throw new IllegalArgumentException("Width and Height of filter should be same");
    }
    if (kernel.length % 2 == 0) {
      throw new IllegalArgumentException("Filter should have odd dimensions");
    }
    this.kernel = kernel;
    this.dimension = kernel.length;
  }


  @Override
  public double[][] getKernel() {
    return this.kernel;

  }

  @Override
  public int getDimension() {
    return this.dimension;
  }
}
