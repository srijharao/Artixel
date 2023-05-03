package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import model.image.Image;
import model.pixel.Channel;
import model.pixel.Pixel;

/**
 * The ImageViewGUIImpl class implements the ImageViewGUI interface and extends the JFrame.
 * It represents the graphical user interface for displaying an image and its histogram.
 */
public class ImageViewGUIImpl extends JFrame implements ImageViewGUI {
  private final JLabel imageLabel;
  private final JLabel histogramLabel;
  private final JLabel statusLabel;
  private Consumer<String> commandCallback;
  private int isFlag;
  private int isSplitFlag = 0;
  private int isError = 0;

  /**
   * Constructs a new ImageViewGUIImpl object and initializes the GUI.
   */
  public ImageViewGUIImpl() {

    setTitle("IME: Image Manipulation and Enhancement");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    // Creating a file menu and add it to the menu bar
    JMenuBar menuBar = new JMenuBar();
    JMenu homeMenu = new JMenu("Home");
    JMenuItem clearMenuItem = new JMenuItem("Clear Image");
    homeMenu.add(clearMenuItem);
    menuBar.add(homeMenu);

    JMenu fileMenu = new JMenu("File");
    JMenuItem loadMenuItem = new JMenuItem("Load Image");
    JMenuItem saveMenuItem = new JMenuItem("Save Image");
    fileMenu.add(loadMenuItem);
    fileMenu.add(saveMenuItem);
    menuBar.add(fileMenu);

    // Create the operation menu and add it to the menu bar
    JMenu operationMenu = new JMenu("Operations");

    JMenuItem brightMenuItem = new JMenuItem("Brightness");
    JMenuItem hflipMenuItem = new JMenuItem("Horizontal Flip");
    JMenuItem vflipMenuItem = new JMenuItem("Vertical Flip");
    operationMenu.add(brightMenuItem);
    operationMenu.add(hflipMenuItem);
    operationMenu.add(vflipMenuItem);

    menuBar.add(operationMenu);

    JMenu filterMenu = new JMenu("Filters");
    JMenuItem blurMenuItem = new JMenuItem("Blur");
    JMenuItem sharpenMenuItem = new JMenuItem("Sharpen");
    JMenuItem ditherMenuItem = new JMenuItem("Dither");
    filterMenu.add(blurMenuItem);
    filterMenu.add(sharpenMenuItem);
    filterMenu.add(ditherMenuItem);

    menuBar.add(filterMenu);

    JMenu colorMenu = new JMenu("Colors");
    JMenuItem sepiaMenuItem = new JMenuItem("Sepia");
    JMenuItem grayscaleMenuItem = new JMenuItem("Grayscale");
    JMenuItem rgbSplitMenuItem = new JMenuItem("RGB Split");
    JMenuItem rgbCombineMenuItem = new JMenuItem("RGB Combine");
    colorMenu.add(sepiaMenuItem);
    colorMenu.add(grayscaleMenuItem);
    colorMenu.add(rgbSplitMenuItem);
    colorMenu.add(rgbCombineMenuItem);
    colorMenu.add(colorMenu);

    menuBar.add(colorMenu);
    JMenu aboutMenu = new JMenu("About");

    // Create About menu item and add ActionListener
    JMenuItem aboutMenuItem = new JMenuItem("About");
    aboutMenuItem.addActionListener(e -> {
      String message = "<html><body><p style='width: 300px;'>This is an application that" +
              " enables image manipulation and enhancement, " +
              "offering a variety of operations to brighten, darken, flip, blur, " +
              "sharpen, dither, sepia-tone, grayscale, split RGB components, " +
              "and combine them back over a selected image. It supports multiple " +
              "image types, including ASCII PPM, JPG, JPEG, BMP, and PNG.</p><br><br>" +
              "Developed by:<br>Rohit Sahoo and Srijha Thammareddy</body></html>";

      JOptionPane.showMessageDialog(null, message, "About",
              JOptionPane.INFORMATION_MESSAGE);
    });

    aboutMenu.add(aboutMenuItem);
    menuBar.add(aboutMenu);

    // Create Help menu item and add ActionListener
    JMenu helpMenu = new JMenu("Help");
    JMenuItem helpMenuItem = new JMenuItem("Help");
    helpMenuItem.addActionListener(e -> {
      String message = "<html><body><p style='width: 300px;'><b>How to use the application:" +
              "</b> <br>Load: To load an image in the application. <br> Save: To save the " +
              "current image from the application. <br> Clear: To clear the image panel. " +
              "<br> Brightness: To increase and decrease the brightness of an image. <br> " +
              "Horizontal Flip: To flip the image horizontally<br>Vertical Flip: To flip " +
              "the image Vertically. <br> Blur, Sharpen Dither: Applies the filters on " +
              "the image. <br> Sepia, Grayscale: To apply color transformation on the image. " +
              "<br> RGB Split: split the RGB components of an image. <br> RGB Combine: " +
              "To combine the rgb components that were splitted.</p> </body></html>";
      JOptionPane.showMessageDialog(null, message, "Help",
              JOptionPane.INFORMATION_MESSAGE);
    });
    helpMenu.add(helpMenuItem);
    menuBar.add(helpMenu);
    setJMenuBar(menuBar);

    // Create the buttons panel and add the buttons to it
    JPanel buttonsPanel = new JPanel();

    buttonsPanel.setLayout(new GridLayout(0, 2, 10, 10));
    buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    JButton clearButton = new JButton("Clear Image");
    addActionListener(clearButton, clearMenuItem, "clear");
    buttonsPanel.add(clearButton);

    buttonsPanel.add(new JLabel()); // Empty component to align the buttons with the headers

    // Add headers for categories
    JLabel fileManagementLabel = new JLabel("File Management");
    fileManagementLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    buttonsPanel.add(fileManagementLabel);
    buttonsPanel.add(new JLabel()); // Empty component to align the headers with the buttons

    // Add buttons for file management category
    JButton loadButton = new JButton("Load Image");
    addActionListener(loadButton, loadMenuItem, "load");
    buttonsPanel.add(loadButton);
    JButton saveButton = new JButton("Save Image");
    addActionListener(saveButton, saveMenuItem, "save");
    buttonsPanel.add(saveButton);

    JLabel imageAdjustmentsLabel = new JLabel("Image Operations");
    imageAdjustmentsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    buttonsPanel.add(imageAdjustmentsLabel);

    buttonsPanel.add(new JLabel()); // Empty component to align the headers with the buttons

    JButton brightButton = new JButton("Brightness");
    addActionListener(brightButton, brightMenuItem, "brighten");

    buttonsPanel.add(brightButton);
    JButton hflipButton = new JButton("Horizontal Flip");
    addActionListener(hflipButton, hflipMenuItem, "horizontal-flip");
    buttonsPanel.add(hflipButton);
    JButton vflipButton = new JButton("Vertical Flip");
    addActionListener(vflipButton, vflipMenuItem, "vertical-flip");
    buttonsPanel.add(vflipButton);

    buttonsPanel.add(new JLabel()); // Empty component to align the buttons with the headers

    JLabel imageFiltersLabel = new JLabel("Image Filters");
    imageFiltersLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    buttonsPanel.add(imageFiltersLabel);
    buttonsPanel.add(new JLabel()); // Empty component to align the headers with the buttons

    // Add buttons for image filters category
    JButton blurButton = new JButton("Blur");
    buttonsPanel.add(blurButton);
    addActionListener(blurButton, blurMenuItem, "blur");
    JButton sharpenButton = new JButton("Sharpen");
    addActionListener(sharpenButton, sharpenMenuItem, "sharpen");
    buttonsPanel.add(sharpenButton);
    JButton ditherButton = new JButton("Dither");
    addActionListener(ditherButton, ditherMenuItem, "dither");
    buttonsPanel.add(ditherButton);
    buttonsPanel.add(new JLabel()); // Empty component to align the buttons with the headers

    JLabel colorAdjustmentsLabel = new JLabel("Color Transformation");
    colorAdjustmentsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    buttonsPanel.add(colorAdjustmentsLabel);
    buttonsPanel.add(new JLabel()); // Empty component to align the headers with the buttons

    JButton sepiaButton = new JButton("Sepia");
    addActionListener(sepiaButton, sepiaMenuItem, "sepia");
    buttonsPanel.add(sepiaButton);

    JButton grayscaleTButton = new JButton("Grayscale");
    grayscaleButtonsAction(grayscaleTButton, grayscaleMenuItem);
    buttonsPanel.add(grayscaleTButton);

    JButton rgbSplitButton = new JButton("RGB Split");
    rgbsplit(rgbSplitButton, rgbSplitMenuItem);
    buttonsPanel.add(rgbSplitButton);

    JButton rgbCombineButton = new JButton("RGB Combine");
    addActionListener(rgbCombineButton, rgbCombineMenuItem, "rgb-combine");
    buttonsPanel.add(rgbCombineButton);

    buttonsPanel.add(new JLabel()); // Empty component to align the buttons with the headers


    // Create the image label and add it to the left panel
    imageLabel = new JLabel();
    imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
    imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    JScrollPane imageScrollPane = new JScrollPane(imageLabel);
    JPanel leftPanel = new JPanel(new BorderLayout());
    leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    leftPanel.add(imageScrollPane, BorderLayout.CENTER);

    // Create the histogram label and add it to the left panel
    histogramLabel = new JLabel();
    histogramLabel.setHorizontalAlignment(SwingConstants.CENTER);
    histogramLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    // set preferred size of histogram label
    histogramLabel.setPreferredSize(new Dimension(800, 260));
    histogramLabel.setOpaque(true); // enable background painting
    histogramLabel.setBackground(Color.WHITE); // set background color to white
    leftPanel.setBackground(Color.GRAY);
    leftPanel.add(histogramLabel, BorderLayout.SOUTH);
    buttonsPanel.setBackground(Color.LIGHT_GRAY);

    // Create the border layout
    BorderLayout borderLayout = new BorderLayout();
    setLayout(borderLayout);
    buttonsPanel.setPreferredSize(new Dimension(300, Integer.MAX_VALUE));
    // Add the left panel to the WEST position
    add(leftPanel, BorderLayout.CENTER);

    // Add the buttons panel to the PAGE_END position
    add(buttonsPanel, BorderLayout.EAST);

    // Create the status label and add it to the bottom panel
    JPanel bottomPanel = new JPanel();
    statusLabel = new JLabel("Ready");
    statusLabel.setForeground(Color.WHITE);
    bottomPanel.add(statusLabel);
    bottomPanel.setBackground(Color.darkGray);
    add(bottomPanel, BorderLayout.SOUTH);

    // Set the size of the frame
    setSize(1000, 800);
    setLocationRelativeTo(null); // Center the frame on the screen


  }

  private void rgbsplit(JButton button, JMenuItem menuItem) {
    ActionListener action = e -> {
      JDialog popup = new JDialog();
      popup.setModal(true);
      popup.setSize(500, 75);
      popup.setLocationRelativeTo(null);
      popup.setTitle("Select RGB Split Component");
      JPanel buttonPanel = new JPanel();

      JButton redComp = new JButton("Red Component");
      addActionListener(redComp, null, "rgb-split -red");
      redComp.addActionListener(e1 -> popup.dispose());
      buttonPanel.add(redComp);

      JButton greenComp = new JButton("Green Component");
      addActionListener(greenComp, null, "rgb-split -green");
      greenComp.addActionListener(e12 -> popup.dispose());
      buttonPanel.add(greenComp);

      JButton blueComp = new JButton("Blue Component");
      addActionListener(blueComp, null, "rgb-split -blue");
      blueComp.addActionListener(e13 -> popup.dispose());
      buttonPanel.add(blueComp);

      popup.add(buttonPanel);
      popup.setVisible(true);
      isSplitFlag = 1;
    };
    button.addActionListener(action);
    menuItem.addActionListener(action);
  }


  private void grayscaleButtonsAction(JButton grayscaleTButton, JMenuItem menuItem) {
    ActionListener action = e -> {
      JDialog popup = new JDialog();
      popup.setModal(true);
      popup.setSize(500, 100);
      popup.setLocationRelativeTo(null);
      popup.setTitle("Select Grayscale Component");
      JPanel buttonPanel = new JPanel();

      JButton redComp = new JButton("Red Grayscale");
      addActionListener(redComp, null, "grayscale red-component");
      redComp.addActionListener(e1 -> popup.dispose());
      buttonPanel.add(redComp);

      JButton greenComp = new JButton("Green Grayscale");
      addActionListener(greenComp, null, "grayscale green-component");
      greenComp.addActionListener(e12 -> popup.dispose());
      buttonPanel.add(greenComp);

      JButton blueComp = new JButton("Blue Grayscale");
      addActionListener(blueComp, null, "grayscale blue-component");
      blueComp.addActionListener(e13 -> popup.dispose());
      buttonPanel.add(blueComp);

      JButton intensityComp = new JButton("Intensity Grayscale");
      addActionListener(intensityComp, null, "grayscale intensity-component");
      intensityComp.addActionListener(e14 -> popup.dispose());
      buttonPanel.add(intensityComp);

      JButton valueComp = new JButton("Value Grayscale");
      addActionListener(valueComp, null, "grayscale value-component");
      valueComp.addActionListener(e15 -> popup.dispose());
      buttonPanel.add(valueComp);

      JButton lumaComp = new JButton("Luma Grayscale");
      addActionListener(lumaComp, null, "grayscale luma-component");
      lumaComp.addActionListener(e16 -> popup.dispose());
      buttonPanel.add(lumaComp);

      popup.add(buttonPanel);
      popup.setVisible(true);
    };
    grayscaleTButton.addActionListener(action);
    menuItem.addActionListener(action);
  }

  private void addActionListener(JButton button, JMenuItem menuItem, String command) {
    ActionListener listener = e -> {
      switch (command) {
        case "load":
          File file = showOpenFileDialog();
          commandCallback.accept(command + " " + file.getPath());
          displayStatus("Image " + file.getName() + " loaded successfully");
          isFlag = 1;
          break;
        case "save":
          if (isFlag == 1) {
            File savedFile = showSaveFileDialog();
            if (savedFile != null) {
              commandCallback.accept("save " + savedFile);
              if (isError == 1) {
                displayStatus("Image " + savedFile.getName() + " not saved. Please try again");
                isError = 0;
              } else {
                displayStatus("Image " + savedFile.getName() + " saved successfully");
              }
            }
          }
          break;
        case "brighten":
          if (isFlag == 1) {
            String brightnessValue = getInput("Enter Brightness Value:");
            if (!brightnessValue.matches("-?[0-9]+")) {
              displayError("Invalid Input, please enter a number");
            } else {
              callCommand(command + " " + brightnessValue);
            }
          } else {
            displayError("Please load an image.");
          }
          break;
        case "rgb-combine":
          if (isSplitFlag == 1) {
            callCommand(command);
          } else if (isFlag == 0) {
            displayError("Please load an image.");
          } else {
            displayError("Please split the RGB colors using RGB Split.");
          }
          break;
        case "clear":
          callCommand(command);
          isFlag = 0;
          isSplitFlag = 0;
          imageLabel.setIcon(null);
          histogramLabel.setIcon(null);
          displayStatus("Your workspace is now clear and ready for new Image");
          break;
        default:
          callCommand(command);
          break;
      }
    };
    button.addActionListener(listener);
    if (menuItem != null) {
      menuItem.addActionListener(listener);
    }
  }

  private void callCommand(String command) {
    if (isFlag == 1) {
      commandCallback.accept(command);
      displayStatus("Image " + command + " performed successfully");
    } else {
      displayError("Please load an image.");
    }
  }

  @Override
  public void displayImage(Image image) {
    int height = image.getImageHeight();
    int width = image.getImageWidth();

    // Create a new BufferedImage object with the same dimensions as the image
    BufferedImage imageOP = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    // Get the pixel array from the image
    Pixel[][] imageArray = image.getPixelArray();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int red = imageArray[i][j].getComponent(Channel.RED.ordinal());
        int green = imageArray[i][j].getComponent(Channel.GREEN.ordinal());
        int blue = imageArray[i][j].getComponent(Channel.BLUE.ordinal());

        // Combine the color components into a single integer value
        int color = (red << 16) + (green << 8) + blue;

        // Set the color of the corresponding pixel in the BufferedImage object
        imageOP.setRGB(j, i, color);
      }
    }
    imageLabel.setIcon(new ImageIcon(imageOP));
    imageLabel.revalidate();
    refresh();
  }

  @Override
  public void displayError(String error) {
    JOptionPane.showMessageDialog(this, error, "Error",
            JOptionPane.ERROR_MESSAGE);
    isError = 1;
  }


  private void displayStatus(String status) {
    statusLabel.setText(status);
  }

  private JFrame getMainFrame() {
    return this;
  }

  private File showOpenFileDialog() {
    JFileChooser fileChooser = new JFileChooser();
    int result = fileChooser.showOpenDialog(this.getMainFrame());
    if (result == JFileChooser.APPROVE_OPTION) {
      return fileChooser.getSelectedFile();
    }
    return null;
  }

  private File showSaveFileDialog() {
    JFileChooser fileChooser = new JFileChooser();
    int result = fileChooser.showSaveDialog(this.getMainFrame());
    if (result == JFileChooser.APPROVE_OPTION) {
      return fileChooser.getSelectedFile();
    }
    return null;
  }

  private void refresh() {
    this.repaint();
  }


  @Override
  public void displayGUI() {
    this.setVisible(true);
  }


  @Override
  public void actionCallback(Consumer<String> callback) {
    commandCallback = callback;
  }

  @Override
  public void displayHist(int[][] image) {
    int w = 550;
    int h = 180;
    int x = 50;
    int y = 50;

    BufferedImage imageOP = new BufferedImage(w + 100, h + 100, BufferedImage.TYPE_INT_RGB);
    Graphics2D g2d = imageOP.createGraphics();

    // Fill the background with white color
    g2d.setColor(Color.WHITE);
    g2d.fillRect(0, 0, w + 100, h + 100);

    // Draw horizontal and vertical axis lines
    g2d.setColor(Color.BLACK);
    g2d.drawLine(x, y + h, x + w, y + h);
    g2d.drawLine(x, y, x, y + h);

    // Draw labels for the horizontal axis
    g2d.setFont(new Font("Arial", Font.PLAIN, 12));
    g2d.drawString("Pixel Values", x + w / 2 - 40, y + h + 40);

    // Add legend
    g2d.setColor(Color.RED);
    g2d.drawString("Red Channel", x + w + 10, y + h + 70);

    // Define position and size of legend box
    int legendY = y - 10;
    int legendSize = 10;

    // Draw legend box
    g2d.setColor(Color.RED);
    g2d.fillRect(x, legendY, legendSize, legendSize);

    g2d.setColor(Color.GREEN);
    g2d.fillRect(x + 150, legendY, legendSize, legendSize);

    g2d.setColor(Color.BLUE);
    g2d.fillRect(x + 300, legendY, legendSize, legendSize);

    g2d.setColor(Color.MAGENTA);
    g2d.fillRect(x + 450, legendY, legendSize, legendSize);

    // Draw color name next to legend box
    g2d.setColor(Color.BLACK);
    g2d.setFont(new Font("Arial", Font.PLAIN, 12));
    g2d.drawString("Red Channel", x + legendSize + 5, legendY + legendSize - 2);
    g2d.drawString("Green Channel", x + legendSize + 155, legendY + legendSize - 2);
    g2d.drawString("Blue Channel", x + legendSize + 305, legendY + legendSize - 2);
    g2d.drawString("Intensity Channel", x + legendSize + 455, legendY + legendSize - 2);

    // Draw labels for the vertical axis
    g2d.rotate(-Math.PI / 2, x - 35, y + h / 2);
    g2d.setFont(new Font("Arial", Font.PLAIN, 12));
    g2d.drawString("Frequency", x - 60, y + h / 2); // shift down by 5 pixels

    // Plot line chart for the red color channel histogram
    g2d.rotate(Math.PI / 2, x - 35, y + h / 2);

    for (int i = 0; i < image.length; i++) {
      int x1 = x + (int) (i * w / (image.length - 1.0));
      g2d.setColor(Color.BLACK);
      if (i == image.length - 1) {
        g2d.drawString(Integer.toString(i), x1 - 10, y + h + 20);
      } else if (i % 32 == 0) {
        g2d.drawString(Integer.toString(i), x1 - 10, y + h + 20);
      }
    }

    // Define the maximum value on the y-axis
    int maxFreq = 0;
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < 4; j++) {
        if (image[i][j] > maxFreq) {
          maxFreq = image[i][j];
        }
      }
    }
    int maxY = (maxFreq / 1000 + 1) * 1000;

    // Calculate the interval for the y-axis
    int interval = (int) Math.ceil((double) maxY / 4);

    // Draw labels for the vertical axis
    g2d.setFont(new Font("Arial", Font.PLAIN, 12));
    for (int i = 1000; i < maxY; i += interval) {
      String label = i < 1000 ? Integer.toString(i) : Integer.toString(i / 1000) + "K";
      if (i == 0) {
        continue;
      }
      g2d.drawString(label, x - 30, y + h - i * h / maxY);
    }

    if (maxY > 1000) {
      String topLabel = Integer.toString(maxY / 1000) + "K";
      g2d.drawString(topLabel, x - 30, y + h - maxY * h / maxY);
    } else {
      String topLabel = Integer.toString(maxFreq);
      g2d.drawString(topLabel, x - 30, y + h - maxY * h / maxY);
    }

    g2d.rotate(0);

    //Plot the histogram
    g2d.setColor(Color.RED);
    Path2D.Double pathR = drawHistogram(image, 0, x, y, h, w);
    g2d.draw(pathR);

    g2d.setColor(Color.GREEN);
    Path2D.Double pathG = drawHistogram(image, 1, x, y, h, w);
    g2d.draw(pathG);

    g2d.setColor(Color.BLUE);
    Path2D.Double pathB = drawHistogram(image, 2, x, y, h, w);
    g2d.draw(pathB);

    g2d.setColor(Color.MAGENTA);
    Path2D.Double pathI = drawHistogram(image, 3, x, y, h, w);
    g2d.draw(pathI);
    histogramLabel.setIcon(new ImageIcon(imageOP));
    histogramLabel.revalidate();
    refresh();
  }

  @Override
  public String getInput(String message) {
    if (message == null) {
      throw new IllegalArgumentException("Invalid input");
    }
    return JOptionPane.showInputDialog(null, message,
            "Brightness", JOptionPane.PLAIN_MESSAGE);
  }

  private Path2D.Double drawHistogram(int[][] image, int color, int x, int y, int h, int w) {
    int maxValI = Arrays.stream(image).mapToInt(row -> row[0]).max().orElse(0);
    Path2D.Double pathI = new Path2D.Double();
    int scaleValI = (int) (maxValI * 1.2);
    boolean startedI = false;
    for (int i = 0; i < image.length; i++) {
      int x1 = x + (int) (i * w / (image.length - 1.0));
      int y1 = y + h - (image[i][color] * h / scaleValI);
      if (!startedI) {
        pathI.moveTo(x1, y1);
        startedI = true;
      } else {
        pathI.lineTo(x1, y1);
      }
    }
    return pathI;
  }


}
