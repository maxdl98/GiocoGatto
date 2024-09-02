package Lavoro;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CaricatoreImmagini {
 BufferedImage image;
 
 
 public BufferedImage caricoImmagini(String posizione) throws Exception {
	 try {
		image = ImageIO.read(getClass().getResource(posizione));
	} catch (IOException e) {
		System.out.println("Immagine alla posizione" + posizione + "caricata correttamente");
		e.printStackTrace();
	}
	 return image;
	 
 }
 
}
