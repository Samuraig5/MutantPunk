package Main.TimeLogic;

import Main.RenderLogic.Console;
import Main.RenderLogic.Logic.GameState;
import Main.WorldLogic.GameWorld;

import java.util.List;
import java.util.TimerTask;

public class WorldClock extends TimerTask
{
    private Console c;
    private GameWorld activeWorld;
    private boolean isClockRunning = false;

    public WorldClock(Console c)
    {
        this.c = c;
    }

    public void run()
    {
        if (c.getGameState() == GameState.MAIN_MENU)
        {
            c.ls.updateLogoScreen();
        }
        else
        {
            if (!isClockRunning) {return;}
            if (activeWorld == null) {return;}
            if (activeWorld.getActiveLocalMap() == null) {return;}

            activeWorld.getActiveLocalMap().updateTick();

            List<Updatable> updatables = activeWorld.getActiveLocalMap().getLocalUpdatables();
            if (updatables == null) {return;}
            for (int i = 0; i < updatables.size(); i++) {
                updatables.get(i).update();
            }
        }
    }

    public void startClock()
    {
        isClockRunning = true;
    }

    public void stopClock()
    {
        isClockRunning = false;
    }

    public void toggleClock() {isClockRunning = !isClockRunning;}

    public boolean isClockRunning() {return isClockRunning;}

    public GameWorld getActiveWorld() {return activeWorld;}

    public void setActiveWorld(GameWorld newWorld) {activeWorld = newWorld;}
}
