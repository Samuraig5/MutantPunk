package Main.WorldLogic;

import Main.BodyLogic.Person;
import Main.ErrorHandler;

import java.util.ArrayList;
import java.util.List;

public class LocalMap
{
    private final int[] size = new int[2];
    private final Cell[][] cells;

    private GameWorld myWorld;
    private String mapName;
    private List<Person> localPersons = new ArrayList<>();

    protected LocalMap(int[] xySize, Cell[][] mapCells, GameWorld gameWorld, String name)
    {
        size[0] = xySize[0];
        size[1] = xySize[1];
        cells = mapCells;
        myWorld = gameWorld;
        mapName = name;
    }

    public int[] getSize()
    {
        return size;
    }

    public Cell[][] getCells()
    {
        return cells;
    }

    public GameWorld getMyWorld() {
        return myWorld;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public List<Person> getLocalPersons() {
        ErrorHandler.LogData(true, "The local map: " + mapName + " has " + localPersons.size() + " persons.");
        return localPersons;
    }
}
