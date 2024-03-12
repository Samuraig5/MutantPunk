package Main.ObjectLogic.BodyLogic;

import Main.MathHelper;

import java.util.ArrayList;
import java.util.List;

public class BodyPartStat
{
    final static public int STATS_NUM = 16;
    final static public int MODS_NUM = 5;

    final static public int GROSS = 0;
    final static public int MOD = 1;
    final static public int UPSTREAM_GROSS = 2;
    final static public int UPSTREAM_MOD = 3;
    final static public int PERSON_MOD = 4;

    final static public int BLOOD_CAPACITY = 0;
    final static public int BLOOD_GENERATION = 1;
    final static public int BLOOD_NEED = 2;
    final static public int ENERGY_CAPACITY = 3;
    final static public int ENERGY_GENERATION = 4;
    final static public int ENERGY_NEED = 5;
    final static public int MAX_HEALTH = 6;
    final static public int REGEN_RATE = 7;
    final static public int REGEN_LIMIT = 8;
    final static public int ARMOUR = 9;
    final static public int SIZE = 10;
    final static public int ATTACHMENT_CAPACITY = 11;
    final static public int SPEED = 12;
    final static public int CONSCIOUSNESS = 13;
    final static public int GRABBING_SLOTS = 14;
    final static public int SIGHT = 15;


    private BodyPart bodyPart;
    /**
     * [0] Gross, [1] Modifier, [2] Upstream BodyPart Gross, [3] Upstream BodyPart Modifier, [4] Person Modifier
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
     * [11] - Attachment Capacity
     * [12] - Speed
     * [13] - Consciousness
     * [14] - Grabbing Slots
     * [15] - Sight
     */
    private float[][] myStats;

    private float currentHealth;

    public BodyPartStat(BodyPart bp)
    {
        bodyPart = bp;
    }

    public void setStats(float[][] stats)
    {
        myStats = stats;
    }

    public float getCurrentHealth()
    {
        return currentHealth;
    }

    public void changeHealth(float change)
    {
        currentHealth += MathHelper.clamp(currentHealth+change,0, getNetStats()[MAX_HEALTH]);
    }

    public void regenerateHealth()
    {
        if (currentHealth < getNetStats()[REGEN_LIMIT])
        {
            changeHealth(getNetStats()[REGEN_RATE]);
        }
    }

    public float getRemainingAttachmentCapacity()
    {
        float capacity = getNetStats()[ATTACHMENT_CAPACITY];

        for (int i = 0; i < bodyPart.getAttachedBodyParts().size(); i++)
        {
            capacity -= bodyPart.getAttachedBodyParts().get(i).getStats()[SIZE];
        }
        return capacity;
    }

    public float[] getGrossStats()
    {
        float[] grossStats = new float[STATS_NUM];
        for (int i = 0; i < STATS_NUM; i++) {
            grossStats[i] = myStats[i][GROSS];
        }
        return grossStats;
    }

    public float[] getStatModifier()
    {
        float[] statMod = new float[STATS_NUM];
        for (int i = 0; i < STATS_NUM; i++) {
            statMod[i] = myStats[i][MOD];
        }
        return statMod;
    }

    public float[] getUpstreamGrossStat()
    {
        float[] upstreamGrossStat = new float[STATS_NUM];
        for (int i = 0; i < STATS_NUM; i++) {
            upstreamGrossStat[i] = myStats[i][UPSTREAM_GROSS];
        }
        return upstreamGrossStat;
    }
    public float[] getUpstreamStatModifier()
    {
        float[] upstreamStatModifier = new float[STATS_NUM];
        for (int i = 0; i < STATS_NUM; i++) {
            upstreamStatModifier[i] = myStats[i][UPSTREAM_MOD];
        }
        return upstreamStatModifier;
    }
    public float[] getPersonModifier()
    {
        float[] personModifier = new float[STATS_NUM];
        for (int i = 0; i < STATS_NUM; i++) {
            personModifier[i] = myStats[i][PERSON_MOD];
        }
        return personModifier;
    }

    protected float[][] getRawStats()
    {
        return myStats;
    }
    protected float[] getNetStats()
    {
        float[] netStats = new float[STATS_NUM];
        float[] gross = getGrossStats();
        float[] mod = getStatModifier();
        List<float[]> upGross = new ArrayList<>();
        List<float[]> upMod = new ArrayList<>();

        for (int i = 0; i < bodyPart.getAttachedBodyParts().size(); i++)
        {
            upGross.add(bodyPart.getAttachedBodyParts().get(i).getUpstreamGrossStat());
            upMod.add(bodyPart.getAttachedBodyParts().get(i).getUpstreamStatModifier());
        }

        for (int i = 0; i < STATS_NUM; i++) {

            netStats[i] = gross[i];

            for (int j = 0; j < upGross.size(); j++)
            {
                netStats[i] += upGross.get(j)[i];
            }

            netStats[i] *= mod[i];

            for (int j = 0; j < upMod.size(); j++)
            {
                netStats[i] *= upMod.get(j)[i];
            }
        }


        return netStats;
    }
}
