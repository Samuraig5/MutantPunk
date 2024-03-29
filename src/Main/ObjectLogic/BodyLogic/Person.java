package Main.ObjectLogic.BodyLogic;

import Main.AILogic.ThinkingThing;
import Main.Direction;
import Main.ErrorHandler;
import Main.MathHelper;
import Main.ObjectLogic.ObjectTag;
import Main.ObjectLogic.Thing;
import Main.RenderLogic.MapIcon;
import Main.WorldLogic.Cell;
import Main.WorldLogic.GameWorld;
import Main.WorldLogic.LocalMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Person extends Thing
{
    public List<BodyPart> myBodyParts = new ArrayList<>();

    private ThinkingThing myThoughts;

    protected Person(boolean addThoughts)
    {
        super();
        if (addThoughts)
        {
            setMyThoughts(new ThinkingThing(this));
        }
        setRenderPriority(9);
    }

    public float[] getMyTotalStats()
    {
        float[] stats = new float[BodyPartStat.STATS_NUM];

        for (int i = 0; i < BodyPartStat.STATS_NUM; i++)
        {
            stats[i] = 0;

            for (BodyPart myBodyPart : myBodyParts) {
                stats[i] += myBodyPart.getStats()[i]; // Collect net stat from all body parts
            }
            for (BodyPart myBodyPart : myBodyParts) {
                stats[i] *= myBodyPart.getRawStats()[i][BodyPartStat.PERSON_MOD]; // Collect person mod from all body parts
            }
        }

        return stats;
    }

    public float getMyTotalSpeed()
    {
    return getMyTotalStats()[BodyPartStat.SPEED];
    }

    public void setGameWorld(GameWorld gw)
    {
        super.setGameWorld(gw);
        super.getGameWord().getAllCharacters().add(this);
    }

    public void move(Direction d)
    {
        Cell c = getMyCell();
        if (c == null) {return;}
        int[] coord = c.getCoordinates();
        int x = coord[0];
        int y = coord[1];
        Cell targetCell;
        switch (d)
        {
            case NORTH:
                targetCell = getLocalMap().getCell( MathHelper.boundedInteger(x,0,getLocalMap().getSize()[0]), MathHelper.boundedInteger(y,-1,getLocalMap().getSize()[1]));
                if (targetCell.isThereAThingWithCollision()) {
                    break;
                }
                setMyCell(targetCell, Direction.NORTH);
                break;
            case NORTH_EAST:
                targetCell = getLocalMap().getCell( MathHelper.boundedInteger(x,+1,getLocalMap().getSize()[0]), MathHelper.boundedInteger(y,-1,getLocalMap().getSize()[1]));
                if (targetCell.isThereAThingWithCollision()) {
                    break;
                }
                setMyCell(targetCell, Direction.NORTH_EAST);
                break;
            case EAST:
                targetCell = getLocalMap().getCell( MathHelper.boundedInteger(x,+1,getLocalMap().getSize()[0]), MathHelper.boundedInteger(y,0,getLocalMap().getSize()[1]));
                if (targetCell.isThereAThingWithCollision()) {
                    break;
                }
                setMyCell(targetCell, Direction.EAST);
                break;
            case SOUTH_EAST:
                targetCell = getLocalMap().getCell( MathHelper.boundedInteger(x,+1,getLocalMap().getSize()[0]), MathHelper.boundedInteger(y,+1,getLocalMap().getSize()[1]));
                if (targetCell.isThereAThingWithCollision()) {
                    break;
                }
                setMyCell(targetCell, Direction.SOUTH_EAST);
                break;
            case SOUTH:
                targetCell = getLocalMap().getCell( MathHelper.boundedInteger(x,0,getLocalMap().getSize()[0]), MathHelper.boundedInteger(y,+1,getLocalMap().getSize()[1]));
                if (targetCell.isThereAThingWithCollision()) {
                    break;
                }
                setMyCell(targetCell, Direction.SOUTH);
                break;
            case SOUTH_WEST:
                targetCell = getLocalMap().getCell( MathHelper.boundedInteger(x,-1,getLocalMap().getSize()[0]), MathHelper.boundedInteger(y,+1,getLocalMap().getSize()[1]));
                if (targetCell.isThereAThingWithCollision()) {
                    break;
                }
                setMyCell(targetCell, Direction.SOUTH_WEST);
                break;
            case WEST:
                targetCell = getLocalMap().getCell( MathHelper.boundedInteger(x,-1,getLocalMap().getSize()[0]), MathHelper.boundedInteger(y,0,getLocalMap().getSize()[1]));
                if (targetCell.isThereAThingWithCollision()) {
                    break;
                }
                setMyCell(targetCell, Direction.WEST);
                break;
            case NORTH_WEST:
                targetCell = getLocalMap().getCell( MathHelper.boundedInteger(x,-1,getLocalMap().getSize()[0]), MathHelper.boundedInteger(y,-1,getLocalMap().getSize()[1]));
                if (targetCell.isThereAThingWithCollision()) {
                    break;
                }
                setMyCell(targetCell, Direction.NORTH_WEST);
                break;
            case NONE:
                break;
        }
    }

    public ThinkingThing getMyThoughts()
    {
        return myThoughts;
    }

    public void setMyThoughts(ThinkingThing newThoughts)
    {
        myThoughts = newThoughts;
    }

    @Override
    public void newNeightbour(Thing t, Direction directionToSource) {

    }

    @Override
    public void thingLeftCell(Thing t, Direction directionToNewCell) {

    }

    public boolean canDigest(ObjectTag[] objectTag)
    {
        for (BodyPart bp:myBodyParts)
        {
            for (BodyPartAbility bpa:bp.getAbilities())
            {
                if (bpa.getAbilityTag() == AbilityTag.DIGESTION)
                {
                    for (ObjectTag ot:bpa.getRelatedObjectTags())
                    {
                        for (ObjectTag input:objectTag)
                        {
                            if (ot == input)
                            {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void doAction()
    {
        int eatingCost = 50;
        List<Thing> things = getMyCell().getThings();
        List<Thing> eatableThings = new ArrayList<>();
        if (getActionPoints() > eatingCost) {
            for (int i = 0; i < things.size(); i++) {
                if (canDigest(things.get(i).getTags())) {
                    things.get(i).destroy();
                    changeActionPoints(-eatingCost);
                    return;
                }
            }
        }

        int movementCost = Math.round(100/getMyTotalSpeed()*333);
        if (getActionPoints() < movementCost) {return;}
        changeActionPoints(-movementCost);
        myThoughts.thinkAboutMovement();
    }

}