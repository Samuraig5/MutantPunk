package Main.RenderLogic;

import Main.Settings;

import javax.swing.*;
import java.awt.*;

public class ConsolePainter extends JPanel
{
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        repaint(); revalidate();

        g.setFont(new Font("arial", Font.PLAIN, 30));
        g.drawString("Hello World", 800, 50);
    }

}
