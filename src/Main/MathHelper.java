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

    /**
     * This function return the modulo the sum of a value and an add. The result will always be positive. If the result would be -1 its modulo-1 instead.
     * @param value Value 1
     * @param add Value to be added
     * @param modulo Value of the modulo
     * @return Positive modulo of the sum
     */
    static public int boundedInteger(int value, int add, int modulo){return (value + add + modulo) % modulo;}
}
