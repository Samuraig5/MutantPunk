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
    Action eAction;
    Action fAction;
    Action gAction;
    Action hAction;
    Action iAction;
    Action jAction;
    Action kAction;
    Action lAction;
    Action mAction;
    Action nAction;
    Action oAction;
    Action pAction;
    Action qAction;
    Action rAction;
    Action sAction;
    Action tAction;
    Action uAction;
    Action vAction;
    Action wAction;
    Action xAction;
    Action yAction;
    Action zAction;
    Action periodAction;
    Action commaAction;
    Action escapeAction;
    Action num0Action;
    Action num1Action;
    Action num2Action;
    Action num3Action;
    Action num4Action;
    Action num5Action;
    Action num6Action;
    Action num7Action;
    Action num8Action;
    Action num9Action;




    ConsoleKeyBinds(Console console)
    {
        c = console;

        label = new JLabel();
        label.setOpaque(false);

        aAction = new AAction();
        bAction = new BAction();
        cAction = new CAction();
        dAction = new DAction();
        eAction = new EAction();
        fAction = new FAction();
        gAction = new GAction();
        hAction = new HAction();
        iAction = new IAction();
        jAction = new JAction();
        kAction = new KAction();
        lAction = new LAction();
        mAction = new MAction();
        nAction = new NAction();
        oAction = new OAction();
        pAction = new PAction();
        qAction = new QAction();
        rAction = new RAction();
        sAction = new SAction();
        tAction = new TAction();
        uAction = new UAction();
        vAction = new VAction();
        wAction = new WAction();
        xAction = new XAction();
        yAction = new YAction();
        zAction = new ZAction();
        periodAction = new PeriodAction();
        commaAction = new CommaAction();
        escapeAction = new EscapeAction();
        num0Action = new Num0Action();
        num1Action = new Num1Action();
        num2Action = new Num2Action();
        num3Action = new Num3Action();
        num4Action = new Num4Action();
        num5Action = new Num5Action();
        num6Action = new Num6Action();
        num7Action = new Num7Action();
        num8Action = new Num8Action();
        num9Action = new Num9Action();

        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('a'), "aAction");
        label.getActionMap().put("aAction", aAction);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('b'), "bAction");
        label.getActionMap().put("bAction", bAction);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('c'), "cAction");
        label.getActionMap().put("cAction", cAction);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('d'), "dAction");
        label.getActionMap().put("dAction", dAction);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('e'), "eAction");
        label.getActionMap().put("eAction", eAction);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('f'), "fAction");
        label.getActionMap().put("fAction", fAction);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('g'), "gAction");
        label.getActionMap().put("gAction", gAction);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('h'), "hAction");
        label.getActionMap().put("hAction", hAction);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('i'), "iAction");
        label.getActionMap().put("iAction", iAction);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('j'), "jAction");
        label.getActionMap().put("jAction", jAction);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('k'), "kAction");
        label.getActionMap().put("kAction", kAction);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('l'), "lAction");
        label.getActionMap().put("lAction", lAction);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('m'), "mAction");
        label.getActionMap().put("mAction", mAction);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('n'), "nAction");
        label.getActionMap().put("nAction", nAction);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('o'), "oAction");
        label.getActionMap().put("oAction", oAction);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('p'), "pAction");
        label.getActionMap().put("pAction", pAction);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('q'), "qAction");
        label.getActionMap().put("qAction", qAction);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('r'), "rAction");
        label.getActionMap().put("rAction", rAction);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('s'), "sAction");
        label.getActionMap().put("sAction", sAction);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('t'), "tAction");
        label.getActionMap().put("tAction", tAction);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('u'), "uAction");
        label.getActionMap().put("uAction", uAction);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('v'), "vAction");
        label.getActionMap().put("vAction", vAction);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('w'), "wAction");
        label.getActionMap().put("wAction", wAction);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('x'), "xAction");
        label.getActionMap().put("xAction", xAction);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('y'), "yAction");
        label.getActionMap().put("yAction", yAction);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('z'), "zAction");
        label.getActionMap().put("zAction", zAction);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('.'), "periodAction");
        label.getActionMap().put("periodAction", periodAction);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(','), "commaAction");
        label.getActionMap().put("commaAction", commaAction);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "escapeAction");
        label.getActionMap().put("escapeAction", escapeAction);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('0'), "num0Action");
        label.getActionMap().put("num0Action", num0Action);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('1'), "num1Action");
        label.getActionMap().put("num1Action", num1Action);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('2'), "num2Action");
        label.getActionMap().put("num2Action", num2Action);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('3'), "num3Action");
        label.getActionMap().put("num3Action", num3Action);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('4'), "num4Action");
        label.getActionMap().put("num4Action", num4Action);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('5'), "num5Action");
        label.getActionMap().put("num5Action", num5Action);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('6'), "num6Action");
        label.getActionMap().put("num6Action", num6Action);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('7'), "num7Action");
        label.getActionMap().put("num7Action", num7Action);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('8'), "num8Action");
        label.getActionMap().put("num8Action", num8Action);
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('9'), "num9Action");
        label.getActionMap().put("num9Action", num9Action);

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
            System.out.println("Button pressed");
        }
    }
    public class DAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMenuLogic.dElement();
        }
    }
    public class EAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMenuLogic.eElement();
        }
    }
    public class FAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMenuLogic.fElement();
        }
    }
    public class GAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMenuLogic.gElement();
        }
    }
    public class HAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMenuLogic.hElement();
        }
    }
    public class IAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMenuLogic.iElement();
        }
    }
    public class JAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMenuLogic.jElement();
        }
    }
    public class KAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMenuLogic.kElement();
        }
    }
    public class LAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMenuLogic.lElement();
        }
    }
    public class MAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMenuLogic.mElement();
        }
    }
    public class NAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMenuLogic.nElement();
        }
    }
    public class OAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMenuLogic.oElement();
        }
    }
    public class PAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMenuLogic.pElement();
        }
    }
    public class QAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMenuLogic.qElement();
        }
    }
    public class RAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMenuLogic.rElement();
        }
    }
    public class SAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMenuLogic.sElement();
        }
    }
    public class TAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMenuLogic.tElement();
        }
    }
    public class UAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMenuLogic.uElement();
        }
    }
    public class VAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMenuLogic.vElement();
        }
    }
    public class WAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMenuLogic.wElement();
        }
    }
    public class XAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMenuLogic.xElement();
        }
    }
    public class YAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMenuLogic.yElement();
        }
    }
    public class ZAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMenuLogic.zElement();
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
    public class Num0Action extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMenuLogic.num0Element();
        }
    }
    public class Num1Action extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMenuLogic.num1Element();
        }
    }
    public class Num2Action extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMenuLogic.num2Element();
        }
    }
    public class Num3Action extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMenuLogic.num3Element();
        }
    }
    public class Num4Action extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMenuLogic.num4Element();
        }
    }
    public class Num5Action extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMenuLogic.num5Element();
        }
    }
    public class Num6Action extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMenuLogic.num6Element();
        }
    }
    public class Num7Action extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMenuLogic.num7Element();
        }
    }
    public class Num8Action extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMenuLogic.num8Element();
        }
    }
    public class Num9Action extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMenuLogic.num9Element();
        }
    }
}
