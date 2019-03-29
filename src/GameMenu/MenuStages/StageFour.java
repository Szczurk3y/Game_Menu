package GameMenu.MenuStages;

import GameMenu.Interfaces.ButtonsInterface;
import GameMenu.Menu;
import GameMenu.MenuHandler;
import GameMenu.WindowType;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public class StageFour extends StageBuilder {
    private MenuHandler menuHandler;

    public StageFour(MenuHandler menuHandler) {
        super(10, 8, 10, menuHandler.windowType.getMiddleOfTheScreenX(),
                menuHandler.windowType.getMiddleOfTheScreenY() + (Menu.ButtonType.STANDARD.getButtonHeight()));
        this.menuHandler = menuHandler;
        general_initialize();
        events_initialize();
    }

    @Override
    protected void general_initialize() {
        setConstraints(moveUpButton, 0, 0);
        setConstraints(moveDownButton, 0, 1);
        setConstraints(moveLeftButton, 0, 2);
        setConstraints(moveRightButton, 0, 3);
        setConstraints(stageFour_backButton, 0, 4);
        setRowIndex(resetButton, 3);
        resetButton.setTranslateX(-Menu.ButtonType.STANDARD.getButtonWidth()/1.5);
        getChildren().addAll(moveUpButton, moveDownButton, moveLeftButton, moveRightButton, stageFour_backButton, resetButton);
    }

    @Override
    protected void events_initialize() {
        resetButton.setOnMouseEntered(event -> {
            menuHandler.createTempButton(menuHandler.moveUpKey, 0);
            menuHandler.createTempButton(menuHandler.moveDownKey, 1);
            menuHandler.createTempButton(menuHandler.moveLeftKey, 2);
            menuHandler.createTempButton(menuHandler.moveRightKey, 3);
        });
        resetButton.setOnMouseClicked(event -> {
            menuHandler.moveUpKey = "W";
            menuHandler.moveDownKey = "S";
            menuHandler.moveLeftKey = "A";
            menuHandler.moveRightKey = "D";
            menuHandler.createTempButton(menuHandler.moveUpKey, 0);
            menuHandler.createTempButton(menuHandler.moveDownKey, 1);
            menuHandler.createTempButton(menuHandler.moveLeftKey, 2);
            menuHandler.createTempButton(menuHandler.moveRightKey, 3);
        });
        resetButton.setOnMouseExited(event -> {
            menuHandler.deleteAdditives(this);
        });
        moveUpButton.setOnMouseEntered(event -> {
            moveUpButton.setTranslateX(10);
            menuHandler.createTempButton(menuHandler.moveUpKey, 0);
        });
        moveUpButton.setOnMouseExited(event -> {
            moveUpButton.setTranslateX(0);
            menuHandler.moveUpKey = menuHandler.tempButton.getText();
            this.getChildren().remove(menuHandler.tempButton);
        });

        moveDownButton.setOnMouseEntered(event -> {
            moveDownButton.setTranslateX(10);
            menuHandler.createTempButton(menuHandler.moveDownKey, 1);
        });
        moveDownButton.setOnMouseExited(event -> {
            moveDownButton.setTranslateX(0);
            menuHandler.moveDownKey = menuHandler.tempButton.getText();
            this.getChildren().remove(menuHandler.tempButton);
        });

        moveLeftButton.setOnMouseEntered(event -> {
            moveLeftButton.setTranslateX(10);
            menuHandler.createTempButton(menuHandler.moveLeftKey, 2);
        });
        moveLeftButton.setOnMouseExited(event -> {
            moveLeftButton.setTranslateX(0);
            menuHandler.moveLeftKey = menuHandler.tempButton.getText();
            this.getChildren().remove(menuHandler.tempButton);
        });

        moveRightButton.setOnMouseEntered(event -> {
            moveRightButton.setTranslateX(10);
            menuHandler.createTempButton(menuHandler.moveRightKey, 3);
        });
        moveRightButton.setOnMouseExited(event -> {
            moveRightButton.setTranslateX(0);
            menuHandler.moveRightKey = menuHandler.tempButton.getText();
            this.getChildren().remove(menuHandler.tempButton);
        });
    }
}
