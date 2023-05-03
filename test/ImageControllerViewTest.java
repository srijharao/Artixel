import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import controller.ImageController;
import controller.ImageControllerImpl;
import exception.ValidationException;
import model.ImageModel;
import model.ImageModelImpl;
import view.ImageView;
import view.MockImageView;

import static org.junit.Assert.assertEquals;


/**
 * This class contains JUnit tests for the Image Controller.
 * It sets up an environment to mock the view and tests the Image Controller functionality.
 */

public class ImageControllerViewTest {

  private final ByteArrayOutputStream outStreamContent = new ByteArrayOutputStream();
  private final PrintStream streamOut = System.out;

  @Before
  public void setUpStreams() {
    System.setOut(new PrintStream(outStreamContent));
  }

  @After
  public void restoreStreams() {
    System.setOut(streamOut);
  }

  @Test
  public void testViewStatusLoad() {
    try {
      String input = "load test/res/mat.ppm mat";
      System.setIn(new ByteArrayInputStream(input.getBytes()));

      // Set up model, mock view, and controller
      ImageModel model = new ImageModelImpl();
      ImageView mockView = new MockImageView();
      ImageController controller = new ImageControllerImpl(model, mockView);

      // Call controller's process method to execute "load" command
      controller.process();

      // Assert that view's status has been updated
      assertEquals("Operation performed\n", mockView.toString());
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testViewStatusBrighten() {
    try {
      String input = "load test/res/mat.ppm mat\nbrighten 10 mat mat-brighter";
      System.setIn(new ByteArrayInputStream(input.getBytes()));

      // Set up model, mock view, and controller
      ImageModel model = new ImageModelImpl();
      ImageView mockImageView = new MockImageView();
      ImageController controller = new ImageControllerImpl(model, mockImageView);

      // Call controller's process method to execute "load" command
      controller.process();
      assertEquals("Operation performed\n"
              + "Operation performed\n", mockImageView.toString());

    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }


  @Test
  public void testViewStatusHFlip() {
    try {
      String input = "load test/res/mat.ppm mat\nhorizontal-flip mat mat-horizontal";
      System.setIn(new ByteArrayInputStream(input.getBytes()));

      // Set up model, mock view, and controller
      ImageModel model = new ImageModelImpl();
      ImageView mockImageView = new MockImageView();
      ImageController controller = new ImageControllerImpl(model, mockImageView);

      // Call controller's process method to execute "load" command
      controller.process();
      assertEquals("Operation performed\nOperation performed\n", mockImageView.toString());
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testViewStatusVFlip() {
    try {
      String input = "load test/res/mat.ppm mat\nvertical-flip mat mat-vertical";
      System.setIn(new ByteArrayInputStream(input.getBytes()));

      // Set up model, mock view, and controller
      ImageModel model = new ImageModelImpl();
      ImageView mockImageView = new MockImageView();
      ImageController controller = new ImageControllerImpl(model, mockImageView);
      controller.process();

      // Call controller's process method to execute "load" command
      assertEquals("Operation performed\nOperation performed\n", mockImageView.toString());
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testViewStatusGreyscale() {
    try {
      String input = "load test/res/mat.ppm mat\ngreyscale luma-component mat mat-gray";
      System.setIn(new ByteArrayInputStream(input.getBytes()));

      // Set up model, mock view, and controller
      ImageModel model = new ImageModelImpl();
      ImageView mockImageView = new MockImageView();
      ImageController controller = new ImageControllerImpl(model, mockImageView);
      controller.process();

      // Call controller's process method to execute "load" command
      assertEquals("Operation performed\nOperation performed\n", mockImageView.toString());
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testViewStatusSplit() {
    try {
      String input = "load test/res/mat.ppm mat\nrgb-split mat mat-red mat-green mat-blue";
      System.setIn(new ByteArrayInputStream(input.getBytes()));
      ImageModel model = new ImageModelImpl();
      ImageView mockImageView = new MockImageView();
      ImageController controller = new ImageControllerImpl(model, mockImageView);
      controller.process();
      assertEquals("Operation performed\nOperation performed\n", mockImageView.toString());
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }


  @Test
  public void testViewStatusMockSepia() {
    try {
      String input = "load test/res/mat.ppm mat"
              + "\nsepia mat mat-sepia";
      System.setIn(new ByteArrayInputStream(input.getBytes()));
      ImageModel model = new ImageModelImpl();
      ImageView mockImageView = new MockImageView();
      ImageController controller = new ImageControllerImpl(model, mockImageView);
      controller.process();
      assertEquals("Operation performed" +
              "\nOperation performed\n", mockImageView.toString());
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testViewStatusMockBlur() {
    try {
      String input = "load test/res/mat.ppm mat" +
              "\nblur mat mat-blur";
      System.setIn(new ByteArrayInputStream(input.getBytes()));
      ImageModel model = new ImageModelImpl();
      ImageView mockImageView = new MockImageView();
      ImageController controller = new ImageControllerImpl(model, mockImageView);
      controller.process();
      assertEquals("Operation performed" +
              "\nOperation performed\n", mockImageView.toString());
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }


  @Test
  public void testViewStatusMockDither() {
    try {
      String input = "load test/res/mat.ppm mat"
              + "\ndither mat mat-dither";
      System.setIn(new ByteArrayInputStream(input.getBytes()));
      ImageModel model = new ImageModelImpl();
      ImageView mockImageView = new MockImageView();
      ImageController controller = new ImageControllerImpl(model, mockImageView);
      controller.process();
      assertEquals("Operation performed" +
              "\nOperation performed\n", mockImageView.toString());
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }


  @Test
  public void testViewStatusMockSharpen() {
    try {
      String input = "load test/res/mat.ppm mat"
              + "\nsharpen mat mat-sharpen";
      System.setIn(new ByteArrayInputStream(input.getBytes()));
      ImageModel model = new ImageModelImpl();
      ImageView mockImageView = new MockImageView();
      ImageController controller = new ImageControllerImpl(model, mockImageView);
      controller.process();
      assertEquals("Operation performed" +
              "\nOperation performed\n", mockImageView.toString());
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testViewStatusGrayscaleTransformation() {
    try {
      String input = "load test/res/mat.ppm mat"
              + "\ngreyscale mat mat-sharpen";
      System.setIn(new ByteArrayInputStream(input.getBytes()));
      ImageModel model = new ImageModelImpl();
      ImageView mockImageView = new MockImageView();
      ImageController controller = new ImageControllerImpl(model, mockImageView);
      controller.process();
      assertEquals("Operation performed" +
              "\nOperation performed\n", mockImageView.toString());
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testViewStatusCombine() {
    try {
      String input = "load test/res/mat.ppm mat"
              + "\nrgb-split mat mat-red mat-green mat-blue"
              + "\nrgb-combine mat-combine mat-red mat-green mat-blue";
      System.setIn(new ByteArrayInputStream(input.getBytes()));
      ImageModel model = new ImageModelImpl();
      ImageView mockImageView = new MockImageView();
      ImageController controller = new ImageControllerImpl(model, mockImageView);
      controller.process();
      assertEquals("Operation performed" +
              "\nOperation performed" +
              "\nOperation performed\n", mockImageView.toString());
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }


  @Test
  public void testViewStatusSave() {
    try {
      String input = "load test/res/mat.ppm mat\nsave test/res/mat-copy.ppm mat";
      System.setIn(new ByteArrayInputStream(input.getBytes()));
      ImageModel model = new ImageModelImpl();
      ImageView mockImageView = new MockImageView();
      ImageController controller = new ImageControllerImpl(model, mockImageView);
      controller.process();
      assertEquals("Operation performed\n"
              + "Operation performed\n", mockImageView.toString());
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testViewStatusRun() {
    try {
      String input = "run test/scripts/runScript.txt\n";
      System.setIn(new ByteArrayInputStream(input.getBytes()));
      ImageModel model = new ImageModelImpl();
      ImageView mockImageView = new MockImageView();
      ImageController controller = new ImageControllerImpl(model, mockImageView);
      controller.process();
      assertEquals("Operation performed\n" +
              "Operation performed\n" +
              "Operation performed\n" +
              "Operation performed\n" +
              "Operation performed\n" +
              "Operation performed\n" +
              "Operation performed\n" +
              "Operation performed\n" +
              "Operation performed\n" +
              "Operation performed\n" +
              "Operation performed\n" +
              "Operation performed\n" +
              "Operation performed\n" +
              "Operation performed\n", mockImageView.toString());
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

}

