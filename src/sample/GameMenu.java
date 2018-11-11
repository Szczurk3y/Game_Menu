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
    private ScrollBar scrollBar;
    protected int offset;
    private double soundButtonPosicion;
    protected WindowType windowType;
    private LinkedList<Menu.MenuButton> clickedButtons = new LinkedList<>();

    public GameMenu(WindowType type) {
        windowType = type;
        init();
        addingButtonEvents();

        firstMenu.getChildren().addAll(resumeButton, optionButton, exitButton);
        secondMenu.getChildren().addAll(tempButton, soundButton, backButton);
        getChildren().addAll(firstMenu);
    }

    private void init() {
        offset = windowType.getWindowWidth()/2;
        firstMenu = new VBox(10);
        secondMenu = new VBox(10);
        soundButtonPosicion = soundButton.getTranslateY() - 50;
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
                clickedButtons.add(soundButton);
                scrollBar = new ScrollBar();
                soundButton.animateButton().setOnFinished(event1 -> {
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
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), secondMenu);
            TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.35), firstMenu);

            secondMenu.getChildren().remove(scrollBar);

            // Hidding buttons
            for (Menu.MenuButton x : clickedButtons){
                if (x == clickedButtons.getLast()) {
                    x.getBackButton().setOnFinished(event1 -> {
                        getChildren().add(firstMenu);
                        tt.setToX(secondMenu.getTranslateX() + offset);
                        tt2.setToX(windowType.getWindowWidth()/2 - 125);
                        tt.play();
                        tt2.play();
                        tt.setOnFinished(event2 -> {
                            getChildren().remove(secondMenu);
                        });
                    });
                } else {
                    x.getBackButton();
                }
            }
            // finish hidding buttons
            clearLists(); // clearing all lists
        });

        tempButton.setOnMouseClicked(event -> {
            clickedButtons.add(tempButton);
            tempButton.animateButton();
        });
    }

    private void clearLists() {
        clickedButtons.clear();
    }
}