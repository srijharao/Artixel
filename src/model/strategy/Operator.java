package model.strategy;

import model.image.Image;

/**
 * Interface for filter and transform operations.
 */
public interface Operator {

  /**
   * Applies filter or color transform operations on an image.
   *
   * @param image to apply operations on
   * @return updated image
   */
  Image applyOperator(Image image);

}
