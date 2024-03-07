package Main.ObjectLogic.BodyLogic;

import Main.ObjectLogic.ObjectTag;

import java.util.List;

public class BodyPartAbility
{
    private AbilityTag abilityTag;
    private ObjectTag[] relatedObjectTags;

    public BodyPartAbility(AbilityTag abilityTag, ObjectTag[] relatedObjectTags)
    {
        this.abilityTag = abilityTag;
        this.relatedObjectTags = relatedObjectTags;
    }

    public AbilityTag getAbilityTag() {return abilityTag;}
    public ObjectTag[] getRelatedObjectTags() {return relatedObjectTags;}
}
