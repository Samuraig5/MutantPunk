package Main.RenderLogic;

import java.util.List;

public class ConsoleListRenderer
{
    Console c;
    public ConsoleListRenderer(Console console)
    {
        c = console;
    }

    public void renderList(List<String> list, String title)
    {
        c.cc.enteringAMenu();
        c.println(title);
        c.println("");
        for(int i = 0; i < list.size(); i++)
        {
            c.println(indexToLetter(i+1) + " - " + list.get(i));
        }
    }

    public String indexToLetter(int n)
    {
        String letter;
        switch (n)
        {
            case 1:  letter = "a";
                break;
            case 2:  letter = "b";
                break;
            case 3:  letter = "c";
                break;
            case 4:  letter = "d";
                break;
            case 5:  letter = "e";
                break;
            case 6:  letter = "f";
                break;
            case 7:  letter = "g";
                break;
            case 8:  letter = "h";
                break;
            case 9:  letter = "i";
                break;
            case 10: letter = "j";
                break;
            case 11: letter = "k";
                break;
            case 12: letter = "l";
                break;
            case 13: letter = "m";
                break;
            case 14: letter = "n";
                break;
            case 15: letter = "o";
                break;
            case 16: letter = "p";
                break;
            case 17: letter = "q";
                break;
            case 18: letter = "r";
                break;
            case 19: letter = "s";
                break;
            case 20: letter = "t";
                break;
            case 21: letter = "u";
                break;
            case 22: letter = "v";
                break;
            case 23: letter = "w";
                break;
            case 24: letter = "x";
                break;
            case 25: letter = "y";
                break;
            case 26: letter = "z";
                break;
            default: letter = "Invalid index";
                break;
        }
        return letter;
    }
}
