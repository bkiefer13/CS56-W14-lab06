package edu.ucsb.cs56.w14.drawings.shuailang.advanced;
import java.awt.geom.GeneralPath; // combinations of lines and curves
import java.awt.geom.AffineTransform; // translation, rotation, scale
import java.awt.Shape; // general class for shapes

// all imports below this line needed if you are implementing Shape
import java.awt.geom.Point2D; 
import java.awt.geom.Line2D; 
import java.awt.geom.Rectangle2D;
import java.awt.Rectangle;
import java.awt.geom.PathIterator;
import java.awt.geom.AffineTransform;

import edu.ucsb.cs56.w14.drawings.utilities.ShapeTransforms;
import edu.ucsb.cs56.w14.drawings.utilities.GeneralPathWrapper;
/**
   water 
      
   @author Shuai Lang
   @version for CS56, W14, UCSB, 02/25/2014
   
*/
public class water extends GeneralPathWrapper implements Shape
{
    /**
     * Constructor for objects of class CoffeeCup
     */
    public water(double x, double y, double width, double height)
    {
	// construct the basic house shell
	
	// get the GeneralPath that we are going to append stuff to
	GeneralPath gp = this.get();
	
	double hose1height =  height;

	Line2D.Double Water1 =
	    new Line2D.Double (x+2.8*hose1height, y+6*hose1height,
			       x+2.8*hose1height, y+17*hose1height);
	Line2D.Double Water2 =
            new Line2D.Double (x+0.5*hose1height, y+6*hose1height,
                               x+0.5*hose1height, y+13*hose1height);
	Line2D.Double Water3 =
            new Line2D.Double (x-2.8*hose1height, y+6*hose1height,
                               x-2.8*hose1height, y+16*hose1height);
	Line2D.Double Water4 =
            new Line2D.Double (x+2*hose1height, y+10*hose1height,
                               x+2*hose1height, y+22*hose1height);
	Line2D.Double Water5 =
	    new Line2D.Double (x-2*hose1height, y+10*hose1height,
			       x-2*hose1height, y+22*hose1height);
	
	
	// add the windows to the house
	// Look up the meaning of the second parameter of append
	// (Hint--is a method of "GeneralPath")

        GeneralPath wholeWater = this.get();
         wholeWater.append(Water1, false);
	 wholeWater.append(Water2, false);
	 wholeWater.append(Water3, false);
	 wholeWater.append(Water4, false);
	 wholeWater.append(Water5, false);


    }

}
