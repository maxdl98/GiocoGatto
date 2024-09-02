package Lavoro;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Pioggia extends Thread {
    private int numero;
    private int attesa;
    private boolean piove;
    BufferedImage img_goccia;
    private ArrayList<Goccia> goccia;
    private Random ra;
    Gioco main;
    private final int maxVel = 8;

    public Pioggia(int numero, int attesa, BufferedImage img_goccia, Gioco main) {
        this.numero = numero;
        this.attesa = attesa;
        this.img_goccia = img_goccia;
        this.main = main;

        // Inizializzazione delle variabili
        this.ra = new Random(); // Inizializza l'oggetto Random
        this.goccia = new ArrayList<>(); // Inizializza l'ArrayList di Goccia
    }

    public void run() {
        piove = true;
        while (piove) {
            for (int i = 0; i < numero; i++) {
                goccia.add(new Goccia(ra.nextInt(main.getLarghezza()), -50, 50, 50, ra.nextInt(maxVel + 2), img_goccia, main));
            }
            try {
                Thread.sleep(attesa);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void disegna(Graphics g) {
        for (int i = 0; i < goccia.size(); i++) {
            Goccia temp = goccia.get(i);
            temp.disegna(g);
        }
    }
    
    public ArrayList getGocce() {
    	return goccia;
    }
}
