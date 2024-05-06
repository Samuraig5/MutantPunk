package Main.RenderLogic.Logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class MapIcon
{
    private Image sprite;
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

    public boolean hasSprite()
    {
        if (sprite == null) { return false;}
        else { return true; }
    }
    public void setSprite(Image sprite) {this.sprite = sprite;}
    public Image getSprite() {return sprite;}
    public char getSymbol(){return symbol;}
    public void setSymbol(char newSymbol){symbol = newSymbol;}

    public Color getIconColour(){return iconColour;}
    public void setIconColour(Color newColour){iconColour = newColour;}
}
