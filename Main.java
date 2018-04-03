import java.awt.Color;

public class Main{
	public static void main(String [] args){
		ImageUtils imgu = new ImageUtils();
		Color[][] img = imgu.loadImage("LennaCV.png");
		imgu.addImage(img,"TestTab");
		imgu.display();
	}
}
