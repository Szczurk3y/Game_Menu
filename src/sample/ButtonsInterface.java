package sample;

import javafx.scene.paint.Color;

import java.util.LinkedList;

public interface ButtonsInterface {
    public Color color = Color.WHITE;
    public Color specialColor = Color.MAGENTA;
    public Menu.MenuButton resumeButton = new Menu.MenuButton("RESUME", color);
    public Menu.MenuButton optionButton = new Menu.MenuButton("SETTINGS", specialColor);
    public Menu.MenuButton exitButton = new Menu.MenuButton("EXIT", color);
    public Menu.MenuButton soundButton = new Menu.MenuButton("SOUND", color);
    public Menu.MenuButton backButton = new Menu.MenuButton("BACK", specialColor);
    public Menu.MenuButton controllerButton = new Menu.MenuButton("CONTROLLER", specialColor);
    public Menu.MenuButton resolutionButton = new Menu.MenuButton("VIDEO SETTINGS", specialColor);
    public Menu.MenuButton fullHdButton = new Menu.MenuButton("1920 x 1080", color);
    public Menu.MenuButton halfFullHdButton = new Menu.MenuButton("1600 x 900", color);
    public Menu.MenuButton hdButton = new Menu.MenuButton("1080 x 720", color);
    public Menu.MenuButton backButton2 = new Menu.MenuButton("BACK", specialColor);
    public LinkedList<Object> additivesList = new LinkedList<>();
}
