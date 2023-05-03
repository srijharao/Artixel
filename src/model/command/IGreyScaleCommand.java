package model.command;

import exception.ValidationException;
import model.image.Image;

/**
 * Interface for GreyScale commands execution.
 */
public interface IGreyScaleCommand {

  Image execute(Image image, String component) throws ValidationException;
}
