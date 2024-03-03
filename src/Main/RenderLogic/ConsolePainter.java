package Main.RenderLogic;

import Main.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class ConsolePainter extends JPanel
{
    public Console c;

    private Graphics g;

    private KeyListener activeKeyListener;

    public ConsolePainter(Console c)
    {
        this.c = c;
    }

    public void newListener(KeyListener keyListener)
    {
        if (activeKeyListener != null)
        {
            removeKeyListener(activeKeyListener);
        }
        addKeyListener(keyListener);
        this.setFocusable(true);
        activeKeyListener = keyListener;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        repaint(); revalidate();

        this.g = g;

        g.setColor(Color.LIGHT_GRAY);
        g.setFont(new Font("Courier New", Font.PLAIN, Settings.fontSize));

        drawBackground(new Color(50,50,50));

        GameState gameState = c.getGameState();

        switch (c.getGameState())
        {
            case MAIN_MENU:
                drawMainMenu();
                break;
            case WORLD_MENU:
                drawWorldMenu();
                break;
        }
    }

    private void printString(int xPos, int yPos, Color c, String s)
    {
        Color current = g.getColor();
        g.setColor(c);
        g.drawString(s, xPos, yPos);
        g.setColor(current);
    }

    private void printString(int xPos, int yPos, Color c, String[] s)
    {
        for (int i = 0; i < s.length; i++)
        {
            printString(xPos,yPos+Math.round((50+(i*Settings.fontSize*Settings.relativeFontHeight))), c,s);
        }
    }

    private void printCentredString(int yPos, Color c, String s)
    {
        float stringWidth = ((s.length()-1)* (Settings.fontSize)*Settings.relativeFontWidth);
        int xPos = Math.round((Settings.windowWidth-stringWidth)/2);

        printString(xPos,yPos,c,s);
    }

    private void printCentredString(int yPos, Color c, String s[])
    {
        for (int i = 0; i < s.length; i++) {
            float stringWidth = ((s[i].length()-1)* (Settings.fontSize)*Settings.relativeFontWidth);
            int xPos = Math.round((Settings.windowWidth-stringWidth)/2);

            printString(xPos,yPos+Math.round((50+(i*Settings.fontSize*Settings.relativeFontHeight))), c,s[i]);
        }
    }

    private void drawBackground(Color backColor)
    {
        Color current = g.getColor();
        g.setColor(backColor);
        g.fillRect(0, 0, Settings.windowWidth, Settings.windowHeight);
        g.setColor(current);
    }

    private void drawMainMenu()
    {
        String[] logo =c.cc.generateLogo();
        printCentredString(50,Color.green,logo);

        printCentredString(250, Color.green, "Strange creatures in a strange land");
        printCentredString(300, Color.lightGray, "a: Generate a new World");
    }

    private void drawWorldMenu()
    {
        printCentredString(50, Color.lightGray, "Welcome to a new world");
    }
}
