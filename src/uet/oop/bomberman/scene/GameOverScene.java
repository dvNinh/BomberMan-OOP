package uet.oop.bomberman.scene;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import uet.oop.bomberman.BombermanGame;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;

public class GameOverScene extends Scenes {
    private double scale = BombermanGame.STAGE_WIDTH / 256;
    private GraphicsContext gc;
    private Canvas canvas;
    private Timer timer = new Timer();

    public GameOverScene(Group root) {
        super(root);

        canvas = new Canvas(256 * scale, 240 * scale);
        gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                BombermanGame.setScene(new MenuScene(new Group()));
            }
        }, 8000);
    }

    @Override
    public void show() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        Scenes.renderTextCenter(canvas, "GAME OVER", scale, Color.WHITE, Color.GRAY);
        Scenes.renderTextXCenter(canvas, "YOUR SCORE " + GameScene.getScore(), 200, scale, Color.WHITE, Color.GRAY);

        try {
            gc.setFont(Font.loadFont(new FileInputStream("res/font/Kongtext.ttf"), 8 * scale));
        } catch (FileNotFoundException e) {
            System.exit(1);
        }
    }
}
