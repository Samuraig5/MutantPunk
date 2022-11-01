package Main.RenderLogic;

import Main.BodyLogic.Person;
import Main.RenderLogic.Menus.LocalMapMenu;
import Main.RenderLogic.Menus.LocalMapView;
import Main.RenderLogic.Menus.WorldMenu;
import Main.WorldLogic.Cell;
import Main.WorldLogic.GameWorld;
import Main.WorldLogic.LocalMap;
import Main.WorldLogic.MapGenerator;

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

    public void RenderLocalMap(LocalMap lm)
    {
        c.cc.fullClear();
        LocalMapView localMapView = new LocalMapView(c, lm);
        c.ckb.setCurrentMenu(localMapView);

        int[] xy = lm.getSize();
        char[][] symbols = TranslateCellsToSymbols(lm.getCells(),xy);
        for (int y = 0; y < xy[1]; y++)
        {
            String line = "";
            for (int x = 0; x < xy[0]; x++)
            {
                line = line + symbols[x][y] + " ";
            }
            c.cc.println(line);
        }
    }

    private char[][] TranslateCellsToSymbols(Cell[][] cells, int[] size)
    {
        char[][] symbols = new char[size[0]][size[1]];
        for (int x = 0; x < size[0]; x++)
        {
            for (int y = 0; y < size[1]; y++)
            {
                if (cells[x][y].isEmpty())
                {
                    symbols[x][y] = '.';
                }
                else if (cells[x][y].getPeople().size() < 0)
                {
                    symbols[x][y] = '@';
                }
                else
                {
                    symbols[y][y] = '#';
                }
            }
        }
        return symbols;
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
