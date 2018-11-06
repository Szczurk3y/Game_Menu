package sample;

public enum WindowType implements WindowInterface {
    MENU(menuWindowWidth, menuWindowHeight, "Menu", "res/graphics/menu_background.jpg", false),
    GAME(gameWindowWidth, gameWindowHeight, "Game", "res/graphics/menu_background.jpg", true);

    private final int windowWidth, windowHeight;
    private final String windowTitle, windowImagePath;
    private final boolean isFullScreen;

    private WindowType(int width, int height, String title, String path, boolean isFullScreen) {
        this.windowTitle = title;
        this.windowWidth = width;
        this.windowHeight = height;
        this.windowImagePath = path;
        this.isFullScreen = isFullScreen;
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
}

