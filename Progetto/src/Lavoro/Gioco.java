package Lavoro;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;

public class Gioco extends Canvas implements Runnable, KeyListener, MouseMotionListener {

	
    private static final int larghezza = 1400;
    private static final int altezza = 700;
    private static String nome_gioco = "Gioco_FreakyAxel";
    private boolean GiocoAttivo = false;
    private Topo ogg_topo;
    private Giocatore giocatore;
    private Pioggia pioggia;

    BufferedImage sfondo = null;
    BufferedImage img_topo = null;
    BufferedImage ombrello = null;
    BufferedImage goccia = null;

    public Gioco() {
        try {
            CaricaRisorse();
            IniziaGioco();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Gioco gioco = new Gioco();

        JFrame finestra_gioco = new JFrame(nome_gioco);
        Dimension dimensione_finestra = new Dimension(altezza, larghezza);
        finestra_gioco.setPreferredSize(dimensione_finestra);

        finestra_gioco.setResizable(true);
        finestra_gioco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        finestra_gioco.add(gioco);
        finestra_gioco.addKeyListener(gioco);
        gioco.addMouseMotionListener(gioco);

        finestra_gioco.pack();
        finestra_gioco.setVisible(true);

        Thread thread_gioco = new Thread(gioco);
        thread_gioco.start();
    }

    public static int getLarghezza() {
        return larghezza;
    }

    public static int getAltezza() {
        return altezza;
    }

    private void CaricaRisorse() throws Exception {
        CaricatoreImmagini loader = new CaricatoreImmagini();
        sfondo = loader.caricoImmagini("/Immagini/sfondo.jpg");
        img_topo = loader.caricoImmagini("/Immagini/gatto.png");
        ombrello = loader.caricoImmagini("/Immagini/ombrello.png");
        goccia = loader.caricoImmagini("/Immagini/pietra.png");

        System.out.println("Risorse caricate");
    }

    private void IniziaGioco() {
        giocatore = new Giocatore(0, 400, 200, ombrello, this);
        ogg_topo = new Topo(150, 100, 100, 620, img_topo, this);
        ogg_topo.start();
        pioggia = new Pioggia(15, 600, goccia, this);
        pioggia.start();
    }

    @Override
    public void run() {
        GiocoAttivo = true;
        while (GiocoAttivo) {
            aggiorna();
            disegna();
           
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            
        }
        
        if (!GiocoAttivo) {
        	restartGame();
        }
    }

    private void aggiorna() {
        ArrayList<Goccia> gocce = pioggia.getGocce();
        Iterator<Goccia> iterator = gocce.iterator();

        while (iterator.hasNext()) {
            Goccia g = iterator.next();
            if (GestoreCollisioni.controllaCollisione(giocatore, g)) {
                iterator.remove();
                break;
            }
            if (GestoreCollisioni.controllaCollisioneTopo(ogg_topo, g)) {
                iterator.remove();
                this.ogg_topo.vita -= 20;
                break;
            }
        }

        if (controllaSconfitta()) {
            this.GiocoAttivo = false;
            disegna();
        }
       
    }

    private boolean controllaSconfitta() {
        return ogg_topo.vita <= 0;
    }

    private void disegna() {
        BufferStrategy buffer = this.getBufferStrategy();
        if (buffer == null) {
            createBufferStrategy(2);
            return;
        }

        Graphics g = buffer.getDrawGraphics();

        g.drawImage(sfondo, 0, 0, larghezza, altezza, this);

        ogg_topo.disegna(g);
        giocatore.disegna(g);
        pioggia.disegna(g);
        g.drawString("Vita: " + ogg_topo.vita, 25, 25);

        if (!GiocoAttivo) {
            g.setColor(Color.black);
            g.clearRect(0, 0, larghezza, altezza);
            g.setColor(Color.RED);
            g.drawString("Hai perso", larghezza / 2 - 100, altezza / 2 - 50);
            g.drawString("Premi Spazio per ricominciare", larghezza / 2 - 150, altezza / 2);
        }

        g.dispose();
        buffer.show();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
   

    @Override
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();
        switch (keycode) {
            case KeyEvent.VK_LEFT:
                giocatore.spostaSinistra();
                break;

            case KeyEvent.VK_RIGHT:
                giocatore.spostaDestra();
                break;

            case KeyEvent.VK_SPACE:
                if (!GiocoAttivo) {
                	try {
                    restartGame();
                }catch(Exception e1) {
                	e1.printStackTrace();
                }
                break;
        }
        }
        
    }
    private void restartGame() {
        // Reimposta lo stato del gioco
        GiocoAttivo = true;
        ogg_topo = new Topo(150, 100, 100, 620, img_topo, this);
        ogg_topo.start();
        pioggia = new Pioggia(15, 600, goccia, this);
        pioggia.start();
        giocatore = new Giocatore(0, 400, 200, ombrello, this);
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int posizione = (e.getPoint().x) - giocatore.getLarghezza() / 2;
        if (posizione >= 0 && ((posizione + giocatore.getLarghezza() <= larghezza))) {
            giocatore.setX(posizione);
        }
        System.out.println("Il mouse si muove");
    }

  
}
