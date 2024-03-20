package Main.AILogic;

import Main.ObjectLogic.BodyLogic.Person;
import Main.Direction;
import Main.ObjectLogic.Thing;

import java.util.List;

public class ThinkingThing
{
    private Person myPerson;

    public ThinkingThing(Person p)
    {
        myPerson = p;
    }

    public void think()
    {
        List<Thing> things = myPerson.getMyCell().getThings();
        if (myPerson.getActionPoints() > myPerson.getEatingCost()) {
            for (int i = 0; i < things.size(); i++) {
                if (myPerson.canDigest(things.get(i).getTags())) {
                    eat( things.get(i));
                    return;
                }
            }
        }

        int movementCost = Math.round(100/myPerson.getMyTotalSpeed()*333);
        if (myPerson.getActionPoints() < movementCost) {return;}
        myPerson.changeActionPoints(-movementCost);
        move();
    }

    public void eat(Thing target)
    {
        target.destroy();
        myPerson.changeActionPoints(-myPerson.getEatingCost());
    }

    private void move()
    {
        Direction d = Direction.getRandomDirection();
        if (myPerson.getMyCell() == null) {return;}
        myPerson.move(d);
    }
}
