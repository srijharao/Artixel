package view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * JUnit test class for ImageModelImpl.
 */
public class ImageViewImplTest {

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  @Before
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
  }

  @After
  public void restoreStreams() {
    System.setOut(originalOut);
  }

  @Test
  public void testShowStatusLoad() {
    ImageView view = new ImageViewImpl();

    // Test load operation
    view.showStatus("test-image.ppm", "load");
    assertEquals("Image test-image.ppm Loaded Successfully\n", systemOut());
  }

  @Test
  public void testShowStatusBrighten() {
    ImageView view = new ImageViewImpl();

    // Test brighten operation
    view.showStatus("test-image.ppm", "brighten");
    assertEquals("Image test-image.ppm Brightend Successfully\n", systemOut());
  }

  @Test
  public void testShowStatusHflip() {
    ImageView view = new ImageViewImpl();

    view.showStatus("test-image.ppm", "horizontal-flip");
    assertEquals("Image test-image.ppm flipped Successfully\n", systemOut());
  }

  @Test
  public void testShowStatusVFlip() {
    ImageView view = new ImageViewImpl();

    view.showStatus("test-image.ppm", "vertical-flip");
    assertEquals("Image test-image.ppm flipped Successfully\n", systemOut());
  }

  @Test
  public void testShowStatusSplit() {
    ImageView view = new ImageViewImpl();

    // Test rgb-split operation
    view.showStatus("test-image.ppm", "rgb-split");
    assertEquals("Image test-image.ppm split Successfully\n", systemOut());
  }

  @Test
  public void testShowStatusRGBCombine() {
    ImageView view = new ImageViewImpl();

    // Test rgb-combine operation
    view.showStatus("test-image.ppm", "rgb-combine");
    assertEquals("Image test-image.ppm combined Successfully\n", systemOut());
  }

  @Test
  public void testShowStatusGrayscale() {
    ImageView view = new ImageViewImpl();

    // Test greyscale operation
    view.showStatus("test-image.ppm", "greyscale");
    assertEquals("greyscale test-image.ppm created Successfully\n", systemOut());
  }

  @Test
  public void testShowStatusSepia() {
    ImageView view = new ImageViewImpl();

    // Test brighten operation
    view.showStatus("test-image.ppm", "sepia");
    assertEquals("Image test-image.ppm sepia Successfully\n", systemOut());
  }

  @Test
  public void testShowStatusDither() {
    ImageView view = new ImageViewImpl();

    // Test brighten operation
    view.showStatus("test-image.ppm", "dither");
    assertEquals("Image test-image.ppm dither Successfully\n", systemOut());
  }

  @Test
  public void testShowStatusBlur() {
    ImageView view = new ImageViewImpl();

    // Test brighten operation
    view.showStatus("test-image.ppm", "blur");
    assertEquals("Image test-image.ppm blurred Successfully\n", systemOut());
  }

  @Test
  public void testShowStatusSharpen() {
    ImageView view = new ImageViewImpl();

    // Test sharpen operation
    view.showStatus("test-image.ppm", "sharpen");
    assertEquals("Image test-image.ppm sharpened Successfully\n", systemOut());
  }

  @Test
  public void testShowStatusSave() {
    ImageView view = new ImageViewImpl();

    // Test save operation
    view.showStatus("test-image.ppm", "save");
    assertEquals("Image test-image.ppm saved Successfully\n", systemOut());
  }

  @Test
  public void testShowStatusRun() {
    ImageView view = new ImageViewImpl();

    // Test run operation
    view.showStatus("test-script.txt", "run");
    assertEquals("Script test-script.txt ran Successfully\n", systemOut());
  }


  private String systemOut() {
    return outContent.toString();
  }
}
