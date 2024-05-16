package Main.RenderLogic.UI_Painter;

import Main.RenderLogic.Console;
import Main.RenderLogic.ConsolePainter;
import Main.RenderLogic.Logic.ColouredString;
import Main.RenderLogic.Logic.GameState;
import Main.RenderLogic.UI_Painter.Button.Button;
import Main.RenderLogic.UI_Painter.Button.GameStateChanger;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ButtonHandler
{
    private final List<Button> worldMapViewButtons = new ArrayList<>();

    public ButtonHandler(Console c)
    {
        worldMapViewButtons.add(new Button("Return to Menu", 10, 10,
                new ActionListener[]{new GameStateChanger(c, GameState.MAIN_MENU)}));
    }

    public void drawWorldMapView(Graphics g)
    {
        for (Button button: worldMapViewButtons)
        {
            button.draw(g);
        }
    }

    public boolean mouseClickInWorldMapView(Point p)
    {
        for (Button button: worldMapViewButtons)
        {
            if (button.buttonClick(p))
            {
                return true;
            }
        }
        return false;
    }
}
