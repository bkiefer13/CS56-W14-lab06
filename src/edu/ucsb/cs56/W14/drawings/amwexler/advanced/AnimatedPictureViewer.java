package edu.ucsb.cs56.w14.drawings.amwexler.advanced;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AnimatedPictureViewer {

    private DrawPanel panel = new DrawPanel();
    
    private Kirby kirby = new Kirby(50,50, 100, 100);
    
    Thread anim;   
    
    private int x = 50;
    private int y = 50;
    
    private int dx = 5;
    private int dy = 5;

    private JFrame frame;

    public static void main (String[] args) {
      new AnimatedPictureViewer().go();
    }

    public void go() {
      frame = new JFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      frame.getContentPane().add(panel);
      frame.setSize(640,480);
      frame.setVisible(true);
      
      frame.getContentPane().addMouseListener(new MouseAdapter() {
        public void mouseEntered(MouseEvent e){
          anim = new Animation();
          anim.start();
        }

        public void mouseExited(MouseEvent e){        
          anim.interrupt();
          while (anim.isAlive()){}
          anim = null;         
          panel.repaint();        
        }
      });
      
    }

    class DrawPanel extends JPanel {
       public void paintComponent(Graphics g) {
	Graphics2D g2 = (Graphics2D) g;
       	g2.setColor(Color.white);
       	g2.fillRect(0,0,this.getWidth(), this.getHeight());
        g2.setColor(Color.PINK);
        Kirby k = new Kirby(x, y, 100,100);
        g2.draw(k);
       }
    }
    
    class Animation extends Thread {
      public void run() {
        try {
          while (true) {
	    if (x >= frame.getWidth()-150) {dx = -3 + (int)(-10*Math.random());}            
	    if(x <= 50) {dx = 3 + (int)(10*Math.random());}

            if (y >= frame.getHeight()-150) {dy = -3 + (int)(-10*Math.random());}                         
            if(y <= 50) {dy = 3 + (int)(10*Math.random());}

	    y += dy;
            x += dx;                
            panel.repaint();
            Thread.sleep(50);
          }
        } catch(Exception ex) {
          if (ex instanceof InterruptedException) {
          } else {
            ex.printStackTrace();
            System.exit(1);
          }
        }
      }
    }
    
}
