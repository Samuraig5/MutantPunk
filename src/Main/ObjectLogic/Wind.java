package Main.ObjectLogic;

import Main.Direction;
import Main.MathHelper;
import Main.RenderLogic.MapIcon;
import Main.WorldLogic.Cell;

import java.awt.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Wind extends Thing
{
    private Direction directionOfTravel;
    private float strengthOfSpread;
    private Wind windSource;

    public Wind(Cell spawnPoint, Direction directionOfTravel, float strengthOfSpread)
    {
        super();
        initialize(spawnPoint, directionOfTravel, strengthOfSpread, this);
    }
    public Wind(Cell spawnPoint, Direction directionOfTravel, float strengthOfSpread, Wind windSource)
    {
        super();
        setLocalMap(spawnPoint.getLocalMap());
        Cell fixedSpawnPoint = boundDiffrence(getMyCell(), windSource.getMyCell(), 1);

        initialize(fixedSpawnPoint, directionOfTravel, strengthOfSpread, windSource);
    }

    private void initialize(Cell spawnPoint, Direction directionOfTravel, float strengthOfSpread, Wind windSource)
    {
        setLocalMap(spawnPoint.getLocalMap());
        setMyCell(spawnPoint, Direction.NONE);

        setCollision(false);

        MapIcon mi = new MapIcon();
        mi.setSymbol('~');
        mi.setIconColour(Color.white);
        setMapIcon(mi);

        setRenderPriority(-1);

        setName("Gust of Wind");

        this.windSource = windSource;

        this.directionOfTravel = directionOfTravel;
        this.strengthOfSpread = strengthOfSpread;
        spreadWind(directionOfTravel, windSource);
    }

    public void spreadWind(Direction directionOfTravel, Wind windSource)
    {
        if (Math.random() > strengthOfSpread) {return;}
        List<Direction> targetDirections = new ArrayList<>();
        if (Math.random() < 0.33)
        {
            targetDirections.add(Direction.directionsAt90Degrees(directionOfTravel)[0]);
        }
        else if (Math.random() < 0.66) {
            targetDirections.add(Direction.directionsAt90Degrees(directionOfTravel)[1]);
        }
        else
        {
            targetDirections.add(Direction.directionsAt90Degrees(directionOfTravel)[0]);
            targetDirections.add(Direction.directionsAt90Degrees(directionOfTravel)[1]);
        }

        for (Direction targetDirection : targetDirections) {
            Cell targetCell = Direction.getCellInDirection(getMyCell(), targetDirection);
            new Wind(targetCell, directionOfTravel, weakenStrengthOfSpread(strengthOfSpread), windSource);
        }
    }

    private float weakenStrengthOfSpread(float startingPoint)
    {
        return (float)(startingPoint - (Math.random() * (startingPoint/10)));
    }

    private Cell boundDiffrence(Cell myCell, Cell sourceCell, int maxDiffrence)
    {
        if (sourceCell == null) {return myCell;}
        int x = myCell.getCoordinates()[0];
        int y = sourceCell.getCoordinates()[1];
        if (directionOfTravel == Direction.NORTH || directionOfTravel == Direction.SOUTH)
        {
            y = MathHelper.clamp(myCell.getCoordinates()[1],
                    sourceCell.getCoordinates()[1]-maxDiffrence,
                    sourceCell.getCoordinates()[1]+maxDiffrence);
        }
        if (directionOfTravel == Direction.EAST || directionOfTravel == Direction.WEST)
        {
            x = MathHelper.clamp(myCell.getCoordinates()[0],
                    sourceCell.getCoordinates()[0]-maxDiffrence,
                    sourceCell.getCoordinates()[0]+maxDiffrence);
        }
        return getLocalMap().getCell(x,y);
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
        if(windSource != null && this != windSource)
        {
            targetCell = boundDiffrence(targetCell, windSource.getMyCell(), 0);
        }
        setMyCell(targetCell, targetDirection);
    }
}
