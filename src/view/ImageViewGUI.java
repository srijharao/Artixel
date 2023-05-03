package view;

import java.util.function.Consumer;

import model.image.Image;

/**
 * The ImageViewGUI interface specifies the methods that must be implemented by a
 * class that provides a graphical user interface for displaying images.
 */
public interface ImageViewGUI {

  /**
   * Displays the specified image in the user interface.
   *
   * @param image The image to be displayed.
   */
  void displayImage(Image image);

  /**
   * Displays the specified error message in the user interface.
   *
   * @param error The error message to be displayed.
   */
  void displayError(String error);

  /**
   * Makes the user interface visible to the user.
   */
  void displayGUI();

  /**
   * Sets the callback function to be invoked when a command is entered in the user interface.
   *
   * @param callback The callback function to be invoked.
   */
  void actionCallback(Consumer<String> callback);

  /**
   * Displays the histogram of the specified image in the user interface.
   *
   * @param image The image whose histogram is to be displayed.
   */
  void displayHist(int[][] image);

  /**
   * Prompts the user to enter a string input and returns the input.
   *
   * @param message The message to be displayed as a prompt for the user.
   * @return The string input entered by the user.
   */
  String getInput(String message);
}
