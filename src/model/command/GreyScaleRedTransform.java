package model.command;

import model.image.Image;
import model.strategy.AbstractTransformStrategy;

import static model.util.Constants.GREYSCALE_RED_TRANSFORM_MATRIX;

/**
 * RedTransform strategy class for GreyScale.
 * Transforms an image into greyscale image using red
 * component of the color image.
 * Extends AbstractTransformStrategy and implements applyOperator
 * method with color transformation.
 */
public class GreyScaleRedTransform extends AbstractTransformStrategy {

    public GreyScaleRedTransform() {
        super(GREYSCALE_RED_TRANSFORM_MATRIX);
    }

    @Override
    public Image applyOperator(Image image) {
        return doTransform(image);
    }
}
