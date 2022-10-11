package Main.BodyLogic;

import java.util.ArrayList;
import java.util.List;

public class Person
{
    public List<BodyPart> myBodyParts = new ArrayList<>();
    public String name;
    private int[] bloodCapacity = new int[2];
    private int[] bloodGeneration = new int[2];
    private int[] bloodNeeded = new int[2];
    private int[] energyCapacity = new int[2];
    private int[] energyGeneration = new int[2];
    private int[] energyNeeded = new int[2];
    private int[] size = new int[2];
    private int[] speedModifier = new int[2];
    private int[] consciousness = new int[2];
    private int[] sight = new int[2];

    public void changeGrossBloodCapacity(int change)
    {
        bloodCapacity[0] = bloodCapacity[0] + change;
    }
    public void changeGrossBloodGeneration(int change)
    {
        bloodGeneration[0] = bloodGeneration[0] + change;
    }
    public void changeGrossBloodNeeded(int change)
    {
        bloodNeeded[0] = bloodNeeded[0] + change;
    }
    public void changeGrossEnergyCapacity(int change)
    {
        energyCapacity[0] = energyCapacity[0] + change;
    }
    public void changeGrossEnergyGeneration(int change)
    {
        energyGeneration[0] = energyGeneration[0] + change;
    }
    public void changeGrossEnergyNeeded(int change)
    {
        energyNeeded[0] = energyNeeded[0] + change;
    }
    public void changeGrossSize(int change)
    {
        size[0] = size[0] + change;
    }
    public void changeGrossSpeed(int change)
    {
        speedModifier[0] = speedModifier[0] + change;
    }
    public void changeGrossConsciousness(int change)
    {
        consciousness[0] = consciousness[0] + change;
    }
    public void changeGrossSight(int change)
    {
        sight[0] = sight[0] + change;
    }


    /**
     * This function prints out the person's stats for debugging;
     */
    public void printPersonToTerminal()
    {
        System.out.println("> Name: " + this.name);
        System.out.println("    GROSS STATS:");
        System.out.println("    Blood capacity: " + bloodCapacity);
        System.out.println("    Blood generation: " + bloodGeneration);
        System.out.println("    Blood needed: " + bloodNeeded);
        System.out.println("    Energy Capacity: " + energyCapacity);
        System.out.println("    Energy Capacity: " + energyGeneration);
        System.out.println("    Energy Capacity: " + energyNeeded);
        System.out.println("    Size: " + size);
        System.out.println("    Speed: " + speedModifier);
        System.out.println("    Consciousness: " + consciousness);
        System.out.println("    Sight: " + sight);

    }
}