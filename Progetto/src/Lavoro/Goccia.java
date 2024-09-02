package Lavoro;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Goccia extends Thread {
private int x;
private int y;
private int larghezza;
private int altezza;
private int velocita;
BufferedImage img_pioggia;
private boolean attivo;
private Gioco main;
public Goccia(int x, int y, int larghezza, int altezza, int velocita, BufferedImage img_pioggia, Gioco main) {
	this.x = x;
	this.y = y;
	this.larghezza = larghezza;
	this.altezza = altezza;
	this.velocita = velocita;
	this.img_pioggia = img_pioggia;
	this.main = main;
	this.start();
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

public void run() {
	attivo = true;
	while(attivo) {
		aggiorna();
		try {
			Thread.sleep(80);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

private void aggiorna() {
	y+= velocita;
}

public void disegna(Graphics g) {
	g.drawImage(img_pioggia, x, y,larghezza,altezza,main);
}

public Rectangle getBordi() {
	return new Rectangle(x,y,larghezza,altezza);
}








}
