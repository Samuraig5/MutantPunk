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

    // == MAP GENERATION ==
    public static boolean spawnGrass = true;
    public static boolean spawnWalls = true;
    public static float wallCover = 0.01f;
    public static int mapSizeX = 250;
    public static int mapSizeY = 250;

    // == STATIC - DO NOT CHANGE ==
    public static final float relativeFontHeight = 1.2f;
    public static final float fontHeight = fontSize * relativeFontHeight;
    public static final float relativeFontWidth = 2/3f;
    public static final float fontWidth = fontSize * relativeFontWidth;
}
