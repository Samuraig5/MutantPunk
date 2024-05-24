package Main.MenuLogic;

import Main.Direction;
import Main.RenderLogic.Console;
import Main.RenderLogic.Logic.GameState;
import Main.WorldLogic.LocalMap;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class WorldMenu implements MenuLogic
{
    Console c;

    public WorldMenu(Console console)
    {
        c = console;
    }

    private void openLocalMapView(int x, int y)
    {
        LocalMap lm = c.wc.getActiveWorld().getLocalMaps()[x][y];
        c.wc.getActiveWorld().setActiveLocalMap(lm);
        c.setGameState(GameState.LOCAL_MAP_MENU);
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
            case KeyEvent.VK_ENTER:
                c.wc.getActiveWorld().setActiveLocalMap(c.wc.getActiveWorld().getLocalMaps()[c.cp.getCursorPosition()[0]][c.cp.getCursorPosition()[1]]);
                c.setGameState(GameState.LOCAL_MAP_MENU);
                c.cp.setCursorPosition(new int[] {0,0});
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
        c.bh.mouseClick(c.cp.getMousePosition());
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
