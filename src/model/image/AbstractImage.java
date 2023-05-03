package model.image;

/**
 * Abstract class for Image to get image width and height. Implements methods getImageWidth and
 * getImageHeight from Image interface.
 */
public abstract class AbstractImage implements Image {

  // Width of the image
  protected int width;
  // Height of the image
  protected int height;


  /**
   * Default constructor of the AbstractImage.
   */
  public AbstractImage() {
    //This constructor is for Abstract Image
  }

  @Override
  public int getImageWidth() {
    return this.width;
  }

  @Override
  public int getImageHeight() {
    return this.height;
  }

}