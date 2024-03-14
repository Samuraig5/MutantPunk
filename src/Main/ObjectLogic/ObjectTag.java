package Main.ObjectLogic;

public enum ObjectTag
{
    SOLID,
    LIQUID,
    GASEOUS,
    ORGANIC,
    CYBERNETIC,
    PLANT,
    FRUIT,
    MEAT;


    public static ObjectTag[] translateStringToTag(String[] split)
    {
        ObjectTag[] tags = new ObjectTag[split.length];
        for (int i = 0; i < split.length; i++)
        {
            tags[i] = valueOf(split[i]);
        }
        return tags;
    }
}
