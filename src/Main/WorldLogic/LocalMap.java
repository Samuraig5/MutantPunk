package Main.WorldLogic;

import Main.Direction;
import Main.ErrorHandler;
import Main.MathHelper;
import Main.ObjectLogic.BodyLogic.Person;
import Main.ObjectLogic.Thing;
import Main.ObjectLogic.Wind;
import Main.RenderLogic.Logic.MapIcon;
import Main.Settings;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

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
    private MapIcon mapIcon;
    private List<Thing> localThings = new ArrayList<>();
    private Direction localWind = Direction.EAST;

    protected LocalMap(int[] xySize, GameWorld gameWorld, MapIcon mapIcon, String name, boolean spawnWind)
    {
        size[0] = xySize[0];
        size[1] = xySize[1];

        cells = new Cell[size[0]][size[1]];

        for (int x = 0; x < size[0]; x++) {
            for (int y = 0; y < size[1]; y++) {
                int[] xy = {x,y};
                cells[x][y] = new Cell(xy, this, new ArrayList<>());
            }
        }

        myWorld = gameWorld;
        mapName = name;
        this.mapIcon = mapIcon;
        if (spawnWind)
        {
            this.localWind = Direction.getRandomDirection(false);
        }
        else
        {
            this.localWind = Direction.NONE;
        }
    }

    public int[] getSize()
    {
        return size;
    }

    public Cell[][] getCells()
    {
        return cells;
    }

    public Cell getCell(int x, int y) {return cells[x][y];}

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
     * Returns the list of all persons currently on the local map. DO NOT use this to add persons to this list. Use addThingToLocalMap instead.
     * @return the list of all persons
     */
    public List<Thing> getLocalThings() {
        ErrorHandler.LogData(false, "The local map: " + mapName + " has " + localThings.size() + " persons.");
        return localThings;
    }

    public void addLocalThing(Thing t)
    {
        localThings.add(t);
    }

    public List<Person> getLocalPeople()
    {
        List<Thing> things = getLocalThings();
        List<Person> people = new ArrayList<>();

        for (int i = 0; i < things.size(); i++)
        {
            if (things.get(i) instanceof Person)
            {
                people.add((Person)things.get(i));
            }
        }

        return people;
    }

    public MapIcon getMapIcon()
    {
        if (mapIcon == null)
        {
            System.err.println("Tried to get MapIcon of local map but mapIcon is null");
            return new MapIcon('?');
        }
        return mapIcon;
    }

    public void addThingToLocalMap(Thing t)
    {
        localThings.add(t);
        if (t.getMyCell() == null) {
            Random r = new Random();
            int xCoord = r.nextInt(size[0]);
            int yCoord = r.nextInt(size[1]);
            cells[xCoord][yCoord].thingEnters(t, Direction.NONE);
        }
    }

    public void removeThingFromLocalMap(Thing t)
    {
        boolean removedThing = true;
        while (removedThing)
        {
            removedThing = localThings.remove(t);;
        }
    }

    public void updateTick()
    {
        float normalizeForTickSpeed = Settings.tickSpeed/100f;
        float normalizeForMapSize = (Settings.localMapSizeX +Settings.localMapSizeY)/100f;
        float chanceToSpawn = 0.1f * normalizeForTickSpeed * normalizeForMapSize;

        if (MathHelper.randomDecider(chanceToSpawn))
        {
            spawnWind();
        }
    }

    private void spawnWind()
    {
        if (localWind == Direction.NONE) {return;}

        int xMax = getMyWorld().getActiveLocalMap().getSize()[0];
        int yMax = getMyWorld().getActiveLocalMap().getSize()[1];

        int xRandOrigin = (int) (Math.random() * xMax);
        int yRandOrigin = (int) (Math.random() * yMax);


        int x = MathHelper.clamp(xRandOrigin,0,xMax-1);
        int y = MathHelper.clamp(yRandOrigin,0,yMax-1);

        new Wind(cells[x][y],localWind,2f);
    }
}
