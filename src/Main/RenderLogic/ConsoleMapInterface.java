package Main.RenderLogic;

import Main.RenderLogic.Menus.LocalMapMenu;
import Main.WorldLogic.Cell;
import Main.WorldLogic.GameWorld;
import Main.WorldLogic.LocalMap;
import Main.WorldLogic.MapGenerator;

public class ConsoleMapInterface
{
    Console c;
    public ConsoleMapInterface(Console console)
    {
        c = console;
    }

    public LocalMap GenerateEmptyLocalMap(int[] size, GameWorld gameWorld)
    {
        return MapGenerator.generateEmptyLocalMap(size, gameWorld);
    }

    public void RenderLocalMap(LocalMap lm)
    {
        c.cc.fullClear();
        LocalMapMenu localMapMenu = new LocalMapMenu(c, lm.getMyWorld());
        c.ckb.setCurrentMenu(localMapMenu);

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
                else
                {
                    symbols[y][y] = '#';
                }
            }
        }
        return symbols;
    }
}
