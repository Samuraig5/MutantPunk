package Main.RenderLogic.Menus;

import Main.RenderLogic.Console;
import Main.RenderLogic.GameState;
import Main.WorldLogic.GameWorld;
import Main.WorldLogic.MapGenerator;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BodyPartMenu implements KeyListener
{
    Console c;
    public BodyPartMenu(Console console)
    {
        c = console;
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_ESCAPE:
                c.setGameState(GameState.PERSON_VIEW);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyReleased(KeyEvent e) {

    }
}
