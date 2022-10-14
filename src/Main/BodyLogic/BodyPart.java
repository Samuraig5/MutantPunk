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
    private final float[] bloodCapacity = {0,1,0};

    /**
     * Amount of blood this bodyPart generates per time unit.
     */
    private final float[] bloodGeneration = {0,1,0};

    /**
     * Amount of blood needed for the bodyPart to function.
     */
    private final float[] neededBlood = {0,1,0};

    /**
     * Amount of energy this bodyPart can store.
     */
    private final float[] energyCapacity = {0,1,0};

    /**
     * Amount of energy this bodyPart generates per turn.
     */
    private final float[] energyGeneration = {0,1,0};

    /**
     * Amount of energy needed for the bodyPart to function.
     */
    private final float[] neededEnergy = {0,1,0};

    /**
     * The maximum health of the bodyPart. Determines how much damage the bodyPart can take before being destroyed.
     */
    private final float[] maxHealth = {0,1,0};

    /**
     * The current health of the bodyPart. It can't normally be higher than maxHealth and if it's equal to 0 the bodyPart
     * is destroyed.
     */
    private float currentHealth;

    /**
     * The amount of health this bodyPart generates per amount of time.
     */
    private final float[] regenRate = {0,1,0};

    /**
     * The amount of health this bodyPart can regenerate from. If health is lower than this amount,
     * it can usually not regenerate without help.
     */
    private final float[] regenLimit = {0,1,0};

    /**
     * The armour or resilience of the bodyPart. A higher armour rating makes incoming hits less likely to deal full
     * or any damage.
     */
    private final float[] armour = {0,1,0};

    /**
     * The size of the bodyPart. The bigger the size, the more likely a bodyPart is going to be hit compared to a
     * smaller one.
     */
    private final float[] size = {0,1,0};

    /**
     * This signifies how many organs fit inside this bodyPart
     */
    private final float[] organCapacity = {0,1,0};

    /**
     * How much space the bodyPart still has for internal bodyParts. If this value is less than the internal's size,
     * the internal can't be attached to it.
     */
    private float currentOrganCapacity;

    /**
     * This calculates how much this bodyPart increases the speed of its person.
     */
    private final float[] speedModifier = {0,1,0};

    /**
     * How much this bodyPart can "think". A person with no consciousness is considered dead.
     */
    private final float[] consciousness = {0,1,0};

    /**
     * How much this bodyPart can grab. Used for holding weapons.
     */
    private final float[] grabbingSlots = {0,1,0};

    /**
     * How much this bodyPart can see.
     */
    private final float[] sightModifier = {0,1,0};

    /**
     * This is used to generate the bodyPart.
     * It takes on the typical values of the bodyPart. These values are manipulated by the bias and a randomness.
     *
     * @param bias improves (if bias is positive) or worsens (if bias is negative) the stats and attribute of the body part.
     *             The bias is expressed as a present. So a bias of '10' would increase the bodyParts stats by 10%.
     * @param randomness changes the stats of the bodyPart by a random amount (both positive and negative).
     *                   The greater the value, the stronger the random drift.
     */
    public void generateBodyPart(List<String[]> data, int bias, int randomness)
    {
        name = data.get(0)[0];
        type = Integer.parseInt(data.get(1)[0]);
        bodyPartClass = data.get(2)[0];

        float[] standardBloodCapacity = BodyLogicHelper.calculateBodyPartStat(data.get(3),bias,randomness);
        AddToBloodCapacity(standardBloodCapacity[0], standardBloodCapacity[1]);
        float[] standardBloodGeneration = BodyLogicHelper.calculateBodyPartStat(data.get(4),bias,randomness);
        AddToBloodGeneration(standardBloodGeneration[0], standardBloodGeneration[1]);
        float[] standardBloodNeed = BodyLogicHelper.calculateBodyPartStat(data.get(5),bias,randomness);
        AddToBloodNeeded(standardBloodNeed[0], standardBloodNeed[1]);
        float[] standardEnergyCapacity = BodyLogicHelper.calculateBodyPartStat(data.get(6),bias,randomness);
        AddToEnergyCapacity(standardEnergyCapacity[0], standardEnergyCapacity[1]);
        float[] standardEnergyGeneration = BodyLogicHelper.calculateBodyPartStat(data.get(7),bias,randomness);
        AddToEnergyGeneration(standardEnergyGeneration[0], standardEnergyGeneration[1]);
        float[] standardEnergyNeed = BodyLogicHelper.calculateBodyPartStat(data.get(8),bias,randomness);
        AddToEnergyNeeded(standardEnergyNeed[0], standardEnergyNeed[1]);
        float[] standardHealth = BodyLogicHelper.calculateBodyPartStat(data.get(9),bias,randomness);
        AddToMaxHealth(standardHealth[0], standardHealth[1]);
        float[] standardRegenRate = BodyLogicHelper.calculateBodyPartStat(data.get(10),bias,randomness);
        AddToRegenRate(standardRegenRate[0], standardRegenRate[1]);
        float[] standardRegenLimit = BodyLogicHelper.calculateBodyPartStat(data.get(11),bias,randomness);
        AddToRegenLimit(standardRegenLimit[0], standardRegenLimit[1]);
        float[] standardArmour = BodyLogicHelper.calculateBodyPartStat(data.get(12),bias,randomness);
        AddToArmour(standardArmour[0], standardArmour[1]);
        float[] standardSize = BodyLogicHelper.calculateBodyPartStat(data.get(13),bias,randomness);
        AddToSize(standardSize[0], standardSize[1]);
        float[] standardOrganCapacity = BodyLogicHelper.calculateBodyPartStat(data.get(14),bias,randomness);
        AddToOrganCapacity(standardOrganCapacity[0], standardOrganCapacity[1]);
        float[] standardSpeed = BodyLogicHelper.calculateBodyPartStat(data.get(15),bias,randomness);
        AddToSpeed(standardSpeed[0], standardSpeed[1]);
        float[] standardConsciousness = BodyLogicHelper.calculateBodyPartStat(data.get(16),bias,randomness);
        AddToConsciousness(standardConsciousness[0], standardConsciousness[1]);
        float[] standardGrabbingSlots = BodyLogicHelper.calculateBodyPartStat(data.get(17),bias,randomness);
        AddToGrabbingSlots(standardGrabbingSlots[0], standardGrabbingSlots[1]);
        float[] standardSight = BodyLogicHelper.calculateBodyPartStat(data.get(18),bias,randomness);
        AddToSight(standardSight[0], standardSight[1]);

        currentHealth = maxHealth[2];
        currentOrganCapacity = organCapacity[2];
    }

    /**
     * This function will deal a certain amount of damage to a bodyPart.
     *
     * @param damage the amount of damage to be dealt to the bodyPart
     */
    public void doDamage(float damage)
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
    public void regenerateDamage()
    {
        if (currentHealth < maxHealth[2] && currentHealth > regenLimit[2])
        {
            if((maxHealth[2]-currentHealth)<regenRate[2])
            {
                currentHealth = maxHealth[2];
            }
            else
            {
                currentHealth += regenRate[2];
            }
        }
    }

    /**
     * This function attaches this bodyPart to the given bodyPart and updates the new parents attachedBodyPart list
     * and updates the myPerson.
     *
     * @param bodyPartToAttachTo the bodyPart this bodyPart should be attached to.
     * @return weather or not attaching the bodyPart was successful.
     */
    public boolean TryToAttachTo(BodyPart bodyPartToAttachTo)
    {
        if(this.bodyPartClass.equalsIgnoreCase("internal"))
        {
            if(bodyPartToAttachTo.getCurrentOrganCapacity() >= this.getSize()[2])
            {
                bodyPartToAttachTo.AddToCurrentOrganCapacity(this.getSize()[2]);
                return attach(bodyPartToAttachTo);
            }
            else
            {
                System.out.println("BodyPart -> Failed to attach internal because it's too big!");
                return false;
            }
        }
        else
        {
            return attach(bodyPartToAttachTo);
        }
    }
    private boolean attach(BodyPart bodyPartToAttachTo)
    {
        this.bodyPartAttachedTo = bodyPartToAttachTo;
        this.myPerson = this.bodyPartAttachedTo.myPerson;
        this.myPerson.myBodyParts.add(this);
        this.bodyPartAttachedTo.attachedBodyParts.add(this);
        updatePersonWhenAttached();
        return true;
    }

    /**
     * This function removes this bodyPart from the person. All bodyParts attached to this bodyPart stay attached
     * to this bodyPart and are removed from the person.
     * The bodyPart is replaced with a grievous wound.
     */
    public void removeBodyPart()
    {
        if(this.bodyPartClass.equalsIgnoreCase("internal"))
        {
            this.bodyPartAttachedTo.AddToCurrentOrganCapacity(this.getSize()[2]);
        }
        removeBodyPartRecursively();
        BodyPart resultingWound = BodyFileDecoder.loadBodyPartFromFile("Resources/BodyParts/Misc/GrievousWound",0,20);
        resultingWound.TryToAttachTo(bodyPartAttachedTo);
        bodyPartAttachedTo.attachedBodyParts.remove(this);
        bodyPartAttachedTo = null;
    }

    /**
     * This function travels recursively through all the attached bodyParts and removes them from the old Person.
     */
    private void removeBodyPartRecursively()
    {
        for (BodyPart attachedBodyPart : this.attachedBodyParts)
        {
            attachedBodyPart.removeBodyPartRecursively();
        }
        this.myPerson.myBodyParts.remove(this);
        updatePersonWhenRemoved();
        this.myPerson = null;
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

    public float[] getBloodCapacity() {return bloodCapacity;}
    public float[] getBloodGeneration() {return bloodGeneration;}
    public float[] getBloodNeeded() {return neededBlood;}
    public float[] getEnergyCapacity() {return energyCapacity;}
    public float[] getEnergyGeneration() {return energyGeneration;}
    public float[] getEnergyNeeded() {return neededEnergy;}
    public float[] getMaxHealth(){return maxHealth;}
    public float getCurrentHealth(){return currentHealth;}
    public float[] getRegenLimit(){return regenLimit;}
    public float[] getRegenRate(){return regenRate;}
    public float[] getArmour() {return armour;}
    public float[] getSize() {return size;}
    public float[] getConsciousness() {return consciousness;}
    public float[] getOrganCapacity() {return organCapacity;}
    public float getCurrentOrganCapacity() {return currentOrganCapacity;}
    public float[] getSpeed() {return speedModifier;}
    public float[] getSight() {return sightModifier;}
    public float[] getGrabbingSlots() {return grabbingSlots;}

    public void AddToCurrentOrganCapacity(float change) {currentOrganCapacity = currentOrganCapacity + change;}

    public void AddToBloodCapacity(float grossChange, float modifierChange)
    {
        bloodCapacity[0] = bloodCapacity[0] + grossChange;
        bloodCapacity[1] = bloodCapacity[1] + modifierChange;
        bloodCapacity[2] = bloodCapacity[0] * bloodCapacity[1];
    }
    public void AddToBloodGeneration(float grossChange, float modifierChange)
    {
        bloodGeneration[0] = bloodGeneration[0] + grossChange;
        bloodGeneration[1] = bloodGeneration[1] + modifierChange;
        bloodGeneration[2] = bloodGeneration[0] * bloodGeneration[1];
    }
    public void AddToBloodNeeded(float grossChange, float modifierChange)
    {
        neededBlood[0] = neededBlood[0] + grossChange;
        neededBlood[1] = neededBlood[1] + modifierChange;
        neededBlood[2] = neededBlood[0] * neededBlood[1];
    }
    public void AddToEnergyCapacity(float grossChange, float modifierChange)
    {
        energyCapacity[0] = energyCapacity[0] + grossChange;
        energyCapacity[1] = energyCapacity[1] + modifierChange;
        energyCapacity[2] = energyCapacity[0] * energyCapacity[1];
    }
    public void AddToEnergyGeneration(float grossChange, float modifierChange)
    {
        energyGeneration[0] = energyGeneration[0] + grossChange;
        energyGeneration[1] = energyGeneration[1] + modifierChange;
        energyGeneration[2] = energyGeneration[0] * energyGeneration[1];
    }
    public void AddToEnergyNeeded(float grossChange, float modifierChange)
    {
        neededEnergy[0] = neededEnergy[0] + grossChange;
        neededEnergy[1] = neededEnergy[1] + modifierChange;
        neededEnergy[2] = neededEnergy[0] * neededEnergy[1];
    }
    public void AddToMaxHealth(float grossChange, float modifierChange)
    {
        maxHealth[0] = maxHealth[0] + grossChange;
        maxHealth[1] = maxHealth[1] + modifierChange;
        maxHealth[2] = maxHealth[0] * maxHealth[1];
    }
    public void AddToRegenLimit(float grossChange, float modifierChange)
    {
        regenLimit[0] = regenLimit[0] + grossChange;
        regenLimit[1] = regenLimit[1] + modifierChange;
        regenLimit[2] = regenLimit[0] * regenLimit[1];
    }
    public void AddToRegenRate(float grossChange, float modifierChange)
    {
        regenRate[0] = regenRate[0] + grossChange;
        regenRate[1] = regenRate[1] + modifierChange;
        regenRate[2] = regenRate[0] * regenRate[1];
    }
    public void AddToArmour(float grossChange, float modifierChange)
    {
        armour[0] = armour[0] + grossChange;
        armour[1] = armour[1] + modifierChange;
        armour[2] = armour[0] * armour[1];
    }
    public void AddToSize(float grossChange, float modifierChange)
    {
        size[0] = size[0] + grossChange;
        size[1] = size[1] + modifierChange;
        size[2] = size[0] * size[1];
    }
    public void AddToOrganCapacity(float grossChange, float modifierChange)
    {
        organCapacity[0] = organCapacity[0] + grossChange;
        organCapacity[1] = organCapacity[1] + modifierChange;
        organCapacity[2] = organCapacity[0] * organCapacity[1];
    }
    public void AddToSpeed(float grossChange, float modifierChange)
    {
        speedModifier[0] = speedModifier[0] + grossChange;
        speedModifier[1] = speedModifier[1] + modifierChange;
        speedModifier[2] = speedModifier[0] * speedModifier[1];
    }
    public void AddToConsciousness(float grossChange, float modifierChange)
    {
        consciousness[0] = consciousness[0] + grossChange;
        consciousness[1] = consciousness[1] + modifierChange;
        consciousness[2] = consciousness[0] * consciousness[1];
    }
    public void AddToGrabbingSlots(float grossChange, float modifierChange)
    {
        grabbingSlots[0] = grabbingSlots[0] + grossChange;
        grabbingSlots[1] = grabbingSlots[1] + modifierChange;
        grabbingSlots[2] = grabbingSlots[0] * grabbingSlots[1];
    }
    public void AddToSight(float grossChange, float modifierChange)
    {
        sightModifier[0] = sightModifier[0] + grossChange;
        sightModifier[1] = sightModifier[1] + modifierChange;
        sightModifier[2] = sightModifier[0] * sightModifier[1];
    }
}
