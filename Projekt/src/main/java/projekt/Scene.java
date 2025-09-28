package projekt;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Scene {
    //pozice a velikost
    private final double platformX;
    private final double platformY;
    private final double platformWidth;
    private final double platformHeight;

    public Scene(double platformX, double platformY, double platformWidth, double platformHeight) {
        this.platformX = platformX;
        this.platformY = platformY;
        this.platformWidth = platformWidth;
        this.platformHeight = platformHeight;
    }

    public void draw(GraphicsContext gc,Color color) {
        gc.setFill(color);
        gc.fillRect(platformX, platformY, platformWidth, platformHeight);
    }

    //detekce kolize
    public void collision(Entity player){
        boolean playerPosition = player.getEntityX() + player.getEntityWidth() > platformX && player.getEntityX() < platformX + platformWidth;
        boolean falling = player.getEntityVelocityY() >= 0;
        if(playerPosition && falling && player.getEntityY() +  player.getEntityHeight() > platformY && player.getEntityY() < platformY + platformHeight){
            player.setEntityY(platformY - player.getEntityHeight());
            player.setEntityVelocityY(0);
            player.setOnGround(true);
        }
    }
    //detekce ukonceni hry
    public boolean collisionFinal(Entity player) {
        boolean playerPosition = player.getEntityX() + player.getEntityWidth() > platformX && player.getEntityX() < platformX + platformWidth;
        boolean falling = player.getEntityVelocityY() >= 0;
        if (playerPosition && falling && player.getEntityY() + player.getEntityHeight() > platformY && player.getEntityY() < platformY + platformHeight) {
            return true;
        }
        return false;
    }
}
