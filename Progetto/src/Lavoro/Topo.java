package Lavoro;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Topo extends Thread {
private int larghezza;
private int altezza;
private boolean attivo;
private int velocita = 7 ;
private static int max_velocita = 7;
public  int vita;
Gioco main;
private int x;
private int y;
BufferedImage img_topo;
public Topo(int larghezza, int altezza,  int x, int y, BufferedImage img_topo, Gioco main) {
	this.larghezza = larghezza;
	this.altezza = altezza;
	attivo = true;
	this.x = x;
	this.y = y;
	this.img_topo = img_topo;
	this.main = main;
	vita = 100;
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




public boolean isAttivo() {
	return attivo;
}




public void setAttivo(boolean attivo) {
	this.attivo = attivo;
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




public BufferedImage getImg_topo() {
	return img_topo;
}




public void setImg_topo(BufferedImage img_topo) {
	this.img_topo = img_topo;
}




public void run() {
	attivo = true;
	while(attivo) {
		aggiorna();
		try {
			Thread.sleep(10);
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

private void aggiorna() {
	Random nrd = new Random();
	
	if(this.x <= 0) {
		velocita = nrd.nextInt(max_velocita) + 1;
		
	     
	}
	else if(this.x >= main.getLarghezza() - this.larghezza) {
		velocita = nrd.nextInt(max_velocita) + 1;
		velocita *= -1;
	}
	x+=velocita;
	
	
}

public void disegna(Graphics g) {
	g.drawImage(img_topo, x, y, larghezza,altezza, main);
	
}


public Rectangle getBordi() {
	return new Rectangle(x,y,larghezza,altezza);
}










}
