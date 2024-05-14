package Main.AILogic;

import Main.ObjectLogic.BodyLogic.BodyPartAbility;
import Main.ObjectLogic.BodyLogic.BodyPartStat;
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
                BodyPartAbility stomach = myPerson.getStomach(things.get(i).getTags());
                if (stomach != null) {
                    eat(stomach, things.get(i));
                    return;
                }
            }
        }

        int movementCost = Math.round(100/myPerson.getMyTotalSpeed()*333);
        if (myPerson.getActionPoints() < movementCost) {return;}
        myPerson.changeActionPoints(-movementCost);
        move();
    }

    public void eat(BodyPartAbility stomach, Thing target)
    {
        //TODO: Implement fill level to be tied to size of target (or something)
        stomach.addToCurrentFillLevel(10);
        target.destroy();
        myPerson.changeActionPoints(-myPerson.getEatingCost());
    }

    private void move()
    {
        Direction d;
        if (myPerson.getMyTotalStats()[BodyPartStat.CONSCIOUSNESS] >= 75)
        {
            d = FoodFinder.directionToNearestFood(myPerson.getLocalMap().getCells(), myPerson.getMyCell(), 5, myPerson.getDigestables());
            if (d == null)
            {
                d = Direction.getRandomDirection();
            }
        }
        else
        {
            d = Direction.getRandomDirection();
        }

        if (myPerson.getMyCell() == null) {return;}
        myPerson.move(d);
    }
}
