package Main.WorldLogic;

public class LocalMap
{
    private final int[] size = new int[2];
    private final Cell[][] cells;

    private GameWorld myWorld;

    public LocalMap(int[] xySize, Cell[][] mapCells, GameWorld gameWorld)
    {
        size[0] = xySize[0];
        size[1] = xySize[1];
        cells = mapCells;
        myWorld = gameWorld;
    }

    public int[] getSize()
    {
        return size;
    }

    public Cell[][] getCells()
    {
        return cells;
    }

    public GameWorld getMyWorld() {
        return myWorld;
    }
}
