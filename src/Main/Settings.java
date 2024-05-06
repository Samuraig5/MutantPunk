package Main;

public class Settings
{
    // == RENDERING ==
    public static final int windowWidth = 1600;
    public static final int windowHeight = 800;
    public static final int gridScale = 48;
    public static final int menuFontSize = 12;
    //TODO: Replace this with gridScale & menuFontSize to separate map & menu scaling
    //public static final int fontSize = 12;
    // == GAME RULES ==
    public static int tickSpeed = 10;
    public static int actionPointsPerTick = tickSpeed;

    // == WORLD MAP GENERATION ==
    public static int worldMapSizeX = 5;
    public static int worldMapSizeY = 5;

    // == LOCAL MAP GENERATION ==
    public static int localMapSizeX = 50;
    public static int localMapSizeY = 50;

    // == STATIC - DO NOT CHANGE ==
    public static final float relativeMenuFontHeight = 1.2f;
    public static final float menuFontHeight = menuFontSize * relativeMenuFontHeight;
    public static final float relativeMenuFontWidth = 2/3f;
    public static final float menuFontWidth = menuFontSize * relativeMenuFontWidth;
}
