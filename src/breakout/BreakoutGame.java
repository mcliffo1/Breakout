package breakout;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;

/**
 * The game of Breakout.
 */
public class BreakoutGame {
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 800;
    private final double DX = 5;
    private final double DY = 5;
    private int lives = 3;
    private List<Brick> brickList = new ArrayList<Brick>();
    private boolean animating;
    private Ball ball;
    private Bumper bumper;
    private CanvasWindow canvas;
    private GraphicsText lifeCount;
    private GraphicsText winLoss;

    /**
     * BreakoutGame creates the canvas, runs initializeGameAssets, and uses lambda functions to animate our game, and run the other 
     * core methods that help the game run.
     */
    public BreakoutGame() {
        canvas = new CanvasWindow("Breakout!", CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.setBackground(Color.black);
        initializeGameAssets();
        
        canvas.onCharacterTyped(Space -> animating = true);

        canvas.onDrag(event -> bumper.getBumperShape().setX(event.getPosition().getX()));
        canvas.animate(() -> {if(animating){
            ball.moveBall(canvas);
            brickCollision();
            bumperCollision();
            lifeCounter();
            winLoss();
        }});
    }

    /**
     * This Initializes all of our objects that we are putting on the canvas, adds them to the canvas, runs bricksetup, and
     * also changes up some fonts.
     */
    public void initializeGameAssets(){
        bumper = new Bumper();
        canvas.add(bumper.getBumperShape());
        bumper.getBumperShape().setCenter(300, 700);
        
        ball = new Ball(300, 450, DX, DY);
        canvas.add(ball.getBallShape());
        ball.getBallShape().setCenter(300, 450);
        
        lifeCount = new GraphicsText("", 20, 20);
        canvas.add(lifeCount);
        lifeCount.setText("Press Any Key To Begin! Lives remaining: " + lives);
        lifeCount.setFillColor(Color.WHITE);

        winLoss = new GraphicsText("", 0, 0);
        winLoss.setText("");
        canvas.add(winLoss);
        winLoss.setFontSize(30.0);
        winLoss.setFillColor(Color.WHITE);

        brickSetup(5);
        

    }
    /**
     * Uses intersect to check for collision on every brick with the ball, and sets the direction of the ball after impact 
     * as needed.
     * @return Needs to return something so that I can use for loops in my lambdas. :)
     */
    private boolean brickCollision() {
        for (Brick brick : brickList) {
            if (brick.intersects(ball) == 3 || brick.intersects(ball) == 4) {
                brickList.remove(brick);
                canvas.remove(brick.getBrickShape());
                ball.setDY(-(ball.getDY()));
                return true;
            }
            else if (brick.intersects(ball) == 1 || brick.intersects(ball) == 4){
                brickList.remove(brick);
                canvas.remove(brick.getBrickShape());
                ball.setDY(Math.abs(ball.getDY()));
                ball.setDX(-(ball.getDX()));
                return true;
            }
            
        }
        return false;
    }

    /**
     * Uses intersect to check for collision on the bumper with the ball, and sets the direction of the ball after impact 
     * as needed.
     */
    private void bumperCollision() {
        if (bumper.intersects(ball) == 3){
            ball.setDY(-Math.abs(ball.getDY()));
        }
        else if (bumper.intersects(ball) == 1 || bumper.intersects(ball) == 2){
            ball.setDX(Math.abs(ball.getDX()));
            ball.setDY(-Math.abs(ball.getDY()));
        }
    }
    /**
     * Decreases lives, and resets the ball whenever the ball hits the bottom wall.
     */
    private void lifeCounter(){
        if (ball.getCenterY() == 795){
            lives -= 1;
            lifeCount.setText("Press Any Key To Begin! Lives remaining: " + lives);
            animating = false;
            
            canvas.remove(ball.getBallShape());
            ball = new Ball(300, 450, DX, DY);
            canvas.add(ball.getBallShape());
            ball.getBallShape().setCenter(300, 450);
            ball.setDX(-ball.getDX());
        }
    }
    /**
     * Checks for a win or loss, and ends the game and informs the user of the termination.
     */
    private void winLoss() {
        
        if (lives == 0){
            animating = false;

            winLoss.setCenter(300, 400);
            winLoss.setText("You Lose.");
        }
        if (brickList.isEmpty()){
            animating = false;

            winLoss.setCenter(300, 400);
            winLoss.setText("You Win!");

        }

    }

    /**
     * Makes 5 rows of 10 bricks, using the brick object's constructor. I just bruteforced the spacing by putting in numbers
     * until I was happy.
     * @param numRows How many rows are created.
     */
    public void brickSetup(int numRows){
        int x = 5;
        int y = 100;
        int i = 0;

        while(brickList.size() != numRows*10){
            Brick brick = new Brick(x, y);
            brickList.add(brick);
            canvas.add(brick.getBrickShape());
            i++;
            x += 60;

            if(i % 10 == 0){
            y += 25;
            x = 5;
            }
        }
    }
    public static void main(String[] args){
        new BreakoutGame();
    }    
}
