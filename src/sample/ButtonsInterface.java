package sample;

import java.util.LinkedList;

public interface ButtonsInterface {
    public Menu.MenuButton resumeButton = new Menu.MenuButton("RESUME");
    public Menu.MenuButton optionButton = new Menu.MenuButton("SETTINGS");
    public Menu.MenuButton exitButton = new Menu.MenuButton("EXIT");
    public Menu.MenuButton soundButton = new Menu.MenuButton("SOUND");
    public Menu.MenuButton backButton = new Menu.MenuButton("BACK");
    public Menu.MenuButton tempButton = new Menu.MenuButton("TEMP BUTTON");
    public Menu.MenuButton tempButton2 = new Menu.MenuButton("TEMP BUTTTON");
    public LinkedList<Object> additivesList = new LinkedList<>();
}
