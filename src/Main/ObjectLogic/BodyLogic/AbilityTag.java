package Main.ObjectLogic.BodyLogic;

import Main.ObjectLogic.ObjectTag;

public enum AbilityTag
{
    DIGESTION;

    public static AbilityTag translateStringToTag(String split)
    {
        AbilityTag tags = valueOf(split);
        return tags;
    }
}
