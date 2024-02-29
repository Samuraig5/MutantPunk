package Main.ObjectLogic;

import Main.Direction;
import Main.RenderLogic.MapIcon;
import Main.WorldLogic.Cell;

import java.awt.*;
import java.time.Duration;

public class Wind extends Thing
{
    private Direction directionOfTravel;
    private float strengthOfSpread;
    public Wind(Cell spawnPoint, Direction directionOfTravel, float strengthOfSpread)
    {
        super();

        setLocalMap(spawnPoint.getLocalMap());
        setMyCell(spawnPoint, Direction.NONE);
        
        setCollision(false);

        MapIcon mi = new MapIcon();
        mi.setSymbol('~');
        mi.setIconColour(Color.white);
        setMapIcon(mi);

        setRenderPriority(-1);

        setName("Gust of Wind");

        this.directionOfTravel = directionOfTravel;
        this.strengthOfSpread = strengthOfSpread;
        spreadWind(directionOfTravel);
    }

    public void spreadWind(Direction directionOfTravel)
    {
        if (Math.random() > strengthOfSpread) {return;}
        Direction targetDirection;
        if (Math.random() < 0.33)
        {
            targetDirection = Direction.directionsAt90Degrees(directionOfTravel)[0];
            
            Cell targetCell = Direction.getCellInDirection(getMyCell(), targetDirection);
            new Wind(targetCell, directionOfTravel, weakenStrengthOfSpread(strengthOfSpread));
        }
        else if (Math.random() < 0.66)
        {
            targetDirection = Direction.directionsAt90Degrees(directionOfTravel)[1];
            
            Cell targetCell = Direction.getCellInDirection(getMyCell(), targetDirection);
            new Wind(targetCell, directionOfTravel, weakenStrengthOfSpread(strengthOfSpread));        }
        else
        {
            targetDirection = Direction.directionsAt90Degrees(directionOfTravel)[0];
            
            Cell targetCell = Direction.getCellInDirection(getMyCell(), targetDirection);
            new Wind(targetCell, directionOfTravel, weakenStrengthOfSpread(strengthOfSpread));

            targetDirection = Direction.directionsAt90Degrees(directionOfTravel)[1];
            
            targetCell = Direction.getCellInDirection(getMyCell(), targetDirection);
            new Wind(targetCell, directionOfTravel, weakenStrengthOfSpread(strengthOfSpread));
        }
    }

    private float weakenStrengthOfSpread(float startingPoint)
    {
        return (float)(startingPoint - (Math.random() * (startingPoint/25)));
    }

    @Override
    public void newNeightbour(Thing t, Direction directionToSource) {

    }

    @Override
    public void thingLeftCell(Thing t, Direction directionToNewCell) {

    }

    @Override
    public void updateTick() {
        if (Math.random() < 0.05) {destroy(); return;}

        Direction targetDirection = directionOfTravel;


        if (Math.random() < 0.1)
        {
            if (Math.random() < 0.5)
            {
                targetDirection = Direction.directionsAt90Degrees(directionOfTravel)[0];
            }
            else
            {
                targetDirection = Direction.directionsAt90Degrees(directionOfTravel)[1];
            }
        }
        Cell targetCell = Direction.getCellInDirection(getMyCell(), targetDirection);
        setMyCell(targetCell, targetDirection);
    }
}
