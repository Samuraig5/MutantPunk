package Main.RenderLogic.UI_Painter.Button;

import Main.RenderLogic.Console;
import Main.RenderLogic.Logic.GameState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameStateChanger implements ActionListener
{
    private final GameState gameState;
    private final Console c;
    public GameStateChanger(Console c, GameState gameState)
    {
        this.c = c;
        this.gameState = gameState;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        c.setGameState(gameState);
    }
}
