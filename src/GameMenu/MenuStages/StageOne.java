package GameMenu.MenuStages;

import GameMenu.Interfaces.ButtonsInterface;
import GameMenu.Menu;
import GameMenu.MenuHandler;
import GameMenu.WindowType;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.Formatter;

public class StageOne extends StageBuilder {
    private Formatter fileWriter;
    MenuHandler menuHandler;

    public StageOne(MenuHandler menuHandler) {
        super();
        this.menuHandler = menuHandler;
        general_initialize();
        events_initialize();
    }

    @Override
    protected void general_initialize() {
        setPadding(new Insets(10,10,10,10));
        setVgap(8);
        setHgap(10);
        setTranslateX(menuHandler.windowType.getMiddleOfTheScreenX());
        setTranslateY(menuHandler.windowType.getMiddleOfTheScreenY() - Menu.ButtonType.STANDARD.getButtonHeight());
        setConstraints(resumeButton, 0,0);
        setConstraints(optionButton, 0,1);
        setConstraints(stageOne_exitButton, 0,2);
        getChildren().addAll(resumeButton, optionButton, stageOne_exitButton);
    }

    @Override
    protected void events_initialize() {
        resumeButton.setOnMouseClicked(event -> {

        });

        stageOne_exitButton.setOnMouseClicked(event -> {
            try {
                fileWriter = new Formatter("./res/files/Settings.txt");
                fileWriter.format(menuHandler.moveUpKey + "\r\n" + menuHandler.moveDownKey + "\r\n" + menuHandler.moveLeftKey + "\r\n" + menuHandler.moveRightKey + "\r\n");
                fileWriter.format(menuHandler.resolution + "\r\n" + String.valueOf(menuHandler.volume));
                fileWriter.close();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            System.exit(0);
        });
    }
}
