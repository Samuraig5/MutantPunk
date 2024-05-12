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
        int values = 0;
        for (int i = 0; i < split.length; i++)
        {
            if (split[i] == null) {break;}
            values++;
        }
        ObjectTag[] tags = new ObjectTag[values];
        for (int i = 0; i < values; i++)
        {
            tags[i] = valueOf(split[i]);
        }
        return tags;
    }
}
