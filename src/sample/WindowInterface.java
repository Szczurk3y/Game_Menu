package sample;

import java.awt.*;

public interface WindowInterface {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // getting screenSize to set height and width window by procent of user monitor dimensions

    public int menuWindowWidth = (int)screenSize.getWidth()/2; // divide by 2 to center window on screen
    public int menuWindowHeight = (int)screenSize.getHeight()/2; // same as above
    public int gameWindowWidth = (int)screenSize.getWidth(); // just fullscreen
    public int gameWindowHeight = (int)screenSize.getHeight(); // same as above
}
