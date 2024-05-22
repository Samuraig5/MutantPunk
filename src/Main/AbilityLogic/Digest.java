package Main.AbilityLogic;

import Main.MathHelper;
import Main.ObjectLogic.BodyLogic.AbilityTag;
import Main.ObjectLogic.BodyLogic.BodyPart;
import Main.ObjectLogic.BodyLogic.BodyPartStat;
import Main.ObjectLogic.ObjectTag;
import Main.ObjectLogic.Thing;

public class Digest extends Ability
{
    public Digest(BodyPart bodyPart, String abilityName, AbilityTag abilityTag, ObjectTag[] relatedObjectTags,
                  int actionCost, float capacity, float efficiency)
    {
        setBodyPart(bodyPart);
        setAbilityName(abilityName);
        setAbilityTag(abilityTag);
        setRelatedObjectTags(relatedObjectTags);
        setActionCost(actionCost);
        setCapacity(capacity);
        setEfficiency(efficiency);
    }

    @Override
    public void activeEffect(Thing origin, Thing target)
    {
        if (!origin.canAffordAction(getActionCost())) {return;}
        origin.changeActionPoints(-getActionCost());
        changeCurrentFillLevel(10);
        target.destroy();
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
