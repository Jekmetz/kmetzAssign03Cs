import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Images {
	private BufferedImage[] img = null;

	public void addImagesFromFolder(String filepath) {
		File folder = null;
		File[] filenames = null;
		try {
			folder = new File(filepath).getCanonicalFile();
			filenames = folder.listFiles();
		} catch (IOException e) {
			System.out.println("Something went wrong");
		}
		
		img = new BufferedImage[filenames.length];
		
		for (int i = 0; i < filenames.length; i++) {

			img[i] = loadImage(filenames[i].toString());
			
		}
	}

	private BufferedImage loadImage(String filepath) {
		// Load in the image.
		BufferedImage buffImg = loadBufferedImage(filepath);
		return buffImg;
	}

	private static BufferedImage loadBufferedImage(String filepath) {
		// A BufferedImage initialization.
		BufferedImage img = null;

		// Try to read an image from the specified path.
		try {
			// Read the (image) File the path directs to.
			img = ImageIO.read(new File(filepath));
		} catch (IOException e) {
			System.out.println("Could not load the image, please ensure the filepath" + " was properly specified.");
			e.printStackTrace();
			System.exit(1);
		}

		return img;
	}
	
	public BufferedImage getImage(int index) {
		return img[index];		
	}
	
	public int getLength() {
		return img.length;
	}

}

