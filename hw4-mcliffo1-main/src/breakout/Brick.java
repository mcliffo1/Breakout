package breakout;

import java.awt.Color;

import edu.macalester.graphics.Rectangle;

/**
 * The bricks from breakout!
 */
public class Brick {

    private final double width = 50;
    private final double height = 20;
    private Rectangle brick;

    /**
     * Our brick constructor. We are gonna see a lot of these bricks.
     */
    public Brick(double x, double y){
        
        brick = new Rectangle(x, y, width, height);
        brick.setFillColor(Color.CYAN);
        
    }


    /**
     * Compares the top, right, bottom and left points of our ball with the sides of the brick.
     * @param ball Our ball object.
     * @return a number corresponding to the side struck by the ball.
     */
    public int intersects(Ball ball) {
        if ((ball.getCenterX() + 30 >= this.getX() && ball.getCenterX() + 30 <= this.getX() + width) && ball.getCenterY() >= this.getY() && ball.getCenterY() <= this.getY() + height){
            // right point hit
            return 1;
        }
        else if ((ball.getCenterX() - 30 >= this.getX() && ball.getCenterX() - 30 <= this.getX() + width) && ball.getCenterY() >= this.getY() && ball.getCenterY() <= this.getY() + height){
            // left point hit
            return 2;
        }
        else if ((ball.getCenterX() >= this.getX() && ball.getCenterX() <= this.getX() + width) && ball.getCenterY() + 30 >= this.getY() && ball.getCenterY() + 30 <= this.getY() + height){
            // top point hit
            return 3;
        }
        else if ((ball.getCenterX() >= this.getX() && ball.getCenterX() <= this.getX() + width) && ball.getCenterY() - 30 >= this.getY() && ball.getCenterY() - 30 <= this.getY() + height){
            // bottom point hit
            return 4;
        }
        else{
            return 5;
        }
    }
    public Rectangle getBrickShape(){
        return brick;
    }

    public double getX(){
        return brick.getX();
    }
    public double getY(){
        return brick.getY();
    }
}


