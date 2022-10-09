package Main.RenderLogic;

import Main.BodyLogic.Person;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class ConsoleBodyMenu
{
    Console c;
    List<Person> allCharacters = new ArrayList<>();

    public ConsoleBodyMenu(Console console)
    {
        c = console;
    }

    public void drawPersonMenu(Person p)
    {
        c.println("> " + p.name);
        c.println("Number of body parts: " + p.myBodyParts.size());
    }

    public void listAllPersons()
    {
        List<String> allCharacterNames = new ArrayList<>();
        for (Person p:allCharacters)
        {
            allCharacterNames.add(p.name);
        }
        c.clir.renderList(allCharacterNames, "Current Characters");
    }
}
