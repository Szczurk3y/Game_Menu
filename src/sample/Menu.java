package sample;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Menu extends Application implements WindowInterface {

    private Stage stage;
    private Scene scene;
    private Pane root;
    private WindowType windowType;
    private ImageView imageView;
    private GameMenu gameMenu;

    public Menu() {
        windowType = WindowType.MENU;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setMaxHeight(windowType.getWindowHeight());
        stage.setMaxWidth(windowType.getWindowWidth());
        stage.setTitle(windowType.getWindowTitle());
        stage.setResizable(false);

        InputStream is = Files.newInputStream(Paths.get(windowType.getWindowImagePath()));
        Image image = new Image(is);
        is.close();
        imageView = new ImageView(image);
        imageView.setFitWidth(windowType.getWindowWidth());
        imageView.setFitHeight(windowType.getWindowHeight());

        root = new Pane();
        root.setPrefSize(windowType.getWindowWidth(), windowType.getWindowHeight());

        gameMenu = new GameMenu(windowType);
        root.getChildren().addAll(imageView, gameMenu);

        stage.setFullScreen(windowType.isFullScreen());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static class MenuButton extends StackPane {
        private Text text;
        private boolean isClicked = false;

        public MenuButton(String name) {
            text = new Text(name);
            text.setFont(text.getFont().font(20));
            text.setFill(Color.WHITE);

            Rectangle rectangle = new Rectangle(250, 30);
            rectangle.setOpacity(0.6);
            rectangle.setFill(Color.BLACK);
            rectangle.setEffect(new GaussianBlur(3.5));

            setAlignment(Pos.CENTER);

            setOnMouseEntered(event -> {
                rectangle.setTranslateX(10);
                text.setTranslateX(10);
                rectangle.setFill(Color.WHITE);
                text.setFill(Color.BLACK);
            });
            setOnMouseExited(event -> {
                rectangle.setTranslateX(0);
                text.setTranslateX(0);
                rectangle.setFill(Color.BLACK);
                text.setFill(Color.WHITE);
            });

            DropShadow dropShadow = new DropShadow(50, Color.WHITE);
            dropShadow.setInput(new Glow());

            setOnMousePressed(event -> {
                setEffect(dropShadow);
            });
            setOnMouseReleased(event -> setEffect(null));

            getChildren().addAll(rectangle, text);
        }

        public boolean isClicked() { return this.isClicked; }

        protected TranslateTransition animateButton() {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), this);
            isClicked = true;
            tt.setToX(-75);
            tt.play();
            return tt;
        }
        protected TranslateTransition getBackButton() {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), this);
            tt.setToX(0);
            tt.play();
            isClicked = false;
            return tt;
        }

    }
}
