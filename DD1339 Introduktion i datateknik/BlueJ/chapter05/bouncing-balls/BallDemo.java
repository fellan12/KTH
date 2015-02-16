import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;

/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class BallDemo   
{
    private Canvas myCanvas;
    private ArrayList<BouncingBall> bounceList;
    private ArrayList<BoxBall> boxList;
    private BouncingBall bounceBall;
    private BoxBall boxBall;
    private int ground;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
    }

    /**
     * 5.63
     * ArrayList känns mest användbar för att du vill lägga bollarna i en lista och sen skriva ut dem
     * och det kan man göra enkelt med index och arraylist, du kan göra det med HashSet/Map,
     * men ArrayLisy kändes enklare.
     * 
     * 5.64
     * Anänder två random variabler (randx och randy) och lägger dem på bollen x- och y-kordinater
     * för att lägga ut dem random, sen lägger randx=300 och randy=250 för det ser ut som att vara
     * top vänster till mitten då Canvasen är 600x500.
     * 
     * 5.62 Change the method bounce in class BallDemo to let the user choose how
     * many balls should be bouncing.
     * 
     * Simulate n-number of bouncing balls
     * 
     * @parameter n the number of balls
     */
    public void bounce(int n)
    {
      bounceList = new ArrayList<BouncingBall>();
               
        myCanvas.setVisible(true);
                       
        // draw the ground
        myCanvas.drawLine(50, 400, 550, 400);
        
        //add balls to ArrayList
        Random randx = new Random();
        Random randy = new Random();
        
        for(int i = 0; i<n; i++){
            bounceBall = new BouncingBall(randx.nextInt(300), randy.nextInt(250), 20, Color.RED, 400, myCanvas);
            bounceList.add(bounceBall);
        }
        
        // draw them and make them bounce
        boolean finished = false;
        while (!finished) {
           finished = true;
           
           //draw the balls
           for(int i= 0; i<bounceList.size();i++){
               BouncingBall showBall = bounceList.get(i);
               showBall.draw();
               
               // move the ball
               if (showBall.getXPosition() < 550) {
                   showBall.move(); // bounce the ball
                   
                   // stop once ball when they reach the end of the screen
                   if (showBall.getXPosition() < 550) {
                       finished = false;
                   }
               }
           }
           myCanvas.wait(50);
        }                       
    }
           
    /**
     * 5.65
     * Write a new method named boxBounce. This method draws a rectangle
     * (the “box”) on screen and one or more balls inside the box. For the balls, do not use
     * BouncingBall, but create a new class BoxBall that moves around inside the box, bouncing
     * off the walls of the box so that the ball always stays inside. The initial position and speed of
     * the ball should be random. The boxBounce method should have a parameter that specifies
     * how many balls are in the box.
     * 
     * 5.66
     * Give the balls in boxBounce random colors.
     * 
     * Simulate n-number of bouncing balls
     * 
     * @parameter n the number of balls
     */
    public void boxBounce(int n){
        boxList = new ArrayList<BoxBall>();
               
        myCanvas.setVisible(true);
        
        // draw box
        myCanvas.drawLine(50, 416, 550, 416); //ground
        myCanvas.drawLine(50, 100, 550, 100); //roof
        myCanvas.drawLine(50, 100, 50, 416); //left wall
        myCanvas.drawLine(550, 100, 550, 416); //right wall
        
        //add balls to ArrayList
        Random randx = new Random();
        Random randy = new Random();
        Random randColor = new Random();
        for(int i = 0; i<n; i++){
            float r = randColor.nextFloat();
            float g = randColor.nextFloat();
            float b = randColor.nextFloat();
            Color randomColor = new Color(r,g,b);
            boxBall = new BoxBall(randx.nextInt(500), randy.nextInt(250), 16, randomColor, 400, 100, 550, 50, myCanvas);
            boxList.add(boxBall);
        }
        
         // draw them and make them bounce
        boolean finished = false;
        while (!finished) {
           //draw the balls
           for(int i= 0; i<boxList.size();i++){
               BoxBall showBall = boxList.get(i);
               showBall.draw();
               showBall.move();
               
           }
           myCanvas.wait(30);
        }     
    }
}
