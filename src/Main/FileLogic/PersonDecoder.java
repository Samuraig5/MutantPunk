package Main.FileLogic;

import Main.ErrorHandler;
import Main.ObjectLogic.BodyLogic.BodyPart;
import Main.ObjectLogic.BodyLogic.Person;
import Main.ObjectLogic.ObjectTag;
import Main.RenderLogic.Logic.MapIcon;
import Main.WorldLogic.LocalMap;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class PersonDecoder
{
    /**
     * Creates a new person from a standard bodyplan file
     *
     * @param filePath file path to the bodyplan. (resources>BodyPlans)
     * @param bias nudge the stats of an bodypart according to the bias. Positive bias improves the bodypart
     * @param randomness magnitude of the randomness applied to the stats of the bodypart.
     * @return
     */
    static public Person SpawnNewPersonFromFile(String filePath, int bias, int randomness, LocalMap lm)
    {
        try
        {
            Scanner fileIn = new Scanner(new File(filePath));

            int fileVersion = 0;
            String line = fileIn.nextLine();
            while (!Objects.equals(line, "§END_OF_HEADER§"))
            {
                String[] vars = line.split("§");
                if (vars[0].equalsIgnoreCase("version"))
                {
                    fileVersion = Integer.parseInt(vars[1]);
                }
                line = fileIn.nextLine();
            }
            return findDecoder(fileIn, fileVersion, bias, randomness, lm);
        }
        catch (FileNotFoundException e) {
            System.err.println("Unable to load person from file");
            throw new RuntimeException(e);
        }
    }

    private static Person findDecoder(Scanner fileIn, int fileVersion, int bias, int randomness, LocalMap lm)
    {
        try
        {
            if (fileVersion == 0) {throw new RuntimeException();}
            else if (fileVersion == 1)
            {
                return decoderVersion1(fileIn, bias, randomness, lm);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private static Person decoderVersion1(Scanner fileIn, int bias, int randomness, LocalMap lm)
    {
        Person p = new Person(true);
        p.setLocalMap(lm);
        p.setGameWorld(lm.getMyWorld());
        p.setMapIcon(new MapIcon());

        String line = fileIn.nextLine();

        while (!Objects.equals(line, "§BODY§"))
        {
            String[] vars = line.split("§");
            switch (vars[0].toLowerCase())
            {
                case "name":
                    setName(vars, p);
                    break;
                case "icon":
                    setIcon(vars, p);
                    break;
                case "sprite":
                    setSprite(vars, p);
                    break;
                case "colour":
                    setColour(vars, p);
                    break;
                case "description":
                    setDescription(vars, p);
                    break;
                case "tags":
                    setTags(vars, p);
                    break;
            }
            line = fileIn.nextLine();
        }
        List<BodyPart> currentTargetsToAttach = new ArrayList<>();
        int depth;
        String bodyPartName;
        String bodyPartPath;
        try {
            bodyPartName = fileIn.nextLine().split("§")[1];
            bodyPartPath = fileIn.nextLine().split("§")[1];
            p.myBodyParts.add(BodyFileDecoder.loadBodyPartFromFile(bodyPartPath, bias, randomness, p));
            p.myBodyParts.get(0).changeName(bodyPartName);
            p.myBodyParts.get(0).setMyPerson(p);
            currentTargetsToAttach.add(p.myBodyParts.get(0));

            while(fileIn.hasNextLine())
            {
                depth = fileIn.nextLine().length();
                bodyPartName = fileIn.nextLine().split("§")[1];
                bodyPartPath = fileIn.nextLine().split("§")[1];
                BodyPart newBodyPart = BodyFileDecoder.loadBodyPartFromFile(bodyPartPath, bias, randomness, p);
                newBodyPart.changeName(bodyPartName);
                if(depth > currentTargetsToAttach.size()-1)
                {
                    currentTargetsToAttach.add(newBodyPart);
                }
                else if(depth == currentTargetsToAttach.size()-1)
                {
                    currentTargetsToAttach.set(depth, newBodyPart);
                }
                else if(depth < currentTargetsToAttach.size()-1)
                {
                    currentTargetsToAttach.remove(depth+1);
                    currentTargetsToAttach.set(depth, newBodyPart);
                }
                currentTargetsToAttach.get(depth-1).tryToAttach(newBodyPart);
            }
        }
        catch (Exception e)
        {
            ErrorHandler.PrintAndTraceError(e);
        }
        return p;
    }
    private static void setName (String[] nameLine, Person p)
    {
        p.setName(nameLine[1]);
    }
    private static void setIcon (String[] iconLine, Person p)
    {
        p.getMapIcon().setSymbol(iconLine[1].toCharArray()[0]);
    }
    private static void setSprite(String[] vars, Person p)
    {
        if (vars.length > 1)
        {
            p.getMapIcon().setSprite(vars[1], true); //Default is to assume that an image is a full cover

            if (vars.length > 2)
            {
                if (Objects.equals(vars[2].toLowerCase(), "false"))
                {
                    p.getMapIcon().setSprite(vars[1], false); //Only change it if its marked as false
                }
            }
        }
    }
    private static void setColour(String[] vars, Person p)
    {
        if (vars.length > 1)
        {
            String[] RGBValues =  vars[1].split(":");
            Color newColor = new Color(Integer.parseInt(RGBValues[0]), Integer.parseInt(RGBValues[1]), Integer.parseInt(RGBValues[2]));
            p.getMapIcon().setIconColour(newColor);
        }
    }
    private static void setDescription(String[] vars, Person p)
    {
        p.setDescription(vars[1]);
    }
    private static void setTags(String[] vars, Person p)
    {
        ObjectTag[] tags = ObjectTag.translateStringToTag(vars[1].split(":"));
        p.setTags(tags);
    }
}
