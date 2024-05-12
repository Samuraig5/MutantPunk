package Main.RenderLogic.Menus;

import Main.Direction;
import Main.RenderLogic.Console;
import Main.RenderLogic.Logic.GameState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LocalMapView implements KeyListener, MouseListener
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
                c.cp.setFocusedThing(null);
                break;
            case KeyEvent.VK_A:
                c.cp.setCursorEnabled(true);
                c.cp.moveCursor(Direction.WEST, 1);
                c.cp.setFocusedThing(null);
                break;
            case KeyEvent.VK_S:
                c.cp.setCursorEnabled(true);
                c.cp.moveCursor(Direction.SOUTH, 1);
                c.cp.setFocusedThing(null);
                break;
            case KeyEvent.VK_D:
                c.cp.setCursorEnabled(true);
                c.cp.moveCursor(Direction.EAST, 1);
                c.cp.setFocusedThing(null);
                break;
            case KeyEvent.VK_F:
                if (c.cp.isCursorEnabled())
                {
                    int[] xy = c.cp.getCursorPosition();
                    c.cp.setFocusedThing(
                            c.wc.getActiveWorld().getActiveLocalMap().
                                    getCell(xy[0],xy[1]).getThings().
                                    get(c.cp.getListSelector()));
                }
                break;
            case KeyEvent.VK_UP:
                c.cp.setListSelector(c.cp.getListSelector()+1);
                break;
            case KeyEvent.VK_DOWN:
                c.cp.setListSelector(c.cp.getListSelector()-1);
                break;
            case KeyEvent.VK_ENTER:
                if (c.cp.isCursorEnabled())
                {
                    int[] xy = c.cp.getCursorPosition();
                    if (c.cp.getFocusedThing() != null)
                    {
                        c.cp.setInspectedThing(c.cp.getFocusedThing());
                    }
                    else
                    {
                        c.cp.setInspectedThing(
                                c.wc.getActiveWorld().getActiveLocalMap().
                                        getCell(xy[0],xy[1]).getThings().
                                        get(c.cp.getListSelector()));
                    }
                    c.setGameState(GameState.THING_INSPECTOR);
                }
                break;
            case KeyEvent.VK_SPACE:
                c.wc.toggleClock();
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

    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        //int[] xy = c.cp.getMouseCellCoordsOnLocalMap();
        c.cp.setCursorPosition(c.cp.getMouseCellCoordsOnLocalMap());
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
