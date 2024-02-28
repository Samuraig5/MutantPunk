package Main.RenderLogic;

import Main.RenderLogic.Menus.LocalMapMenu;
import Main.RenderLogic.Menus.LocalMapView;
import Main.RenderLogic.Menus.WorldMenu;
import Main.WorldLogic.Cell;
import Main.WorldLogic.GameWorld;
import Main.WorldLogic.LocalMap;
import Main.WorldLogic.MapGenerator;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ConsoleMapInterface
{
    Console c;
    public ConsoleMapInterface(Console console)
    {
        c = console;
    }

    public LocalMap GenerateEmptyLocalMap(int[] size, GameWorld gameWorld, String name)
    {
        return MapGenerator.generateEmptyLocalMap(size, gameWorld, name);
    }
    public LocalMap GenerateLocalMapWithWalls(int[] size, GameWorld gameWorld, String name, float wallCover)
    {
        return MapGenerator.generateLocalMapWithWalls(size,gameWorld,name,wallCover);
    }

    public void RenderLocalMap(LocalMap lm)
    {
        lm.getMyWorld().setActiveLocalMap(lm);

        c.cc.fullClear();
        LocalMapView localMapView = new LocalMapView(c, lm);
        c.ckb.setCurrentMenu(localMapView);

        int[] xy = lm.getSize();
        MapIcon[][] mapIcons = TranslateCellsToSymbols(lm.getCells(),xy);

        List<String> stringList = new ArrayList<>();
        List<Color> colorList = new ArrayList<>();

        for (int y = 0; y < xy[1]; y++)
        {
            for (int x = 0; x < xy[0]; x++)
            {
                MapIcon mi = mapIcons[x][y];
                String s = String.valueOf(mi.getSymbol());
                s += " ";
                Color c =  mi.getIconColour();

                stringList.add(s);
                colorList.add(c);
            }
            stringList.add("\n");
            colorList.add(Color.lightGray);
        }
        c.cc.printStrings(stringList,false,colorList);
    }

    private MapIcon[][] TranslateCellsToSymbols(Cell[][] cells, int[] size)
    {
        MapIcon[][] mapIcons = new MapIcon[size[0]][size[1]];
        for (int x = 0; x < size[0]; x++)
        {
            for (int y = 0; y < size[1]; y++)
            {
                if (cells[x][y].isEmpty()) //Empty Cell
                {
                    mapIcons[x][y] = new MapIcon('.');
                }
                else if (cells[x][y].getThings().size() > 0)
                {
                    mapIcons[x][y] = cells[x][y].getThings().get(0).getMapIcon();
                }
                else
                {
                    mapIcons[y][y] = new MapIcon();
                }
            }
        }
        return mapIcons;
    }

    public void openWorldMenu(GameWorld gw)
    {
        List<String> mainMenuOptions = new ArrayList<>();
        mainMenuOptions.add("Generate a 10x10 local map \n\nCurrent Local Maps");

        List<LocalMap> localMaps = gw.getLocalMaps();
        for (int i = 0; i < localMaps.size(); i++)
        {
            mainMenuOptions.add("Local Map " + i);
        }
        c.clir.renderList(mainMenuOptions, gw.getWorldName(), new WorldMenu(c, gw));
    }

    public void openLocalMapMenu(LocalMap lm)
    {
        List<String> mainMenuOptions = new ArrayList<>();
        mainMenuOptions.add("Open Map View");
        mainMenuOptions.add("List all local character\n\nSpawn character");
        mainMenuOptions.add("Spawn a Human");
        mainMenuOptions.add("Spawn 10 Humans");
        mainMenuOptions.add("Spawn 100 Humans");
        mainMenuOptions.add("Spawn a Minor Mutant");
        mainMenuOptions.add("Spawn a Human Spider");
        mainMenuOptions.add("Spawn a Slime");
        c.clir.renderList(mainMenuOptions, lm.getMapName(), new LocalMapMenu(c, lm));
    }
}
