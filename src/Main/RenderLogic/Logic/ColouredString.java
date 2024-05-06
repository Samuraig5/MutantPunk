package Main.RenderLogic.Logic;

import java.awt.*;

public class ColouredString
{
    private String string;
    private Color color;
    public ColouredString(String string, Color color)
    {
        this.string = string;
        this.color = color;
    }
    public ColouredString(String string)
    {
        this.string = string;
        this.color = Color.lightGray;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
