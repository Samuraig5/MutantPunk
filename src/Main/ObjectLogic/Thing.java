package Main.ObjectLogic;

import Main.RenderLogic.Logic.MapIcon;
import Main.Settings;
import Main.TimeLogic.Updatable;
import Main.WorldLogic.Cell;
import Main.WorldLogic.GameWorld;
import Main.WorldLogic.LocalMap;
import Main.Direction;


abstract public class Thing extends Updatable {
    private GameWorld gw;
    private LocalMap lm;
    private Cell myCell;
    private MapIcon mapIcon;
    private ObjectTag[] tags;
    private String name;
    private String description;
    private boolean hasCollision = true;
    public LocalMap getLocalMap() {
        return lm;
    }

    /**
     * Priority with which this thing is rendered. Higher number indicates a higher priority.
     */
    private int renderPriority;

    public void setLocalMap(LocalMap lm)
    {
        if (this.lm != null)
        {
            this.lm.getLocalThings().remove(this);
        }
        this.lm = lm;
        this.lm.addThingToLocalMap(this);
    }

    public Cell getMyCell() {
        return myCell;
    }

    public void setMyCell(Cell newCell, Direction directionOfTravel) {
        if (myCell != null)
        {
            myCell.thingLeaves(this, directionOfTravel);
        }
        myCell = newCell;
        if (!newCell.getThings().contains(this)) {
            newCell.thingEnters(this, Direction.invertDirection(directionOfTravel));
        }
        if (getLocalMap() != newCell.getLocalMap())
        {
            setLocalMap(newCell.getLocalMap());
        }
    }

    public MapIcon getMapIcon() {return mapIcon;}
    public void setMapIcon(MapIcon mi)
    {
        this.mapIcon = mi;
    }
    public void setName(String n)
    {
        name = n;
    }
    public String getName()
    {
        return name;
    }
    public void setDescription(String d) {description=d;}
    public String getDescription() {return description;}
    public GameWorld getGameWord() {
        return gw;
    }
    public void setGameWorld(GameWorld gw)
    {
        this.gw = gw;
    }
    public boolean hasCollision()
    {
        return hasCollision;
    }
    public void setCollision(boolean bool)
    {
        hasCollision = bool;
    }
    public int getRenderPriority()
    {
        return renderPriority;
    }
    public void setRenderPriority(int renderPriority)
    {
        this.renderPriority = renderPriority;
    }
    public abstract void newNeightbour(Thing t, Direction directionToSource);
    public abstract void thingLeftCell(Thing t, Direction directionToNewCell);
    public ObjectTag[] getTags() {return tags;}
    public void setTags(ObjectTag[] newTags) {tags = newTags;}

    public abstract void doAction();

    public void destroy()
    {
        myCell.thingLeaves(this, Direction.NONE);
        myCell = null;
        lm.removeThingFromLocalMap(this);
        lm = null;
        gw = null;
    }
}
