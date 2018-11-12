package sample;

import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.LinkedList;

public class GameMenu extends Parent implements ButtonsInterface {
    protected VBox firstMenu;
    private VBox secondMenu;
    private ScrollBar scrollBar = new ScrollBar();
    protected int offset;
    protected WindowType windowType;
    private Menu.MenuButton lastClickedButton = new Menu.MenuButton("");
    private LinkedList<Object> additives = new LinkedList<>();

    public GameMenu(WindowType type) {
        windowType = type;
        init();
        addingButtonEvents();

        firstMenu.getChildren().addAll(resumeButton, optionButton, exitButton);
        secondMenu.getChildren().addAll(tempButton2, tempButton, soundButton, backButton);
        getChildren().addAll(firstMenu);
    }

    private void init() {
        offset = windowType.getWindowWidth()/2;
        firstMenu = new VBox(10);
        secondMenu = new VBox(10);
        firstMenu.setTranslateX(windowType.getWindowWidth()/2 - 125);
        firstMenu.setTranslateY(windowType.getWindowHeight()/2 - 30);
        secondMenu.setTranslateX(windowType.getWindowWidth());
        secondMenu.setTranslateY(windowType.getWindowHeight()/2 - 30);
    }

    private void addingButtonEvents() {
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
            if (!soundButton.isClicked()) {
                deleteAdditives();
                scrollBar.setTranslateX(0);
                scrollBar.setTranslateY(-1 * soundButton.getHeight() * 2 - 15);
                scrollBar.setMin(-50);
                scrollBar.setMax(50);
                scrollBar.setValue(0);
                scrollBar.setMaxWidth(200);
                scrollBar.setBackground(Background.EMPTY);

                lastClickedButton.setVisible(true);
                lastClickedButton.getBackButton();

                soundButton.animateButton().setOnFinished(event1 -> {
                    soundButton.setVisible(false);
                    TranslateTransition tt = new TranslateTransition(Duration.seconds(0.10),  scrollBar);
                    tt.setToX(35);
                    tt.play();
                    secondMenu.getChildren().add(scrollBar);
                    additivesList.add(scrollBar);
                });
                lastClickedButton = soundButton;
            }
        });

        tempButton.setOnMouseClicked(event -> {
            deleteAdditives();
            lastClickedButton.setVisible(true);
            lastClickedButton.getBackButton();
            tempButton.animateButton();
            lastClickedButton = tempButton;
        });

        tempButton2.setOnMouseClicked(event -> {
            deleteAdditives();
            lastClickedButton.setVisible(true);
            lastClickedButton.getBackButton();
            tempButton2.animateButton();
            lastClickedButton = tempButton2;
        });

        backButton.setOnMouseClicked(event -> {
            deleteAdditives();

            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), secondMenu);
            TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.35), firstMenu);
            tt.setToX(secondMenu.getTranslateX() + offset);
            tt2.setToX(windowType.getWindowWidth()/2 - 125);

            lastClickedButton.setVisible(true);
            lastClickedButton.getBackButton().setOnFinished(event1 -> {
                getChildren().add(firstMenu);
                tt.play();
                tt2.play();
                tt.setOnFinished(event2 -> {
                    getChildren().remove(secondMenu);
                });
            });
        });
    }
    private void deleteAdditives() {
        for (Object x : additivesList) {
            secondMenu.getChildren().remove(x);
        }
    }

}