package Main;

public class Settings
{
    // == RENDERING ==
    public static final int windowWidth = 1600;
    public static final int windowHeight = 800;
    public static final int fontSize = 12;

    // == GAME RULES ==
    public static int tickSpeed = 10;
    public static int actionPointsPerTick = tickSpeed;

    // == WORLD MAP GENERATION ==
    public static int worldMapSizeX = 5;
    public static int worldMapSizeY = 5;

    // == LOCAL MAP GENERATION ==
    public static int localMapSizeX = 250;
    public static int localMapSizeY = 250;

    // == STATIC - DO NOT CHANGE ==
    public static final float relativeFontHeight = 1.2f;
    public static final float fontHeight = fontSize * relativeFontHeight;
    public static final float relativeFontWidth = 2/3f;
    public static final float fontWidth = fontSize * relativeFontWidth;
}
