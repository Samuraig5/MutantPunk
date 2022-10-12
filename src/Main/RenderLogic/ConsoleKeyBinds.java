package Main.RenderLogic;

import Main.RenderLogic.Console;
import Main.RenderLogic.Menus.MenuLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

class ConsoleKeyBinds
{
    Console c;
    JFrame frame;
    JLabel label;

    private MenuLogic currentMenuLogic;

    Action aAction;
    Action bAction;
    Action cAction;
    Action dAction;
    Action periodAction;
    Action commaAction;
    Action escapeAction;

    ConsoleKeyBinds(Console console)
    {
        c = console;

        label = new JLabel();
        label.setOpaque(false);

        aAction = new AAction();
        bAction = new BAction();
        cAction = new CAction();
        dAction = new DAction();
        periodAction = new PeriodAction();
        commaAction = new CommaAction();
        escapeAction = new EscapeAction();

        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('a'), "aAction");
        label.getActionMap().put("aAction", aAction);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('b'), "bAction");
        label.getActionMap().put("bAction", bAction);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('c'), "cAction");
        label.getActionMap().put("cAction", cAction);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('d'), "dAction");
        label.getActionMap().put("dAction", dAction);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('.'), "periodAction");
        label.getActionMap().put("periodAction", periodAction);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(','), "commaAction");
        label.getActionMap().put("commaAction", commaAction);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "escapeAction");
        label.getActionMap().put("escapeAction", escapeAction);
    }

    public void setCurrentMenu(MenuLogic newMenuLogic)
    {
        currentMenuLogic = newMenuLogic;
    }

    public class AAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMenuLogic.aElement();
        }
    }
    public class BAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMenuLogic.bElement();
        }
    }
    public class CAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMenuLogic.cElement();
        }
    }
    public class DAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMenuLogic.dElement();
        }
    }
    public class PeriodAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMenuLogic.periodElement();
        }
    }
    public class CommaAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMenuLogic.commaElement();
        }
    }
    public class EscapeAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMenuLogic.escapeElement();
        }
    }
}
