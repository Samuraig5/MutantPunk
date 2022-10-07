package Main.BodyLogic.Torso;

import Main.BodyLogic.BodyLogicHelper;
import Main.BodyLogic.BodyPart;

public class HumanTorso extends BodyPart
{
    final int standardOrganCapacity = 12;
    final int standardNeededBlood = 40;
    final int size = 100;

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
