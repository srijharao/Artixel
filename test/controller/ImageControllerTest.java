package controller;


import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import exception.ValidationException;
import model.ImageModel;
import model.MockImageModel;
import view.ImageView;
import view.ImageViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * Junit tests for testing Controller specific actions and exceptions.
 */
public class ImageControllerTest {

  @Test
  public void exceptionUnknownCommand() {
    try {
      ImageModel mockImageModel = new MockImageModel();
      String input1 = "hello";
      System.setIn(new ByteArrayInputStream(input1.getBytes()));
      ImageView view = new ImageViewImpl();
      ImageController test = new ImageControllerImpl(mockImageModel, view);
      //test.process();
      ByteArrayOutputStream outContent = new ByteArrayOutputStream();
      PrintStream originalErr = System.err;
      System.setErr(new PrintStream(outContent));
      test.process();
      System.setErr(originalErr);
      String capturedOutput = outContent.toString().trim();
      assertEquals("Invalid command: hello", capturedOutput);
    } catch (ValidationException e) {
      assertEquals("exception.ValidationException: Invalid command: hello", e.toString());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void exceptionLoadInvalid() {
    try {
      String input1 = "load1 res";
      System.setIn(new ByteArrayInputStream(input1.getBytes()));
      ImageModel mockImageModel = new MockImageModel();
      ImageView view = new ImageViewImpl();
      ImageController test = new ImageControllerImpl(mockImageModel, view);
      ByteArrayOutputStream outContent = new ByteArrayOutputStream();
      PrintStream originalErr = System.err;
      System.setErr(new PrintStream(outContent));
      test.process();
      System.setErr(originalErr);
      String capturedOutput = outContent.toString().trim();
      assertEquals("Invalid command: load1", capturedOutput);
    } catch (ValidationException e) {
      assertEquals("exception.ValidationException: Invalid command: load1", e.toString());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void exceptionBrightenInvalid() {
    try {
      String input1 = "brgihten 73 mat";
      System.setIn(new ByteArrayInputStream(input1.getBytes()));
      ImageModel mockImageModel = new MockImageModel();
      ImageView view = new ImageViewImpl();
      ImageController test = new ImageControllerImpl(mockImageModel, view);
      ByteArrayOutputStream outContent = new ByteArrayOutputStream();
      PrintStream originalErr = System.err;
      System.setErr(new PrintStream(outContent));
      test.process();
      System.setErr(originalErr);
      String capturedOutput = outContent.toString().trim();
      assertEquals("Invalid command: brgihten", capturedOutput);
    } catch (ValidationException e) {
      assertEquals("exception.ValidationException: " +
              "Invalid command: brgihten", e.toString());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void exceptionSaveInvalid() {
    try {
      String input1 = "write res";
      System.setIn(new ByteArrayInputStream(input1.getBytes()));
      ImageModel mockImageModel = new MockImageModel();
      ImageView view = new ImageViewImpl();
      ImageController test = new ImageControllerImpl(mockImageModel, view);
      ByteArrayOutputStream outContent = new ByteArrayOutputStream();
      PrintStream originalErr = System.err;
      System.setErr(new PrintStream(outContent));
      test.process();
      System.setErr(originalErr);
      String capturedOutput = outContent.toString().trim();
      assertEquals("Invalid command: write", capturedOutput);
    } catch (ValidationException e) {
      assertEquals("exception.ValidationException: Invalid command: write", e.toString());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void exceptionVFlipInvalid() {
    try {
      String input1 = "vflip mat ";
      System.setIn(new ByteArrayInputStream(input1.getBytes()));
      ImageModel mockImageModel = new MockImageModel();
      ImageView view = new ImageViewImpl();
      ImageController test = new ImageControllerImpl(mockImageModel, view);
      ByteArrayOutputStream outContent = new ByteArrayOutputStream();
      PrintStream originalErr = System.err;
      System.setErr(new PrintStream(outContent));
      test.process();
      System.setErr(originalErr);
      String capturedOutput = outContent.toString().trim();
      assertEquals("Invalid command: vflip", capturedOutput);
    } catch (ValidationException e) {
      assertEquals("exception.ValidationException: Invalid command: vflip", e.toString());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void exceptionGreyScaleInvalid() {
    try {
      String input1 = "greyscaling value-component1 mat";
      System.setIn(new ByteArrayInputStream(input1.getBytes()));
      ImageModel mockImageModel = new MockImageModel();
      ImageView view = new ImageViewImpl();
      ImageController test = new ImageControllerImpl(mockImageModel, view);
      ByteArrayOutputStream outContent = new ByteArrayOutputStream();
      PrintStream originalErr = System.err;
      System.setErr(new PrintStream(outContent));
      test.process();
      System.setErr(originalErr);
      String capturedOutput = outContent.toString().trim();
      assertEquals("Invalid command: greyscaling", capturedOutput);
    } catch (ValidationException e) {
      assertEquals("exception.ValidationException: Invalid command: " +
              "greyscaling", e.toString());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void exceptionRGBSplitInvalid() {
    try {
      String input1 = "split-rgb mat mat-red mat-green";
      System.setIn(new ByteArrayInputStream(input1.getBytes()));
      ImageModel mockImageModel = new MockImageModel();
      ImageView view = new ImageViewImpl();
      ImageController test = new ImageControllerImpl(mockImageModel, view);
      ByteArrayOutputStream outContent = new ByteArrayOutputStream();
      PrintStream originalErr = System.err;
      System.setErr(new PrintStream(outContent));
      test.process();
      System.setErr(originalErr);
      String capturedOutput = outContent.toString().trim();
      assertEquals("Invalid command: split-rgb", capturedOutput);
    } catch (ValidationException e) {
      assertEquals("exception.ValidationException: Invalid command: " +
              "split-rgb", e.toString());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void exceptionRunInvalid() {
    try {
      String input1 = "runthis res/scripts/runScript.txt";
      System.setIn(new ByteArrayInputStream(input1.getBytes()));
      ImageModel mockImageModel = new MockImageModel();
      ImageView view = new ImageViewImpl();
      ImageController test = new ImageControllerImpl(mockImageModel, view);
      ByteArrayOutputStream outContent = new ByteArrayOutputStream();
      PrintStream originalErr = System.err;
      System.setErr(new PrintStream(outContent));
      test.process();
      System.setErr(originalErr);
      String capturedOutput = outContent.toString().trim();
      assertEquals("Invalid command: runthis", capturedOutput);
    } catch (ValidationException e) {
      assertEquals("exception.ValidationException: Invalid command: " +
              "runthis", e.toString());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void exceptionRGBCombineInvalid() {
    try {
      String input1 = "combine-rgb mat-combine mat-red mat-green mat-blue";
      System.setIn(new ByteArrayInputStream(input1.getBytes()));
      ImageModel mockImageModel = new MockImageModel();
      ImageView view = new ImageViewImpl();
      ImageController test = new ImageControllerImpl(mockImageModel, view);
      ByteArrayOutputStream outContent = new ByteArrayOutputStream();
      PrintStream originalErr = System.err;
      System.setErr(new PrintStream(outContent));
      test.process();
      System.setErr(originalErr);
      String capturedOutput = outContent.toString().trim();
      assertEquals("Invalid command: combine-rgb", capturedOutput);
    } catch (ValidationException e) {
      assertEquals("exception.ValidationException: Invalid command: " +
              "combine-rgb", e.toString());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void exceptionSepiaInvalid() {
    try {
      String input1 = "sepiaImage mat ";
      System.setIn(new ByteArrayInputStream(input1.getBytes()));

      ImageModel mockImageModel = new MockImageModel();
      ImageView view = new ImageViewImpl();
      ImageController test = new ImageControllerImpl(mockImageModel, view);

      ByteArrayOutputStream outContent = new ByteArrayOutputStream();
      PrintStream originalErr = System.err;
      System.setErr(new PrintStream(outContent));
      test.process();
      System.setErr(originalErr);
      String capturedOutput = outContent.toString().trim();
      assertEquals("Invalid command: sepiaImage", capturedOutput);
    } catch (ValidationException e) {
      assertEquals("exception.ValidationException: Invalid command: " +
              "sepiaImage", e.toString());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void exceptionDitherInvalid() {
    try {
      String input1 = "ditherImage mat ";
      System.setIn(new ByteArrayInputStream(input1.getBytes()));

      ImageModel mockImageModel = new MockImageModel();
      ImageView view = new ImageViewImpl();
      ImageController test = new ImageControllerImpl(mockImageModel, view);

      ByteArrayOutputStream outContent = new ByteArrayOutputStream();
      PrintStream originalErr = System.err;
      System.setErr(new PrintStream(outContent));
      test.process();
      System.setErr(originalErr);
      String capturedOutput = outContent.toString().trim();
      assertEquals("Invalid command: ditherImage", capturedOutput);
    } catch (ValidationException e) {
      assertEquals("exception.ValidationException: Invalid command: " +
              "ditherImage", e.toString());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void exceptionBlurInvalid() {
    try {
      String input1 = "blurImage mat ";
      System.setIn(new ByteArrayInputStream(input1.getBytes()));

      ImageModel mockImageModel = new MockImageModel();
      ImageView view = new ImageViewImpl();
      ImageController test = new ImageControllerImpl(mockImageModel, view);

      ByteArrayOutputStream outContent = new ByteArrayOutputStream();
      PrintStream originalErr = System.err;
      System.setErr(new PrintStream(outContent));
      test.process();
      System.setErr(originalErr);
      String capturedOutput = outContent.toString().trim();
      assertEquals("Invalid command: blurImage", capturedOutput);
    } catch (ValidationException e) {
      assertEquals("exception.ValidationException: Invalid command: " +
              "blurImage", e.toString());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void exceptionSharpenInvalid() {
    try {
      String input1 = "Sharpen mat ";
      System.setIn(new ByteArrayInputStream(input1.getBytes()));
      ImageModel mockImageModel = new MockImageModel();
      ImageView view = new ImageViewImpl();
      ImageController test = new ImageControllerImpl(mockImageModel, view);
      ByteArrayOutputStream outContent = new ByteArrayOutputStream();
      PrintStream originalErr = System.err;
      System.setErr(new PrintStream(outContent));
      test.process();
      System.setErr(originalErr);
      String capturedOutput = outContent.toString().trim();
      assertEquals("Invalid command: Sharpen", capturedOutput);
    } catch (ValidationException e) {
      assertEquals("exception.ValidationException: Invalid command: " +
              "Sharpen", e.toString());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void exceptionHFlipInvalid() {
    try {
      String input1 = "hflip mat ";
      System.setIn(new ByteArrayInputStream(input1.getBytes()));
      ImageModel mockImageModel = new MockImageModel();
      ImageView view = new ImageViewImpl();
      ImageController test = new ImageControllerImpl(mockImageModel, view);
      ByteArrayOutputStream outContent = new ByteArrayOutputStream();
      PrintStream originalErr = System.err;
      System.setErr(new PrintStream(outContent));
      test.process();
      System.setErr(originalErr);
      String capturedOutput = outContent.toString().trim();
      assertEquals("Invalid command: hflip", capturedOutput);
    } catch (ValidationException e) {
      assertEquals("exception.ValidationException: Invalid command: " +
              "hflip", e.toString());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


  @Test
  public void exceptionVFlipInvalidCase2() throws ValidationException, IOException {
    try {
      String input1 = "vertical-flip mat";
      System.setIn(new ByteArrayInputStream(input1.getBytes()));
      ImageModel mockImageModel = new MockImageModel();
      ImageView view = new ImageViewImpl();
      ImageController test = new ImageControllerImpl(mockImageModel, view);
      ByteArrayOutputStream outContent = new ByteArrayOutputStream();
      PrintStream originalErr = System.err;
      System.setErr(new PrintStream(outContent));
      test.process();
      System.setErr(originalErr);
      String capturedOutput = outContent.toString().trim();
      assertEquals("Error in command: Command Incomplete: Correct format is: " +
              "vertical-flip [image] [image alias]", capturedOutput);
    } catch (ArrayIndexOutOfBoundsException e) {
      assertEquals("java.lang.ArrayIndexOutOfBoundsException: " +
              "Index 2 out of bounds for length 2", e.toString());
    }
  }


  @Test
  public void exceptionHFlipInvalidCase2() throws ValidationException, IOException {
    try {
      String input1 = "horizontal-flip mat";
      System.setIn(new ByteArrayInputStream(input1.getBytes()));
      ImageModel mockImageModel = new MockImageModel();
      ImageView view = new ImageViewImpl();
      ImageController test = new ImageControllerImpl(mockImageModel, view);
      ByteArrayOutputStream outContent = new ByteArrayOutputStream();
      PrintStream originalErr = System.err;
      System.setErr(new PrintStream(outContent));
      test.process();
      System.setErr(originalErr);
      String capturedOutput = outContent.toString().trim();
      assertEquals("Error in command: Command Incomplete: " +
              "Correct format is: horizontal-flip [image] [image alias]", capturedOutput);
    } catch (ArrayIndexOutOfBoundsException e) {
      assertEquals("java.lang.ArrayIndexOutOfBoundsException: " +
              "Index 2 out of bounds for length 2", e.toString());
    }
  }

  @Test
  public void exceptionGreyScaleInvalidCase2() throws ValidationException, IOException {
    try {
      String input1 = "greyscale value-component1";
      System.setIn(new ByteArrayInputStream(input1.getBytes()));
      ImageModel mockImageModel = new MockImageModel();
      ImageView view = new ImageViewImpl();
      ImageController test = new ImageControllerImpl(mockImageModel, view);
      ByteArrayOutputStream outContent = new ByteArrayOutputStream();
      PrintStream originalErr = System.err;
      System.setErr(new PrintStream(outContent));
      test.process();
      System.setErr(originalErr);
      String capturedOutput = outContent.toString().trim();
      assertEquals("Invalid command: load1", capturedOutput);
    } catch (ArrayIndexOutOfBoundsException e) {
      assertEquals("java.lang.ArrayIndexOutOfBoundsException: " +
              "Index 2 out of bounds for length 2", e.toString());
    }
  }

  @Test
  public void exceptionRGBSplitInvalidCase2() throws ValidationException, IOException {
    try {
      String input1 = "rgb-split mat mat-red mat-green";
      System.setIn(new ByteArrayInputStream(input1.getBytes()));
      ImageModel mockImageModel = new MockImageModel();
      ImageView view = new ImageViewImpl();
      ImageController test = new ImageControllerImpl(mockImageModel, view);
      ByteArrayOutputStream outContent = new ByteArrayOutputStream();
      PrintStream originalErr = System.err;
      System.setErr(new PrintStream(outContent));
      test.process();
      System.setErr(originalErr);
      String capturedOutput = outContent.toString().trim();
      assertEquals("Error in command: Command Incomplete: " +
              "Correct format is: rgb-split [image] [alias red] [alias green]" +
              " [alias blue]", capturedOutput);
    } catch (ArrayIndexOutOfBoundsException e) {
      assertEquals("java.lang.ArrayIndexOutOfBoundsException: " +
              "Index 4 out of bounds for length 4", e.toString());
    }
  }

  @Test
  public void exceptionRGBCombineInvalidCase2() throws ValidationException, IOException {
    try {
      String input1 = "rgb-split mat mat-red mat-green";
      System.setIn(new ByteArrayInputStream(input1.getBytes()));
      ImageModel mockImageModel = new MockImageModel();
      ImageView view = new ImageViewImpl();
      ImageController test = new ImageControllerImpl(mockImageModel, view);
      ByteArrayOutputStream outContent = new ByteArrayOutputStream();
      PrintStream originalErr = System.err;
      System.setErr(new PrintStream(outContent));
      test.process();
      System.setErr(originalErr);
      String capturedOutput = outContent.toString().trim();
      assertEquals("Error in command: Command Incomplete: " +
              "Correct format is: rgb-split [image] [alias red] [alias green] " +
              "[alias blue]", capturedOutput);
    } catch (ArrayIndexOutOfBoundsException e) {
      assertEquals("java.lang.ArrayIndexOutOfBoundsException: " +
              "Index 4 out of bounds for length 4", e.toString());
    }
  }

  @Test
  public void exceptionDitherInvalidCase2() throws ValidationException, IOException {
    try {
      String input1 = "dither mat";
      System.setIn(new ByteArrayInputStream(input1.getBytes()));
      ImageModel mockImageModel = new MockImageModel();
      ImageView view = new ImageViewImpl();
      ImageController test = new ImageControllerImpl(mockImageModel, view);
      ByteArrayOutputStream outContent = new ByteArrayOutputStream();
      PrintStream originalErr = System.err;
      System.setErr(new PrintStream(outContent));
      test.process();
      System.setErr(originalErr);
      String capturedOutput = outContent.toString().trim();
      assertEquals("Error in command: Command Incomplete: " +
              "Correct format is: dither [image] [image alias]", capturedOutput);
    } catch (ArrayIndexOutOfBoundsException e) {
      assertEquals("java.lang.ArrayIndexOutOfBoundsException: " +
              "Index 2 out of bounds for length 2", e.toString());
    }
  }

  @Test
  public void exceptionBlurInvalidCase2() throws ValidationException, IOException {
    try {
      String input1 = "blur mat";
      System.setIn(new ByteArrayInputStream(input1.getBytes()));
      ImageModel mockImageModel = new MockImageModel();
      ImageView view = new ImageViewImpl();
      ImageController test = new ImageControllerImpl(mockImageModel, view);
      ByteArrayOutputStream outContent = new ByteArrayOutputStream();
      PrintStream originalErr = System.err;
      System.setErr(new PrintStream(outContent));
      test.process();
      System.setErr(originalErr);
      String capturedOutput = outContent.toString().trim();
      assertEquals("Error in command: Command Incomplete: " +
              "Correct format is: blur [image] [image alias]", capturedOutput);
    } catch (ArrayIndexOutOfBoundsException e) {
      assertEquals("java.lang.ArrayIndexOutOfBoundsException: " +
              "Index 2 out of bounds for length 2", e.toString());
    }
  }

  @Test
  public void exceptionSharpenInvalidCase2() throws ValidationException, IOException {
    try {
      String input1 = "sharpen mat";
      System.setIn(new ByteArrayInputStream(input1.getBytes()));
      ImageModel mockImageModel = new MockImageModel();
      ImageView view = new ImageViewImpl();
      ImageController test = new ImageControllerImpl(mockImageModel, view);
      ByteArrayOutputStream outContent = new ByteArrayOutputStream();
      PrintStream originalErr = System.err;
      System.setErr(new PrintStream(outContent));
      test.process();
      System.setErr(originalErr);
      String capturedOutput = outContent.toString().trim();
      assertEquals("Error in command: Command Incomplete: Correct format is: " +
              "sharpen [image] [image alias]", capturedOutput);
    } catch (ArrayIndexOutOfBoundsException e) {
      assertEquals("java.lang.ArrayIndexOutOfBoundsException: " +
              "Index 2 out of bounds for length 2", e.toString());
    }
  }

  @Test
  public void exceptionSepiaInvalidCase2() throws ValidationException, IOException {
    try {
      String input1 = "sepia mat";
      System.setIn(new ByteArrayInputStream(input1.getBytes()));
      ImageModel mockImageModel = new MockImageModel();
      ImageView view = new ImageViewImpl();
      ImageController test = new ImageControllerImpl(mockImageModel, view);
      ByteArrayOutputStream outContent = new ByteArrayOutputStream();
      PrintStream originalErr = System.err;
      System.setErr(new PrintStream(outContent));
      test.process();
      System.setErr(originalErr);
      String capturedOutput = outContent.toString().trim();
      assertEquals("Error in command: Command Incomplete: Correct format is: " +
              "sepia [image] [image alias]", capturedOutput);
    } catch (ArrayIndexOutOfBoundsException e) {
      assertEquals("java.lang.ArrayIndexOutOfBoundsException: " +
              "Index 2 out of bounds for length 2", e.toString());
    }
  }

  @Test
  public void exceptionGreyscaleInvalid() {
    try {
      String input1 = "greyscaleImage mat ";
      System.setIn(new ByteArrayInputStream(input1.getBytes()));

      ImageModel mockImageModel = new MockImageModel();
      ImageView view = new ImageViewImpl();
      ImageController test = new ImageControllerImpl(mockImageModel, view);

      ByteArrayOutputStream outContent = new ByteArrayOutputStream();
      PrintStream originalErr = System.err;
      System.setErr(new PrintStream(outContent));
      test.process();
      System.setErr(originalErr);
      String capturedOutput = outContent.toString().trim();
      assertEquals("Invalid command: greyscaleImage", capturedOutput);
    } catch (ValidationException e) {
      assertEquals("exception.ValidationException: Invalid command: " +
              "greyscaleImage", e.toString());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void exceptionGreyscaleInvalidCase2() throws ValidationException, IOException {
    try {
      String input1 = "greyscale mat";
      System.setIn(new ByteArrayInputStream(input1.getBytes()));
      ImageModel mockImageModel = new MockImageModel();
      ImageView view = new ImageViewImpl();
      ImageController test = new ImageControllerImpl(mockImageModel, view);
      ByteArrayOutputStream outContent = new ByteArrayOutputStream();
      PrintStream originalErr = System.err;
      System.setErr(new PrintStream(outContent));
      test.process();
      System.setErr(originalErr);
      String capturedOutput = outContent.toString().trim();
      assertEquals("Invalid command: load1", capturedOutput);
    } catch (ArrayIndexOutOfBoundsException e) {
      assertEquals("java.lang.ArrayIndexOutOfBoundsException: " +
              "Index 2 out of bounds for length 2", e.toString());
    }
  }
}