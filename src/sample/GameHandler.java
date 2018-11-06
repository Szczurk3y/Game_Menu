package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class GameHandler {
    private String[] args;

    public GameHandler(String[] args) {
        this.args = args;
        Application.launch(Menu.class, args);
    }
}
