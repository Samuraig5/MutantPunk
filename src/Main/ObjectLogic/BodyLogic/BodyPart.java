package Main.ObjectLogic.BodyLogic;

import Main.ErrorHandler;
import Main.MathHelper;
import Main.ObjectLogic.ObjectTag;
import Main.Settings;

import java.awt.*;
import java.util.*;
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
    private BodyPart parentBodyPart;

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
    final private BodyPartStat myStats;
    private List<BodyPartAbility> abilities = new ArrayList<>();

    private int bloodConsumeCooldown = 0;

    public BodyPart()
    {
        myStats = new BodyPartStat(this);
    }

    /**
     * This is used to generate the bodyPart.
     * It takes on the typical values of the bodyPart. These values are manipulated by the bias and a randomness.
     *
     * @param bias improves (if bias is positive) or worsens (if bias is negative) the stats and attribute of the body part.
     *             The bias is expressed as a present. So a bias of '10' would increase the bodyParts stats by 10%.
     * @param randomness changes the stats of the bodyPart by a random amount (both positive and negative).
     *                   The greater the value, the stronger the random drift.
     */
    public void generateBodyPart(List<String[]> data, List<String[]> abilities, int bias, int randomness)
    {
        name = data.get(0)[0];
        type = Integer.parseInt(data.get(1)[0]);
        bodyPartClass = data.get(2)[0];

        float[][] calculatedStats = new float[myStats.STATS_NUM][myStats.MODS_NUM];

        for (int i = 0; i < myStats.STATS_NUM; i++)
        {
            calculatedStats[i] = BodyLogicHelper.calculateBodyPartStat(data.get(i+3),bias,randomness);
        }

        myStats.setStats(calculatedStats);

        myStats.changeHealth(getStats()[BodyPartStat.MAX_HEALTH]);
        myStats.changeBloodLevel(getStats()[BodyPartStat.BLOOD_CAPACITY]);

        addAbility(abilities);
    }
    private void addAbility(List<String[]> abilities)
    {
        for (int i = 0; i < abilities.size(); i++) {
            int capacity = 0;
            int efficiency = 0;
            String abilityName = abilities.get(i)[0];
            AbilityTag abilityTag = AbilityTag.translateStringToTag(abilities.get(i)[1]);

            String[] objTagStrings = new String[abilities.get(i).length-2];
            for (int j = 0; j < abilities.get(i).length-2; j++)
            {
                String[] s = abilities.get(i)[j+2].split("#");
                if (s.length == 1)
                {
                    objTagStrings[j] = s[0];
                }
                else
                {
                    if (Objects.equals(s[0], "CAPACITY"))
                    {
                        capacity = Integer.parseInt(s[1]);
                    }
                    else if (Objects.equals(s[0], "EFFICIENCY"))
                    {
                        efficiency = Integer.parseInt(s[1]);
                    }
                }
            }

            ObjectTag[] objectTags = ObjectTag.translateStringToTag(objTagStrings);

            BodyPartAbility ability = new BodyPartAbility(this, abilityName, abilityTag, objectTags);
            if (capacity != 0) {ability.setCapacity(capacity);}
            if (efficiency != 0) {ability.setEfficiency(efficiency);}
            addAbility(ability);
        }
    }

    public List<BodyPartAbility> getAbilities() {return abilities;}
    public void addAbility(BodyPartAbility ability) {abilities.add(ability);}

    public boolean tryToAttach(BodyPart newChild)
    {
        if(getRemainingAttachmentCapacity() >= newChild.getStats()[BodyPartStat.SIZE])
        {
            newChild.setMyPerson(getMyPerson());
            attachedBodyParts.add(newChild);
            newChild.parentBodyPart = this;
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * This function removes this bodyPart from the person. All bodyParts attached to this bodyPart stay attached
     * to this bodyPart and are removed from the person.
     * The bodyPart is replaced with a grievous wound.
     */
    public void removeBodyPart()
    {
        removeBodyPartRecursively();
        BodyPart resultingWound = BodyFileDecoder.loadBodyPartFromFile("Resources/BodyParts/Misc/GrievousWound",0,20);
        parentBodyPart.tryToAttach(resultingWound);
        parentBodyPart.attachedBodyParts.remove(this);
        parentBodyPart = null;
    }

    /**
     * This function travels recursively through all the attached bodyParts and removes them from the old Person.
     */
    private void removeBodyPartRecursively()
    {
        //TODO: The stats of Children of a removed bodyParts seem not to be removed from the person
        for (BodyPart attachedBodyPart : this.attachedBodyParts)
        {
            attachedBodyPart.removeBodyPartRecursively();
        }
        this.myPerson.myBodyParts.remove(this);
        this.myPerson = null;
    }

    /**
     * This function will deal a certain amount of damage to a bodyPart.
     *
     * @param damage the amount of damage to be dealt to the bodyPart
     */
    public void doDamage(float damage)
    {
        myStats.changeHealth(-damage);
        if (myStats.getCurrentHealth() <= 0)
        {
            //removeBodyPart();
        }
    }

    /**
     * This function will heal the bodyPart by the amount specified in the bodyPart.
     */
    public void regenerateDamage()
    {
        myStats.regenerateHealth();
    }

    public float getBloodLevel() {return myStats.getBloodLevel();}
    public void changeBloodLevels(float change)
    {
        myStats.changeBloodLevel(change);
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
    public BodyPart getParentBodyPart(){return parentBodyPart;}
    public Person getMyPerson(){return myPerson;}
    public void setMyPerson(Person newPerson)
    {
        myPerson = newPerson;
        if (!newPerson.myBodyParts.contains(this))
        {
            newPerson.myBodyParts.add(this);
        }
    }
    public float getCurrentHealth() {return myStats.getCurrentHealth();}
    public boolean isAlive() {if (getCurrentHealth() > 0) {return true;} else {return false;}}
    public float getRemainingAttachmentCapacity(){return myStats.getRemainingAttachmentCapacity();}

    public float[] getUpstreamGrossStat()
    {
        return myStats.getUpstreamGrossStat();
    }
    public float[] getUpstreamStatModifier()
    {
        return myStats.getUpstreamStatModifier();
    }

    public float[][] getRawStats()
    {
        return myStats.getRawStats();
    }
    public float[] getStats()
    {
        return myStats.getNetStats();
    }

    public Color getColourBasedOnHealth()
    {
        float curr = getCurrentHealth();
        float max = getStats()[BodyPartStat.MAX_HEALTH];
        if (curr > (max/2))
        {
            return Color.green;
        }
        else if (curr > (max/4))
        {
            return Color.yellow;
        }
        else if (curr > 0)
        {
            return Color.red;
        }
        else
        {
            return Color.darkGray;
        }
    }

    private void spreadBlood() {
        List<BodyPart> neighbours = new ArrayList<>();
        neighbours.add(parentBodyPart);
        for (BodyPart child : attachedBodyParts) {
            neighbours.add(child);
        }
        Collections.shuffle(neighbours);
        for (BodyPart neighbour : neighbours)
        {
            if (neighbour == null) {continue;}
            if (!neighbour.isAlive()) {continue;}
            if (neighbour.getBloodLevel() >= neighbour.getStats()[BodyPartStat.BLOOD_CAPACITY]){continue;}
            if (getBloodLevel() > getStats()[BodyPartStat.BLOOD_NEED] && //Only transfer if bp has enough blood & neighbour's fill level is lower
                    (getBloodLevel()/getStats()[BodyPartStat.BLOOD_CAPACITY]) >
                            neighbour.getBloodLevel()/neighbour.getStats()[BodyPartStat.BLOOD_CAPACITY])
            {
                float change = getBloodLevel() - neighbour.getBloodLevel();
                float retainment = change - getStats()[BodyPartStat.BLOOD_NEED];
                float maxTransfer = neighbour.getStats()[BodyPartStat.BLOOD_CAPACITY] - neighbour.getBloodLevel();
                change = MathHelper.clamp(change,0, retainment);
                change = MathHelper.clamp(change,0, maxTransfer);
                change = change * 0.1f;
                changeBloodLevels(-change);
                neighbour.changeBloodLevels(change);
            }
        }
    }

    public void update()
    {
        bloodConsumeCooldown += Settings.actionPointsPerTick;
        int consumeCost = 1000;
        if (bloodConsumeCooldown > consumeCost)
        {
            bloodConsumeCooldown -= consumeCost;
            myStats.consumeBlood();
            if (myStats.getBloodLevel() < getStats()[BodyPartStat.BLOOD_NEED])
            {
                float difference = myStats.getBloodLevel() - getStats()[BodyPartStat.BLOOD_NEED];
                doDamage(-difference);
            }
        }
        for (BodyPartAbility bpa:abilities) {
            bpa.update();
        }
        spreadBlood();
    }
}
