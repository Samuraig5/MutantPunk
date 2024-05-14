package Main.AILogic;

import Main.ObjectLogic.ObjectTag;
import Main.WorldLogic.Cell;

import java.util.*;

public class AStar {
    public static List<Cell> findPath(Cell[][] grid, Cell start, Cell goal)
    {
        PriorityQueue<Cell> openSet = new PriorityQueue<>(Comparator.comparingInt(cell -> getF(cell, start, goal)));
        Map<Cell, Cell> cameFrom = new HashMap<>();
        Map<Cell, Integer> gScore = new HashMap<>();

        gScore.put(start, 0);
        openSet.add(start);

        while (!openSet.isEmpty()) {
            Cell current = openSet.poll();

            if (current == goal) {
                return reconstructPath(cameFrom, current);
            }

            for (Cell neighbor : getNeighbors(grid, current)) {
                if (gScore.getOrDefault(current, Integer.MAX_VALUE) + 1 < gScore.getOrDefault(neighbor, Integer.MAX_VALUE) &&
                        !neighbor.isThereAThingWithCollision()) {
                    cameFrom.put(neighbor, current);
                    gScore.put(neighbor, gScore.get(current) + 1);

                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }

        return null; // Path not found
    }

    private static List<Cell> reconstructPath(Map<Cell, Cell> cameFrom, Cell current) {
        List<Cell> path = new ArrayList<>();
        path.add(current);
        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            path.add(current);
        }
        Collections.reverse(path);
        return path;
    }

    private static int heuristicCost(Cell a, Cell b) {
        // Manhattan distance heuristic
        return Math.abs(a.getCoordinates()[0] - b.getCoordinates()[0]) +
                Math.abs(a.getCoordinates()[1] - b.getCoordinates()[1]);
    }

    private static List<Cell> getNeighbors(Cell[][] grid, Cell cell) {
        List<Cell> neighbors = new ArrayList<>();
        int x = cell.getCoordinates()[0];
        int y = cell.getCoordinates()[1];
        int maxX = grid.length;
        int maxY = grid[0].length;

        if (x > 0) {
            neighbors.add(grid[x - 1][y]);
        }
        if (x < maxX - 1) {
            neighbors.add(grid[x + 1][y]);
        }
        if (y > 0) {
            neighbors.add(grid[x][y - 1]);
        }
        if (y < maxY - 1) {
            neighbors.add(grid[x][y + 1]);
        }

        return neighbors;
    }

    private static int getF(Cell current, Cell start, Cell goal) {
        return getG(current, start) + heuristicCost(current, goal);
    }

    private static int getG(Cell current, Cell start) {
        // Assuming movement cost is always 1
        return Math.abs(current.getCoordinates()[0] - start.getCoordinates()[0]) +
                Math.abs(current.getCoordinates()[1] - start.getCoordinates()[1]);
    }
}
