package Main.RenderLogic.Menus.LEGACY;

import Main.RenderLogic.Console;
import Main.RenderLogic.Menus.MenuLogic;
import Main.WorldLogic.GameWorld;
import Main.WorldLogic.MapGenerator;

public class LEGACY_MainMenu implements MenuLogic
{
    Console c;
    public LEGACY_MainMenu(Console console)
    {
        c = console;
        //c.setInMainMenu(true);
    }

    @Override
    public void aElement() {
        GameWorld gw = MapGenerator.generateNewGameWorld("New World");
        c.wc.setActiveWorld(gw);
        c.cm.openWorldMenu(gw);
    }

    @Override
    public void bElement() {

    }

    @Override
    public void cElement() {

    }

    @Override
    public void dElement() {

    }

    @Override
    public void eElement() {

    }

    @Override
    public void fElement() {

    }

    @Override
    public void gElement() {

    }

    @Override
    public void hElement() {

    }

    @Override
    public void iElement() {

    }

    @Override
    public void jElement() {

    }

    @Override
    public void kElement() {

    }

    @Override
    public void lElement() {

    }

    @Override
    public void mElement() {

    }

    @Override
    public void nElement() {

    }

    @Override
    public void oElement() {

    }

    @Override
    public void pElement() {

    }

    @Override
    public void qElement() {

    }

    @Override
    public void rElement() {

    }

    @Override
    public void sElement() {

    }

    @Override
    public void tElement() {

    }

    @Override
    public void uElement() {

    }

    @Override
    public void vElement() {

    }

    @Override
    public void wElement() {

    }

    @Override
    public void xElement() {

    }

    @Override
    public void yElement() {

    }

    @Override
    public void zElement() {

    }

    @Override
    public void periodElement() {

    }

    @Override
    public void commaElement() {

    }

    @Override
    public void escapeElement() {

    }

    @Override
    public void num0Element() {

    }

    @Override
    public void num1Element() {

    }

    @Override
    public void num2Element() {

    }

    @Override
    public void num3Element() {

    }

    @Override
    public void num4Element() {

    }

    @Override
    public void num5Element() {

    }

    @Override
    public void num6Element() {

    }

    @Override
    public void num7Element() {

    }

    @Override
    public void num8Element() {

    }

    @Override
    public void num9Element() {

    }
}
