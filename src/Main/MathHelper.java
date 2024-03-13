package Main;

public class MathHelper
{
    static public float randomRange(float min, float max)
    {
        return min + (int)(Math.random() * ((max - min) + 1));
    }
    static public int randomRangeInt(float min, float max)
    {
        return Math.round(randomRange(min, max));
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

    static public boolean fiftyFifty()
    {
        return randomDecider(0.5f);
    }

    static public boolean randomDecider(float percentageToHit)
    {
        if (Math.random() < percentageToHit) {
            return true;
        }
        return false;
    }

    static public int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    static public float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(max, value));
    }

    public static String indexToLetter(int n)
    {
        String letter;
        switch (n)
        {
            case 0:  letter = "a";
                break;
            case 1:  letter = "b";
                break;
            case 2:  letter = "c";
                break;
            case 3:  letter = "d";
                break;
            case 4:  letter = "e";
                break;
            case 5:  letter = "f";
                break;
            case 6:  letter = "g";
                break;
            case 7:  letter = "h";
                break;
            case 8:  letter = "i";
                break;
            case 9: letter = "j";
                break;
            case 10: letter = "k";
                break;
            case 11: letter = "l";
                break;
            case 12: letter = "m";
                break;
            case 13: letter = "n";
                break;
            case 14: letter = "o";
                break;
            case 15: letter = "p";
                break;
            case 16: letter = "q";
                break;
            case 17: letter = "r";
                break;
            case 18: letter = "s";
                break;
            case 19: letter = "t";
                break;
            case 20: letter = "u";
                break;
            case 21: letter = "v";
                break;
            case 22: letter = "w";
                break;
            case 23: letter = "x";
                break;
            case 24: letter = "y";
                break;
            case 25: letter = "z";
                break;
            default: letter = "Invalid index";
                break;
        }
        return letter;
    }
}
