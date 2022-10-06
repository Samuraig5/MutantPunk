package Main.BodyLogic.Torso;

import Main.BodyLogic.BodyPart;

public class HumanTorso extends BodyPart
{
    final int standardOrganCapacity = 12;

    @Override
    public void instantiateBodyPart(float bias, float randomness)
    {
        organCapacity = Math.round(standardOrganCapacity +((bias/100)*(standardOrganCapacity)));
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
