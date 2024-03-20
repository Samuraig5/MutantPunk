package Main.ObjectLogic.BodyLogic;

import Main.MathHelper;
import Main.ObjectLogic.ObjectTag;

import java.util.List;

public class BodyPartAbility
{
    private String abilityName;
    private AbilityTag abilityTag;
    private ObjectTag[] relatedObjectTags;
    private int capacity;
    private int currentFillLevel;
    private int efficiency;

    public BodyPartAbility(String abilityName, AbilityTag abilityTag, ObjectTag[] relatedObjectTags)
    {
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

    public void setCurrentFillLevel(int currentFillLevel) {
        this.currentFillLevel = MathHelper.clamp(currentFillLevel,0,getCapacity());
    }
}
