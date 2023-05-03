package model;

import java.util.HashMap;
import java.util.Map;

import exception.ValidationException;
import model.command.GreyScaleCommandImpl;
import model.command.IGreyScaleCommand;
import model.image.Image;
import model.image.RgbImage;
import model.pixel.Pixel;
import model.pixel.PixelImpl;
import model.pixel.RgbChannel;
import model.pixel.RgbColor;
import model.strategy.BlurFilter;
import model.strategy.DitherFilter;
import model.strategy.Operator;
import model.strategy.SepiaTransform;
import model.strategy.SharpenFilter;

/**
 * This class contains Utility methods to read a PPM image from file.
 */
public class ImageModelImpl implements ImageModel {

  private final Map<String, Image> imageMap;
  private final Map<String, int[][]> histMap;
  private IGreyScaleCommand greyScaleCommand;
  private Map<String, Operator> colorTransformCommandMap;
  private Map<String, Operator> filterTransformCommandMap;


  /**
   * Constructs an instance of the ImageModelImpl class.
   * Initializes the imageMap and the two command maps.
   */
  public ImageModelImpl() {
    super();
    this.imageMap = new HashMap<>();
    this.histMap = new HashMap<>();
    initializeCommandMaps();
  }

  private void initializeCommandMaps() {
    colorTransformCommandMap = new HashMap<>();
    filterTransformCommandMap = new HashMap<>();
    greyScaleCommand = new GreyScaleCommandImpl();
    colorTransformCommandMap.put("sepia", new SepiaTransform());
    filterTransformCommandMap.put("blur", new BlurFilter());
    filterTransformCommandMap.put("sharpen", new SharpenFilter());
    filterTransformCommandMap.put("dither", new DitherFilter());
  }

  @Override
  public Image getImage(String source) throws ValidationException {
    if (!imageMap.containsKey(source)) {
      throw new ValidationException("The image " + source + " does not exist");
    }
    return imageMap.get(source);
  }


  @Override
  public void brighten(int value, String source, String destination) throws ValidationException {
    fileExistHelper(source);
    Image image = imageMap.get(source);
    int height = image.getImageHeight();
    int width = image.getImageWidth();
    Pixel[][] newPixelArray = image.getPixelArray();
    for (int p = 0; p < height; p++) {
      for (int q = 0; q < width; q++) {
        newPixelArray[p][q].brighten(value);
      }
    }
    imageMap.put(destination, new RgbImage(newPixelArray));
  }

  @Override
  public void vFlip(String source, String destination) throws ValidationException {
    fileExistHelper(source);
    Image image = imageMap.get(source);
    int height = image.getImageHeight();
    Pixel[][] newPixelArray = image.getPixelArray();
    for (int i = 0; i < height / 2; i++) {
      Pixel[] tempRow = newPixelArray[i];
      newPixelArray[i] = newPixelArray[height - i - 1];
      newPixelArray[height - i - 1] = tempRow;
    }
    imageMap.put(destination, new RgbImage(newPixelArray));

  }


  @Override
  public void hFlip(String source, String destination) throws ValidationException {
    fileExistHelper(source);
    Image image = imageMap.get(source);
    int height = image.getImageHeight();
    int width = image.getImageWidth();
    Pixel[][] newPixelArray = image.getPixelArray();
    for (int p = 0; p < height; p++) {
      for (int q = 0; q < width / 2; q++) {
        Pixel tempPixel = newPixelArray[p][q];
        newPixelArray[p][q] = newPixelArray[p][width - q - 1];
        newPixelArray[p][width - q - 1] = tempPixel;
      }
    }
    imageMap.put(destination, new RgbImage(newPixelArray));
  }

  @Override
  public void imgConvertGreyScale(String component, String source, String destination)
          throws ValidationException {
    fileExistHelper(source);
    Image image = imageMap.get(source);
    imageMap.put(destination, greyScaleCommand.execute(image, component));
  }


  @Override
  public void imgSplitRGB(String source, String destinationRedImage, String destinationGreenImage,
                          String destinationBlueImage) throws ValidationException {
    fileExistHelper(source);
    Image image = imageMap.get(source);
    imageMap.put(destinationRedImage, greyScaleCommand.execute(image, "red-component"));
    imageMap.put(destinationGreenImage, greyScaleCommand.execute(image, "green-component"));
    imageMap.put(destinationBlueImage, greyScaleCommand.execute(image, "blue-component"));
  }

  @Override
  public void imgCombineRGB(String destination, String redSourceImage, String greenSourceImage,
                            String blueSourceImage) throws ValidationException {
    fileExistHelper(redSourceImage);
    fileExistHelper(greenSourceImage);
    fileExistHelper(blueSourceImage);

    Image redImage = imageMap.getOrDefault(redSourceImage, null);
    Image greenImage = imageMap.getOrDefault(greenSourceImage, null);
    Image blueImage = imageMap.getOrDefault(blueSourceImage, null);
    int height = redImage.getImageHeight();
    int width = redImage.getImageWidth();
    Pixel[][] newRedPixelArray = redImage.getPixelArray();
    Pixel[][] newGreenPixelArray = greenImage.getPixelArray();
    Pixel[][] newBluePixelArray = blueImage.getPixelArray();
    // Combine the red, green, and blue grayscale images into an RGB image
    for (int p = 0; p < height; p++) {
      for (int q = 0; q < width; q++) {
        newRedPixelArray[p][q] = new PixelImpl(p, q,
                new RgbColor(newRedPixelArray[p][q].getComponent(RgbChannel.RED.ordinal()),
                        newGreenPixelArray[p][q].getComponent(RgbChannel.GREEN.ordinal()),
                        newBluePixelArray[p][q].getComponent(RgbChannel.BLUE.ordinal())));
      }
    }
    imageMap.put(destination, new RgbImage(newRedPixelArray));

  }

  @Override
  public void filterTransformation(String source, String destination, String command)
          throws ValidationException {
    fileExistHelper(source);
    Image image = imageMap.get(source);
    if (command.equals("dither")) {
      imageMap.put(destination, filterTransformCommandMap.get("dither")
              .applyOperator(greyScaleCommand.execute(image, "luma-component")));
    } else {
      imageMap.put(destination, filterTransformCommandMap.get(command).applyOperator(image));
    }
  }

  @Override
  public void colorTransformation(String source, String destination, String command)
          throws ValidationException {
    fileExistHelper(source);
    Image image = imageMap.get(source);
    imageMap.put(destination, colorTransformCommandMap.get(command).applyOperator(image));
  }

  @Override
  public void grayscale(String source, String destination) throws ValidationException {
    fileExistHelper(source);
    imgConvertGreyScale("luma-component", source, destination);
    Image image = imageMap.get(destination);
    imageMap.put(destination, image);
  }

  @Override
  public void setImage(String alias, int[][][] image) {
    imageMap.put(alias, new RgbImage(image));
  }

  /**
   * This helper method will help to check whether the image exists in
   * the imageMap.
   *
   * @param source is the image name.
   * @throws ValidationException if the file does not exist in imageMap.
   */
  private void fileExistHelper(String source) throws ValidationException {
    if (!imageMap.containsKey(source)) {
      throw new ValidationException("The image " + source + " does not exist");
    }
  }

  @Override
  public void histogram(String source, String destination) throws ValidationException {
    fileExistHelper(source);
    Histogram hist = new Histogram(imageMap.get(source));
    histMap.put(destination, hist.histogram());
  }

  @Override
  public int[][] getHist(String source) throws ValidationException {
    if (!histMap.containsKey(source)) {
      throw new ValidationException("The histogram " + source + " does not exist");
    }
    return histMap.get(source);
  }

}

