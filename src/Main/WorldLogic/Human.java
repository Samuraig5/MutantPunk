package Main.WorldLogic;

import Main.BodyLogic.Arms.HumanArm;
import Main.BodyLogic.BodyPart;
import Main.BodyLogic.Hands.HumanHand;
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

        HumanArm leftArm = new HumanArm();
        leftArm.instantiateBodyPart(bias, randomness);
        leftArm.attachTo(newTorso);

        HumanArm rightArm = new HumanArm();
        rightArm.instantiateBodyPart(bias, randomness);
        rightArm.attachTo(newTorso);

        HumanHand leftHand = new HumanHand();
        leftHand.instantiateBodyPart(bias, randomness);
        leftHand.attachTo(leftArm);

        HumanHand rightHand = new HumanHand();
        rightHand.instantiateBodyPart(bias, randomness);
        rightHand.attachTo(rightArm);
    }
}