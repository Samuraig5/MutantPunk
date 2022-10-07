package Main.BodyLogic.Arms;

import Main.BodyLogic.BodyLogicHelper;
import Main.BodyLogic.BodyPart;
import Main.BodyLogic.Hands.HumanHand;

public class HumanArm extends BodyPart
{
    final int standardBloodCapacity = 10;
    final int standardNeededBlood = 10;
    final int standardHealth = 40;
    final int standardRegenRate = 1;
    final int standardRegenLimit = 30;
    final int standardArmour = 10;
    final int standardSize = 50;
    final int standardOrganCapacity = 5;

    @Override
    public void generateBodyPart(int bias, int randomness)
    {
        type = 1;
        bodyPartClass = "arm";

        bloodCapacity = BodyLogicHelper.calculateBodyPartStat(standardBloodCapacity, bias, randomness);
        neededBlood = BodyLogicHelper.calculateBodyPartStat(standardNeededBlood, bias, randomness);
        health = BodyLogicHelper.calculateBodyPartStat(standardHealth, bias, randomness);
        regenRate = BodyLogicHelper.calculateBodyPartStat(standardRegenRate, bias, randomness);
        regenLimit = BodyLogicHelper.calculateBodyPartStat(standardRegenLimit, bias, randomness);
        armour = BodyLogicHelper.calculateBodyPartStat(standardArmour, bias, randomness);
        size = BodyLogicHelper.calculateBodyPartStat(standardSize, bias, randomness);
        organCapacity = BodyLogicHelper.calculateBodyPartStat(standardOrganCapacity, bias, randomness);
    }
}
