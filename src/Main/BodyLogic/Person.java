package Main.BodyLogic;

import Main.ErrorHandler;
import Main.RenderLogic.MapIcon;
import Main.WorldLogic.Cell;
import Main.WorldLogic.GameWorld;
import Main.WorldLogic.LocalMap;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Person
{
    public List<BodyPart> myBodyParts = new ArrayList<>();
    private GameWorld gw;
    private LocalMap lm;
    private Cell myCell;
    private MapIcon mapIcon;

    public String name;
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

    protected Person()
    {
        for (int i = 0; i < 10; i++) {
            myStats.add(new ArrayList<>());
            myTotalStats[i] = new float[]{0, 1, 0};
        }
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

    public void changeName(String newName)
    {
        name = newName;
    }

    public GameWorld getGameWord() {
        return gw;
    }
    public void setGameWorld(GameWorld gw)
    {
        this.gw = gw;
        this.gw.getAllCharacters().add(this);
    }

    public LocalMap getLocalMap() {
        return lm;
    }

    public void setLocalMap(LocalMap lm)
    {
        if (this.lm != null)
        {
            this.lm.getLocalPersons().remove(this);
        }
        this.lm = lm;
        this.lm.addPersonToLocalMap(this);
    }

    public Cell getMyCell() {
        return myCell;
    }

    public void setMyCell(Cell myCell) {
        if (this.myCell != null)
        {
            this.myCell.getPeople().remove(this);
        }
        this.myCell = myCell;
        this.myCell.getPeople().add(this);
    }

    public MapIcon getMapIcon() {return mapIcon;}

    public void setMapIcon(MapIcon mi)
    {
        this.mapIcon = mi;
    }
}