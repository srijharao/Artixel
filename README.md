# Project Artixel - IME: Image Manipulation and Enhancement

<div style="text-align: justify">

- This is an image manipulation and enhancement application that can perform operations over a given image.

- This project is developed using the MVC architecture, with a Model, View and a Controller. Other design patterns used are
1. Command Design Pattern
2. Strategy Desgin Pattern
3. Adapter Design Pattern
4. Visitor Design Pattern

- Application supports image operations on file formats (ppm, bmp, jpg, jpeg and png): 
Load, Save, Brighten, Darken, Blur, Sharpen, Vertical Flip, Horizontal Flip, Dither, Convert to Sepia, Convert to greyscale, Split an RGB image into 3 greyscale images each for one of R,G,B channels, Combine 3 greyscale images to a single RGB image. One operation can be applied over the other in any particular order. Application uses a hashmap to store all the resultant intermediate image operations and allows user to save the desired image in their chosen location.

- Application supports text-based scripting i.e, command line inputs, ignoring lines commented by '#', and provides a sophisticated GUI for  user to easily apply filter or color transformations on an image.

- GUI has Histogram support for frequency of colors Red, Green, Blue and Intensity.

### To run Application:
1. GUI mode: ```java -jar IME.jar```
2. Interactive Text mode: ```java -jar IME.jar -text```
3. Command line Argument Script: ```java -jar IME.jar -file script.txt```



## Design patterns implemented in this Project:
1. **MVC (Model-View-Controller) Architectural Design pattern**
2. **Strategy Design pattern**
3. **Command Design pattern**
4. **Adapter Design pattern**
5. **Visitor Design pattern**


## Overview of purposes for every class and interfaces 

## Model

### Interface:
*ImageModel:* Starting point and contains all methods that commands would invoke for operations on an image - Load, Brighten, Vertical Flip, Horizontal Flip, Convert to Greyscale, RGB Split, RGB Combine, Save.

### Class:
*ImageModelImpl:* Implements ImageModel methods by,
1. Handles the possible exceptions wrapped for logic.
2. Delegates the business logic operation to classes further down (RgbImage).
3. Uses a hashmap to store results and intermediate results, so that they can be saved at any time.  

### Interface:
*Color:* Contains methods that work at the color level of a pixel-Value, Intensity, Luma, brighten, Color component

### AbstractClass:
*AbstractColor:* implements getComponent and brighten methods, that can also be used for other classes with different components than rgb

### Class:
*RgbColor:* Has operations specific to rgb Color of an image. This class can be used by image formats that use rgb components. implements Value, Intensity, Luma methods and has private methods to get R, G, B component values. And, a toString() implementation.  


### Interface:
*Pixel:* Extends color (for relevant color-pixel operations) and has getXCoordinate(), getXCoordinate() to get the location of pixel.

### Class:
*PixelImpl:* Takes in X, Y, and color. Implements Pixel methods, and also the methods from Color (as pixel extends color) to get values of a pixel with that color at the given space.  

### Interface:
*Image:* Contains all methods that work on Image level. getPixelArray(); getImageWidth(); getImageHeight(); brighten(int value); vFlip();hFlip(); imgConvertGreyScale(); imgCombineRGB

### AbstractClass:
*AbstractImage:* Implements getImageWidth(); and getImageHeight(); of an image. Class is abstract with these two features for extendability.

### Class:
*RgbImage:* Contains constructors to create the Pixel Array of the image based on the arguments given to this class.
Contains the actual business logic for operations on image.


### Interface:
*Operator:* Has a single method applyOperator(), that takes in an image and returns an image. This interface is implemented by two abstract classes, AbstractTransformStrategy and AbstractFilterStrategy.

### AbstractClass:
*AbstractTransformStrategy:* has doTransform() protected method, implements the respective strategies for filter and color transform classes.

### AbstractClass:
*AbstractFilterStrategy:* has a doFilter() protected method, implements the strategy for filter transform classes.

### Classes:
*BlurFilter:* extends AbstractFilterStrategy and passes Blur kernel to perform filter operation.

*SharpenFilter:* extends AbstractFilterStrategy and passes Sharpen kernel to perform filter operation.

*DitherFilter:* Implements Operator interface and implements its own strategy.

*SepiaTransform:* extends AbstractTransformStrategy and passes sepia kernel to perform transform operation.

*GreyScaleRedTransform:* extends AbstractTransformStrategy and passes Red kernel to perform transform operation.

*GreyScaleGreenTransform:* extends AbstractTransformStrategy and passes Green kernel to perform transform operation.

*GreyScaleBlueTransform:* extends AbstractTransformStrategy and passes Blue kernel to perform transform operation.

*GreyScaleLumaTransform:* (default for greyscale command) extends AbstractTransformStrategy and passes luma kernel to perform transform operation.

*GreyScaleIntensityTransform:* extends AbstractTransformStrategy and passes Blue kernel to perform transform operation.

*GreyScaleBlueTransform:* extends AbstractTransformStrategy and overrides the doTransform() method to implement value component for greyscale transform.

### Class
Histogram : This class is added to support rbi plotting for image. The histogram refreshes to the image displayed on screen after each image manipulation action.


### Interface:
*IKernel:* has methods to get kernel and its dimension.

### Classes:
*KernelImpl:* implements Ikernel and its methods. Validates kernel and throws exceptions.

*RgbTransformImpl:* extends KernelImpl and is used to check kernel length for rgb.

## Controller
### Interface:
*ImageController:* Has a process method that is used by Main to run the controller.

### Class:
*ImageControllerImpl:* Implements ImageController and has the process() code to run commands from console.  

### Class
*ControllerGUIImpl:* This new class in created to support GUI view and controller interactions. This class implements the existing Controller Interface and is listening and awaits a callback command from the view and calls model to perform the desired action and enables view to display the output to user.

## View
### Interface:
*ImageView:* has a showStatus method that takes in image and operation executed on it.

### Class:
*ImageViewImpl:* implementation of showStatus to give feedback to user after executing operations.  

### Interface
*ImageViewGUI:* This new interface is added to support View GUI operations.

### Class
*ViewGUIImpl:* Is an implementation of the GUI view and is responsible for displaying a user interface for the image processing and manipulation application. This provides user with the option to enter commands. ControllerGUIImpl will then act on these commands.
   
## Design Enhancements in sequential order from Version 1 to 3:
**Enhancements made over Version 1 to 2:**

1. Previously in Version 1, for converting an image to GreyScale, hard coded if-else ladder was used to call the respective component methods. Used command design pattern to make it extendable.
So, as part of this, introduced Interface IGreyScaleCommand, (which has an execute method, that takes an image and component). 
The class GreyScaleCommandImpl has hash map, mapping the component name to the respective operation. 
Each of the GreyScale color transform operations are separated into individual classes. The responsibility of each of these classes is to pass respective transformation matrices to its super class AbstractTransformStrategy.

2. To incorporate the functionality of Blur, Sharpen, Sepia, GreyScale, Dither, utilized Strategy Design pattern.
Architected this by incorporating an Operator Interface, that has a single method applyOperator(), that takes in an image and returns an image. 
This interface is implemented by two abstract classes, AbstractTransformStrategy and AbstractFilterStrategy
AbstractTransformStrategy has doTransform() protected method, and 
AbstractFilterStrategy has a doFilter() protected method, these methods implement the respective strategies for filter and color transform classes. BlurFilter and SharpenFilter extends AbstractFilterStrategy
and SepiaTransform and GreyScale<component>Transform classes extend AbstractTransformStrategy.

- DitherFilter implements Operator interface and implements its own strategy.

- This is because, blur, sharpen have similar strategies ; Sepia and all GreyScale Transforms use a similar strategy; Dither uses its own strategy.

- Implemented Kernel Interface, KernelImpl and RgbTransformImpl classes to facilitate us with the kernel operations.

- Constant file has the respective kernels for each of the color and filter transform operations. 

- Changes to ImageModelImpl, RgbImage: Moved all the operation methods from RgbImage to ImageModelImpl, this is because, RgbImage responsibility can be limited to returning PixelArray and dimensions and performing operations is responsibility of ImageModelImpl.

- imgConvertGreyScale(): Now uses greyScaleCommand Map instead of the if-else ladder to invoke respective command.
- getValue(): is moved from RgbColor to AbstractColor (this operation is independent of the number of channels)
- Clamp() : Added this to Color and Pixel interfaces, to be utilized by AbstractFilterStrategy, AbstractTransformStrategy and DitherFilter.

**Summary:**
In Version 2, project is enhanced by incorporating Strategy Design Pattern along with MVC Design pattern. Separate classes are created for color transformations such as sepia, blur, dither, grayscale, and sharpen, which can be applied to images.

**Benefits:**
- The use of the Strategy Design Pattern allows for greater flexibility in the application of color transformations to images. By encapsulating each transformation as a separate class, adding or removing transformations can be done as needed without affecting the overall structure of the program. Additionally, this approach allows us to create new transformations that can be easily integrated into the program without modifying the existing code.

- The creation of separate classes for color transformations also helps to reduce code duplication and improve maintainability. Each transformation class is responsible for implementing a specific algorithm, which reduces the complexity of the overall design and makes it easier to test and debug.

**Change from Version 2 to 3:**

Utility class is updated to object-oriented pattern, to now reuse existing code while supporting extendability by using the public read and write methods.


Justification: This allows adding support to other file patterns easily, instead of the functional approach which requires new public methods.


## Design Changes:
**Change from Version 1 to 2:**
   
1. Utility is moved from the model to the controller in our program. This means that the controller will now handle input/output (IO) operations, such as reading/writing data to/from files, rather than the model.

- **Justification:**
  - To follow the Model-View-Controller (MVC) design pattern more closely. According to MVC, the model should represent the data and business logic of the program, while the view handles the user interface, and the controller acts as an intermediary between the model and the view.

  - By moving the IO operations from the model to the controller, seperated model and controller, making the design more modular and maintainable. Additionally, this change makes it easier to test the model in isolation, without having to worry about IO operations.

2. imgConvertGreyScale(): Now uses greyScaleCommand Map instead of the if-else ladder to invoke respective command.

- **Justification:**
  - For extendability.
3. getValue(): is moved from RgbColor to AbstractColor

- **Justification:**
  - This operation is independent of the number of channels.






</div>
