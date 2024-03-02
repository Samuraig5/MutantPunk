package Main.RenderLogic;

import Main.Settings;

import javax.swing.*;
import java.awt.*;

public class ConsolePainter extends JPanel
{
    public Console c;
    public RenderState renderState;

    public ConsolePainter(Console c)
    {
        this.c = c;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        repaint(); revalidate();

        g.setColor(Color.LIGHT_GRAY);
        g.setFont(new Font("Courier New", Font.PLAIN, Settings.fontSize));

        drawBackground(g, new Color(50,50,50));
        switch (renderState)
        {
            case MAIN_MENU:
                drawMainMenu(g);
        }
    }

    private void drawBackground(Graphics g, Color backColor)
    {
        Color current = g.getColor();
        g.setColor(backColor);
        g.fillRect(0, 0, Settings.windowWidth, Settings.windowHeight);
        g.setColor(current);
    }

    private void drawMainMenu(Graphics g)
    {
        Color current = g.getColor();
        String[] logo =c.cc.generateLogo();

        float logoLength = (
                (logo[0].length()-1)*
                        (Settings.fontSize)*Settings.relativeFontWidth);

        for (int i = 0; i < logo.length; i++)
        {
            g.setColor(Color.GREEN);
            g.drawString(logo[i],
                    Math.round((Settings.windowWidth-logoLength)/2),
                    Math.round((50+(i*Settings.fontSize*Settings.relativeFontHeight))));
        }
        g.setColor(current);
    }

}
