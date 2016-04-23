package ModelSubsystem;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MapObject {

	public BufferedImage icon;
	public boolean destroyable;
	public int x;
	public int y;

	public boolean isDestroyable() {
		return this.destroyable;
	}

	public void draw(Graphics g) {
		g.drawImage(icon, this.getX(), this.getY(), 64, 64, null);
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
	public void getIconFromFile(String path){
		try {
			File img = new File(path);
			icon = ImageIO.read(img);
			//System.out.println(icon);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}