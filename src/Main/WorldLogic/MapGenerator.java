package Main.WorldLogic;

import java.util.ArrayList;

public class MapGenerator
{
    static public LocalMap generateEmptyLocalMap(int[] size)
    {
        Cell[][] cells = new Cell[size[0]][size[1]];
        for (int x = 0; x < size[0]; x++)
        {
            for (int y = 0; y < size[1]; y++)
            {
                int[] xy = {x,y};
                cells[x][y] = new Cell(xy, new ArrayList<>());
            }
        }
        return new LocalMap(size,cells);
    }
}
