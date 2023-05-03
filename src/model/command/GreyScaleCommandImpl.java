package model.command;

import java.util.HashMap;
import java.util.Map;

import exception.ValidationException;
import model.image.Image;
import model.image.RgbImage;
import model.pixel.Pixel;
import model.pixel.PixelImpl;
import model.pixel.RgbColor;
import model.strategy.AbstractTransformStrategy;
import model.strategy.Operator;

import static model.util.Constants.GREYSCALE_BLUE_TRANSFORM_MATRIX;
import static model.util.Constants.GREYSCALE_GREEN_TRANSFORM_MATRIX;
import static model.util.Constants.GREYSCALE_IDENTITY_TRANSFORM_MATRIX;
import static model.util.Constants.GREYSCALE_INTENSITY_TRANSFORM_MATRIX;
import static model.util.Constants.GREYSCALE_LUMA_TRANSFORM_MATRIX;
import static model.util.Constants.GREYSCALE_RED_TRANSFORM_MATRIX;

/**
 * Implementation of IGreyScaleCommand. Initializes command map for GreyScale and executes
 * respective transformation by applying operation on the image.
 */
public class GreyScaleCommandImpl implements IGreyScaleCommand {

  private final Map<String, Operator> greyScaleCommandMap;

  /**
   * This class represents an implementation of the IGreyScaleCommand interface,
   * which contains methods for converting an image to grayscale.
   * It maintains a map of grayscale transformation objects with their names as keys.
   */
  public GreyScaleCommandImpl() {
    greyScaleCommandMap = new HashMap<>();
    greyScaleCommandMap.put("red-component", new GreyScaleRedTransform());
    greyScaleCommandMap.put("green-component", new GreyScaleGreenTransform());
    greyScaleCommandMap.put("blue-component", new GreyScaleBlueTransform());
    greyScaleCommandMap.put("luma-component", new GreyScaleLumaTransform());
    greyScaleCommandMap.put("intensity-component", new GreyScaleIntensityTransform());
    greyScaleCommandMap.put("value-component", new GreyScaleValueTransform());
  }

  @Override
  public Image execute(Image image, String component) throws ValidationException {
    if (greyScaleCommandMap.containsKey(component)) {
      return greyScaleCommandMap.get(component).applyOperator(image);
    } else {
      throw new ValidationException("Invalid component");
    }
  }

  /**
   * BlueTransform strategy class for GreyScale. Transforms an image into greyscale image using blue
   * component of the color image. Extends AbstractTransformStrategy and implements applyOperator
   * method with color transformation.
   */
  public static class GreyScaleBlueTransform extends AbstractTransformStrategy {

    public GreyScaleBlueTransform() {
      super(GREYSCALE_BLUE_TRANSFORM_MATRIX);
    }


    @Override
    public Image applyOperator(Image image) {
      return doTransform(image);
    }
  }

  /**
   * GreenTransform strategy class for GreyScale.
   * Transforms an image into greyscale image using green
   * component of the color image.
   * Extends AbstractTransformStrategy and implements applyOperator
   * method with color transformation.
   */
  public static class GreyScaleGreenTransform extends AbstractTransformStrategy {

    public GreyScaleGreenTransform() {
      super(GREYSCALE_GREEN_TRANSFORM_MATRIX);
    }


    @Override
    public Image applyOperator(Image image) {
      return doTransform(image);
    }
  }

  /**
   * IntensityTransform strategy class for GreyScale.
   * Transforms an image into greyscale image using
   * intensity component of the color image.
   * Extends AbstractTransformStrategy and implements
   * applyOperator method with color transformation.
   */
  public static class GreyScaleIntensityTransform extends AbstractTransformStrategy {

    public GreyScaleIntensityTransform() {
      super(GREYSCALE_INTENSITY_TRANSFORM_MATRIX);
    }


    @Override
    public Image applyOperator(Image image) {
      return doTransform(image);
    }
  }

  /**
   * LumaTransform strategy class for GreyScale.
   * Transforms an image into greyscale image using Luma
   * of the color image.
   * Extends AbstractTransformStrategy and implements applyOperator method with
   * color transformation.
   */
  public static class GreyScaleLumaTransform extends AbstractTransformStrategy {

    public GreyScaleLumaTransform() {
      super(GREYSCALE_LUMA_TRANSFORM_MATRIX);
    }

    @Override
    public Image applyOperator(Image image) {
      return doTransform(image);
    }
  }


  /**
   * RedTransform strategy class for GreyScale.
   * Transforms an image into greyscale image using red
   * component of the color image.
   * Extends AbstractTransformStrategy and implements applyOperator
   * method with color transformation.
   */
  public static class GreyScaleRedTransform extends AbstractTransformStrategy {

    public GreyScaleRedTransform() {
      super(GREYSCALE_RED_TRANSFORM_MATRIX);
    }

    @Override
    public Image applyOperator(Image image) {
      return doTransform(image);
    }
  }

  /**
   * ValueTransform strategy class for GreyScale.
   * Transforms an image into greyscale image using value
   * component of the color image.
   * Extends AbstractTransformStrategy and implements applyOperator
   * method with color transformation.
   */
  public static class GreyScaleValueTransform extends AbstractTransformStrategy {

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
}
