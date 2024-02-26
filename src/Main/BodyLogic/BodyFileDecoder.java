package Main.BodyLogic;

import Main.ErrorHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
                if(s.equals("§END§"))
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
    static public BodyPart loadBodyPartFromFile(String filePath,int bias, int randomness)
    {
        BodyPart bp = new BodyPart();
        List<String[]> data = getBodyPartData(filePath);
        bp.generateBodyPart(data,bias,randomness);
        return bp;
    }

    /**
     * Creates a new person from a standard bodyplan file
     *
     * @param filePath file path to the bodyplan. (resources>BodyPlans)
     * @param bias nudge the stats of an bodypart according to the bias. Positive bias improves the bodypart
     * @param randomness magnitude of the randomness applied to the stats of the bodypart.
     * @return
     */
    static public Person SpawnNewPersonFromFile(String filePath, int bias, int randomness)
    {
        Person p = new Person();
        List<BodyPart> currentTargetsToAttach = new ArrayList<>();
        int depth;
        String bodyPartName;
        String bodyPartPath;
        try
        {
            Scanner fileIn = new Scanner(new File(filePath));

            String[] personInfo = fileIn.nextLine().split("§");
            p.changeName(personInfo[0]);
            char[] c = personInfo[1].toCharArray();
            p.setMapIcon(c[0]);

            bodyPartName = fileIn.nextLine().split("§")[1];
            bodyPartPath = fileIn.nextLine().split("§")[1];
            p.myBodyParts.add(loadBodyPartFromFile(bodyPartPath, bias, randomness));
            p.myBodyParts.get(0).changeName(bodyPartName);
            p.myBodyParts.get(0).setMyPerson(p);
            p.myBodyParts.get(0).updateParentAndPerson();
            currentTargetsToAttach.add(p.myBodyParts.get(0));

            while(fileIn.hasNextLine())
            {
                depth = fileIn.nextLine().length();
                bodyPartName = fileIn.nextLine().split("§")[1];
                bodyPartPath = fileIn.nextLine().split("§")[1];
                BodyPart bp = loadBodyPartFromFile(bodyPartPath, bias, randomness);
                bp.changeName(bodyPartName);
                if(depth > currentTargetsToAttach.size()-1)
                {
                    currentTargetsToAttach.add(bp);
                }
                else if(depth == currentTargetsToAttach.size()-1)
                {
                    currentTargetsToAttach.set(depth, bp);
                }
                else if(depth < currentTargetsToAttach.size()-1)
                {
                    currentTargetsToAttach.remove(depth+1);
                    currentTargetsToAttach.set(depth, bp);
                }
                bp.TryToAttachTo(currentTargetsToAttach.get(depth-1));
            }
        }
        catch (Exception e)
        {
            ErrorHandler.PrintAndTraceError(e);
        }
        return p;
    }
}
