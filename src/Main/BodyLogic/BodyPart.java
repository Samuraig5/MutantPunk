package Main.BodyLogic;

import java.util.ArrayList;
import java.util.List;

public class BodyPart
{
    /**
     * The (nick-)name of this specific bodyPart.
     */
    private String name;

    /**
     * This keeps track of the person this bodyPart is attached to.
     * If the body part is cut off, it is equal to 'null'
     */
    private Person myPerson;

    /**
     * This is the bodyPart this bodyPart is attached to.
     */
    private BodyPart bodyPartAttachedTo;

    /**
     * These are the bodyParts attacked to this bodyPart.
     */
    private final List<BodyPart> attachedBodyParts = new ArrayList<>();

    /**
     * This is an index that signifies which type the body part it.
     *  1-Organic
     *  2-Cybernetic
     */
    private int type;

    /**
     * This is keeping track of the class of the bodyPart like 'arm', 'heart', 'skin', 'implant' and so on.
     */
    private String bodyPartClass;

    /**
     * Amount of blood that can be stored in this bodyPart
     */
    private final int[] bloodCapacity = {0,1,0};

    /**
     * Amount of blood this bodyPart generates per time unit.
     */
    private final int[] bloodGeneration = {0,1,0};

    /**
     * Amount of blood needed for the bodyPart to function.
     */
    private final int[] neededBlood = {0,1,0};

    /**
     * Amount of energy this bodyPart can store.
     */
    private final int[] energyCapacity = {0,1,0};

    /**
     * Amount of energy this bodyPart generates per turn.
     */
    private final int[] energyGeneration = {0,1,0};

    /**
     * Amount of energy needed for the bodyPart to function.
     */
    private final int[] neededEnergy = {0,1,0};

    /**
     * The maximum health of the bodyPart. Determines how much damage the bodyPart can take before being destroyed.
     */
    private final int[] maxHealth = {0,1,0};

    /**
     * The current health of the bodyPart. It can't normally be higher than maxHealth and if it's equal to 0 the bodyPart
     * is destroyed.
     */
    private int currentHealth;

    /**
     * The amount of health this bodyPart generates per amount of time.
     */
    private final int[] regenRate = {0,1,0};

    /**
     * The amount of health this bodyPart can regenerate from. If health is lower than this amount,
     * it can usually not regenerate without help.
     */
    private final int[] regenLimit = {0,1,0};

    /**
     * The armour or resilience of the bodyPart. A higher armour rating makes incoming hits less likely to deal full
     * or any damage.
     */
    private final int[] armour = {0,1,0};

    /**
     * The size of the bodyPart. The bigger the size, the more likely a bodyPart is going to be hit compared to a
     * smaller one.
     */
    private final int[] size = {0,1,0};

    /**
     * This signifies how many organs fit inside this bodyPart
     */
    private final int[] organCapacity = {0,1,0};

    /**
     * This calculates how much this bodyPart increases the speed of its person.
     */
    private final int[] speedModifier = {0,1,0};

    /**
     * How much this bodyPart can "think". A person with no consciousness is considered dead.
     */
    private final int[] consciousness = {0,1,0};

    /**
     * How much this bodyPart can grab. Used for holding weapons.
     */
    private final int[] grabbingSlots = {0,1,0};

    /**
     * How much this bodyPart can see.
     */
    private final int[] sightModifier = {0,1,0};

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
        bloodCapacity[0] = BodyLogicHelper.calculateBodyPartStat(Integer.parseInt(data.get(3)),bias,randomness);
        bloodGeneration[0] = BodyLogicHelper.calculateBodyPartStat(Integer.parseInt(data.get(4)),bias,randomness);
        neededBlood[0] = BodyLogicHelper.calculateBodyPartStat(Integer.parseInt(data.get(5)),bias,randomness);
        energyCapacity[0] = BodyLogicHelper.calculateBodyPartStat(Integer.parseInt(data.get(6)),bias,randomness);
        energyGeneration[0] = BodyLogicHelper.calculateBodyPartStat(Integer.parseInt(data.get(7)),bias,randomness);
        neededEnergy[0] = BodyLogicHelper.calculateBodyPartStat(Integer.parseInt(data.get(8)),bias,randomness);
        maxHealth[0] = BodyLogicHelper.calculateBodyPartStat(Integer.parseInt(data.get(9)),bias,randomness);
        regenRate[0] = BodyLogicHelper.calculateBodyPartStat(Integer.parseInt(data.get(10)),bias,randomness);
        regenLimit[0] = BodyLogicHelper.calculateBodyPartStat(Integer.parseInt(data.get(11)),bias,randomness);
        armour[0] = BodyLogicHelper.calculateBodyPartStat(Integer.parseInt(data.get(12)),bias,randomness);
        size[0] = BodyLogicHelper.calculateBodyPartStat(Integer.parseInt(data.get(13)),bias,randomness);
        organCapacity[0] = BodyLogicHelper.calculateBodyPartStat(Integer.parseInt(data.get(14)),bias,randomness);
        speedModifier[0] = BodyLogicHelper.calculateBodyPartStat(Integer.parseInt(data.get(15)),bias,randomness);
        consciousness[0] = BodyLogicHelper.calculateBodyPartStat(Integer.parseInt(data.get(16)),bias,randomness);
        grabbingSlots[0] = BodyLogicHelper.calculateBodyPartStat(Integer.parseInt(data.get(17)),bias,randomness);
        sightModifier[0] = BodyLogicHelper.calculateBodyPartStat(Integer.parseInt(data.get(18)),bias,randomness);

        currentHealth = maxHealth[2];
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
        if (currentHealth < maxHealth[2] && currentHealth > regenLimit[2])
        {
            currentHealth += regenRate[2];
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
        myPerson.changeGrossBloodCapacity(bloodCapacity[0]);
        myPerson.changeGrossBloodGeneration(bloodGeneration[0]);
        myPerson.changeGrossBloodNeeded(neededBlood[0]);
        myPerson.changeGrossEnergyCapacity(energyCapacity[0]);
        myPerson.changeGrossEnergyGeneration(energyGeneration[0]);
        myPerson.changeGrossEnergyNeeded(neededEnergy[0]);
        myPerson.changeGrossSize(size[0]);
        myPerson.changeGrossSpeed(speedModifier[0]);
        myPerson.changeGrossConsciousness(consciousness[0]);
        myPerson.changeGrossSight(sightModifier[0]);
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
        myPerson.changeGrossBloodCapacity(-bloodCapacity[0]);
        myPerson.changeGrossBloodGeneration(-bloodGeneration[0]);
        myPerson.changeGrossBloodNeeded(-neededBlood[0]);
        myPerson.changeGrossEnergyCapacity(-energyCapacity[0]);
        myPerson.changeGrossEnergyGeneration(-energyGeneration[0]);
        myPerson.changeGrossEnergyNeeded(-neededEnergy[0]);
        myPerson.changeGrossSize(-size[0]);
        myPerson.changeGrossSpeed(-speedModifier[0]);
        myPerson.changeGrossConsciousness(-consciousness[0]);
        myPerson.changeGrossSight(-sightModifier[0]);
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
        System.out.println("    Blood capacity: " + bloodCapacity[0]);
        System.out.println("    Blood generation: " + bloodGeneration[0]);
        System.out.println("    Blood needed: " + neededBlood[0]);
        System.out.println("    Energy capacity: " + energyCapacity[0]);
        System.out.println("    Energy generation: " + energyGeneration[0]);
        System.out.println("    Energy needed: " + neededEnergy[0]);
        System.out.println("    Health: " + currentHealth + "/" + maxHealth[0]);
        System.out.println("    RegenRate: " + regenRate[0]);
        System.out.println("    RegenLimit: " + regenLimit[0]);
        System.out.println("    Armour: " + armour[0]);
        System.out.println("    Size: " + size[0]);
        System.out.println("    Organ capacity: " + organCapacity[0]);
        System.out.println("    Speed: " + speedModifier[0]);
        System.out.println("    Consciousness: " + consciousness[0]);
        System.out.println("    Grabbing slots: " + grabbingSlots[0]);
        System.out.println("    Sight: " + sightModifier[0]);
    }

    public void changeName(String newName)
    {
        name = newName;
    }
    public String getName()
    {
         return name;
    }
    public List<BodyPart> getAttachedBodyParts(){return attachedBodyParts;}
    public Person getMyPerson(){return myPerson;}
    public void setMyPerson(Person newPerson){myPerson = newPerson;}

    public int[] getMaxHealth(){return maxHealth;}
    public int getCurrentHealth(){return currentHealth;}
}
