import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import controller.ImageController;
import controller.ImageControllerImpl;
import exception.ValidationException;
import model.ImageModel;
import model.MockImageModel;
import view.ImageView;
import view.ImageViewImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Junit Tests for Image Controller Script file.
 */
public class ImageControllerModelTest {

  @Test
  public void testProcessLoadBrightenVFlipHFlip() {
    try {
      String input = "load res/mat.ppm mat";
      System.setIn(new ByteArrayInputStream(input.getBytes()));
      ImageModel mockImageModel = new MockImageModel();
      ImageView view = new ImageViewImpl();
      ImageController testController = new ImageControllerImpl(mockImageModel, view);
      testController.process();

      String input1 = "brighten -111 mat mat-brighter-st";
      System.setIn(new ByteArrayInputStream(input1.getBytes()));
      ImageController testController1 = new ImageControllerImpl(mockImageModel, view);
      testController1.process();
      assertEquals("mat Brightened as mat-brighter-st ", mockImageModel.toString());


      String input2 = "vertical-flip mat mat-vertical";
      System.setIn(new ByteArrayInputStream(input2.getBytes()));
      ImageController testController2 = new ImageControllerImpl(mockImageModel, view);
      testController2.process();
      assertEquals("mat Brightened as mat-brighter-st "
                      + "mat Vertically Flipped as mat-vertical ",
              mockImageModel.toString());


      String input3 = "horizontal-flip mat mat-horizontal";
      System.setIn(new ByteArrayInputStream(input3.getBytes()));
      ImageController testController3 = new ImageControllerImpl(mockImageModel, view);
      testController3.process();
      assertEquals(
              "mat Brightened as mat-brighter-st mat Vertically "
                      + "Flipped as mat-vertical mat Horizontally Flipped as mat-horizontal ",
              mockImageModel.toString());

      assertNotNull(mockImageModel);
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testProcessRgbSplitRgbCombineGrayscale() {
    try {
      String input1 = "load res/mat.ppm mat";
      System.setIn(new ByteArrayInputStream(input1.getBytes()));
      ImageModel mockImageModel = new MockImageModel();
      ImageView view = new ImageViewImpl();
      ImageController testController = new ImageControllerImpl(mockImageModel, view);
      testController.process();

      String input2 = "#split this image\nrgb-split mat mat-red mat-blue mat-green";
      System.setIn(new ByteArrayInputStream(input2.getBytes()));
      ImageController testController1 = new ImageControllerImpl(mockImageModel, view);
      testController1.process();
      assertEquals("mat RGB Split successful ", mockImageModel.toString());

      String input3 = "rgb-combine rgb-image mat-red-tint mat-red mat-green mat-blue";
      System.setIn(new ByteArrayInputStream(input3.getBytes()));
      ImageController testController2 = new ImageControllerImpl(mockImageModel, view);
      testController2.process();
      assertEquals("mat RGB Split successful "
              + "RGB combined as rgb-image ", mockImageModel.toString());

      String input4 = "greyscale value-component mat mat-greyscale";
      System.setIn(new ByteArrayInputStream(input4.getBytes()));
      ImageController testController3 = new ImageControllerImpl(mockImageModel, view);
      testController3.process();
      assertEquals(
              "mat RGB Split successful RGB combined as rgb-image "
                      + "mat grayscale successful as mat-greyscale ",
              mockImageModel.toString());
      assertNotNull(mockImageModel);
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }


  @Test
  public void testProcessModelControllerOPEqual() {
    try {
      MockImageModel mockedModel = new MockImageModel();
      ImageView view = new ImageViewImpl();
      mockedModel.imgConvertGreyScale("value-component",
              "mat", "mat-greyscale");
      String expectedString = "mat grayscale successful as mat-greyscale ";
      assertEquals(expectedString, mockedModel.toString());

      String input1 = "greyscale value-component mat mat-greyscale";
      System.setIn(new ByteArrayInputStream(input1.getBytes()));
      MockImageModel mockedModel1 = new MockImageModel();
      ImageController test = new ImageControllerImpl(mockedModel1, view);
      test.process();
      assertEquals(expectedString, mockedModel.toString());
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testProcessBrightenWorksForHigherNumber() {
    try {
      String input4 = "brighten 276 mat mat-brighter";
      System.setIn(new ByteArrayInputStream(input4.getBytes()));
      ImageModel mockImageModel = new MockImageModel();
      ImageView view = new ImageViewImpl();
      ImageController test = new ImageControllerImpl(mockImageModel, view);
      test.process();
      assertEquals("mat Brightened as mat-brighter ", mockImageModel.toString());
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testProcessBrightenWithHash() {
    try {
      String input4 = "#bright this image\nbrighten 276 mat mat-brighter";
      System.setIn(new ByteArrayInputStream(input4.getBytes()));
      ImageModel mockImageModel = new MockImageModel();
      ImageView view = new ImageViewImpl();
      ImageController test = new ImageControllerImpl(mockImageModel, view);
      test.process();
      assertEquals("mat Brightened as mat-brighter ", mockImageModel.toString());
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testProcessRunScriptFile() {
    try {
      String input4 = "#run script\nrun test/scripts/runScript.txt";
      System.setIn(new ByteArrayInputStream(input4.getBytes()));
      ImageModel mockImageModel = new MockImageModel();
      ImageView view = new ImageViewImpl();
      ImageController test = new ImageControllerImpl(mockImageModel, view);
      test.process();
      assertEquals("mat Brightened as mat-brighter " +
                      "mat Vertically Flipped as mat-vertical " +
                      "mat Horizontally Flipped as mat-horizontal " +
                      "mat grayscale successful as mat-greyscale-value " +
                      "mat grayscale successful as mat-greyscale-red " +
                      "mat RGB Split successful RGB combined as mat-combine " +
                      "mat sepia successful as mat-sepia " +
                      "mat blur successful as mat-blur " +
                      "mat dither successful as mat-dither " +
                      "mat sharpen successful as mat-sharpen " +
                      "mat-grey grayscale successful as mat "
              , mockImageModel.toString());
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }


  @Test
  public void testProcessSepiaDitherBlurSharpenGreyscale() {
    try {
      String input = "load res/mat.ppm mat";
      System.setIn(new ByteArrayInputStream(input.getBytes()));
      ImageModel mockImageModel = new MockImageModel();
      ImageView view = new ImageViewImpl();
      ImageController testController = new ImageControllerImpl(mockImageModel, view);
      testController.process();

      String input1 = "sepia mat mat-sepia";
      System.setIn(new ByteArrayInputStream(input1.getBytes()));
      ImageController testController1 = new ImageControllerImpl(mockImageModel, view);
      testController1.process();
      assertEquals("mat sepia successful as mat-sepia ", mockImageModel.toString());


      String input2 = "dither mat mat-dither";
      System.setIn(new ByteArrayInputStream(input2.getBytes()));
      ImageController testController2 = new ImageControllerImpl(mockImageModel, view);
      testController2.process();
      assertEquals("mat sepia successful as mat-sepia " +
                      "mat dither successful as mat-dither ",
              mockImageModel.toString());


      String input3 = "blur mat mat-blur";
      System.setIn(new ByteArrayInputStream(input3.getBytes()));
      ImageController testController3 = new ImageControllerImpl(mockImageModel, view);
      testController3.process();
      assertEquals(
              "mat sepia successful as mat-sepia " +
                      "mat dither successful as mat-dither " +
                      "mat blur successful as mat-blur ",
              mockImageModel.toString());

      String input4 = "sharpen mat mat-sharpen";
      System.setIn(new ByteArrayInputStream(input4.getBytes()));
      ImageController testController4 = new ImageControllerImpl(mockImageModel, view);
      testController4.process();
      assertEquals(
              "mat sepia successful as mat-sepia " +
                      "mat dither successful as mat-dither " +
                      "mat blur successful as mat-blur " +
                      "mat sharpen successful as mat-sharpen ",
              mockImageModel.toString());
      assertNotNull(mockImageModel);

      String input5 = "greyscale mat mat-sharpen";
      System.setIn(new ByteArrayInputStream(input5.getBytes()));
      ImageController testController5 = new ImageControllerImpl(mockImageModel, view);
      testController5.process();
      assertEquals(
              "mat sepia successful as mat-sepia " +
                      "mat dither successful as mat-dither " +
                      "mat blur successful as mat-blur " +
                      "mat sharpen successful as mat-sharpen " +
                      "mat-sharpen grayscale successful as mat ",
              mockImageModel.toString());
      assertNotNull(mockImageModel);
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

}