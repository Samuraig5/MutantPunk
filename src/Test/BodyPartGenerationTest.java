package Test;

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

        guy.printPersonToTerminal();
    }
}
