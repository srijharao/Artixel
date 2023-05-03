package model;

import model.image.Image;

/**
 * The MockImageModel class provides a mock implementation of the
 * ImageModel interface for testing purposes.
 */
public class MockImageModel implements ImageModel {

  private final StringBuilder modelOPLog;

  public MockImageModel() {
    this.modelOPLog = new StringBuilder();
  }

  @Override
  public void setImage(String alias, int[][][] imgData) {
    //This is to load the image file and would not return
  }

  @Override
  public void brighten(int value, String fileName, String newFilename) {
    modelOPLog.append(fileName).append(" Brightened as ").append(newFilename).append(" ");
  }

  @Override
  public void vFlip(String fileName, String newFilename) {
    modelOPLog.append(fileName).append(" Vertically Flipped as ").append(newFilename).append(" ");
  }

  @Override
  public void hFlip(String fileName, String newFilename) {
    modelOPLog.append(fileName).append(" Horizontally Flipped as ").append(newFilename).append(" ");
  }

  @Override
  public void imgSplitRGB(String fileName, String redImagee,
                          String greenImagee, String blueImagee) {
    modelOPLog.append(fileName).append(" RGB Split successful ");

  }

  @Override
  public void imgCombineRGB(String newFilename, String redImage, String greenImage,
                            String blueImage) {
    modelOPLog.append("RGB combined as ").append(newFilename).append(" ");
  }

  @Override
  public void imgConvertGreyScale(String component, String fileName, String newFilename) {
    modelOPLog.append(fileName).append(" grayscale successful as ").append(newFilename).append(" ");
  }

  @Override
  public Image getImage(String filename) {
    modelOPLog.append(filename).append(" saved");
    return null;
  }

  @Override
  public void grayscale(String filePath, String fileName) {
    modelOPLog.append(fileName).append(" grayscale successful as ").append(filePath).append(" ");
  }

  @Override
  public void histogram(String source, String destination) {
    modelOPLog.append("Histogram was created for " + source + "and saved as " + destination);
  }

  @Override
  public int[][] getHist(String source) {
    return new int[0][];
  }

  @Override
  public void filterTransformation(String source, String destination, String command) {
    modelOPLog.append(source).append(" " + command + " successful as ")
            .append(destination).append(" ");
  }

  @Override
  public void colorTransformation(String source, String destination, String command) {
    modelOPLog.append(source).append(" " + command + " successful as ")
            .append(destination).append(" ");
  }

  @Override
  public String toString() {

    return "" + modelOPLog;
  }
}
