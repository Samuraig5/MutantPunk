package Main.RenderLogic.Logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class MapIcon
{
    private Sprite sprite = new Sprite();
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
        if (sprite == null) {return false;}
        if (sprite.getImage() == null) {return false;}
        else {return true;}
    }
    public void setSprite(Image image, boolean fullCover)
    {
        sprite.setImage(image);
        sprite.setFullCover(fullCover);
    }
    public void setSprite(String addr, boolean fullCover)
    {
        sprite.setImage(addr);
        sprite.setFullCover(fullCover);
    }
    public void setSprite(Sprite sprite)
    {
        this.sprite = sprite;
    }
    public Sprite getSprite() {return sprite;}
    public char getSymbol() {return symbol;}
    public void setSymbol(char newSymbol) {symbol = newSymbol;}

    public Color getIconColour() {return iconColour;}
    public void setIconColour(Color newColour) {iconColour = newColour;}
}
