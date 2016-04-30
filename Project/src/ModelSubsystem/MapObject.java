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

	protected int draw_x;
	protected int draw_y;

	/**
	 *
	 * @return destroyable
     */
	public boolean isDestroyable() {
		return this.destroyable;
	}

	/**
	 *
	 * @return x
     */
	public int getX() {
		return this.x;
	}

	/**
	 *
	 * @return y
     */
	public int getY() {
		return this.y;
	}

	/**
	 *
	 * @param x
     */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 *
	 * @param y
     */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 *
	 * @return draw_x
     */
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

	/**
	 *
	 * @return draw_y
     */
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

	/**
	 *
	 * @param path
     */
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

	/**
	 *
	 * @param g
     */
	public void draw(Graphics g) {

		icon.paintIcon(null, g, this.getX()*64, this.getY()*64);

	}
}