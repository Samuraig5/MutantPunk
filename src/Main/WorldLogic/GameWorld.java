package Main.WorldLogic;

import Main.BodyLogic.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class GameWorld
{
    private String worldName;
    private List<LocalMap> localMaps = new ArrayList<>();

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

    public void addLocalMap(LocalMap localMap)
    {
        localMaps.add(localMap);
    }

    public void addCharacter(Person person)
    {
        allCharacters.add(person);
        person.setGameWorld(this);
    }

    public void removeLocalMap(LocalMap localMap)
    {
        localMaps.remove(localMap);
    }

    public void removeCharacter(Person person)
    {
        allCharacters.remove(person);
        person.setGameWorld(null);
    }

    public String getWorldName() {
        return worldName;
    }

    public List<LocalMap> getLocalMaps() {
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
