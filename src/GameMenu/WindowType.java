package GameMenu;

import GameMenu.Interfaces.WindowInterface;

public enum WindowType implements WindowInterface {
    MENU(menuWindowWidth, menuWindowHeight, "Menu", "res/graphics/menu_background.jpg", false),
    FULLSCREAN(gameWindowWidth, gameWindowHeight, "Game", "res/graphics/menu_background.jpg", true);

    private final int windowWidth, windowHeight;
    private final String windowTitle, windowImagePath;
    private final boolean isFullScreen;
    private final double middleOfTheScreenX;
    private final double middleOfTheScreenY;
    private final double leftSideOfTheScreen;
    private final double topOfTheScreen;
    private final double rightSideOfTheScreen;
    private final double bottomOfTheScreen;

    private WindowType(int width, int height, String title, String path, boolean isFullScreen) {
        this.windowTitle = title;
        this.windowWidth = width;
        this.windowHeight = height;
        this.windowImagePath = path;
        this.isFullScreen = isFullScreen;
        this.middleOfTheScreenX = windowWidth/2 - (windowWidth/4)/2;
        this.leftSideOfTheScreen = -1 * windowWidth/2;
        this.rightSideOfTheScreen = windowWidth;
        this.middleOfTheScreenY = windowHeight/2 - (windowHeight/18)/2 - 50;
        this.bottomOfTheScreen = windowHeight;
        this.topOfTheScreen = -1 * windowHeight;
    }

    public boolean isFullScreen() { return this.isFullScreen; }

    public int getWindowWidth() {
        return this.windowWidth;
    }

    public int getWindowHeight() {
        return this.windowHeight;
    }

    public String getWindowTitle() {
        return this.windowTitle;
    }

    public String getWindowImagePath() { return this.windowImagePath; }

    public double getLeftSideOfTheScreen() { return  this.leftSideOfTheScreen; }

    public double getRightSideOfTheScreen() {
        return rightSideOfTheScreen;
    }

    public double getMiddleOfTheScreenX() {
        return middleOfTheScreenX;
    }

    public double getMiddleOfTheScreenY() {
        return middleOfTheScreenY;
    }

    public double getTopOfTheScreen() {
        return topOfTheScreen;
    }

    public double getBottomOfTheScreen() {
        return bottomOfTheScreen;
    }
}

