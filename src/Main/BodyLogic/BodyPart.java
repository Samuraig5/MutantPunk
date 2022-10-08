package Main.BodyLogic;

import java.util.ArrayList;
import java.util.List;

public class BodyPart
{
    /**
     * The (nick-)name of this specific bodyPart.
     */
    public String name;

    /**
     * This keeps track of the person this bodyPart is attached to.
     * If the body part is cut off, it is equal to 'null'
     */
    public Person myPerson;

    /**
     * This is the bodyPart this bodyPart is attached to.
     */
    public BodyPart bodyPartAttachedTo;

    /**
     * These are the bodyParts attacked to this bodyPart.
     */
    public List<BodyPart> attachedBodyParts = new ArrayList<>();

    /**
     * This is an index that signifies which type the body part it.
     *  1-Organic
     *  2-Cybernetic
     */
    public int type;

    /**
     * This is keeping track of the class of the bodyPart like 'arm', 'heart', 'skin', 'implant' and so on.
     */
    public String bodyPartClass;

    /**
     * Amount of blood that can be stored in this bodyPart
     */
    public int bloodCapacity;

    /**
     * Amount of blood this bodyPart generates per time unit.
     */
    public int bloodGeneration;

    /**
     * Amount of blood needed for the bodyPart to function.
     */
    public int neededBlood;

    /**
     * Amount of energy this bodyPart can store.
     */
    public int energyCapacity;

    /**
     * Amount of energy this bodyPart generates per turn.
     */
    public int energyGeneration;

    /**
     * Amount of energy needed for the bodyPart to function.
     */
    public int neededEnergy;

    /**
     * The maximum health of the bodyPart. Determines how much damage the bodyPart can take before being destroyed.
     */
    public int maxHealth;

    /**
     * The current health of the bodyPart. It can't normally be higher than maxHealth and if it's equal to 0 the bodyPart
     * is destroyed.
     */
    public int currentHealth;

    /**
     * The amount of health this bodyPart generates per amount of time.
     */
    public int regenRate;

    /**
     * The amount of health this bodyPart can regenerate from. If health is lower than this amount,
     * it can usually not regenerate without help.
     */
    public int regenLimit;

    /**
     * The armour or resilience of the bodyPart. A higher armour rating makes incoming hits less likely to deal full
     * or any damage.
     */
    public int armour;

    /**
     * The size of the bodyPart. The bigger the size, the more likely a bodyPart is going to be hit compared to a
     * smaller one.
     */
    public int size;

    /**
     * This signifies how many organs fit inside this bodyPart
     */
    public int organCapacity;

    /**
     * This calculates how much this bodyPart increases the speed of its person.
     */
    public int speedModifier;

    /**
     * How much this bodyPart can "think". A person with no consciousness is considered dead.
     */
    public int consciousness;

    /**
     * How much this bodyPart can grab. Used for holding weapons.
     */
    public int grabbingSlots;

    /**
     * How much this bodyPart can see.
     */
    public int sightModifier;

    /**
     * This is used to generate the bodyPart.
     * It takes on the typical values of the bodyPart. These values are manipulated by the bias and a randomness.
     *
     * @param bias improves (if bias is positive) or worsens (if bias is negative) the stats and attribute of the body part.
     *             The bias is expressed as a present. So a bias of '10' would increase the bodyParts stats by 10%.
     * @param randomness changes the stats of the bodyPart by a random amount (both positive and negative).
     *                   The greater the value, the stronger the random drift.
     */
    public void generateBodyPart(List<String> data, int bias, int randomness)
    {
        name = data.get(0);
        type = BodyLogicHelper.calculateBodyPartStat(Integer.parseInt(data.get(1)),bias,randomness);
        bodyPartClass = data.get(2);
        bloodCapacity = BodyLogicHelper.calculateBodyPartStat(Integer.parseInt(data.get(3)),bias,randomness);
        bloodGeneration = BodyLogicHelper.calculateBodyPartStat(Integer.parseInt(data.get(4)),bias,randomness);
        neededBlood = BodyLogicHelper.calculateBodyPartStat(Integer.parseInt(data.get(5)),bias,randomness);
        energyCapacity = BodyLogicHelper.calculateBodyPartStat(Integer.parseInt(data.get(6)),bias,randomness);
        energyGeneration = BodyLogicHelper.calculateBodyPartStat(Integer.parseInt(data.get(7)),bias,randomness);
        neededEnergy = BodyLogicHelper.calculateBodyPartStat(Integer.parseInt(data.get(8)),bias,randomness);
        maxHealth = BodyLogicHelper.calculateBodyPartStat(Integer.parseInt(data.get(9)),bias,randomness);
        regenRate = BodyLogicHelper.calculateBodyPartStat(Integer.parseInt(data.get(10)),bias,randomness);
        regenLimit = BodyLogicHelper.calculateBodyPartStat(Integer.parseInt(data.get(11)),bias,randomness);
        armour = BodyLogicHelper.calculateBodyPartStat(Integer.parseInt(data.get(12)),bias,randomness);
        size = BodyLogicHelper.calculateBodyPartStat(Integer.parseInt(data.get(13)),bias,randomness);
        organCapacity = BodyLogicHelper.calculateBodyPartStat(Integer.parseInt(data.get(14)),bias,randomness);
        speedModifier = BodyLogicHelper.calculateBodyPartStat(Integer.parseInt(data.get(15)),bias,randomness);
        consciousness = BodyLogicHelper.calculateBodyPartStat(Integer.parseInt(data.get(16)),bias,randomness);
        grabbingSlots = BodyLogicHelper.calculateBodyPartStat(Integer.parseInt(data.get(17)),bias,randomness);
        sightModifier = BodyLogicHelper.calculateBodyPartStat(Integer.parseInt(data.get(18)),bias,randomness);

        currentHealth = maxHealth;
    }

    /**
     * This function will deal a certain amount of damage to a bodyPart.
     *
     * @param damage the amount of damage to be dealt to the bodyPart
     */
    public void doDamage(int damage)
    {
        this.currentHealth = this.currentHealth -damage;
        if (this.currentHealth <= 0)
        {
            removeBodyPart();
        }
    }

    /**
     * This function will heal the bodyPart by the amount specified in the bodyPart.
     */
    public void healDamage()
    {
        if (currentHealth < maxHealth && currentHealth > regenLimit)
        {
            currentHealth += regenRate;
        }
    }

    /**
     * This function attaches this bodyPart to the given bodyPart and updates the new parents attachedBodyPart list
     * and updates the myPerson.
     *
     * @param bodyPartToAttachTo the bodyPart this bodyPart should be attached to.
     */
    public void attachTo(BodyPart bodyPartToAttachTo)
    {
        this.bodyPartAttachedTo = bodyPartToAttachTo;
        this.myPerson = this.bodyPartAttachedTo.myPerson;
        this.myPerson.myBodyParts.add(this);
        this.bodyPartAttachedTo.attachedBodyParts.add(this);
        updatePersonWhenAttached();
    }

    /**
     * This function makes sure all the stats and attributes of the person this bodyPart is attached to, are updated
     * according to the bodyParts own stats.
     */
    public void updatePersonWhenAttached()
    {
        myPerson.grossBloodCapacity += bloodCapacity;
        myPerson.grossBloodGeneration += bloodGeneration;
        myPerson.grossBloodNeeded += neededBlood;
        myPerson.grossEnergyCapacity += energyCapacity;
        myPerson.grossEnergyGeneration += energyGeneration;
        myPerson.grossEnergyNeeded += neededEnergy;
        myPerson.grossSize += size;
        myPerson.grossSpeedModifier += speedModifier;
        myPerson.grossConsciousness += consciousness;
        myPerson.grossSight += sightModifier;
    }

    /**
     * This function removes this bodyPart from the person. All bodyParts attached to this bodyPart stay attached
     * to this bodyPart and are removed from the person.
     * The bodyPart is replaced with a grievous wound.
     */
    public void removeBodyPart()
    {
        BodyPart resultingWound = BodyFileDecoder.loadBodyPartFromFile("Resources/BodyParts/Misc/GrievousWound",0,20);
        resultingWound.attachTo(bodyPartAttachedTo);
        bodyPartAttachedTo.attachedBodyParts.remove(this);
        bodyPartAttachedTo = null;

        removeBodyPartRecursively();
    }

    /**
     * This function travels recursively through all the attached bodyParts and removes them from the old Person.
     */
    private void removeBodyPartRecursively()
    {
        this.myPerson.myBodyParts.remove(this);
        updatePersonWhenRemoved();
        this.myPerson = null;
        for (BodyPart attachedBodyPart : this.attachedBodyParts)
        {
            attachedBodyPart.removeBodyPartRecursively();
        }
    }

    /**
     * This function makes sure all the stats and attributes of the person this bodyPart is removed from, are updated
     * according to the bodyParts own stats.
     */
    public void updatePersonWhenRemoved()
    {
        myPerson.grossBloodCapacity -= bloodCapacity;
        myPerson.grossBloodGeneration -= bloodGeneration;
        myPerson.grossBloodNeeded -= neededBlood;
        myPerson.grossEnergyCapacity -= energyCapacity;
        myPerson.grossEnergyGeneration -= energyGeneration;
        myPerson.grossEnergyNeeded -= neededEnergy;
        myPerson.grossSize -= size;
        myPerson.grossSpeedModifier -= speedModifier;
        myPerson.grossConsciousness -= consciousness;
        myPerson.grossSight -= sightModifier;
    }

    /**
     * This function prints out the bodyPart stats for debugging;
     */
    public void printBodyPartToTerminal()
    {
        System.out.println("# Name: " + name);
        System.out.println("    Number of body parts attached: " + attachedBodyParts.size());
        System.out.println("    Type: " + type);
        System.out.println("    Class: " + bodyPartClass);
        System.out.println("    Blood capacity: " + bloodCapacity);
        System.out.println("    Blood generation: " + bloodGeneration);
        System.out.println("    Blood needed: " + neededBlood);
        System.out.println("    Energy capacity: " + energyCapacity);
        System.out.println("    Energy generation: " + energyGeneration);
        System.out.println("    Energy needed: " + neededEnergy);
        System.out.println("    Health: " + currentHealth + "/" + maxHealth);
        System.out.println("    RegenRate: " + regenRate);
        System.out.println("    RegenLimit: " + regenLimit);
        System.out.println("    Armour: " + armour);
        System.out.println("    Size: " + size);
        System.out.println("    Organ capacity: " + organCapacity);
        System.out.println("    Speed: " + speedModifier);
        System.out.println("    Consciousness: " + consciousness);
        System.out.println("    Grabbing slots: " + grabbingSlots);
        System.out.println("    Sight: " + sightModifier);
    }

    public void changeName(String newName)
    {
        name = newName;
    }
}
