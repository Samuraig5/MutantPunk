package Main.WorldLogic;

import Main.BodyLogic.Person;

import java.util.List;

public class Cell
{
    private final int[] coordinates = new int[2];
    private final List<Person> people;

    public Cell(int[] xy, List<Person> personList)
    {
        coordinates[0] = xy[0];
        coordinates[1] = xy[1];
        people = personList;
    }

    public int[] getCoordinates()
    {
        return coordinates;
    }
    public List<Person> getPeople()
    {
        return people;
    }
    public void personEnters(Person p)
    {
        if(!people.contains(p))
        {
            people.add(p);
        }
        if(p.getMyCell() != this)
        {
            p.setMyCell(this);
        }
    }
    public void personLeaves(Person p)
    {
        people.remove(p);
    }
    public boolean isEmpty()
    {
        if (people.size() == 0)
        {
            return true;
        }
        return false;
    }
}
