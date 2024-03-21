package Main.WorldLogic;

import Main.ObjectLogic.BodyLogic.Person;
import Main.Settings;

import java.util.ArrayList;
import java.util.List;

public class GameWorld
{
    private String worldName;
    private LocalMap[][] localMaps = new LocalMap[Settings.worldMapSizeX][Settings.worldMapSizeY];
    private LocalMap activeLocalMap;
    private List<Person> allCharacters = new ArrayList<>();

    private Person playerCharacter;

    protected GameWorld(String name)
    {
        worldName = name;
    }

    public void setWorldName(String worldName) {
        this.worldName = worldName;
    }

    public void addCharacter(Person person)
    {
        allCharacters.add(person);
        person.setGameWorld(this);
    }

    public void removeCharacter(Person person)
    {
        allCharacters.remove(person);
        person.setGameWorld(null);
    }

    public String getWorldName() {
        return worldName;
    }

    public LocalMap[][] getLocalMaps() {
        return localMaps;
    }

    public LocalMap getActiveLocalMap() {return activeLocalMap;}
    public void setActiveLocalMap(LocalMap lm)
    {
        activeLocalMap = lm;
    }

    public List<Person> getAllCharacters() {
        return allCharacters;
    }
}
