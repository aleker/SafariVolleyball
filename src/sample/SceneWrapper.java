package sample;

import javafx.beans.NamedArg;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;


public abstract class SceneWrapper extends Scene {

    int width;
    int height;
    Background background;

    public SceneWrapper(/*@NamedArg("root")*/ Group root, int windowWidth, int windowHeight) {
        super(root, windowWidth, windowHeight);
        this.width = windowWidth;
        this.height = windowHeight;
    }
     public SceneWrapper(Group root,int windowWidth, int windowHeight, Image background) {
         super(root, windowWidth, windowHeight);
//         this.background = background;
         BackgroundSize backgroundSize = new BackgroundSize(800, 600, true, true, true, false);
         BackgroundImage backgroundImage = new BackgroundImage(background, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
         Background theBackground = new Background(backgroundImage);
         this.background = theBackground;
     }

    public abstract void handleEvents();

    public abstract void run();

    public abstract void draw();

    public void exit(SceneWrapper newScene) {}

    public void initialize() {}
}

