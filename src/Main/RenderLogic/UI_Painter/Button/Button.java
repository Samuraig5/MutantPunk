package Main.RenderLogic.UI_Painter.Button;

import Main.RenderLogic.Logic.ColouredString;
import Main.RenderLogic.UI_Painter.UIHelper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;

public class Button
{
    private ColouredString label;
    private Rectangle bounds;
    private Color backgroundColour;
    private Color rimColour;
    private List<ActionListener> listeners = new ArrayList<>();

    private void Initialize(ColouredString label, int x, int y, int width, int height, Color bgColor, Color rimColor, ActionListener[] listeners)
    {
        this.label = label;
        this.bounds = new Rectangle(x, y, width, height);
        this.backgroundColour = bgColor;
        this.rimColour = rimColor;

        for (ActionListener listener:listeners)
        {
            addActionListener(listener);
        }
    }

    public Button(ColouredString label, int x, int y, int width, int height, Color bgColor, Color rimColor, ActionListener[] listeners)
    {
        Initialize(label, x, y, width, height, bgColor, rimColor, listeners);
    }

    public Button(String label, int x, int y, ActionListener[] listeners)
    {
        Initialize(new ColouredString(label, Color.lightGray), x, y, 150, 50, Color.darkGray, Color.lightGray, listeners);
    }



    public void draw(Graphics g) {
        g.setColor(backgroundColour);
        g.fillRoundRect(bounds.x, bounds.y, bounds.width, bounds.height, 5, 5);
        g.setColor(rimColour);
        g.drawRoundRect(bounds.x, bounds.y, bounds.width, bounds.height, 5, 5);
        UIHelper.printString(g, bounds.x + 10, bounds.y + bounds.height/2f, label);
    }

    public boolean contains(Point p) {
        return bounds.contains(p);
    }

    public void addActionListener(ActionListener listener)
    {
        listeners.add(listener);
        /*
        addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "clicked"));
            }
        });
         */
    }

    public boolean buttonClick(Point p)
    {
        if (contains(p))
        {
            for (ActionListener listener:listeners)
            {
                listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "clicked"));
            }
            return true;
        }
        else
        {
            return false;
        }
    }
}