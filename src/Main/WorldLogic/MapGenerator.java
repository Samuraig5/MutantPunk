package Main.WorldLogic;

import Main.Direction;
import Main.MathHelper;
import Main.ObjectLogic.Decorations.Decoration;
import Main.RenderLogic.MapIcon;
import Main.Settings;

import java.awt.*;
import java.io.File;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class MapGenerator
{
    static public LocalMap generateLocalMap(GameWorld gameWorld, String name, File biome)
    {
        String biomeName;
        char icon;
        Color c;
        boolean wind;
        String[] floorSource;
        String[] decorSource;
        try
        {
            Scanner fileIn = new Scanner(biome);
            biomeName = fileIn.nextLine().split("§")[1];
            icon = fileIn.nextLine().split("§")[1].toCharArray()[0];
            String[] colour = fileIn.nextLine().split("§")[1].split(":");
            c = new Color(Integer.parseInt(colour[0]), Integer.parseInt(colour[1]), Integer.parseInt(colour[2]));
            wind = !Objects.equals(fileIn.nextLine().split("§")[1], "false");
            floorSource = fileIn.nextLine().split("§");
            decorSource = fileIn.nextLine().split("§");
        }
        catch(Exception e)
        {
            throw new RuntimeException("Failed to generate local map");
        }
        LocalMap newMap = new LocalMap(new int[]{Settings.localMapSizeX, Settings.localMapSizeY},
                gameWorld, new MapIcon(icon, c), name, wind);
        Cell[][] cells = newMap.getCells();

        Decoration[] floors = new Decoration[floorSource.length-1];
        float[] floorSpawnChance = new float[floorSource.length-1];
        for (int i = 1; i < floorSource.length; i++)
        {
            String[] sub = floorSource[i].split(":");
            floors[i-1] = new Decoration(sub[0]);
            floorSpawnChance[i-1] = Float.parseFloat(sub[1]);
        }

        Decoration[] decorations = new Decoration[decorSource.length-1];
        float[] decorationSpawnChance = new float[decorSource.length-1];
        for (int i = 1; i < decorSource.length; i++)
        {
            String[] sub = decorSource[i].split(":");
            decorations[i-1] = new Decoration(sub[0]);
            decorationSpawnChance[i-1] = Float.parseFloat(sub[1]);
        }

        for (int x = 0; x < Settings.localMapSizeX; x++)
        {
            for (int y = 0; y < Settings.localMapSizeY; y++)
            {
                Decoration spawnedFloor = null;
                while (spawnedFloor == null)
                {
                    int rnd = new Random().nextInt(floors.length);
                    if (MathHelper.randomDecider(floorSpawnChance[rnd]))
                    {
                        spawnedFloor = floors[rnd];
                    }
                }
                cells[x][y].thingEnters(spawnedFloor.copy(), Direction.NONE);

                for (int i = 0; i < decorations.length; i++)
                {
                    if (MathHelper.randomDecider(decorationSpawnChance[i]))
                    {
                        cells[x][y].thingEnters(decorations[i].copy(), Direction.NONE);
                        break;
                    }
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
