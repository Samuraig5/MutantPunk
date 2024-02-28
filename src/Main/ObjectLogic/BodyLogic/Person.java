package Main.ObjectLogic.BodyLogic;

import Main.AILogic.ThinkingThing;
import Main.Direction;
import Main.ErrorHandler;
import Main.MathHelper;
import Main.ObjectLogic.Thing;
import Main.RenderLogic.MapIcon;
import Main.WorldLogic.Cell;
import Main.WorldLogic.GameWorld;
import Main.WorldLogic.LocalMap;

import java.util.ArrayList;
import java.util.List;

public class Person extends Thing
{
    public List<BodyPart> myBodyParts = new ArrayList<>();
    final private List<List<float[]>> myStats = new ArrayList<>();
    /**
     *  Gross, Modifier, Total
     * [0] - Blood Capacity
     * [1] - Blood Generation
     * [2] - Blood Needed
     * [3] - Energy Capacity
     * [4] - Energy Generation
     * [5] - Energy Needed
     * [6] - Size
     * [7] - Speed
     * [8] - Consciousness
     * [9] - Sight
     */
    final private float[][] myTotalStats = new float[10][3];

    private ThinkingThing myThoughts;

    protected Person(boolean addThoughts)
    {
        super();
        if (addThoughts)
        {
            setMyThoughts(new ThinkingThing(this));
        }
        for (int i = 0; i < 10; i++) {
            myStats.add(new ArrayList<>());
            myTotalStats[i] = new float[]{0, 1, 0};
        }

        setRenderPriority(9);
    }

    public void AddToStat(List<float[]> statList)
    {
        for (int i = 0; i < statList.size(); i++) {
            if(!myStats.get(i).contains(statList.get(i)))
            {
                myStats.get(i).add(statList.get(i));
            }
            else
            {
                ErrorHandler.LogData(true, "Already Present");
            }
            myTotalStats[i] = calculateStat(myStats.get(i));
        }
    }
    public void RemoveFromStat(List<float[]> statList)
    {
        for (int i = 0; i < statList.size(); i++) {
            if(myStats.get(i).contains(statList.get(i)))
            {
                myStats.get(i).remove(statList.get(i));
            }
            myTotalStats[i] = calculateStat(myStats.get(i));
        }
    }
    private float[] calculateStat(List<float[]> stat)
    {
        ErrorHandler.LogData(false, "stat length: " + stat.size() + " for " + myBodyParts.size() + " bodyParts.");
        float[] totalStat = new float[]{0, 1, 0, 0, 0, 0};
        for (float[] f: stat)
        {
            ErrorHandler.LogData(false,totalStat[0]+"+"+f[2]+"="+(totalStat[0] + f[2]));
            totalStat[0] += f[2];
            totalStat[1] += f[5];
            totalStat[2] = totalStat[0]*totalStat[1];
        }
        return totalStat;
    }

    public float[][] GetMyTotalStats()
    {
        return myTotalStats;
    }

    public void setGameWorld(GameWorld gw)
    {
        super.setGameWorld(gw);
        super.getGameWord().getAllCharacters().add(this);
    }

    public void move(Direction d)
    {
        Cell c = getMyCell();
        if (c == null) {return;}
        int[] coord = c.getCoordinates();
        int x = coord[0];
        int y = coord[1];
        Cell targetCell;
        switch (d)
        {
            case NORTH:
                targetCell = getLocalMap().getCell( MathHelper.boundedInteger(x,0,getLocalMap().getSize()[0]), MathHelper.boundedInteger(y,-1,getLocalMap().getSize()[1]));
                if (targetCell.isThereAThingWithCollision()) {
                    break;
                }
                setMyCell(targetCell);
                break;
            case NORTH_EAST:
                targetCell = getLocalMap().getCell( MathHelper.boundedInteger(x,+1,getLocalMap().getSize()[0]), MathHelper.boundedInteger(y,-1,getLocalMap().getSize()[1]));
                if (targetCell.isThereAThingWithCollision()) {
                    break;
                }
                setMyCell(targetCell);
                break;
            case EAST:
                targetCell = getLocalMap().getCell( MathHelper.boundedInteger(x,+1,getLocalMap().getSize()[0]), MathHelper.boundedInteger(y,0,getLocalMap().getSize()[1]));
                if (targetCell.isThereAThingWithCollision()) {
                    break;
                }
                setMyCell(targetCell);
                break;
            case SOUTH_EAST:
                targetCell = getLocalMap().getCell( MathHelper.boundedInteger(x,+1,getLocalMap().getSize()[0]), MathHelper.boundedInteger(y,+1,getLocalMap().getSize()[1]));
                if (targetCell.isThereAThingWithCollision()) {
                    break;
                }
                setMyCell(targetCell);
                break;
            case SOUTH:
                targetCell = getLocalMap().getCell( MathHelper.boundedInteger(x,0,getLocalMap().getSize()[0]), MathHelper.boundedInteger(y,+1,getLocalMap().getSize()[1]));
                if (targetCell.isThereAThingWithCollision()) {
                    break;
                }
                setMyCell(targetCell);
                break;
            case SOUTH_WEST:
                targetCell = getLocalMap().getCell( MathHelper.boundedInteger(x,-1,getLocalMap().getSize()[0]), MathHelper.boundedInteger(y,+1,getLocalMap().getSize()[1]));
                if (targetCell.isThereAThingWithCollision()) {
                    break;
                }
                setMyCell(targetCell);
                break;
            case WEST:
                targetCell = getLocalMap().getCell( MathHelper.boundedInteger(x,-1,getLocalMap().getSize()[0]), MathHelper.boundedInteger(y,0,getLocalMap().getSize()[1]));
                if (targetCell.isThereAThingWithCollision()) {
                    break;
                }
                setMyCell(targetCell);
                break;
            case NORTH_WEST:
                targetCell = getLocalMap().getCell( MathHelper.boundedInteger(x,-1,getLocalMap().getSize()[0]), MathHelper.boundedInteger(y,-1,getLocalMap().getSize()[1]));
                if (targetCell.isThereAThingWithCollision()) {
                    break;
                }
                setMyCell(targetCell);
                break;
            case NONE:
                break;
        }
    }

    public ThinkingThing getMyThoughts()
    {
        return myThoughts;
    }

    public void setMyThoughts(ThinkingThing newThoughts)
    {
        myThoughts = newThoughts;
    }

    @Override
    public void newNeightbour(Thing t, Direction directionToSource) {

    }

    @Override
    public void updateTick() {
        myThoughts.thinkAboutMovement();
    }
}