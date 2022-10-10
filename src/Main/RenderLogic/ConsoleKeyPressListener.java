package Main.RenderLogic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ConsoleKeyPressListener
{
    Console c;
    public ConsoleKeyPressListener(Console console)
    {
        c = console;
    }

    public void initialize()
    {
        c.input.addKeyListener(new KeyListener()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {

            }
            @Override
            public void keyPressed(KeyEvent e)
            {
                if(e.getKeyCode() == KeyEvent.VK_UP)
                {
                    if(c.recentUsedID < (c.recentUsedMax-1) && c.recentUsedID < (c.recentUsed.size()-1))
                    {
                        c.recentUsedID++;
                    }
                    c.input.setText(c.recentUsed.get(c.recentUsed.size()-1-c.recentUsedID));
                }
                else if(e.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    if(c.recentUsedID > 0)
                    {
                        c.recentUsedID--;
                    }
                    c.input.setText(c.recentUsed.get(c.recentUsed.size()-1-c.recentUsedID));
                }
                else if(e.getKeyCode() == KeyEvent.VK_PERIOD)
                {
                    if(c.clir.isCurrentlyRendering())
                    {
                        c.clear();
                        System.out.println("Page Up");
                        c.clir.pageUp();
                        c.clir.renderPage();
                    }
                    c.clearInputField();
                }
                else if(e.getKeyCode() == KeyEvent.VK_COMMA)
                {
                    if(c.clir.isCurrentlyRendering())
                    {
                        c.clear();
                        System.out.println("Page Down");
                        c.clir.pageDown();
                        c.clir.renderPage();
                    }
                    c.clearInputField();
                }
            }
            @Override
            public void keyReleased(KeyEvent e)
            {

            }
        });
    }
}
