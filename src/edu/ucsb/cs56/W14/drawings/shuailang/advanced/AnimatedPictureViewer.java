package edu.ucsb.cs56.w14.drawings.shuailang.advanced;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
public class AnimatedPictureViewer {

    private DrawPanel panel = new DrawPanel();
    
    private showerHead sh = new showerHead(100, 100, 60, 8);
    private water w = new water(100, 100, 60, 8);
    Thread anim;   
    
    private int x = 100;
    private int y = 100;
    
    private int dy = 5;
    private int dx = 5;
    private double dangle = Math.PI/20;
    private double angle = 0f;
    private double sc = 0.8;
    private double dsc = 0;
    private Color color = Color.RED;
    public static void main (String[] args) {
      new AnimatedPictureViewer().go();
    }

    public void go() {
      JFrame frame = new JFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      frame.getContentPane().add(panel);
      frame.setSize(640,480);
      frame.setVisible(true);
      
      frame.getContentPane().addMouseListener(new MouseAdapter() {
        public void mouseEntered(MouseEvent e){
        System.out.println("mouse entered");
          anim = new Animation();
          anim.start();
        }

        public void mouseExited(MouseEvent e){        
          System.out.println("Mouse exited");
          // Kill the animation thread
          anim.interrupt();
          while (anim.isAlive()){}
          anim = null;         
          panel.repaint();        
        }
      });
      
    } // go()

    class DrawPanel extends JPanel {
       public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;

         // Clear the panel first
          g2.setColor(Color.white);
          g2.fillRect(0,0,this.getWidth(), this.getHeight());

          // Draw the Ipod
          g2.setColor(color);
	  showerHead test = new showerHead(x, y, 60, 8);
	  water test1 = new water(x, y, 60, 8);
	  //modification starts here
	  //	  g2.translate(180, -50);
      	 
	  AffineTransform tx1 = new AffineTransform();
	  tx1.scale(sc, sc);

	  g2.setTransform(tx1);
	  g2.rotate(angle);
          g2.translate(x,y);
	  g2.drawString("A Crazy shower head by Shuai Lang", 20,20);
	  g2.draw(test);
	  g2.draw(test1);

       }
    }
    
    class Animation extends Thread {
      public void run() {
        try {
          while (true) {
            // Bounce off the walls
	      /*
	      if (x >= 400) { dx = -5; }
	      if (x <= 50) { dx = 5; }
	      
	      if (y >=400) {dy = -5;}
	      if (y <=50) {dy = 5;}
	      */
	      if (angle <= -Math.PI/7) {dangle = Math.PI/60; color = Color.RED;}
	      if (angle > Math.PI/5) {dangle = -Math.PI/60; color = Color.BLUE;}
	      if (sc <=0.9) { dsc = 0.01;}
	      if (sc >=2.2) { dsc = -0.01;}
	      //x += dx;                
	      //y += dy;
	      angle += dangle;
	      sc += dsc;
	      panel.repaint();
	      Thread.sleep(50);
          }
        } catch(Exception ex) {
	    if (ex instanceof InterruptedException) {
		// Do nothing - expected on mouseExited
	    } else {
		ex.printStackTrace();
		System.exit(1);
	    }
        }
      }
    }
    
}
