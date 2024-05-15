package Main.ObjectLogic.BodyLogic;

import Main.AILogic.ThinkingThing;
import Main.Direction;
import Main.MathHelper;
import Main.ObjectLogic.ObjectTag;
import Main.ObjectLogic.Thing;
import Main.WorldLogic.Cell;
import Main.WorldLogic.GameWorld;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Person extends Thing
{
    public List<BodyPart> myBodyParts = new ArrayList<>();
    private ThinkingThing myThoughts;
    private boolean isPlayer;

    protected Person(boolean addThoughts)
    {
        super();
        if (addThoughts)
        {
            setMyThoughts(new ThinkingThing(this));
        }
        setRenderPriority(9);
    }

    public boolean isPlayer() {return isPlayer;}

    /**
     * Used to designate a person as being controlled by a player.
     * ONLY CALL THIS FROM CONSOLE PAINTER!
     * @param val
     */
    public void isPlayer(boolean val) {isPlayer = val;}

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

    public boolean isAlive()
    {
        if (getMyTotalStats()[BodyPartStat.CONSCIOUSNESS] > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private int getMovementCost()
    {
        return Math.round(100 / getMyTotalSpeed() * 333);

    }

    public boolean canMove() {
        if (!isAlive()) {
            return false;
        }

        if (getActionPoints() > getMovementCost())
        {
            return true;
        } else
        {
            return false;
        }
    }
    public void tryToMove(Direction d)
    {
        if (canMove())
        {
            changeActionPoints(-getMovementCost());
            doMove(d);
        }
    }
    private void doMove(Direction d)
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

    /**
     *
     * @param objectTag Object Tag of the item that is to be eaten
     * @return returns the body part that can digest the object tag. Null if none are available.
     */
    public BodyPartAbility getStomach(ObjectTag[] objectTag)
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
                            if (ot == input && !bpa.isFull())
                            {
                                return bpa;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public ObjectTag[] getDigestables()
    {
        List<ObjectTag> digestibles = new ArrayList<>();
        for (BodyPart bp:myBodyParts)
        {
            for (BodyPartAbility bpa:bp.getAbilities())
            {
                if (bpa.getAbilityTag() == AbilityTag.DIGESTION)
                {
                    digestibles.addAll(Arrays.asList(bpa.getRelatedObjectTags()));
                }
            }
        }
        return digestibles.toArray(new ObjectTag[0]);
    }

    public int getEatingCost()
    {
        return 50;
    }

    @Override
    public void doAction()
    {
        if (!isPlayer())
        {
            myThoughts.think();
        }
        for (BodyPart bp:myBodyParts) {
            bp.update();
        }
    }
}