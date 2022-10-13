package Test.BodyLogic;

import Main.BodyLogic.BodyFileDecoder;
import Main.BodyLogic.BodyPart;
import Main.BodyLogic.Person;

public class PersonStatsTest
{
    public static void main(String[] args)
    {
        Person guy = BodyFileDecoder.getBodyPlanData("Resources/BodyPlans/Human",0,0);
        guy.changeName("Test Dummy");
        System.out.println("Human without any modifiers");
        guy.printPersonToTerminal();

        guy.changeGrossSpeed(100);
        System.out.println("Human with +100 speed");
        guy.printPersonToTerminal();

        guy.changeSpeedModifier(0.1f);
        System.out.println("Human with a +10% speed");
        guy.printPersonToTerminal();
    }
}
