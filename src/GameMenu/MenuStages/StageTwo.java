package GameMenu.MenuStages;

import GameMenu.Interfaces.ButtonsInterface;
import GameMenu.Menu;
import GameMenu.MenuHandler;
import GameMenu.WindowType;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.Formatter;

public class StageTwo extends StageBuilder {
    private MenuHandler menuHandler;
    private ScrollBar scrollBar = new ScrollBar();
    private Menu.MenuButton tempButton;

    public StageTwo(MenuHandler menuHandler) {
        this.menuHandler = menuHandler;
        general_initialize();
        events_initialize();
    }

    @Override
    protected void general_initialize() {
        setPadding(new Insets(10,10,10,10));
        setVgap(8);
        setHgap(10);
        setTranslateX(menuHandler.windowType.getRightSideOfTheScreen()); // to do animation from the right side to the middle
        setTranslateY(menuHandler.windowType.getMiddleOfTheScreenY() - (Menu.ButtonType.STANDARD.getButtonHeight())); //
        setConstraints(resolutionButton, 0, 0);
        setConstraints(controllerButton, 0, 1);
        setConstraints(soundButton, 0, 2);
        setConstraints(stageTwo_backButton, 0, 3);
        getChildren().addAll(resolutionButton, controllerButton, soundButton, stageTwo_backButton);
    }

    @Override
    protected void events_initialize() {
        soundButton.setOnMouseClicked(event -> {
            if (!soundButton.isClicked()) {
                scrollBar.setTranslateX(scrollBar.getTranslateX()-35);
                menuHandler.deleteAdditives(this);
                GridPane.setConstraints(scrollBar, 0, 2);
                scrollBar.setMin(0);
                scrollBar.setMax(50);
                scrollBar.setValue(menuHandler.volume);
                scrollBar.setMaxWidth(soundButton.getWidth());
                scrollBar.setMaxHeight(soundButton.getHeight()/2);
                scrollBar.setBackground(new Background(new BackgroundFill(Color.CRIMSON, CornerRadii.EMPTY, Insets.EMPTY)));

                menuHandler.lastClickedButton.setVisible(true);
                menuHandler.lastClickedButton.getBackButton();

                soundButton.animateButton().setOnFinished(event1 -> {
                    soundButton.setVisible(false);
                    TranslateTransition tt = new TranslateTransition(Duration.seconds(0.10),  scrollBar);
                    tt.setToX(scrollBar.getTranslateX()+35);
                    tt.play();
                    tt.setOnFinished(event2 -> {
                        tempButton = new Menu.MenuButton(String.valueOf((int)scrollBar.getValue()), Menu.ButtonType.SMALL);
                        this.getChildren().add(tempButton);
                        GridPane.setConstraints(tempButton, 1, 2);
                        scrollBar.valueProperty().addListener(new ChangeListener<Number>() {
                            @Override
                            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                                menuHandler.volume = newValue.intValue();
                                tempButton.setText(String.valueOf(menuHandler.volume));
                            }
                        });
                        additivesList.add(tempButton);
                    });

                    this.getChildren().add(scrollBar);
                    additivesList.add(scrollBar);
                });
                menuHandler.lastClickedButton = soundButton;
            }
        });
    }
}
