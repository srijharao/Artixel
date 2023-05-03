package model.command;

import model.image.Image;
import model.strategy.AbstractTransformStrategy;

import static model.util.Constants.GREYSCALE_BLUE_TRANSFORM_MATRIX;

/**
 * BlueTransform strategy class for GreyScale. Transforms an image into greyscale image using blue
 * component of the color image. Extends AbstractTransformStrategy and implements applyOperator
 * method with color transformation.
 */
public class GreyScaleBlueTransform extends AbstractTransformStrategy {

    public GreyScaleBlueTransform() {
        super(GREYSCALE_BLUE_TRANSFORM_MATRIX);
    }


    @Override
    public Image applyOperator(Image image) {
        return doTransform(image);
    }
}
