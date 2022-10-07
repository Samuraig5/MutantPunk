package Main.BodyLogic.Torso;

import Main.BodyLogic.BodyLogicHelper;
import Main.BodyLogic.BodyPart;

public class HumanTorso extends BodyPart
{
    final int type = 1;
    final String bodyPartClass = "torso";
    final int standardNeededBlood = 40;
    final int standardBloodGeneration = 0;
    final int standardNeededEnergy = 0;
    final int standardHealth = 100;
    final int standardRegenRate = 1;
    final int standardRegenLimit = 80;
    final int standardArmour = 10;
    final int standardSize = 100;
    final int standardOrganCapacity = 12;
    final int standardSpeedModifier = 0;

    @Override
    public void instantiateBodyPart(int bias, int randomness)
    {
        organCapacity = BodyLogicHelper.calculateBodyPartStat(standardOrganCapacity, bias, randomness);
    }

    @Override
    public void doDamage(int damage)
    {

    }

    @Override
    public void updatePersonWhenAttached()
    {

    }

    @Override
    public void updatePersonWhenRemoved()
    {

    }
}
