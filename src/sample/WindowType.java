package sample;

public enum WindowType implements WindowInterface {
    MENU(menuWindowWidth, menuWindowHeight, "Menu", "res/graphics/menu_background.jpg", false),
    FULLSCREAN(gameWindowWidth, gameWindowHeight, "Game", "res/graphics/menu_background.jpg", true);

    private final int windowWidth, windowHeight;
    private final String windowTitle, windowImagePath;
    private final boolean isFullScreen;
    public final double centerOfScreenX;
    public final double centerOfScreenY;
    public final double leftEdgeOfScreenX;
    public final double upperEdgeOfScreenY;
    public final double rightEdgeOfScreenX;
    public final double lowerEdgeOfScreenY;

    private WindowType(int width, int height, String title, String path, boolean isFullScreen) {
        this.windowTitle = title;
        this.windowWidth = width;
        this.windowHeight = height;
        this.windowImagePath = path;
        this.isFullScreen = isFullScreen;
        this.centerOfScreenX = windowWidth/2 - (windowWidth/4)/2;
        this.leftEdgeOfScreenX = -1 * windowWidth/2;
        this.rightEdgeOfScreenX = windowWidth;
        this.centerOfScreenY = windowHeight/2 - (windowHeight/18)/2 - 50;
        this.lowerEdgeOfScreenY = windowHeight;
        this.upperEdgeOfScreenY = -1 * windowHeight;
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

    public double getLeftEdgeOfScreenX() { return  this.leftEdgeOfScreenX; }

    public double getRightEdgeOfScreenX() {
        return rightEdgeOfScreenX;
    }

    public double getCenterOfScreenX() {
        return centerOfScreenX;
    }

    public double getCenterOfScreenY() {
        return centerOfScreenY;
    }

    public double getUpperEdgeOfScreenY() {
        return upperEdgeOfScreenY;
    }

    public double getLowerEdgeOfScreenY() {
        return lowerEdgeOfScreenY;
    }
}

