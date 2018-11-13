package sample;

import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.LinkedList;

public class elo extends Parent implements ButtonsInterface {
    protected VBox firstMenu, secondMenu, thirdMenu;
    private ScrollBar scrollBar = new ScrollBar();
    protected int offset;
    protected WindowType windowType;
    private Menu.MenuButton lastClickedButton = new Menu.MenuButton("", Color.WHITE);
    private LinkedList<Object> additives = new LinkedList<>();
    private final double centerOfScreenX = windowType.getWindowWidth()/2 - (windowType.getWindowWidth()/4)/2;
    private final double leftEdgeOScreen = -1 * windowType.getWindowWidth() -100;
    private final double rightEdgeOfScreen = windowType.getWindowWidth() + 100;

    public elo(WindowType type) {
        windowType = type;
        init();
        addingButtonEvents();

        firstMenu.getChildren().addAll(resumeButton, optionButton, exitButton);
        secondMenu.getChildren().addAll(resolutionButton, controllerButton, soundButton, backButton);
        thirdMenu.getChildren().addAll(fullHdButton, halfFullHdButton, hdButton, backButton2);
        getChildren().addAll(firstMenu);
    }

    private void init() {
        offset = windowType.getWindowWidth()/2;
        firstMenu = new VBox(10);
        secondMenu = new VBox(10);
        thirdMenu = new VBox(10);
        firstMenu.setTranslateX(centerOfScreenX);
        firstMenu.setTranslateY(windowType.getWindowHeight()/2 - 30);
        secondMenu.setTranslateX(windowType.getWindowWidth() + 100);
        secondMenu.setTranslateY(windowType.getWindowHeight()/2 - 75);
        thirdMenu.setTranslateX(windowType.getWindowWidth()/2 - 125);
        thirdMenu.setTranslateY(windowType.getWindowHeight()/2);
    }

    private void addingButtonEvents() {
        optionButton.setOnMouseClicked(event -> {
            getChildren().add(secondMenu);
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), firstMenu);
            TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.45), secondMenu);

            tt.setToX(firstMenu.getTranslateX() - offset - 100);
            tt2.setToX(windowType.getWindowWidth()/2 - (windowType.getWindowWidth()/4)/2);

            tt.play();
            tt2.play();

            tt.setOnFinished(event1 -> {
                getChildren().remove(firstMenu);
            });
        });

        resolutionButton.setOnMouseClicked(event -> {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.15), secondMenu);
            TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.55), thirdMenu);
            tt.setToY(secondMenu.getTranslateY() + 15);
            tt2.setToY(secondMenu.getTranslateY());
            thirdMenu.setVisible(false);
            getChildren().add(thirdMenu);

            if (!additivesList.isEmpty()) {
                deleteAdditives();
            }
            if (!lastClickedButton.isVisible()) {
                lastClickedButton.setVisible(true);
            }
            if (lastClickedButton.isClicked()) {
                lastClickedButton.getBackButton().setOnFinished(event1 -> {
                    tt.play();
                    tt2.play();
                    tt.setOnFinished(event2 -> {
                        thirdMenu.setVisible(true);
                        getChildren().remove(secondMenu);
                    });
                });
            } else {
                tt.play();
                tt2.play();
                tt.setOnFinished(event1 -> {
                    thirdMenu.setVisible(true);
                    getChildren().remove(secondMenu);
                });
            }
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

        controllerButton.setOnMouseClicked(event -> {
            deleteAdditives();
            lastClickedButton.setVisible(true);
            lastClickedButton.getBackButton();
            controllerButton.animateButton();
            lastClickedButton = controllerButton;
        });

        backButton.setOnMouseClicked(event -> {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), secondMenu);
            TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.45), firstMenu);
            tt.setToX(secondMenu.getTranslateX() + offset);
            tt2.setToX(windowType.getWindowWidth()/2 - (windowType.getWindowWidth()/4)/2);

            if (!additivesList.isEmpty()) {
                deleteAdditives();
            }
            if (!lastClickedButton.isVisible()) {
                lastClickedButton.setVisible(true);
            }
            if (lastClickedButton.isClicked()) {
                lastClickedButton.getBackButton().setOnFinished(event1 -> {
                    getChildren().add(firstMenu);
                    tt.play();
                    tt2.play();
                    tt.setOnFinished(event2 -> {
                        getChildren().remove(secondMenu);
                    });
                });
            } else {
                getChildren().add(firstMenu);
                tt.play();
                tt2.play();
                tt.setOnFinished(event2 -> {
                    getChildren().remove(secondMenu);
                });
            }

        });

        backButton2.setOnMouseClicked(event -> {
            getChildren().add(secondMenu);
            secondMenu.setVisible(false);
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), thirdMenu);
            TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.55), secondMenu);

            tt.setToY(thirdMenu.getTranslateY() + 30);
            tt2.setToY(thirdMenu.getTranslateY());

            tt.play();
            tt2.play();

            tt.setOnFinished(event1 -> {
                secondMenu.setVisible(true);
                getChildren().remove(thirdMenu);
            });
        });
    }

    private void deleteAdditives() {
        for (Object x : additivesList) {
            secondMenu.getChildren().remove(x);
        }
    }

}