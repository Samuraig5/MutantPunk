package Main.AILogic;

import Main.BodyLogic.Person;
import Main.Direction;

public class ThinkingThing
{
    private Person myPerson;

    public ThinkingThing(Person p)
    {
        myPerson = p;
    }

    public Person getMyPerson() {return myPerson;}

    public void doMovement()
    {
        System.out.println(myPerson.getMyCell());
        Direction d = Direction.getRandomDirection();
        if (myPerson.getMyCell() == null) {return;}
        myPerson.move(d);
    }
}
