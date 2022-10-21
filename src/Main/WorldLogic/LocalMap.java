package Main.WorldLogic;

public class LocalMap
{
    private final int[] size = new int[2];
    private final Cell[][] cells;

    public LocalMap(int[] xySize, Cell[][] mapCells)
    {
        size[0] = xySize[0];
        size[1] = xySize[1];
        cells = mapCells;
    }

    public int[] getSize()
    {
        return size;
    }

    public Cell[][] getCells()
    {
        return cells;
    }
}
