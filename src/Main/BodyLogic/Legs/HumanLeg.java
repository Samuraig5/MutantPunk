package Main.BodyLogic.Legs;

import Main.BodyLogic.BodyLogicHelper;
import Main.BodyLogic.BodyPart;

public class HumanLeg extends BodyPart
{
    final int standardBloodCapacity = 20;
    final int standardNeededBlood = 20;
    final int standardHealth = 80;
    final int standardRegenRate = 1;
    final int standardRegenLimit = 60;
    final int standardArmour = 10;
    final int standardSize = 75;
    final int standardOrganCapacity = 10;
    final int standardSpeedModifier = 45;

    @Override
    public void generateBodyPart(int bias, int randomness)
    {
        name = "Human Leg";
        type = 1;
        bodyPartClass = "leg";

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
