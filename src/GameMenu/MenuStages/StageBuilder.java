package GameMenu.MenuStages;

import GameMenu.Interfaces.ButtonsInterface;
import GameMenu.Menu;
import GameMenu.MenuHandler;
import GameMenu.WindowType;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.awt.*;

public abstract class StageBuilder extends GridPane implements ButtonsInterface {

    public StageBuilder(int padding, int Vgap, int Hgap, double posicionX, double posicionY) {
        setPadding(new Insets(padding));
        setVgap(Vgap);
        setHgap(Hgap);
        setTranslateX(posicionX);
        setTranslateY(posicionY);
    }

    protected abstract void general_initialize();

    protected abstract void events_initialize();
}
