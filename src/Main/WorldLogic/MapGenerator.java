package Main.WorldLogic;

import Main.Direction;
import Main.MathHelper;
import Main.ObjectLogic.Decorations.Decoration;
import Main.ObjectLogic.Thing;
import Main.RenderLogic.Logic.MapIcon;
import Main.Settings;

import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.List;

public class MapGenerator
{
    static public LocalMap generateLocalMap(GameWorld gameWorld, String name, File biome)
    {
        String biomeName;
        char icon;
        Color c;
        boolean wind;
        List<ObjectSource> objectSources = new ArrayList<>();
        try
        {
            Scanner fileIn = new Scanner(biome);
            biomeName = fileIn.nextLine().split("§")[1];
            icon = fileIn.nextLine().split("§")[1].toCharArray()[0];
            String[] colour = fileIn.nextLine().split("§")[1].split(":");
            c = new Color(Integer.parseInt(colour[0]), Integer.parseInt(colour[1]), Integer.parseInt(colour[2]));
            wind = !Objects.equals(fileIn.nextLine().split("§")[1], "false");

            if (Objects.equals(fileIn.nextLine(), "§OBJECTS§"))
            {
                List<String> sources = new ArrayList<>();
                String s = fileIn.nextLine();
                while (!Objects.equals(s, "§END§"))
                {

                    sources.add(s);
                    s = fileIn.nextLine();
                }
                objectSources = manageSources(sources);
            }
        }
        catch(Exception e)
        {
            throw new RuntimeException("Failed to generate local map");
        }
        LocalMap newMap = new LocalMap(new int[]{Settings.localMapSizeX, Settings.localMapSizeY},
                gameWorld, new MapIcon(icon, c), name, wind);
        Cell[][] cells = newMap.getCells();

        for (int x = 0; x < Settings.localMapSizeX; x++)
        {
            for (int y = 0; y < Settings.localMapSizeY; y++)
            {
                for (ObjectSource src:objectSources)
                {
                    List<Decoration> decorations = src.spawnDecorations();
                    for (Decoration decor:decorations)
                    {
                        cells[x][y].thingEnters(decor.copy(), Direction.NONE);
                    }
                }
            }
        }
        //newMap.getMyWorld().getLocalMaps().add(newMap);
        return newMap;
    }

    static private List<ObjectSource> manageSources(List<String> sources)
    {
        List<ObjectSource> rootObjects = new ArrayList<>();
        int depth = 0;
        ObjectSource parent = null;
        ObjectSource curr;
        for (String s:sources)
        {
            String[] line = s.split("§");

            if (line[0].length() == 0)
            {
                curr = new ObjectSource(line);
                rootObjects.add(curr);
                parent = curr;
                depth = 0;
                continue;
            }
            if (line[0].length() == depth+1)
            {
                curr = new ObjectSource(parent, line);
                parent.addChild(curr);
                parent = curr;
                depth++;
            }
            else if (line[0].length() == depth)
            {
                parent = parent.getParent();
                curr = new ObjectSource(parent, line);
                parent.addChild(curr);
                parent = curr;
            }
            else if (depth > line[0].length())
            {
                while (depth > line[0].length())
                {
                    parent = parent.getParent();
                    depth--;
                }
                curr = new ObjectSource(parent, line);
                parent.addChild(curr);
                parent = curr;
            }
        }
        return rootObjects;
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
