package Main.RenderLogic.Menus;

import Main.RenderLogic.Console;
import Main.RenderLogic.GameState;
import Main.Settings;
import Main.WorldLogic.GameWorld;
import Main.WorldLogic.LocalMap;
import Main.WorldLogic.MapGenerator;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class WorldMenu implements KeyListener
{
    Console c;
    public WorldMenu(Console console)
    {
        c = console;
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_A:
                int[] size = {Settings.mapSizeX, Settings.mapSizeY};
                GameWorld gw = c.wc.getActiveWorld();
                System.out.println(gw.getLocalMaps().size());

                LocalMap lm = c.cm.GenerateLocalMapWithWalls(size, gw, "LocalMap: " + gw.getLocalMaps().size(), Settings.wallCover);
                System.out.println(gw);
                break;
            case KeyEvent.VK_ESCAPE:
                c.setGameState(GameState.MAIN_MENU);
                break;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyReleased(KeyEvent e) {

    }
}
