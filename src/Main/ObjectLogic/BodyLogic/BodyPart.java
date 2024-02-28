package Main.ObjectLogic.BodyLogic;

import Main.ErrorHandler;

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

    /**
     *  Gross, Modifier, Total, Upstream BodyPart Gross, Upstream BodyPart Modifier, Upstream Person Modifier
     * [0] - Blood Capacity
     * [1] - Blood Generation
     * [2] - Blood Needed
     * [3] - Energy Capacity
     * [4] - Energy Generation
     * [5] - Energy Needed
     * [6] - Max Health
     * [7] - Regen Rate
     * [8] - Regen Limit
     * [9] - Armour
     * [10] - Size
     * [11] - Organ Capacity
     * [12] - Speed
     * [13] - Consciousness
     * [14] - Grabbing Slots
     * [15] - Sight
     */
    final private List<List<float[]>> myStats = new ArrayList<>();
    final private float[][] myTotalStats = new float[16][6];
    final private List<float[]> personStats = new ArrayList<>();
    private float currentHealth = 0;
    final private List<float[]> currentOrganSizes = new ArrayList<>();
    private float currentOrganCapacity = 0;

    public BodyPart()
    {
        for (int i = 0; i < 16; i++) {
            myStats.add(new ArrayList<>());
            myTotalStats[i] = new float[]{0, 0, 0, 0, 0, 0};
        }
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
    public void generateBodyPart(List<String[]> data, int bias, int randomness)
    {
        name = data.get(0)[0];
        type = Integer.parseInt(data.get(1)[0]);
        bodyPartClass = data.get(2)[0];

        float[][] calculatedStats = new float[16][6];

        for (int i = 0; i < 16; i++)
        {
            calculatedStats[i] = rearrangeArray(BodyLogicHelper.calculateBodyPartStat(data.get(i+3),bias,randomness));
        }

        AddToStat(calculatedStats);

        currentHealth = myTotalStats[6][2];
        currentOrganCapacity = myTotalStats[11][2];
    }
    private float[] rearrangeArray(float[] f)
    {
        float[] g = new float[6];
        g[3] = f[0];
        g[4] = f[1];
        return g;
    }

    /**
     * This function will deal a certain amount of damage to a bodyPart.
     *
     * @param damage the amount of damage to be dealt to the bodyPart
     */
    public void doDamage(float damage)
    {
        this.currentHealth = this.currentHealth - damage;
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
        if (currentHealth < myTotalStats[6][2] && currentHealth > myTotalStats[8][2])
        {
            if((myTotalStats[6][2]-currentHealth)<myTotalStats[7][2])
            {
                currentHealth = myTotalStats[6][2];
            }
            else
            {
                currentHealth += myTotalStats[7][2];
            }
        }
    }

    /**
     * This function attaches this bodyPart to the given bodyPart and updates the new parents attachedBodyPart list.
     * THIS FUNCTION DOES NOT UPDATE THE PERSON. THE BodyFileDecoder DOES THAT WHEN LOADING A bodyPart!
     *
     * @param bodyPartToAttachTo the bodyPart this bodyPart should be attached to.
     * @return weather or not attaching the bodyPart was successful.
     */
    public boolean TryToAttachTo(BodyPart bodyPartToAttachTo)
    {
        if(this.bodyPartClass.equalsIgnoreCase("internal"))
        {
            bodyPartToAttachTo.calculateCurrentOrganCapacity();
            if(bodyPartToAttachTo.getCurrentOrganCapacity() >= myTotalStats[10][2])
            {
                bodyPartToAttachTo.calculateCurrentOrganCapacity(myTotalStats[10]);
                return attach(bodyPartToAttachTo);
            }
            else
            {
                ErrorHandler.LogData(true,"Failed to attach internal bodyPart because it's too big!");
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
        parentBodyPart = bodyPartToAttachTo;
        parentBodyPart.attachedBodyParts.add(this);

        if(this.parentBodyPart.myPerson != null)
        {
            myPerson = this.parentBodyPart.myPerson;
            myPerson.myBodyParts.add(this);
        }

        ErrorHandler.LogData(false,"Successfully added " + this.name + " to " + parentBodyPart.name +
                ". The parent now has: " + parentBodyPart.attachedBodyParts.size() + " attached bodyParts and the " +
                "child is attached to: " + bodyPartToAttachTo.name);
        updateParentAndPerson();
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
            this.parentBodyPart.removeOrgansSize(myTotalStats[10]);
        }
        removeBodyPartRecursively();
        BodyPart resultingWound = BodyFileDecoder.loadBodyPartFromFile("Resources/BodyParts/Misc/GrievousWound",0,20);
        resultingWound.TryToAttachTo(parentBodyPart);
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
        updateParentAndPerson();
        this.myPerson = null;
    }

    /**
     * This function makes sure all the stats and modifiers of the person the parent bodyPart are updated when needed.
     */
    List<float[]> pre = personStats;
    public void updateParentAndPerson()
    {
        updateStats();
        if(parentBodyPart != null)
        {
            ErrorHandler.LogData(false,"Updating parent bodyPart " + parentBodyPart.name + " according to the upstream stats");
            parentBodyPart.AddToStat(myTotalStats);
            ErrorHandler.LogData(false," Done Updating parent bodyPart " + parentBodyPart.name);
            parentBodyPart.updateParentAndPerson();
        }
        else
        {
            ErrorHandler.LogData(false,name + " has no parent bodyPart: " + parentBodyPart);
        }
        if(myPerson != null)
        {
            myPerson.RemoveFromStat(personStats);
            ErrorHandler.LogData(false,name + " is Updating person: " + myPerson.getName());
            personStats.removeAll(personStats);
            if(currentHealth > 0)
            {
                personStats.add(myTotalStats[0]); //Blood Capacity
                personStats.add(myTotalStats[1]); //Blood Generation
                personStats.add(myTotalStats[2]); //Blood Needed
                personStats.add(myTotalStats[3]); //Energy Capacity
                personStats.add(myTotalStats[4]); //Energy Generation
                personStats.add(myTotalStats[5]); //Energy Needed
                personStats.add(myTotalStats[10]); //Size
                personStats.add(myTotalStats[12]); //Speed
                personStats.add(myTotalStats[13]); //Consciousness
                personStats.add(myTotalStats[15]); //Sight
            }
            myPerson.AddToStat(personStats);
        }
        else
        {
            ErrorHandler.LogData(false,name + " has no person: " + myPerson);
        }
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
    public List<float[]> getStatsToPerson(){return personStats;}
    public Person getMyPerson(){return myPerson;}
    public void setMyPerson(Person newPerson){myPerson = newPerson;}
    public float getCurrentHealth() {return currentHealth;}
    public float getCurrentOrganCapacity(){return currentOrganCapacity;}
    public void removeOrgansSize(float[] size)
    {
        if(currentOrganSizes.contains(size))
        {
            currentOrganSizes.remove(size);
        }
        calculateCurrentOrganCapacity();
    }
    public void calculateCurrentOrganCapacity(float[] size)
    {
        if(!currentOrganSizes.contains(size))
        {
            currentOrganSizes.add(size);
        }
        calculateCurrentOrganCapacity();
    }
    public void calculateCurrentOrganCapacity()
    {
        currentOrganCapacity = myTotalStats[11][2];
        for (float[] f: currentOrganSizes)
        {
            currentOrganCapacity -= f[2];
        }
    }

    public void AddToStat(float[][] statList)
    {
        for (int i = 0; i < 16; i++) {
            if(!myStats.get(i).contains(statList[i]))
            {
                ErrorHandler.LogData(false,"Added the " + i + "th array to myStats!");
                myStats.get(i).add(statList[i]);
            }
            myTotalStats[i] = new float[]{0, 0, 0, 0, 0, 0};
            for (float[] f: myStats.get(i))
            {
                ErrorHandler.LogData(false,"gross value of the " + i + "th stat before changing: " + myTotalStats[i][0]);
                myTotalStats[i][0] += f[3];
                ErrorHandler.LogData(false,"gross value of the " + i + "th stat: " + myTotalStats[i][0] + ". The change was: " + f[3]);
                myTotalStats[i][1] += f[4];
                myTotalStats[i][2] = myTotalStats[i][0]*myTotalStats[i][1];
            }
        }
    }

    public void updateStats()
    {
        for (int i = 0; i < 16; i++)
        {
            myTotalStats[i] = new float[]{0, 0, 0, 0, 0, 0};
            for (float[] f: myStats.get(i))
            {
                myTotalStats[i][0] += f[3];
                myTotalStats[i][1] += f[4];
                myTotalStats[i][2] = myTotalStats[i][0]*myTotalStats[i][1];
            }
        }
    }

    public float[][] GetMyTotalStats()
    {
        return myTotalStats;
    }

    public void PrintBodyPart()
    {
        ErrorHandler.LogData(false, "===" + name + "===");
        for (int i = 0; i < 16; i++)
        {
            ErrorHandler.LogData(false,i + "th stat: " + myTotalStats[i][0] + " ¦ " + myTotalStats[i][1] + "¦" + myTotalStats[i][2]);
        }
        ErrorHandler.LogData(false, "======");
    }
}
