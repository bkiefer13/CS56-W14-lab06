package edu.ucsb.cs56.w14.drawings.dconard.advanced;
import java.awt.geom.GeneralPath; // combinations of lines and curves
import java.awt.geom.AffineTransform; // translation, rotation, scale
import java.awt.Shape; // general class for shapes

// all imports below this line needed if you are implementing Shape
import java.awt.geom.Point2D; 
import java.awt.geom.Rectangle2D;
import java.awt.Rectangle;
import java.awt.geom.PathIterator;
import java.awt.geom.AffineTransform;

import edu.ucsb.cs56.w14.drawings.utilities.ShapeTransforms;
import edu.ucsb.cs56.w14.drawings.utilities.GeneralPathWrapper;
import edu.ucsb.cs56.w14.drawings.dconard.simple.*;

/**
   A Coffee Cup (wrapper around a General Path, implements Shape)

   This provides an example of how you can start with the coordinates
   of a hard coded object, and end up with an object that can be
   drawn anywhere, with any width or height.
   
      
   @author Phill Conrad 
   @version for CS56, W11, UCSB, 02/23/2011
   
*/
public class CoffeeCup extends GeneralPathWrapper implements Shape
{

    
    /**
     * Constructor for objects of class CoffeeCup
     */
    public CoffeeCup(double x, double y, double width, double height)
    {
    
        // Specify the upper left corner, and the 
        //  width and height of the original points used to 
        //  plot the *hard-coded* coffee cup
        
        final double ORIG_ULX = 100.0; 
        final double ORIG_ULY = 100.0; 
        final double ORIG_HEIGHT = 300.0; 
        final double ORIG_WIDTH = 400.0; 
                
        GeneralPath leftSide = new GeneralPath();
      
        // left side of cup
	leftSide.moveTo(200,200);



	leftSide.moveTo(195,200);
	leftSide.lineTo(195,230);//neck
	leftSide.lineTo(150,230);//shoulder
	leftSide.lineTo(140,250);//shoulder slant
	leftSide.lineTo(140,340);//armleft
	leftSide.lineTo(165,340);//armbottom
	leftSide.lineTo(160,260);//armright
	leftSide.lineTo(170,270);//armpit
	leftSide.lineTo(170,350);//chest
	leftSide.lineTo(160,510);//legleft
	leftSide.lineTo(185,510);//legbottom
	leftSide.lineTo(200,360);//legright
	leftSide.lineTo(220,360);//middle
	/*			
        leftSide.moveTo(200,400);
        leftSide.lineTo(160,360);
        leftSide.lineTo(130,300);
        leftSide.lineTo(100,200);
        leftSide.lineTo(100,100);
        
        GeneralPath topAndBottom = new GeneralPath();
	
        topAndBottom.moveTo(100,100);
        topAndBottom.lineTo(500,100); // top of cup
        
        topAndBottom.moveTo(200,400);
        topAndBottom.lineTo(400,400); // bottom of cup
	*/
        Shape rightSide = ShapeTransforms.horizontallyFlippedCopyOf(leftSide);
       
        // after flipping around the upper left hand corner of the
        // bounding box, we move this over to the right by 400 pixels
       
        rightSide = ShapeTransforms.translatedCopyOf(rightSide, 140.0, 0.0);
	Circle head = new Circle(210,170,35);
        // now we put the whole thing together into a single path.
       
        GeneralPath wholeCup = new GeneralPath ();
	wholeCup.append(head,false);
	// wholeCup.append(topAndBottom, false);
        wholeCup.append(leftSide, false);
        wholeCup.append(rightSide, false);

        // translate to the origin by subtracting the original upper left x and y
        // then translate to (x,y) by adding x and y
        
        Shape s = ShapeTransforms.translatedCopyOf(wholeCup, -ORIG_ULX + x, -ORIG_ULY + y);
 
	// scale to correct height and width
        s =  ShapeTransforms.scaledCopyOf(s,
					  width/ORIG_WIDTH,
					  height/ORIG_HEIGHT) ;
	 
	// Use the GeneralPath constructor that takes a shape and returns
	// it as a general path to set our instance variable cup
        
	this.set(new GeneralPath(s));
        
    }

}
