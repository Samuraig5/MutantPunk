package Test.BodyLogic;

import Main.BodyLogic.BodyFileDecoder;
import Main.BodyLogic.BodyPart;
import Main.BodyLogic.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BodyPartTest {
    BodyPart bp;
    @BeforeEach
    void setUp()
    {
        bp = BodyFileDecoder.loadBodyPartFromFile("resources/BodyParts/Containers/HumanTorso",0,0);
    }

    @org.junit.jupiter.api.Test
    void generateBodyPart() {
    }

    @org.junit.jupiter.api.Test
    void doDamage() {
        int damageAmount = 10;
        int initialHealth = bp.getCurrentHealth();
        bp.doDamage(damageAmount);
        assertEquals(initialHealth-damageAmount,bp.getCurrentHealth());
    }

    @Test
    void regenerateAtMaxHealth()
    {
        bp.regenerateDamage();
        assertEquals(bp.getMaxHealth()[2], bp.getCurrentHealth());
    }
    @Test
    void regenerateWhenNotAtMaxHealth()
    {
        bp.AddToMaxHealth(100,0); //This is just to make sure the bodyPart doesn't accidentally die
        int initialHealth = bp.getCurrentHealth();
        for(int i = 0; i < (bp.getMaxHealth()[2] - initialHealth); i++)
        {
            bp.regenerateDamage();
        }
        assertEquals(bp.getMaxHealth()[2],bp.getCurrentHealth());
    }
    @Test
    void regenerateWhenBelowRegenLimit()
    {
        bp.AddToRegenLimit(-bp.getRegenLimit()[0],0);
        bp.AddToRegenLimit(bp.getMaxHealth()[2],0);
        bp.doDamage(1);
        int initialHealth = bp.getCurrentHealth();
        bp.regenerateDamage();
        assertEquals(initialHealth,bp.getCurrentHealth());
    }
    @Test
    void regenerateWhenNoRegenRate()
    {
        bp.AddToRegenRate(-bp.getRegenRate()[0],0);
        bp.doDamage(1);
        int initialHealth = bp.getCurrentHealth();
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
    void updatePersonWhenAttached() {
    }

    @org.junit.jupiter.api.Test
    void removeBodyPart() {
    }

    @org.junit.jupiter.api.Test
    void updatePersonWhenRemoved() {
    }

    @org.junit.jupiter.api.Test
    void printBodyPartToTerminal() {
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

    @org.junit.jupiter.api.Test
    void addToBloodCapacity() {
        int[] initial = {bp.getBloodCapacity()[0], bp.getBloodCapacity()[1],bp.getBloodCapacity()[2]};
        int grossChange = 100;
        int modChange = 1;

        //Change the Gross Stats
        bp.AddToBloodCapacity(grossChange,0);
        assertEquals(initial[0]+grossChange, bp.getBloodCapacity()[0]);
        assertEquals(initial[1], bp.getBloodCapacity()[1]);
        assertEquals((initial[0]+grossChange)*initial[1], bp.getBloodCapacity()[2]);

        //Reset to initial
        bp.AddToBloodCapacity(-grossChange,0);
        assertEquals(initial[0], bp.getBloodCapacity()[0]);
        assertEquals(initial[1], bp.getBloodCapacity()[1]);
        assertEquals(initial[0]*initial[1], bp.getBloodCapacity()[2]);

        //Change the Mod Stats
        bp.AddToBloodCapacity(0,modChange);
        assertEquals(initial[0], bp.getBloodCapacity()[0]);
        assertEquals(initial[1]+modChange, bp.getBloodCapacity()[1]);
        assertEquals(initial[0]*(initial[1]+modChange), bp.getBloodCapacity()[2]);

        //Reset to initial
        bp.AddToBloodCapacity(0,-modChange);
        assertEquals(initial[0], bp.getBloodCapacity()[0]);
        assertEquals(initial[1], bp.getBloodCapacity()[1]);
        assertEquals(initial[0]*initial[1], bp.getBloodCapacity()[2]);

        //Change both gross Stats and the modifier
        bp.AddToBloodCapacity(grossChange,modChange);
        assertEquals(initial[0]+grossChange, bp.getBloodCapacity()[0]);
        assertEquals(initial[1]+modChange, bp.getBloodCapacity()[1]);
        assertEquals((initial[0]+grossChange)*(initial[1]+modChange), bp.getBloodCapacity()[2]);

        //Reset to initial
        bp.AddToBloodCapacity(-grossChange,-modChange);
        assertEquals(initial[0], bp.getBloodCapacity()[0]);
        assertEquals(initial[1], bp.getBloodCapacity()[1]);
        assertEquals(initial[0]*initial[1], bp.getBloodCapacity()[2]);
    }

    @org.junit.jupiter.api.Test
    void addToBloodGeneration() {
    }

    @org.junit.jupiter.api.Test
    void addToBloodNeeded() {
    }

    @org.junit.jupiter.api.Test
    void addToEnergyCapacity() {
    }

    @org.junit.jupiter.api.Test
    void addToEnergyGeneration() {
    }

    @org.junit.jupiter.api.Test
    void addToEnergyNeeded() {
    }

    @org.junit.jupiter.api.Test
    void addToMaxHealth() {
        int[] initialMaxHealth = bp.getMaxHealth();
        bp.AddToMaxHealth(100,1);
        initialMaxHealth[0] += 100;
        initialMaxHealth[1] += 1;
        initialMaxHealth[2] = initialMaxHealth[0]*initialMaxHealth[1];
        assertEquals(initialMaxHealth, bp.getMaxHealth());
    }

    @org.junit.jupiter.api.Test
    void addToRegenLimit() {
    }

    @org.junit.jupiter.api.Test
    void addToRegenRate() {
    }

    @org.junit.jupiter.api.Test
    void addToArmour() {
    }

    @org.junit.jupiter.api.Test
    void addToSize() {
    }

    @org.junit.jupiter.api.Test
    void addToOrganCapacity() {
    }

    @org.junit.jupiter.api.Test
    void addToSpeed() {
    }

    @org.junit.jupiter.api.Test
    void addToConsciousness() {
    }

    @org.junit.jupiter.api.Test
    void addToGrabbingSlots() {
    }

    @org.junit.jupiter.api.Test
    void addToSight() {
    }
}