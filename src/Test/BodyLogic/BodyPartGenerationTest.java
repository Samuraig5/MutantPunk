package Test.BodyLogic;

import Main.BodyLogic.BodyFileDecoder;
import Main.BodyLogic.BodyPart;
import Main.BodyLogic.Person;

public class BodyPartGenerationTest {

    public static void main(String[] args)
    {
        Person guy = BodyFileDecoder.getBodyPlanData("Resources/BodyPlans/Human",0,0);
        guy.name = "Test Dummy";

        System.out.println("--> Number of Body Parts: " + guy.myBodyParts.size());
        for (BodyPart bp:guy.myBodyParts)
        {
            bp.printBodyPartToTerminal();
        }
    }
}
