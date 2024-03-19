package Main.ObjectLogic.BodyLogic;

import Main.ObjectLogic.ObjectTag;

import java.util.List;

public class BodyPartAbility
{
    private String abilityName;
    private AbilityTag abilityTag;
    private ObjectTag[] relatedObjectTags;

    public BodyPartAbility(String abilityName, AbilityTag abilityTag, ObjectTag[] relatedObjectTags)
    {
        this.abilityName = abilityName;
        this.abilityTag = abilityTag;
        this.relatedObjectTags = relatedObjectTags;
    }

    public String getAbilityName() {return abilityName;}
    public AbilityTag getAbilityTag() {return abilityTag;}
    public ObjectTag[] getRelatedObjectTags() {return relatedObjectTags;}
}
