package Main.RenderLogic;

import Main.ObjectLogic.BodyLogic.BodyFileDecoder;
import Main.ObjectLogic.BodyLogic.BodyPart;
import Main.ObjectLogic.BodyLogic.BodyPartStat;
import Main.ObjectLogic.BodyLogic.Person;
import Main.ErrorHandler;
import Main.WorldLogic.GameWorld;
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
            Person newCharacter = BodyFileDecoder.SpawnNewPersonFromFile(filePath, b, r);
            newCharacter.setGameWorld(lm.getMyWorld());
            newCharacter.setLocalMap(lm);
            return  newCharacter;
        }
        catch (Exception e)
        {
            throw new RuntimeException("WARNING: The second and third parameters of spawnPerson must be integer numbers");
        }
    }

    public void listAllPersons(GameWorld gw)
    {
        List<String> allCharacterNames = new ArrayList<>();
        for (Person p:gw.getAllCharacters())
        {
            allCharacterNames.add(p.getName());
        }
        //c.clir.renderList(allCharacterNames, "Current Characters", new AllCharactersInWorldMenu(c, gw));
    }

    public List<String> openPersonView(Person p)
    {
        int grossPadding = 7;
        int modPadding = 10;
        int finalPadding = 7;

        List<String> s = new ArrayList<>();

        s.add(" STATS             ¦ GROSS ¦ MODIFIER ¦ TOTAL ¦");
        s.add("Blood Capacity:    ¦" + generateStatLine(p.GetMyTotalStats()[0],grossPadding,modPadding,finalPadding));
        s.add("Blood Generation:  ¦" + generateStatLine(p.GetMyTotalStats()[1],grossPadding,modPadding,finalPadding));
        s.add("Blood Needed:      ¦" + generateStatLine(p.GetMyTotalStats()[2],grossPadding,modPadding,finalPadding));
        s.add("Energy Capacity:   ¦" + generateStatLine(p.GetMyTotalStats()[3],grossPadding,modPadding,finalPadding));
        s.add("Energy Generation: ¦" + generateStatLine(p.GetMyTotalStats()[4],grossPadding,modPadding,finalPadding));
        s.add("Energy Needed:     ¦" + generateStatLine(p.GetMyTotalStats()[5],grossPadding,modPadding,finalPadding));
        s.add("Size:              ¦" + generateStatLine(p.GetMyTotalStats()[6],grossPadding,modPadding,finalPadding));
        s.add("Speed:             ¦" + generateStatLine(p.GetMyTotalStats()[7],grossPadding,modPadding,finalPadding));
        s.add("Consciousness:     ¦" + generateStatLine(p.GetMyTotalStats()[8],grossPadding,modPadding,finalPadding));
        s.add("Sight:             ¦" + generateStatLine(p.GetMyTotalStats()[9],grossPadding,modPadding,finalPadding));
        return s;
    }
    private String rightpad(int text, int length) {
        return String.format("%-" + length + "." + length + "s", text);
    }
    private String rightpad(String text, int length) {
        return String.format("%-" + length + "." + length + "s", text);
    }
    private String expressInPercent(int f)
    {
        return f*100 + "%";
    }
    private String generateStatLine(float[] stat, int grossPadding, int modPadding, int finalPadding)
    {
        return rightpad((int)stat[0],grossPadding) + "¦" + rightpad(expressInPercent((int)stat[1]),modPadding) + "¦" + rightpad((int)stat[2],finalPadding) + "¦";
    }
    private String generateStatLine(float[] stat, int grossPadding, int modPadding, int finalPadding, int parentGrossPadding, int parentModPadding, int personModPadding)
    {
        String s = rightpad((int)stat[0],grossPadding) + "¦";
        s = s + rightpad(expressInPercent((int)stat[1]),modPadding) + "¦";
        s = s + rightpad((int)stat[2],finalPadding) + "¦";
        s = s + rightpad((int)stat[3],parentGrossPadding) + "¦";
        s = s + rightpad(expressInPercent((int)stat[4]), parentModPadding) + "¦";
        s = s + rightpad(expressInPercent((int)stat[5]), personModPadding) + "¦";

        return s;
    }
    public List<String> openBodyView(Person p)
    {
        List<String> list = new ArrayList<>();
        addChildrenBodyPartsToList(p.myBodyParts.get(0), list, "");
        return list;
        //c.clir.renderList(list, p.getName()+"'s Body", new BodyMenu(c,p));
    }
    private void addChildrenBodyPartsToList(BodyPart bp, List<String> list, String depth)
    {
        ErrorHandler.LogData(false,"Number of attached BodyParts: " + bp.getAttachedBodyParts().size());
        list.add(depth + "> " + bp.getName());
        for (BodyPart nextbp:bp.getAttachedBodyParts())
        {
            addChildrenBodyPartsToList(nextbp, list, depth+"¦ ");
        }
    }

    public void openBodyPartView(BodyPart bp)
    {
        c.cc.clear();

        List<String> topBarMenuBlockList = new ArrayList<>();
        topBarMenuBlockList.add("Deal one damage");
        topBarMenuBlockList.add("Allow to regenerate");

        //LEGACY_displayBodyPartStats(bp);

        List<String> bodyPartList = new ArrayList<>();
        assembleBodyPartRelations(bp, bodyPartList);
        //c.clir.appendList(bodyPartList, bp.getMyPerson().getName()+"'s "+bp.getName(), new BodyPartMenu(c,bp), topBarMenuBlockList);

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

    private void assembleBodyPartRelations(BodyPart bp, List<String> list)
    {
        if (bp.getMyPerson() != null)
        {
            list.add(bp.getMyPerson().getName()  + "\n" + "" + "\n" + "My parent body parts:");
        }
        else
        {
            list.add("N/A"  + "\n" + "" + "\n" + "My parent body part:");
        }
        if (bp.getParentBodyPart() != null)
        {
            list.add(bp.getParentBodyPart().getName()  + "\n" + "" + "\n" + "Attached body parts: ");
        }
        else
        {
            list.add("N/A"  + "\n" + "" + "\n" + "Attached body parts: ");
        }
        for (BodyPart nextbp:bp.getAttachedBodyParts())
        {
            list.add(nextbp.getName());
        }
    }
}
