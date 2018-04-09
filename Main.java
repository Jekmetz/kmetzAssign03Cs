import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;

public class Main {
	public static void main(String[] args) {
		//Define independent things and add the independent image
		ImageUtils imgu = new ImageUtils();
		Color[][] img = imgu.loadImage("LennaCV.png");
		String folderName = "pictureFolder";
		imgu.addImage(img, "TestTab");
		
		//Add image modifications
		imgu.addImage(grayScale(img, "light"), "Ligtness GrayScale");
		imgu.addImage(grayScale(img, "ave"), "Average GrayScale");
		imgu.addImage(grayScale(img, "lumin"), "Luminosity GrayScale");
		imgu.addImage(invertColor(img), "Inverted Color");
		
		//pictureMess(img,folderName,15,75);	//Uncomment out the line before this if you want to make a filefolder with name String folderName, img Color[][] img, with 15 pngs all messed up with a color deviation of 75 :)
	
		imgu.display();		//Display the JFrame
		
		Images imgArray = new Images();		//Create an Image array
		
		try {
			imgArray.addImagesFromFolder(new File(".\\" + folderName).getCanonicalPath());	//See if that folder exists and if it does, add all of the images from it into the Images class imgArray. Look in the Images class to find out what the method does
		} catch (IOException e) {
			System.out.println("Listen, we just could not find that folder.");	//Damn.
		}
		
		VideoFrame vidFrame = new VideoFrame();	//Create a new vidframe class. Look at that class to know about it
		vidFrame.display();	//Display the video frame
		vidFrame.repeatedImages(imgArray,30);	//Start the timer for the images and display at 30 frames per second

	}

	public static Color[][] grayScale(Color[][] orig, String type) {
		/*
		 *Function that turns an image into grayscale using the lightness algorithm, the average algorithm, or the luminosity algorithm
		*/

		Color[][] img = ImageUtils.cloneArray(orig);
		int r = 0;
		int g = 0;
		int b = 0;
		int gray = 0;

		for (int row = 0; row < img.length; row++) {
			for (int col = 0; col < img[row].length; col++) { //parse through each pixel
				r = img[row][col].getRed();
				g = img[row][col].getGreen();
				b = img[row][col].getBlue();
				gray = 0;

				//Below are the different algorithms. they are self explanitory. the luminosity method is default cause I like it the best.
				switch (type) {
				case "light":
					gray = (Math.max(r, Math.max(g, b)) + Math.min(r, Math.min(g, b))) / 2;
					break;

				case "ave":
					gray = (r + g + b) / 3;
					break;

				default:
					gray = (int) Math.round(.21 * r + .72 * g + .07 * b);
					break;
				}

				img[row][col] = new Color(gray, gray, gray);
			}
		}
		return img;
	}

	public static Color[][] messUp(Color[][] orig, int range) {
		/*
		 *Method that turns each pixels rgb value +- something in the range. Makes static basically
	        */
		Color[][] img = ImageUtils.cloneArray(orig);
		int r = 0;
		int g = 0;
		int b = 0;
		Random rng = new Random();

		for (int row = 0; row < img.length; row++) {
			for (int col = 0; col < img[row].length; col++) {
				r = abs(img[row][col].getRed() + rng.nextInt(2 * range) - range) % 255;
				g = abs(img[row][col].getGreen() + rng.nextInt(2 * range) - range) % 255;
				b = abs(img[row][col].getBlue() + rng.nextInt(2 * range) - range) % 255;

				img[row][col] = new Color(r, g, b);
			}
		}
		return img;
	}

	public static Color[][] invertColor(Color[][] orig) {
		/*
		 *Function that inverts the color for each pixel
		 */
		Color[][] img = ImageUtils.cloneArray(orig);
		int r = 0;
		int g = 0;
		int b = 0;

		for (int row = 0; row < img.length; row++) {
			for (int col = 0; col < img[row].length; col++) {
				r = 255 - img[row][col].getRed();
				g = 255 - img[row][col].getGreen();
				b = 255 - img[row][col].getBlue();

				img[row][col] = new Color(r, g, b);

			}
		}
		return img;
	}

	public static float loopFloat(float add, float num, float loop) {
		/*
		 *Function that will loop a float in between a range. So if I add 1.7 to .5 with a loop of 2, I will get back .3.
		 */
		float sum = add + num;
		if (sum > loop) {
			sum = sum - loop;
		}
		return sum;
	}

	public static int abs(int num) {
		if (num < 0) {
			num = -num;
		}
		return num;
	}

	public static void pictureMess(Color[][] orig, String folderName, int numPics, int mess) {
		/*
		 *Function that Creates a folder with a bunch of static-y images in it.
		 */

		for (int i = 0; i < numPics; i++) {

			Color[][] img = messUp(ImageUtils.cloneArray(orig),mess);

			BufferedImage bi = ImageUtils.convertToBufferedFrom2D(img);

			try {
				File filepath = new File(".\\" + folderName + "\\output" + Integer.toString(i) + ".png").getCanonicalFile();

				if (!new File(".\\" + folderName).getCanonicalFile().exists()) {
					new File(".\\" + folderName).getCanonicalFile().mkdir();
				}

				ImageIO.write(bi, "png", filepath);
			} catch (IIOException e) {
				System.out.println("Damn it all");
			} catch (IOException e) {
				System.out.println("Damn it all again");
			}
		}
	}
}

