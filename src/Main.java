import java.io.FileReader;
import java.io.IOException;

import controller.ImageController;
import controller.ImageControllerGUIImpl;
import controller.ImageControllerImpl;
import exception.ValidationException;
import model.ImageModel;
import model.ImageModelImpl;
import view.ImageView;
import view.ImageViewGUI;
import view.ImageViewGUIImpl;
import view.ImageViewImpl;

/**
 * This class represents the main entry point of the application.
 * It creates instances of the ImageModel, ImageView and ImageController classes to manage the
 * image processing and handle user inputs.
 */
public class Main {

  /**
   * The main method of the program.
   * It initializes the ImageModel, ImageView, and ImageController classes, and then starts the
   * application.
   *
   * @param args command line arguments
   * @throws ValidationException if there's an invalid command line argument passed
   */
  public static void main(String[] args) throws ValidationException, IOException {
    ImageModel model = new ImageModelImpl();
    ImageView view = new ImageViewImpl();
    ImageController controller = new ImageControllerImpl(model, view);
    if (args.length == 2 && args[0].equals("-file")) {
      String filePath = args[1];
      FileReader fileReader = new FileReader(filePath);
      controller.processScript(fileReader);
    } else if (args.length > 0 && args[0].equals("-text")) {
      controller.process();
    } else if (args.length == 0) {
      ImageViewGUI viewGUI = new ImageViewGUIImpl();
      ImageController controllerGUI = new ImageControllerGUIImpl(model, viewGUI);
      controllerGUI.process();
    } else {
      throw new ValidationException("Error: Invalid Command Line Argument");
      
    }
  }

}