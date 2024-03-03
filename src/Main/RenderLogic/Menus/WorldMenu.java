package Main.RenderLogic.Menus;

import Main.RenderLogic.Console;
import Main.RenderLogic.GameState;
import Main.WorldLogic.GameWorld;
import Main.WorldLogic.MapGenerator;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class WorldMenu implements KeyListener
{
    Console c;
    GameWorld gw;
    public WorldMenu(Console console)
    {
        c = console;
        gw = c.wc.getActiveWorld();
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_ESCAPE:
                c.setGameState(GameState.MAIN_MENU);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyReleased(KeyEvent e) {

    }
}
