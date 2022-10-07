package Main.BodyLogic.Legs;

import Main.BodyLogic.BodyLogicHelper;
import Main.BodyLogic.BodyPart;
import Main.WorldLogic.Person;

public class HumanLeg extends BodyPart
{
    final int type = 1;
    final String bodyPartClass = "leg";
    final int standardBloodCapacity = 25;
    final int standardBloodGeneration = 0;
    final int standardNeededBlood = 20;
    final int standardEnergyCapacity = 0;
    final int standardEnergyGeneration = 0;
    final int standardNeededEnergy = 0;
    final int standardHealth = 80;
    final int standardRegenRate = 1;
    final int standardRegenLimit = 60;
    final int standardArmour = 10;
    final int standardSize = 80;
    final int standardOrganCapacity = 5;
    final int standardSpeedModifier = 50;

    @Override
    public void generateBodyPart(int bias, int randomness)
    {
        bloodCapacity = BodyLogicHelper.calculateBodyPartStat(standardBloodCapacity, bias, randomness);
        bloodGeneration = BodyLogicHelper.calculateBodyPartStat(standardBloodGeneration);
        neededBlood = BodyLogicHelper.calculateBodyPartStat(standardNeededBlood, bias, randomness);
        energyCapacity = BodyLogicHelper.calculateBodyPartStat(standardEnergyCapacity);
        energyGeneration = BodyLogicHelper.calculateBodyPartStat(standardEnergyGeneration);
        neededEnergy = BodyLogicHelper.calculateBodyPartStat(standardNeededEnergy);
        health = BodyLogicHelper.calculateBodyPartStat(standardHealth, bias, randomness);
        regenRate = BodyLogicHelper.calculateBodyPartStat(standardRegenRate, bias, randomness);
        regenLimit = BodyLogicHelper.calculateBodyPartStat(standardRegenLimit, bias, randomness);
        armour = BodyLogicHelper.calculateBodyPartStat(standardArmour, bias, randomness);
        size = BodyLogicHelper.calculateBodyPartStat(standardSize, bias, randomness);
        organCapacity = BodyLogicHelper.calculateBodyPartStat(standardOrganCapacity, bias, randomness);
        speedModifier = BodyLogicHelper.calculateBodyPartStat(standardSpeedModifier, bias, randomness);
    }
}
