package edu.ucsb.cs56.w14.drawings.ytsai.advanced;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AnimatedPictureViewer {

    private DrawPanel panel = new DrawPanel();
    
    private AngryRobot robo = new AngryRobot(100, 100, 100, 150);
    
    Thread anim;   
    
    private int x = 100;
    private int y = 100;
    
    private int dx = 5;
    private int dy = 5;
    
    Color c = Color.BLUE;

    public static void main (String[] args) {
      new AnimatedPictureViewer().go();
    }

    public void go() {
      JFrame frame = new JFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.getContentPane().add(panel);
      frame.setSize(640,480);
      frame.setVisible(true);
      
      frame.getContentPane().addMouseListener(new MouseAdapter() {
	      public void mouseEntered(MouseEvent e){
		  System.out.println("Mouse on. GO!!!");
		  anim = new Animation();
		  anim.start();
	      }
	      
	      public void mouseExited(MouseEvent e){        
		  System.out.println("Mouse off. STOP ANIMATION!");
		  // Kill the animation thread
		  anim.interrupt();
		  //while (anim.isAlive()){}
		  anim = null;         
		  panel.repaint();        
	      }
	      
	      public void mouseClicked(MouseEvent e){
		  System.out.println("Mouse clicked. Changing colors.");
		  if(c==Color.BLUE){
		      c=Color.GREEN;
		      panel.repaint();
		      return;
		  }
		  if(c==Color.GREEN){
		      c=Color.RED;
		      panel.repaint();
		      return;
		  }
		  else
		      c=Color.BLUE;
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
	    
	    // Draw the AngryRobot
	    g2.setColor(c);
	    AngryRobot test = new AngryRobot(x, y, 100, 150);
	    g2.draw(test);
	}
    }
    

    class Animation extends Thread {
	@Override
      public void run() {
        try {
          while (true) {
            // Bounce off the walls

	      if (x >= 640-100-50){
		  dx = -5;
	      }
	      if (x <= 50){ 
		  dx = 5; 
	      }
	      if (y >= 480-150-50-69){
		  dy = -5;
	      }
	      if (y <= 50){
		  dy = 5;
	      }
            
            x += dx;
	    y += dy;
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
