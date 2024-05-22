package Main.AILogic;

import Main.AbilityLogic.Ability;
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
        tryToEat();
        if (myPerson.canMove())
        {
            move();
        }
    }

    private void tryToEat()
    {
        List<Thing> things = myPerson.getMyCell().getThings();

        for (int i = 0; i < things.size(); i++) {
            List<Ability> stomachs = myPerson.getStomach(things.get(i).getTags());
            if (stomachs != null) {
                Ability.getMinCost(stomachs).activeEffect(myPerson, things.get(i));
                return;
            }
        }
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
        myPerson.tryToMove(d);
    }
}
