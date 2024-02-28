package Main.WorldLogic;

import Main.MathHelper;
import Main.ObjectLogic.Walls.Wall;

import java.util.ArrayList;
import java.util.Random;

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

    static public LocalMap generateLocalMapWithWalls(int[] size, GameWorld gameWorld, String name, float wallCover)
    {
        Cell[][] cells = new Cell[size[0]][size[1]];
        for (int x = 0; x < size[0]; x++)
        {
            for (int y = 0; y < size[1]; y++)
            {
                int[] xy = {x,y};
                cells[x][y] = new Cell(xy, new ArrayList<>());
                if (Math.random() < wallCover)
                {
                    Wall wall = new Wall();
                    cells[x][y].thingEnters(wall);
                }
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
