package Main.RenderLogic.UI_Painter;

import Main.MathHelper;

import java.awt.*;

public class UIHelper
{
    public static void drawBar(Graphics g, float xPos, float yPos, float width, float height,
                               float currVal, float minVal, float maxVal,
                               Color fillColour, Color backColour)
    {
        drawBar(g, xPos, yPos, width, height, currVal, minVal, maxVal, fillColour, backColour, null);
    }
    public static void drawBar(Graphics g, float xPos, float yPos, float width, float height,
                               float currVal, float minVal, float maxVal,
                               Color fillColour, Color backColour, Color rimColour)
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

        if (rimColour != null)
        {
            g.setColor(rimColour);
            g.drawRoundRect(rxPos, ryPos, rwidth, rheight, 5, 5);
        }

        g.setColor(oldColour);
    }
    public static void drawBarWithMarkers(Graphics g, float xPos, float yPos, float width, float height,
                                          float currVal, float minVal, float maxVal,
                                          Color fillColour, Color backColour, Color rimColour,
                                          float[] markerVals, Color[] markerColours)
    {
        Color oldColour = g.getColor();

        drawBar(g, xPos, yPos, width, height, currVal, minVal, maxVal, fillColour, backColour, rimColour);
        int rxPos = Math.round(xPos);
        int ryPos = Math.round(yPos);
        int rheight = Math.round(height);

        for (int i = 0; i < markerVals.length; i++)
        {
            float percentage = (markerVals[i]-minVal)/(maxVal-minVal);
            percentage = MathHelper.clamp(percentage, 0, maxVal);
            int markerPos = Math.round(width*percentage);

            if (markerColours[i] != null)
            {
                g.setColor(markerColours[i]);
            }
            g.fillRoundRect(markerPos-1+rxPos, ryPos, 2, rheight, 0, 0);
        }

        g.setColor(oldColour);
    }
}
