package Main.BodyLogic;

import Main.ErrorHandler;
import Main.WorldLogic.Cell;
import Main.WorldLogic.GameWorld;
import Main.WorldLogic.LocalMap;

import java.util.ArrayList;
import java.util.List;

public class Person
{
    public List<BodyPart> myBodyParts = new ArrayList<>();
    private GameWorld gw;
    private LocalMap lm;
    private Cell myCell;
    public String name;
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
    final private List<List<float[]>> myStats = new ArrayList<>();
    final private float[][] myTotalStats = new float[10][3];

    public Person()
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
    public void setGameWorld(GameWorld gw) {
        this.gw = gw;
    }

    public LocalMap getLocalMap() {
        return lm;
    }

    public void setLocalMap(LocalMap lm) {
        this.lm = lm;
    }

    public Cell getMyCell() {
        return myCell;
    }

    public void setMyCell(Cell myCell) {
        this.myCell = myCell;
    }
}