package Main.BodyLogic;

public class GrievousWound extends BodyPart
{
    final int type = -1;
    final String bodyPartClass = "misc";
    final int standardBloodCapacity = 0;
    final int standardBloodGeneration = -10;
    final int standardNeededBlood = 0;
    final int standardEnergyCapacity = 0;
    final int standardEnergyGeneration = 0;
    final int standardNeededEnergy = 0;
    final int standardHealth = 1;
    final int standardRegenRate = 0;
    final int standardRegenLimit = 0;
    final int standardArmour = 0;
    final int standardSize = 0;
    final int standardOrganCapacity = 0;
    final int standardSpeedModifier = 0;

    @Override
    public void instantiateBodyPart(int bias, int randomness)
    {
        bloodCapacity = BodyLogicHelper.calculateBodyPartStat(standardBloodCapacity);
        bloodGeneration = BodyLogicHelper.calculateBodyPartStat(standardBloodGeneration, bias, randomness);
        neededBlood = BodyLogicHelper.calculateBodyPartStat(standardNeededBlood);
        energyCapacity = BodyLogicHelper.calculateBodyPartStat(standardEnergyCapacity);
        energyGeneration = BodyLogicHelper.calculateBodyPartStat(standardEnergyGeneration);
        neededEnergy = BodyLogicHelper.calculateBodyPartStat(standardNeededEnergy);
        health = BodyLogicHelper.calculateBodyPartStat(standardHealth);
        regenRate = BodyLogicHelper.calculateBodyPartStat(standardRegenRate);
        regenLimit = BodyLogicHelper.calculateBodyPartStat(standardRegenLimit);
        armour = BodyLogicHelper.calculateBodyPartStat(standardArmour);
        size = BodyLogicHelper.calculateBodyPartStat(standardSize);
        organCapacity = BodyLogicHelper.calculateBodyPartStat(standardOrganCapacity);
        speedModifier = BodyLogicHelper.calculateBodyPartStat(standardSpeedModifier);
    }
}
