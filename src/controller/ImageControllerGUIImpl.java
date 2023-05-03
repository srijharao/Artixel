package controller;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import controller.util.Utility;
import exception.ValidationException;
import model.ImageModel;
import view.ImageViewGUI;

/**
 * A class that implements the ImageController interface and provides
 * functionality to control the image model and view.
 */
public class ImageControllerGUIImpl implements ImageController {
  //The ImageModel object that this controller interacts with.
  private final ImageModel model;
  //The ImageView object that this controller interacts with.
  private final ImageViewGUI view;
  //A Map object that contains the controller input commands and corresponding CInput objects.
  private final Map<String, CInput> controllerInput;
  private final String[] colorNames = new String[4];
  private String current;


  /**
   * Constructs an ImageControllerGUIImpl object with the given ImageModel and ImageViewGUI.
   *
   * @param model The ImageModel to control.
   * @param view  The ImageViewGUI to display the image.
   */
  public ImageControllerGUIImpl(ImageModel model, ImageViewGUI view) {
    this.model = model;
    this.view = view;

    // Initialize command map
    controllerInput = new HashMap<>();
    // loads an image 
    controllerInput.put("load", new LoadCommand());
    // applying sepia color transformation
    controllerInput.put("sepia", new SepiaCommand());
    // blurring an image
    controllerInput.put("blur", new BlurCommand());
    // sharpening an image
    controllerInput.put("sharpen", new SharpenCommand());
    // dithering an image
    controllerInput.put("dither", new DitherCommand());
    // converts an image to greyscale
    controllerInput.put("grayscale", new ConvertGreyAction());
    // flips an image horizontally
    controllerInput.put("horizontal-flip", new HorizontalFlipCommand());
    // flips an image vertically
    controllerInput.put("vertical-flip", new VerticalFlipCommand());
    // saves an image to a file
    controllerInput.put("save", new SaveCommand());
    // splits an image into RGB channels
    controllerInput.put("rgb-split", new RGBSplitAction());
    // combines RGB channels into an image
    controllerInput.put("rgb-combine", new RGBCombineAction());
    // brightens an image
    controllerInput.put("brighten", new BrightenCommandAction());
  }

  @Override
  public void process() {
    this.view.actionCallback(input -> {
      try {
        commandCallback(input);
      } catch (ValidationException e) {
        view.displayError(e.getMessage());
      }
    });
    this.view.displayGUI();
  }

  @Override
  public void processScript(Reader scriptReader) {
    // this method implementation is not required for this Assignment but can be used in
    // future if the GUI runs a script file
  }

  private void commandCallback(String input) throws ValidationException {
    try {
      executeCommand(input);
    } catch (Exception exception) {
      view.displayError(exception.getMessage());
    }
    view.displayImage(model.getImage(current));
    view.displayHist(model.getHist(current));
  }

  private void executeCommand(String line) throws ValidationException, IOException {
    String[] tokens = line.trim().split("\\s+");
    if (controllerInput.containsKey(tokens[0])) {
      CInput command = controllerInput.get(tokens[0]);
      command.run(model, view, tokens);
    }
  }

  /**
   * An interface that defines the functionality for executing an input command.
   */
  private interface CInput {
    /**
     * Runs the specified input command on the given model and view with the provided arguments.
     *
     * @param model The ImageModel on which to run the command.
     * @param view  The ImageView on which to display the image.
     * @param words The arguments for the command.
     * @throws ValidationException If the command or its arguments are invalid.
     * @throws IOException         If an error occurs while executing the command.
     */
    void run(ImageModel model, ImageViewGUI view, String[] words)
            throws IOException, ValidationException;
  }

  /**
   * An implementation of the CInput interface that performs the "load" operation,
   * which loads an image.
   */
  private class LoadCommand implements CInput {
    @Override
    public void run(ImageModel model, ImageViewGUI view, String[] words)
            throws IOException, ValidationException {
      StringBuilder combined = new StringBuilder();
      for (int i = 1; i < words.length; i++) {
        combined.append(words[i]);
        if (i < words.length - 1) {
          combined.append(" ");
        }
      }
      current = "image";
      ImageControllerGUIImpl.this.model.setImage(current,
              Utility.readImage(combined.toString()));
      ImageControllerGUIImpl.this.model.histogram(current, current);
    }
  }

  /**
   * The SepiaAction class implements the CInput interface and represents
   * an action to apply a Sepia filter to an image specified by the input arguments.
   */
  private class SepiaCommand implements CInput {
    @Override
    public void run(ImageModel model, ImageViewGUI view, String[] words)
            throws ValidationException {
      ImageControllerGUIImpl.this.model.colorTransformation(current,
              current + "-sepia", words[0]);
      current = current + "-sepia";
      ImageControllerGUIImpl.this.model.histogram(current, current);
    }
  }

  /**
   * An implementation of the CInput interface for the "blur" command that
   * performs the blurring operation on an image.
   */
  private class BlurCommand implements CInput {
    @Override
    public void run(ImageModel model, ImageViewGUI view, String[] words)
            throws ValidationException {
      ImageControllerGUIImpl.this.model.filterTransformation(current,
              current + "-blur", words[0]);
      current = current + "-blur";
      ImageControllerGUIImpl.this.model.histogram(current, current);
    }
  }

  /**
   * An implementation of the CInput interface that performs a sharpening operation on an image.
   */
  private class SharpenCommand implements CInput {
    @Override
    public void run(ImageModel model, ImageViewGUI view, String[] words)
            throws ValidationException {
      ImageControllerGUIImpl.this.model.filterTransformation(current,
              current + "-sharpen", words[0]);
      current = current + "-sharpen";
      ImageControllerGUIImpl.this.model.histogram(current, current);
    }
  }

  /**
   * A class that implements the CInput interface to perform dithering on an image.
   */
  private class DitherCommand implements CInput {
    @Override
    public void run(ImageModel model, ImageViewGUI view, String[] words)
            throws ValidationException {
      ImageControllerGUIImpl.this.model.filterTransformation(current,
              current + "-dither", words[0]);
      current = current + "-dither";
      ImageControllerGUIImpl.this.model.histogram(current, current);
    }
  }

  /**
   * Class implementing the CInput interface to run the horizontal flip action.
   */
  private class HorizontalFlipCommand implements CInput {
    @Override
    public void run(ImageModel model, ImageViewGUI view, String[] words)
            throws ValidationException {
      ImageControllerGUIImpl.this.model.hFlip(current, current + "-hflip");
      current = current + "-hflip";
      ImageControllerGUIImpl.this.model.histogram(current, current);
    }
  }

  /**
   * An implementation of the CInput interface that performs the "vertical-flip" command,
   * which flips the image vertically.
   */
  private class VerticalFlipCommand implements CInput {
    @Override
    public void run(ImageModel model, ImageViewGUI view, String[] words)
            throws ValidationException {
      ImageControllerGUIImpl.this.model.vFlip(current, current + "-vflip");
      current = current + "-vflip";
      ImageControllerGUIImpl.this.model.histogram(current, current);
    }
  }

  /**
   * An implementation of the CInput interface that
   * converts an image to grayscale.
   */
  private class ConvertGreyAction implements CInput {
    @Override
    public void run(ImageModel model, ImageViewGUI view, String[] words)
            throws ValidationException {
      ImageControllerGUIImpl.this.model.imgConvertGreyScale(words[1], current,
              current + "-" + words[1]);
      current = current + "-" + words[1];
      ImageControllerGUIImpl.this.model.histogram(current, current);
    }
  }

  /**
   * Class implementing the CInput interface to run the image RGB split action.
   */
  private class RGBSplitAction implements CInput {
    @Override
    public void run(ImageModel model, ImageViewGUI view, String[] words)
            throws ValidationException {
      ImageControllerGUIImpl.this.model.imgSplitRGB(current, current + "-red",
              current + "-green", current + "-blue");
      colorNames[0] = current + "-red";
      colorNames[1] = current + "-green";
      colorNames[2] = current + "-blue";
      colorNames[3] = words[1];
      current = current + words[1];
      ImageControllerGUIImpl.this.model.histogram(current, current);
    }
  }

  /**
   * An implementation of the CInput interface that combines
   * three RGB images into one.
   */
  private class RGBCombineAction implements CInput {
    @Override
    public void run(ImageModel model, ImageViewGUI view, String[] words)
            throws ValidationException {
      if (colorNames[3].equals("-red")) {
        ImageControllerGUIImpl.this.model.imgCombineRGB(current + "-combine", current,
                colorNames[1], colorNames[2]);
      } else if (colorNames[3].equals("-green")) {
        ImageControllerGUIImpl.this.model.imgCombineRGB(current + "-combine",
                colorNames[0], current, colorNames[2]);
      } else {
        ImageControllerGUIImpl.this.model.imgCombineRGB(current + "-combine",
                colorNames[0], colorNames[1], current);
      }
      current = current + "-combine";
      ImageControllerGUIImpl.this.model.histogram(current, current);
    }
  }

  /**
   * A class that implements the CInput interface and defines
   * the functionality for the "brighten" command.
   */
  private class BrightenCommandAction implements CInput {

    @Override
    public void run(ImageModel model, ImageViewGUI view, String[] words)
            throws ValidationException {
      ImageControllerGUIImpl.this.model.brighten(Integer.parseInt(words[1]), current,
              current + "-brighten");
      current = current + "-brighten";
      ImageControllerGUIImpl.this.model.histogram(current, current);
    }
  }

  /**
   * A class that implements the CInput interface and defines
   * the functionality for the "save"
   * command.
   */
  private class SaveCommand implements CInput {
    @Override
    public void run(ImageModel model, ImageViewGUI view, String[] words)
            throws ValidationException, IOException {
      StringBuilder combined = new StringBuilder();
      for (int i = 1; i < words.length; i++) {
        combined.append(words[i]);
        if (i < words.length - 1) {
          combined.append(" ");
        }
      }
      Utility.writeImage(ImageControllerGUIImpl.this.model.getImage(current), combined.toString());
    }
  }

}
