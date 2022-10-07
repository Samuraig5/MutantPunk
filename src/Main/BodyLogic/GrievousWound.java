package Main.BodyLogic;

import Main.WorldLogic.Person;

public class GrievousWound extends BodyPart
{
    final int standardBloodGeneration = -10;
    final int standardHealth = 1;

    public void generateBodyPart(int bias, int randomness)
    {
        this.type = 1;
        this.bodyPartClass = "misc";

        bloodGeneration = BodyLogicHelper.calculateBodyPartStat(standardBloodGeneration, bias, randomness);
        health = BodyLogicHelper.calculateBodyPartStat(standardHealth, bias, randomness, 0);
    }
}
