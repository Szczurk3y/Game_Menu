package sample;

import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import java.util.LinkedList;

public class GameMenu extends Parent implements ButtonsInterface {
    protected String moveUpKey = "W", moveDownKey = "S", moveLeftKey = "A", moveRightKey = "D";
    protected VBox firstMenu = new VBox(10);
    protected VBox secondMenu = new VBox(10);
    protected VBox thirdMenu = new VBox(10);
    protected VBox fourthMenu = new VBox(5);
    private ScrollBar scrollBar = new ScrollBar();
    private Menu.MenuButton tempButton;
    protected WindowType windowType;
    private Menu.MenuButton lastClickedButton = new Menu.MenuButton("", Color.WHITE);
    private LinkedList<Object> additives = new LinkedList<>();

    public GameMenu(WindowType type) {
        windowType = type;
        init();
        addingButtonsEvents();
    }

    private void init() {
        firstMenu.setTranslateX(windowType.getMiddleOfTheScreenX());
        firstMenu.setTranslateY(windowType.getMiddleOfTheScreenY());
        secondMenu.setTranslateX(windowType.getRightSideOfTheScreen());
        secondMenu.setTranslateY(windowType.getMiddleOfTheScreenY());
        thirdMenu.setTranslateX(windowType.getMiddleOfTheScreenX());
        thirdMenu.setTranslateY(windowType.getMiddleOfTheScreenY() + 30);
        fourthMenu.setTranslateX(windowType.getMiddleOfTheScreenX());
        fourthMenu.setTranslateY(windowType.getTopOfTheScreen());

        firstMenu.getChildren().addAll(resumeButton, optionButton, exitButton);
        secondMenu.getChildren().addAll(resolutionButton, controllerButton, soundButton, backButton);
        thirdMenu.getChildren().addAll(fullHdButton, halfFullHdButton, hdButton, thirdMenuBackButton);
        fourthMenu.getChildren().addAll(moveUpButton, moveDownButton, moveLeftButton, moveRightButton, fourthMenuBackButton);
        getChildren().add(firstMenu);
    }

    private void addingButtonsEvents() {
        optionButton.setOnMouseClicked(event -> {
            getChildren().add(secondMenu);
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), firstMenu);
            TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.45), secondMenu);

            tt.setToX(windowType.getLeftSideOfTheScreen());
            tt2.setToX(windowType.getMiddleOfTheScreenX());

            tt.play();
            tt2.play();

            tt.setOnFinished(event1 -> {
                getChildren().remove(firstMenu);
            });
        });

        resolutionButton.setOnMouseClicked(event -> {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), secondMenu);
            TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.75), thirdMenu);
            tt.setToY(secondMenu.getTranslateY() + 40);
            tt2.setToY(secondMenu.getTranslateY());
            thirdMenu.setVisible(false);
            getChildren().add(thirdMenu);

            if (!additivesList.isEmpty()) {
                deleteAdditives(secondMenu);
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
                deleteAdditives(secondMenu);
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
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), secondMenu);
            TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.50), fourthMenu);

            tt.setToY(windowType.getBottomOfTheScreen());
            tt2.setToY(windowType.getMiddleOfTheScreenY());

            if(!additivesList.isEmpty()) {
                deleteAdditives(secondMenu);
            }
            if (!lastClickedButton.isVisible()) {
                lastClickedButton.setVisible(true);

            }
            if (lastClickedButton.isClicked()) {
                lastClickedButton.getBackButton().setOnFinished(event1 -> {
                    getChildren().add(fourthMenu);
                    tt.play();
                    tt2.play();
                    tt.setOnFinished(event2 -> {
                        getChildren().remove(secondMenu);
                    });
                });
            } else {
                getChildren().add(fourthMenu);
                tt.play();
                tt2.play();
                tt.setOnFinished(event1 -> {
                    getChildren().remove(secondMenu);
                });
            }
            lastClickedButton = controllerButton;
        });

        backButton.setOnMouseClicked(event -> {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), secondMenu);
            TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.45), firstMenu);
            tt.setToX(windowType.getRightSideOfTheScreen());
            tt2.setToX(windowType.getMiddleOfTheScreenX());

            if (!additivesList.isEmpty()) {
                deleteAdditives(secondMenu);
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

        thirdMenuBackButton.setOnMouseClicked(event -> {
            getChildren().add(secondMenu);
            secondMenu.setVisible(false);
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), thirdMenu);
            TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.50), secondMenu);

            tt.setToY(thirdMenu.getTranslateY() + 30);
            tt2.setToY(thirdMenu.getTranslateY());

            tt.play();
            tt2.play();

            tt.setOnFinished(event1 -> {
                secondMenu.setVisible(true);
                getChildren().remove(thirdMenu);
            });
        });

        fourthMenuBackButton.setOnMouseClicked(event -> {
            getChildren().add(secondMenu);
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.40), fourthMenu);
            TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.35), secondMenu);

            tt.setToY(windowType.getTopOfTheScreen());
            tt2.setToY(windowType.getMiddleOfTheScreenY());

            tt.play();
            tt2.play();

            tt.setOnFinished(event1 -> {
                getChildren().remove(fourthMenu);
            });
        });

        moveUpButton.setOnMouseEntered(event -> {
            createTempButton(moveUpKey, moveUpButton);
        });
        moveUpButton.setOnMouseExited(event -> {
            fourthMenu.getChildren().remove(tempButton);
        });
        moveDownButton.setOnMouseEntered(event -> {
            createTempButton(moveDownKey, moveDownButton);
        });
        moveDownButton.setOnMouseExited(event -> {
            fourthMenu.getChildren().remove(tempButton);
        });
        moveLeftButton.setOnMouseEntered(event -> {
            createTempButton(moveLeftKey, moveLeftButton);
        });
        moveLeftButton.setOnMouseExited(event -> {
            fourthMenu.getChildren().remove(tempButton);
        });
        moveRightButton.setOnMouseEntered(event -> {

        });
    }
    private void createTempButton(String existingKey, Menu.MenuButton button) {
        tempButton = new Menu.MenuButton(existingKey);
        tempButton.setTranslateY(-1 * button.getHeight() * 2 - 15);
        tempButton.setTranslateX(button.getTranslateX() + 100);
        fourthMenu.getChildren().add(tempButton);
    }
    private void deleteAdditives(VBox menu) {
        for (Object x : additivesList) {
            menu.getChildren().remove(x);
        }
    }

}