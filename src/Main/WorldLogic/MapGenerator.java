package Main.WorldLogic;

import Main.Direction;
import Main.ObjectLogic.Decorations.Decoration;
import Main.Settings;

public class MapGenerator
{
    static public LocalMap generateLocalMapWithWalls(int[] size, GameWorld gameWorld, String name, float wallCover)
    {
        LocalMap newMap = new LocalMap(size, gameWorld, name);
        Cell[][] cells = newMap.getCells();

        Decoration grass = new Decoration("Resources/Decorations/Grass");
        Decoration dirtFloor = new Decoration("Resources/Decorations/DirtFloor");
        Decoration stoneWall = new Decoration("Resources/Decorations/StoneWall");

        for (int x = 0; x < size[0]; x++)
        {
            for (int y = 0; y < size[1]; y++)
            {
                cells[x][y].thingEnters(dirtFloor.copy(), Direction.NONE);
                if (Settings.spawnWalls && Math.random() < wallCover)
                {
                    cells[x][y].thingEnters(stoneWall.copy(), Direction.NONE);
                }
                else if (Settings.spawnGrass)
                {
                    cells[x][y].thingEnters(grass.copy(), Direction.NONE);
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
