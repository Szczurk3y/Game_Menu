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
    public Menu.MenuButton resolutionButton = new Menu.MenuButton("VIDEO SETTINGS");
    public Menu.MenuButton fullHdButton = new Menu.MenuButton("1920 x 1080");
    public Menu.MenuButton halfFullHdButton = new Menu.MenuButton("1600 x 900");
    public Menu.MenuButton hdButton = new Menu.MenuButton("1080 x 720");
    public Menu.MenuButton backButton2 = new Menu.MenuButton("BACK");
    public LinkedList<Object> additivesList = new LinkedList<>();
}
