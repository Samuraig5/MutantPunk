package Main.WorldLogic;

import Main.BodyLogic.BodyPart;
import Main.BodyLogic.Legs.HumanLeg;
import Main.BodyLogic.Torso.HumanTorso;

import java.util.ArrayList;
import java.util.List;

public class Human extends Person
{
    public void generateBody(int bias, int randomness)
    {
        HumanTorso newTorso = new HumanTorso();
        newTorso.instantiateBodyPart(this, bias, randomness);

        HumanLeg leftLeg = new HumanLeg();
        leftLeg.instantiateBodyPart(bias, randomness);
        leftLeg.attachTo(newTorso);

        HumanLeg rightLeg = new HumanLeg();
        rightLeg.instantiateBodyPart(bias, randomness);
        rightLeg.attachTo(newTorso);
    }
}