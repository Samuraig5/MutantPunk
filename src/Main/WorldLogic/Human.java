package Main.WorldLogic;

import Main.BodyLogic.Arms.HumanArm;
import Main.BodyLogic.BodyPart;
import Main.BodyLogic.Feet.HumanFoot;
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
        leftLeg.changeName("Human left leg");

        HumanLeg rightLeg = new HumanLeg();
        rightLeg.instantiateBodyPart(bias, randomness);
        rightLeg.attachTo(newTorso);
        rightLeg.changeName("Human right leg");

        HumanFoot leftFoot = new HumanFoot();
        leftFoot.instantiateBodyPart(bias, randomness);
        leftFoot.attachTo(leftLeg);
        leftFoot.changeName("Human left foot");

        HumanFoot rightFoot = new HumanFoot();
        rightFoot.instantiateBodyPart(bias, randomness);
        rightFoot.attachTo(rightLeg);
        rightFoot.changeName("Human right foot");

        HumanArm leftArm = new HumanArm();
        leftArm.instantiateBodyPart(bias, randomness);
        leftArm.attachTo(newTorso);
        leftArm.changeName("Human left arm");

        HumanArm rightArm = new HumanArm();
        rightArm.instantiateBodyPart(bias, randomness);
        rightArm.attachTo(newTorso);
        rightArm.changeName("Human right arm");

        HumanHand leftHand = new HumanHand();
        leftHand.instantiateBodyPart(bias, randomness);
        leftHand.attachTo(leftArm);
        leftHand.changeName("Human left hand");

        HumanHand rightHand = new HumanHand();
        rightHand.instantiateBodyPart(bias, randomness);
        rightHand.attachTo(rightArm);
        rightHand.changeName("Human right hand");
    }
}