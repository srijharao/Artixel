package model;

/**
 * Kernel interface for get kernel and its dimension.
 */
public interface IKernel {

  /**
   * Gives Kernel to the caller.
   * @return kernel
   */
  double[][] getKernel();

  /**
   * Gives kernel dimension.
   * @return dimension
   */
  int getDimension();

}
