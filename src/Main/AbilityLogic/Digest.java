package Main.AbilityLogic;

import Main.MathHelper;
import Main.ObjectLogic.BodyLogic.AbilityTag;
import Main.ObjectLogic.BodyLogic.BodyPart;
import Main.ObjectLogic.BodyLogic.BodyPartStat;
import Main.ObjectLogic.ObjectTag;
import Main.ObjectLogic.Thing;

public class Digest extends Ability
{
    public Digest(BodyPart bodyPart, String abilityName, AbilityTag abilityTag, ObjectTag[] relatedObjectTags)
    {
        setBodyPart(bodyPart);
        setAbilityName(abilityName);
        setAbilityTag(abilityTag);
        setRelatedObjectTags(relatedObjectTags);
    }

    @Override
    public void activeEffect(Thing target)
    {

    }

    public void update() {
        super.update();
        int digestCost = 1000;
        if (!isEmpty() && canAffordAction(digestCost))
        {
            changeActionPoints(-digestCost);
            passiveEffect();
        }
    }

    @Override
    public void passiveEffect()
    {
        float digestRate = getBodyPart().getStats()[BodyPartStat.BLOOD_GENERATION];
        digestRate = MathHelper.clamp(digestRate, 0, getCurrentFillLevel()-digestRate);
        changeCurrentFillLevel(-digestRate);
        getBodyPart().changeBloodLevels(digestRate*getEfficiency());
    }
}
