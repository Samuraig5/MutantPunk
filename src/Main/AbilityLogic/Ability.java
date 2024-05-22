package Main.AbilityLogic;

import Main.MathHelper;
import Main.ObjectLogic.BodyLogic.AbilityTag;
import Main.ObjectLogic.BodyLogic.BodyPart;
import Main.ObjectLogic.ObjectTag;
import Main.ObjectLogic.Thing;
import Main.TimeLogic.Updatable;

import java.util.List;

public abstract class Ability extends Updatable
{
    private BodyPart bodyPart;
    private String abilityName;
    private AbilityTag abilityTag;
    private ObjectTag[] relatedObjectTags;
    private int actionCost;
    private float capacity;
    private float currentFillLevel;
    private float efficiency;

    public BodyPart getBodyPart() {return bodyPart;}
    public void setBodyPart(BodyPart bodyPart) {this.bodyPart = bodyPart;}
    public String getAbilityName() {return abilityName;}
    public void setAbilityName(String abilityName) {this.abilityName = abilityName;}
    public AbilityTag getAbilityTag() {return abilityTag;}
    public void setAbilityTag(AbilityTag abilityTag) {this.abilityTag = abilityTag;}
    public ObjectTag[] getRelatedObjectTags() {return relatedObjectTags;}
    public void setRelatedObjectTags(ObjectTag[] relatedObjectTags) {this.relatedObjectTags = relatedObjectTags;}
    public int getActionCost() {return actionCost;}
    public void setActionCost(int cost) {actionCost = cost;}
    public float getCapacity() {return capacity;}
    public void setCapacity(float capacity) {this.capacity = capacity;}
    public float getCurrentFillLevel() {return currentFillLevel;}
    public void setCurrentFillLevel(float currentFillLevel) {this.currentFillLevel = MathHelper.clamp(currentFillLevel,0,getCapacity());}
    public float getEfficiency() {return efficiency;}
    public void setEfficiency(float efficiency) {this.efficiency = efficiency;}
    public boolean isFull() {return currentFillLevel >= capacity;}
    public boolean isEmpty() {return currentFillLevel == 0;}
    public void changeCurrentFillLevel(float change)
    {
        setCurrentFillLevel(currentFillLevel + change);
    }

    public abstract void activeEffect(Thing origin, Thing target);

    public abstract void passiveEffect();

    public static Ability getMinCost(List<Ability> abilities)
    {
        Ability res = abilities.get(0);
        for (Ability ability:abilities)
        {
            res = ability;
        }
        return res;
    }




}
