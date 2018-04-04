import java.awt.Color;
import java.lang.Math;

public class Main{
	public static void main(String [] args){
		ImageUtils imgu = new ImageUtils();
		Color[][] img = imgu.loadImage("LennaCV.png");
		imgu.addImage(img,"TestTab");
		
		Color [][] lightGrayScale = imgu.cloneArray(img);
		Color [][] aveGrayScale = imgu.cloneArray(img);
		Color [][] luminGrayScale = imgu.cloneArray(img);
		
		imgu.addImage(grayScale(lightGrayScale,"light"),"Ligtness GrayScale");
		imgu.addImage(grayScale(aveGrayScale,"ave"),"Average GrayScale");
		imgu.addImage(grayScale(luminGrayScale,"lumin"),"Luminosity GrayScale");
		imgu.display();
	}
	
	public static Color[][] grayScale(Color[][] img, String type){
		int r = 0;
		int g = 0;
		int b = 0;
		int gray = 0;

		for(int row = 0; row < img.length; row++){
			for(int col = 0; col < img[row].length; col++){
				r = img[row][col].getRed();
				g = img[row][col].getGreen();
				b = img[row][col].getBlue();
				gray = 0;
				
				switch(type){
				case "light":
					gray = (Math.max(r,Math.max(g,b)) + Math.min(r,Math.min(g,b)))/2;
					break;

				case "ave":
					gray = (r + g + b)/3;
					break;

				default:
					gray = (int)Math.round(.21*r + .72*g + .07*b);
					break;
				}
				
				img[row][col] = new Color(gray,gray,gray);
			}
		}	
		return img;
	}

}
