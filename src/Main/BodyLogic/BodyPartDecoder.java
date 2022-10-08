package Main.BodyLogic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BodyPartDecoder
{
    /**
     * This function reads the bodyPart file at given address, trims all the non-value data, like descriptions and the
     * separator '§', and returns a ArrayList of Strings of Values.
     *
     * @param filePath path to the desired file
     * @return ArrayList of Strings containing the values of the bodyPart in the same order as the file had them listed.
     */
    static public List<String> getData(String filePath)
    {
        List<String> data = new ArrayList<>();
        try
        {
            Scanner fileIn = new Scanner(new File(filePath));
            while(fileIn.hasNextLine())
            {
                data.add(fileIn.nextLine().split("§")[1]);
            }
        }
        catch (Exception e)
        {
            System.out.println("!!!!! BodyPartDecoder could not find File with given FilePath !!!!!");
        }
        return data;
    }
    static public BodyPart loadBodyPartFromFile(String filePath,int bias, int randomness)
    {
        BodyPart bp = new BodyPart();
        List<String> data = getData(filePath);
        bp.generateBodyPart(data,bias,randomness);
        return bp;
    }
}
