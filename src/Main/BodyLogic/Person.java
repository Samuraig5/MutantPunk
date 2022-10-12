package Main.BodyLogic;

import java.util.ArrayList;
import java.util.List;

public class Person
{
    public List<BodyPart> myBodyParts = new ArrayList<>();
    public String name;
    final private float[] bloodCapacity = {0,1,0};
    final private float[] bloodGeneration = {0,1,0};
    final private float[] bloodNeeded = {0,1,0};
    final private float[] energyCapacity = {0,1,0};
    final private float[] energyGeneration = {0,1,0};
    final private float[] energyNeeded = {0,1,0};
    final private float[] size = {0,1,0};
    final private float[] speedModifier = {0,1,0};
    final private float[] consciousness = {0,1,0};
    final private float[] sight = {0,1,0};

    /**
     * Gross sum of the stats.
     * @param change Amount to add or subtract to the stat.
     */
    public void changeGrossBloodCapacity(int change)
    {
        bloodCapacity[0] = bloodCapacity[0] + change;
        changeFinalBloodCapacity();
    }
    public void changeGrossBloodGeneration(int change)
    {
        bloodGeneration[0] = bloodGeneration[0] + change;
        changeFinalBloodGeneration();
    }
    public void changeGrossBloodNeeded(int change)
    {
        bloodNeeded[0] = bloodNeeded[0] + change;
        changeFinalBloodNeeded();
    }
    public void changeGrossEnergyCapacity(int change)
    {
        energyCapacity[0] = energyCapacity[0] + change;
        changeFinalEnergyCapacity();
    }
    public void changeGrossEnergyGeneration(int change)
    {
        energyGeneration[0] = energyGeneration[0] + change;
        changeFinalEnergyGeneration();
    }
    public void changeGrossEnergyNeeded(int change)
    {
        energyNeeded[0] = energyNeeded[0] + change;
        changeFinalEnergyNeeded();
    }
    public void changeGrossSize(int change)
    {
        size[0] = size[0] + change;
        changeFinalSize();
    }
    public void changeGrossSpeed(int change)
    {
        speedModifier[0] = speedModifier[0] + change;
        changeFinalSpeed();
    }
    public void changeGrossConsciousness(int change)
    {
        consciousness[0] = consciousness[0] + change;
        changeFinalConsciousness();
    }
    public void changeGrossSight(int change)
    {
        sight[0] = sight[0] + change;
        changeFinalSight();
    }

    /**
     * Changes the modifiers of a stat. It is a multiplier, ie 1 = 100% of the gross stat, 2 = 200%, ...
     * @param change change of the modifier. 0.1 is a 10% change.
     */
    public void changeBloodCapacityModifier(float change)
    {
        bloodCapacity[1] = bloodCapacity[1] + change;
        changeFinalBloodCapacity();
    }
    public void changeBloodGenerationModifier(float change)
    {
        bloodGeneration[1] = bloodGeneration[1] + change;
        changeFinalBloodGeneration();
    }
    public void changeBloodNeededModifier(float change)
    {
        bloodNeeded[1] = bloodNeeded[1] + change;
        changeFinalBloodNeeded();
    }
    public void changeEnergyCapacityModifier(float change)
    {
        energyCapacity[1] = energyCapacity[1] + change;
        changeFinalEnergyCapacity();
    }
    public void changeEnergyGenerationModifier(float change)
    {
        energyGeneration[1] = energyGeneration[1] + change;
        changeFinalEnergyGeneration();
    }
    public void changeEnergyNeededModifier(float change)
    {
        energyNeeded[1] = energyNeeded[1] + change;
        changeFinalEnergyNeeded();
    }
    public void changeSizeModifier(float change)
    {
        size[1] = size[1] + change;
        changeFinalSize();
    }
    public void changeSpeedModifier(float change)
    {
        speedModifier[1] = speedModifier[1] + change;
        changeFinalSpeed();
    }
    public void changeConsciousnessModifier(float change)
    {
        consciousness[1] = consciousness[1] + change;
        changeFinalConsciousness();
    }
    public void changeSightModifier(float change)
    {
        sight[1] = sight[1] + change;
        changeFinalSight();
    }

    /**
     * This calculates the final stats based on the gross stats and the modifiers
     */
    private void changeFinalBloodCapacity()
    {
        bloodCapacity[2] = bloodCapacity[0]*bloodCapacity[1];
    }
    private void changeFinalBloodGeneration()
    {
        bloodGeneration[2] = bloodGeneration[0]*bloodGeneration[1];
    }
    private void changeFinalBloodNeeded()
    {
        bloodNeeded[2] = bloodNeeded[0]*bloodNeeded[1];
    }
    private void changeFinalEnergyCapacity()
    {
        energyCapacity[2] = energyCapacity[0]*energyCapacity[1];
    }
    private void changeFinalEnergyGeneration()
    {
        energyGeneration[2] = energyGeneration[0]*energyGeneration[1];
    }
    private void changeFinalEnergyNeeded()
    {
        energyNeeded[2] = energyNeeded[0]*energyNeeded[1];
    }
    private void changeFinalSize()
    {
        size[2] = size[0]*size[1];
    }
    private void changeFinalSpeed()
    {
        speedModifier[2] = speedModifier[0]*speedModifier[1];
    }
    private void changeFinalConsciousness()
    {
        consciousness[2] = consciousness[0]*consciousness[1];
    }
    private void changeFinalSight()
    {
        sight[2] = sight[0]*sight[1];
    }

    /**
     * This function prints out the person's stats for debugging;
     */
    public void printPersonToTerminal()
    {
        System.out.println("> Name: " + this.name);
        System.out.println("    GROSS STATS:");
        System.out.println("    Blood capacity: " + bloodCapacity[2]);
        System.out.println("    Blood generation: " + bloodGeneration[2]);
        System.out.println("    Blood needed: " + bloodNeeded[2]);
        System.out.println("    Energy Capacity: " + energyCapacity[2]);
        System.out.println("    Energy Capacity: " + energyGeneration[2]);
        System.out.println("    Energy Capacity: " + energyNeeded[2]);
        System.out.println("    Size: " + size[2]);
        System.out.println("    Speed: " + speedModifier[2]);
        System.out.println("    Consciousness: " + consciousness[2]);
        System.out.println("    Sight: " + sight[2]);

    }

    public void changeName(String newName)
    {
        name = newName;
    }
}