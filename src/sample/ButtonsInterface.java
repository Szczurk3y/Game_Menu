package sample;

import javafx.scene.paint.Color;

import java.util.LinkedList;

public interface ButtonsInterface {
    public Color color = Color.WHITE;
    public Color specialColor = Color.MAGENTA;
    public LinkedList<Object> additivesList = new LinkedList<>();
    // first menu buttons
    public Menu.MenuButton resumeButton = new Menu.MenuButton("RESUME", color);
    public Menu.MenuButton optionButton = new Menu.MenuButton("SETTINGS", specialColor);
    public Menu.MenuButton exitButton = new Menu.MenuButton("EXIT", color);
    // second menu buttons
    public Menu.MenuButton soundButton = new Menu.MenuButton("SOUND", color);
    public Menu.MenuButton backButton = new Menu.MenuButton("BACK", specialColor);
    public Menu.MenuButton controllerButton = new Menu.MenuButton("CONTROLLER", specialColor);
    public Menu.MenuButton resolutionButton = new Menu.MenuButton("VIDEO SETTINGS", specialColor);
    // third menu buttons
    public Menu.MenuButton fullHdButton = new Menu.MenuButton("1920 x 1080", color);
    public Menu.MenuButton halfFullHdButton = new Menu.MenuButton("1600 x 900", color);
    public Menu.MenuButton hdButton = new Menu.MenuButton("1280 x 720", color);
    public Menu.MenuButton thirdMenuBackButton = new Menu.MenuButton("BACK", specialColor);
    // fourth menu buttons
    public Menu.MenuButton moveUpButton = new Menu.MenuButton("MOVE UP", color);
    public Menu.MenuButton moveDownButton = new Menu.MenuButton("MOVE DOWN", color);
    public Menu.MenuButton moveLeftButton = new Menu.MenuButton("MOVE LEFT", color);
    public Menu.MenuButton moveRightButton = new Menu.MenuButton("MOVE RIGHT", color);
    public Menu.MenuButton fourthMenuBackButton = new Menu.MenuButton("BACK", specialColor);
}
