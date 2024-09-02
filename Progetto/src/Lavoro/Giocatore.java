package Lavoro;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Giocatore {
	private int x;
	private int y;
	private int larghezza;
	private final int velocita = 7;
	private int altezza;
	private boolean attivo;
	BufferedImage image;
	Gioco main;
	
	public Giocatore() {}
	
	
	
	
	public Giocatore(int x, int larghezza, int altezza, BufferedImage image,Gioco main) {
		super();
		this.x = x;
		this.larghezza = larghezza;
		this.altezza = altezza;
		this.image = image;
		y = 500;
		this.main = main;
	}




	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getLarghezza() {
		return larghezza;
	}
	public void setLarghezza(int larghezza) {
		this.larghezza = larghezza;
	}
	public int getAltezza() {
		return altezza;
	}
	public void setAltezza(int altezza) {
		this.altezza = altezza;
	}
	
	public void spostaDestra() {
		if((x + larghezza)<=main.getLarghezza()) {
			x+=velocita;
		}
		
	}
	
	public void spostaSinistra() {
		if(x>=0) {
			x = x - velocita;
			
		}
		
	}
	
	public void disegna(Graphics g) {
		g.drawImage(image,x,y,larghezza,altezza,null);
	}
	
	public Rectangle getBordi() {
		return new Rectangle(x,y,larghezza,altezza);
		
	}
	
	

}
