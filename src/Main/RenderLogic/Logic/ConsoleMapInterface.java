package Main.RenderLogic.Logic;

import Main.RenderLogic.Console;
import Main.WorldLogic.Cell;

public class ConsoleMapInterface
{
    Console c;
    public ConsoleMapInterface(Console console)
    {
        c = console;
    }

    public MapIcon[][] TranslateCellsToSymbols(Cell[][] cells, int[] size)
    {
        MapIcon[][] mapIcons = new MapIcon[size[0]][size[1]];
        for (int x = 0; x < size[0]; x++)
        {
            for (int y = 0; y < size[1]; y++)
            {
                if (cells[x][y].isEmpty()) //Empty Cell
                {
                    mapIcons[x][y] = new MapIcon(' ');
                }
                else if (!cells[x][y].getThings().isEmpty())
                {
                    mapIcons[x][y] = cells[x][y].getThingWithHighestRenderPriority().getMapIcon();
                }
                else
                {
                    mapIcons[y][y] = new MapIcon();
                }
            }
        }
        return mapIcons;
    }
}
