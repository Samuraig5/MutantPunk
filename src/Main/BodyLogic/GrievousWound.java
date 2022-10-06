package Main.BodyLogic;

public class GrievousWound extends BodyPart
{
    final int standardBloodGeneration = -10;

    @Override
    public void instantiateBodyPart(float bias, float randomness)
    {
        bloodGeneration = Math.round(-standardBloodGeneration +((bias/100)*(standardBloodGeneration)));
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
