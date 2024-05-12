package Main.ObjectLogic.BodyLogic;

import java.util.List;

public class BodyLogicHelper
{
    /**
     * Calculates one stat of a bodyPart based on the standardStat of the bodyPart, a bias, a randomness and a shiftStrength.
     *
     * @param incomingStandardStat The standardStat of the bodyPart which is manipulated to get the final stat.
     * @param bias A bias expressed as a percentage.
     *             '10' would shift the final stat 10% higher.
     *             '-10' would shift the final stat 10% lower.
     * @param randomness The magnitude of the random shift expressed as a percentage.
     *                   '0' means no random shift of the stat.
     *                   '10' means at most a 10% shift of the stat both into the positive and negative relative to
     *                      the standard stat
     *                   '100' means at most a 100% shift of the stat both into the positive and negative relative to
     *                      the standard stat
     * @param shiftStrength Modifies the magnitude of the bias and random shift. Expressed as a percentage.
     *                      '10' means halving the magnitude of the calculated shifts.
     *                      '100' means no magnification of the calculated shifts.
     *                      '200' means doubling the magnitude of the calculated shifts.
     * @return the final calculated Stat.
     */
    static public float[] calculateBodyPartStat(String[] incomingStandardStat, int bias, int randomness, int shiftStrength)
    {
        float[] sol = new float[5];
        for (int i = 0; i < 5; i++)
        {
            float a = Float.parseFloat(incomingStandardStat[i]);
            float b = (float)bias;
            float r = (float)randomness;
            float s = (float)shiftStrength;

            float calculatedBias = ((b/100)*a)*(s/100);
            float calculatedRandomness = (randomRange()*(r/100)*a)*(s/100);
            sol[i] = Math.round(a + calculatedBias + calculatedRandomness);
        }
        return sol;
    }
    static public float[] calculateBodyPartStat(String[] incomingStandardStat, int bias, int randomness)
    {
        return calculateBodyPartStat(incomingStandardStat,bias,randomness,100);
    }

    /**
     * Generates a number between '-1' and '1'
     *
     * @return the random float between '-1' and '1'
     */
    static public float randomRange()
    {
        return (float) Math.random()*2-1;
    }
}
