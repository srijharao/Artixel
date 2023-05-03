package view;

/**
 * The ImageView interface defines a contract for classes that
 * implement image viewing functionality.
 */
public interface ImageView {
  /**
   * Displays the status of an image operation.
   *
   * @param image     the name of the image being operated on
   * @param operation the type of operation being performed on the image
   */
  void showStatus(String image, String operation);
}
