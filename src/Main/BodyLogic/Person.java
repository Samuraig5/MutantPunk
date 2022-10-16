package Main.BodyLogic;

import java.util.ArrayList;
import java.util.List;

public class Person
{
    public List<BodyPart> myBodyParts = new ArrayList<>();
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
            myStats.add(new ArrayList<float[]>());
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
            myTotalStats[i] = new float[]{0, 1, 0, 0, 0, 0};
            for (float[] f: myStats.get(i))
            {
                myTotalStats[i][0] += f[2];
                myTotalStats[i][1] += f[5];
                myTotalStats[i][2] = myTotalStats[i][0]*myTotalStats[i][1];
            }
        }
    }

    public float[][] GetMyTotalStats()
    {
        return myTotalStats;
    }

    public void changeName(String newName)
    {
        name = newName;
    }
}