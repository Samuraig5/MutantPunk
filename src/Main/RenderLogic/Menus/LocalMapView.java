package Main.RenderLogic.Menus;

import Main.Direction;
import Main.RenderLogic.Console;
import Main.RenderLogic.GameState;
import Main.Settings;
import Main.WorldLogic.GameWorld;
import Main.WorldLogic.LocalMap;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LocalMapView implements KeyListener
{
    Console c;
    public LocalMapView(Console console)
    {
        c = console;
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_W:
                c.cp.setCursorEnabled(true);
                c.cp.moveCursor(Direction.NORTH, 1);
                break;
            case KeyEvent.VK_A:
                c.cp.setCursorEnabled(true);
                c.cp.moveCursor(Direction.WEST, 1);
                break;
            case KeyEvent.VK_S:
                c.cp.setCursorEnabled(true);
                c.cp.moveCursor(Direction.SOUTH, 1);
                break;
            case KeyEvent.VK_D:
                c.cp.setCursorEnabled(true);
                c.cp.moveCursor(Direction.EAST, 1);
                break;
            case KeyEvent.VK_ESCAPE:
                if (c.cp.isCursorEnabled())
                {
                    c.cp.setCursorEnabled(false);
                }
                else
                {
                    c.setGameState(GameState.LOCAL_MAP_MENU);
                }
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
