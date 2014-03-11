package edu.ucsb.cs56.w14.drawings.ziping_he.advanced;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AnimatedPictureViewer {

    private DrawPanel panel = new DrawPanel();
    
    Thread anim;   
    
    private int x = 100;
    private int y = 100;
    private int stroke = 1;
    private Color color = Color.ORANGE;
    
    private int dx = 5;
    private int dy = 5;
    private int dStroke = 1;

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

          // Draw the Flag
          g2.setColor(color);
	  g2.setStroke(new BasicStroke(stroke));
	  FlagWithStick test = new FlagWithStick(x,y,180,90);
          g2.draw(test);
       }
    }
    
    class Animation extends Thread {
      public void run() {
        try {
          while (true) {
	  //To create an animation that makes the flag floate in the panel
            // Bounce off the walls
            if (x >= 440) { dx = -5; 
		color = Color.BLUE;}//turn to blue if hitting the right edge
            if (x <= 10) { dx = 5;
		color = Color.GREEN;}//turn to orange if hitting the left edge            
            x += dx;

	    //Update line thickness
	    if (stroke >= 11){ dStroke = -1;}
	    if (stroke <= 1){ dStroke = 1;}
	    stroke += dStroke;
            panel.repaint();

	    // Bounce up and down in the panel
	    if(y >= 355){ dy = -5;
		color = Color.MAGENTA;}//turn to light_gray if hitting the bottom
	    if(y <= 100){ dy = 5;
		color = Color.ORANGE;}//turn to red if hitting the top
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
