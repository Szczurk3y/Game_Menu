package GameMenu.MenuStages;

import GameMenu.Interfaces.ButtonsInterface;
import GameMenu.MenuHandler;
import GameMenu.WindowType;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public abstract class StageBuilder extends GridPane implements ButtonsInterface {

    protected abstract void general_initialize();

    protected abstract void events_initialize();

    public void makeHorizontalAnimation(GridPane currentStage, GridPane nextStage, double currentStage_endLocation, double nextStage_endLocation) {
        getChildren().add(nextStage);
        TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), currentStage);
        TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.45), nextStage);

        tt.setToX(currentStage_endLocation);
        tt2.setToX(nextStage_endLocation);

        tt.play();
        tt2.play();

        tt.setOnFinished(event1 -> {
            getChildren().remove(currentStage);
        });
    }

    public void makeVerticalAnimation(GridPane currentStage, GridPane nextStage, double currentStage_endLocation, double nextStage_endLocation) {
        getChildren().add(nextStage);
        TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), currentStage);
        TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.45), nextStage);

        tt.setToX(currentStage_endLocation);
        tt2.setToX(nextStage_endLocation);

        tt.play();
        tt2.play();

        tt.setOnFinished(event1 -> {
            getChildren().remove(currentStage);
        });
    }
}
