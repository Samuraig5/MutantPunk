package Main;

import Main.BodyLogic.BodyLogicHelper;

public class Main {

    public static void main(String[] args)
    {
        for(int i = 0; i<100; i++)
        {
            System.out.println(BodyLogicHelper.randomRange());
        }
    }
}