package Main.WorldLogic;

import Main.BodyLogic.BodyPart;

import java.util.ArrayList;
import java.util.List;

public abstract class Person
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

    /**
     * This function instantiates a person.
     *
     * @param bias improves (if bias is positive) or worsens (if bias is negative) the stats and attribute of the persons bodyParts.
     *             The bias is expressed as a present. So a bias of '10' would increase the persons bodyPart stats by 10%.
     * @param randomness changes the stats of the persons bodyPart by a random amount (both positive and negative).
     *                   The greater the value, the stronger the random drift.
     */
    public void instantiatePerson(String name, int bias, int randomness)
    {
        this.name = name;
        generateBody(bias,randomness);
    }
    public void instantiatePerson(int bias, int randomness)
    {
        instantiatePerson("Nameless One" ,bias, randomness);
    }

    /**
     * This function generates a persons body based on their body plan.
     *
     * @param bias improves (if bias is positive) or worsens (if bias is negative) the stats and attribute of the persons bodyParts.
     *             The bias is expressed as a present. So a bias of '10' would increase the persons bodyPart stats by 10%.
     * @param randomness changes the stats of the persons bodyPart by a random amount (both positive and negative).
     *                   The greater the value, the stronger the random drift.
     */
    abstract void generateBody(int bias, int randomness);

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

    }
}