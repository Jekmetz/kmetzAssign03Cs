import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;

public class Main {
	public static void main(String[] args) {
		ImageUtils imgu = new ImageUtils();
		Color[][] img = imgu.loadImage("LennaCV.png");
		String folderName = "pictureFolder";
		imgu.addImage(img, "TestTab");

		imgu.addImage(grayScale(img, "light"), "Ligtness GrayScale");
		imgu.addImage(grayScale(img, "ave"), "Average GrayScale");
		imgu.addImage(grayScale(img, "lumin"), "Luminosity GrayScale");
		imgu.addImage(invertColor(img), "Inverted Color");
		
		//pictureMess(img,folderName,15,75);	//Uncomment out the line before this if you want to make a filefolder with name String folderName, img Color[][] img, with 15 pngs all messed up with a color deviation of 75 :)

		imgu.display();
		
		Images imgArray = new Images();
		
		try {
			imgArray.addImagesFromFolder(new File(".\\" + folderName).getCanonicalPath());
		} catch (IOException e) {
			System.out.println("Listen, we just could not find that folder.");
		}
		
		VideoFrame vidFrame = new VideoFrame();
		vidFrame.display();
		vidFrame.repeatedImages(imgArray,30);

	}

	public static Color[][] grayScale(Color[][] orig, String type) {

		Color[][] img = ImageUtils.cloneArray(orig);
		int r = 0;
		int g = 0;
		int b = 0;
		int gray = 0;

		for (int row = 0; row < img.length; row++) {
			for (int col = 0; col < img[row].length; col++) {
				r = img[row][col].getRed();
				g = img[row][col].getGreen();
				b = img[row][col].getBlue();
				gray = 0;

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

