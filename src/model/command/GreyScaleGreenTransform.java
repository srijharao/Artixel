package model.command;

import model.image.Image;
import model.strategy.AbstractTransformStrategy;

import static model.util.Constants.GREYSCALE_GREEN_TRANSFORM_MATRIX;

/**
 * GreenTransform strategy class for GreyScale.
 * Transforms an image into greyscale image using green
 * component of the color image.
 * Extends AbstractTransformStrategy and implements applyOperator
 * method with color transformation.
 */
public class GreyScaleGreenTransform extends AbstractTransformStrategy {

    public GreyScaleGreenTransform() {
        super(GREYSCALE_GREEN_TRANSFORM_MATRIX);
    }


    @Override
    public Image applyOperator(Image image) {
        return doTransform(image);
    }
}
