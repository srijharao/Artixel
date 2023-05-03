package model.command;

import org.junit.Test;

import java.util.Arrays;

import exception.ValidationException;
import model.image.Image;
import model.image.RgbImage;

import static org.junit.Assert.assertEquals;

/**
 * The GreyScaleCommandTests class provides test cases for the GreyScaleCommandImpl class.
 */

public class GreyScaleCommandTests {

  private static final String FILENAME = "res/mat.ppm";

  private static final int[][][] IMAGE_ARRAY = {
          {{0, 0, 0}, {255, 255, 255}},
          {{255, 0, 0}, {0, 255, 0}}
  };

  @Test
  public void testExecuteGreyScaleCommandImpl() throws ValidationException {
    Image image = new RgbImage(IMAGE_ARRAY);
    GreyScaleCommandImpl greyScaleCommand = new GreyScaleCommandImpl();

    String expectedLumaOutput = "[[{x=0, y=0, Color[0, 0, 0]}, {x=0, y=1, Color[254, 254," +
            " 254]}], [{x=1, y=0, Color[54, 54, 54]}, {x=1, y=1, Color[182, 182, 182]}]]";
    Image actualLumaImage = greyScaleCommand.execute(image, "luma-component");
    assertEquals(expectedLumaOutput, Arrays.deepToString(actualLumaImage.getPixelArray()));

    String expectedValueOutput = "[[{x=0, y=0, Color[0, 0, 0]}, {x=0, y=1, Color[255, 255, " +
            "255]}], [{x=1, y=0, Color[255, 255, 255]}, {x=1, y=1, Color[255, 255, 255]}]]";
    Image actualValueImage = greyScaleCommand.execute(image, "value-component");
    assertEquals(expectedValueOutput, Arrays.deepToString(actualValueImage.getPixelArray()));

    String expectedIntensityOutput = "[[{x=0, y=0, Color[0, 0, 0]}, {x=0, y=1, Color[254, 254," +
            " 254]}], [{x=1, y=0, Color[84, 84, 84]}, {x=1, y=1, Color[84, 84, 84]}]]";
    Image actualIntensityImage = greyScaleCommand.execute(image, "intensity-component");
    assertEquals(expectedIntensityOutput,
            Arrays.deepToString(actualIntensityImage.getPixelArray()));

    String expectedRedOutput = "[[{x=0, y=0, Color[0, 0, 0]}, {x=0, y=1, Color[255, 255, " +
            "255]}], [{x=1, y=0, Color[255, 255, 255]}, {x=1, y=1, Color[0, 0, 0]}]]";
    Image actualRedImage = greyScaleCommand.execute(image, "red-component");
    assertEquals(expectedRedOutput, Arrays.deepToString(actualRedImage.getPixelArray()));

    String expectedBlueOutput = "[[{x=0, y=0, Color[0, 0, 0]}, {x=0, y=1, Color[255, 255, " +
            "255]}], [{x=1, y=0, Color[0, 0, 0]}, {x=1, y=1, Color[0, 0, 0]}]]";
    Image actualBlueImage = greyScaleCommand.execute(image, "blue-component");
    assertEquals(expectedBlueOutput, Arrays.deepToString(actualBlueImage.getPixelArray()));

    String expectedGreenOutput = "[[{x=0, y=0, Color[0, 0, 0]}, {x=0, y=1, Color[255, 255, " +
            "255]}], [{x=1, y=0, Color[0, 0, 0]}, {x=1, y=1, Color[255, 255, 255]}]]";
    Image actualGeenImage = greyScaleCommand.execute(image, "green-component");
    assertEquals(expectedGreenOutput, Arrays.deepToString(actualGeenImage.getPixelArray()));

  }

}
