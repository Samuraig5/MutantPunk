package Main.BodyLogic;

public class GrievousWound extends BodyPart
{
    final int standardBloodGeneration = -10;

    @Override
    void instantiateBodyPart(float bias, float randomness)
    {
        bloodGeneration = Math.round(-standardBloodGeneration +((bias/100)*(standardBloodGeneration)));
    }

    @Override
    void doDamage(int damage)
    {

    }

    @Override
    void updatePersonWhenAttached()
    {

    }

    @Override
    void updatePersonWhenRemoved()
    {

    }
}
