package sample;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
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
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

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
        windowType = WindowType.MENU;
        standardButtonWidth = windowType.getWindowWidth()/4;
        standardButtonHeight = windowType.getWindowHeight()/18;
        smallButtonWidth = windowType.getWindowWidth()/15;
        smallButtonHeight = windowType.getWindowHeight()/18;
        buttonFont = windowType.getWindowHeight()/30;
    }

    public static WindowType getWindowType() {
        return windowType;
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

    public enum ButtonType {
        STANDARD(standardButtonWidth, standardButtonHeight, buttonFont),
        SMALL(smallButtonWidth, smallButtonHeight, buttonFont);

        private final int buttonWidth;
        private final int butotnHeight;
        private final int font;

        private ButtonType(int width, int height, int font) {
            this.buttonWidth = width;
            this.butotnHeight = height;
            this.font = windowType.getWindowHeight()/30;
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
        private Color backgroundColor;
        private WindowType windowType = Menu.windowType;
        private ButtonType buttonType;
        private boolean isClicked = false;
        private boolean canBeClicked = true;
        protected Rectangle rectangle;

        public MenuButton(String name) {
            this.name = name;
            this.backgroundColor = Color.WHITE;
            creatingRectangle();
        }

        public MenuButton(String name, Color color) {
            this.name = name;
            this.backgroundColor = color;
            this.buttonType = ButtonType.STANDARD;
            creatingRectangle();
        }

        public MenuButton(String name, Color color, ButtonType buttonType) {
            this.name = name;
            this.backgroundColor = color;
            this.buttonType = buttonType;
            creatingRectangle();
        }

        private void creatingRectangle() {
            text = new Text(name);
            text.setFont(text.getFont().font(buttonType.getFont()));
            text.setFill(Color.WHITE);

            rectangle = new Rectangle(buttonType.getButtonWidth(), buttonType.getButtonHeight());
            rectangle.setOpacity(0.6);
            rectangle.setFill(Color.BLACK);
            rectangle.setEffect(new GaussianBlur(3.5));

            setAlignment(Pos.CENTER);

            setOnMouseEntered(event -> {
                rectangle.setTranslateX(10);
                text.setTranslateX(10);
                rectangle.setFill(backgroundColor);
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
        protected void setText(String tempText) {
            text.textProperty().setValue(tempText);
            text.setFill(Color.LIGHTGREEN);
            text.setFont(text.getFont().font(buttonType.font));
        }
        public String getText() {
            return this.text.getText();
        }
    }
}
