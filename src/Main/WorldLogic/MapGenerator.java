package Main.WorldLogic;

import Main.Direction;
import Main.ObjectLogic.Decorations.Decoration;
import Main.ObjectLogic.Decorations.DirtFloor;
import Main.ObjectLogic.Walls.Wall;
import Main.Settings;

public class MapGenerator
{
    static public LocalMap generateLocalMapWithWalls(int[] size, GameWorld gameWorld, String name, float wallCover)
    {
        LocalMap newMap = new LocalMap(size, gameWorld, name);
        Cell[][] cells = newMap.getCells();

        Decoration grass = new Decoration("Resources/Grass");

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
                    Decoration newGrass = grass.copy();
                    cells[x][y].thingEnters(newGrass, Direction.NONE);
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
