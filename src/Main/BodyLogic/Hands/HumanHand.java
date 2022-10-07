package Main.BodyLogic.Hands;

import Main.BodyLogic.BodyLogicHelper;
import Main.BodyLogic.BodyPart;

public class HumanHand extends BodyPart
{
    final int standardBloodCapacity = 5;
    final int standardNeededBlood = 5;
    final int standardHealth = 10;
    final int standardRegenRate = 1;
    final int standardRegenLimit = 5;
    final int standardArmour = 10;
    final int standardSize = 10;
    final int standardOrganCapacity = 2;

    @Override
    public void generateBodyPart(int bias, int randomness)
    {
        type = 1;
        bodyPartClass = "hand";

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
