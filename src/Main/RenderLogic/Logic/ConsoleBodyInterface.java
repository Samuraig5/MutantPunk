package Main.RenderLogic.Logic;

import Main.MathHelper;
import Main.ObjectLogic.BodyLogic.BodyFileDecoder;
import Main.ObjectLogic.BodyLogic.BodyPart;
import Main.ObjectLogic.BodyLogic.BodyPartStat;
import Main.ObjectLogic.BodyLogic.Person;
import Main.RenderLogic.Console;
import Main.WorldLogic.LocalMap;

import java.util.ArrayList;
import java.util.List;

public class ConsoleBodyInterface
{
    Console c;
    public ConsoleBodyInterface(Console console)
    {
        c = console;
    }

    public Person spawnPerson(String bias, String randomness, String filePath, LocalMap lm)
    {
        try
        {
            int b = Integer.parseInt(bias);
            int r = Integer.parseInt(randomness);
            return BodyFileDecoder.SpawnNewPersonFromFile(filePath, b, r, lm);
        }
        catch (Exception e)
        {
            throw new RuntimeException("WARNING: The second and third parameters of spawnPerson must be integer numbers");
        }
    }

    public String[][] openPersonView(Person p)
    {
        float[] stats = p.getMyTotalStats();
        String[][] result = new String[BodyPartStat.STATS_NUM][2];

        //Description Value
        result[0][0] = "Blood Capacity";
        result[1][0] = "Blood Generation";
        result[2][0] = "Blood Needed";
        result[3][0] = "Energy Capacity";
        result[4][0] = "Energy Generation";
        result[5][0] = "Energy Needed";
        result[6][0] = "Max Health";
        result[7][0] = "Regen Rate";
        result[8][0] = "Regen Limit";
        result[9][0] = "Armour";
        result[10][0] = "Size";
        result[11][0] = "Attachment Capacity";
        result[12][0] = "Speed";
        result[13][0] = "Consciousness";
        result[14][0] = "Grabbing Slots";
        result[15][0] = "Sight";


        for (int i = 0; i < stats.length; i++)
        {
            result[i][1] = stats[i] + "";
        }

        return result;
    }

    public ColouredString[][] getBodyView(Person p)
    {
        List<BodyPart> bodyParts = p.myBodyParts;
        ColouredString[][] result = new ColouredString[bodyParts.size()][3];

        List<ColouredString[]> children = addChildrenBodPartsToView(bodyParts.get(0), "");

        for (int j = 0; j < children.size(); j++)
        {
            result[j][0] = new ColouredString(MathHelper.indexToLetter(j) + ": ");
            result[j][1] = children.get(j)[0];
            result[j][2] = children.get(j)[1];
        }
        return result;
    }

    private List<ColouredString[]> addChildrenBodPartsToView(BodyPart bp, String depth)
    {
        String depthSymbol = "> ";
        if (depth.isEmpty())
        {
            depthSymbol = "@ ";
        }

        List<ColouredString[]> result = new ArrayList<>();
        result.add(
                new ColouredString[]{
                        new ColouredString(depth + depthSymbol),
                        new ColouredString(bp.getName(), bp.getColourBasedOnHealth())});

        for (BodyPart nextbp:bp.getAttachedBodyParts())
        {
            List<ColouredString[]> children = addChildrenBodPartsToView(nextbp, depth+"¦ ");
            result.addAll(children);
        }
        return result;
    }

    /**
     * Final / Gross / Mod / UpGross / UpMod / PersonMod
     */
    public String[][] displayBodyPartStats(BodyPart bp)
    {
        String[][] result = new String[BodyPartStat.STATS_NUM][BodyPartStat.MODS_NUM+2];// +2 for description and final

        //Description Value
        result[0][0] = "Blood Capacity";
        result[1][0] = "Blood Generation";
        result[2][0] = "Blood Needed";
        result[3][0] = "Energy Capacity";
        result[4][0] = "Energy Generation";
        result[5][0] = "Energy Needed";
        result[6][0] = "Max Health";
        result[7][0] = "Regen Rate";
        result[8][0] = "Regen Limit";
        result[9][0] = "Armour";
        result[10][0] = "Size";
        result[11][0] = "Attachment Capacity";
        result[12][0] = "Speed";
        result[13][0] = "Consciousness";
        result[14][0] = "Grabbing Slots";
        result[15][0] = "Sight";


        //Final Value
        for (int i = 0; i < BodyPartStat.STATS_NUM; i++) {
            result[i][1] = bp.getStats()[i] + "";
        }

        for (int i = 0; i < BodyPartStat.STATS_NUM; i++) {
            for (int j = 0; j < BodyPartStat.MODS_NUM; j++) {
                result[i][j+2] = bp.getRawStats()[i][j] + "";
            }
        }

        return result;
    }
}
