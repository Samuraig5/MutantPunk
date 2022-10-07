package Test;

import Main.BodyLogic.BodyPart;
import Main.WorldLogic.Human;

public class BodyPartDestructionTest {

    public static void main(String[] args)
    {
        //We generate a Human as normal
        Human guy = new Human();
        guy.instantiatePerson("Test Guy",0,0);
        System.out.println("--> Number of Body Parts: " + guy.myBodyParts.size());

        for (BodyPart bp:guy.myBodyParts)
        {
            bp.printBodyPartToTerminal();
        }
        guy.printPersonToTerminal();

        System.out.println("================ BEGIN OF DAMAGE TEST ================");
        BodyPart bp = guy.myBodyParts.get(1);
        System.out.println("> DEALING 10 DAMAGE TO LEFT LEG <");
        bp.doDamage(10);
        printNameAndHealth(bp);

        System.out.println("> ALLOWING THE LEFT LEG TO HEAL <");
        bp.healDamage();
        printNameAndHealth(bp);

        System.out.println("> DEALING 40 DAMAGE TO LEFT LEG <");
        bp.doDamage(40);
        printNameAndHealth(bp);

        System.out.println("> ALLOWING THE LEFT LEG TO HEAL, BUT IT IS UNABLE TO DUE TO DAMAGE <");
        bp.healDamage();
        printNameAndHealth(bp);

        System.out.println("> DESTROYING LEFT LEG <");
        bp.doDamage(80);
        printNameAndHealth(bp);
        System.out.println("================ END OF DAMAGE TEST ================");

        System.out.println("--> Number of Body Parts: " + guy.myBodyParts.size());

        for (BodyPart bps:guy.myBodyParts)
        {
            bps.printBodyPartToTerminal();
        }
        guy.printPersonToTerminal();
    }

    static void printNameAndHealth(BodyPart bp)
    {
        System.out.println("# Name: " + bp.name);
        System.out.println("    Health: " + bp.currentHealth + "/" + bp.maxHealth);
    }
}
