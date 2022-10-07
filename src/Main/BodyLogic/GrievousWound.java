package Main.BodyLogic;

public class GrievousWound extends BodyPart
{
    final int standardBloodGeneration = -10;

    @Override
    public void instantiateBodyPart(int bias, int randomness)
    {
        bloodGeneration = BodyLogicHelper.calculateBodyPartStat(standardBloodGeneration,bias,randomness);
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
