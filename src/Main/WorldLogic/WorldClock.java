package Main.WorldLogic;

import Main.ObjectLogic.Thing;
import Main.RenderLogic.Console;
import Main.RenderLogic.GameState;

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

            List<Thing> things = activeWorld.getActiveLocalMap().getLocalThings();
            if (things == null) {return;}
            for (int i = 0; i < things.size(); i++) {
                things.get(i).updateTick();
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
