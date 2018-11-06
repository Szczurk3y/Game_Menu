package sample;

public enum WindowType implements WindowInterface {
    MENU(menuWindowWidth, menuWindowHeight, "Menu", "res/graphics/menu_background.jpg"), GAME(gameWindowWidth, gameWindowHeight, "Game", "res/graphics/menu_background.jpg");

    private final int windowWidth, windowHeight;
    private final String windowTitle, windowImagePath;

    private WindowType(int width, int height, String title, String path) {
        this.windowTitle = title;
        this.windowWidth = width;
        this.windowHeight = height;
        this.windowImagePath = path;
    }

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
}

