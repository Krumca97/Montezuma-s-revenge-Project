package projekt;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Entity {
    //pozice a velikost entity
    private  double entityX;
    private double entityY;
    private double entityVelocityX;
    private double entityVelocityY;
    private double entityWidth;
    private double entityHeight;

    private boolean onGround = false;

    //pohyb
    private static final double MOVE_SPEED = 200;
    private static final double JUMP_SPEED = -450;
    private static final double GRAVITY = 600;

    //tlacitka pro pohyb
    private boolean left;
    private boolean right;
    private boolean jump;

    public Entity(double entityX, double entityY, double entityWidth, double entityHeight) {
        this.entityX = entityX;
        this.entityY = entityY;
        this.entityWidth = entityWidth;
        this.entityHeight = entityHeight;
    }

    public void input(boolean left, boolean right, boolean jump) {
        this.left = left;
        this.right = right;
        this.jump = jump;
    }

    //Zmena pohybu
    public void update(double deltaTime) {
        entityVelocityX = 0.0;
        if(left) {
            entityVelocityX -= MOVE_SPEED;
        }
        if(right) {
            entityVelocityX += MOVE_SPEED;
        }
        if(jump && onGround) {
            entityVelocityY = JUMP_SPEED;
            onGround = false;
        }

        entityVelocityY += GRAVITY * deltaTime;

        entityX += entityVelocityX * deltaTime;
        entityY += entityVelocityY  * deltaTime;
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillRect(entityX, entityY, entityWidth, entityHeight);
    }

    //zabraneni vystoupeni mimo okno
    public void keepInWorld(double minX, double maxX, double maxY) {
        if (entityX < minX) {
            entityX = minX;
        }
        if (entityX + entityWidth > maxX) {
            entityX = maxX - entityWidth;
        }
        if (entityY + entityHeight > maxY) {
            entityY = maxY - entityHeight;
            entityVelocityY = 0;
            onGround = true;
        }
    }

    //gettery a settery
    public double getEntityX(){
        return entityX;
    }

    public double getEntityY(){
        return entityY;
    }

    public double getEntityWidth(){
        return entityWidth;
    }

    public double getEntityHeight(){
        return entityHeight;
    }

    public double getEntityVelocityX(){
        return entityVelocityX;
    }

    public double getEntityVelocityY(){
        return entityVelocityY;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    public void setEntityX(double entityX) {
        this.entityX = entityX;
    }

    public void setEntityY(double entityY) {
        this.entityY = entityY;
    }

    public void setEntityWidth(double entityWidth) {
        this.entityWidth = entityWidth;
    }

    public void setEntityHeight(double entityHeight) {
        this.entityHeight = entityHeight;
    }

    public void setEntityVelocityX(double entityVelocityX) {
        this.entityVelocityX = entityVelocityX;
    }

    public void setEntityVelocityY(double entityVelocityY) {
        this.entityVelocityY = entityVelocityY;
    }

}
