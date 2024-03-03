package Main.RenderLogic.Menus;

import Main.RenderLogic.Console;
import Main.RenderLogic.GameState;
import Main.Settings;
import Main.WorldLogic.GameWorld;
import Main.WorldLogic.LocalMap;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LocalMapMenu implements KeyListener
{
    Console c;
    public LocalMapMenu(Console console)
    {
        c = console;
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_A:
                break;
            case KeyEvent.VK_ESCAPE:
                c.setGameState(GameState.WORLD_MENU);
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
