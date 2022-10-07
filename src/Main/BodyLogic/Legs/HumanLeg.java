package Main.BodyLogic.Legs;

import Main.BodyLogic.BodyLogicHelper;
import Main.BodyLogic.BodyPart;

public class HumanLeg extends BodyPart
{
    final int standardSpeedModifier = 50;
    final int standardNeededBlood = 20;

    int speedModifier;

    @Override
    public void instantiateBodyPart(int bias, int randomness)
    {
        speedModifier = BodyLogicHelper.calculateBodyPartStat(standardSpeedModifier,bias,randomness);
        neededBlood = BodyLogicHelper.calculateBodyPartStat(standardSpeedModifier,bias,randomness);
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
