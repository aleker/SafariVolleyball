package sample;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.application.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class Menu extends SceneWrapper {

    public Menu(Group root, Game game, int windowWidth, int windowHeight) {
        super(root, game, windowWidth, windowHeight);
    }

    @Override
    public void initialize() {
        // button Menu
        this.addBackground(new Image("file:src/Pictures/background.png"));
        Button b_newGame = new Button("New Game (go to Gameplay)");
        b_newGame.setStyle("-fx-focus-color: #FF9933; -fx-font: 22 verdana; -fx-base: #FFFF66;");
        b_newGame.setOnAction(e -> this.exit(new Gameplay(new Group(), this.game, 800, 600)));
        b_newGame.setLayoutX(200);
        b_newGame.setLayoutY(200);
//        DropShadow shadow = new DropShadow();
//        shadow.setColor(Color.ORANGERED);
//        b_newGame.setEffect(shadow);
        // BUTTON's ICON:
        //Button b_newGame = new Button("New Game (go to Gameplay)", new ImageView("file:src/Pictures/lion_white.png"));
        // BUTTON's IMAGE:
        //b_newGame.setBackground(new Background(new BackgroundImage(new Image("file:src/Pictures/net.png"),
        //        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        // BUTTON's FONT
        //b_newGame.setFont(Font.font ("Verdana", 20));
        this.addEntity(b_newGame);

        // button Change Player
        Button b_changePlayer = new Button("Change Player");
        b_changePlayer.setOnAction(e -> this.changePlayer());
        b_changePlayer.setStyle("-fx-focus-color: #FF9933; -fx-font: 18 verdana; -fx-base: #FFFF66; -fx-background-color: transparent");
        b_changePlayer.setLayoutX(200);
        b_changePlayer.setLayoutY(300);
        this.addEntity(b_changePlayer);

        // button Exit
        Button b_close = new Button("Exit");
        b_close.setOnAction(e -> this.quit());
        b_close.setStyle("-fx-focus-color: #FF9933; -fx-font: 18 verdana; -fx-base: #FFFF66;");
        b_close.setLayoutX(200);
        b_close.setLayoutY(400);
        this.addEntity(b_close);
    }

    @Override
    public void handleEvents() {

    }

    @Override
    public void update() {

    }

    public void changePlayer() {}
    public void quit() { this.game.stage.close(); }

}
