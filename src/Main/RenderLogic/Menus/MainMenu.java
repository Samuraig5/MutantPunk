package Main.RenderLogic.Menus;

import Main.RenderLogic.Console;
import Main.RenderLogic.Logic.GameState;
import Main.WorldLogic.GameWorld;
import Main.WorldLogic.MapGenerator;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainMenu implements KeyListener
{
    Console c;
    public MainMenu(Console console)
    {
        c = console;
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_A:
                GameWorld gw = MapGenerator.generateNewGameWorld("New World");
                c.wc.setActiveWorld(gw);
                c.setGameState(GameState.WORLD_MENU);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyReleased(KeyEvent e) {

    }
}
