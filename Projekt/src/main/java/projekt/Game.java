package projekt;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game extends Application {

    //velikost okna
    private static final double WIDTH = 1920;
    private static final double HEIGHT = 1080;

    //tlacitka
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    private boolean jump;

    public void start(Stage stage) {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        javafx.scene.Scene scene = new javafx.scene.Scene(new Group(canvas), WIDTH, HEIGHT);

        //vytvoreni entit a scene
        Entity player = new Entity(120,HEIGHT-220,32,48);
        Scene ground = new Scene(0,HEIGHT-40,WIDTH,48);
        Scene ledge1 = new Scene(320,HEIGHT-180,220,20);
        Scene ledge2 = new Scene(640,HEIGHT-360,220,20);
        Scene ledge3 = new Scene(960,HEIGHT-540,220,20);
        Scene ledge4 = new Scene(1280,HEIGHT-720,660,20);
        Scene doorFinal = new Scene(1890,HEIGHT-770,30,50);

        //nastaveni tlacitek
        scene.setOnKeyPressed(event -> {
            KeyCode kc = event.getCode();
        if (kc == KeyCode.A || kc == KeyCode.LEFT) left = true;
        if (kc == KeyCode.D || kc == KeyCode.RIGHT) right = true;
        if (kc == KeyCode.W || kc == KeyCode.UP) up = true;
        if (kc == KeyCode.S || kc == KeyCode.DOWN) down = true;
        if (kc == KeyCode.SPACE)  jump = true;
        });

        scene.setOnKeyReleased(event -> {
           KeyCode kc = event.getCode();
           if (kc == KeyCode.A || kc == KeyCode.LEFT) left = false;
           if (kc == KeyCode.D || kc == KeyCode.RIGHT) right = false;
           if (kc == KeyCode.W || kc == KeyCode.UP) up = false;
           if (kc == KeyCode.S || kc == KeyCode.DOWN) down = false;
           if (kc == KeyCode.SPACE)  jump = false;
        });

        //nastaveni okna
        stage.setTitle("Montezuma's revenge (demo)");
        stage.setScene(scene);
        stage.show();

        //herni smycka
        new AnimationTimer() {
            long last = -1;
            @Override
            public void handle(long now) {
                if(last < 0){
                    last = now;
                    return;
                }

                double deltaTime = (now - last) / 1_000_000_000.0;
                last = now;

                player.input(left,right,jump);
                player.update(deltaTime);

                ground.collision(player);
                ledge1.collision(player);
                ledge2.collision(player);
                ledge3.collision(player);
                ledge4.collision(player);
                if(doorFinal.collisionFinal(player)){
                    gc.setFill(Color.BLACK);
                    gc.fillRect(0,0,WIDTH,HEIGHT);

                    gc.setFill(Color.WHITE);
                    gc.setFont(javafx.scene.text.Font.font("Arial",100));
                    gc.fillText("Game Over", WIDTH/2 - 150, HEIGHT/2);

                    stop();
                    return;

                }

                player.keepInWorld(0,WIDTH,HEIGHT);

                gc.setFill(Color.BLACK);
                gc.fillRect(0,0,WIDTH,HEIGHT);

                ground.draw(gc,Color.GREEN);
                ledge1.draw(gc,Color.GREEN);
                ledge2.draw(gc,Color.GREEN);
                ledge3.draw(gc,Color.GREEN);
                ledge4.draw(gc,Color.GREEN);
                doorFinal.draw(gc,Color.BROWN);
                player.draw(gc);
            }
        }.start();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}