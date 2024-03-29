package Main.RenderLogic;

import Main.ErrorHandler;
import Main.RenderLogic.Menus.MenuLogic;

import java.util.ArrayList;
import java.util.List;

import static Main.RenderLogic.Console.styledDocument;

public class ConsoleListRenderer
{
    Console c;
    private boolean currentlyRendering = false;
    private List<String> currentlyRenderedList = new ArrayList<>();
    private String currentlyRenderedTitle;
    private int amountOfPages = 0;
    private int currentPage = 0;

    public ConsoleListRenderer(Console console)
    {
        c = console;
    }

    public String renderList(List<String> list, String title)
    {
        generateList(list,title);
        return generateString();
    }
    public void appendList(List<String> list, String title, List<String> topMenuBar)
    {
        generateList(list,title);
        generateString();
    }
    public void appendList(List<String> list, String title)
    {
        appendList(list,title,new ArrayList<>());
    }

    private void generateList(List<String> list, String title)
    {
        reset();
        currentlyRendering = true;
        currentlyRenderedList = list;
        currentlyRenderedTitle = title;
        currentPage = 1;
        amountOfPages = 1;

        int amountOfItems = list.size();

        while (amountOfItems > 26)
        {
            amountOfPages++;
            amountOfItems = amountOfItems - 26;
        }
    }

    public String generateString()
    {
        int finalPageSize = currentlyRenderedList.size()%26;
        if (finalPageSize == 0 && currentlyRenderedList.size() != 0)
        {
            finalPageSize = 26;
        }

        try
        {
            String s = styledDocument.getText(0, styledDocument.getLength());
            StringBuilder newString = new StringBuilder();

            if (amountOfPages > 1)
            {

                newString.append(currentlyRenderedTitle + "     (" + currentPage + "/" + amountOfPages + ")" + "\n");
            }
            else
            {
                newString.append(currentlyRenderedTitle + "\n");
            }
            newString.append("\n");

            newString.append(s);

            if (currentPage == amountOfPages)
            {
                for(int i = 26*(currentPage-1); i < 26*(currentPage-1)+finalPageSize; i++)
                {
                    newString.append(indexToLetter((i%26)) + " - " + currentlyRenderedList.get(i)+"\n");
                }
            }
            else
            {
                for(int i = 26*(currentPage-1); i < 26*(currentPage); i++)
                {
                    newString.append(indexToLetter((i%26)) + " - " + currentlyRenderedList.get(i)+"\n");
                }
            }
            return  newString.toString();
        }
        catch (Exception e)
        {
            ErrorHandler.LogData(true,"Was unable to copy the content on screen");
        }
        return "ConsoleListRenderer was unable to generate String";
    }

    public void reset()
    {
        amountOfPages = 0;
        currentPage = 0;
        currentlyRendering = false;
    }

    public String pageUp()
    {
        if (currentPage<amountOfPages)
        {
            currentPage++;
        }
        else
        {
            currentPage = 1;
        }
        return generateString();
    }

    public String pageDown()
    {
        if (currentPage>1)
        {
            currentPage--;
        }
        else
        {
            currentPage = amountOfPages;
        }
        return generateString();
    }

    public int getCurrentPage()
    {
        return currentPage;
    }

    public boolean isCurrentlyRendering()
    {
        return currentlyRendering;
    }

    private String indexToLetter(int n)
    {
        String letter;
        switch (n)
        {
            case 0:  letter = "a";
                break;
            case 1:  letter = "b";
                break;
            case 2:  letter = "c";
                break;
            case 3:  letter = "d";
                break;
            case 4:  letter = "e";
                break;
            case 5:  letter = "f";
                break;
            case 6:  letter = "g";
                break;
            case 7:  letter = "h";
                break;
            case 8:  letter = "i";
                break;
            case 9: letter = "j";
                break;
            case 10: letter = "k";
                break;
            case 11: letter = "l";
                break;
            case 12: letter = "m";
                break;
            case 13: letter = "n";
                break;
            case 14: letter = "o";
                break;
            case 15: letter = "p";
                break;
            case 16: letter = "q";
                break;
            case 17: letter = "r";
                break;
            case 18: letter = "s";
                break;
            case 19: letter = "t";
                break;
            case 20: letter = "u";
                break;
            case 21: letter = "v";
                break;
            case 22: letter = "w";
                break;
            case 23: letter = "x";
                break;
            case 24: letter = "y";
                break;
            case 25: letter = "z";
                break;
            default: letter = "Invalid index";
                break;
        }
        return letter;
    }
}
