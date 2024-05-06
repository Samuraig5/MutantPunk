package Main.RenderLogic.Menus;

import Main.RenderLogic.Console;
import Main.RenderLogic.Logic.GameState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.List;

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
                c.setGameState(GameState.LOCAL_MAP_VIEW);
                return;
            case KeyEvent.VK_B:
                c.setGameState(GameState.ALL_CHARACTERS_IN_LOCAL_MAP);
                return;
            case KeyEvent.VK_ESCAPE:
                c.setGameState(GameState.WORLD_MENU);
                c.cp.setCursorPosition(new int[] {0,0});
                return;
        }

        List<File> bodyPlans = c.getSortedBodyPlans();
        if (bodyPlans == null) {return;}
        switch (e.getKeyCode()) // All keys that involve spawning people
        {
            case KeyEvent.VK_C:
                c.cb.spawnPerson("0","0", bodyPlans.get(0).getAbsolutePath(), c.wc.getActiveWorld().getActiveLocalMap());
                c.setGameState(GameState.ALL_CHARACTERS_IN_LOCAL_MAP);
                return;
            case KeyEvent.VK_D:
                c.cb.spawnPerson("0","0", bodyPlans.get(1).getAbsolutePath(), c.wc.getActiveWorld().getActiveLocalMap());
                c.setGameState(GameState.ALL_CHARACTERS_IN_LOCAL_MAP);
                return;
            case KeyEvent.VK_E:
                c.cb.spawnPerson("0","0", bodyPlans.get(2).getAbsolutePath(), c.wc.getActiveWorld().getActiveLocalMap());
                c.setGameState(GameState.ALL_CHARACTERS_IN_LOCAL_MAP);
                return;
            case KeyEvent.VK_F:
                c.cb.spawnPerson("0","0", bodyPlans.get(3).getAbsolutePath(), c.wc.getActiveWorld().getActiveLocalMap());
                c.setGameState(GameState.ALL_CHARACTERS_IN_LOCAL_MAP);
                return;
            case KeyEvent.VK_G:
                c.cb.spawnPerson("0","0", bodyPlans.get(4).getAbsolutePath(), c.wc.getActiveWorld().getActiveLocalMap());
                c.setGameState(GameState.ALL_CHARACTERS_IN_LOCAL_MAP);
                return;
            case KeyEvent.VK_H:
                c.cb.spawnPerson("0","0", bodyPlans.get(5).getAbsolutePath(), c.wc.getActiveWorld().getActiveLocalMap());
                c.setGameState(GameState.ALL_CHARACTERS_IN_LOCAL_MAP);
                return;
            case KeyEvent.VK_I:
                c.cb.spawnPerson("0","0", bodyPlans.get(6).getAbsolutePath(), c.wc.getActiveWorld().getActiveLocalMap());
                c.setGameState(GameState.ALL_CHARACTERS_IN_LOCAL_MAP);
                return;
            case KeyEvent.VK_J:
                c.cb.spawnPerson("0","0", bodyPlans.get(7).getAbsolutePath(), c.wc.getActiveWorld().getActiveLocalMap());
                c.setGameState(GameState.ALL_CHARACTERS_IN_LOCAL_MAP);
                return;
            case KeyEvent.VK_K:
                c.cb.spawnPerson("0","0", bodyPlans.get(8).getAbsolutePath(), c.wc.getActiveWorld().getActiveLocalMap());
                c.setGameState(GameState.ALL_CHARACTERS_IN_LOCAL_MAP);
                return;
            case KeyEvent.VK_L:
                c.cb.spawnPerson("0","0", bodyPlans.get(9).getAbsolutePath(), c.wc.getActiveWorld().getActiveLocalMap());
                c.setGameState(GameState.ALL_CHARACTERS_IN_LOCAL_MAP);
                return;
            case KeyEvent.VK_M:
                c.cb.spawnPerson("0","0", bodyPlans.get(10).getAbsolutePath(), c.wc.getActiveWorld().getActiveLocalMap());
                c.setGameState(GameState.ALL_CHARACTERS_IN_LOCAL_MAP);
                return;
            case KeyEvent.VK_N:
                c.cb.spawnPerson("0","0", bodyPlans.get(11).getAbsolutePath(), c.wc.getActiveWorld().getActiveLocalMap());
                c.setGameState(GameState.ALL_CHARACTERS_IN_LOCAL_MAP);
                return;
            case KeyEvent.VK_O:
                c.cb.spawnPerson("0","0", bodyPlans.get(12).getAbsolutePath(), c.wc.getActiveWorld().getActiveLocalMap());
                c.setGameState(GameState.ALL_CHARACTERS_IN_LOCAL_MAP);
                return;
            case KeyEvent.VK_P:
                c.cb.spawnPerson("0","0", bodyPlans.get(13).getAbsolutePath(), c.wc.getActiveWorld().getActiveLocalMap());
                c.setGameState(GameState.ALL_CHARACTERS_IN_LOCAL_MAP);
                return;
            case KeyEvent.VK_Q:
                c.cb.spawnPerson("0","0", bodyPlans.get(14).getAbsolutePath(), c.wc.getActiveWorld().getActiveLocalMap());
                c.setGameState(GameState.ALL_CHARACTERS_IN_LOCAL_MAP);
                return;
            case KeyEvent.VK_R:
                c.cb.spawnPerson("0","0", bodyPlans.get(15).getAbsolutePath(), c.wc.getActiveWorld().getActiveLocalMap());
                c.setGameState(GameState.ALL_CHARACTERS_IN_LOCAL_MAP);
                return;
            case KeyEvent.VK_S:
                c.cb.spawnPerson("0","0", bodyPlans.get(16).getAbsolutePath(), c.wc.getActiveWorld().getActiveLocalMap());
                c.setGameState(GameState.ALL_CHARACTERS_IN_LOCAL_MAP);
                return;
            case KeyEvent.VK_T:
                c.cb.spawnPerson("0","0", bodyPlans.get(17).getAbsolutePath(), c.wc.getActiveWorld().getActiveLocalMap());
                c.setGameState(GameState.ALL_CHARACTERS_IN_LOCAL_MAP);
                return;
            case KeyEvent.VK_U:
                c.cb.spawnPerson("0","0", bodyPlans.get(18).getAbsolutePath(), c.wc.getActiveWorld().getActiveLocalMap());
                c.setGameState(GameState.ALL_CHARACTERS_IN_LOCAL_MAP);
                return;
            case KeyEvent.VK_V:
                c.cb.spawnPerson("0","0", bodyPlans.get(19).getAbsolutePath(), c.wc.getActiveWorld().getActiveLocalMap());
                c.setGameState(GameState.ALL_CHARACTERS_IN_LOCAL_MAP);
                return;
            case KeyEvent.VK_W:
                c.cb.spawnPerson("0","0", bodyPlans.get(20).getAbsolutePath(), c.wc.getActiveWorld().getActiveLocalMap());
                c.setGameState(GameState.ALL_CHARACTERS_IN_LOCAL_MAP);
                return;
            case KeyEvent.VK_X:
                c.cb.spawnPerson("0","0", bodyPlans.get(21).getAbsolutePath(), c.wc.getActiveWorld().getActiveLocalMap());
                c.setGameState(GameState.ALL_CHARACTERS_IN_LOCAL_MAP);
                return;
            case KeyEvent.VK_Y:
                c.cb.spawnPerson("0","0", bodyPlans.get(22).getAbsolutePath(), c.wc.getActiveWorld().getActiveLocalMap());
                c.setGameState(GameState.ALL_CHARACTERS_IN_LOCAL_MAP);
                return;
            case KeyEvent.VK_Z:
                c.cb.spawnPerson("0","0", bodyPlans.get(23).getAbsolutePath(), c.wc.getActiveWorld().getActiveLocalMap());
                c.setGameState(GameState.ALL_CHARACTERS_IN_LOCAL_MAP);
                return;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyReleased(KeyEvent e) {

    }
}
