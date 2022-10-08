package Test;

import Main.BodyLogic.BodyPartDecoder;

import java.io.FileNotFoundException;
import java.util.List;

public class BodyPartDecoderTest
{
    /**
     * This test tests the BodyPartDecoder and returns the data it was able to get.
     * @param args not used
     * @throws FileNotFoundException exception that should be thrown in the second test.
     */
    public static void main(String[] args) throws FileNotFoundException
    {
        System.out.println("================ PROPER FILE PATH TEST ================");
        List<String> data = BodyPartDecoder.getData("Resources/BodyParts/Torso/HumanTorso");
        for (String d : data)
        {
            System.out.println(d);
        }

        System.out.println("================ GARBAGE FILE PATH TEST ================");
        List<String> data2 = BodyPartDecoder.getData("Resources/BodyParts/Torso/GarbageFilePath");
        for (String s : data2) {
            System.out.println(s);
        }
    }
}
