package Main;

public class Settings
{
    // == RENDERING ==
    public static final int windowWidth = 1200;
    public static final int windowHeight = 600;

    public static final int fontSize = 10;

    // == MAP GENERATION ==
    public static boolean spawnGrass = true;

    public static boolean spawnWalls = true;
    public static float wallCover = 0.01f;

    public static int updateSpeed = 333;

    public static int mapSizeX = 50;

    public static int mapSizeY = 25;

    // == STATIC - DO NOT CHANGE ==
    public static final float relativeFontHeight = 1.2f;
    public static final float fontHeight = fontSize * relativeFontHeight;
    public static final float relativeFontWidth = 2/3f;
    public static final float fontWidth = fontSize * relativeFontWidth;
}
