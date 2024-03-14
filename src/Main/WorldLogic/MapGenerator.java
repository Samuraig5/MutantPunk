package Main.WorldLogic;

import Main.Direction;
import Main.ObjectLogic.Decorations.DirtFloor;
import Main.ObjectLogic.Decorations.Grass;
import Main.ObjectLogic.Walls.Wall;
import Main.Settings;

import java.util.ArrayList;

public class MapGenerator
{
    static public LocalMap generateLocalMapWithWalls(int[] size, GameWorld gameWorld, String name, float wallCover)
    {
        LocalMap newMap = new LocalMap(size, gameWorld, name);

        Cell[][] cells = newMap.getCells();

        for (int x = 0; x < size[0]; x++)
        {
            for (int y = 0; y < size[1]; y++)
            {
                cells[x][y].thingEnters(new DirtFloor(), Direction.NONE);
                if (Settings.spawnWalls && Math.random() < wallCover)
                {
                    Wall wall = new Wall();
                    cells[x][y].thingEnters(wall, Direction.NONE);
                }
                else if (Settings.spawnGrass)
                {
                    Grass grass = new Grass();
                    cells[x][y].thingEnters(grass, Direction.NONE);
                }
            }
        }
        newMap.getMyWorld().getLocalMaps().add(newMap);
        return newMap;
    }

    static public GameWorld generateNewGameWorld(String worldName)
    {
        return new GameWorld(worldName);
    }
}
