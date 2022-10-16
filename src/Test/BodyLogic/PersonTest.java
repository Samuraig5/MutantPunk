package Test.BodyLogic;

import Main.BodyLogic.Person;
import Main.MathHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    Person p;
    float accuracyDelta = 0.0001f;

    @BeforeEach
    void setUp()
    {
        p = new Person();
        p.changeName("Test Guy");
    }

    @Test
    void addToStat()
    {
        List<float[]> newStatList = new ArrayList<>();

        for (int i = 0; i < 10; i++)
        {
            float gross = MathHelper.randomRange()*10;
            float mod = MathHelper.randomRange();
            float total = gross*mod;
            float upsGrossBP = MathHelper.randomRange()*10;
            float upsModBP = MathHelper.randomRange();
            float upsModPers = MathHelper.randomRange();
            float[] someStat = {gross,mod,total,upsGrossBP,upsModBP,upsModPers};
            newStatList.add(someStat);
        }

        p.AddToStat(newStatList);
        List<float[]> newStatListCopy = newStatList;
        p.AddToStat(newStatListCopy);
        p.AddToStat(newStatList);

        for (int i = 0; i < 10; i++)
        {
            assertEquals(newStatList.get(i)[2],p.GetMyTotalStats()[i][0],accuracyDelta);
            assertEquals(newStatList.get(i)[5]+1,p.GetMyTotalStats()[i][1],accuracyDelta);
            assertEquals(p.GetMyTotalStats()[i][2],p.GetMyTotalStats()[i][0]*p.GetMyTotalStats()[i][1],accuracyDelta);
        }

        List<float[]> newStatList2 = new ArrayList<>();

        for (int i = 0; i < 10; i++)
        {
            float gross = MathHelper.randomRange()*10;
            float mod = MathHelper.randomRange();
            float total = gross*mod;
            float upsGrossBP = MathHelper.randomRange()*10;
            float upsModBP = MathHelper.randomRange();
            float upsModPers = MathHelper.randomRange();
            float[] someStat2 = {gross,mod,total,upsGrossBP,upsModBP,upsModPers};
            newStatList2.add(someStat2);
        }

        p.AddToStat(newStatList2);

        for (int i = 0; i < 10; i++)
        {
            assertEquals(newStatList.get(i)[2]+newStatList2.get(i)[2],p.GetMyTotalStats()[i][0],accuracyDelta);
            assertEquals(newStatList.get(i)[5]+newStatList2.get(i)[5]+1,p.GetMyTotalStats()[i][1],accuracyDelta);
            assertEquals(p.GetMyTotalStats()[i][2],p.GetMyTotalStats()[i][0]*p.GetMyTotalStats()[i][1],accuracyDelta);
        }
    }

    @Test
    public void RemoveFromStat()
    {
        float[][] initialStats = p.GetMyTotalStats();
        List<float[]> newStatList = new ArrayList<>();

        for (int i = 0; i < 10; i++)
        {
            float gross = MathHelper.randomRange()*10;
            float mod = MathHelper.randomRange();
            float total = gross*mod;
            float upsGrossBP = MathHelper.randomRange()*10;
            float upsModBP = MathHelper.randomRange();
            float upsModPers = MathHelper.randomRange();
            float[] someStat = {gross,mod,total,upsGrossBP,upsModBP,upsModPers};
            newStatList.add(someStat);
        }

        p.AddToStat(newStatList);
        p.RemoveFromStat(newStatList);

        for (int i = 0; i < 10; i++)
        {
            assertEquals(initialStats[i][0],p.GetMyTotalStats()[i][0],accuracyDelta);
            assertEquals(initialStats[i][1],p.GetMyTotalStats()[i][1],accuracyDelta);
            assertEquals(initialStats[i][2],p.GetMyTotalStats()[i][2],accuracyDelta);
        }

        List<float[]> newStatList2 = new ArrayList<>();

        for (int i = 0; i < 10; i++)
        {
            float gross = MathHelper.randomRange()*10;
            float mod = MathHelper.randomRange();
            float total = gross*mod;
            float upsGrossBP = MathHelper.randomRange()*10;
            float upsModBP = MathHelper.randomRange();
            float upsModPers = MathHelper.randomRange();
            float[] someStat2 = {gross,mod,total,upsGrossBP,upsModBP,upsModPers};
            newStatList2.add(someStat2);
        }

        p.AddToStat(newStatList);
        p.AddToStat(newStatList);
        p.AddToStat(newStatList2);
        p.RemoveFromStat(newStatList2);
        p.RemoveFromStat(newStatList);

        for (int i = 0; i < 10; i++)
        {
            assertEquals(initialStats[i][0],p.GetMyTotalStats()[i][0],accuracyDelta);
            assertEquals(initialStats[i][1],p.GetMyTotalStats()[i][1],accuracyDelta);
            assertEquals(initialStats[i][2],p.GetMyTotalStats()[i][2],accuracyDelta);
        }
    }
}