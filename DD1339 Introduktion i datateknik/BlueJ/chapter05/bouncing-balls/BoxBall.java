import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

/**
 * Class BouncingBall - a graphical ball that observes the effect of gravity. The ball
 * has the ability to move. Details of movement are determined by the ball itself. It
 * will fall downwards, accelerating with time due to the effect of gravity, and bounce
 * upward again when hitting the ground.
 *
 * This movement can be initiated by repeated calls to the "move" method.
 * 
 * @author Felix De Silva

 * @version 21 Nov 2013
 */

public class BoxBall
{
    private static final int GRAVITY = 3;  // effect of gravity
    private Ellipse2D.Double circle;
    private Color color;
    private int ballDegradation = 2;
    private int diameter;
    private int xPosition;
    private int yPosition;
    private final int groundPosition;      // y position of ground
    private final int roofPosition;         // y position of roof
    private final int rightWallPosition;    //x position of right wall
    private final int leftWallPosition;     // x position of left wall
    private Canvas canvas;
    private int ySpeed = 5;
    private int xSpeed = 5;
    private Random randSpeed;

    /**
     * Constructor for objects of class BouncingBall
     *
     * @param xPos  the horizontal coordinate of the ball
     * @param yPos  the vertical coordinate of the ball
     * @param ballDiameter  the diameter (in pixels) of the ball
     * @param ballColor  the color of the ball
     * @param groundPos  the position of the ground (where the wall will bounce)
     * @param drawingCanvas  the canvas to draw this ball on
     */
    public BoxBall(int xPos, int yPos, int ballDiameter, Color ballColor,
                        int groundPos, int roofPos, int rightPos, int leftPos, Canvas drawingCanvas)
    {
        xPosition = xPos;
        yPosition = yPos;
        color = ballColor;
        diameter = ballDiameter;
        groundPosition = groundPos;
        roofPosition = roofPos;
        rightWallPosition = rightPos;
        leftWallPosition = leftPos;
        canvas = drawingCanvas;
    }

    /**
     * Draw this ball at its current position onto the canvas.
     **/
    public void draw()
    {
        canvas.setForegroundColor(color);
        canvas.fillCircle(xPosition, yPosition, diameter);
    }

    /**
     * Erase this ball at its current position.
     **/
    public void erase()
    {
        canvas.eraseCircle(xPosition, yPosition, diameter);
    }    

    /**
     * Move this ball according to its position and speed and redraw.
     **/
    public void move()
    {
        Random randSpeed = new Random();
        
        // remove from canvas at the current position
        erase();
            
        // compute new position
        
        yPosition += ySpeed;
        xPosition += xSpeed;

        // check if it has hit the ground
        if(yPosition >= (groundPosition)) {
            yPosition = (int)(groundPosition);
            ySpeed = - ySpeed;
        }else if(yPosition <=(roofPosition)) {
            yPosition = (int)(roofPosition);
            ySpeed = - ySpeed;
        }else if(xPosition >= (rightWallPosition - diameter)) {
            xPosition = (int)(rightWallPosition - diameter);
            xSpeed = -xSpeed;
        }else if(xPosition <= (leftWallPosition)) {
            xPosition = (int)(leftWallPosition);
            xSpeed = -xSpeed;
        }

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
}
