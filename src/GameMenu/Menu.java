package GameMenu;

import GameMenu.Interfaces.ButtonsInterface;
import GameMenu.Interfaces.WindowInterface;
import GameMenu.MenuStages.StageOne;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.*;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;

public class Menu extends Application implements WindowInterface, ButtonsInterface {
    private Stage stage;
    private Scene scene;
    private Pane pane;
    private static int standardButtonWidth, standardButtonHeight;
    private static int smallButtonWidth, smallButtonHeight;
    private static int buttonFont;
    private ImageView imageView;
    private MenuHandler menuHandler;
    public static final WindowType windowType = WindowType.MENU;
    public static int next = -1;
    public LinkedList<Menu.MenuButton> currentStage = new LinkedList<>();

    public Menu() {
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

        pane = new Pane();
        menuHandler = new MenuHandler(windowType, currentStage);

        scene = new Scene(pane, windowType.getWindowWidth(), windowType.getWindowHeight());

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.TAB && next > 0) {
                currentStage.get(next).unSelect();
                next--;
                currentStage.get(next).select();
                System.out.println("TAB");
                System.out.println("Next: " + next);
            } else if (event.getCode().isLetterKey() || event.getCode().isArrowKey()) {
                MenuHandler.tempButton.setText(event.getCode().toString());
            } else if (event.getCode() == KeyCode.CAPS && next < currentStage.size()-1) {
                if (next > -1)
                    currentStage.get(next).unSelect();
                next++;
                currentStage.get(next).select();
                System.out.println("CAPSLOCK");
                System.out.println("Next: " + next);
            } else if(event.getCode() == KeyCode.ENTER) {
//                System.out.println(

//                );
//                EventDispatcher dispatcher = new EventDispatcher() {
//                    @Override
//                    public Event dispatchEvent(Event event, EventDispatchChain tail) {
//
//                        return
//                    }
//                };
//                currentStage.get(next).getEventDispatcher().dispatchEvent();
//                try {
//                    Robot robot = new Robot();
//                    robot.mouseMove((int)currentStage. (int)currentStage.get(next).getScaleY());
//                    robot.mousePress(InputEvent.BUTTON2_DOWN_MASK);
//                    robot.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
//                } catch (AWTException ex) {
//                    System.out.println(ex.getMessage());
//                }
            }
        });

        pane.getChildren().addAll(imageView, menuHandler);
        stage.setFullScreen(windowType.isFullScreen());
        stage.setMinWidth(WindowType.menuWindowWidth);
        stage.setMinHeight(WindowType.menuWindowHeight);

        stage.setScene(scene);
        stage.show();
    }

    public void hide() {
        pane.setVisible(false);
    }

    // Necessaries class
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
                select();
            });
            setOnMouseExited(event -> {
                unSelect();
            });
            getChildren().addAll(button, text);
        }

        public void changeWidth(int width) {
            button.setFitWidth(width);
        }

        public void changeHeight(int height) {
            button.setFitHeight(height);
        }

        protected void select() {
            button.setTranslateX(10);
            text.setTranslateX(10);
            text.setFill(Color.GREEN);
        }

        protected void unSelect() {
            button.setTranslateX(0);
            text.setTranslateX(0);
            text.setFill(textColor);;
        }

        public TranslateTransition animateButton() {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), this);
            isClicked = true;
            tt.setToX(-75);
            tt.play();
            return tt;
        }

        public TranslateTransition getBackButton() {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), this);
            tt.setToX(0);
            tt.play();
            isClicked = false;
            return tt;
        }

        public String getText() {
            return this.text.getText();
        }

        public void setText(String tempText) {
            text.textProperty().setValue(tempText);
            text.setFill(Color.GREEN);
        }

        public boolean isClicked() { return this.isClicked; }

        public void setClick() { this.isClicked = true; }
    }
}
