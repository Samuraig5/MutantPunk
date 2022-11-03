package Main.WorldLogic;

import java.util.ArrayList;

public class MapGenerator
{
    static public LocalMap generateEmptyLocalMap(int[] size, GameWorld gameWorld, String name)
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
        LocalMap newMap = new LocalMap(size,cells, gameWorld, name);
        newMap.getMyWorld().getLocalMaps().add(newMap);
        return newMap;
    }

    static public GameWorld generateNewGameWorld(String worldName)
    {
        return new GameWorld(worldName);
    }
}
