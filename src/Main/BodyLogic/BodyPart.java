package Main.BodyLogic;

import Main.WorldLogic.Person;

import java.util.List;

public abstract class BodyPart
{
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
    public List<BodyPart> attachedBodyParts;

    /**
     * This is an index that signifies which type the body part it.
     *  1-Organic
     *  2-Cybernetic
     */
    public int type;

    /**
     * This is keeping track of the class of the bodyPart like 'arm', 'heart', 'skin', 'implant' and so on.
     *
     * List of
     */
    public String bodyPartClass;

    /**
     * Amount of blood needed for the bodyPart to function.
     */
    public int neededBlood;

    /**
     * Amount of blood this bodyPart generates per time unit.
     */
    public int bloodGeneration;

    /**
     * Amount of energy needed for the bodyPart to function.
     */
    public int neededEnergy;

    /**
     * The health of the bodyPart. Determines how much damage the bodyPart can take before being destroyed.
     */
    public int health;

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
     * This is used to instantiate the bodyPart.
     * It takes on the typical values of the bodyPart. These values are manipulated by the bias and a randomness.
     *
     * @param bias improves (if bias is positive) or worsens (if bias is negative) the stats and attribute of the body part.
     *             The bias is expressed as a present. So a bias of '10' would increase the bodyParts stats by 10%.
     * @param randomness changes the stats of the bodyPart by a random amount (both positive and negative).
     *                   The greater the value, the stronger the random drift.
     */
    abstract public void instantiateBodyPart(int bias, int randomness);

    /**
     * This function will deal a certain amount of damage to a bodyPart.
     *
     * @param damage the amount of damage to be dealt to the bodyPart
     */
    abstract public void doDamage(int damage);

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
    abstract public void updatePersonWhenAttached();

    /**
     * This function removes this bodyPart from the person. All bodyParts attached to this bodyPart stay attached
     * to this bodyPart and are removed from the person.
     * The bodyPart is replaced with a grievous wound.
     */
    public void removeBodyPart()
    {
        GrievousWound resultingWound = new GrievousWound();
        resultingWound.attachTo(bodyPartAttachedTo);
        this.bodyPartAttachedTo = null;
    }

    /**
     * This function makes sure all the stats and attributes of the person this bodyPart is removed from, are updated
     * according to the bodyParts own stats.
     */
    abstract public void updatePersonWhenRemoved();

    /**
     * This function travels recursively through all the attached bodyParts and removes them from the old Person.
     */
    public void removeBodyPartFromBody()
    {
        this.myPerson.myBodyParts.remove(this);
        updatePersonWhenRemoved();
        this.myPerson = null;
        for (BodyPart attachedBodyPart : this.attachedBodyParts)
        {
            attachedBodyPart.removeBodyPartFromBody();
        }
    }
}
