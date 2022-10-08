package Test;

import Main.BodyLogic.BodyPart;
import Main.WorldLogic.Human;

public class BodyPartGenerationTest {

    public static void main(String[] args)
    {
        Human guy = new Human();

        guy.instantiatePerson("Test Guy",0,0);
        System.out.println("--> Number of Body Parts: " + guy.myBodyParts.size());
        System.out.println("==========HUMAN TORSO==========");
        guy.myBodyParts.get(0).printBodyPartToTerminal();
        System.out.println("==========HUMAN LEFT LEG==========");
        guy.myBodyParts.get(1).printBodyPartToTerminal();
        System.out.println("==========HUMAN RIGHT LEG==========");
        guy.myBodyParts.get(2).printBodyPartToTerminal();
        System.out.println("==========HUMAN LEFT FOOT==========");
        guy.myBodyParts.get(3).printBodyPartToTerminal();
        System.out.println("==========HUMAN RIGHT FOOT==========");
        guy.myBodyParts.get(4).printBodyPartToTerminal();
        System.out.println("==========HUMAN LEFT ARM==========");
        guy.myBodyParts.get(5).printBodyPartToTerminal();
        System.out.println("==========HUMAN RIGHT ARM==========");
        guy.myBodyParts.get(6).printBodyPartToTerminal();
        System.out.println("==========HUMAN LEFT HAND==========");
        guy.myBodyParts.get(7).printBodyPartToTerminal();
        System.out.println("==========HUMAN RIGHT HAND==========");
        guy.myBodyParts.get(8).printBodyPartToTerminal();
    }
}
