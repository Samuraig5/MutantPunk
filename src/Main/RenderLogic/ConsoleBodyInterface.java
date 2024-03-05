package Main.RenderLogic;

import Main.ObjectLogic.BodyLogic.BodyFileDecoder;
import Main.ObjectLogic.BodyLogic.BodyPart;
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
    public void listAllPersons(LocalMap lm)
    {
        List<String> allCharacterNames = new ArrayList<>();
        for (Person p:lm.getLocalPeople())
        {
            allCharacterNames.add(p.getName());
        }
        //c.clir.renderList(allCharacterNames, "Current Characters", new AllCharactersInLocalMapsMenu(c, lm));
    }

    public void openPersonView(Person p)
    {
        c.cc.clear();

        int grossPadding = 7;
        int modPadding = 10;
        int finalPadding = 7;
        c.cc.println(" STATS             ¦ GROSS ¦ MODIFIER ¦ TOTAL ¦");
        c.cc.println("Blood Capacity:    ¦" + generateStatLine(p.GetMyTotalStats()[0],grossPadding,modPadding,finalPadding));
        c.cc.println("Blood Generation:  ¦" + generateStatLine(p.GetMyTotalStats()[1],grossPadding,modPadding,finalPadding));
        c.cc.println("Blood Needed:      ¦" + generateStatLine(p.GetMyTotalStats()[2],grossPadding,modPadding,finalPadding));
        c.cc.println("Energy Capacity:   ¦" + generateStatLine(p.GetMyTotalStats()[3],grossPadding,modPadding,finalPadding));
        c.cc.println("Energy Generation: ¦" + generateStatLine(p.GetMyTotalStats()[4],grossPadding,modPadding,finalPadding));
        c.cc.println("Energy Needed:     ¦" + generateStatLine(p.GetMyTotalStats()[5],grossPadding,modPadding,finalPadding));
        c.cc.println("Size:              ¦" + generateStatLine(p.GetMyTotalStats()[6],grossPadding,modPadding,finalPadding));
        c.cc.println("Speed:             ¦" + generateStatLine(p.GetMyTotalStats()[7],grossPadding,modPadding,finalPadding));
        c.cc.println("Consciousness:     ¦" + generateStatLine(p.GetMyTotalStats()[8],grossPadding,modPadding,finalPadding));
        c.cc.println("Sight:             ¦" + generateStatLine(p.GetMyTotalStats()[9],grossPadding,modPadding,finalPadding));
        c.cc.println("");


        List<String> list = new ArrayList<>();
        list.add("View Body");
        //c.clir.appendList(list, p.getName(), new PersonMenu(c,p));
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

        displayBodyPartStats(bp);

        List<String> bodyPartList = new ArrayList<>();
        assembleBodyPartRelations(bp, bodyPartList);
        //c.clir.appendList(bodyPartList, bp.getMyPerson().getName()+"'s "+bp.getName(), new BodyPartMenu(c,bp), topBarMenuBlockList);

    }
    public String[] displayBodyPartStats(BodyPart bp)
    {
        int grossPadding = 7;
        int modPadding = 10;
        int finalPadding = 7;
        int parentGrossPadding = 22;
        int parentModPadding = 25;
        int personModPadding = 25;
        String[] s = new String[20];

        s[0] = ("Health:         "+bp.getCurrentHealth()+"/"+bp.GetMyTotalStats()[6][2]);
        s[1] = ("Organ Capacity: "+bp.getCurrentOrganCapacity()+"/"+bp.GetMyTotalStats()[11][2]);
        s[2] = " ";

        s[3] = (" STATS             ¦ GROSS ¦ MODIFIER ¦ TOTAL ¦ GROSS BONI TO PARENT ¦ MODIFIER BONI TO PARENT ¦ MODIFIER BONI TO PERSON ¦");
        s[4] = ("Blood Capacity:    ¦" + generateStatLine(bp.GetMyTotalStats()[0],grossPadding,modPadding,finalPadding,parentGrossPadding,parentModPadding,personModPadding));
        s[5] = ("Blood Generation:  ¦" + generateStatLine(bp.GetMyTotalStats()[1],grossPadding,modPadding,finalPadding,parentGrossPadding,parentModPadding,personModPadding));
        s[6] = ("Blood Needed:      ¦" + generateStatLine(bp.GetMyTotalStats()[2],grossPadding,modPadding,finalPadding,parentGrossPadding,parentModPadding,personModPadding));
        s[7] = ("Energy Capacity:   ¦" + generateStatLine(bp.GetMyTotalStats()[3],grossPadding,modPadding,finalPadding,parentGrossPadding,parentModPadding,personModPadding));
        s[8] = ("Energy Generation: ¦" + generateStatLine(bp.GetMyTotalStats()[4],grossPadding,modPadding,finalPadding,parentGrossPadding,parentModPadding,personModPadding));
        s[9] = ("Energy Needed:     ¦" + generateStatLine(bp.GetMyTotalStats()[5],grossPadding,modPadding,finalPadding,parentGrossPadding,parentModPadding,personModPadding));
        s[10] = ("Max Health:        ¦" + generateStatLine(bp.GetMyTotalStats()[6],grossPadding,modPadding,finalPadding,parentGrossPadding,parentModPadding,personModPadding));
        s[11] = ("Regen Rate:        ¦" + generateStatLine(bp.GetMyTotalStats()[7],grossPadding,modPadding,finalPadding,parentGrossPadding,parentModPadding,personModPadding));
        s[12] = ("Regen Limit:       ¦" + generateStatLine(bp.GetMyTotalStats()[8],grossPadding,modPadding,finalPadding,parentGrossPadding,parentModPadding,personModPadding));
        s[13] = ("Armour:            ¦" + generateStatLine(bp.GetMyTotalStats()[9],grossPadding,modPadding,finalPadding,parentGrossPadding,parentModPadding,personModPadding));
        s[14] = ("Size:              ¦" + generateStatLine(bp.GetMyTotalStats()[10],grossPadding,modPadding,finalPadding,parentGrossPadding,parentModPadding,personModPadding));
        s[15] = ("Organ Capacity:    ¦" + generateStatLine(bp.GetMyTotalStats()[11],grossPadding,modPadding,finalPadding,parentGrossPadding,parentModPadding,personModPadding));
        s[16] = ("Speed:             ¦" + generateStatLine(bp.GetMyTotalStats()[12],grossPadding,modPadding,finalPadding,parentGrossPadding,parentModPadding,personModPadding));
        s[17] = ("Consciousness:     ¦" + generateStatLine(bp.GetMyTotalStats()[13],grossPadding,modPadding,finalPadding,parentGrossPadding,parentModPadding,personModPadding));
        s[18] = ("Grabbing Slots:    ¦" + generateStatLine(bp.GetMyTotalStats()[14],grossPadding,modPadding,finalPadding,parentGrossPadding,parentModPadding,personModPadding));
        s[19] = ("Sight:             ¦" + generateStatLine(bp.GetMyTotalStats()[15],grossPadding,modPadding,finalPadding,parentGrossPadding,parentModPadding,personModPadding));

        return s;
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
