package Main.WorldLogic;

import Main.Direction;
import Main.ObjectLogic.Decorations.Decoration;
import Main.RenderLogic.MapIcon;
import Main.Settings;

import java.awt.*;
import java.io.File;
import java.util.Random;
import java.util.Set;

public class MapGenerator
{
    static public LocalMap generateLocalMap(GameWorld gameWorld, String name, File biome)
    {
        LocalMap newMap = new LocalMap(new int[] {Settings.localMapSizeX, Settings.localMapSizeY},
                gameWorld, new MapIcon('#', Color.green), name);
        Cell[][] cells = newMap.getCells();

        Decoration grass = new Decoration("Resources/Decorations/Grass");
        Decoration dirtFloor = new Decoration("Resources/Decorations/DirtFloor");
        Decoration stoneWall = new Decoration("Resources/Decorations/StoneWall");

        for (int x = 0; x < Settings.localMapSizeX; x++)
        {
            for (int y = 0; y < Settings.localMapSizeY; y++)
            {
                cells[x][y].thingEnters(dirtFloor.copy(), Direction.NONE);
                if (Settings.spawnWalls)
                {
                    cells[x][y].thingEnters(stoneWall.copy(), Direction.NONE);
                }
                else if (Settings.spawnGrass)
                {
                    cells[x][y].thingEnters(grass.copy(), Direction.NONE);
                }
            }
        }
        //newMap.getMyWorld().getLocalMaps().add(newMap);
        return newMap;
    }

    static public GameWorld generateNewGameWorld(String worldName)
    {
        GameWorld gw = new GameWorld(worldName);

        File directory = new File("Resources/Biomes");
        File[] biomes = directory.listFiles();

        for (int x = 0; x < Settings.worldMapSizeX; x++)
        {
            for (int y = 0; y < Settings.worldMapSizeY; y++)
            {
                int rnd = new Random().nextInt(biomes.length);
                File targetBiome = biomes[rnd];
                gw.getLocalMaps()[x][y] = generateLocalMap(gw, "Local Map: " + x + " / " + y, targetBiome);
            }
        }

        return gw;
    }
}
