package Main.BodyLogic.Torso;

import Main.BodyLogic.BodyLogicHelper;
import Main.BodyLogic.BodyPart;

public class HumanTorso extends BodyPart
{
    final int standardBloodCapacity = 50;
    final int standardNeededBlood = 40;
    final int standardHealth = 100;
    final int standardRegenRate = 1;
    final int standardRegenLimit = 80;
    final int standardArmour = 10;
    final int standardSize = 100;
    final int standardOrganCapacity = 100;

    @Override
    public void generateBodyPart(int bias, int randomness)
    {
        name = "Human Torso";
        type = 1;
        bodyPartClass = "torso";

        bloodCapacity = BodyLogicHelper.calculateBodyPartStat(standardBloodCapacity, bias, randomness);
        neededBlood = BodyLogicHelper.calculateBodyPartStat(standardNeededBlood, -bias, randomness);
        maxHealth = BodyLogicHelper.calculateBodyPartStat(standardHealth, bias, randomness);
        regenRate = BodyLogicHelper.calculateBodyPartStat(standardRegenRate, bias, randomness);
        regenLimit = BodyLogicHelper.calculateBodyPartStat(standardRegenLimit, bias, randomness);
        armour = BodyLogicHelper.calculateBodyPartStat(standardArmour, bias, randomness);
        size = BodyLogicHelper.calculateBodyPartStat(standardSize, bias, randomness);
        organCapacity = BodyLogicHelper.calculateBodyPartStat(standardOrganCapacity, bias, randomness);
    }
}
