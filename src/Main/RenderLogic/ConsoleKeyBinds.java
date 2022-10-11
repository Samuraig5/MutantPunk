package Main.RenderLogic;

import Main.RenderLogic.Console;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

class ConsoleKeyBinds
{
    Console c;
    JFrame frame;
    JLabel label;

    Action aAction;
    Action bAction;
    Action cAction;
    Action dAction;

    ConsoleKeyBinds(Console console)
    {
        c = console;
        frame = c.frame;

        label = new JLabel();
        label.setOpaque(false);

        aAction = new AAction();
        bAction = new BAction();
        cAction = new CAction();
        dAction = new DAction();

        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('a'), "aAction");
        label.getActionMap().put("aAction", aAction);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('b'), "bAction");
        label.getActionMap().put("bAction", bAction);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('c'), "cAction");
        label.getActionMap().put("cAction", cAction);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('d'), "dAction");
        label.getActionMap().put("dAction", dAction);
    }

    public class AAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
    public class BAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("PageDown!");
        }
    }
    public class CAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
    public class DAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
