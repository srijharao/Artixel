package model.command;

import model.image.Image;
import model.strategy.AbstractTransformStrategy;

import static model.util.Constants.GREYSCALE_INTENSITY_TRANSFORM_MATRIX;

/**
 * IntensityTransform strategy class for GreyScale.
 * Transforms an image into greyscale image using
 * intensity component of the color image.
 * Extends AbstractTransformStrategy and implements
 * applyOperator method with color transformation.
 */
public class GreyScaleIntensityTransform extends AbstractTransformStrategy {

    public GreyScaleIntensityTransform() {
        super(GREYSCALE_INTENSITY_TRANSFORM_MATRIX);
    }


    @Override
    public Image applyOperator(Image image) {
        return doTransform(image);
    }
}