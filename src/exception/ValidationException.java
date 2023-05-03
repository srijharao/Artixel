package exception;

/**
 * This class represents the Validation Exception that should be
 * thrown for invalid input.
 */

public class ValidationException extends Exception {

  /**
   * Constructor for error exception instantiation.
   *
   * @param errorMessage message for exception.
   */
  public ValidationException(String errorMessage) {
    super(errorMessage);
  }

}
