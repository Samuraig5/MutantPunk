package Main.BodyLogic;

public class GrievousWound extends BodyPart
{
    final int standardBloodGeneration = -10;
    final int standardHealth = 1;

    public void generateBodyPart(int bias, int randomness)
    {
        name = "Grievous Wound";
        this.type = 1;
        this.bodyPartClass = "misc";

        bloodGeneration = BodyLogicHelper.calculateBodyPartStat(standardBloodGeneration, bias, randomness);
        maxHealth = BodyLogicHelper.calculateBodyPartStat(standardHealth, bias, randomness, 0);
    }
}
