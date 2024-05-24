package Main.MenuLogic;

import Main.ObjectLogic.BodyLogic.BodyPart;
import Main.ObjectLogic.BodyLogic.Person;
import Main.RenderLogic.Console;
import Main.RenderLogic.Logic.GameState;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class ThingInspector implements MenuLogic
{
    Console c;

    int currentPage = 0;

    public ThingInspector(Console console)
    {
        c = console;
    }

    private void openBodyPartView(int i)
    {
        Person p = (Person) c.cp.getInspectedThing();
        BodyPart bp = p.myBodyParts.get(i);
        c.cp.setInspectedBodyPart(bp);
        c.setGameState(GameState.BODY_PART_MENU);
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_A:
                openBodyPartView(0);
                break;
            case KeyEvent.VK_B:
                openBodyPartView(1);
                break;
            case KeyEvent.VK_C:
                openBodyPartView(2);
                break;
            case KeyEvent.VK_D:
                openBodyPartView(3);
                break;
            case KeyEvent.VK_E:
                openBodyPartView(4);
                break;
            case KeyEvent.VK_F:
                openBodyPartView(5);
                break;
            case KeyEvent.VK_G:
                openBodyPartView(6);
                break;
            case KeyEvent.VK_H:
                openBodyPartView(7);
                break;
            case KeyEvent.VK_I:
                openBodyPartView(8);
                break;
            case KeyEvent.VK_J:
                openBodyPartView(9);
                break;
            case KeyEvent.VK_K:
                openBodyPartView(10);
                break;
            case KeyEvent.VK_L:
                openBodyPartView(11);
                break;
            case KeyEvent.VK_M:
                openBodyPartView(12);
                break;
            case KeyEvent.VK_N:
                openBodyPartView(13);
                break;
            case KeyEvent.VK_O:
                openBodyPartView(14);
                break;
            case KeyEvent.VK_P:
                openBodyPartView(15);
                break;
            case KeyEvent.VK_Q:
                openBodyPartView(16);
                break;
            case KeyEvent.VK_R:
                openBodyPartView(17);
                break;
            case KeyEvent.VK_S:
                openBodyPartView(18);
                break;
            case KeyEvent.VK_T:
                openBodyPartView(19);
                break;
            case KeyEvent.VK_U:
                openBodyPartView(20);
                break;
            case KeyEvent.VK_V:
                openBodyPartView(21);
                break;
            case KeyEvent.VK_W:
                openBodyPartView(22);
                break;
            case KeyEvent.VK_X:
                openBodyPartView(23);
                break;
            case KeyEvent.VK_Y:
                openBodyPartView(24);
                break;
            case KeyEvent.VK_Z:
                openBodyPartView(25);
                break;
            case KeyEvent.VK_ESCAPE:
                if (c.getPreviousGameState() == GameState.LOCAL_MAP_MENU)
                {
                    c.setGameState(GameState.LOCAL_MAP_MENU);
                }
                else if (c.getPreviousGameState() == GameState.LOCAL_MAP_VIEW)
                {
                    c.setGameState(GameState.LOCAL_MAP_VIEW);
                }
                break;
            case KeyEvent.VK_ENTER:
                if (c.cp.getInspectedThing() instanceof  Person)
                {
                    c.cp.setPlayerCharacter((Person)c.cp.getInspectedThing());
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

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
