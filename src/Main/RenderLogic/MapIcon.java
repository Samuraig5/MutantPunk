package Main.RenderLogic;

import java.awt.*;

public class MapIcon
{
    private char symbol;
    private Color iconColour;

    public MapIcon(char symbol, Color iconColour)
    {
        this.symbol = symbol;
        this.iconColour = iconColour;
    }
    public MapIcon(char symbol)
    {
        this.symbol = symbol;
        this.iconColour = Color.lightGray;
    }
    public MapIcon()
    {
        this.symbol = '?';
        this.iconColour = Color.lightGray;
    }

    public char getSymbol(){return symbol;}
    public void setSymbol(char newSymbol){symbol = newSymbol;}

    public Color getIconColour(){return iconColour;}
    public void setIconColour(Color newColour){iconColour = newColour;}
}
