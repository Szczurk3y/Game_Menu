package GameMenu.Interfaces;

import GameMenu.*;

import java.util.LinkedList;

public interface ButtonsInterface {
    public LinkedList<Object> additivesList = new LinkedList<>();
    // first menu buttons
    public Menu.MenuButton resumeButton = new Menu.MenuButton("START / RESUME");
    public Menu.MenuButton optionButton = new Menu.MenuButton("SETTINGS");
    public Menu.MenuButton stageOne_exitButton = new Menu.MenuButton("EXIT");
    // second menu buttons
    public Menu.MenuButton soundButton = new Menu.MenuButton("SOUND");
    public Menu.MenuButton controllerButton = new Menu.MenuButton("CONTROLLER");
    public Menu.MenuButton resolutionButton = new Menu.MenuButton("VIDEO SETTINGS");
    public Menu.MenuButton stageTwo_backButton = new Menu.MenuButton("BACK");
    // third menu buttons
    public Menu.MenuButton fullHdButton = new Menu.MenuButton("1920 x 1080");
    public Menu.MenuButton halfFullHdButton = new Menu.MenuButton("1600 x 900");
    public Menu.MenuButton hdButton = new Menu.MenuButton("1280 x 720");
    public Menu.MenuButton stageThree_backButton = new Menu.MenuButton("BACK");
    // fourth menu buttons
    public Menu.MenuButton resetButton = new Menu.MenuButton("RESET", Menu.ButtonType.SMALL);
    public Menu.MenuButton moveUpButton = new Menu.MenuButton("MOVE UP");
    public Menu.MenuButton moveDownButton = new Menu.MenuButton("MOVE DOWN");
    public Menu.MenuButton moveLeftButton = new Menu.MenuButton("MOVE LEFT");
    public Menu.MenuButton moveRightButton = new Menu.MenuButton("MOVE RIGHT");
    public Menu.MenuButton stageFour_backButton = new Menu.MenuButton("BACK");
}
