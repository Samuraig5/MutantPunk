package Main.RenderLogic.UI_Painter;

import Main.MathHelper;

import java.awt.*;

public class UIHelper
{
    public static void drawBar(Graphics g, float xPos, float yPos, float width, float height,
                               float currVal, float minVal, float maxVal,
                               Color fillColour, Color backColour)
    {
        int rxPos = Math.round(xPos);
        int ryPos = Math.round(yPos);
        int rwidth = Math.round(width);
        int rheight = Math.round(height);

        Color oldColour = g.getColor();

        g.setColor(backColour);
        g.fillRoundRect(rxPos,ryPos,rwidth,rheight,5,5);

        float percentage = (currVal-minVal)/(maxVal-minVal);
        percentage = MathHelper.clamp(percentage, 0, maxVal);
        int fillWidth = Math.round(width*percentage);

        g.setColor(fillColour);
        g.fillRoundRect(rxPos,ryPos,fillWidth, rheight,5,5);

        g.setColor(oldColour);
    }
}
