package Main.BodyLogic.Legs;

import Main.BodyLogic.BodyPart;

public class HumanLeg extends BodyPart
{
    final int standardSpeedModifier = 50;
    final int standardNeededBlood = 20;

    int speedModifier;

    @Override
    public void instantiateBodyPart(float bias, float randomness)
    {
        speedModifier = Math.round(standardSpeedModifier +((bias/100)*(standardSpeedModifier)));
        neededBlood = Math.round(standardNeededBlood +((bias/100)*(standardNeededBlood)));
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
