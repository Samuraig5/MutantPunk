package Main.BodyLogic.Feet;

import Main.BodyLogic.BodyLogicHelper;
import Main.BodyLogic.BodyPart;

public class HumanFoot extends BodyPart
{
    final int standardBloodCapacity = 5;
    final int standardNeededBlood = 5;
    final int standardHealth = 10;
    final int standardRegenRate = 1;
    final int standardRegenLimit = 5;
    final int standardArmour = 10;
    final int standardSize = 10;
    final int standardOrganCapacity = 5;
    final int standardSpeedModifier = 5;

    @Override
    public void generateBodyPart(int bias, int randomness)
    {
        name = "Human Foot";
        type = 1;
        bodyPartClass = "foot";

        bloodCapacity = BodyLogicHelper.calculateBodyPartStat(standardBloodCapacity, bias, randomness);
        neededBlood = BodyLogicHelper.calculateBodyPartStat(standardNeededBlood, -bias, randomness);
        maxHealth = BodyLogicHelper.calculateBodyPartStat(standardHealth, bias, randomness);
        regenRate = BodyLogicHelper.calculateBodyPartStat(standardRegenRate, bias, randomness);
        regenLimit = BodyLogicHelper.calculateBodyPartStat(standardRegenLimit, bias, randomness);
        armour = BodyLogicHelper.calculateBodyPartStat(standardArmour, bias, randomness);
        size = BodyLogicHelper.calculateBodyPartStat(standardSize, bias, randomness);
        organCapacity = BodyLogicHelper.calculateBodyPartStat(standardOrganCapacity, bias, randomness);
        speedModifier = BodyLogicHelper.calculateBodyPartStat(standardSpeedModifier, bias, randomness);
    }
}
