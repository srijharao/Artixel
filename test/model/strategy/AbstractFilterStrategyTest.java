package model.strategy;

import org.junit.Before;
import org.junit.Test;

import model.IKernel;
import model.KernelImpl;
import model.image.Image;
import model.image.RgbImage;
import model.pixel.Channel;
import model.pixel.Pixel;
import model.pixel.PixelImpl;
import model.pixel.RgbColor;

import static org.junit.Assert.assertEquals;

/**
 * This class represents the test suite for the abstract FilterStrategy class.
 * It contains test cases to ensure the proper functionality of the concrete
 * classes that inherit from it.
 */
public class AbstractFilterStrategyTest {
  private Image image;
  private double[][] kernelMatrix;

  @Before
  public void setUp() {
    Pixel[][] pixels = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        pixels[i][j] = new PixelImpl(0, 0, new RgbColor(100, 100, 100));
      }
    }
    image = new RgbImage(pixels);
    kernelMatrix = new double[][]{
            {0.0, 0.2, 0.0}, {0.2, 0.2, 0.2}, {0.0, 0.2, 0.0}
    };
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidKernelDimension() {
    double[][] kernel = new double[][]{{1, 2}, {3, 4}, {5, 6}};
    IKernel invalidKernel = new KernelImpl(kernel);
    AbstractFilterStrategy filterStrategy = new TestFilterStrategy(invalidKernel);
  }

  @Test
  public void testFilterWithKernel() {
    IKernel kernel = new KernelImpl(kernelMatrix);
    AbstractFilterStrategy filterStrategy = new TestFilterStrategy(kernel);
    Image filteredImage = filterStrategy.doFilter(image);
    assertEquals(3, filteredImage.getImageWidth());
    assertEquals(3, filteredImage.getImageHeight());
    assertEquals(60, filteredImage.getPixelArray()[0][0]
            .getComponent(Channel.RED.ordinal()));
    assertEquals(60, filteredImage.getPixelArray()[0][0]
            .getComponent(Channel.GREEN.ordinal()));
    assertEquals(60, filteredImage.getPixelArray()[0][0]
            .getComponent(Channel.BLUE.ordinal()));
  }

  private static class TestFilterStrategy extends AbstractFilterStrategy {
    TestFilterStrategy(IKernel kernel) {
      super(kernel);
    }

    @Override
    public Image applyOperator(Image image) {
      return null;
    }
  }
}