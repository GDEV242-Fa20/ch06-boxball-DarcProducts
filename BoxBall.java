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
    private Canvas thisCanvas;
    private int ySpeed = 0;  
    private int xSpeed = 0;
    private int boxSize = 200;
    int originX = 300; int originY = 250;
    int thisSpeed = 2;
    private int xPosition;
    private int yPosition;

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
        color = new Color(getRandomMinMax(10,200), getRandomMinMax(10,200), getRandomMinMax(10,200));
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
        // remove at current position
        erase();  
        //set speed depending on box size
        if (boxSize>200) thisSpeed = 9;
        else if (boxSize>100&&boxSize<=200) thisSpeed = 6;
        else thisSpeed = 3;
        //get new position
        yPosition += ySpeed * thisSpeed;
        xPosition += xSpeed * thisSpeed;
        //stores locations of walls
        int cielPosition = originY - boxSize;
        int rWallPosition = originX - boxSize;
        int lWallPosition = originX + boxSize;
        int groundPosition = originY + boxSize;
        
        //checks to see if ball is within bounds {had to adjust a small amount, wasnt detecting top left wall close enough}
        if (xPosition > lWallPosition - 14 || xPosition < rWallPosition + 4)
        xSpeed = -xSpeed;
        if (yPosition > groundPosition - 14 || yPosition < cielPosition + 4)
        ySpeed = -ySpeed; 
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
