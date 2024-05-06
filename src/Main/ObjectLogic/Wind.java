package Main.ObjectLogic;

import Main.Direction;
import Main.RenderLogic.Logic.MapIcon;
import Main.WorldLogic.Cell;

import java.awt.*;

public class Wind extends Thing
{
    private Direction directionOfTravel;
    private float strengthOfSpread;

    public Wind(Cell spawnPoint, Direction directionOfTravel, float strengthOfSpread)
    {
        super();

        initialize(spawnPoint, directionOfTravel, strengthOfSpread);

        spreadWind(directionOfTravel, Direction.invertDirection(directionOfTravel), Direction.directionsAt90Degrees(directionOfTravel)[0]);
        spreadWind(directionOfTravel, Direction.invertDirection(directionOfTravel), Direction.directionsAt90Degrees(directionOfTravel)[0]);
        spreadWind(directionOfTravel, Direction.directionsAt90Degrees(directionOfTravel)[0]);
        spreadWind(directionOfTravel, Direction.directionsAt90Degrees(directionOfTravel)[1]);
    }
    public Wind(Cell spawnPoint, Direction directionOfTravel, float strengthOfSpread, Direction sourceOfSpread)
    {
        super();
        setLocalMap(spawnPoint.getLocalMap());
        //Cell fixedSpawnPoint = boundDiffrence(getMyCell(), windSource.getMyCell(), 1);
        Cell fixedSpawnPoint = spawnPoint;

        initialize(fixedSpawnPoint, directionOfTravel, strengthOfSpread);
        spreadWind(directionOfTravel, sourceOfSpread);
    }

    private void initialize(Cell spawnPoint, Direction directionOfTravel, float strengthOfSpread)
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
        setDescription("This is a nice gust of wind");
        setTags(new ObjectTag[] {ObjectTag.GASEOUS});


        this.directionOfTravel = directionOfTravel;
        this.strengthOfSpread = strengthOfSpread;
    }

    public void spreadWind(Direction directionOfTravel, Direction directionOfSpread)
    {
        spreadWind(directionOfTravel, directionOfSpread, directionOfSpread);
    }
    public void spreadWind(Direction directionOfTravel, Direction directionOfTranslation, Direction directionOfPropagation)
    {
        if (Math.random() > strengthOfSpread) {return;}

        Cell targetCell = Direction.getCellInDirection(getMyCell(), directionOfTranslation);
        new Wind(targetCell, directionOfTravel, weakenStrengthOfSpread(strengthOfSpread), directionOfPropagation);
    }

    private float weakenStrengthOfSpread(float startingPoint)
    {
        return (float)(startingPoint - (Math.random() * (startingPoint/10)));
    }

    @Override
    public void newNeightbour(Thing t, Direction directionToSource) {

    }

    @Override
    public void thingLeftCell(Thing t, Direction directionToNewCell) {

    }

    @Override
    public void doAction() {
        int actionCost = 250;
        if (getActionPoints() < actionCost) {return;}
        if (Math.random() < 0.05) {destroy(); return;}

        changeActionPoints(-actionCost);

        Direction targetDirection = Direction.coneCastChance(directionOfTravel, 0.9f);
        Cell targetCell = Direction.getCellInDirection(getMyCell(), targetDirection);
        setMyCell(targetCell, targetDirection);
    }
}
