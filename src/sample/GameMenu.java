package sample;

import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;

public class GameMenu extends Parent implements ButtonsInterface {
    protected String moveUpKey = "W", moveDownKey = "S", moveLeftKey = "A", moveRightKey = "D";
    protected GridPane firstMenu = new GridPane();
    protected GridPane secondMenu = new GridPane();
    protected GridPane thirdMenu = new GridPane();
    protected GridPane fourthMenu = new GridPane();
    private ScrollBar scrollBar = new ScrollBar();
    private ImageView imageView;
    private Menu.MenuButton tempButton;
    protected WindowType windowType;
    private Menu.ButtonType buttonType = Menu.ButtonType.STANDARDBUTTON;
    private Menu.MenuButton lastClickedButton = new Menu.MenuButton("", Color.WHITE);
    private LinkedList<Object> additives = new LinkedList<>();

    public GameMenu(WindowType type) {
        windowType = type;
        init();
        addingButtonsEvents();
    }

    private void init() {
        try {
            InputStream is = Files.newInputStream(Paths.get("res/graphics/tick.png"));
            Image image = new Image(is);
            is.close();
            imageView = new ImageView(image);
            imageView.setFitWidth(buttonType.getButtonWidth()/10);
            imageView.setFitHeight(buttonType.getButtonHeight());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        firstMenu.setPadding(new Insets(10,10,10,10));
        firstMenu.setVgap(8);
        firstMenu.setHgap(10);
        firstMenu.setTranslateX(windowType.getMiddleOfTheScreenX());
        firstMenu.setTranslateY(windowType.getMiddleOfTheScreenY() - buttonType.getButtonHeight());

        secondMenu.setPadding(new Insets(10,10,10,10));
        secondMenu.setVgap(8);
        secondMenu.setHgap(10);
        secondMenu.setTranslateX(windowType.getRightSideOfTheScreen());
        secondMenu.setTranslateY(windowType.getMiddleOfTheScreenY() - (buttonType.getButtonHeight() * 2));

        thirdMenu.setPadding(new Insets(10,10,10,10));
        thirdMenu.setVgap(8);
        thirdMenu.setHgap(10);
        thirdMenu.setTranslateX(windowType.getMiddleOfTheScreenX());
        thirdMenu.setTranslateY(windowType.getMiddleOfTheScreenY() - buttonType.getButtonHeight() + 30);

        fourthMenu.setPadding(new Insets(10,10,10,10));
        fourthMenu.setVgap(8);
        fourthMenu.setHgap(10);
        fourthMenu.setTranslateX(windowType.getMiddleOfTheScreenX());
        fourthMenu.setTranslateY(windowType.getMiddleOfTheScreenY() - (buttonType.getButtonHeight() * 2)+ 30);

        GridPane.setConstraints(resumeButton, 0,0);
        GridPane.setConstraints(optionButton, 0,1);
        GridPane.setConstraints(exitButton, 0,2);
        GridPane.setConstraints(resolutionButton, 0, 0);
        GridPane.setConstraints(controllerButton, 0, 1);
        GridPane.setConstraints(soundButton, 0, 2);
        GridPane.setConstraints(backButton, 0, 3);
        GridPane.setConstraints(fullHdButton, 0, 0);
        GridPane.setConstraints(halfFullHdButton, 0, 1);
        GridPane.setConstraints(hdButton, 0, 2);
        GridPane.setConstraints(imageView, 1, 0);
        GridPane.setConstraints(thirdMenuBackButton, 0, 3);
        GridPane.setConstraints(moveUpButton, 0, 0);
        GridPane.setConstraints(moveDownButton, 0, 1);
        GridPane.setConstraints(moveLeftButton, 0, 2);
        GridPane.setConstraints(moveRightButton, 0, 3);
        GridPane.setConstraints(fourthMenuBackButton, 0, 4);
        firstMenu.getChildren().addAll(resumeButton, optionButton, exitButton);
        secondMenu.getChildren().addAll(resolutionButton, controllerButton, soundButton, backButton);
        thirdMenu.getChildren().addAll(fullHdButton, halfFullHdButton, hdButton, imageView, thirdMenuBackButton);
        fourthMenu.getChildren().addAll(moveUpButton, moveDownButton, moveLeftButton, moveRightButton, fourthMenuBackButton);
        getChildren().add(firstMenu);
    }

    private void addingButtonsEvents() {
        // Buttons events of first menu
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
        exitButton.setOnMouseClicked(event -> {
            System.exit(0);
        });

        // Buttons events of second menu
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
        controllerButton.setOnMouseClicked(event -> {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), secondMenu);
            TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.75), fourthMenu);
            tt.setToY(secondMenu.getTranslateY() + 40);
            tt2.setToY(secondMenu.getTranslateY());
            fourthMenu.setVisible(false);
            getChildren().add(fourthMenu);

            if(!additivesList.isEmpty()) {
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
                        fourthMenu.setVisible(true);
                        getChildren().remove(secondMenu);
                    });
                });
            } else {
                tt.play();
                tt2.play();
                tt.setOnFinished(event1 -> {
                    fourthMenu.setVisible(true);
                    getChildren().remove(secondMenu);
                });
            }
        });
        soundButton.setOnMouseClicked(event -> {
            if (!soundButton.isClicked()) {
                scrollBar.setTranslateX(scrollBar.getTranslateX()-35);
                deleteAdditives(secondMenu);
                GridPane.setConstraints(scrollBar, 0, 2);
                scrollBar.setMin(-50);
                scrollBar.setMax(50);
                scrollBar.setValue(0);
                scrollBar.setMaxWidth(300);
                scrollBar.setBackground(Background.EMPTY);

                lastClickedButton.setVisible(true);
                lastClickedButton.getBackButton();

                soundButton.animateButton().setOnFinished(event1 -> {
                    soundButton.setVisible(false);
                    TranslateTransition tt = new TranslateTransition(Duration.seconds(0.10),  scrollBar);
                    tt.setToX(scrollBar.getTranslateX()+35);
                    tt.play();
                    secondMenu.getChildren().add(scrollBar);
                    additivesList.add(scrollBar);
                });
                lastClickedButton = soundButton;
            }
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

        // Buttons events of Video Settings
        fullHdButton.setOnMouseClicked(event -> {
            GridPane.setConstraints(imageView, 1, 0);
        });
        halfFullHdButton.setOnMouseClicked(event -> {
            GridPane.setConstraints(imageView,1,1);
        });
        hdButton.setOnMouseClicked(event -> {
            GridPane.setConstraints(imageView, 1,2);
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

        // Buttons events of Controllet
        moveUpButton.setOnMouseEntered(event -> {
            createTempButton(moveUpKey, 0);
        });
        moveUpButton.setOnMouseExited(event -> {
            fourthMenu.getChildren().remove(tempButton);
        });
        moveDownButton.setOnMouseEntered(event -> {
            createTempButton(moveDownKey, 1);
        });
        moveDownButton.setOnMouseExited(event -> {
            fourthMenu.getChildren().remove(tempButton);
        });
        moveLeftButton.setOnMouseEntered(event -> {
            createTempButton(moveLeftKey, 2);
        });
        moveLeftButton.setOnMouseExited(event -> {
            fourthMenu.getChildren().remove(tempButton);
        });
        moveRightButton.setOnMouseEntered(event -> {
            createTempButton(moveRightKey, 3);
        });
        moveRightButton.setOnMouseExited(event -> {
            fourthMenu.getChildren().remove(tempButton);
        });
        fourthMenuBackButton.setOnMouseClicked(event -> {
            getChildren().add(secondMenu);
            secondMenu.setVisible(false);
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), fourthMenu);
            TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.50), secondMenu);

            tt.setToY(fourthMenu.getTranslateY() + 30);
            tt2.setToY(fourthMenu.getTranslateY());

            tt.play();
            tt2.play();

            tt.setOnFinished(event1 -> {
                secondMenu.setVisible(true);
                getChildren().remove(fourthMenu);
            });
        });
    }
    private void createTempButton(String existingKey, int row) {
        tempButton = new Menu.MenuButton(existingKey, Color.WHITE, Menu.ButtonType.SMALLBUTTON);
        GridPane.setConstraints(tempButton, 1, row);
        fourthMenu.getChildren().add(tempButton);
    }
    private void deleteAdditives(GridPane menu) {
        for (Object x : additivesList) {
            menu.getChildren().remove(x);
        }
    }

}