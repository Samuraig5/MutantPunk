package Test.BodyLogic;

import Main.BodyLogic.BodyFileDecoder;
import Main.BodyLogic.BodyPart;
import Main.BodyLogic.Person;
import Main.ErrorHandler;
import Main.MathHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BodyPartTest {
    BodyPart bp;
    Person p;
    float accuracyDelta = 0.0001f;

    @BeforeEach
    void setUp()
    {
        p = new Person();
        p.changeName("Test Dummy");
        bp = BodyFileDecoder.loadBodyPartFromFile("resources/BodyParts/Containers/HumanTorso",0,0);
        p.myBodyParts.add(bp);
        bp.setMyPerson(p);
        ErrorHandler.LogData(true,"Person: " + bp.getMyPerson().name);
    }

    @org.junit.jupiter.api.Test
    void generateBodyPart() {
    }

    @org.junit.jupiter.api.Test
    void doDamage() {
        float damageAmount = 10;
        float initialHealth = bp.getCurrentHealth();
        bp.doDamage(damageAmount);
        assertEquals(initialHealth-damageAmount,bp.getCurrentHealth());
    }

    @Test
    void regenerateAtMaxHealth()
    {
        bp.regenerateDamage();
        assertEquals(bp.GetMyTotalStats()[6][2], bp.getCurrentHealth());
    }
    @Test
    void regenerateWhenNotAtMaxHealth()
    {
        float[][] f = new float[16][6];
        f[6] = new float[]{0, 0, 0, 100, 0, 0};
        bp.AddToStat(f);//Increasing the max health doesn't raise current health so this simulates healing.
        float initialHealth = bp.getCurrentHealth();
        for(int i = 0; i < (bp.GetMyTotalStats()[6][2] - initialHealth); i++)
        {
            bp.regenerateDamage();
        }
        assertEquals(bp.GetMyTotalStats()[6][2],bp.getCurrentHealth());
    }
    @Test
    void regenerateWhenBelowRegenLimit()
    {
        float[][] f = new float[16][6];
        f[8][3] = -bp.GetMyTotalStats()[8][0];
        bp.AddToStat(f); //Set RegenLimit to zero
        f[8][3] = bp.GetMyTotalStats()[6][0];
        bp.AddToStat(f); //Set RegenLimit to max health

        bp.doDamage(1);
        float initialHealth = bp.getCurrentHealth();
        bp.regenerateDamage();
        assertEquals(initialHealth,bp.getCurrentHealth());
    }
    @Test
    void regenerateWhenNoRegenRate()
    {
        float[][] f = new float[16][6];
        f[7][3] = -bp.GetMyTotalStats()[7][0];
        bp.AddToStat(f); //Set RegenRate to zero

        bp.doDamage(1);
        float initialHealth = bp.getCurrentHealth();
        bp.regenerateDamage();
        assertEquals(initialHealth,bp.getCurrentHealth());
    }
    @org.junit.jupiter.api.Test
    void regenerateDamage() {
        regenerateAtMaxHealth();
        regenerateWhenNotAtMaxHealth();
        regenerateWhenBelowRegenLimit();
        regenerateWhenNoRegenRate();
    }

    @org.junit.jupiter.api.Test
    void attachTo() {
    }

    @org.junit.jupiter.api.Test
    void removeBodyPart() {
    }

    @org.junit.jupiter.api.Test
    void updatePersonWhenRemoved() {
    }

    @org.junit.jupiter.api.Test
    void changeName() {
        bp.changeName("NewName");
        assertEquals("NewName",bp.getName());
    }

    @org.junit.jupiter.api.Test
    void getAttachedBodyParts() {
    }

    @org.junit.jupiter.api.Test
    void setMyPerson() {
        Person p = new Person();
        bp.setMyPerson(p);
        assertEquals(p, bp.getMyPerson());
    }

    @Test
    void AddToStatLocal()
    {
        float[][] initialStats = bp.GetMyTotalStats().clone();
        float[][] newStatList = new float[16][6];
        for (int i = 0; i < 16; i++)
        {
            float gross = Math.round(MathHelper.randomRange()*10);
            float mod = MathHelper.randomRange();
            float total = gross*mod;
            float upsGrossBP = MathHelper.randomRange()*10;
            float upsModBP = MathHelper.randomRange();
            float upsModPers = MathHelper.randomRange();
            newStatList[i] = new float[]{gross, mod, total, upsGrossBP, upsModBP, upsModPers};
        }
        bp.PrintBodyPart();
        bp.AddToStat(newStatList);
        bp.PrintBodyPart();

        for (int i = 0; i < 16; i++)
        {
            assertEquals(bp.GetMyTotalStats()[i][0],newStatList[i][3]+initialStats[i][0], accuracyDelta);
            assertEquals(bp.GetMyTotalStats()[i][1],newStatList[i][4]+initialStats[i][1], accuracyDelta);
            assertEquals(bp.GetMyTotalStats()[i][2],bp.GetMyTotalStats()[i][0]*bp.GetMyTotalStats()[i][1], accuracyDelta);
        }

        float[][] newStatList2 = new float[16][6];
        for (int i = 0; i < 16; i++)
        {
            float gross = MathHelper.randomRange()*10;
            float mod = MathHelper.randomRange();
            float total = gross*mod;
            float upsGrossBP = MathHelper.randomRange()*10;
            float upsModBP = MathHelper.randomRange();
            float upsModPers = MathHelper.randomRange();
            float[] someStat = {gross,mod,total,upsGrossBP,upsModBP,upsModPers};
            newStatList2[i] = new float[]{gross, mod, total, upsGrossBP, upsModBP, upsModPers};
        }

        bp.AddToStat(newStatList2);

        for (int i = 0; i < 16; i++)
        {
            assertEquals(bp.GetMyTotalStats()[i][0],newStatList[i][3]+newStatList2[i][3]+initialStats[i][0], accuracyDelta);
            assertEquals(bp.GetMyTotalStats()[i][1],newStatList[i][4]+newStatList2[i][4]+initialStats[i][1], accuracyDelta);
            assertEquals(bp.GetMyTotalStats()[i][2],bp.GetMyTotalStats()[i][0]*bp.GetMyTotalStats()[i][1], accuracyDelta);
        }
    }
}