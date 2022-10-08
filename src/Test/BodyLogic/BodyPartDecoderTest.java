package Test.BodyLogic;

import Main.BodyLogic.BodyFileDecoder;

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
        List<String> data = BodyFileDecoder.getBodyPartData("Resources/BodyParts/Torso/HumanTorso");
        for (String d : data)
        {
            System.out.println(d);
        }

        System.out.println("================ GARBAGE FILE PATH TEST ================");
        List<String> data2 = BodyFileDecoder.getBodyPartData("Resources/BodyParts/Torso/GarbageFilePath");
        for (String s : data2) {
            System.out.println(s);
        }
    }
}
