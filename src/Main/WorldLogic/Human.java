package Main.WorldLogic;

import Main.BodyLogic.BodyPart;
import Main.BodyLogic.BodyPartDecoder;

import java.util.ArrayList;
import java.util.List;

public class Human extends Person
{
    public void generateBody(int bias, int randomness)
    {
        BodyPart newTorso = BodyPartDecoder.loadBodyPartFromFile(this,"Resources/BodyParts/Torso/HumanTorso", bias, randomness);

        BodyPart leftLeg = BodyPartDecoder.loadBodyPartFromFile("Resources/BodyParts/Legs/HumanLeg", bias, randomness);
        leftLeg.attachTo(newTorso);
        leftLeg.changeName("Human left leg");

        BodyPart rightLeg = BodyPartDecoder.loadBodyPartFromFile("Resources/BodyParts/Legs/HumanLeg", bias, randomness);
        rightLeg.attachTo(newTorso);
        rightLeg.changeName("Human right leg");

        BodyPart leftFoot = BodyPartDecoder.loadBodyPartFromFile("Resources/BodyParts/Feet/HumanFoot", bias, randomness);
        leftFoot.attachTo(leftLeg);
        leftFoot.changeName("Human left foot");

        BodyPart rightFoot = BodyPartDecoder.loadBodyPartFromFile("Resources/BodyParts/Feet/HumanFoot", bias, randomness);
        rightFoot.attachTo(rightLeg);
        rightFoot.changeName("Human right foot");

        BodyPart leftArm = BodyPartDecoder.loadBodyPartFromFile("Resources/BodyParts/Arms/HumanArm", bias, randomness);
        leftArm.attachTo(newTorso);
        leftArm.changeName("Human left arm");

        BodyPart rightArm = BodyPartDecoder.loadBodyPartFromFile("Resources/BodyParts/Arms/HumanArm", bias, randomness);
        rightArm.attachTo(newTorso);
        rightArm.changeName("Human right arm");

        BodyPart leftHand = BodyPartDecoder.loadBodyPartFromFile("Resources/BodyParts/Hands/HumanHand", bias, randomness);
        leftHand.attachTo(leftArm);
        leftHand.changeName("Human left hand");

        BodyPart rightHand = BodyPartDecoder.loadBodyPartFromFile("Resources/BodyParts/Hands/HumanHand", bias, randomness);
        rightHand.attachTo(rightArm);
        rightHand.changeName("Human right hand");
    }
}