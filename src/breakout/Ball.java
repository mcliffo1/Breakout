package breakout;

import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;

/**
 * This is the ball from breakout that bounces and breaks bricks.
 */
public class Ball {
    
    private static final double RADIUS = 30;
    private Ellipse ball;
    private double centerX = 300;
    private double centerY = 450; 
    private double ballDX;
    private double ballDY;
    /**
     * Constructor for our ball.
     * @param centerw x value
     * @param centerh y value
     * @param dx change in x 
     * @param dy change in y
     */
    public Ball(double centerw, double centerh, double dx, double dy){
        ball = new Ellipse(centerX, centerY, RADIUS, RADIUS);
        ball.setFillColor(Color.blue); 

        ballDX = dx;
        ballDY = dy;
        
    }
    /**
     * changes the center of the ball in the given direction, by adding dx or dy to centerx or centery, the conditionals
     * ensure that it is in the bounds, and decide behavior once it touches a wall.
     * @param canvas
     */
    public void moveBall(CanvasWindow canvas){
        double centerX2 = getCenterX() + ballDX;
        double centerY2 = getCenterY() + ballDY;
        if (centerX2 > 0 && centerX2 < 600 && centerY2 > 0 && centerY2 < 800) {
            ball.setCenter(centerX2, centerY2);
            centerX = centerX2;
            centerY = centerY2;
        }
        else if (centerX2 > 0 && centerX2 < 600){
            ballDY = -(ballDY);
            ball.setCenter(centerX2, centerY2);

        }
        else {
            ballDX = -(ballDX);
            ball.setCenter(centerX2, centerY2);
        }

    }
        
    public void setDY(Double dy){
        this.ballDY = dy;
    }
    public void setDX(Double dx){
        this.ballDX = dx;
    }
    public double getDY(){
        return this.ballDY;
    }
        public double getDX(){
        return this.ballDX;
    }
    public double getCenterY(){
        return centerY;
    }
    public double getCenterX(){
        return centerX;
    }
    public Ellipse getBallShape(){
        return ball;
    }
}
