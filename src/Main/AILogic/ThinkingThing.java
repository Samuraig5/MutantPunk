package Main.AILogic;

import Main.ObjectLogic.BodyLogic.Person;
import Main.Direction;

public class ThinkingThing
{
    private Person myPerson;

    public ThinkingThing(Person p)
    {
        myPerson = p;
    }

    public Person getMyPerson() {return myPerson;}

    public void thinkAboutMovement()
    {
        Direction d = Direction.getRandomDirection();
        if (myPerson.getMyCell() == null) {return;}
        myPerson.move(d);
    }
}
