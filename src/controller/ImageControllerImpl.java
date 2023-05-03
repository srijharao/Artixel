package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import controller.util.Utility;
import exception.ValidationException;
import model.ImageModel;
import view.ImageView;

/**
 * A class that implements the ImageController interface and provides
 * functionality to control the image model and view.
 */
public class ImageControllerImpl implements ImageController {
  //The ImageModel object that this controller interacts with.
  private final ImageModel model;
  //The ImageView object that this controller interacts with.
  private final ImageView view;
  //A Map object that contains the controller input commands and corresponding CInput objects.
  private final Map<String, CInput> controllerInput;

  /**
   * Constructs an ImageControllerImpl object with the given ImageModel and ImageView.
   *
   * @param model The ImageModel to control.
   * @param view  The ImageView to display the image.
   */
  public ImageControllerImpl(ImageModel model, ImageView view) {
    this.model = model;
    this.view = view;

    // Create a new HashMap to store the controller inputs
    // Add each controller input to the HashMap with a keyword and its associated action
    this.controllerInput = new HashMap<>();

    // loads an image
    this.controllerInput.put("load", new LoadAction());
    // brightens an image
    this.controllerInput.put("brighten", new BrightAction());
    // converts an image to greyscale
    this.controllerInput.put("greyscale", new ImgConvertGreyAction());
    // flips an image horizontally
    this.controllerInput.put("horizontal-flip", new HorizontalFlipAction());
    // splits an image into RGB channels
    this.controllerInput.put("rgb-split", new ImgRGBSplitAction());
    // combines RGB channels into an image
    this.controllerInput.put("rgb-combine", new ImgRGBCombineAction());
    // flips an image vertically
    this.controllerInput.put("vertical-flip", new VerticalFlipAction());
    // saves an image to a file
    this.controllerInput.put("save", new SaveImageAction());
    // stops the program
    this.controllerInput.put("quit", new StopAction());
    // runs a script to execute multiple commands
    this.controllerInput.put("run", new ScriptAction());
    // blurring an image
    this.controllerInput.put("blur", new BlurAction());
    // sharpening an image
    this.controllerInput.put("sharpen", new SharpenAction());
    // applying sepia color transformation
    this.controllerInput.put("sepia", new SepiaAction());
    // dithering an image
    this.controllerInput.put("dither", new DitherAction());
  }

  @Override
  public void process() throws IllegalArgumentException {
    try (Scanner scanner = new Scanner(System.in)) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();

        // skip comment lines
        if (line.startsWith("#")) {
          continue;
        }
        processHelper(line);
      }
    }
  }

  @Override
  public void processScript(Reader scriptReader) throws IOException {
    try (BufferedReader reader = new BufferedReader(scriptReader)) {
      StringBuilder scriptBuilder = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {

        // skip comment lines
        if (line.startsWith("#")) {
          continue;
        }
        scriptBuilder.append(line).append("\n");
      }
      String script = scriptBuilder.toString();
      String[] lines = script.split("\\R+");
      for (String l : lines) {
        processHelper(l);
      }
    }
  }

  /**
   * Processes the given command line input by splitting it into tokens and validating the command.
   * If the command is valid, it will execute the corresponding controller input's run method.
   *
   * @param line the command line input to process
   */
  private void processHelper(String line) {
    String[] tokens = line.trim().split("\\s+");
    if (tokens.length < 1) {
      return;
    }
    String command = tokens[0];
    CInput input = controllerInput.get(command);
    if (input == null) {
      System.err.println("Invalid command: " + command);
      return;
    }
    try {
      input.run(model, view, tokens);
    } catch (ValidationException e) {
      System.err.println("Error in command: " + e.getMessage());
    } catch (IOException e) {
      System.err.println("Error running command: " + e.getMessage());
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
    void run(ImageModel model, ImageView view, String[] words)
            throws ValidationException, IOException;
  }

  /**
   * A static class that implements the CInput interface and defines the
   * functionality for the "quit"
   * command.
   */
  private static class StopAction implements CInput {
    @Override
    public void run(ImageModel model, ImageView view, String[] words) {
      System.exit(0);
    }
  }

  /**
   * A class that implements the CInput interface and defines
   * the functionality for the "brighten" command.
   */
  private class BrightAction implements CInput {
    @Override
    public void run(ImageModel model, ImageView view, String[] words)
            throws ValidationException {
      if (words.length < 4) {
        throw new ValidationException("Command Incomplete: Correct format is: brighten " +
                "[Value] [Image] [alias]");
      } else {
        try {
          // Validate that the value parameter is a valid integer
          int intValue = Integer.parseInt(words[1]);
          ImageControllerImpl.this.model.brighten(intValue, words[2], words[3]);
          ImageControllerImpl.this.view.showStatus(words[3], words[0]);
        } catch (NumberFormatException e) {
          throw new ValidationException("Invalid Integer value: " + words[1]);
        }
      }
    }
  }

  /**
   * A class that implements the CInput interface and defines
   * the functionality for the "save"
   * command.
   */
  private class SaveImageAction implements CInput {
    @Override
    public void run(ImageModel model, ImageView view, String[] words)
            throws ValidationException, IOException {
      if (words.length < 3) {
        throw new ValidationException("Command Incomplete: Correct format is: save " +
                "[image] [image alias]");
      } else {
        Utility.writeImage(ImageControllerImpl.this.model.getImage(words[2]), words[1]);
        ImageControllerImpl.this.view.showStatus(words[2], words[0]);
      }
    }
  }

  /**
   * Class implementing the CInput interface to run the horizontal flip action.
   */
  private class HorizontalFlipAction implements CInput {
    @Override
    public void run(ImageModel model, ImageView view, String[] words)
            throws ValidationException {
      if (words.length < 3) {
        throw new ValidationException("Command Incomplete: Correct format is: horizontal-flip " +
                "[image] [image alias]");
      } else {
        ImageControllerImpl.this.model.hFlip(words[1], words[2]);
        ImageControllerImpl.this.view.showStatus(words[2], words[0]);
      }
    }
  }

  /**
   * Class implementing the CInput interface to run the image RGB split action.
   */
  private class ImgRGBSplitAction implements CInput {
    @Override
    public void run(ImageModel model, ImageView view, String[] words)
            throws ValidationException {
      if (words.length < 5) {
        throw new ValidationException("Command Incomplete: Correct format is: rgb-split [image] " +
                "[alias red] [alias green] [alias blue]");
      }
      ImageControllerImpl.this.model.imgSplitRGB(words[1], words[2], words[3], words[4]);
      ImageControllerImpl.this.view.showStatus(words[1], words[0]);
    }
  }

  /**
   * An implementation of the CInput interface that combines
   * three RGB images into one.
   */
  private class ImgRGBCombineAction implements CInput {
    @Override
    public void run(ImageModel model, ImageView view, String[] words)
            throws ValidationException {
      if (words.length < 5) {
        throw new ValidationException("Command Incomplete: Correct format is: rgb-combine " +
                "[image alias] [image red] [image green] [image blue]");
      } else {
        ImageControllerImpl.this.model.imgCombineRGB(words[1], words[2], words[3], words[4]);
        ImageControllerImpl.this.view.showStatus(words[1], words[0]);
      }
    }
  }


  /**
   * An implementation of the CInput interface that
   * converts an image to greyscale.
   */
  private class ImgConvertGreyAction implements CInput {
    @Override
    public void run(ImageModel model, ImageView view, String[] words)
            throws ValidationException {
      if (words.length < 2) {
        throw new ValidationException("Command Incomplete: Correct format is: greyscale " +
                "[Optional: Component] [image] [image alias]");
      } else if (words.length == 3) {
        ImageControllerImpl.this.model.grayscale(words[1], words[2]);
        ImageControllerImpl.this.view.showStatus(words[1], words[0]);
      } else {
        ImageControllerImpl.this.model.imgConvertGreyScale(words[1], words[2], words[3]);
        ImageControllerImpl.this.view.showStatus(words[3], words[0]);
      }
    }
  }

  /**
   * An implementation of the CInput interface that reads
   * commands from a script file and executes them.
   */
  private class ScriptAction implements CInput {
    @Override
    public void run(ImageModel model, ImageView view, String[] words)
            throws ValidationException, IOException {
      Reader imgInput = new FileReader(words[1]);
      processScript(imgInput);
      ImageControllerImpl.this.view.showStatus(words[1], words[0]);
    }
  }

  /**
   * An implementation of the CInput interface that performs the "vertical-flip" command,
   * which flips the image vertically.
   */
  private class VerticalFlipAction implements CInput {
    @Override
    public void run(ImageModel model, ImageView view, String[] words)
            throws ValidationException {
      if (words.length < 3) {
        throw new ValidationException("Command Incomplete: Correct format is: vertical-flip " +
                "[image] [image alias]");
      } else {
        ImageControllerImpl.this.model.vFlip(words[1], words[2]);
        ImageControllerImpl.this.view.showStatus(words[2], words[0]);
      }
    }
  }

  /**
   * An implementation of the CInput interface for the "blur" command that
   * performs the blurring operation on an image.
   */
  private class BlurAction implements CInput {
    @Override
    public void run(ImageModel model, ImageView view, String[] words)
            throws IOException, ValidationException {
      if (words.length < 3) {
        throw new ValidationException("Command Incomplete: Correct format is: blur " +
                "[image] [image alias]");
      } else {
        ImageControllerImpl.this.model.filterTransformation(words[1], words[2], words[0]);
        ImageControllerImpl.this.view.showStatus(words[1], words[0]);
      }
    }
  }

  /**
   * An implementation of the CInput interface that performs a sharpening operation on an image.
   */
  private class SharpenAction implements CInput {
    @Override
    public void run(ImageModel model, ImageView view, String[] words)
            throws IOException, ValidationException {
      if (words.length < 3) {
        throw new ValidationException("Command Incomplete: Correct format is: sharpen " +
                "[image] [image alias]");
      } else {
        ImageControllerImpl.this.model.filterTransformation(words[1], words[2], words[0]);
        ImageControllerImpl.this.view.showStatus(words[1], words[0]);
      }
    }
  }

  /**
   * The SepiaAction class implements the CInput interface and represents
   * an action to apply a Sepia filter to an image specified by the input arguments.
   */
  private class SepiaAction implements CInput {
    @Override
    public void run(ImageModel model, ImageView view, String[] words)
            throws ValidationException {
      if (words.length < 3) {
        throw new ValidationException("Command Incomplete: Correct format is: sepia " +
                "[image] [image alias]");
      } else {
        ImageControllerImpl.this.model.colorTransformation(words[1], words[2], words[0]);
        ImageControllerImpl.this.view.showStatus(words[1], words[0]);
      }
    }
  }

  /**
   * A class that implements the CInput interface to perform dithering on an image.
   */
  private class DitherAction implements CInput {
    @Override
    public void run(ImageModel model, ImageView view, String[] words)
            throws ValidationException {
      if (words.length < 3) {
        throw new ValidationException("Command Incomplete: Correct format is: dither " +
                "[image] [image alias]");
      } else {
        ImageControllerImpl.this.model.filterTransformation(words[1], words[2], words[0]);
        ImageControllerImpl.this.view.showStatus(words[1], words[0]);
      }
    }
  }

  /**
   * An implementation of the CInput interface that performs the "load" command,
   * which loads an image.
   */
  private class LoadAction implements CInput {
    @Override
    public void run(ImageModel model, ImageView view, String[] words)
            throws ValidationException, IOException {
      if (words.length > 2) {
        ImageControllerImpl.this.model.setImage(words[2], Utility.readImage(words[1]));
        ImageControllerImpl.this.view.showStatus(words[2], words[0]);
      } else {
        throw new ValidationException("Command Incomplete: Correct format is: load " +
                "[filepath/filename] [alias]");
      }
    }
  }

}