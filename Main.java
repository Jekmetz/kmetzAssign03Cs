import java.awt.Color;

public class Main{
	public static void main(String [] args){
		ImageUtils imgu = new ImageUtils();
		Color[][] img = imgu.loadImage("LennaCV.png");
		imgu.addImage(img,"TestTab");
		
		imgu.addImage(grayScale(img,"l"),"GrayScale");
		imgu.display();
	}
	
	public static Color[][] grayScale(Color[][] img, String type){
		int r = 0;
		int g = 0;
		int b = 0;
		int y = 0;

		for(int row = 0; row < img.length; row++){
			for(int col = 0; col < img[row].length; col++){
				r = img[row][col].getRed();
				g = img[row][col].getGreen();
				b = img[row][col].getBlue();
				
				switch(type){
				case "l":

					break;

				case "h":
					
					break;

				default:

					break;
				}
			}
		}	
		return img;
	}

}
