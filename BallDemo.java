import java.awt.Color;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
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
    private int boxSize = 100;
    //currently not able to turn off
    private boolean isBouncing = true;
  
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
     * creates balls in a box
     * @method
     */
    public void boxBounce(int numberOfBalls)
    {
        //draws canvas and sets box size
        //current balls
        int currentBalls = 0;
        //creates array of balls
        CopyOnWriteArrayList<BoxBall> myBalls = new CopyOnWriteArrayList<BoxBall>();
        //add a specific number of balls to list and draw them
        while (currentBalls<numberOfBalls)
        {
            BoxBall thisBall = new BoxBall(boxSize, myCanvas);
            myBalls.add(thisBall);
            thisBall.draw();
            currentBalls++;
        }   
        //while bouncing keep bouncing
        while (isBouncing)
        {
            drawCraigsBox(boxSize);
            for (BoxBall myBall : myBalls)
            {
                myCanvas.wait(1);
                myBall.move();
            }
        }
    }
        
    /**
     * sets box size
     * @mthod
     */
    public void setBoxSize(int size)
    {
        boxSize = size;
        drawCraigsBox(boxSize);
    }
    
    /**
     * draws Craigs new canvas
     * @method
     */
    private void drawCraigsBox(int thisBoxSize)
    {
        //erase old canvas before drawing on new one
        if (thisBoxSize>2&&thisBoxSize<246)
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
        } else System.out.println("Enter a box size greater than 2 and less than 246");        
    }
}
