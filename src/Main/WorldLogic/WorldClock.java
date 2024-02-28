package Main.WorldLogic;

import Main.ObjectLogic.BodyLogic.Person;
import Main.RenderLogic.Console;

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
        if (!isClockRunning) {return;}
        if (activeWorld == null) {return;}
        List<Person> people = activeWorld.getActiveLocalMap().getLocalPeople();
        if (people == null) {return;}
        for (int i = 0; i < people.size(); i++)
        {
            people.get(i).getMyThoughts().thinkAboutMovement();
        }
        updateScreen();
    }

    private void updateScreen()
    {
        c.cm.RenderLocalMap(activeWorld.getActiveLocalMap());
    }

    public void startClock()
    {
        isClockRunning = true;
    }

    public void stopClock()
    {
        isClockRunning = false;
    }

    public boolean isClockRunning() {return isClockRunning;}

    public GameWorld getActiveWorld() {return activeWorld;}

    public void setActiveWorld(GameWorld newWorld) {activeWorld = newWorld;}
}
