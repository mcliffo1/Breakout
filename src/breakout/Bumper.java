package breakout;
import java.awt.Color;

import edu.macalester.graphics.Rectangle;
/**
 * This is the bumper/paddle in breakout that bounces the ball. Controlled by the user.
 */
public class Bumper {

    private final double WIDTH = 90;
    private final double HEIGHT = 20;
    private Rectangle bump;
    private double x = 0;
    private double y = 0;
    
    /**
     * Constructor for our bumper. Just a rectangle :).
     */
    public Bumper(){
        bump = new Rectangle(x, y, WIDTH, HEIGHT);
        bump.setFillColor(Color.red);
    }
    
    

    /**
     * Compares the top, right, and left points of our ball with the sides of the bumper.
     * @param ball Our ball object.
     * @return a number corresponding to the side struck by the ball.
     */
    public int intersects(Ball ball) {
        if ((ball.getCenterX() + 10 >= this.getX() && ball.getCenterX() + 10 <= this.getX() + WIDTH) && ball.getCenterY() >= this.getY() && ball.getCenterY() <= this.getY() + HEIGHT){
            // right point hit
            return 1;
        }
        else if ((ball.getCenterX() - 10 >= this.getX() && ball.getCenterX() - 10 <= this.getX() + WIDTH) && ball.getCenterY() >= this.getY() && ball.getCenterY() <= this.getY() + HEIGHT){
            // left point hit
            return 2;
        }
        else if ((ball.getCenterX() >= this.getX() && ball.getCenterX() <= this.getX() + WIDTH) && ball.getCenterY() + 10 >= this.getY() && ball.getCenterY() + 10 <= this.getY() + HEIGHT){
            // top point hit
            return 3;
        }
        else{
            return 5;
        }
    }
        public Rectangle getBumperShape(){
        return bump;
    }

    public double getX(){
        return bump.getX();
    }
    public double getY(){
        return bump.getY();
    }
}


