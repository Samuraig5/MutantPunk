package Main.RenderLogic.UI_Painter;

import Main.MenuLogic.*;
import Main.RenderLogic.Console;
import Main.RenderLogic.Logic.GameState;
import Main.RenderLogic.UI_Painter.Button.Button;
import Main.RenderLogic.UI_Painter.Button.ClearPlayerCharacter;
import Main.RenderLogic.UI_Painter.Button.GameStateChanger;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.invoke.SwitchPoint;
import java.util.ArrayList;
import java.util.List;

public class ButtonHandler
{
    Console c;
    private final List<Button> worldMapViewButtons = new ArrayList<>();
    private final List<Button> localMapPlayerViewButtons = new ArrayList<>();


    public ButtonHandler(Console c)
    {
        this.c = c;
        worldMapViewButtons.add(new Button("Return to Menu", 10, 10,
                new ActionListener[]{new GameStateChanger(c, GameState.MAIN_MENU)}));

        localMapPlayerViewButtons.add(new Button("Clear Player", 10, 10,
                new ActionListener[]{new ClearPlayerCharacter(c)}));
    }

    public void drawButtons(Graphics g)
    {
        switch (c.getGameState())
        {
            case MAIN_MENU:
                break;
            case WORLD_MENU:
                for (Button button: worldMapViewButtons)
                {
                    button.draw(g);
                }
                break;
            case LOCAL_MAP_MENU:
                break;
            case LOCAL_MAP_VIEW:
                if (c.cp.getPlayerCharacter() != null)
                {
                    for (Button button: localMapPlayerViewButtons)
                    {
                        button.draw(g);
                    }
                }
                break;
            case ALL_CHARACTERS_IN_LOCAL_MAP:
                break;
            case THING_INSPECTOR:
                break;
            case BODY_PART_MENU:
                break;
            default:
                throw new RuntimeException("This state is not defined in ButtonHandler");
        }
    }

    public boolean mouseClick(Point p)
    {
        switch (c.getGameState())
        {
            case MAIN_MENU:
                break;
            case WORLD_MENU:
                for (Button button: worldMapViewButtons)
                {
                    if (button.buttonClick(p))
                    {
                        return true;
                    }
                }
                break;
            case LOCAL_MAP_MENU:
                break;
            case LOCAL_MAP_VIEW:
                if (c.cp.getPlayerCharacter() != null)
                {
                    for (Button button: localMapPlayerViewButtons)
                    {
                        if (button.buttonClick(p))
                        {
                            return true;
                        }
                    }
                }
                break;
            case ALL_CHARACTERS_IN_LOCAL_MAP:
                break;
            case THING_INSPECTOR:
                break;
            case BODY_PART_MENU:
                break;
            default:
                throw new RuntimeException("This state is not defined in ButtonHandler");
        }
        return false;
    }
}
