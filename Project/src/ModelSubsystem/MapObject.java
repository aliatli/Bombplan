package ModelSubsystem;

import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class MapObject {

	private ImageIcon icon;
	protected boolean destroyable;
	protected int x;
	protected int y;
	
	public boolean isDestroyable() {
		return this.destroyable;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	protected void getIconFromFile(String path){
		//try {
			icon = new ImageIcon(path);
		//	File img = new File(path);
		//	this.icon = ImageIO.read(img);
			//System.out.println(icon);
	//	} catch (IOException e) {
	//			e.printStackTrace();
		//}
	}

	public void draw(Graphics g) {

		icon.paintIcon(null, g, this.getY()*64, this.getX()*64);
		//g.drawImage(icon, this.getX(), this.getY(), 64, 64, null);
	}
}