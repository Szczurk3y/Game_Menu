package GameMenu;

import GameMenu.Interfaces.ButtonsInterface;
import GameMenu.MenuStages.StageFour;
import GameMenu.MenuStages.StageOne;
import GameMenu.MenuStages.StageThree;
import GameMenu.MenuStages.StageTwo;
import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Scanner;

public class MenuHandler extends Parent implements ButtonsInterface {
    public WindowType windowType;
    public String moveUpKey, moveDownKey, moveLeftKey, moveRightKey;
    public int resolution;
    public int volume;
    public Menu.MenuButton lastClickedButton = new Menu.MenuButton("");
    public Menu.MenuButton tempButton;
    public StageOne stageOne;
    public StageTwo stageTwo;
    public StageThree stageThree;
    public StageFour stageFour;

    public MenuHandler(WindowType windowType) {
        this.windowType = windowType;
        general_initialize();
    }

    private void general_initialize() {
        try {
            Scanner scanner = new Scanner(new File("res/files/Settings.txt"));
            moveUpKey = scanner.nextLine();
            moveDownKey = scanner.nextLine();
            moveLeftKey = scanner.nextLine();
            moveRightKey = scanner.nextLine();
            resolution = Integer.parseInt(scanner.nextLine());
            volume = Integer.parseInt(scanner.nextLine());
            scanner.close();
        } catch (IOException | NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
        stageOne = new StageOne(this);
        stageTwo = new StageTwo(this);
        stageThree = new StageThree(this);
        stageFour = new StageFour(this);
        events_initialize();
        // setting up first visible stage
        getChildren().add(stageOne);
    }

    private void events_initialize() {
        optionButton.setOnMouseClicked(event -> {
            getChildren().add(stageTwo);
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), stageOne);
            TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.45), stageTwo);

            tt.setToX(windowType.getLeftSideOfTheScreen());
            tt2.setToX(windowType.getMiddleOfTheScreenX());

            tt.play();
            tt2.play();

            tt.setOnFinished(event1 -> {
                getChildren().remove(stageOne);
            });
        });

        resolutionButton.setOnMouseClicked(event -> {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), stageTwo);
            TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.75), stageThree);
            tt.setToY(stageTwo.getTranslateY() + 40);
            tt2.setToY(stageTwo.getTranslateY());
            stageThree.setVisible(false);
            getChildren().add(stageThree);

            if (!additivesList.isEmpty()) {
                deleteAdditives(stageTwo);
            }
            if (!lastClickedButton.isVisible()) {
                lastClickedButton.setVisible(true);
            }
            if (lastClickedButton.isClicked()) {
                lastClickedButton.getBackButton().setOnFinished(event1 -> {
                    tt.play();
                    tt2.play();
                    tt.setOnFinished(event2 -> {
                        stageThree.setVisible(true);
                        getChildren().remove(stageTwo);
                    });
                });
            } else {
                tt.play();
                tt2.play();
                tt.setOnFinished(event1 -> {
                    stageThree.setVisible(true);
                    getChildren().remove(stageTwo);
                });
            }
        });

        controllerButton.setOnMouseClicked(event -> {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), stageTwo);
            TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.75), stageFour);
            tt.setToY(stageTwo.getTranslateY() + 40);
            tt2.setToY(stageTwo.getTranslateY());
            stageFour.setVisible(false);
            getChildren().add(stageFour);

            if(!additivesList.isEmpty()) {
                deleteAdditives(stageTwo);
            }
            if (!lastClickedButton.isVisible()) {
                lastClickedButton.setVisible(true);
            }
            if (lastClickedButton.isClicked()) {
                lastClickedButton.getBackButton().setOnFinished(event1 -> {
                    tt.play();
                    tt2.play();
                    tt.setOnFinished(event2 -> {
                        stageFour.setVisible(true);
                        getChildren().remove(stageTwo);
                    });
                });
            } else {
                tt.play();
                tt2.play();
                tt.setOnFinished(event1 -> {
                    stageFour.setVisible(true);
                    getChildren().remove(stageTwo);
                });
            }
        });

        stageTwo_backButton.setOnMouseClicked(event -> {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), stageTwo);
            TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.45), stageOne);
            tt.setToX(windowType.getRightSideOfTheScreen());
            tt2.setToX(windowType.getMiddleOfTheScreenX());

            if (!additivesList.isEmpty()) {
                deleteAdditives(stageTwo);
            }
            if (!lastClickedButton.isVisible()) {
                lastClickedButton.setVisible(true);
            }
            if (lastClickedButton.isClicked()) {
                lastClickedButton.getBackButton().setOnFinished(event1 -> {
                    getChildren().add(stageOne);
                    tt.play();
                    tt2.play();
                    tt.setOnFinished(event2 -> {
                        getChildren().remove(stageTwo);
                    });
                });
            } else {
                getChildren().add(stageOne);
                tt.play();
                tt2.play();
                tt.setOnFinished(event2 -> {
                    getChildren().remove(stageTwo);
                });
            }

        });

        stageThree_backButton.setOnMouseClicked(event -> {
            System.out.println(resolution);
            getChildren().add(stageTwo);
            stageTwo.setVisible(false);
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), stageThree);
            TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.50), stageTwo);

            tt.setToY(stageThree.getTranslateY() + 30);
            tt2.setToY(stageThree.getTranslateY());

            tt.play();
            tt2.play();

            tt.setOnFinished(event1 -> {
                stageTwo.setVisible(true);
                getChildren().remove(stageThree);
            });
        });

        stageFour_backButton.setOnMouseClicked(event -> {
            getChildren().add(stageTwo);
            stageTwo.setVisible(false);
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), stageFour);
            TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.50), stageTwo);

            tt.setToY(stageFour.getTranslateY() + 30);
            tt2.setToY(stageFour.getTranslateY());

            tt.play();
            tt2.play();

            tt.setOnFinished(event1 -> {
                stageTwo.setVisible(true);
                getChildren().remove(stageFour);
            });
        });
    }

    public void deleteAdditives(GridPane stage) {
        for (Object x : additivesList) {
            stage.getChildren().remove(x);
        }
    }

    public String createTempButton(String existingKey, int row) {
        String keyPressed;
        tempButton = new Menu.MenuButton(existingKey, Menu.ButtonType.SMALL);
        tempButton.setTranslateX(10);
        getScene().setOnKeyPressed(event -> {
            if (event.getCode().isLetterKey() || event.getCode().isArrowKey()) {
                tempButton.setText(event.getCode().toString());
            }
        });
        keyPressed = tempButton.getText();
        GridPane.setConstraints(tempButton, 1, row);
        stageFour.getChildren().add(tempButton);
        additivesList.add(tempButton);
        return keyPressed;
    }
}
