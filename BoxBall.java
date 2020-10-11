import java.awt.*;
import java.awt.geom.*;
import java.util.Random;
import java.util.ArrayList;
/**
 * Class BoxBall creates a ball that bounces inside a box
 * 
 * @author Craig Hussey
 *
 * @version 2020.10.10
 */

public class BoxBall
{
    private Random rand = new Random();
    private Ellipse2D.Double circle;
    private Color color;
    private static final int diameter = 10;
    private int xPosition;
    private int yPosition;
    private Canvas thisCanvas;
    private int ySpeed = 0;  
    private int xSpeed = 0;
    private int boxSize = 100;
    int originX = 300; int originY = 250;
    int speedMultiplier = 3;

    /**
     * Constructor for objects of class BoxBall
     *@constructor
     */
    public BoxBall( int myBoxSize, Canvas myCanvas)
    {
        //sets a random initial x speed
        if (rand.nextInt(2)==1)
        xSpeed = 1; else xSpeed = -1;
        //sets a random initial y speed
        if (rand.nextInt(2)==1)
        ySpeed = 1; else ySpeed = -1;
        //canvas to draw on
        thisCanvas = myCanvas;
        //box size to bounce in
        boxSize = myBoxSize;
        //sets balls to random color
        color = new Color(getRandomMinMax(20,180), getRandomMinMax(20,180), getRandomMinMax(20,180));
        //ball x position random inside new canvas
        xPosition = getRandomMinMax((originX - myBoxSize) + diameter, (originX + myBoxSize) - diameter);
        //ball y position random inside new canvas
        yPosition = getRandomMinMax((originY - myBoxSize) + diameter, (originY + myBoxSize) - diameter);
    }

    /**
     * Draw this ball at its current position onto the canvas.
     **/
    public void draw()
    {
        thisCanvas.setForegroundColor(color);
        thisCanvas.fillCircle(xPosition, yPosition, diameter);
    }

    /**
     * Erase this ball at its current position.
     **/
    public void erase()
    {
        thisCanvas.eraseCircle(xPosition, yPosition, diameter);
    }    

    /**
     * Move this ball according to its position and speed and redraw.
     **/
    public void move()
    {
        // remove from canvas at the current position
        erase();  
        // compute new position
        yPosition += ySpeed * speedMultiplier;
        xPosition += xSpeed * speedMultiplier;
        
        if (xPosition > (originX + boxSize) - diameter || xPosition < (originX - boxSize) + diameter)
        xSpeed = xSpeed * -1;
        if (yPosition > (originY + boxSize) - diameter || yPosition < (originY - boxSize) + diameter)
        ySpeed = ySpeed * -1;
        
        // draw again at new position
        draw();
    }    

    /**
     * return the horizontal position of this ball
     */
    public int getXPosition()
    {
        return xPosition;
    }

    /**
     * return the vertical position of this ball
     */
    public int getYPosition()
    {
        return yPosition;
    }  
    
    /**
     * gets a random value between two values {min, max(inclusive)}
     * @method
     */
    private int getRandomMinMax(int min, int max)
    {
        //check if max is greater than min
        if (max>=min)
        {
            //store a random value between a min and max {inclusive}
            int randMinMax = rand.nextInt(max + 1 - min) + min;
            //return value
            return randMinMax;
            //error if max is less than min and returns a 0
        } else System.out.println("Error; returning: "); return 0;
    }
}
