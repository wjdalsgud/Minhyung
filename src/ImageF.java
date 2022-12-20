import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImageF extends JPanel{
ImageIcon i=new ImageIcon("image/1.jpg");
Image i1=i.getImage();
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(i1,0,0,getWidth(),getHeight(),this);
	}	

}
