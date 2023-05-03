package controller;

import java.io.IOException;
import java.io.Reader;

import exception.ValidationException;

/**
 * Controller interface that facilitates communication between model and view.
 */
public interface ImageController {

  /**
   * Begins the image controller, allowing it to receive input and execute commands.
   *
   * @throws IOException         If an error occurs while reading input.
   * @throws ValidationException If the input is invalid.
   */
  void process() throws IOException, ValidationException;

  /**
   * Processes a script from a given Reader, skipping any lines that start with "#" (comments).
   * The script is split into individual lines and processed one by one using
   * the ProcessHelper(String) method.
   *
   * @param imgInput a Reader object that provides access to the script to be processed.
   * @throws IOException         if an I/O error occurs while reading the script.
   * @throws ValidationException if the script fails validation.
   */
  void processScript(Reader imgInput) throws IOException, ValidationException;
}
