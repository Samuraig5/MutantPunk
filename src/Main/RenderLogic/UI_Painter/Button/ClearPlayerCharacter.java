package Main.RenderLogic.UI_Painter.Button;

import Main.RenderLogic.Console;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearPlayerCharacter implements ActionListener
{
    private final Console c;
    public ClearPlayerCharacter(Console c)
    {
        this.c = c;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        c.cp.clearPlayerCharacter();
        c.cp.setFocusedThing(null);
        c.cp.setCursorEnabled(true);
    }
}
