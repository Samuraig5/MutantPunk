package Main.RenderLogic.Map_Painter;

import Main.RenderLogic.ConsolePainter;
import Main.RenderLogic.Logic.MapIcon;
import Main.RenderLogic.UI_Painter.UIPainter;
import Main.Settings;
import Main.WorldLogic.GameWorld;
import Main.WorldLogic.LocalMap;

import java.awt.*;

public class MapPainter
{
    public static int printThing(ConsolePainter cp, int xPos, int yPos, MapIcon mi)
    {
        if (mi.hasSprite() && Settings.renderSprites)
        {
            return printSprite(cp, xPos, yPos, mi.getSprite());
        }
        else
        {
            return printString(cp.g, Math.round(xPos+Settings.gridScale*(0.1f)), Math.round(yPos+Settings.gridScale*0.6f), mi.getIconColour(),mi.getSymbol()+"");
        }
    }

    private static int printSprite(ConsolePainter cp, int xPos, int yPos, Image image)
    {
        cp.g.drawImage(image, xPos, yPos, Settings.gridScale, Settings.gridScale, cp);
        return yPos + image.getWidth(cp);
    }

    private static int printString(Graphics g, int xPos, int yPos, Color c, String s)
    {
        Color current = g.getColor();
        g.setColor(c);
        g.drawString(s, xPos, yPos);
        g.setColor(current);
        return xPos + Math.round(s.length()*Settings.gridScale);
    }

    public static void drawWorldView(ConsolePainter cp)
    {
        GameWorld gw = cp.c.wc.getActiveWorld();

        MapIcon[][] mapIcons = new MapIcon[Settings.worldMapSizeX][Settings.worldMapSizeY];
        for (int x = 0; x < Settings.worldMapSizeX; x++)
        {
            for (int y = 0; y < Settings.worldMapSizeY; y++)
            {
                mapIcons[x][y] = gw.getLocalMaps()[x][y].getMapIcon();
            }
        }

        for (int y = 0; y < Settings.worldMapSizeY; y++)
        {
            for (int x = 0; x < Settings.worldMapSizeX; x++)
            {
                int xBase = Math.round(x*Settings.gridScale);
                int yBase = Math.round((y+1)*Settings.gridScale);

                int xCursorOffset = Math.round(cp.getCursorPosition()[0]*Settings.gridScale);
                int yCursorOffset = Math.round(cp.getCursorPosition()[1]*Settings.gridScale);

                int xScreenCenter = Math.round((float) Settings.windowWidth /2);
                int yScreenCenter = Math.round((float) Settings.windowHeight /2);

                int xPos = xBase-xCursorOffset+xScreenCenter;
                int yPos = yBase-yCursorOffset+yScreenCenter;

                if (cp.isCursorEnabled() && cp.getFocusedThing() == null && cp.getCursorPosition()[0] == x && cp.getCursorPosition()[1] == y)
                {
                    MapIcon mi = new MapIcon('X', Color.yellow);
                    printThing(cp, xPos, yPos, mi);
                }
                else
                {
                    MapIcon mi = mapIcons[x][y];
                    printThing(cp, xPos, yPos, mi);
                }
            }
        }
    }

    public static void drawLocalMapView(ConsolePainter cp)
    {
        LocalMap lm = cp.c.wc.getActiveWorld().getActiveLocalMap();

        int[] xy = lm.getSize();
        MapIcon[][] mapIcons = cp.c.cm.TranslateCellsToSymbols(lm.getCells(),xy);

        for (int y = 0; y < xy[1]; y++)
        {
            for (int x = 0; x < xy[0]; x++)
            {
                int xBase = Math.round(x* Settings.gridScale);
                int yBase = Math.round((y+1)*Settings.gridScale);

                int xCursorOffset = Math.round(cp.getCursorPosition()[0]*Settings.gridScale);
                int yCursorOffset = Math.round(cp.getCursorPosition()[1]*Settings.gridScale);

                int xScreenCenter = Math.round((float) Settings.windowWidth /2);
                int yScreenCenter = Math.round((float) Settings.windowHeight /2);

                int xPos = xBase-xCursorOffset+xScreenCenter;
                int yPos = yBase-yCursorOffset+yScreenCenter;

                if (cp.isCursorEnabled() && cp.getFocusedThing() == null && cp.getCursorPosition()[0] == x && cp.getCursorPosition()[1] == y)
                {
                    MapIcon mi = new MapIcon('X', Color.yellow);
                    printThing(cp, xPos, yPos, mi);
                }
                else
                {
                    MapIcon mi = mapIcons[x][y];
                    printThing(cp, xPos, yPos, mi);
                }
            }
        }
        UIPainter.drawCursorUI(cp);
        UIPainter.drawPausedButton(cp);
    }
}
