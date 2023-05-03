package view;

/**
 * The ImageViewImpl class provides an implementation of the ImageView interface.
 */
public class ImageViewImpl implements ImageView {
  /**
   * Displays the status of an image operation.
   *
   * @param image     the name of the image being operated on
   * @param operation the type of operation being performed on the image
   */
  @Override
  public void showStatus(String image, String operation) {
    switch (operation) {
      case "load":
        System.out.println("Image " + image + " Loaded Successfully");
        break;
      case "brighten":
        System.out.println("Image " + image + " Brightend Successfully");
        break;
      case "horizontal-flip":
      case "vertical-flip":
        System.out.println("Image " + image + " flipped Successfully");
        break;
      case "rgb-split":
        System.out.println("Image " + image + " split Successfully");
        break;
      case "rgb-combine":
        System.out.println("Image " + image + " combined Successfully");
        break;
      case "greyscale":
        System.out.println("greyscale " + image + " created Successfully");
        break;
      case "save":
        System.out.println("Image " + image + " saved Successfully");
        break;
      case "run":
        System.out.println("Script " + image + " ran Successfully");
        break;
      case "blur":
        System.out.println("Image " + image + " blurred Successfully");
        break;
      case "sharpen":
        System.out.println("Image " + image + " sharpened Successfully");
        break;
      case "gray":
        System.out.println("Image " + image + " grayed Successfully");
        break;
      case "sepia":
        System.out.println("Image " + image + " sepia Successfully");
        break;
      case "dither":
        System.out.println("Image " + image + " dither Successfully");
        break;
      default: System.out.println("unexpected output");
    }
  }

}
