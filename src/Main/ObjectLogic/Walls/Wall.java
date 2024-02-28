package Main.ObjectLogic.Walls;

import Main.ObjectLogic.Thing;
import Main.RenderLogic.MapIcon;

public class Wall extends Thing
{
    public Wall()
    {
        super();
        MapIcon mi = new MapIcon();
        mi.setSymbol('#');
        setMapIcon(mi);
        setName("Wall");
    }
}
