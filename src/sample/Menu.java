package sample;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;

public class Menu extends Application implements WindowInterface {
    private Stage stage;
    public Scene scene;
    private Pane root;
    public static WindowType windowType;
    private ImageView imageView;
    private GameMenu gameMenu;
    public static int standardButtonWidth, standardButtonHeight;
    public static int smallButtonWidth, smallButtonHeight;
    public static int buttonFont;

    public Menu() {
        windowType = WindowType.FULLSCREAN;
        standardButtonWidth = windowType.getWindowWidth()/4;
        standardButtonHeight = windowType.getWindowHeight()/13;
        smallButtonWidth = windowType.getWindowWidth()/15;
        smallButtonHeight = standardButtonHeight;
        buttonFont = windowType.getWindowHeight()/30;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setTitle(windowType.getWindowTitle());
        stage.setResizable(true);

        InputStream is = Files.newInputStream(Paths.get(windowType.getWindowImagePath()));
        Image image = new Image(is);
        is.close();
        imageView = new ImageView(image);
        imageView.setFitWidth(windowType.getWindowWidth());
        imageView.setFitHeight(windowType.getWindowHeight());

        root = new Pane();

        gameMenu = new GameMenu(windowType);
        root.getChildren().addAll(imageView, gameMenu);

        stage.setFullScreen(windowType.isFullScreen());
        stage.setMinWidth(WindowType.menuWindowWidth);
        stage.setMinHeight(WindowType.menuWindowHeight);
        scene = new Scene(root, windowType.getWindowWidth(), windowType.getWindowHeight());
        
        stage.setScene(scene);
        stage.show();
    }

    public static WindowType getWindowType() {
        return windowType;
    }

    public enum ButtonType {
        STANDARD(standardButtonWidth, standardButtonHeight, buttonFont, "res/graphics/StandardButton.png"),
        SMALL(smallButtonWidth, smallButtonHeight, buttonFont, "res/graphics/SmallButton.png");

        private final int buttonWidth;
        private final int butotnHeight;
        private final int font;
        private final String path;

        private ButtonType(int width, int height, int font, String path) {
            this.buttonWidth = width;
            this.butotnHeight = height;
            this.font = windowType.getWindowHeight()/30;
            this.path = path;
        }

        public int getButtonWidth() {
            return buttonWidth;
        }

        public int getButtonHeight() {
            return butotnHeight;
        }

        public int getFont() {
            return font;
        }
    }

    public static class MenuButton extends StackPane {
        private Text text;
        private String name;
        private WindowType windowType = Menu.windowType;
        private ButtonType buttonType;
        private boolean isClicked = false;
        protected ImageView button;
        protected Color textColor = Color.BLACK;
        public static LinkedList<MenuButton> allButtons = new LinkedList<>();

        public MenuButton(String name) {
            this.name = name;
            this.buttonType = ButtonType.STANDARD;
            initButton();
        }

        public MenuButton(String name, ButtonType buttonType) {
            this.name = name;
            this.buttonType = buttonType;
            initButton();
        }

        private void initButton() {
            allButtons.add(this);
            try {
                InputStream is = Files.newInputStream(Paths.get(buttonType.path));
                Image image = new Image(is);
                is.close();
                button = new ImageView(image);
                button.setFitWidth(buttonType.getButtonWidth());
                button.setFitHeight(buttonType.getButtonHeight());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            text = new Text(name);
            text.setFont(text.getFont().font(buttonType.getFont()));
            text.setFill(textColor);

            setOnMouseEntered(event -> {
                button.setTranslateX(10);
                text.setTranslateX(10);
                text.setFill(Color.GREEN);
            });
            setOnMouseExited(event -> {
                button.setTranslateX(0);
                text.setTranslateX(0);
                text.setFill(textColor);
            });
            getChildren().addAll(button, text);
        }

        public void changeWidth(int width) {
            button.setFitWidth(width);
        }

        public void changeHeight(int height) {
            button.setFitHeight(height);
        }

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

        public String getText() {
            return this.text.getText();
        }

        protected void setText(String tempText) {
            text.textProperty().setValue(tempText);
            text.setFill(Color.GREEN);
        }

        public boolean isClicked() { return this.isClicked; }
    }
}
