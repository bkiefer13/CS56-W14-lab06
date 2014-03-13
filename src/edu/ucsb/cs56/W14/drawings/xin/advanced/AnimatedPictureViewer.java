package edu.ucsb.cs56.w14.drawings.xin.advanced;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AnimatedPictureViewer {

    private DrawPanel panel = new DrawPanel();

    private BalloonWithString balloon = new BalloonWithString(100, 100, 40);

    Thread anim;

    private int x = 100;
    private int y = 100;
    private int z = 40;

    private int dx = 5;
    private int dy = 5;
    private int dz = 2;

    private Color color = Color.YELLOW;

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
		    //Kill the animation thread
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
	    // Draw the Balloon                                                                                                                                       	
       	    Stroke thick = new BasicStroke (4.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
	    Stroke orig=g2.getStroke();
	    g2.setStroke(thick);
	    g2.setColor(color);
	    BalloonWithString test = new BalloonWithString(x, y, z);
	    g2.draw(test);
		    
      	}
    }

    class Animation extends Thread {
	public void run() {
	    try {
		while (true) {
		    // Bounce off the walls                                                                                                                                                                                                                                                                               
		    if (x >= 350){
			    dx = -5;
			    dz=-2;
			    color=Color.RED;
		    }

		    if (x <= 50) {
			    dx = 5;
			    dz=1;
			    color = Color.YELLOW;
		    }

		    if(y>= 350){
			dy=-5;
			color = Color.PINK;
		    }

		    if(y<= 150){
			dy=5;
			color=Color.RED;
		    }

		    x += dx;
		    y += dy;
		    z += dz;
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
