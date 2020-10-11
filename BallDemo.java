import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;
/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Craig Hussey
 * @version 2020.10.10
 */

public class BallDemo   
{
    private Canvas myCanvas;
    //random generator to use
    private Random rand = new Random();
    //box size
    private int boxSize = 200;
      
    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     * @constructor
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
    }

    /**
     * Simulate two bouncing balls
     * @method
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.setForegroundColor(Color.BLACK);
        myCanvas.drawLine(50, ground, 550, ground);

        // create and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        ball2.draw();

        // make them bounce
        boolean finished =  false;
        while (!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }
    
    /**
     * creates balls in a box, set the box size from this method and the box balls will respond 
     * the more balls the longer the simulation will last* 
     * @method
     */
    public void boxBounce(int numberOfBalls)
    {
        boolean isBouncing = true;
        float currentTime = 200f;
        //current balls
        int currentBalls = 0;
        //creates array of balls
        ArrayList<BoxBall> myBalls = new ArrayList<BoxBall>();
        //add a specific number of balls to list and draw them
        while (currentBalls<numberOfBalls)
        {
            //create new box ball and add it to array
            BoxBall thisBall = new BoxBall(boxSize, myCanvas);
            myBalls.add(thisBall);
            //draw ball
            thisBall.draw();
            //add to current balls
            currentBalls++;
        }   
        //while bouncing keep bouncing
        while (isBouncing)
        {
            //sets a time to stop the bouncing balls
            currentTime-=.5f;
            if (currentTime<=0)
            {
                isBouncing = false;
            }
            //keeps the canvas updated
            drawCraigsBox(boxSize);
            for (BoxBall myBall : myBalls)
            {
                //waits 1 millisecond to smooth out animation a little
                myCanvas.wait(1);
                //moves ball
                myBall.move();
            }
        }
    }
        
    /**
     * sets box size; must be a value between 13 and 245
     * @method
     */
    public void setBoxSize(int size)
    {
        //sets box size
        boxSize = size;
        //erases old canvas
        myCanvas.erase();
        //draws new canvas
        drawCraigsBox(boxSize);
    }
    
    /**
     * draws Craigs new canvas with box
     * @method
     */
    private void drawCraigsBox(int thisBoxSize)
    {
        if (thisBoxSize>12&&thisBoxSize<246)
        {
            //origin of canvas
            int originX = 300; int originY = 250;
            //right wall line
            myCanvas.setForegroundColor(Color.BLACK);
            myCanvas.drawLine(originX-thisBoxSize, originY-thisBoxSize, originX-thisBoxSize, originY+thisBoxSize);
            // draw the ground
            myCanvas.setForegroundColor(Color.BLACK);
            myCanvas.drawLine(originX-thisBoxSize, originY+thisBoxSize, originX+thisBoxSize, originY+thisBoxSize);
            //left wall line
            myCanvas.setForegroundColor(Color.BLACK);
            myCanvas.drawLine(originX+thisBoxSize, originY+thisBoxSize, originX+thisBoxSize, originY-thisBoxSize);
            //cieling line
            myCanvas.setForegroundColor(Color.BLACK);
            myCanvas.drawLine(originX+thisBoxSize, originY-thisBoxSize, originX-thisBoxSize, originY-thisBoxSize);
        } else System.out.println("Enter a box size greater than 12 and less than 246");        
    }
}
