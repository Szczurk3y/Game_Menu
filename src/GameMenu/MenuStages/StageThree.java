package GameMenu.MenuStages;

import GameMenu.Interfaces.ButtonsInterface;
import GameMenu.Menu;
import GameMenu.MenuHandler;
import GameMenu.WindowType;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StageThree extends StageBuilder {
    private ImageView imageView;
    private MenuHandler menuHandler;

    public StageThree(MenuHandler menuHandler) {
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
        setTranslateY(menuHandler.windowType.getMiddleOfTheScreenY() - Menu.ButtonType.STANDARD.getButtonHeight() + 30); // + 30 to do animation from the top a little down
        try {
            InputStream is = Files.newInputStream(Paths.get("res/graphics/tick.png"));
            Image image = new Image(is);
            is.close();
            imageView = new ImageView(image);
            imageView.setFitWidth(Menu.ButtonType.SMALL.getButtonWidth());
            imageView.setFitHeight(Menu.ButtonType.SMALL.getButtonHeight());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        setConstraints(imageView, 1, menuHandler.resolution);
        setConstraints(fullHdButton, 0, 0);
        setConstraints(halfFullHdButton, 0, 1);
        setConstraints(hdButton, 0, 2);
        setConstraints(stageThree_backButton, 0, 3);
        getChildren().addAll(fullHdButton, halfFullHdButton, hdButton, imageView, stageThree_backButton);
    }

    @Override
    protected void events_initialize() {
        fullHdButton.setOnMouseClicked(event -> {
            GridPane.setConstraints(imageView, 1, 0);
            menuHandler.resolution = 0;
        });
        halfFullHdButton.setOnMouseClicked(event -> {
            GridPane.setConstraints(imageView,1,1);
            menuHandler.resolution = 1;
        });
        hdButton.setOnMouseClicked(event -> {
            GridPane.setConstraints(imageView, 1,2);
            menuHandler.resolution = 2;
        });
    }
}
