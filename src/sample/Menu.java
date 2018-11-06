package sample;

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

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Menu extends Application implements WindowInterface {

    private Stage stage;
    private Scene scene;
    private Pane root;
    private WindowType windowType = WindowType.MENU;
    private ImageView imageView;
    private GameMenu gameMenu;

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

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public class GameMenu extends Parent {
        public GameMenu() {
            VBox firstmenu = new VBox(10);
            firstmenu.setTranslateX(menuWindowWidth/3 +18);
            firstmenu.setTranslateY(menuWindowHeight/3 +15);

            MenuButton resumeButton = new MenuButton("RESUME");
            MenuButton optionButton = new MenuButton("OPTIONS");
            MenuButton exitButton = new MenuButton("EXIT");
            firstmenu.getChildren().addAll(resumeButton, optionButton, exitButton);
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
