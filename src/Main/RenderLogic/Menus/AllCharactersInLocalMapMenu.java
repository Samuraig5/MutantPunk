package Main.RenderLogic.Menus;

import Main.ObjectLogic.BodyLogic.Person;
import Main.RenderLogic.Console;
import Main.RenderLogic.GameState;
import Main.Settings;
import Main.WorldLogic.GameWorld;
import Main.WorldLogic.LocalMap;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AllCharactersInLocalMapMenu implements KeyListener
{
    Console c;

    int currentPage = 0;

    public AllCharactersInLocalMapMenu(Console console)
    {
        c = console;
    }

    private void openPersonView(int i)
    {
        LocalMap lm = c.wc.getActiveWorld().getActiveLocalMap();

        Person p = lm.getLocalPeople().get(i);

        c.cp.setFocusedPerson(p);
        c.setGameState(GameState.PERSON_VIEW);
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_A:
                openPersonView(0);
                break;
            case KeyEvent.VK_B:
                openPersonView(1);
                break;
            case KeyEvent.VK_C:
                openPersonView(2);
                break;
            case KeyEvent.VK_D:
                openPersonView(3);
                break;
            case KeyEvent.VK_E:
                openPersonView(4);
                break;
            case KeyEvent.VK_F:
                openPersonView(5);
                break;
            case KeyEvent.VK_G:
                openPersonView(6);
                break;
            case KeyEvent.VK_H:
                openPersonView(7);
                break;
            case KeyEvent.VK_I:
                openPersonView(8);
                break;
            case KeyEvent.VK_J:
                openPersonView(9);
                break;
            case KeyEvent.VK_K:
                openPersonView(10);
                break;
            case KeyEvent.VK_L:
                openPersonView(11);
                break;
            case KeyEvent.VK_M:
                openPersonView(12);
                break;
            case KeyEvent.VK_N:
                openPersonView(13);
                break;
            case KeyEvent.VK_O:
                openPersonView(14);
                break;
            case KeyEvent.VK_P:
                openPersonView(15);
                break;
            case KeyEvent.VK_Q:
                openPersonView(16);
                break;
            case KeyEvent.VK_R:
                openPersonView(17);
                break;
            case KeyEvent.VK_S:
                openPersonView(18);
                break;
            case KeyEvent.VK_T:
                openPersonView(19);
                break;
            case KeyEvent.VK_U:
                openPersonView(20);
                break;
            case KeyEvent.VK_V:
                openPersonView(21);
                break;
            case KeyEvent.VK_W:
                openPersonView(22);
                break;
            case KeyEvent.VK_X:
                openPersonView(23);
                break;
            case KeyEvent.VK_Y:
                openPersonView(24);
                break;
            case KeyEvent.VK_Z:
                openPersonView(25);
                break;
            case KeyEvent.VK_ESCAPE:
                c.setGameState(GameState.LOCAL_MAP_MENU);
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
