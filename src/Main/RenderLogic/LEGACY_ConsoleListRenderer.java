package Main.RenderLogic;

import Main.ErrorHandler;
import Main.RenderLogic.Menus.MenuLogic;

import java.util.ArrayList;
import java.util.List;

import static Main.RenderLogic.Console.styledDocument;

public class LEGACY_ConsoleListRenderer
{
    Console c;
    private boolean currentlyRendering = false;
    private List<String> currentlyRenderedList = new ArrayList<>();
    private String currentlyRenderedTitle;
    private List<String> currentTopBarMenu = new ArrayList<>();
    private int amountOfPages = 0;
    private int currentPage = 0;

    public LEGACY_ConsoleListRenderer(Console console)
    {
        c = console;
    }

    public void renderList(List<String> list, String title, MenuLogic newMenuLogic, List<String> topMenuBar)
    {
        generateList(list,title,newMenuLogic,topMenuBar);
        renderPage();
    }
    public void renderList(List<String> list, String title, MenuLogic newMenuLogic)
    {
        renderList(list,title,newMenuLogic,new ArrayList<>());
    }
    public void appendList(List<String> list, String title, MenuLogic newMenuLogic, List<String> topMenuBar)
    {
        generateList(list,title,newMenuLogic, topMenuBar);
        appendPage();
    }
    public void appendList(List<String> list, String title, MenuLogic newMenuLogic)
    {
        appendList(list,title,newMenuLogic,new ArrayList<>());
    }

    private void generateList(List<String> list, String title, MenuLogic newMenuLogic, List<String> topMenuBar)
    {
        c.ckb.setCurrentMenu(newMenuLogic);
        reset();
        currentlyRendering = true;
        currentlyRenderedList = list;
        currentlyRenderedTitle = title;
        currentTopBarMenu = topMenuBar;
        currentPage = 1;
        amountOfPages = 1;

        int amountOfItems = list.size();

        while (amountOfItems > 26)
        {
            amountOfPages++;
            amountOfItems = amountOfItems - 26;
        }
    }

    public void appendPage()
    {
        int finalPageSize = currentlyRenderedList.size()%26;
        if (finalPageSize == 0 && currentlyRenderedList.size() != 0)
        {
            finalPageSize = 26;
        }

        try
        {
            String s = styledDocument.getText(0, styledDocument.getLength());
            c.cc.clear();

            if (currentTopBarMenu.size()>0)
            {
                c.ctmr.renderTopMenuList(currentTopBarMenu);
            }

            if (amountOfPages > 1)
            {
                c.cc.println(currentlyRenderedTitle + "     (" + currentPage + "/" + amountOfPages + ")");
            }
            else
            {
                c.cc.println(currentlyRenderedTitle);
            }
            c.cc.println("");

            c.cc.print(s);

            if (currentPage == amountOfPages)
            {
                for(int i = 26*(currentPage-1); i < 26*(currentPage-1)+finalPageSize; i++)
                {
                    c.cc.println(indexToLetter((i%26)) + " - " + currentlyRenderedList.get(i));
                }
            }
            else
            {
                for(int i = 26*(currentPage-1); i < 26*(currentPage); i++)
                {
                    c.cc.println(indexToLetter((i%26)) + " - " + currentlyRenderedList.get(i));
                }
            }
        }
        catch (Exception e)
        {
            ErrorHandler.LogData(true,"Was unable to copy the content on screen");
        }
    }
    public void renderPage()
    {
        c.cc.clear();
        appendPage();
    }

    public void reset()
    {
        amountOfPages = 0;
        currentPage = 0;
        currentlyRendering = false;
    }

    public void pageUp()
    {
        if (currentPage<amountOfPages)
        {
            currentPage++;
        }
        else
        {
            currentPage = 1;
        }
        renderPage();
    }

    public void pageDown()
    {
        if (currentPage>1)
        {
            currentPage--;
        }
        else
        {
            currentPage = amountOfPages;
        }
        renderPage();
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
