# IME: Image Manipulation and Enhancement

## How to work with GUI Application:

**To start GUI application:**

1. Open Terminal / Command prompt, then move to the directory where the IME.jar is located.

2. Run the IME.jar using ```java -jar IME.jar```

This will open the GUI application.

**To perform image manipulation operations:**

**1. Load Image**

Load the image first, this can be done using "Load Image" Button in the right panel or click on File Menu in menu bar and then click on "Load Image" menu option.
For testing purpose, use photo.jpg image from the res folder.
```agsl
    1.1. This will open a image selection dialog box: Move to the folder where the image is located and select the image.
    
    1.2. Click "Open" and the image will be loaded. 
```

**2. Save Image**
The current image that is visible on the screen can be saved using "Save Image" button in the right panel or click the File Menu in the menu bar and click on "Save Image" option.

```agsl
    1.1. This will open a path selection dialog box: Move to the folder where the image is to be saved and then click on saved button.
    
    1.2. Click "save" and the image will be saved to that location. 
```
    
**3. Brighten / Darken Image**

To increase brightness i.e. brightening the image or decrease brightness i.e. darkening the image 

Option 1: click on "Brighness" Button. 

Option 2: click on "Operations" Menu in the Menu bar and then click on "Brightness" menu option. 

```agsl
3.1. This will give you a pop up to "Enter Brightness Value:". Enter a integer number that can be either positive or negetive.
3.2. To Brighen a Image: Enter a Positive integer number (If RGB image then value can be from 1 to 255).
3.3. To Darken a Image: Enter a negetive integer number. (If RGB image then values can be from -255 to 0).
```

**4. Horizontal / Vertical Flip**

To flip an image Horizontally or Vertically. 

4.1 To Flip an image Horizontally:
```agsl
Option 1: click on "Horizontal Flip" Button in the Image Operations section.

Option 2: click on "Operations" Menu in the Menu bar and then click on "Horizontal Flip" menu option.
```

4.2 To Flip an image Vertically.:

```agsl
Option 1: click on "Vertical Flip" Button in the Image Operations section.

Option 2: click on "Operations" Menu in the Menu bar and then click on "Vertical Flip" menu option.
```
**5. Image Filter: Blur an Image**

To blur an image: 
```agsl
Option 1: click on "Blur" Button in the Image Filters section.

Option 2: click on "Filters" Menu in the Menu bar and then click on "Blur" menu option.
```

**6. Image Filter: Sharpen an Image**

To sharpen an image:
```agsl
Option 1: click on "Sharpen" Button in the Image Filters section.

Option 2: click on "Filters" Menu in the Menu bar and then click on "Sharpen" menu option.
```

**7. Image Filter: Dither an Image**

To dither an image:
```agsl
Option 1: click on "Dither" Button in the Image Filters section.

Option 2: click on "Filters" Menu in the Menu bar and then click on "Dither" menu option.
```

**8. Color Transformation: Sepia an Image.**

To sepia an image:
```agsl
Option 1: click on "Sepia" Button in the color Transformation section.

Option 2: click on "Colors" Menu in the Menu bar and then click on "Sepia" menu option.
```

**9. Color Transformation: Grayscale**

To grayscale an image:
```agsl
Option 1: click on "Grayscale" Button in the color Transformation section.

Option 2: click on "Colors" Menu in the Menu bar and then click on "Grayscale" menu option.
```
```agsl
9.1. A pop up will appear with 6 buttons:
a. Red Grayscale: To convert the image to grayscale using red component.
b. Green Grayscale: To convert the image to grayscale using green component.
c. Blue Grayscale: To convert the image to grayscale using blue component.
d. Intensity  Grayscale: To convert the image to grayscale using intensity component.
e. Value Grayscale: To convert the image to grayscale using value component.
f. Luma Grayscale: To convert the image to grayscale using luma component.

9.2: click on the button in which the grayscale is required.
```
This will grayscale the image.

**10. Color Transformation: RGB Split**
   
To split the RGB components of the image.
```agsl
Option 1: click on "RGB Split" Button in the color Transformation section.

Option 2: click on "Colors" Menu in the Menu bar and then click on "RGB Split" menu option.
```
```agsl
10.1. A pop up will appear with 3 buttons:
a. Red Component: To convert the image to grayscale using red component.
b. Green Component: To convert the image to grayscale using green component.
c. Blue Component: To convert the image to grayscale using blue component.

10.2: click on the button for which component you want to see. Other components are stored in the application.
To view another component: 
10.2.1. If not performed any other operation after RGB Split then, click RGB Combine button and then 
click on RGB split and choose another component.
10.2.2. If performed any other operation after RGB Split then load the same image again.
```
This will split the image.

**11. Color Transformation: RGB Combine**

To combine the red, green and blue components.
```agsl
Option 1: click on "RGB combine" Button in the color Transformation section.

Option 2: click on "Colors" Menu in the Menu bar and then click on "RGB Combine" menu option.
```

## How to work with interactive text application:

**To start interactive text application:**

1. Open Terminal / Command prompt, then move to the directory where the IME.jar is located.

2. Run the IME.jar using 
```java -jar Program.jar -text```

### Commands supported by application:

**1. Loading of images of different file formats:**

This command is used to load an image file into the program. Supported file formats include ASCII PPM, PNG, JPG, JPEG, and BMP. The loaded image is given a name that can be used in other commands.

**Command syntax:**
```load <file path> <image name>```

**Example**
```
load mat.ppm mat
load mat.png mat
load mat.jpg mat
load mat.bmp mat
```
**2. Brightening of an image.**

This command is used to increase the brightness of an image. The amount of brightness adjustment is specified as a value.

**Command syntax:** 

```brighten <value> <source image name> <destination image name>```

**Example**
```
brighten 50 mat mat-brighter
```

**3. Darkening of an image.**

The brighten command helps to darken the image as well by passing negative values.

**Command syntax:**

```brighten <value> <source image name> <destination image name>```

**Example**
```
brighten -90 mat mat-darken
```
**4. Flipping of image horizontally.**

This command is used to flip an image horizontally.

**Command syntax:**

```horizontal-flip <source image name> <destination image name>```

**Example**

```
horizontal-flip mat mat-horizontal
```
**5. Flipping of image vertically.**

This command is used to flip an image vertically.

**Command syntax:**

```vertical-flip <source image name> <destination image name>```

**Example**

```
vertical-flip mat mat-vertical
```
**6. Grayscale of image based on component (Red, Green, Blue, Value, Luma, Intensity).**

This command is used to convert an image to grayscale. The type of grayscale conversion can be specified as either red-component, green-component, blue-component, value-component, luma-component, or intensity-component.

**Command syntax:**

```grayscale <grayscale type> <source image name> <destination image name>```

**Example**

```
grayscale red-component mat mat-red-grayscale
grayscale green-component mat mat-green-grayscale
grayscale blue-component mat mat-blue-grayscale
grayscale value-component mat mat-value-grayscale
grayscale luma-component mat mat-luma-grayscale
grayscale intensity-component mat mat-intensity-grayscale
```
**7. Splitting of RGB colors of an image.**

This command is used to split an RGB image into its three color components: red, green, and blue.

**Command syntax:**

```rgb-split <source image name> <destination red image name> <destination green image name> <destination blue image name>```

**Example**


```
rgb-split mat mat-red mat-green mat-blue
```
**8. Combining RGB colors of an image.**

This command is used to combine three separate color components into an RGB image.

**Command syntax:**

```rgb-combine <destination image name> <source red image name> <source green image name> <source blue image name>```

**Example**


```
rgb-combine mat-combine mat-red mat-green mat-blue
```
**9. Transformations on Image (Sepia).**

This command is used to apply a sepia filter to an image.

**Command syntax:**

```sepia <source image name> <destination image name>```

**Example**


```
sepia mat mat-sepia
```

**10. Transformations on Image (Grayscale).**

This command is used to apply a greyscale filter to an image.

**Command syntax:**

```greyscale <source image name> <destination image name>```

**Example**


```
greyscale mat mat-grey
```

**11. Filters on Image (Blur).**

This command is used to apply a blur filter to an image.

**Command syntax:**

```blur <source image name> <destination image name>```

**Example**

```
blur mat mat-blur
```

**11. Filters on Image (Sharpen).**

This command is used to apply a sharpen filter to an image.

**Command syntax:**

```sharpen <source image name> <destination image name>```

**Example**

```
sharpen mat mat-sharpen
```

**12. Dither**

This command is used to apply a dithering effect to an image.

**Command syntax:**

```dither <source image name> <destination image name>```

**Example**

```
dither mat mat-dither
```
**13. Saving the image in any file format (ASCII PPM, PNG, JPG, JPEG, BMP).**
This command is used to save an image in a specified file format. Supported file formats include ASCII PPM, PNG, JPG, JPEG, and BMP.

**Command syntax:**

```save <destination path> <image name>```

**Example**

```
save mat.png mat
save mat.ppm mat
save mat.jpg mat
save mat.jpeg mat
save mat.bmp mat
```

**14. Run from Script file in run-time:**

This command allows the user to run a script file during runtime. The script file should contain a sequence of valid commands for the application. The command syntax is as follows:

**Command syntax:**

```run <file_path>```

where ```<file_path>``` is the path of the script file to be executed. The file should exist in the specified location. Once the command is executed, the application will run the commands specified in the script file.

**Example:**

Suppose you have a script file named "script.txt" located in the "res" folder. To run the script file, enter the following command

**Example**

```
run script.txt
```

**15. Run from Script file by passing file in command line argument.**

This command allows the user to pass a script file as a command-line argument to the application. The script file should contain a sequence of valid commands for the application. The command syntax is as follows:

**Command syntax:**

```-file <file_path>```

where <file_path> is the path of the script file to be executed. The file should exist in the specified location. Once the command is executed, the application will run the commands specified in the script file.

**Example:**

Suppose you have a script file named "script.txt" located in the "res" folder. To run the script file using the command-line argument, enter the following command:

```
-file script.txt
```


## Conditions:
1. It is necessary to load the image before executing any command that requires an image. Please ensure to provide the correct file path when loading the image.
2. Prior to executing the RGB combine command, it is recommended to run the rgb-split command first to obtain the RGB color components.
3. In order to save an image, it is mandatory to load it first.
4. When using the run command or the command line argument to execute a script file, please ensure that the specified file path is valid and the file exists.
5. The application supports on these Image file formats: BMP, JPG, PNG, JPEG, ASCII PPM.

## How to run individual commands:

For Explanation of each command, please check **USEME.md**

### 1. Console

1. Navigate to Project Directory.
2. Place image in resources root folder res/ (or use the existing image mat.ppm).
3. Observe sample script for all commands in res/script.txt (or below)
4. Run the program.
5. Use below commands to perform an operation:

### Example ordering of commands:

1. **Load image: (Load the image first)**

Example:
```
load mat.ppm mat
```

2. **Perform image manipulation operation:**

Example:
```
brighten 72 mat mat-brighter
sepia mat mat-sepia
vertical-flip mat mat-vertical
```

3. **Save image if required or continue Operations:**

Example:
```
save mat-brighter.ppm mat-brighter
save mat-sepia.png mat-sepia
save mat-vertical.bmp mat-vertical
```

4. **If you want to pass a script file.**

Example:
```
run script.txt
```

5. **Quit: (To stop the program)**

Example:
```
quit
```

### Run all commands from Console, using a file


1. Run the program, and pass the command to run all the commands in script.txt

```run script.txt```

<div style="text-align: center"> <b> OR </b> </div>

2. Create a script.txt file using the below commands and put the script file in the same folder as the IME.jar file and then ```run script.txt```:
```
load mat.ppm mat
brighten 50 mat mat-brighter
save mat-brighter.ppm mat-brighter
vertical-flip mat mat-vertical
save mat-vertical.ppm mat-vertical
horizontal-flip mat mat-horizontal
save mat-horizontal.ppm mat-horizontal
greyscale value-component mat mat-greyscale-value
save mat-red-greyscale.ppm mat-greyscale-value
greyscale red-component mat mat-greyscale-red
save mat-red-greyscale.ppm mat-greyscale-red
greyscale blue-component mat mat-greyscale-blue
save mat-red-greyscale.ppm mat-greyscale-blue
greyscale green-component mat mat-greyscale-green
save mat-red-greyscale.ppm mat-greyscale-green
greyscale intensity-component mat mat-greyscale-intensity
save mat-red-greyscale.ppm mat-greyscale-intensity
greyscale luma-component mat mat-greyscale-luma
save mat-red-greyscale.ppm mat-greyscale-luma
rgb-split mat mat-red mat-green mat-blue
save mat-red.ppm mat-red
save mat-green.ppm mat-green
save mat-blue.ppm mat-blue
brighten 50 mat-red mat-red
save mat-red.ppm mat-red
rgb-combine mat-combine mat-red mat-green mat-blue
save mat-combine.ppm mat-combine
sepia mat mat-sepia
save mat-sepia.ppm mat-sepia
blur mat mat-blur
save mat-blur.ppm mat-blur
dither mat mat-dither
save mat-dither.ppm mat-dither
sharpen mat mat-sharpen
save mat-sharpen.ppm mat-sharpen
greyscale mat mat-grey
save mat-grey.ppm mat-grey
save mat-dither.png mat-sharpen
save mat-blur.jpg mat-blur
save mat-sepia.jpeg mat-sepia
save mat-sharpen.bmp mat-sharpen
load mat-dither.png mat-new
vertical-flip mat-new mat-new-vertical
save mat-new-vertical.ppm mat-new-vertical
load mat-sepia.jpeg mat-new
horizontal-flip mat-new mat-new-horizontal
save mat-new-horizontal.ppm mat-new-horizontal
load mat-sharpen.bmp mat-new
blur mat-new mat-new-blur
save mat-new-blur.ppm mat-new-blur
load mat-blur.jpg mat-new
sharpen mat-new mat-new-sharpen
save mat-new-sharpen.ppm mat-new-sharpen
```

### 2. Command Line Argument:
- In order to support the ability to accept a script file as a command-line option, we have implemented a feature that allows the user to specify the name of a script file when running the program from the command line using the "-file" option.
- If a valid file is provided, the program will automatically run the commands in the script and exit.
- To use this feature, simply run the program with the "-file" option followed by the name of the script file you wish to run. If the file is not found or is invalid, the program will print an error message and exit.

Example: (To run using the JAR file provided)
```
java -jar IME.jar -file script.txt
```