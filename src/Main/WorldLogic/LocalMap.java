package Main.WorldLogic;

import Main.BodyLogic.Person;
import Main.ErrorHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LocalMap
{
    /**
     * size[0] = x
     * size[1] = y
     */
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

    /**
     * Returns the list of all persons currently on the local map. DO NOT use this to add persons to this list. Use addPersonToLocalMap instead.
     * @return the list of all persons
     */
    public List<Person> getLocalPersons() {
        ErrorHandler.LogData(true, "The local map: " + mapName + " has " + localPersons.size() + " persons.");
        return localPersons;
    }

    public void addPersonToLocalMap(Person p)
    {
        localPersons.add(p);
        Random r = new Random();
        int xCoord = r.nextInt(size[0]);
        int yCoord = r.nextInt(size[1]);
        cells[xCoord][yCoord].personEnters(p);
    }
}
