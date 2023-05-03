package view;

/**
 * A mock implementation of the ImageView interface that logs all method calls to a
 * StringBuilder.
 */
public class MockImageView implements ImageView {
  //The StringBuilder used to viewOPLog method calls.
  private final StringBuilder viewOPLog;

  /**
   * Creates a new instance of the MockImageView class.
   */
  public MockImageView() {
    this.viewOPLog = new StringBuilder();
  }

  @Override
  public String toString() {
    return "" + viewOPLog;
  }

  @Override
  public void showStatus(String image, String operation) {

    viewOPLog.append("Operation performed\n");
  }
}
