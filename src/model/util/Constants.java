package model.util;

/**
 * The Constants class contains commonly used constant values in the application.
 */
public class Constants {

  public static final double[][] SEPIA_COLOR_TRANSFORM_MATRIX = {
          {0.393, 0.769, 0.189},
          {0.349, 0.686, 0.168},
          {0.272, 0.534, 0.131}
  };

  public static final double[][] GREYSCALE_LUMA_TRANSFORM_MATRIX = {
          {0.2126, 0.7152, 0.0722},
          {0.2126, 0.7152, 0.0722},
          {0.2126, 0.7152, 0.0722}
  };

  public static final double[][] GREYSCALE_IDENTITY_TRANSFORM_MATRIX = {
          {1, 0, 0},
          {0, 1, 0},
          {0, 0, 1}
  };

  public static final double[][] GREYSCALE_INTENSITY_TRANSFORM_MATRIX = {
          {0.33333, 0.33333, 0.33333},
          {0.33333, 0.33333, 0.33333},
          {0.33333, 0.33333, 0.33333}
  };

  public static final double[][] GREYSCALE_RED_TRANSFORM_MATRIX = {
          {1, 0, 0},
          {1, 0, 0},
          {1, 0, 0}
  };

  public static final double[][] GREYSCALE_GREEN_TRANSFORM_MATRIX = {
          {0, 1, 0},
          {0, 1, 0},
          {0, 1, 0}
  };

  public static final double[][] GREYSCALE_BLUE_TRANSFORM_MATRIX = {
          {0, 0, 1},
          {0, 0, 1},
          {0, 0, 1}
  };

  public static final double[][] BLUR_FILTER_KERNEL = {
          {0.0625, 0.125, 0.0625},
          {0.125, 0.25, 0.125},
          {0.0625, 0.125, 0.0625}
  };

  public static final double[][] SHARPEN_FILTER_KERNEL = {
          {-0.125, -0.125, -0.125, -0.125, -0.125},
          {-0.125, 0.25, 0.25, 0.25, -0.125},
          {-0.125, 0.25, 1, 0.25, -0.125},
          {-0.125, 0.25, 0.25, 0.25, -0.125},
          {-0.125, -0.125, -0.125, -0.125, -0.125}
  };

}
