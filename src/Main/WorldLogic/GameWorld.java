package Main.WorldLogic;

import Main.BodyLogic.Person;

import java.util.ArrayList;
import java.util.List;

public class GameWorld
{
    private String worldName;
    private List<LocalMap> localMaps = new ArrayList<>();
    private List<Person> allCharacters = new ArrayList<>();

    public GameWorld(String name)
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
    }

    public void removeLocalMap(LocalMap localMap)
    {
        localMaps.remove(localMap);
    }

    public void removeCharacter(Person person)
    {
        allCharacters.remove(person);
    }

    public String getWorldName() {
        return worldName;
    }

    public List<LocalMap> getLocalMaps() {
        return localMaps;
    }

    public List<Person> getAllCharacters() {
        return allCharacters;
    }
}
