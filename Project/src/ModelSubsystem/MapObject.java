package ModelSubsystem;

import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class MapObject {

	protected ImageIcon icon;
	protected boolean destroyable;
	protected int x;
	protected int y;

	public int getDraw_y() {
		return draw_y;
	}

	public void setDraw_y(int draw_y) {
		this.draw_y = draw_y;
	}

	public int getDraw_x() {
		return draw_x;
	}

	public void setDraw_x(int draw_x) {
		this.draw_x = draw_x;
	}

	protected int draw_x;
	protected int draw_y;


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

	public int getIncrementX(){
		if(draw_x < x * 64){
			draw_x += 16;
			return draw_x;
		}
		else if (draw_x > x * 64){
			draw_x -= 16;
			return draw_x;
		}
		return draw_x;
	}

	public int getIncrementY(){
		if(draw_y < y * 64){
			draw_y+=16;
			return draw_y;
		}
		else if (draw_y > y * 64){
			draw_y -=16;
			return draw_y;
		}
		return draw_y;
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

		icon.paintIcon(null, g, this.getX()*64, this.getY()*64);

	}
}