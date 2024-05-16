package Main.RenderLogic.Menus;

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
        /*
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_A:
                if (currentPage != 0)
                {
                    openLocalMapView(0);
                    break;
                }
                int[] size = {Settings.localMapSizeX, Settings.localMapSizeY};
                GameWorld gw = c.wc.getActiveWorld();

                //LocalMap lm = c.cm.GenerateLocalMap(size, gw, "LocalMap: " + gw.getLocalMaps().size(), Settings.wallCover);
                //c.wc.getActiveWorld().setActiveLocalMap(lm);

                c.setGameState(GameState.LOCAL_MAP_MENU);
                break;
            case KeyEvent.VK_B:
                openLocalMapView(0);
                break;
            case KeyEvent.VK_C:
                openLocalMapView(1);
                break;
            case KeyEvent.VK_D:
                openLocalMapView(2);
                break;
            case KeyEvent.VK_E:
                openLocalMapView(3);
                break;
            case KeyEvent.VK_F:
                openLocalMapView(4);
                break;
            case KeyEvent.VK_G:
                openLocalMapView(5);
                break;
            case KeyEvent.VK_H:
                openLocalMapView(6);
                break;
            case KeyEvent.VK_I:
                openLocalMapView(7);
                break;
            case KeyEvent.VK_J:
                openLocalMapView(8);
                break;
            case KeyEvent.VK_K:
                openLocalMapView(9);
                break;
            case KeyEvent.VK_L:
                openLocalMapView(10);
                break;
            case KeyEvent.VK_M:
                openLocalMapView(11);
                break;
            case KeyEvent.VK_N:
                openLocalMapView(12);
                break;
            case KeyEvent.VK_O:
                openLocalMapView(13);
                break;
            case KeyEvent.VK_P:
                openLocalMapView(14);
                break;
            case KeyEvent.VK_Q:
                openLocalMapView(15);
                break;
            case KeyEvent.VK_R:
                openLocalMapView(16);
                break;
            case KeyEvent.VK_S:
                openLocalMapView(17);
                break;
            case KeyEvent.VK_T:
                openLocalMapView(18);
                break;
            case KeyEvent.VK_U:
                openLocalMapView(19);
                break;
            case KeyEvent.VK_V:
                openLocalMapView(20);
                break;
            case KeyEvent.VK_W:
                openLocalMapView(21);
                break;
            case KeyEvent.VK_X:
                openLocalMapView(22);
                break;
            case KeyEvent.VK_Y:
                openLocalMapView(23);
                break;
            case KeyEvent.VK_Z:
                openLocalMapView(24);
                break;
            case KeyEvent.VK_ESCAPE:
                c.setGameState(GameState.MAIN_MENU);
                break;
        }
         */

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
        c.bh.mouseClickInWorldMapView(c.cp.getMousePosition());
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
