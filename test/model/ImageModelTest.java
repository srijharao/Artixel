package model;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import controller.util.Utility;
import exception.ValidationException;
import model.image.Image;

import static org.junit.Assert.assertEquals;

/**
 * The ImageModelTest class provides test cases for the ImageModelImpl class.
 */
public class ImageModelTest {

  private ImageModelImpl image1;
  private ImageModelImpl image2;

  @Before
  public void setup() throws IOException {
    image1 = new ImageModelImpl();
    int[][][] imgData = Utility.readImage("test/res/mat.ppm");
    image1.setImage("mat", imgData);

    image2 = new ImageModelImpl();
    int[][][] imgData2 = Utility.readImage("test/res/colorMatrix.ppm");
    image2.setImage("colorMat", imgData2);
  }

  @Test
  public void testModelBrighten() {
    try {
      // using the image1 that is used in the setup
      image1.brighten(10, "mat", "mat-brighten");
      Utility.writeImage(image1.getImage("mat-brighten"), "test/res/mat-brighten.ppm");

      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat-brighten.ppm");

      // expected image data
      String dataString = "[[[235, 255, 10], [235, 255, 10], [235, 255, 10], [235, 160, 10]], "
              + "[[235, 255, 10], [10, 225, 110], [200, 10, 10], [170, 10, 130]], "
              + "[[235, 255, 10], [121, 89, 200], [128, 255, 98], [109, 98, 64]], "
              + "[[10, 202, 236], [10, 128, 120], [10, 166, 160], [10, 90, 200]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException | IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testModelDarken() {
    try {
      // using the image1 that is used in the setup
      image1.brighten(-50, "mat", "mat-darken");

      Utility.writeImage(image1.getImage("mat-darken"), "test/res/mat-darken.ppm");

      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat-darken.ppm");

      // expected image data
      String dataString = "[[[175, 205, 0], [175, 205, 0], [175, 205, 0], [175, 100, 0]], "
              + "[[175, 205, 0], [0, 165, 50], [140, 0, 0], [110, 0, 70]], [[175, 205, 0], "
              + "[61, 29, 140], [68, 205, 38], [49, 38, 4]], [[0, 142, 176], [0, 68, 60], "
              + "[0, 106, 100], [0, 30, 140]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }

  }

  @Test
  public void testModelBrightenDarken() {
    try {
      // using the image1 that is used in the setup
      image1.brighten(50, "mat", "mat-brighten");
      image1.brighten(-50, "mat-brighten", "mat-brighten-dark");
      Utility.writeImage(image1.getImage("mat-brighten-dark"), "test/res/mat-brighten-dark.ppm");

      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat-brighten-dark.ppm");


      String dataString = "[[[205, 205, 0], [205, 205, 0], [205, 205, 0], [205, 150, 0]], "
              + "[[205, 205, 0], [0, 205, 100], [190, 0, 0], [160, 0, 120]], [[205, 205, 0], "
              + "[111, 79, 190], [118, 205, 88], [99, 88, 54]], [[0, 192, 205], [0, 118, 110], "
              + "[0, 156, 150], [0, 80, 190]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testModelBrighten255Max() {
    try {
      // using the image1 that is used in the setup
      image1.brighten(255, "mat", "mat-brighten-max");
      Image img = image1.getImage("mat-brighten-max");
      Utility.writeImage(img, "test/res/mat-brighten-max.ppm");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat-brighten-max.ppm");

      String dataString = "[[[255, 255, 255], [255, 255, 255], [255, 255, 255], " +
              "[255, 255, 255]], [[255, 255, 255], [255, 255, 255], [255, 255, 255], " +
              "[255, 255, 255]], [[255, 255, 255], [255, 255, 255], [255, 255, 255], " +
              "[255, 255, 255]], [[255, 255, 255], [255, 255, 255], " +
              "[255, 255, 255], [255, 255, 255]]]";


      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testModelMaxDarken() {
    try {
      // using the image1 that is used in the setup
      image1.brighten(-255, "mat", "mat-dark-max");
      Image img = image1.getImage("mat-dark-max");
      Utility.writeImage(img, "test/res/mat-dark-max.ppm");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat-dark-max.ppm");
      String data = "[[[0, 0, 0], [0, 0, 0], [0, 0, 0], [0, 0, 0]], "
              + "[[0, 0, 0], [0, 0, 0], [0, 0, 0], [0, 0, 0]], "
              + "[[0, 0, 0], [0, 0, 0], [0, 0, 0], [0, 0, 0]], "
              + "[[0, 0, 0], [0, 0, 0], [0, 0, 0], [0, 0, 0]]]";

      assertEquals(data, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }


  @Test
  public void testLoadingOfFile() {
    try {
      int[][][] imgData = Utility.readImage("test/res/mat.ppm");
      image1.setImage("mat", imgData);
      Image img = image1.getImage("mat");
      Utility.writeImage(img, "test/res/mat.ppm");
      // read the saved file from above operation
      int[][][] image = Utility.readImage("test/res/mat.ppm");
      String data = "[[[225, 255, 0], [225, 255, 0], [225, 255, 0], [225, 150, 0]], "
              + "[[225, 255, 0], [0, 215, 100], [190, 0, 0], [160, 0, 120]], [[225, 255, 0], "
              + "[111, 79, 190], [118, 255, 88], [99, 88, 54]], [[0, 192, 226], [0, 118, 110], "
              + "[0, 156, 150], [0, 80, 190]]]";

      assertEquals(data, Arrays.deepToString(image));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }


  @Test
  public void testLoadingOfFileNotExist() {
    try {
      // using the image1 that is used in the setup
      Utility.readImage("test/res/matt.ppm");
    } catch (IOException e) {
      assertEquals("java.io.FileNotFoundException: File " +
              "test/res/matt.ppm not found!", e.toString());
    }
  }


  @Test
  public void testModelVerticalFlipImage() {
    try {
      // using the image1 that is used in the setup
      image1.vFlip("mat", "mat-vertical");
      Image img = image1.getImage("mat-vertical");
      Utility.writeImage(img, "test/res/mat-vertical.ppm");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat-vertical.ppm");
      String dataString = "[[[0, 192, 226], [0, 118, 110], [0, 156, 150], [0, 80, 190]], "
              + "[[225, 255, 0], [111, 79, 190], [118, 255, 88], [99, 88, 54]], [[225, 255, 0], "
              + "[0, 215, 100], [190, 0, 0], [160, 0, 120]], [[225, 255, 0], [225, 255, 0], "
              + "[225, 255, 0], [225, 150, 0]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testModelHorizontalFlipImage() {
    try {
      // using the image1 that is used in the setup
      image1.hFlip("mat", "mat-horizontal");
      Image img = image1.getImage("mat-horizontal");
      Utility.writeImage(img, "test/res/mat-horizontal.ppm");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat-horizontal.ppm");
      String dataString = "[[[225, 150, 0], [225, 255, 0], [225, 255, 0], [225, 255, 0]], "
              + "[[160, 0, 120], [190, 0, 0], [0, 215, 100], [225, 255, 0]], [[99, 88, 54], "
              + "[118, 255, 88], [111, 79, 190], [225, 255, 0]], [[0, 80, 190], [0, 156, 150], "
              + "[0, 118, 110], [0, 192, 226]]]";
      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testModelHorizontalThenVerticalFlip() {
    try {
      // using the image1 that is used in the setup
      image1.hFlip("mat", "mat-horizontal");
      image1.vFlip("mat-horizontal", "mat-horizontal-vertical");
      Image img = image1.getImage("mat-horizontal-vertical");
      Utility.writeImage(img, "test/res/mat-horizontal-vertical.ppm");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat-horizontal-vertical.ppm");
      String dataString = "[[[0, 80, 190], [0, 156, 150], [0, 118, 110], [0, 192, 226]], " +
              "[[99, 88, 54], [118, 255, 88], [111, 79, 190], [225, 255, 0]], " +
              "[[160, 0, 120], [190, 0, 0], [0, 215, 100], [225, 255, 0]], " +
              "[[225, 150, 0], [225, 255, 0], [225, 255, 0], [225, 255, 0]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testModelVerticalThenHorizontalFlip() {
    try {
      // using the image1 that is used in the setup
      image1.vFlip("mat", "mat-vertical");
      image1.hFlip("mat-vertical", "mat-vertical-horizontal");
      Image img = image1.getImage(
              "mat-vertical-horizontal");
      Utility.writeImage(img, "test/res/mat-vertical-horizontal.ppm");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat-vertical-horizontal.ppm");
      String dataString = "[[[0, 80, 190], [0, 156, 150], [0, 118, 110], [0, 192, 226]], "
              + "[[99, 88, 54], [118, 255, 88], [111, 79, 190], [225, 255, 0]], "
              + "[[160, 0, 120], [190, 0, 0], [0, 215, 100], [225, 255, 0]], "
              + "[[225, 150, 0], [225, 255, 0], [225, 255, 0], [225, 255, 0]]]";


      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void TestModelInvalidVFlipHFlipInvalidWithWrongFilename() {
    try {
      // using the image1 that is used in the setup
      image1.vFlip("mat", "mat-vertical");
      image1.hFlip("mat-flipper", "mat-vertical-horizontal");
    } catch (ValidationException e) {
      assertEquals("exception.ValidationException: " +
              "The image mat-flipper does not exist", e.toString());
    }
  }

  @Test
  public void testModelInvalidVFlip() {
    try {
      // using the image1 that is used in the setup
      image1.vFlip("mat-21", "mat-vertical");
    } catch (ValidationException e) {
      assertEquals("exception.ValidationException: " +
              "The image mat-21 does not exist", e.toString());
    }
  }

  @Test
  public void testModelInvalidHFlip() {
    try {
      // using the image1 that is used in the setup
      image1.hFlip("mat-21", "mat-horizontal");
    } catch (ValidationException e) {
      assertEquals("exception.ValidationException: The image mat-21 does not exist", e.toString());
    }
  }


  @Test
  public void oneMethodTestValueImgConvertGreyScale() {
    try {
      // using the image1 that is used in the setup
      image1.imgConvertGreyScale("value-component", "mat",
              "mat-value-grayscale");
      Image img = image1.getImage("mat-value-grayscale");
      Utility.writeImage(img, "test/res/mat-value-grayscale.ppm");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat-value-grayscale.ppm");
      String dataString = "[[[255, 255, 255], [255, 255, 255], [255, 255, 255], " +
              "[225, 225, 225]], [[255, 255, 255], [215, 215, 215], " +
              "[190, 190, 190], [160, 160, 160]], [[255, 255, 255], " +
              "[190, 190, 190], [255, 255, 255], [99, 99, 99]], [[226, 226, 226], " +
              "[118, 118, 118], [156, 156, 156], [190, 190, 190]]]";


      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testModelIntensityImgConvertGreyScale() {
    try {
      // using the image1 that is used in the setup
      image1.imgConvertGreyScale("intensity-component", "mat",
              "mat-intensity-grayscale");
      Image img = image1.getImage(
              "mat-intensity-grayscale");
      Utility.writeImage(img, "test/res/mat-intensity-grayscale.ppm");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat-intensity-grayscale.ppm");
      String dataString = "[[[159, 159, 159], [159, 159, 159], [159, 159, 159], " +
              "[124, 124, 124]], [[159, 159, 159], [104, 104, 104], [63, 63, 63], " +
              "[93, 93, 93]], [[159, 159, 159], [126, 126, 126], [153, 153, 153], " +
              "[80, 80, 80]], [[139, 139, 139], [75, 75, 75]," +
              " [101, 101, 101], [89, 89, 89]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testModelLumaConvertGreyScale() {
    try {
      // using the image1 that is used in the setup
      image1.imgConvertGreyScale("luma-component", "mat",
              "mat-luma-grayscale");
      Image img = image1.getImage(
              "mat-luma-grayscale");
      Utility.writeImage(img, "test/res/mat-luma-grayscale.ppm");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat-luma-grayscale.ppm");
      String dataString = "[[[230, 230, 230], [230, 230, 230], [230, 230, 230], [155, 155, 155]], "
              + "[[230, 230, 230], [160, 160, 160], [40, 40, 40], [42, 42, 42]], [[230, 230, 230], "
              + "[93, 93, 93], [213, 213, 213], [87, 87, 87]], [[153, 153, 153], [92, 92, 92], "
              + "[122, 122, 122], [70, 70, 70]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testRedImgConvertGreyScale() {
    try {
      // using the image1 that is used in the setup
      image1.imgConvertGreyScale("red-component", "mat",
              "mat-red-grayscale");
      Image img = image1.getImage(
              "mat-red-grayscale");
      Utility.writeImage(img, "test/res/mat-red-grayscale.ppm");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat-red-grayscale.ppm");
      String dataString = "[[[225, 225, 225], [225, 225, 225], [225, 225, 225], [225, 225, 225]], "
              + "[[225, 225, 225], [0, 0, 0], [190, 190, 190], [160, 160, 160]], [[225, 225, 225], "
              + "[111, 111, 111], [118, 118, 118], [99, 99, 99]], " +
              "[[0, 0, 0], [0, 0, 0], [0, 0, 0], [0, 0, 0]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }


  @Test
  public void testBlueImgConvertGreyScale() {
    try {
      // using the image1 that is used in the setup
      image1.imgConvertGreyScale("blue-component", "mat",
              "mat-blue-grayscale");
      Image img = image1.getImage(
              "mat-blue-grayscale");
      Utility.writeImage(img, "test/res/mat-blue-grayscale.ppm");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat-blue-grayscale.ppm");
      String dataString = "[[[0, 0, 0], [0, 0, 0], [0, 0, 0], [0, 0, 0]], [[0, 0, 0]," +
              " [100, 100, 100], [0, 0, 0], [120, 120, 120]], [[0, 0, 0], [190, 190, 190]," +
              " [88, 88, 88], [54, 54, 54]], [[226, 226, 226], [110, 110, 110], " +
              "[150, 150, 150], [190, 190, 190]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }


  @Test
  public void testGreenImgConvertGreyScale() {
    try {
      // using the image1 that is used in the setup
      image1.imgConvertGreyScale("green-component", "mat",
              "mat-green-grayscale");
      Image img = image1.getImage(
              "mat-green-grayscale");
      Utility.writeImage(img, "test/res/mat-green-grayscale.ppm");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat-green-grayscale.ppm");
      String dataString = "[[[255, 255, 255], [255, 255, 255], [255, 255, 255], [150, 150, 150]], "
              + "[[255, 255, 255], [215, 215, 215], [0, 0, 0], [0, 0, 0]], [[255, 255, 255], "
              + "[79, 79, 79], [255, 255, 255], [88, 88, 88]], [[192, 192, 192], [118, 118, 118], "
              + "[156, 156, 156], [80, 80, 80]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void TestAllMethodsForGreyScale() {
    try {
      // using the image1 that is used in the setup
      image1.imgConvertGreyScale("blue-component", "mat",
              "mat-blue");
      image1.imgConvertGreyScale("green-component", "mat-blue",
              "mat-blue-green");
      image1.imgConvertGreyScale("red-component", "mat-blue-green",
              "mat-blue-green-red");
      image1.imgConvertGreyScale("luma-component", "mat-blue-green-red",
              "mat-blue-green-red-luma");
      image1.imgConvertGreyScale("intensity-component", "mat-blue-green-red-luma",
              "mat-blue-green-red-luma-intensity");
      image1.imgConvertGreyScale("value-component",
              "mat-blue-green-red-luma-intensity",
              "mat-blue-green-red-luma-intensity-value");
      Image img = image1.getImage(
              "mat-blue-green-red-luma-intensity-value");
      Utility.writeImage(img, "test/res/mat-blue-green-red-luma-intensity-value.ppm");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat-blue-green-red-"
              + "luma-intensity-value.ppm");
      String dataString = "[[[0, 0, 0], [0, 0, 0], [0, 0, 0], [0, 0, 0]], [[0, 0, 0], " +
              "[99, 99, 99], [0, 0, 0], [119, 119, 119]], [[0, 0, 0], [188, 188, 188]," +
              " [87, 87, 87], [53, 53, 53]], [[225, 225, 225], [109, 109, 109], " +
              "[149, 149, 149], [188, 188, 188]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testModelInvalidFileValueGreyscale() {
    try {
      // using the image1 that is used in the setup
      image1.imgConvertGreyScale("value-component", "mat-test-file",
              "colorMatrix-greyscale");
    } catch (ValidationException e) {
      assertEquals("exception.ValidationException: " +
              "The image mat-test-file does not exist", e.toString());
    }
  }

  @Test
  public void testModelInvalidFileIntensityGreyscale() {
    try {
      // using the image1 that is used in the setup
      image1.imgConvertGreyScale("intensity-component", "mat-test-file",
              "colorMatrix-greyscale");
    } catch (ValidationException e) {
      assertEquals("exception.ValidationException: " +
              "The image mat-test-file does not exist", e.toString());
    }
  }

  @Test
  public void testModelInvalidFileLumaGreyscale() {
    try {
      // using the image1 that is used in the setup
      image1.imgConvertGreyScale("luma-component", "colorMatrix-test-file",
              "colorMatrix-greyscale");
    } catch (ValidationException e) {
      assertEquals("exception.ValidationException: " +
              "The image colorMatrix-test-file does not exist", e.toString());
    }
  }

  @Test
  public void testModelInvalidFileRedGreyscale() {
    try {
      // using the image1 that is used in the setup
      image1.imgConvertGreyScale("red-component", "colorMatrix-test-file",
              "colorMatrix-greyscale");
    } catch (ValidationException e) {
      assertEquals("exception.ValidationException: " +
              "The image colorMatrix-test-file does not exist", e.toString());
    }
  }

  @Test
  public void testModelInvalidFileGreenGreyscale() {
    try {
      // using the image1 that is used in the setup
      image1.imgConvertGreyScale("green-component", "colorMatrix-test-file",
              "colorMatrix-greyscale");
    } catch (ValidationException e) {
      assertEquals("exception.ValidationException: " +
              "The image colorMatrix-test-file does not exist", e.toString());
    }
  }

  @Test
  public void testModelInvalidFileBlueGreyscale() {
    try {
      // using the image1 that is used in the setup
      image1.imgConvertGreyScale("blue-component", "colorMatrix-test-file",
              "colorMatrix-greyscale");
    } catch (ValidationException e) {
      assertEquals("exception.ValidationException:" +
              " The image colorMatrix-test-file does not exist", e.toString());
    }
  }

  @Test
  public void testModelInvalidComponentGreyscale() {
    try {
      // using the image1 that is used in the setup
      int[][][] imgData = Utility.readImage("test/res/colorMatrix.ppm");
      image1.setImage("colorMatrix", imgData);
      image1.imgConvertGreyScale("wrong-component", "colorMatrix",
              "colorMatrix-greyscale");
    } catch (ValidationException e) {
      assertEquals("exception.ValidationException: " +
              "Invalid component", e.toString());
    } catch (IOException e) {
      System.out.println("Error: Invalid IO operation");
    }
  }


  @Test
  public void testModelRGBSplitImage() {
    try {
      // using the image1 that is used in the setup
      int[][][] imgData = Utility.readImage("test/res/mat.ppm");
      image1.setImage("mat", imgData);
      image1.imgSplitRGB("mat", "mat-red-split",
              "mat-green-split", "mat-blue-split");
      Image img1 = image1.getImage("mat-red-split");
      Image img2 = image1.getImage("mat-green-split");
      Image img3 = image1.getImage("mat-blue-split");
      Utility.writeImage(img1, "test/res/mat-red-split.ppm");
      Utility.writeImage(img2, "test/res/mat-green-split.ppm");
      Utility.writeImage(img3, "test/res/mat-blue-split.ppm");
      // read the saved file from above operation
      int[][][] savedImageRed = Utility.readImage("test/res/mat-red-split.ppm");
      int[][][] savedImageBlue = Utility.readImage("test/res/mat-blue-split.ppm");
      int[][][] savedImageGreen = Utility.readImage("test/res/mat-green-split.ppm");
      String redData = "[[[225, 225, 225], [225, 225, 225], [225, 225, 225], " +
              "[225, 225, 225]], [[225, 225, 225], [0, 0, 0], [190, 190, 190], " +
              "[160, 160, 160]], [[225, 225, 225], [111, 111, 111], " +
              "[118, 118, 118], [99, 99, 99]], [[0, 0, 0], [0, 0, 0], " +
              "[0, 0, 0], [0, 0, 0]]]";

      String blueData = "[[[0, 0, 0], [0, 0, 0], [0, 0, 0], [0, 0, 0]], " +
              "[[0, 0, 0], [100, 100, 100], [0, 0, 0], [120, 120, 120]], " +
              "[[0, 0, 0], [190, 190, 190], [88, 88, 88], [54, 54, 54]], " +
              "[[226, 226, 226], [110, 110, 110], [150, 150, 150], " +
              "[190, 190, 190]]]";

      String greenData = "[[[255, 255, 255], [255, 255, 255], [255, 255, 255], " +
              "[150, 150, 150]], [[255, 255, 255], [215, 215, 215], [0, 0, 0], " +
              "[0, 0, 0]], [[255, 255, 255], [79, 79, 79], [255, 255, 255], " +
              "[88, 88, 88]], [[192, 192, 192], [118, 118, 118], " +
              "[156, 156, 156], [80, 80, 80]]]";

      assertEquals(redData, Arrays.deepToString(savedImageRed));
      assertEquals(blueData, Arrays.deepToString(savedImageBlue));
      assertEquals(greenData, Arrays.deepToString(savedImageGreen));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testModelImgCombineRGBImage() {
    try {
      // using the image1 that is used in the setup
      image1.imgSplitRGB("mat", "mat-red-split",
              "mat-green-split", "mat-blue-split");
      image1.imgCombineRGB("mat-split-combine", "mat-red-split",
              "mat-green-split", "mat-blue-split");
      Image img = image1.getImage("mat-split-combine");
      Utility.writeImage(img, "test/res/mat-split-combine.ppm");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat-split-combine.ppm");
      String dataString = "[[[225, 255, 0], [225, 255, 0], [225, 255, 0], [225, 150, 0]], " +
              "[[225, 255, 0], [0, 215, 100], [190, 0, 0], [160, 0, 120]], [[225, 255, 0], " +
              "[111, 79, 190], [118, 255, 88], [99, 88, 54]], [[0, 192, 226]," +
              " [0, 118, 110], [0, 156, 150], [0, 80, 190]]]";
      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void validTestRBGCombineWithTwoImages() {
    try {
      image1.imgSplitRGB("mat", "mat-split-Red",
              "mat-split-Green", "mat-split-Blue");
      // using the image1 that is used in the setup
      int[][][] imgData = Utility.readImage("test/res/colorMatrix.ppm");
      image1.setImage("colorMat", imgData);
      image1.imgSplitRGB("colorMat", "colormat-split-Red",
              "colormat-split-Green", "colormat-split-Blue");
      image1.imgCombineRGB("mat-combine", "colormat-split-Red",
              "mat-split-Green", "mat-split-Blue");
      Image img = image1.getImage("mat-combine");
      Utility.writeImage(img, "test/res/mat-combine.ppm");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat-combine.ppm");
      String dataString = "[[[200, 255, 0], [225, 255, 0], [59, 255, 0], [200, 150, 0]], " +
              "[[200, 255, 0], [200, 215, 100], [180, 0, 0], [200, 0, 120]], " +
              "[[200, 255, 0], [111, 79, 190], [200, 255, 88], [200, 88, 54]], " +
              "[[200, 192, 226], [0, 118, 110], [0, 156, 150], [200, 80, 190]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testModelRBGCombineInvalid() {
    try {
      image1.imgSplitRGB("mat", "mat-splitRed",
              "mat-split-Green", "mat-split-Blue");
      Image img1 = image1.getImage("mat-splitRed");
      Image img2 = image1.getImage("mat-splitGreen");
      Image img3 = image1.getImage("mat-split-Blue");
      image1.imgCombineRGB("mat-combine", "matrix-splitRed",
              "matrix-splitGreen", "matrix-split-Blue");
      Image img = image1.getImage("mat-combine");
    } catch (ValidationException e) {
      assertEquals("exception.ValidationException: " +
              "The image mat-splitGreen does not exist", e.toString());
    }
  }

  @Test
  public void testModelCombineInvalid() {
    try {
      image1.imgCombineRGB("mat-combine", "red.ppm",
              "green,ppm", "blue.ppm");
    } catch (ValidationException e) {
      assertEquals("exception.ValidationException: " +
              "The image red.ppm does not exist", e.toString());
    }
  }

  @Test
  public void testModelForAllMethods() {
    try {
      //Loading
      int[][][] imgData = Utility.readImage("test/res/colorMatrix.ppm");
      image1.setImage("colorMat", imgData);
      // Brighten
      image1.brighten(50, "colorMat", "colorMat-brighten");
      // Vertical Flip
      image1.vFlip("colorMat-brighten", "colorMat-brighten-vertical");
      // Horizontal Flip
      image1.hFlip("colorMat-brighten-vertical",
              "colorMat-brighten-vertical-horizontal");
      // GreyScale
      image1.imgConvertGreyScale("blue-component",
              "colorMat-brighten-vertical-horizontal",
              "colorMat-brighten-vertical-horizontal-imgConvertGreyScale");
      // Split RGB
      image1.imgSplitRGB("colorMat-brighten-vertical-horizontal",
              "colorMat-brighten-vertical-horizontal-red-split",
              "colorMat-brighten-vertical-horizontal-green-split",
              "colorMat-brighten-vertical-horizontal-blue-split");
      // Combine RGB
      image1.imgCombineRGB("colorMat-brighten-vertical-horizontal-combine",
              "colorMat-brighten-vertical-horizontal-red-split",
              "colorMat-brighten-vertical-horizontal-green-split",
              "colorMat-brighten-vertical-horizontal-blue-split");
      // Save PPM
      Image img = image1.getImage(
              "colorMat-brighten-vertical-horizontal-combine");
      Utility.writeImage(img, "test/res/colorMat-brighten-" +
              "vertical-horizontal-combine.ppm");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/colorMat-brighten-"
              + "vertical-horizontal-combine.ppm");

      String dataString = "[[[250, 70, 50], [50, 206, 200], [50, 168, 160], " +
              "[250, 70, 50]], [[250, 70, 50], [250, 70, 50], [161, 129, 240]," +
              " [250, 70, 50]], [[250, 70, 50], [230, 255, 240], [250, 70, 50], " +
              "[250, 70, 50]], [[250, 70, 50], [109, 255, 230]," +
              " [255, 255, 50], [250, 70, 50]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testOverwriteExistingImage() {
    try {
      //Loading different image in image1
      int[][][] imgData = Utility.readImage("test/res/colorMatrix.ppm");
      image1.setImage("mat", imgData);
      Image img = image1.getImage("mat");
      Utility.writeImage(img, "test/res/updatedMat.ppm");
      int[][][] savedImageOp = Utility.readImage("test/res/updatedMat.ppm");
      String dataString = "[[[200, 20, 0], [225, 255, 0], [59, 255, 180], [200, 20, 0]]," +
              " [[200, 20, 0], [200, 20, 0], [180, 235, 190], [200, 20, 0]], [[200, 20, 0]," +
              " [111, 79, 190], [200, 20, 0], [200, 20, 0]], [[200, 20, 0], [0, 118, 110]," +
              " [0, 156, 150], [200, 20, 0]]]";
      // read the saved file from above operation
      assertEquals(dataString, Arrays.deepToString(savedImageOp));

    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testSavingOfFile() {
    try {
      //Loading different image in image1
      int[][][] imgData = Utility.readImage("test/res/colorMatrix.ppm");
      image1.setImage("colorMatrix", imgData);
      Image img = image1.getImage("colorMatrix");
      // read the saved file from above operation
      Utility.writeImage(img, "test/res/colorMatrix2.ppm");
      int[][][] savedImageOp = Utility.readImage("test/res/colorMatrix2.ppm");
      String dataString = "[[[200, 20, 0], [225, 255, 0], [59, 255, 180], [200, 20, 0]], " +
              "[[200, 20, 0], [200, 20, 0], [180, 235, 190], [200, 20, 0]], " +
              "[[200, 20, 0], [111, 79, 190], [200, 20, 0], [200, 20, 0]], " +
              "[[200, 20, 0], [0, 118, 110], [0, 156, 150], [200, 20, 0]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testModelInvalidFileBrighten() {
    try {

      //Loading different image in image1
      int[][][] imgData = Utility.readImage("test/res/colorMatrix.ppm");
      image1.setImage("colorMatrix", imgData);
      image1.brighten(50, "colorMat", "colorMatrix-brighten");
    } catch (ValidationException e) {
      assertEquals("exception.ValidationException: " +
              "The image colorMat does not exist", e.toString());
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }


  @Test
  public void testModelInvalidArgsBrighten() {
    try {
      //Loading different image in image1
      int[][][] imgData = Utility.readImage("test/res/colorMatrix.ppm");
      image1.setImage("colorMatrix", imgData);
      image1.brighten(89, "colorMatrix-brighten", "colorMatrix");
    } catch (ValidationException e) {
      assertEquals("exception.ValidationException:" +
              " The image colorMatrix-brighten does not exist", e.toString());
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }


  @Test
  public void testInvalidVFlip() {
    try {
      image1.vFlip("colorMat", "colorMatrix-vertical");
    } catch (ValidationException e) {
      assertEquals("exception.ValidationException: " +
              "The image colorMat does not exist", e.toString());
    }
  }

  @Test
  public void testInvalidHFlip() {
    try {
      image2.vFlip("colorMat", "colorMatrix-horizontal");
    } catch (ValidationException e) {
      assertEquals("exception.ValidationException: " +
              "The image colorMat does not exist", e.toString());
    }
  }

  @Test
  public void testInvalidFile() {
    try {
      int[][][] imgData = Utility.readImage("test/res/new_invalidfile.ppm");
      image1.setImage("new_invalidfile", imgData);
    } catch (IOException e) {
      assertEquals("java.io.FileNotFoundException: " +
              "File test/res/new_invalidfile.ppm not found!", e.toString());
    }
  }


  @Test
  public void testModelSepiaImage() {
    try {
      // using the image1 that is used in the setup
      image1.colorTransformation("mat", "mat-sepia", "sepia");
      Image img = image1.getImage("mat-sepia");
      Utility.writeImage(img, "test/res/mat-sepia.ppm");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat-sepia.ppm");
      String dataString = "[[[255, 253, 197], [255, 253, 197], [255, 253, 197], " +
              "[203, 181, 141]], [[255, 253, 197], [184, 164, 127], [74, 66, 51]," +
              " [85, 76, 59]], [[255, 253, 197], [140, 124, 97], [255, 230, 179]," +
              " [116, 103, 80]], [[190, 169, 132], [111, 99, 77], " +
              "[148, 132, 102], [97, 86, 67]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }


  @Test
  public void testInvalidSepiaImage() {
    try {
      // using the image1 that is used in the setup
      image1.colorTransformation("colorMat", "colorMatrix-sepia", "sepia");
    } catch (ValidationException e) {
      assertEquals("exception.ValidationException: " +
              "The image colorMat does not exist", e.toString());
    }
  }


  @Test
  public void testModelDitherImage() {
    try {
      // using the image1 that is used in the setup
      image1.filterTransformation("mat", "mat-dither", "dither");
      Image img = image1.getImage("mat-dither");
      Utility.writeImage(img, "test/res/mat-dither.ppm");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat-dither.ppm");
      String dataString = "[[[255, 255, 255], [255, 255, 255], [255, 255, 255], " +
              "[255, 255, 255]], [[255, 255, 255], [255, 255, 255], [0, 0, 0], " +
              "[0, 0, 0]], [[255, 255, 255], [0, 0, 0], [255, 255, 255], [0, 0, 0]]," +
              " [[255, 255, 255], [0, 0, 0], [0, 0, 0], [0, 0, 0]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testInvalidDitherImage() {
    try {
      // using the image1 that is used in the setup
      image1.filterTransformation("colorMat", "colorMatrix-dither", "dither");

    } catch (ValidationException e) {
      assertEquals("exception.ValidationException: " +
              "The image colorMat does not exist", e.toString());
    }
  }

  @Test
  public void testModelBlurImage() {
    try {
      // using the image1 that is used in the setup
      image1.filterTransformation("mat", "mat-blur", "blur");
      Image img = image1.getImage("mat-blur");
      Utility.writeImage(img, "test/res/mat-blur.ppm");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat-blur.ppm");
      String dataString = "[[[113, 141, 6], [138, 170, 13], [146, 128, 14], [116, 69, 15]]," +
              " [[134, 175, 24], [143, 191, 54], [152, 126, 54], [126, 62, 42]], " +
              "[[98, 150, 65], [97, 163, 108], [90, 130, 104], [71, 74, 73]], " +
              "[[35, 100, 82], [35, 115, 104], [28, 106, 101], [20, 66, 79]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testInvalidBlurImage() {
    try {
      // using the image1 that is used in the setup
      image1.filterTransformation("colorMat", "colorMatrix-blur", "blur");
    } catch (ValidationException e) {
      assertEquals("exception.ValidationException: " +
              "The image colorMat does not exist", e.toString());
    }
  }

  @Test
  public void testModelGrayScaleTransformationImage() {
    try {
      // using the image1 that is used in the setup
      image1.grayscale("mat", "mat-gray");
      Image img = image1.getImage("mat-gray");
      Utility.writeImage(img, "test/res/mat-gray.ppm");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat-gray.ppm");
      String dataString = "[[[230, 230, 230], [230, 230, 230], [230, 230, 230], " +
              "[155, 155, 155]], [[230, 230, 230], [160, 160, 160], [40, 40, 40], " +
              "[42, 42, 42]], [[230, 230, 230], [93, 93, 93], [213, 213, 213], " +
              "[87, 87, 87]], [[153, 153, 153], [92, 92, 92], " +
              "[122, 122, 122], [70, 70, 70]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }


  @Test
  public void testInvalidGrayScaleTransformationImage() {
    try {
      // using the image1 that is used in the setup
      image1.grayscale("colorMat", "colorMatrix-gray");
    } catch (ValidationException e) {
      assertEquals("exception.ValidationException: " +
              "The image colorMat does not exist", e.toString());
    }
  }

  @Test
  public void testModelSharpenImage() {
    try {
      // using the image1 that is used in the setup
      image1.filterTransformation("mat", "mat-sharpen", "sharpen");
      Image img = image1.getImage("mat-sharpen");
      Utility.writeImage(img, "test/res/mat-sharpen.ppm");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat-sharpen.ppm");
      String dataString = "[[[229, 255, 0], [255, 255, 0], [255, 255, 14], [255, 102, 0]], " +
              "[[255, 255, 1], [255, 255, 63], [255, 160, 54], [255, 74, 63]], " +
              "[[186, 255, 127], [156, 255, 255], [89, 237, 255], [118, 77, 141]], " +
              "[[17, 195, 255], [29, 255, 216], [0, 196, 252], [0, 153, 198]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testInvalidSharpenImage() {
    try {
      // using the image1 that is used in the setup
      image1.filterTransformation("colorMat", "colorMatrix-sharpen", "sharpen");
    } catch (ValidationException e) {
      assertEquals("exception.ValidationException: " +
              "The image colorMat does not exist", e.toString());
    }
  }

  @Test
  public void testModelSharpeBlurImage() {
    try {
      // using the image1 that is used in the setup
      image1.filterTransformation("mat", "mat-sharpen", "sharpen");
      image1.filterTransformation("mat-sharpen", "mat-sharpen-blur", "blur");
      Image img = image1.getImage("mat-sharpen-blur");
      Utility.writeImage(img, "test/res/mat-sharpen-blur.ppm");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat-sharpen-blur.ppm");
      String dataString = "[[[137, 143, 4], [188, 185, 13], [191, 149, 18], [143, 77, 13]], " +
              "[[173, 191, 40], [226, 242, 79], [220, 186, 88], [164, 92, 57]], " +
              "[[118, 184, 113], [142, 239, 182], [122, 191, 185], [88, 100, 119]], " +
              "[[41, 128, 123], [46, 175, 173], [32, 150, 171], [20, 87, 115]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testModelBlurSharpenImage() {
    try {
      // using the image1 that is used in the setup
      image1.filterTransformation("mat", "mat-blur", "blur");
      image1.filterTransformation("mat-blur", "mat-blur-sharpen", "sharpen");
      Image img = image1.getImage("mat-blur-sharpen");
      Utility.writeImage(img, "test/res/mat-blur-sharpen.ppm");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat-blur-sharpen.ppm");
      String dataString = "[[[144, 188, 0], [235, 255, 0], [239, 178, 11], [155, 57, 0]]," +
              " [[221, 255, 28], [255, 255, 89], [255, 255, 102], [212, 92, 50]]," +
              " [[126, 236, 122], [185, 255, 225], [161, 239, 230], [91, 92, 130]], " +
              "[[24, 116, 109], [41, 191, 178], [20, 143, 178], [0, 61, 103]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testModelBlurDitherImage() {
    try {
      // using the image1 that is used in the setup
      image1.filterTransformation("mat", "mat-blur", "blur");
      image1.filterTransformation("mat-blur", "mat-blur-dither", "dither");
      Image img = image1.getImage("mat-blur-dither");
      Utility.writeImage(img, "test/res/mat-blur-dither.ppm");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat-blur-dither.ppm");
      String dataString = "[[[0, 0, 0], [255, 255, 255], [0, 0, 0], [0, 0, 0]], " +
              "[[255, 255, 255], [255, 255, 255], [0, 0, 0], [0, 0, 0]], " +
              "[[255, 255, 255], [255, 255, 255], [0, 0, 0], [0, 0, 0]], " +
              "[[0, 0, 0], [0, 0, 0], [0, 0, 0], [0, 0, 0]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testModelSharpenDitherImage() {
    try {
      // using the image1 that is used in the setup
      image1.filterTransformation("mat", "mat-sharpen", "sharpen");
      image1.filterTransformation("mat-sharpen", "mat-sharpen-dither", "dither");
      Image img = image1.getImage("mat-sharpen-dither");
      Utility.writeImage(img, "test/res/mat-sharpen-dither.ppm");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat-sharpen-dither.ppm");
      String dataString = "[[[255, 255, 255], [255, 255, 255], [255, 255, 255], [0, 0, 0]]," +
              " [[255, 255, 255], [255, 255, 255], [255, 255, 255], [0, 0, 0]], " +
              "[[255, 255, 255], [255, 255, 255], [255, 255, 255], [0, 0, 0]], " +
              "[[255, 255, 255], [255, 255, 255], [255, 255, 255], [0, 0, 0]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testModelSepiaDitherImage() {
    try {
      // using the image1 that is used in the setup
      image1.colorTransformation("mat", "mat-sepia", "sepia");
      image1.filterTransformation("mat-sepia", "mat-sepia-dither", "dither");
      Image img = image1.getImage("mat-sepia-dither");
      Utility.writeImage(img, "test/res/mat-sepia-dither.ppm");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat-sepia-dither.ppm");
      String dataString = "[[[255, 255, 255], [255, 255, 255], [255, 255, 255], " +
              "[255, 255, 255]], [[255, 255, 255], [255, 255, 255], [0, 0, 0], " +
              "[0, 0, 0]], [[255, 255, 255], [0, 0, 0], [255, 255, 255], [0, 0, 0]], " +
              "[[255, 255, 255], [0, 0, 0], [255, 255, 255], [0, 0, 0]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testAllMethodsBlurSharpenSepiaGrayScaleDither() {
    try {
      // using the image1 that is used in the setup
      image1.filterTransformation("mat", "mat-blur", "blur");
      image1.filterTransformation("mat-blur", "mat-blur-sharpen", "sharpen");
      image1.colorTransformation("mat-blur-sharpen", "mat-blur-sharpen-sepia", "sepia");
      image1.grayscale("mat-blur-sharpen-sepia", "mat-blur-sharpen-sepia-gray");
      image1.filterTransformation("mat-blur-sharpen-sepia-gray",
              "mat-blur-sharpen-sepia-gray-dither", "dither");

      Image img = image1.getImage("mat-blur-sharpen-sepia-gray-dither");
      Utility.writeImage(img, "test/res/mat-blur-sharpen-sepia-gray-dither.ppm");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat-" +
              "blur-sharpen-sepia-gray-dither.ppm");
      String dataString = "[[[255, 255, 255], [255, 255, 255], [255, 255, 255], [0, 0, 0]], " +
              "[[255, 255, 255], [255, 255, 255], [255, 255, 255], [255, 255, 255]], " +
              "[[255, 255, 255], [255, 255, 255], [255, 255, 255], [0, 0, 0]], [[0, 0, 0], " +
              "[255, 255, 255], [255, 255, 255], [0, 0, 0]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testAllTypesOfTransformations() {
    try {
      // using the image1 that is used in the setup
      image1.grayscale("mat", "mat-gray");
      image1.colorTransformation("mat-gray", "mat-gray-sepia", "sepia");

      Image img = image1.getImage("mat-gray-sepia");
      Utility.writeImage(img, "test/res/mat-gray-sepia.ppm");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat-gray-sepia.ppm");
      String dataString = "[[[255, 255, 215], [255, 255, 215], [255, 255, 215], " +
              "[209, 186, 145]], [[255, 255, 215], [216, 192, 149], [54, 48, 37], " +
              "[56, 50, 39]], [[255, 255, 215], [125, 111, 87], [255, 255, 199], " +
              "[117, 104, 81]], [[206, 184, 143], [124, 110, 86], " +
              "[164, 146, 114], [94, 84, 65]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testAllTypesOfFilters() {
    try {
      // using the image1 that is used in the setup
      image1.filterTransformation("mat", "mat-blur", "blur");
      image1.filterTransformation("mat-blur", "mat-blur-sharpen", "sharpen");

      Image img = image1.getImage("mat-blur-sharpen");
      Utility.writeImage(img, "test/res/mat-blur-sharpen.ppm");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat-blur-sharpen.ppm");
      String dataString = "[[[144, 188, 0], [235, 255, 0], [239, 178, 11], " +
              "[155, 57, 0]], [[221, 255, 28], [255, 255, 89], [255, 255, 102], " +
              "[212, 92, 50]], [[126, 236, 122], [185, 255, 225], [161, 239, 230], " +
              "[91, 92, 130]], [[24, 116, 109], [41, 191, 178], " +
              "[20, 143, 178], [0, 61, 103]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }


  @Test
  public void testAllMethodsBlurSharpenSepiaExceptDither() {
    try {
      // using the image1 that is used in the setup
      image1.filterTransformation("mat", "mat-blur", "blur");
      image1.filterTransformation("mat-blur", "mat-blur-sharpen", "sharpen");
      image1.colorTransformation("mat-blur-sharpen", "mat-blur-sharpen-sepia", "sepia");
      Image img = image1.getImage("mat-blur-sharpen-sepia");
      Utility.writeImage(img, "test/res/mat-blur-sharpen-sepia.ppm");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat-blur-sharpen-sepia.ppm");
      String dataString = "[[[201, 179, 139], [255, 255, 200], [232, 207, 161], " +
              "[104, 93, 72]], [[255, 255, 199], [255, 255, 217], [255, 255, 218]," +
              " [163, 145, 113]], [[254, 226, 176], [255, 255, 215], [255, 255, 201], " +
              "[131, 116, 90]], [[119, 106, 82], [196, 175, 136]," +
              " [151, 134, 105], [66, 59, 46]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }


  @Test
  public void testModelPPMtoPNG() {
    try {
      // using the image1 that is used in the setup

      image1.filterTransformation("mat", "mat_blur", "blur");
      Image img = image1.getImage("mat_blur");
      Utility.writeImage(img, "test/res/mat_blur.png");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat_blur.png");
      String dataString = "[[[113, 141, 6], [138, 170, 13], [146, 128, 14], [116, 69, 15]]," +
              " [[134, 175, 24], [143, 191, 54], [152, 126, 54], [126, 62, 42]], " +
              "[[98, 150, 65], [97, 163, 108], [90, 130, 104], [71, 74, 73]]," +
              " [[35, 100, 82], [35, 115, 104], [28, 106, 101], [20, 66, 79]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testModelPPMtoJPG() {
    try {
      // using the image1 that is used in the setup
      image1.colorTransformation("mat", "mat_sepia", "sepia");
      Image img = image1.getImage("mat_sepia");
      Utility.writeImage(img, "test/res/mat_sepia.jpg");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat_sepia.jpg");
      String dataString = "[[[255, 249, 210], [255, 254, 215], [245, 235, 199], " +
              "[210, 200, 164]], [[255, 252, 213], [186, 177, 138], [72, 62, 26], " +
              "[80, 70, 34]], [[252, 243, 204], [138, 129, 90], [255, 246, 211], " +
              "[104, 94, 59]], [[181, 172, 133], [117, 108, 69], " +
              "[143, 133, 98], [92, 82, 47]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testModelPPMtoJPEG() {
    try {
      // using the image1 that is used in the setup
      image1.filterTransformation("mat", "mat_dither", "dither");
      Image img = image1.getImage("mat_dither");
      Utility.writeImage(img, "test/res/mat_dither.jpeg");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat_dither.jpeg");
      String dataString = "[[[255, 255, 255], [255, 255, 255], [255, 255, 255], " +
              "[255, 255, 255]], [[246, 246, 246], [255, 255, 255], " +
              "[0, 0, 0], [0, 0, 0]], [[255, 255, 255], [0, 0, 0], " +
              "[255, 255, 255], [7, 7, 7]], [[252, 252, 252], " +
              "[0, 0, 0], [16, 16, 16], [0, 0, 0]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testModelPPMtoBMP() {
    try {
      // using the image1 that is used in the setup
      image1.grayscale("mat", "mat_gray");
      Image img = image1.getImage("mat_gray");
      Utility.writeImage(img, "test/res/mat_gray.bmp");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat_gray.bmp");
      String dataString = "[[[230, 230, 230], [230, 230, 230], [230, 230, 230], " +
              "[155, 155, 155]], [[230, 230, 230], [160, 160, 160], [40, 40, 40]," +
              " [42, 42, 42]], [[230, 230, 230], [93, 93, 93], [213, 213, 213]," +
              " [87, 87, 87]], [[153, 153, 153], [92, 92, 92]," +
              " [122, 122, 122], [70, 70, 70]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testModelPNGtoPPM() {
    try {
      // using the image1 that is used in the setup
      int[][][] imgData = Utility.readImage("test/res/mat_test.png");
      image1.setImage("mat", imgData);
      image1.grayscale("mat", "mat_gray");
      Image img = image1.getImage("mat_gray");
      Utility.writeImage(img, "test/res/mat_gray.ppm");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat_gray.ppm");
      String dataString = "[[[230, 230, 230], [230, 230, 230], [230, 230, 230]," +
              " [155, 155, 155]], [[230, 230, 230], [160, 160, 160], [40, 40, 40]," +
              " [42, 42, 42]], [[230, 230, 230], [93, 93, 93], [213, 213, 213]," +
              " [87, 87, 87]], [[153, 153, 153], [92, 92, 92]," +
              " [122, 122, 122], [70, 70, 70]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testModelJPGtoPPM() {
    try {
      // using the image1 that is used in the setup
      int[][][] imgData = Utility.readImage("test/res/mat_test.jpg");
      image1.setImage("mat", imgData);
      image1.filterTransformation("mat", "mat_dither", "dither");
      Image img = image1.getImage("mat_dither");
      Utility.writeImage(img, "test/res/mat_dither.ppm");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat_dither.ppm");
      String dataString = "[[[255, 255, 255], [255, 255, 255], [255, 255, 255]," +
              " [255, 255, 255]], [[255, 255, 255], [255, 255, 255], [0, 0, 0], " +
              "[0, 0, 0]], [[255, 255, 255], [0, 0, 0], [255, 255, 255], " +
              "[0, 0, 0]], [[255, 255, 255], [0, 0, 0], [255, 255, 255], [0, 0, 0]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testModelJPEGtoPPM() {
    try {
      // using the image1 that is used in the setup
      int[][][] imgData = Utility.readImage("test/res/mat_test.jpeg");
      image1.setImage("mat", imgData);
      image1.filterTransformation("mat", "mat_sharpen", "sharpen");
      Image img = image1.getImage("mat_sharpen");
      Utility.writeImage(img, "test/res/mat_sharpen.ppm");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat_sharpen.ppm");
      String dataString = "[[[255, 255, 63], [255, 255, 79], [255, 223, 52], [252, 143, 35]], " +
              "[[255, 255, 128], [255, 255, 159], [255, 219, 65], [220, 133, 63]], " +
              "[[152, 255, 211], [142, 255, 217], [107, 243, 213], [31, 120, 135]], " +
              "[[28, 194, 146], [47, 253, 203], [35, 197, 200], [27, 138, 150]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testModelBMPtoPPM() {
    try {
      // using the image1 that is used in the setup
      int[][][] imgData = Utility.readImage("test/res/mat_test.bmp");
      image1.setImage("mat", imgData);
      image1.colorTransformation("mat", "mat_sepia", "sepia");
      Image img = image1.getImage("mat_sepia");
      Utility.writeImage(img, "test/res/mat_sepia.ppm");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat_sepia.ppm");
      String dataString = "[[[255, 253, 197], [255, 253, 197], [255, 253, 197], " +
              "[203, 181, 141]], [[255, 253, 197], [184, 164, 127]," +
              " [74, 66, 51], [85, 76, 59]], [[255, 253, 197]," +
              " [140, 124, 97], [255, 230, 179], [116, 103, 80]], " +
              "[[190, 169, 132], [111, 99, 77]," +
              " [148, 132, 102], [97, 86, 67]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testModelBMPtoPNG() {
    try {
      // using the image1 that is used in the setup
      int[][][] imgData = Utility.readImage("test/res/mat_test.bmp");
      image1.setImage("mat", imgData);
      image1.colorTransformation("mat", "mat_sepia", "sepia");
      Image img = image1.getImage("mat_sepia");
      Utility.writeImage(img, "test/res/mat_sepia.png");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat_sepia.png");
      String dataString = "[[[255, 253, 197], [255, 253, 197], [255, 253, 197], " +
              "[203, 181, 141]], [[255, 253, 197], [184, 164, 127], [74, 66, 51], " +
              "[85, 76, 59]], [[255, 253, 197], [140, 124, 97], [255, 230, 179]," +
              " [116, 103, 80]], [[190, 169, 132], [111, 99, 77], " +
              "[148, 132, 102], [97, 86, 67]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testModelBMPtoJPG() {
    try {
      // using the image1 that is used in the setup
      int[][][] imgData = Utility.readImage("test/res/mat_test.bmp");
      image1.setImage("mat", imgData);
      image1.colorTransformation("mat", "mat_sepia", "sepia");
      Image img = image1.getImage("mat_sepia");
      Utility.writeImage(img, "test/res/mat_sepia.jpg");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat_sepia.jpg");
      String dataString = "[[[255, 249, 210], [255, 254, 215], [245, 235, 199], " +
              "[210, 200, 164]], [[255, 252, 213], [186, 177, 138], [72, 62, 26]," +
              " [80, 70, 34]], [[252, 243, 204], [138, 129, 90], [255, 246, 211]," +
              " [104, 94, 59]], [[181, 172, 133], [117, 108, 69], " +
              "[143, 133, 98], [92, 82, 47]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testModelBMPtoJPEG() {
    try {
      // using the image1 that is used in the setup
      int[][][] imgData = Utility.readImage("test/res/mat_test.bmp");
      image1.setImage("mat", imgData);
      image1.colorTransformation("mat", "mat_sepia", "sepia");
      Image img = image1.getImage("mat_sepia");
      Utility.writeImage(img, "test/res/mat_sepia.jpeg");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat_sepia.jpeg");
      String dataString = "[[[255, 249, 210], [255, 254, 215], [245, 235, 199], " +
              "[210, 200, 164]], [[255, 252, 213], [186, 177, 138], [72, 62, 26], " +
              "[80, 70, 34]], [[252, 243, 204], [138, 129, 90], [255, 246, 211], " +
              "[104, 94, 59]], [[181, 172, 133], [117, 108, 69], [143, 133, 98], [92, 82, 47]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }


  @Test
  public void testModelPNGtoBMP() {
    try {
      // using the image1 that is used in the setup
      int[][][] imgData = Utility.readImage("test/res/mat_test.png");
      image1.setImage("mat", imgData);
      image1.colorTransformation("mat", "mat_sepia", "sepia");
      Image img = image1.getImage("mat_sepia");
      Utility.writeImage(img, "test/res/mat_sepia.bmp");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat_sepia.bmp");
      String dataString = "[[[255, 253, 197], [255, 253, 197], [255, 253, 197], " +
              "[203, 181, 141]], [[255, 253, 197], [184, 164, 127], [74, 66, 51], " +
              "[85, 76, 59]], [[255, 253, 197], [140, 124, 97], [255, 230, 179]," +
              " [116, 103, 80]], [[190, 169, 132], [111, 99, 77]," +
              " [148, 132, 102], [97, 86, 67]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testModelPNGtoJPG() {
    try {
      // using the image1 that is used in the setup
      int[][][] imgData = Utility.readImage("test/res/mat_test.png");
      image1.setImage("mat", imgData);
      image1.colorTransformation("mat", "mat_sepia", "sepia");
      Image img = image1.getImage("mat_sepia");
      Utility.writeImage(img, "test/res/mat_sepia.jpg");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat_sepia.jpg");
      String dataString = "[[[255, 249, 210], [255, 254, 215], [245, 235, 199], " +
              "[210, 200, 164]], [[255, 252, 213], [186, 177, 138], [72, 62, 26]," +
              " [80, 70, 34]], [[252, 243, 204], [138, 129, 90], [255, 246, 211]," +
              " [104, 94, 59]], [[181, 172, 133], [117, 108, 69], " +
              "[143, 133, 98], [92, 82, 47]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testModelPNGtoJPEG() {
    try {
      // using the image1 that is used in the setup
      int[][][] imgData = Utility.readImage("test/res/mat_test.png");
      image1.setImage("mat", imgData);
      image1.colorTransformation("mat", "mat_sepia", "sepia");
      Image img = image1.getImage("mat_sepia");
      Utility.writeImage(img, "test/res/mat_sepia.jpeg");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat_sepia.jpeg");
      String dataString = "[[[255, 249, 210], [255, 254, 215], [245, 235, 199], " +
              "[210, 200, 164]], [[255, 252, 213], [186, 177, 138], [72, 62, 26], " +
              "[80, 70, 34]], [[252, 243, 204], [138, 129, 90], [255, 246, 211], " +
              "[104, 94, 59]], [[181, 172, 133], [117, 108, 69], " +
              "[143, 133, 98], [92, 82, 47]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testModelJPGtoBMP() {
    try {
      // using the image1 that is used in the setup
      int[][][] imgData = Utility.readImage("test/res/mat_test.jpg");
      image1.setImage("mat", imgData);
      image1.colorTransformation("mat", "mat_sepia", "sepia");
      Image img = image1.getImage("mat_sepia");
      Utility.writeImage(img, "test/res/mat_sepia.bmp");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat_sepia.bmp");
      String dataString = "[[[255, 255, 201], [255, 251, 195], [255, 248, 193], " +
              "[226, 201, 157]], [[255, 254, 198], [179, 159, 124], [87, 77, 60]," +
              " [74, 66, 51]], [[255, 255, 205], [129, 115, 89], [255, 239, 186], " +
              "[107, 95, 74]], [[183, 163, 127], [90, 80, 62]," +
              " [173, 154, 120], [77, 69, 53]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (ValidationException e) {
      System.out.println("Error: Invalid Operation");
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }


  @Test
  public void testValidHistogramFist20Values() {
    try {
      int[][][] imgData = Utility.readImage("test/res/mat_test.jpg");
      image1.setImage("mat", imgData);
      image1.histogram("mat", "mat-hist");
      int[][] output = image1.getHist("mat-hist");
      int[][] test = new int[20][output[0].length];
      for (int i = 0; i < 20; i++) {
        test[i] = Arrays.copyOf(output[i], output[i].length);
      }
      String dataString = "[[0, 0, 2, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0]," +
              " [1, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0]," +
              " [0, 0, 1, 0], [0, 0, 0, 0], [1, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0]," +
              " [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0]," +
              " [0, 0, 0, 0], [0, 0, 0, 0]]";
      assertEquals(dataString, Arrays.deepToString(test));
    } catch (ValidationException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }

  @Test
  public void testModelInvalidHistogramGetHist() {
    try {
      int[][][] imgData = Utility.readImage("test/res/mat_test.jpg");
      image1.setImage("mat", imgData);
      image1.histogram("mat", "mat-hist");
      int[][] output = image1.getHist("mat-hist2");
    } catch (ValidationException e) {
      assertEquals("exception.ValidationException: " +
              "The histogram mat-hist2 does not exist", e.toString());
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }


  @Test
  public void testModelInvalidHistogramImagePassed() {
    try {
      int[][][] imgData = Utility.readImage("test/res/mat_test.jpg");
      image1.setImage("mat", imgData);
      image1.histogram("mat-hist", "mat");
    } catch (ValidationException e) {
      assertEquals("exception.ValidationException: " +
              "The image mat-hist does not exist", e.toString());
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    }
  }
}

