package Main.TimeLogic;

import Main.MathHelper;
import Main.Settings;

public abstract class Updatable
{
    private int maxActionPoints = 1000;
    private int actionPoints;

    public void setActionPoints(int newVal)
    {
        actionPoints = newVal;
        actionPoints = MathHelper.clamp(actionPoints, 0, maxActionPoints);
    }
    public int getActionPoints() {return actionPoints;}
    public void changeActionPoints(int change) {actionPoints += change;}

    /**
     * Will return true if the current amount of action points is greater equal to the amount given.
     */
    public boolean canAffordAction(int amount)
    {
        return amount >= getActionPoints();
    }
    public void update()
    {
        actionPoints += Settings.actionPointsPerTick;
    }
}
