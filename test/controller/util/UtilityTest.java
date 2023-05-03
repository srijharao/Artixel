package controller.util;

import exception.ValidationException;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import model.image.Image;
import model.image.RgbImage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * The UtilityTest class provides test cases for the Utility class.
 */
public class UtilityTest {

  @Test
  public void testFileDoesNotExistReadFromPPM() {
    assertThrows("File res/new_file not found!",
        IOException.class, () -> Utility.readImage("res/new_file.txt"));
  }

  @Test
  public void testNullFileReadFromPPM() {
    assertThrows(NullPointerException.class, () -> Utility.readImage(null));
  }

  @Test
  public void testReadFromPPM() throws IOException {
    int[][][] image = Utility.readImage("res/mat.ppm");
    String expectedString = "[[[225, 255, 0], [225, 255, 0], [225, 255, 0], [225, 150, 0]], " +
            "[[225, 255, 0], [0, 215, 100], [190, 0, 0], [160, 0, 120]], [[225, 255, 0], " +
            "[111, 79, 190], [118, 255, 88], [99, 88, 54]], [[0, 192, 226], " +
            "[0, 118, 110], [0, 156, 150], [0, 80, 190]]]";
    assertEquals(expectedString, Arrays.deepToString(image));
  }

  @Test
  public void testWriteToPPM() throws IOException, ValidationException {
    int[][][] imageArray = Utility.readImage("res/mat.ppm");
    Image image = new RgbImage(imageArray);
    Utility.writeImage(image, "res/mat_test.ppm");
    int[][][] imageArrayPostWriting = Utility.readImage("res/mat_test.ppm");
    assertEquals(imageArray, imageArrayPostWriting);
  }

  @Test
  public void testFileDoesNotExistReadReadImage() {
    assertThrows("File res/new_file not found!",
        IOException.class, () -> Utility.readImage("res/new_file.txt"));
  }

  @Test
  public void testNullFileReadImage() {
    assertThrows(NullPointerException.class, () -> Utility.readImage(null));
  }

  @Test
  public void testReadImageJPG() throws IOException {
    int[][][] image = Utility.readImage("res/mat-blur.jpg");
    String expectedString = "[[[134, 127, 23], [154, 147, 43], [142, 117, 35], [104, 79, 0]], "
        + "[[166, 159, 55], [179, 172, 68], [157, 132, 50], [111, 86, 4]], [[84, 149, 107], "
        + "[98, 163, 121], [79, 126, 106], [38, 85, 65]], [[34, 99, 57], "
        + "[55, 120, 78], [49, 96, 76], [21, 68, 48]]]";
    assertEquals(expectedString, Arrays.deepToString(image));
  }


  @Test
  public void testReadImageBMP() throws IOException {
    int[][][] image = Utility.readImage("test/res/mat_test.bmp");
    String expectedString = "[[[134, 127, 23], [154, 147, 43], [142, 117, 35], " +
            "[104, 79, 0]], [[166, 159, 55], [179, 172, 68], [157, 132, 50], " +
            "[111, 86, 4]], [[84, 149, 107], [98, 163, 121], [79, 126, 106], " +
            "[38, 85, 65]], [[34, 99, 57], [55, 120, 78], [49, 96, 76], [21, 68, 48]]]";
    assertEquals(expectedString, Arrays.deepToString(image));
  }

  @Test
  public void testReadImageJPEG() throws IOException {
    int[][][] image = Utility.readImage("test/res/mat_test.jpeg");
    String expectedString = "[[[247, 229, 91], [241, 223, 85], [255, 207, 105], " +
        "[216, 168, 66]], [[244, 226, 88], [165, 147, 9], [107, 59, 0], [96, 48, 0]], " +
        "[[158, 255, 203], [33, 132, 78], [153, 222, 204], [33, 102, 84]], " +
        "[[73, 172, 118], [4, 103, 49], [82, 151, 133], [11, 80, 62]]]";
    assertEquals(expectedString, Arrays.deepToString(image));
  }

  @Test
  public void testReadImagePNG() throws IOException {
    int[][][] image = Utility.readImage("test/res/mat_test.png");
    String expectedString = "[[[225, 255, 0], [225, 255, 0], [225, 255, 0]," +
        " [225, 150, 0]], [[225, 255, 0], [0, 215, 100], [190, 0, 0]," +
        " [160, 0, 120]], [[225, 255, 0], [111, 79, 190], [118, 255, 88], " +
        "[99, 88, 54]], [[0, 192, 226], [0, 118, 110]," +
        " [0, 156, 150], [0, 80, 190]]]";
    assertEquals(expectedString, Arrays.deepToString(image));
  }


  @Test
  public void testFileDoesNotExistReadImageInvalid() {
    assertThrows("File res/new_file not found!",
        IOException.class, () -> Utility.readImage("res/new_file.txt"));
  }

  @Test
  public void testWriteImage() throws IOException, ValidationException {
    int[][][] imageArray = Utility.readImage("res/mat-blur.jpg");
    Image image = new RgbImage(imageArray);
    Utility.writeImage(image, "test/res/mat_test.bmp");
    int[][][] imageArrayPostWriting = Utility.readImage("test/res/mat_test.bmp");
    assertEquals(imageArray, imageArrayPostWriting);
  }


  @Test
  public void testUtilityPPMtoPNG() {
    try {
      int[][][] imageArray = Utility.readImage("test/res/mat.ppm");
      Image image = new RgbImage(imageArray);
      Utility.writeImage(image, "test/res/mat_test.png");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat_test.png");
      String dataString = "[[[225, 255, 0], [225, 255, 0], [225, 255, 0], [225, 150, 0]], " +
          "[[225, 255, 0], [0, 215, 100], [190, 0, 0], [160, 0, 120]], " +
          "[[225, 255, 0], [111, 79, 190], [118, 255, 88], [99, 88, 54]], " +
          "[[0, 192, 226], [0, 118, 110], [0, 156, 150], [0, 80, 190]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    } catch (ValidationException e) {
      throw new RuntimeException(e);
    }
  }


  @Test
  public void testUtilityPPMtoJPG() {
    try {
      int[][][] imageArray = Utility.readImage("test/res/mat.ppm");
      Image image = new RgbImage(imageArray);
      Utility.writeImage(image, "test/res/mat_test.jpg");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat_test.jpg");
      String dataString = "[[[247, 229, 91], [241, 223, 85], [255, 207, 105], [216, 168, 66]], " +
          "[[244, 226, 88], [165, 147, 9], [107, 59, 0], [96, 48, 0]], " +
          "[[158, 255, 203], [33, 132, 78], [153, 222, 204], [33, 102, 84]], " +
          "[[73, 172, 118], [4, 103, 49], [82, 151, 133], [11, 80, 62]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    } catch (ValidationException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testUtilityPPMtoJPEG() {
    try {
      int[][][] imageArray = Utility.readImage("test/res/mat.ppm");
      // using the image1 that is used in the setup
      Image image = new RgbImage(imageArray);
      Utility.writeImage(image, "test/res/mat_test.jpeg");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat_test.jpeg");
      String dataString = "[[[247, 229, 91], [241, 223, 85], [255, 207, 105], [216, 168, 66]], " +
          "[[244, 226, 88], [165, 147, 9], [107, 59, 0], [96, 48, 0]], " +
          "[[158, 255, 203], [33, 132, 78], [153, 222, 204], [33, 102, 84]], " +
          "[[73, 172, 118], [4, 103, 49], [82, 151, 133], [11, 80, 62]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    } catch (ValidationException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testUtilityPPMtoBMP() {
    try {
      int[][][] imageArray = Utility.readImage("test/res/mat.ppm");
      // using the image1 that is used in the setup
      Image image = new RgbImage(imageArray);
      Utility.writeImage(image, "test/res/mat_test.bmp");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat_test.bmp");
      String dataString = "[[[225, 255, 0], [225, 255, 0], [225, 255, 0], [225, 150, 0]], " +
          "[[225, 255, 0], [0, 215, 100], [190, 0, 0], [160, 0, 120]], " +
          "[[225, 255, 0], [111, 79, 190], [118, 255, 88], [99, 88, 54]], " +
          "[[0, 192, 226], [0, 118, 110], [0, 156, 150], [0, 80, 190]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    } catch (ValidationException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testUtilityPNGtoPPM() {
    try {
      // using the image1 that is used in the setup
      int[][][] imgData = Utility.readImage("test/res/mat_test.png");
      Image image = new RgbImage(imgData);
      Utility.writeImage(image, "test/res/mat_test.ppm");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat_test.ppm");
      String dataString = "[[[225, 255, 0], [225, 255, 0], [225, 255, 0], [225, 150, 0]], " +
          "[[225, 255, 0], [0, 215, 100], [190, 0, 0], [160, 0, 120]], " +
          "[[225, 255, 0], [111, 79, 190], [118, 255, 88], [99, 88, 54]], " +
          "[[0, 192, 226], [0, 118, 110], [0, 156, 150], [0, 80, 190]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    } catch (ValidationException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testUtilityJPGtoPPM() {
    try {
      // using the image1 that is used in the setup
      int[][][] imgData = Utility.readImage("test/res/mat_test.jpg");
      Image image = new RgbImage(imgData);
      Utility.writeImage(image, "test/res/mat_test.ppm");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat_test.ppm");
      String dataString = "[[[247, 229, 91], [241, 223, 85], [255, 207, 105], [216, 168, 66]], " +
          "[[244, 226, 88], [165, 147, 9], [107, 59, 0], [96, 48, 0]], [[158, 255, 203], " +
          "[33, 132, 78], [153, 222, 204], [33, 102, 84]], [[73, 172, 118], " +
          "[4, 103, 49], [82, 151, 133], [11, 80, 62]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    } catch (ValidationException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testUtilityJPEGtoPPM() {
    try {
      // using the image1 that is used in the setup
      int[][][] imgData = Utility.readImage("test/res/mat_test.jpeg");
      Image image = new RgbImage(imgData);
      Utility.writeImage(image, "test/res/mat_test.ppm");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat_test.ppm");
      String dataString = "[[[247, 229, 91], [241, 223, 85], [255, 207, 105], [216, 168, 66]], " +
          "[[244, 226, 88], [165, 147, 9], [107, 59, 0], [96, 48, 0]], [[158, 255, 203], " +
          "[33, 132, 78], [153, 222, 204], [33, 102, 84]], [[73, 172, 118], " +
          "[4, 103, 49], [82, 151, 133], [11, 80, 62]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    } catch (ValidationException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testUtilityBMPtoPPM() {
    try {
      // using the image1 that is used in the setup
      int[][][] imgData = Utility.readImage("test/res/mat_test.bmp");
      Image image = new RgbImage(imgData);
      Utility.writeImage(image, "test/res/mat_test.ppm");
      // read the saved file from above operation
      int[][][] savedImageOp = Utility.readImage("test/res/mat_test.ppm");
      String dataString = "[[[225, 255, 0], [225, 255, 0], [225, 255, 0], [225, 150, 0]]," +
          " [[225, 255, 0], [0, 215, 100], [190, 0, 0], [160, 0, 120]], " +
          "[[225, 255, 0], [111, 79, 190], [118, 255, 88], [99, 88, 54]], " +
          "[[0, 192, 226], [0, 118, 110], [0, 156, 150], [0, 80, 190]]]";

      assertEquals(dataString, Arrays.deepToString(savedImageOp));
    } catch (IOException e) {
      System.out.println("Error: Invalid File Operation");
    } catch (ValidationException e) {
      throw new RuntimeException(e);
    }
  }

}