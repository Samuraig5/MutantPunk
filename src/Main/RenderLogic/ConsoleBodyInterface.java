package Main.RenderLogic;

import Main.BodyLogic.BodyFileDecoder;
import Main.BodyLogic.BodyPart;
import Main.BodyLogic.Person;
import Main.ErrorHandler;
import Main.RenderLogic.Menus.AllCharactersMenu;
import Main.RenderLogic.Menus.BodyMenu;
import Main.RenderLogic.Menus.BodyPartMenu;
import Main.RenderLogic.Menus.PersonMenu;

import java.util.ArrayList;
import java.util.List;

public class ConsoleBodyInterface
{
    Console c;
    private final List<Person> allCharacters = new ArrayList<>();

    public ConsoleBodyInterface(Console console)
    {
        c = console;
    }

    public void spawnPerson(String name, String bias, String randomness, String filePath)
    {
        try
        {
            int b = Integer.parseInt(bias);
            int r = Integer.parseInt(randomness);
            Person newCharacter = BodyFileDecoder.getBodyPlanData(filePath, b, r);
            newCharacter.changeName(name);
            c.cb.allCharacters.add(newCharacter);
        }
        catch (Exception e)
        {
            c.cc.println("WARNING: The second and third parameters of spawnPerson must be integer numbers", c.errorColour);
        }
    }

    public void listAllPersons()
    {
        List<String> allCharacterNames = new ArrayList<>();
        for (Person p:allCharacters)
        {
            allCharacterNames.add(p.name);
        }
        c.clir.renderList(allCharacterNames, "Current Characters", new AllCharactersMenu(c));
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
        c.clir.appendList(list, p.name, new PersonMenu(c,p));
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

    public List<Person> getAllCharactersList()
    {
        return allCharacters;
    }

    public void openBodyView(Person p)
    {
        c.cc.clear();

        List<String> list = new ArrayList<>();
        addChildrenBodyPartsToList(p.myBodyParts.get(0), list, "");
        c.clir.renderList(list, p.name+"'s Body", new BodyMenu(c,p));
    }
    private void addChildrenBodyPartsToList(BodyPart bp, List<String> list, String depth)
    {
        ErrorHandler.LogData(false,"Number of attached BodyParts: " + bp.getAttachedBodyParts().size());
        list.add(depth + "↳" + bp.getName());
        for (BodyPart nextbp:bp.getAttachedBodyParts())
        {
            addChildrenBodyPartsToList(nextbp, list, depth+"¦ ");
        }
    }

    public void openBodyPartView(BodyPart bp)
    {
        c.cc.clear();

        displayBodyPartStats(bp);

        List<String> list = new ArrayList<>();
        assembleBodyPartRelations(bp, list);
        c.clir.appendList(list, bp.getMyPerson().name+"'s "+bp.getName(), new BodyPartMenu(c,bp));

    }
    private void displayBodyPartStats(BodyPart bp)
    {
        int grossPadding = 7;
        int modPadding = 10;
        int finalPadding = 7;
        int parentGrossPadding = 22;
        int parentModPadding = 25;
        int personModPadding = 25;
        c.cc.println("Health:         "+bp.getCurrentHealth()+"/"+bp.GetMyTotalStats()[6][2]);
        c.cc.println("Organ Capacity: "+bp.getCurrentOrganCapacity()+"/"+bp.GetMyTotalStats()[11][2]);
        c.cc.println("");

        c.cc.println(" STATS             ¦ GROSS ¦ MODIFIER ¦ TOTAL ¦ GROSS BONI TO PARENT ¦ MODIFIER BONI TO PARENT ¦ MODIFIER BONI TO PERSON ¦");
        c.cc.println("Blood Capacity:    ¦" + generateStatLine(bp.GetMyTotalStats()[0],grossPadding,modPadding,finalPadding,parentGrossPadding,parentModPadding,personModPadding));
        c.cc.println("Blood Generation:  ¦" + generateStatLine(bp.GetMyTotalStats()[1],grossPadding,modPadding,finalPadding,parentGrossPadding,parentModPadding,personModPadding));
        c.cc.println("Blood Needed:      ¦" + generateStatLine(bp.GetMyTotalStats()[2],grossPadding,modPadding,finalPadding,parentGrossPadding,parentModPadding,personModPadding));
        c.cc.println("Energy Capacity:   ¦" + generateStatLine(bp.GetMyTotalStats()[3],grossPadding,modPadding,finalPadding,parentGrossPadding,parentModPadding,personModPadding));
        c.cc.println("Energy Generation: ¦" + generateStatLine(bp.GetMyTotalStats()[4],grossPadding,modPadding,finalPadding,parentGrossPadding,parentModPadding,personModPadding));
        c.cc.println("Energy Needed:     ¦" + generateStatLine(bp.GetMyTotalStats()[5],grossPadding,modPadding,finalPadding,parentGrossPadding,parentModPadding,personModPadding));
        c.cc.println("Max Health:        ¦" + generateStatLine(bp.GetMyTotalStats()[6],grossPadding,modPadding,finalPadding,parentGrossPadding,parentModPadding,personModPadding));
        c.cc.println("Regen Rate:        ¦" + generateStatLine(bp.GetMyTotalStats()[7],grossPadding,modPadding,finalPadding,parentGrossPadding,parentModPadding,personModPadding));
        c.cc.println("Regen Limit:       ¦" + generateStatLine(bp.GetMyTotalStats()[8],grossPadding,modPadding,finalPadding,parentGrossPadding,parentModPadding,personModPadding));
        c.cc.println("Armour:            ¦" + generateStatLine(bp.GetMyTotalStats()[9],grossPadding,modPadding,finalPadding,parentGrossPadding,parentModPadding,personModPadding));
        c.cc.println("Size:              ¦" + generateStatLine(bp.GetMyTotalStats()[10],grossPadding,modPadding,finalPadding,parentGrossPadding,parentModPadding,personModPadding));
        c.cc.println("Organ Capacity:    ¦" + generateStatLine(bp.GetMyTotalStats()[11],grossPadding,modPadding,finalPadding,parentGrossPadding,parentModPadding,personModPadding));
        c.cc.println("Speed:             ¦" + generateStatLine(bp.GetMyTotalStats()[12],grossPadding,modPadding,finalPadding,parentGrossPadding,parentModPadding,personModPadding));
        c.cc.println("Consciousness:     ¦" + generateStatLine(bp.GetMyTotalStats()[13],grossPadding,modPadding,finalPadding,parentGrossPadding,parentModPadding,personModPadding));
        c.cc.println("Grabbing Slots:    ¦" + generateStatLine(bp.GetMyTotalStats()[14],grossPadding,modPadding,finalPadding,parentGrossPadding,parentModPadding,personModPadding));
        c.cc.println("Sight:             ¦" + generateStatLine(bp.GetMyTotalStats()[15],grossPadding,modPadding,finalPadding,parentGrossPadding,parentModPadding,personModPadding));

        c.cc.println("");
    }
    private void assembleBodyPartRelations(BodyPart bp, List<String> list)
    {
        if (bp.getMyPerson() != null)
        {
            list.add("My Person: " + bp.getMyPerson().name);
        }
        else
        {
            list.add("My Person: N/A");
        }
        if (bp.getParentBodyPart() != null)
        {
            list.add("My parent body part: " + bp.getParentBodyPart().getName());
        }
        else
        {
            list.add("My parent body part: N/A");
        }
        for (BodyPart nextbp:bp.getAttachedBodyParts())
        {
            list.add("Attached body part: " + nextbp.getName());
        }
    }
}
