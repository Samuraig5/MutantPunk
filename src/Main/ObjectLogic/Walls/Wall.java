package Main.ObjectLogic.Walls;

import Main.ObjectLogic.Thing;
import Main.RenderLogic.MapIcon;

import java.awt.*;

public class Wall extends Thing
{
    public Wall()
    {
        super();
        setCollision(true);

        MapIcon mi = new MapIcon();
        mi.setSymbol('■');
        mi.setIconColour(Color.lightGray);
        setMapIcon(mi);
        setName("Wall");
    }
}
