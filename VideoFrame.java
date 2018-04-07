import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class VideoFrame {
	private JFrame frame;
	private JLabel label;
	private Timer timer = new Timer();

	public VideoFrame() {
		frame = new JFrame("Video Frame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		label = new JLabel();
		frame.setContentPane(label);
	}

	public void display() {
		frame.pack();
		frame.setMinimumSize(frame.getPreferredSize());
		frame.setVisible(true);
	}

	public void repeatedImages(Images imgs,long framerate) {
		TimerTask task = new TimerTask() {
			int index = 0;
			public void run() {
				index++;
				ImageIcon icon = new ImageIcon(imgs.getImage(index%imgs.getLength()));
				label = new JLabel(icon);
				frame.setContentPane(label);
				frame.pack();
			}
		};
		
		timer.schedule(task,0,1000/framerate);
	}

}

