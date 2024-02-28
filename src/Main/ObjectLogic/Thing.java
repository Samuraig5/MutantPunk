package Main.ObjectLogic;

import Main.RenderLogic.MapIcon;
import Main.WorldLogic.Cell;
import Main.WorldLogic.GameWorld;
import Main.WorldLogic.LocalMap;

public class Thing
{
    private GameWorld gw;
    private LocalMap lm;
    private Cell myCell;
    private MapIcon mapIcon;

    private String name;
    public LocalMap getLocalMap() {
        return lm;
    }

    public void setLocalMap(LocalMap lm)
    {
        if (this.lm != null)
        {
            this.lm.getLocalThings().remove(this);
        }
        this.lm = lm;
        this.lm.addPersonToLocalMap(this);
    }

    public Cell getMyCell() {
        return myCell;
    }

    public void setMyCell(Cell newCell) {
        if (myCell != null)
        {
            myCell.thingLeaves(this);
        }
        myCell = newCell;
        if (!newCell.getThings().contains(this)) {
            newCell.thingEnters(this);
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

    public GameWorld getGameWord() {
        return gw;
    }

    public void setGameWorld(GameWorld gw)
    {
        this.gw = gw;
    }
}
