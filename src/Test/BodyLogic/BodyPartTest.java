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
        float damageAmount = 10;
        float initialHealth = bp.getCurrentHealth();
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
        float initialHealth = bp.getCurrentHealth();
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
        float initialHealth = bp.getCurrentHealth();
        bp.regenerateDamage();
        assertEquals(initialHealth,bp.getCurrentHealth());
    }
    @Test
    void regenerateWhenNoRegenRate()
    {
        bp.AddToRegenRate(-bp.getRegenRate()[0],0);
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
    void updatePersonWhenAttached() {
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

    @org.junit.jupiter.api.Test
    void addToBloodCapacity() {
        float[] initial = {bp.getBloodCapacity()[0], bp.getBloodCapacity()[1],bp.getBloodCapacity()[2]};
        float grossChange = 50;
        float modChange = 0.5f;

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
        float[] initial = {bp.getBloodGeneration()[0], bp.getBloodGeneration()[1],bp.getBloodGeneration()[2]};
        float grossChange = 50;
        float modChange = 0.5f;

        //Change the Gross Stats
        bp.AddToBloodGeneration(grossChange,0);
        assertEquals(initial[0]+grossChange, bp.getBloodGeneration()[0]);
        assertEquals(initial[1], bp.getBloodGeneration()[1]);
        assertEquals((initial[0]+grossChange)*initial[1], bp.getBloodGeneration()[2]);

        //Reset to initial
        bp.AddToBloodGeneration(-grossChange,0);
        assertEquals(initial[0], bp.getBloodGeneration()[0]);
        assertEquals(initial[1], bp.getBloodGeneration()[1]);
        assertEquals(initial[0]*initial[1], bp.getBloodGeneration()[2]);

        //Change the Mod Stats
        bp.AddToBloodGeneration(0,modChange);
        assertEquals(initial[0], bp.getBloodGeneration()[0]);
        assertEquals(initial[1]+modChange, bp.getBloodGeneration()[1]);
        assertEquals(initial[0]*(initial[1]+modChange), bp.getBloodGeneration()[2]);

        //Reset to initial
        bp.AddToBloodGeneration(0,-modChange);
        assertEquals(initial[0], bp.getBloodGeneration()[0]);
        assertEquals(initial[1], bp.getBloodGeneration()[1]);
        assertEquals(initial[0]*initial[1], bp.getBloodGeneration()[2]);

        //Change both gross Stats and the modifier
        bp.AddToBloodGeneration(grossChange,modChange);
        assertEquals(initial[0]+grossChange, bp.getBloodGeneration()[0]);
        assertEquals(initial[1]+modChange, bp.getBloodGeneration()[1]);
        assertEquals((initial[0]+grossChange)*(initial[1]+modChange), bp.getBloodGeneration()[2]);

        //Reset to initial
        bp.AddToBloodGeneration(-grossChange,-modChange);
        assertEquals(initial[0], bp.getBloodGeneration()[0]);
        assertEquals(initial[1], bp.getBloodGeneration()[1]);
        assertEquals(initial[0]*initial[1], bp.getBloodGeneration()[2]);
    }

    @org.junit.jupiter.api.Test
    void addToBloodNeeded() {
        float[] initial = {bp.getBloodNeeded()[0], bp.getBloodNeeded()[1],bp.getBloodNeeded()[2]};
        float grossChange = 50;
        float modChange = 0.5f;

        //Change the Gross Stats
        bp.AddToBloodNeeded(grossChange,0);
        assertEquals(initial[0]+grossChange, bp.getBloodNeeded()[0]);
        assertEquals(initial[1], bp.getBloodNeeded()[1]);
        assertEquals((initial[0]+grossChange)*initial[1], bp.getBloodNeeded()[2]);

        //Reset to initial
        bp.AddToBloodNeeded(-grossChange,0);
        assertEquals(initial[0], bp.getBloodNeeded()[0]);
        assertEquals(initial[1], bp.getBloodNeeded()[1]);
        assertEquals(initial[0]*initial[1], bp.getBloodNeeded()[2]);

        //Change the Mod Stats
        bp.AddToBloodNeeded(0,modChange);
        assertEquals(initial[0], bp.getBloodNeeded()[0]);
        assertEquals(initial[1]+modChange, bp.getBloodNeeded()[1]);
        assertEquals(initial[0]*(initial[1]+modChange), bp.getBloodNeeded()[2]);

        //Reset to initial
        bp.AddToBloodNeeded(0,-modChange);
        assertEquals(initial[0], bp.getBloodNeeded()[0]);
        assertEquals(initial[1], bp.getBloodNeeded()[1]);
        assertEquals(initial[0]*initial[1], bp.getBloodNeeded()[2]);

        //Change both gross Stats and the modifier
        bp.AddToBloodNeeded(grossChange,modChange);
        assertEquals(initial[0]+grossChange, bp.getBloodNeeded()[0]);
        assertEquals(initial[1]+modChange, bp.getBloodNeeded()[1]);
        assertEquals((initial[0]+grossChange)*(initial[1]+modChange), bp.getBloodNeeded()[2]);

        //Reset to initial
        bp.AddToBloodNeeded(-grossChange,-modChange);
        assertEquals(initial[0], bp.getBloodNeeded()[0]);
        assertEquals(initial[1], bp.getBloodNeeded()[1]);
        assertEquals(initial[0]*initial[1], bp.getBloodNeeded()[2]);
    }

    @org.junit.jupiter.api.Test
    void addToEnergyCapacity() {
        float[] initial = {bp.getEnergyCapacity()[0], bp.getEnergyCapacity()[1],bp.getEnergyCapacity()[2]};
        float grossChange = 50;
        float modChange = 0.5f;

        //Change the Gross Stats
        bp.AddToEnergyCapacity(grossChange,0);
        assertEquals(initial[0]+grossChange, bp.getEnergyCapacity()[0]);
        assertEquals(initial[1], bp.getEnergyCapacity()[1]);
        assertEquals((initial[0]+grossChange)*initial[1], bp.getEnergyCapacity()[2]);

        //Reset to initial
        bp.AddToEnergyCapacity(-grossChange,0);
        assertEquals(initial[0], bp.getEnergyCapacity()[0]);
        assertEquals(initial[1], bp.getEnergyCapacity()[1]);
        assertEquals(initial[0]*initial[1], bp.getEnergyCapacity()[2]);

        //Change the Mod Stats
        bp.AddToEnergyCapacity(0,modChange);
        assertEquals(initial[0], bp.getEnergyCapacity()[0]);
        assertEquals(initial[1]+modChange, bp.getEnergyCapacity()[1]);
        assertEquals(initial[0]*(initial[1]+modChange), bp.getEnergyCapacity()[2]);

        //Reset to initial
        bp.AddToEnergyCapacity(0,-modChange);
        assertEquals(initial[0], bp.getEnergyCapacity()[0]);
        assertEquals(initial[1], bp.getEnergyCapacity()[1]);
        assertEquals(initial[0]*initial[1], bp.getEnergyCapacity()[2]);

        //Change both gross Stats and the modifier
        bp.AddToEnergyCapacity(grossChange,modChange);
        assertEquals(initial[0]+grossChange, bp.getEnergyCapacity()[0]);
        assertEquals(initial[1]+modChange, bp.getEnergyCapacity()[1]);
        assertEquals((initial[0]+grossChange)*(initial[1]+modChange), bp.getEnergyCapacity()[2]);

        //Reset to initial
        bp.AddToEnergyCapacity(-grossChange,-modChange);
        assertEquals(initial[0], bp.getEnergyCapacity()[0]);
        assertEquals(initial[1], bp.getEnergyCapacity()[1]);
        assertEquals(initial[0]*initial[1], bp.getEnergyCapacity()[2]);
    }

    @org.junit.jupiter.api.Test
    void addToEnergyGeneration() {
        float[] initial = {bp.getEnergyGeneration()[0], bp.getEnergyGeneration()[1],bp.getEnergyGeneration()[2]};
        float grossChange = 50;
        float modChange = 0.5f;

        //Change the Gross Stats
        bp.AddToEnergyGeneration(grossChange,0);
        assertEquals(initial[0]+grossChange, bp.getEnergyGeneration()[0]);
        assertEquals(initial[1], bp.getEnergyGeneration()[1]);
        assertEquals((initial[0]+grossChange)*initial[1], bp.getEnergyGeneration()[2]);

        //Reset to initial
        bp.AddToEnergyGeneration(-grossChange,0);
        assertEquals(initial[0], bp.getEnergyGeneration()[0]);
        assertEquals(initial[1], bp.getEnergyGeneration()[1]);
        assertEquals(initial[0]*initial[1], bp.getEnergyGeneration()[2]);

        //Change the Mod Stats
        bp.AddToEnergyGeneration(0,modChange);
        assertEquals(initial[0], bp.getEnergyGeneration()[0]);
        assertEquals(initial[1]+modChange, bp.getEnergyGeneration()[1]);
        assertEquals(initial[0]*(initial[1]+modChange), bp.getEnergyGeneration()[2]);

        //Reset to initial
        bp.AddToEnergyGeneration(0,-modChange);
        assertEquals(initial[0], bp.getEnergyGeneration()[0]);
        assertEquals(initial[1], bp.getEnergyGeneration()[1]);
        assertEquals(initial[0]*initial[1], bp.getEnergyGeneration()[2]);

        //Change both gross Stats and the modifier
        bp.AddToEnergyGeneration(grossChange,modChange);
        assertEquals(initial[0]+grossChange, bp.getEnergyGeneration()[0]);
        assertEquals(initial[1]+modChange, bp.getEnergyGeneration()[1]);
        assertEquals((initial[0]+grossChange)*(initial[1]+modChange), bp.getEnergyGeneration()[2]);

        //Reset to initial
        bp.AddToEnergyGeneration(-grossChange,-modChange);
        assertEquals(initial[0], bp.getEnergyGeneration()[0]);
        assertEquals(initial[1], bp.getEnergyGeneration()[1]);
        assertEquals(initial[0]*initial[1], bp.getEnergyGeneration()[2]);
    }

    @org.junit.jupiter.api.Test
    void addToEnergyNeeded() {
        float[] initial = {bp.getEnergyNeeded()[0], bp.getEnergyNeeded()[1],bp.getEnergyNeeded()[2]};
        float grossChange = 50;
        float modChange = 0.5f;

        //Change the Gross Stats
        bp.AddToEnergyNeeded(grossChange,0);
        assertEquals(initial[0]+grossChange, bp.getEnergyNeeded()[0]);
        assertEquals(initial[1], bp.getEnergyNeeded()[1]);
        assertEquals((initial[0]+grossChange)*initial[1], bp.getEnergyNeeded()[2]);

        //Reset to initial
        bp.AddToEnergyNeeded(-grossChange,0);
        assertEquals(initial[0], bp.getEnergyNeeded()[0]);
        assertEquals(initial[1], bp.getEnergyNeeded()[1]);
        assertEquals(initial[0]*initial[1], bp.getEnergyNeeded()[2]);

        //Change the Mod Stats
        bp.AddToEnergyNeeded(0,modChange);
        assertEquals(initial[0], bp.getEnergyNeeded()[0]);
        assertEquals(initial[1]+modChange, bp.getEnergyNeeded()[1]);
        assertEquals(initial[0]*(initial[1]+modChange), bp.getEnergyNeeded()[2]);

        //Reset to initial
        bp.AddToEnergyNeeded(0,-modChange);
        assertEquals(initial[0], bp.getEnergyNeeded()[0]);
        assertEquals(initial[1], bp.getEnergyNeeded()[1]);
        assertEquals(initial[0]*initial[1], bp.getEnergyNeeded()[2]);

        //Change both gross Stats and the modifier
        bp.AddToEnergyNeeded(grossChange,modChange);
        assertEquals(initial[0]+grossChange, bp.getEnergyNeeded()[0]);
        assertEquals(initial[1]+modChange, bp.getEnergyNeeded()[1]);
        assertEquals((initial[0]+grossChange)*(initial[1]+modChange), bp.getEnergyNeeded()[2]);

        //Reset to initial
        bp.AddToEnergyNeeded(-grossChange,-modChange);
        assertEquals(initial[0], bp.getEnergyNeeded()[0]);
        assertEquals(initial[1], bp.getEnergyNeeded()[1]);
        assertEquals(initial[0]*initial[1], bp.getEnergyNeeded()[2]);
    }

    @org.junit.jupiter.api.Test
    void addToMaxHealth() {
        float[] initial = {bp.getMaxHealth()[0], bp.getMaxHealth()[1],bp.getMaxHealth()[2]};
        float grossChange = 50;
        float modChange = 0.5f;

        //Change the Gross Stats
        bp.AddToMaxHealth(grossChange,0);
        assertEquals(initial[0]+grossChange, bp.getMaxHealth()[0]);
        assertEquals(initial[1], bp.getMaxHealth()[1]);
        assertEquals((initial[0]+grossChange)*initial[1], bp.getMaxHealth()[2]);

        //Reset to initial
        bp.AddToMaxHealth(-grossChange,0);
        assertEquals(initial[0], bp.getMaxHealth()[0]);
        assertEquals(initial[1], bp.getMaxHealth()[1]);
        assertEquals(initial[0]*initial[1], bp.getMaxHealth()[2]);

        //Change the Mod Stats
        bp.AddToMaxHealth(0,modChange);
        assertEquals(initial[0], bp.getMaxHealth()[0]);
        assertEquals(initial[1]+modChange, bp.getMaxHealth()[1]);
        assertEquals(initial[0]*(initial[1]+modChange), bp.getMaxHealth()[2]);

        //Reset to initial
        bp.AddToMaxHealth(0,-modChange);
        assertEquals(initial[0], bp.getMaxHealth()[0]);
        assertEquals(initial[1], bp.getMaxHealth()[1]);
        assertEquals(initial[0]*initial[1], bp.getMaxHealth()[2]);

        //Change both gross Stats and the modifier
        bp.AddToMaxHealth(grossChange,modChange);
        assertEquals(initial[0]+grossChange, bp.getMaxHealth()[0]);
        assertEquals(initial[1]+modChange, bp.getMaxHealth()[1]);
        assertEquals((initial[0]+grossChange)*(initial[1]+modChange), bp.getMaxHealth()[2]);

        //Reset to initial
        bp.AddToMaxHealth(-grossChange,-modChange);
        assertEquals(initial[0], bp.getMaxHealth()[0]);
        assertEquals(initial[1], bp.getMaxHealth()[1]);
        assertEquals(initial[0]*initial[1], bp.getMaxHealth()[2]);
    }

    @org.junit.jupiter.api.Test
    void addToRegenLimit() {
        float[] initial = {bp.getRegenLimit()[0], bp.getRegenLimit()[1],bp.getRegenLimit()[2]};
        float grossChange = 50;
        float modChange = 0.5f;

        //Change the Gross Stats
        bp.AddToRegenLimit(grossChange,0);
        assertEquals(initial[0]+grossChange, bp.getRegenLimit()[0]);
        assertEquals(initial[1], bp.getRegenLimit()[1]);
        assertEquals((initial[0]+grossChange)*initial[1], bp.getRegenLimit()[2]);

        //Reset to initial
        bp.AddToRegenLimit(-grossChange,0);
        assertEquals(initial[0], bp.getRegenLimit()[0]);
        assertEquals(initial[1], bp.getRegenLimit()[1]);
        assertEquals(initial[0]*initial[1], bp.getRegenLimit()[2]);

        //Change the Mod Stats
        bp.AddToRegenLimit(0,modChange);
        assertEquals(initial[0], bp.getRegenLimit()[0]);
        assertEquals(initial[1]+modChange, bp.getRegenLimit()[1]);
        assertEquals(initial[0]*(initial[1]+modChange), bp.getRegenLimit()[2]);

        //Reset to initial
        bp.AddToRegenLimit(0,-modChange);
        assertEquals(initial[0], bp.getRegenLimit()[0]);
        assertEquals(initial[1], bp.getRegenLimit()[1]);
        assertEquals(initial[0]*initial[1], bp.getRegenLimit()[2]);

        //Change both gross Stats and the modifier
        bp.AddToRegenLimit(grossChange,modChange);
        assertEquals(initial[0]+grossChange, bp.getRegenLimit()[0]);
        assertEquals(initial[1]+modChange, bp.getRegenLimit()[1]);
        assertEquals((initial[0]+grossChange)*(initial[1]+modChange), bp.getRegenLimit()[2]);

        //Reset to initial
        bp.AddToRegenLimit(-grossChange,-modChange);
        assertEquals(initial[0], bp.getRegenLimit()[0]);
        assertEquals(initial[1], bp.getRegenLimit()[1]);
        assertEquals(initial[0]*initial[1], bp.getRegenLimit()[2]);
    }

    @org.junit.jupiter.api.Test
    void addToRegenRate() {
        float[] initial = {bp.getRegenRate()[0], bp.getRegenRate()[1],bp.getRegenRate()[2]};
        float grossChange = 50;
        float modChange = 0.5f;

        //Change the Gross Stats
        bp.AddToRegenRate(grossChange,0);
        assertEquals(initial[0]+grossChange, bp.getRegenRate()[0]);
        assertEquals(initial[1], bp.getRegenRate()[1]);
        assertEquals((initial[0]+grossChange)*initial[1], bp.getRegenRate()[2]);

        //Reset to initial
        bp.AddToRegenRate(-grossChange,0);
        assertEquals(initial[0], bp.getRegenRate()[0]);
        assertEquals(initial[1], bp.getRegenRate()[1]);
        assertEquals(initial[0]*initial[1], bp.getRegenRate()[2]);

        //Change the Mod Stats
        bp.AddToRegenRate(0,modChange);
        assertEquals(initial[0], bp.getRegenRate()[0]);
        assertEquals(initial[1]+modChange, bp.getRegenRate()[1]);
        assertEquals(initial[0]*(initial[1]+modChange), bp.getRegenRate()[2]);

        //Reset to initial
        bp.AddToRegenRate(0,-modChange);
        assertEquals(initial[0], bp.getRegenRate()[0]);
        assertEquals(initial[1], bp.getRegenRate()[1]);
        assertEquals(initial[0]*initial[1], bp.getRegenRate()[2]);

        //Change both gross Stats and the modifier
        bp.AddToRegenRate(grossChange,modChange);
        assertEquals(initial[0]+grossChange, bp.getRegenRate()[0]);
        assertEquals(initial[1]+modChange, bp.getRegenRate()[1]);
        assertEquals((initial[0]+grossChange)*(initial[1]+modChange), bp.getRegenRate()[2]);

        //Reset to initial
        bp.AddToRegenRate(-grossChange,-modChange);
        assertEquals(initial[0], bp.getRegenRate()[0]);
        assertEquals(initial[1], bp.getRegenRate()[1]);
        assertEquals(initial[0]*initial[1], bp.getRegenRate()[2]);
    }

    @org.junit.jupiter.api.Test
    void addToArmour() {
        float[] initial = {bp.getArmour()[0], bp.getArmour()[1],bp.getArmour()[2]};
        float grossChange = 50;
        float modChange = 0.5f;

        //Change the Gross Stats
        bp.AddToArmour(grossChange,0);
        assertEquals(initial[0]+grossChange, bp.getArmour()[0]);
        assertEquals(initial[1], bp.getArmour()[1]);
        assertEquals((initial[0]+grossChange)*initial[1], bp.getArmour()[2]);

        //Reset to initial
        bp.AddToArmour(-grossChange,0);
        assertEquals(initial[0], bp.getArmour()[0]);
        assertEquals(initial[1], bp.getArmour()[1]);
        assertEquals(initial[0]*initial[1], bp.getArmour()[2]);

        //Change the Mod Stats
        bp.AddToArmour(0,modChange);
        assertEquals(initial[0], bp.getArmour()[0]);
        assertEquals(initial[1]+modChange, bp.getArmour()[1]);
        assertEquals(initial[0]*(initial[1]+modChange), bp.getArmour()[2]);

        //Reset to initial
        bp.AddToArmour(0,-modChange);
        assertEquals(initial[0], bp.getArmour()[0]);
        assertEquals(initial[1], bp.getArmour()[1]);
        assertEquals(initial[0]*initial[1], bp.getArmour()[2]);

        //Change both gross Stats and the modifier
        bp.AddToArmour(grossChange,modChange);
        assertEquals(initial[0]+grossChange, bp.getArmour()[0]);
        assertEquals(initial[1]+modChange, bp.getArmour()[1]);
        assertEquals((initial[0]+grossChange)*(initial[1]+modChange), bp.getArmour()[2]);

        //Reset to initial
        bp.AddToArmour(-grossChange,-modChange);
        assertEquals(initial[0], bp.getArmour()[0]);
        assertEquals(initial[1], bp.getArmour()[1]);
        assertEquals(initial[0]*initial[1], bp.getArmour()[2]);
    }

    @org.junit.jupiter.api.Test
    void addToSize() {
        float[] initial = {bp.getSize()[0], bp.getSize()[1],bp.getSize()[2]};
        float grossChange = 50;
        float modChange = 0.5f;

        //Change the Gross Stats
        bp.AddToSize(grossChange,0);
        assertEquals(initial[0]+grossChange, bp.getSize()[0]);
        assertEquals(initial[1], bp.getSize()[1]);
        assertEquals((initial[0]+grossChange)*initial[1], bp.getSize()[2]);

        //Reset to initial
        bp.AddToSize(-grossChange,0);
        assertEquals(initial[0], bp.getSize()[0]);
        assertEquals(initial[1], bp.getSize()[1]);
        assertEquals(initial[0]*initial[1], bp.getSize()[2]);

        //Change the Mod Stats
        bp.AddToSize(0,modChange);
        assertEquals(initial[0], bp.getSize()[0]);
        assertEquals(initial[1]+modChange, bp.getSize()[1]);
        assertEquals(initial[0]*(initial[1]+modChange), bp.getSize()[2]);

        //Reset to initial
        bp.AddToSize(0,-modChange);
        assertEquals(initial[0], bp.getSize()[0]);
        assertEquals(initial[1], bp.getSize()[1]);
        assertEquals(initial[0]*initial[1], bp.getSize()[2]);

        //Change both gross Stats and the modifier
        bp.AddToSize(grossChange,modChange);
        assertEquals(initial[0]+grossChange, bp.getSize()[0]);
        assertEquals(initial[1]+modChange, bp.getSize()[1]);
        assertEquals((initial[0]+grossChange)*(initial[1]+modChange), bp.getSize()[2]);

        //Reset to initial
        bp.AddToSize(-grossChange,-modChange);
        assertEquals(initial[0], bp.getSize()[0]);
        assertEquals(initial[1], bp.getSize()[1]);
        assertEquals(initial[0]*initial[1], bp.getSize()[2]);
    }

    @org.junit.jupiter.api.Test
    void addToOrganCapacity() {
        float[] initial = {bp.getOrganCapacity()[0], bp.getOrganCapacity()[1],bp.getOrganCapacity()[2]};
        float grossChange = 50;
        float modChange = 0.5f;

        //Change the Gross Stats
        bp.AddToOrganCapacity(grossChange,0);
        assertEquals(initial[0]+grossChange, bp.getOrganCapacity()[0]);
        assertEquals(initial[1], bp.getOrganCapacity()[1]);
        assertEquals((initial[0]+grossChange)*initial[1], bp.getOrganCapacity()[2]);

        //Reset to initial
        bp.AddToOrganCapacity(-grossChange,0);
        assertEquals(initial[0], bp.getOrganCapacity()[0]);
        assertEquals(initial[1], bp.getOrganCapacity()[1]);
        assertEquals(initial[0]*initial[1], bp.getOrganCapacity()[2]);

        //Change the Mod Stats
        bp.AddToOrganCapacity(0,modChange);
        assertEquals(initial[0], bp.getOrganCapacity()[0]);
        assertEquals(initial[1]+modChange, bp.getOrganCapacity()[1]);
        assertEquals(initial[0]*(initial[1]+modChange), bp.getOrganCapacity()[2]);

        //Reset to initial
        bp.AddToOrganCapacity(0,-modChange);
        assertEquals(initial[0], bp.getOrganCapacity()[0]);
        assertEquals(initial[1], bp.getOrganCapacity()[1]);
        assertEquals(initial[0]*initial[1], bp.getOrganCapacity()[2]);

        //Change both gross Stats and the modifier
        bp.AddToOrganCapacity(grossChange,modChange);
        assertEquals(initial[0]+grossChange, bp.getOrganCapacity()[0]);
        assertEquals(initial[1]+modChange, bp.getOrganCapacity()[1]);
        assertEquals((initial[0]+grossChange)*(initial[1]+modChange), bp.getOrganCapacity()[2]);

        //Reset to initial
        bp.AddToOrganCapacity(-grossChange,-modChange);
        assertEquals(initial[0], bp.getOrganCapacity()[0]);
        assertEquals(initial[1], bp.getOrganCapacity()[1]);
        assertEquals(initial[0]*initial[1], bp.getOrganCapacity()[2]);
    }

    @org.junit.jupiter.api.Test
    void addToSpeed() {
        float[] initial = {bp.getSpeed()[0], bp.getSpeed()[1],bp.getSpeed()[2]};
        float grossChange = 50;
        float modChange = 0.5f;

        //Change the Gross Stats
        bp.AddToSpeed(grossChange,0);
        assertEquals(initial[0]+grossChange, bp.getSpeed()[0]);
        assertEquals(initial[1], bp.getSpeed()[1]);
        assertEquals((initial[0]+grossChange)*initial[1], bp.getSpeed()[2]);

        //Reset to initial
        bp.AddToSpeed(-grossChange,0);
        assertEquals(initial[0], bp.getSpeed()[0]);
        assertEquals(initial[1], bp.getSpeed()[1]);
        assertEquals(initial[0]*initial[1], bp.getSpeed()[2]);

        //Change the Mod Stats
        bp.AddToSpeed(0,modChange);
        assertEquals(initial[0], bp.getSpeed()[0]);
        assertEquals(initial[1]+modChange, bp.getSpeed()[1]);
        assertEquals(initial[0]*(initial[1]+modChange), bp.getSpeed()[2]);

        //Reset to initial
        bp.AddToSpeed(0,-modChange);
        assertEquals(initial[0], bp.getSpeed()[0]);
        assertEquals(initial[1], bp.getSpeed()[1]);
        assertEquals(initial[0]*initial[1], bp.getSpeed()[2]);

        //Change both gross Stats and the modifier
        bp.AddToSpeed(grossChange,modChange);
        assertEquals(initial[0]+grossChange, bp.getSpeed()[0]);
        assertEquals(initial[1]+modChange, bp.getSpeed()[1]);
        assertEquals((initial[0]+grossChange)*(initial[1]+modChange), bp.getSpeed()[2]);

        //Reset to initial
        bp.AddToSpeed(-grossChange,-modChange);
        assertEquals(initial[0], bp.getSpeed()[0]);
        assertEquals(initial[1], bp.getSpeed()[1]);
        assertEquals(initial[0]*initial[1], bp.getSpeed()[2]);
    }

    @org.junit.jupiter.api.Test
    void addToConsciousness() {
        float[] initial = {bp.getConsciousness()[0], bp.getConsciousness()[1],bp.getConsciousness()[2]};
        float grossChange = 50;
        float modChange = 0.5f;

        //Change the Gross Stats
        bp.AddToConsciousness(grossChange,0);
        assertEquals(initial[0]+grossChange, bp.getConsciousness()[0]);
        assertEquals(initial[1], bp.getConsciousness()[1]);
        assertEquals((initial[0]+grossChange)*initial[1], bp.getConsciousness()[2]);

        //Reset to initial
        bp.AddToConsciousness(-grossChange,0);
        assertEquals(initial[0], bp.getConsciousness()[0]);
        assertEquals(initial[1], bp.getConsciousness()[1]);
        assertEquals(initial[0]*initial[1], bp.getConsciousness()[2]);

        //Change the Mod Stats
        bp.AddToConsciousness(0,modChange);
        assertEquals(initial[0], bp.getConsciousness()[0]);
        assertEquals(initial[1]+modChange, bp.getConsciousness()[1]);
        assertEquals(initial[0]*(initial[1]+modChange), bp.getConsciousness()[2]);

        //Reset to initial
        bp.AddToConsciousness(0,-modChange);
        assertEquals(initial[0], bp.getConsciousness()[0]);
        assertEquals(initial[1], bp.getConsciousness()[1]);
        assertEquals(initial[0]*initial[1], bp.getConsciousness()[2]);

        //Change both gross Stats and the modifier
        bp.AddToConsciousness(grossChange,modChange);
        assertEquals(initial[0]+grossChange, bp.getConsciousness()[0]);
        assertEquals(initial[1]+modChange, bp.getConsciousness()[1]);
        assertEquals((initial[0]+grossChange)*(initial[1]+modChange), bp.getConsciousness()[2]);

        //Reset to initial
        bp.AddToConsciousness(-grossChange,-modChange);
        assertEquals(initial[0], bp.getConsciousness()[0]);
        assertEquals(initial[1], bp.getConsciousness()[1]);
        assertEquals(initial[0]*initial[1], bp.getConsciousness()[2]);
    }

    @org.junit.jupiter.api.Test
    void addToGrabbingSlots() {
        float[] initial = {bp.getGrabbingSlots()[0], bp.getGrabbingSlots()[1],bp.getGrabbingSlots()[2]};
        float grossChange = 50;
        float modChange = 0.5f;

        //Change the Gross Stats
        bp.AddToGrabbingSlots(grossChange,0);
        assertEquals(initial[0]+grossChange, bp.getGrabbingSlots()[0]);
        assertEquals(initial[1], bp.getGrabbingSlots()[1]);
        assertEquals((initial[0]+grossChange)*initial[1], bp.getGrabbingSlots()[2]);

        //Reset to initial
        bp.AddToGrabbingSlots(-grossChange,0);
        assertEquals(initial[0], bp.getGrabbingSlots()[0]);
        assertEquals(initial[1], bp.getGrabbingSlots()[1]);
        assertEquals(initial[0]*initial[1], bp.getGrabbingSlots()[2]);

        //Change the Mod Stats
        bp.AddToGrabbingSlots(0,modChange);
        assertEquals(initial[0], bp.getGrabbingSlots()[0]);
        assertEquals(initial[1]+modChange, bp.getGrabbingSlots()[1]);
        assertEquals(initial[0]*(initial[1]+modChange), bp.getGrabbingSlots()[2]);

        //Reset to initial
        bp.AddToGrabbingSlots(0,-modChange);
        assertEquals(initial[0], bp.getGrabbingSlots()[0]);
        assertEquals(initial[1], bp.getGrabbingSlots()[1]);
        assertEquals(initial[0]*initial[1], bp.getGrabbingSlots()[2]);

        //Change both gross Stats and the modifier
        bp.AddToGrabbingSlots(grossChange,modChange);
        assertEquals(initial[0]+grossChange, bp.getGrabbingSlots()[0]);
        assertEquals(initial[1]+modChange, bp.getGrabbingSlots()[1]);
        assertEquals((initial[0]+grossChange)*(initial[1]+modChange), bp.getGrabbingSlots()[2]);

        //Reset to initial
        bp.AddToGrabbingSlots(-grossChange,-modChange);
        assertEquals(initial[0], bp.getGrabbingSlots()[0]);
        assertEquals(initial[1], bp.getGrabbingSlots()[1]);
        assertEquals(initial[0]*initial[1], bp.getGrabbingSlots()[2]);
    }

    @org.junit.jupiter.api.Test
    void addToSight() {
        float[] initial = {bp.getSight()[0], bp.getSight()[1],bp.getSight()[2]};
        float grossChange = 50;
        float modChange = 0.5f;

        //Change the Gross Stats
        bp.AddToSight(grossChange,0);
        assertEquals(initial[0]+grossChange, bp.getSight()[0]);
        assertEquals(initial[1], bp.getSight()[1]);
        assertEquals((initial[0]+grossChange)*initial[1], bp.getSight()[2]);

        //Reset to initial
        bp.AddToSight(-grossChange,0);
        assertEquals(initial[0], bp.getSight()[0]);
        assertEquals(initial[1], bp.getSight()[1]);
        assertEquals(initial[0]*initial[1], bp.getSight()[2]);

        //Change the Mod Stats
        bp.AddToSight(0,modChange);
        assertEquals(initial[0], bp.getSight()[0]);
        assertEquals(initial[1]+modChange, bp.getSight()[1]);
        assertEquals(initial[0]*(initial[1]+modChange), bp.getSight()[2]);

        //Reset to initial
        bp.AddToSight(0,-modChange);
        assertEquals(initial[0], bp.getSight()[0]);
        assertEquals(initial[1], bp.getSight()[1]);
        assertEquals(initial[0]*initial[1], bp.getSight()[2]);

        //Change both gross Stats and the modifier
        bp.AddToSight(grossChange,modChange);
        assertEquals(initial[0]+grossChange, bp.getSight()[0]);
        assertEquals(initial[1]+modChange, bp.getSight()[1]);
        assertEquals((initial[0]+grossChange)*(initial[1]+modChange), bp.getSight()[2]);

        //Reset to initial
        bp.AddToSight(-grossChange,-modChange);
        assertEquals(initial[0], bp.getSight()[0]);
        assertEquals(initial[1], bp.getSight()[1]);
        assertEquals(initial[0]*initial[1], bp.getSight()[2]);
    }
}