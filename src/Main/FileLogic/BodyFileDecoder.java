package Main.FileLogic;

import Main.ErrorHandler;
import Main.ObjectLogic.BodyLogic.BodyPart;
import Main.ObjectLogic.BodyLogic.Person;
import Main.ObjectLogic.ObjectTag;
import Main.RenderLogic.Logic.MapIcon;
import Main.WorldLogic.LocalMap;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class BodyFileDecoder
{
    /**
     * This function reads the bodyPart file at given address, trims all the non-value data, like descriptions and the
     * separator '§', and returns a ArrayList of Strings of Values.
     *
     * @param filePath path to the desired file
     * @return ArrayList of Strings containing the values of the bodyPart in the same order as the file had them listed.
     */
    static public List<String[]> getBodyPartData(String filePath)
    {
        List<String[]> data = new ArrayList<>();
        try
        {
            Scanner fileIn = new Scanner(new File(filePath));
            while(fileIn.hasNextLine())
            {
                String s = fileIn.nextLine();
                ErrorHandler.LogData(false,s);
                if(s.equals("§END§") || s.equals("§ABILITIES§"))
                {
                    break;
                }
                String[] a = s.split("§")[1].split("#");
                ErrorHandler.LogData(false, filePath + " Data Length: " + a.length);
                ErrorHandler.PrintArray(false, a, "data");
                data.add(a);
            }
        }
        catch (Exception e)
        {
            ErrorHandler.PrintAndTraceError(e);
        }
        return data;
    }

    static public List<String[]> getBodyPartAbility(String filePath)
    {
        List<String[]> abilities = new ArrayList<>();
        try
        {
            Scanner fileIn = new Scanner(new File(filePath));
            String s = fileIn.nextLine();
            while(fileIn.hasNextLine() && !s.equals("§ABILITIES§")) {
                s = fileIn.nextLine();
            }
            while(fileIn.hasNextLine())
            {
                s = fileIn.nextLine();
                if(s.equals("§END§"))
                {
                    break;
                }
                String[] a = s.split("§");
                abilities.add(a);
            }
        }
        catch (Exception e)
        {
            ErrorHandler.PrintAndTraceError(e);
        }
        return abilities;
    }


    /**
     * Generates a bodyPart based on the file addressed by filePath
     *
     * @param filePath path to the desired file
     * @param bias improves (if bias is positive) or worsens (if bias is negative) the stats and attribute of the body part.
     *             The bias is expressed as a present. So a bias of '10' would increase the bodyParts stats by 10%.
     * @param randomness changes the stats of the bodyPart by a random amount (both positive and negative).
     *                   The greater the value, the stronger the random drift.
     * @return the finished BodyPart with generated stats.
     */
    static public BodyPart loadBodyPartFromFile(String filePath, int bias, int randomness, Person p)
    {
        BodyPart bp = new BodyPart();
        List<String[]> data = getBodyPartData(filePath);
        List<String[]> abilities = getBodyPartAbility(filePath);
        bp.generateBodyPart(data, abilities, bias,randomness, p);
        return bp;
    }
}
