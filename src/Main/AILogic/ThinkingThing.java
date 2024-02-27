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
        myPerson.move(Direction.getRandomDirection());
    }
}
