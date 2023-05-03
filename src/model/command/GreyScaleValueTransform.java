package model.command;

import model.image.Image;
import model.image.RgbImage;
import model.pixel.Pixel;
import model.pixel.PixelImpl;
import model.pixel.RgbColor;
import model.strategy.AbstractTransformStrategy;

import static model.util.Constants.GREYSCALE_IDENTITY_TRANSFORM_MATRIX;

/**
 * ValueTransform strategy class for GreyScale.
 * Transforms an image into greyscale image using value
 * component of the color image.
 * Extends AbstractTransformStrategy and implements applyOperator
 * method with color transformation.
 */
public class GreyScaleValueTransform extends AbstractTransformStrategy {

    public GreyScaleValueTransform() {
        super(GREYSCALE_IDENTITY_TRANSFORM_MATRIX);
    }

    @Override
    public Image applyOperator(Image image) {
        return doTransform(image);
    }

    @Override
    protected Image doTransform(Image image) {
        int height = image.getImageHeight();
        int width = image.getImageWidth();
        Pixel[][] newPixelArray = image.getPixelArray();
        for (int p = 0; p < height; p++) {
            for (int q = 0; q < width; q++) {
                int value = (int) newPixelArray[p][q].getValue();
                newPixelArray[p][q] = new PixelImpl(p, q, new RgbColor(value, value, value));
            }
        }
        return new RgbImage(newPixelArray);
    }
}
