package Main.BodyLogic;

import Main.BodyLogic.BodyPart;

import java.util.ArrayList;
import java.util.List;

public class Person
{
    public List<BodyPart> myBodyParts = new ArrayList<>();
    public String name;
    public int grossBloodCapacity;
    public int grossBloodGeneration;
    public int grossBloodNeeded;
    public int grossEnergyCapacity;
    public int grossEnergyGeneration;
    public int grossEnergyNeeded;
    public int grossSize;
    public int grossSpeedModifier;
    public int grossConsciousness;
    public int grossSight;

    /**
     * This function prints out the person's stats for debugging;
     */
    public void printPersonToTerminal()
    {
        System.out.println("> Name: " + this.name);
        System.out.println("    GROSS STATS:");
        System.out.println("    Blood capacity: " + grossBloodCapacity);
        System.out.println("    Blood generation: " + grossBloodGeneration);
        System.out.println("    Blood needed: " + grossBloodNeeded);
        System.out.println("    Energy Capacity: " + grossEnergyCapacity);
        System.out.println("    Energy Capacity: " + grossEnergyGeneration);
        System.out.println("    Energy Capacity: " + grossEnergyNeeded);
        System.out.println("    Size: " + grossSize);
        System.out.println("    Speed: " + grossSpeedModifier);
        System.out.println("    Consciousness: " + grossConsciousness);
        System.out.println("    Sight: " + grossSight);

    }

    public void changeName(String newName)
    {
        name = newName;
    }
}