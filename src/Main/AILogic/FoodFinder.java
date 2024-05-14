package Main.AILogic;

import Main.Direction;
import Main.ObjectLogic.ObjectTag;
import Main.ObjectLogic.Thing;
import Main.WorldLogic.Cell;

import java.util.*;

public class FoodFinder
{
    public static Direction directionToNearestFood(Cell[][] grid, Cell startCell, int lookDistance, ObjectTag[] targetObjectTag)
    {
        List<Cell> targetCells = findFoodCells(grid,startCell,lookDistance,targetObjectTag);
        return findDirection(grid, targetCells, startCell);
    }
    private static List<Cell> findFoodCells(Cell[][] grid, Cell startCell, int lookDistance, ObjectTag[] targetObjectTags) {
        List<Cell> foodCells = new ArrayList<>();

        int startX = startCell.getCoordinates()[0];
        int startY = startCell.getCoordinates()[1];
        int maxX = grid.length;
        int maxY = grid[0].length;

        for (int i = Math.max(0, startX - lookDistance); i < Math.min(maxX, startX + lookDistance); i++)
        {
            for (int j = Math.max(0, startY - lookDistance); j < Math.min(maxY, startY + lookDistance); j++)
            {
                if (Math.abs(startX - i) + Math.abs(startY - j) <= lookDistance)
                {
                    Cell currentCell = grid[i][j];
                    for (Thing thing : currentCell.getThings()) {
                        for (ObjectTag tag : targetObjectTags)
                        {
                            if (Arrays.asList(thing.getTags()).contains(tag))
                            {
                                foodCells.add(currentCell);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return foodCells;
    }

    private static Direction findDirection(Cell[][] grid, List<Cell> targetCells, Cell startCell) {
        Direction directionToNearestTarget = null;
        int minDistance = Integer.MAX_VALUE;

        for (Cell targetCell : targetCells) {
            List<Cell> path = AStar.findPath(grid, startCell, targetCell);
            if (path != null && !path.isEmpty()) {
                int distance = path.size() - 1; // Subtract 1 because the start cell is included in the path
                if (distance < minDistance) {
                    minDistance = distance;
                    if (path.size() > 1)
                    {
                        directionToNearestTarget = calculateDirection(startCell, path.get(1)); // Get the second cell in the path (first step)
                    }
                    else
                    {
                        directionToNearestTarget = Direction.NONE;
                    }
                }
            }
        }

        return directionToNearestTarget;
    }

    private static Direction calculateDirection(Cell startCell, Cell nextCell) {
        int[] startCoords = startCell.getCoordinates();
        int[] nextCoords = nextCell.getCoordinates();

        int dx = nextCoords[0] - startCoords[0];
        int dy = nextCoords[1] - startCoords[1];

        if (dx == 1) {
            if (dy == 1) {
                return Direction.SOUTH_EAST;
            } else if (dy == 0) {
                return Direction.EAST;
            } else {
                return Direction.NORTH_EAST;
            }
        } else if (dx == -1) {
            if (dy == 1) {
                return Direction.SOUTH_WEST;
            } else if (dy == 0) {
                return Direction.WEST;
            } else {
                return Direction.NORTH_WEST;
            }
        } else {
            if (dy == 1) {
                return Direction.SOUTH;
            } else {
                return Direction.NORTH;
            }
        }
    }
}

