package Main.RenderLogic;

import Main.BodyLogic.BodyFileDecoder;
import Main.BodyLogic.BodyPart;
import Main.BodyLogic.Person;
import Main.RenderLogic.Menus.AllCharactersMenu;
import Main.RenderLogic.Menus.BodyMenu;
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
        c.cc.println("Consciousness:     ¦" + generateStatLine(p.getConsciousness(),grossPadding,modPadding,finalPadding));
        c.cc.println("Blood Capacity:    ¦" + generateStatLine(p.getBloodCapacity(),grossPadding,modPadding,finalPadding));
        c.cc.println("Blood Generation:  ¦" + generateStatLine(p.getBloodGeneration(),grossPadding,modPadding,finalPadding));
        c.cc.println("Blood Needed:      ¦" + generateStatLine(p.getBloodNeeded(),grossPadding,modPadding,finalPadding));
        c.cc.println("Energy Capacity:   ¦" + generateStatLine(p.getEnergyCapacity(),grossPadding,modPadding,finalPadding));
        c.cc.println("Energy Generation: ¦" + generateStatLine(p.getEnergyGeneration(),grossPadding,modPadding,finalPadding));
        c.cc.println("Energy Needed:     ¦" + generateStatLine(p.getEnergyNeeded(),grossPadding,modPadding,finalPadding));
        c.cc.println("Size:              ¦" + generateStatLine(p.getSize(),grossPadding,modPadding,finalPadding));
        c.cc.println("Speed:             ¦" + generateStatLine(p.getSpeed(),grossPadding,modPadding,finalPadding));
        c.cc.println("Sight:             ¦" + generateStatLine(p.getSight(),grossPadding,modPadding,finalPadding));
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
        list.add(depth + "↳" + bp.getName());
        for (BodyPart nextbp:bp.getAttachedBodyParts())
        {
            addChildrenBodyPartsToList(nextbp, list, depth+"¦ ");
        }
    }
}
