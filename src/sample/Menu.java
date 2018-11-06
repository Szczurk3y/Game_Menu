package sample;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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

        gameMenu = new GameMenu();
        gameMenu.setVisible(true);
        root.getChildren().addAll(imageView, gameMenu);

        stage.setFullScreen(windowType.isFullScreen());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public class GameMenu extends Parent {
        private final int offset = windowType.getWindowWidth()/2;

        public GameMenu() {
            VBox firstmenu = new VBox(10);
            VBox secondmenu = new VBox(10);

            firstmenu.setTranslateX(windowType.getWindowWidth()/2 - 125);
            firstmenu.setTranslateY(windowType.getWindowHeight()/2 - 30);
            secondmenu.setTranslateX(windowType.getWindowWidth());
            secondmenu.setTranslateY(windowType.getWindowHeight()/2 - 30);

            MenuButton resumeButton = new MenuButton("START");
            MenuButton optionButton = new MenuButton("OPTIONS");
            MenuButton exitButton = new MenuButton("EXIT");
            MenuButton soundButton = new MenuButton("SOUND");
            MenuButton backButton = new MenuButton("BACK");

            optionButton.setOnMouseClicked(event -> {
                getChildren().add(secondmenu);
                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), firstmenu);
                TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.35), secondmenu);

                tt.setToX(firstmenu.getTranslateX() - offset);
                tt2.setToX(windowType.getWindowWidth()/2 - 125);

                tt.play();
                tt2.play();

                tt.setOnFinished(event1 -> {
                    getChildren().remove(firstmenu);
                });
            });
            exitButton.setOnMouseClicked(event -> {
                System.exit(0);
            });

            backButton.setOnMouseClicked(event -> {
                getChildren().add(firstmenu);
                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), secondmenu);
                TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.35), firstmenu);

                tt.setToX(secondmenu.getTranslateX() + offset);
                tt2.setToX(windowType.getWindowWidth()/2 - 125);

                tt.play();
                tt2.play();

                tt.setOnFinished( event1 -> {
                    getChildren().remove(secondmenu);
                });

            });

            firstmenu.getChildren().addAll(resumeButton, optionButton, exitButton);
            secondmenu.getChildren().addAll(soundButton, backButton);
            getChildren().addAll(firstmenu);
        }
    }
    public class MenuButton extends StackPane {
        private Text text;

        public MenuButton(String name) {
            text = new Text(name);
            text.setFont(text.getFont().font(20));
            text.setFill(Color.WHITE);

            Rectangle rectangle = new Rectangle(250, 30);
            rectangle.setOpacity(0.6);
            rectangle.setFill(Color.BLACK);
            rectangle.setEffect(new GaussianBlur(3.5));

            setAlignment(Pos.CENTER);
            setRotate(-0.5);
            getChildren().addAll(rectangle, text);

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

            DropShadow drop = new DropShadow(50, Color.WHITE);
            drop.setInput(new Glow());

            setOnMousePressed(event -> setEffect(drop));
            setOnMouseReleased(event -> setEffect(null));
        }
    }
}
