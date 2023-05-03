package model.strategy;

import model.image.Image;

import static model.util.Constants.SEPIA_COLOR_TRANSFORM_MATRIX;

/**
 * Sepia color transform strategy class to transform image into sepia image.
 */
public class SepiaTransform extends AbstractTransformStrategy {

  /**
   * Passes sepia transformation matrix to AbstractTransformStrategy.
   */
  public SepiaTransform() {
    super(SEPIA_COLOR_TRANSFORM_MATRIX);
  }

  @Override
  public Image applyOperator(Image image) {
    return doTransform(image);
  }

}
