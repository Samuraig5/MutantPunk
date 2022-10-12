package Main.RenderLogic;

import Main.BodyLogic.BodyFileDecoder;
import Main.BodyLogic.Person;
import Main.RenderLogic.Menus.AllCharactersMenu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class ConsoleBodyMenu
{
    Console c;
    private final List<Person> allCharacters = new ArrayList<>();

    public ConsoleBodyMenu(Console console)
    {
        c = console;
    }

    public void spawnHuman(String name, String bias, String randomness)
    {
        try
        {
            int b = Integer.parseInt(bias);
            int r = Integer.parseInt(randomness);
            Person guy = BodyFileDecoder.getBodyPlanData("Resources/BodyPlans/Human", b, r);
            guy.changeName(name);
            c.cb.allCharacters.add(guy);
        }
        catch (Exception e)
        {
            c.cc.println("WARNING: The second and third parameters of /spawnHuman must be integer numbers", c.errorColour);
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
}
