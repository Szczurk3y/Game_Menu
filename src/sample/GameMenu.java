package sample;

import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class GameMenu extends Parent {
    private VBox firstMenu;
    private VBox secondMenu;
    private Menu.MenuButton resumeButton;
    private Menu.MenuButton optionButton;
    private Menu.MenuButton exitButton;
    private Menu.MenuButton soundButton;
    private Menu.MenuButton backButton;
    private ScrollBar scrollBar;
    private final int offset;
    private final double soundButtonPosicion;
    private boolean isSoundButtonClicked = false;
    private WindowType windowType;

    public GameMenu(WindowType type) {
        windowType = type;
        offset = windowType.getWindowWidth()/2;
        firstMenu = new VBox(10);
        secondMenu = new VBox(10);
        resumeButton = new Menu.MenuButton("START");
        optionButton = new Menu.MenuButton("OPTIONS");
        exitButton = new Menu.MenuButton("EXIT");
        soundButton = new Menu.MenuButton("SOUND");
        backButton = new Menu.MenuButton("BACK");

        soundButtonPosicion = soundButton.getTranslateY() - 50;
        firstMenu.setTranslateX(windowType.getWindowWidth()/2 - 125);
        firstMenu.setTranslateY(windowType.getWindowHeight()/2 - 30);
        secondMenu.setTranslateX(windowType.getWindowWidth());
        secondMenu.setTranslateY(windowType.getWindowHeight()/2 - 30);

        optionButton.setOnMouseClicked(event -> {
            getChildren().add(secondMenu);
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), firstMenu);
            TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.35), secondMenu);

            tt.setToX(firstMenu.getTranslateX() - offset);
            tt2.setToX(windowType.getWindowWidth()/2 - 125);

            tt.play();
            tt2.play();

            tt.setOnFinished(event1 -> {
                getChildren().remove(firstMenu);
            });
        });
        exitButton.setOnMouseClicked(event -> {
            System.exit(0);
        });
        soundButton.setOnMouseClicked(event -> {
            if (!isSoundButtonClicked) {
                scrollBar = new ScrollBar();
                isSoundButtonClicked = true;
                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), soundButton);
                tt.setToY(-50);
                tt.play();
                tt.setOnFinished(event1 -> {
                    scrollBar.setTranslateY(soundButtonPosicion - 45);
                    scrollBar.setMin(-50);
                    scrollBar.setMax(50);
                    scrollBar.setValue(0);
                    scrollBar.setBackground(Background.EMPTY);
                    secondMenu.getChildren().add(scrollBar);
                });
            }
        });
        backButton.setOnMouseClicked(event -> {
            isSoundButtonClicked = false;
            TranslateTransition soundTransition = new TranslateTransition(Duration.seconds(0.25), soundButton);
            secondMenu.getChildren().remove(scrollBar);
            soundTransition.setToY(0);
            soundTransition.play();
            soundTransition.setOnFinished(event1 -> {
                getChildren().add(firstMenu);
                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), secondMenu);
                TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.35), firstMenu);

                tt.setToX(secondMenu.getTranslateX() + offset);
                tt2.setToX(windowType.getWindowWidth()/2 - 125);

                tt.play();
                tt2.play();

                tt.setOnFinished(event2 -> {
                    getChildren().remove(secondMenu);
                });
            });
        });

        firstMenu.getChildren().addAll(resumeButton, optionButton, exitButton);
        secondMenu.getChildren().addAll(soundButton, backButton);
        getChildren().addAll(firstMenu);
    }
}