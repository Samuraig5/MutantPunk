package Main;

public class MathHelper
{
    static public float randomRange()
    {
        float ran = (float) (Math.random()*2-1);
        return roundToTwoDecimals(ran);
    }

    static public float roundToTwoDecimals(float input)
    {
        return (float) (Math.round(input * 100.0) / 100.0);
    }
}
