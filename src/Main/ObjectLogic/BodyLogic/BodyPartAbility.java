package Main.ObjectLogic.BodyLogic;

import Main.MathHelper;
import Main.ObjectLogic.ObjectTag;
import Main.Settings;
import Main.TimeLogic.Updatable;

public class BodyPartAbility extends Updatable {
    private BodyPart bodyPart;
    private String abilityName;
    private AbilityTag abilityTag;
    private ObjectTag[] relatedObjectTags;
    private int capacity;
    private int currentFillLevel;
    private int efficiency;

    public BodyPartAbility(BodyPart bodyPart, String abilityName, AbilityTag abilityTag, ObjectTag[] relatedObjectTags)
    {
        this.bodyPart = bodyPart;
        this.abilityName = abilityName;
        this.abilityTag = abilityTag;
        this.relatedObjectTags = relatedObjectTags;
    }

    public String getAbilityName() {return abilityName;}
    public AbilityTag getAbilityTag() {return abilityTag;}
    public ObjectTag[] getRelatedObjectTags() {return relatedObjectTags;}

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(int efficiency) {
        this.efficiency = efficiency;
    }

    public int getCurrentFillLevel() {
        return currentFillLevel;
    }
    public boolean isFull()
    {
        if (currentFillLevel >= capacity)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void addToCurrentFillLevel(int change)
    {
        setCurrentFillLevel(this.currentFillLevel + change);
    }

    public void setCurrentFillLevel(int currentFillLevel)
    {
        this.currentFillLevel = MathHelper.clamp(currentFillLevel,0,getCapacity());
    }

    public void update() {
        super.update();
        int digestCost = 1000;
        if (currentFillLevel > 0 && getActionPoints() > digestCost)
        {
            if (getAbilityTag() == AbilityTag.DIGESTION)
            {
                changeActionPoints(-digestCost);
                float digestRate = bodyPart.getStats()[BodyPartStat.BLOOD_GENERATION];
                digestRate = MathHelper.clamp(digestRate, 0, currentFillLevel-digestRate);
                currentFillLevel -= digestRate;
                bodyPart.changeBloodLevels(digestRate*efficiency);
            }
        }
    }
}
