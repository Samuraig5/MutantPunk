package Main;

import Main.WorldLogic.Cell;
import Main.WorldLogic.LocalMap;

import java.util.Random;

public enum Direction
{
    NORTH,
    NORTH_EAST,
    EAST,
    SOUTH_EAST,
    SOUTH,
    SOUTH_WEST,
    WEST,
    NORTH_WEST,
    NONE;

    public static Direction getRandomDirection()
    {
        return getRandomDirection(true);
    }
    public static Direction getRandomDirection(boolean allowNone) {
        Direction[] values = Direction.values();
        int length = values.length;
        Direction res;
        if (!allowNone)
        {
            do {
                int randIndex = new Random().nextInt(length);
                res = values[randIndex];
            } while (res == Direction.NONE);
        }
        else
        {
            int randIndex = new Random().nextInt(length);
            res = values[randIndex];
        }

        return res;
    }

    public static Direction invertDirection(Direction d){
        switch (d)
        {
            case NORTH:
                return SOUTH;
            case NORTH_EAST:
                return SOUTH_WEST;
            case EAST:
                return WEST;
            case SOUTH_EAST:
                return NORTH_WEST;
            case SOUTH:
                return NORTH;
            case SOUTH_WEST:
                return NORTH_EAST;
            case WEST:
                return EAST;
            case NORTH_WEST:
                return SOUTH_EAST;
        }
        return d;
    }

    /**
     * Will return the two directions that are 90° to the given direction.
     * @param d Input direction.
     * @return Output direction. The first element is 90° clockwise, the second element is 90° anti-clockwise.
     */
    public static Direction[] directionsAt90Degrees(Direction d) {
        Direction[] result = new Direction[2];
        switch (d)
        {
            case NORTH:
                result[0] = EAST;
                result[1] = WEST;
                return result;
            case NORTH_EAST:
                result[0] = SOUTH_EAST;
                result[1] = NORTH_WEST;
                return result;
            case EAST:
                result[0] = SOUTH;
                result[1] = NORTH;
                return result;
            case SOUTH_EAST:
                result[0] = SOUTH_WEST;
                result[1] = NORTH_EAST;
                return result;
            case SOUTH:
                result[0] = WEST;
                result[1] = EAST;
                return result;
            case SOUTH_WEST:
                result[0] = NORTH_WEST;
                result[1] = SOUTH_EAST;
                return result;
            case WEST:
                result[0] = NORTH;
                result[1] = SOUTH;
                return result;
            case NORTH_WEST:
                result[0] = NORTH_EAST;
                result[1] = SOUTH_WEST;
                return result;
        }
        result[0] = d;
        result[1] = d;
        return result;
    }

    static public Direction[] coneCastAll(Direction d)
    {
        Direction[] directionsOptions = new Direction[3];
        switch (d)
        {
            case NORTH:
                directionsOptions[0] = NORTH_WEST;
                directionsOptions[1] = NORTH;
                directionsOptions[2] = NORTH_EAST;
                break;
            case NORTH_EAST:
                directionsOptions[0] = NORTH;
                directionsOptions[1] = NORTH_EAST;
                directionsOptions[2] = EAST;
                break;
            case EAST:
                directionsOptions[0] = NORTH_EAST;
                directionsOptions[1] = EAST;
                directionsOptions[2] = SOUTH_EAST;
                break;
            case SOUTH_EAST:
                directionsOptions[0] = EAST;
                directionsOptions[1] = SOUTH_EAST;
                directionsOptions[2] = SOUTH;
                break;
            case SOUTH:
                directionsOptions[0] = SOUTH_EAST;
                directionsOptions[1] = SOUTH;
                directionsOptions[2] = SOUTH_WEST;
                break;
            case SOUTH_WEST:
                directionsOptions[0] = SOUTH;
                directionsOptions[1] = SOUTH_WEST;
                directionsOptions[2] = WEST;
                break;
            case WEST:
                directionsOptions[0] = SOUTH_WEST;
                directionsOptions[1] = WEST;
                directionsOptions[2] = NORTH_WEST;
                break;
            case NORTH_WEST:
                directionsOptions[0] = WEST;
                directionsOptions[1] = NORTH_WEST;
                directionsOptions[2] = NORTH;
                break;
        }
        return directionsOptions;
    }

    static public Direction coneCastChance (Direction d, float accuracy)
    {
        Direction[] directionsOptions = coneCastAll(d);
        if (MathHelper.randomDecider(accuracy))
        {
            return directionsOptions[1];
        }
        else if (MathHelper.fiftyFifty())
        {
            return directionsOptions[0];
        }
        else
        {
            return directionsOptions[2];
        }
    }

    public static Cell getCellInDirection(Cell origin, Direction direction)
    {
        if (origin == null) {throw new RuntimeException("Origin Cell is null");}
        LocalMap lm = origin.getLocalMap();
        if (lm == null) {throw new RuntimeException("Origin Cell's Local Map is null");}
        int[] coord = origin.getCoordinates();
        int x = coord[0];
        int y = coord[1];
        int maxX = lm.getSize()[0];
        int maxY = lm.getSize()[1];
        Cell targetCell;
        switch (direction)
        {
            case NORTH:
                targetCell = lm.getCell(
                        MathHelper.boundedInteger(x,0,maxX),
                        MathHelper.boundedInteger(y,-1,maxY));
                return targetCell;
            case NORTH_EAST:
                targetCell = lm.getCell(
                        MathHelper.boundedInteger(x,+1,maxX),
                        MathHelper.boundedInteger(y,-1,maxY));
                return targetCell;
            case EAST:
                targetCell = lm.getCell(
                        MathHelper.boundedInteger(x,+1,maxX),
                        MathHelper.boundedInteger(y,0,maxY));
                return targetCell;
            case SOUTH_EAST:
                targetCell = lm.getCell(
                        MathHelper.boundedInteger(x,+1,maxX),
                        MathHelper.boundedInteger(y,+1,maxY));
                return targetCell;
            case SOUTH:
                targetCell = lm.getCell(
                        MathHelper.boundedInteger(x,0,maxX),
                        MathHelper.boundedInteger(y,+1,maxY));
                return targetCell;
            case SOUTH_WEST:
                targetCell = lm.getCell(
                        MathHelper.boundedInteger(x,-1,maxX),
                        MathHelper.boundedInteger(y,+1,maxY));
                return targetCell;
            case WEST:
                targetCell = lm.getCell(
                        MathHelper.boundedInteger(x,-1,maxX),
                        MathHelper.boundedInteger(y,0,maxY));
                return targetCell;
            case NORTH_WEST:
                targetCell = lm.getCell(
                        MathHelper.boundedInteger(x,-1,maxX),
                        MathHelper.boundedInteger(y,-1,maxY));
                return targetCell;
        }
        return origin;
    }
}
