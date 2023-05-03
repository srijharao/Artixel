package model.command;

import model.image.Image;
import model.strategy.AbstractTransformStrategy;

import static model.util.Constants.GREYSCALE_LUMA_TRANSFORM_MATRIX;

/**
 * LumaTransform strategy class for GreyScale.
 * Transforms an image into greyscale image using Luma
 * of the color image.
 * Extends AbstractTransformStrategy and implements applyOperator method with
 * color transformation.
 */
public class GreyScaleLumaTransform extends AbstractTransformStrategy {

    public GreyScaleLumaTransform() {
        super(GREYSCALE_LUMA_TRANSFORM_MATRIX);
    }

    @Override
    public Image applyOperator(Image image) {
        return doTransform(image);
    }
}