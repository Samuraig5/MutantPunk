package Main.WorldLogic;

import Main.BodyLogic.BodyPart;

import java.util.List;

public abstract class Person
{
    public List<BodyPart> myBodyParts;
    public int blood;
    public int bloodGain;

    /**
     * This function generates a normal human person.
     *
     * @param bias improves (if bias is positive) or worsens (if bias is negative) the stats and attribute of the persons bodyParts.
     *             The bias is expressed as a present. So a bias of '10' would increase the persons bodyPart stats by 10%.
     * @param randomness changes the stats of the persons bodyPart by a random amount (both positive and negative).
     *                   The greater the value, the stronger the random drift.
     */
    void instantiateNormalPerson(float bias, float randomness)
    {

    }
}
