package Main.AbilityLogic;

import Main.ObjectLogic.BodyLogic.AbilityTag;
import Main.ObjectLogic.BodyLogic.BodyPart;
import Main.ObjectLogic.ObjectTag;
import Main.TimeLogic.Updatable;
import Main.WorldLogic.LocalMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AbilityCreator
{
    private static List<Ability> addToUpdateList(List<Ability> abilities, LocalMap lm)
    {
        lm.addLocalUpdatables(abilities);
        return abilities;
    }
    public static List<Ability> createAbilities(List<String[]> lines, LocalMap localMap)
    {

        return createAbilities(lines, null, localMap);
    }
    public static List<Ability> createAbilities(List<String[]> lines, BodyPart bodyPart, LocalMap localMap)
    {
        if (bodyPart == null) {System.err.println("Tried to create ability but bodyPart is null");}
        List<Ability> abilities = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            int capacity = 0;
            int efficiency = 0;
            String abilityName = lines.get(i)[0];
            AbilityTag abilityTag = AbilityTag.translateStringToTag(lines.get(i)[1]);

            String[] objTagStrings = new String[lines.get(i).length-2];
            for (int j = 0; j < lines.get(i).length-2; j++)
            {
                String[] s = lines.get(i)[j+2].split("#");
                if (s.length == 1)
                {
                    objTagStrings[j] = s[0];
                }
                else
                {
                    if (Objects.equals(s[0], "CAPACITY"))
                    {
                        capacity = Integer.parseInt(s[1]);
                    }
                    else if (Objects.equals(s[0], "EFFICIENCY"))
                    {
                        efficiency = Integer.parseInt(s[1]);
                    }
                }
            }

            ObjectTag[] objectTags = ObjectTag.translateStringToTag(objTagStrings);

            if (abilityTag == AbilityTag.DIGESTION)
            {
                Ability ability = new Digest(bodyPart, abilityName, abilityTag, objectTags);
                if (capacity != 0) {ability.setCapacity(capacity);}
                if (efficiency != 0) {ability.setEfficiency(efficiency);}
                abilities.add(ability);
            }
        }
        return addToUpdateList(abilities, localMap);
    }
}
