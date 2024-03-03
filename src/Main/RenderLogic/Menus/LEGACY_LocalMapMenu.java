package Main.RenderLogic.Menus;

import Main.RenderLogic.Console;
import Main.WorldLogic.LocalMap;

public class LEGACY_LocalMapMenu implements MenuLogic
{
    Console c;
    LocalMap lm;
    public LEGACY_LocalMapMenu(Console console, LocalMap localMap)
    {
        c = console;
        c.setInMainMenu(false);
        lm = localMap;
    }

    @Override
    public void aElement() {
        c.cm.RenderLocalMap(lm);
        c.wc.startClock();
    }

    @Override
    public void bElement() {
        c.cb.listAllPersons(lm);
    }

    @Override
    public void cElement() {
        c.cb.spawnPerson("Greg","0","0", "Resources/BodyPlans/Human", lm);
        c.cb.listAllPersons(lm);
    }

    @Override
    public void dElement() {
        for (int i = 0; i < 10; i++)
        {
            c.cb.spawnPerson("Bob","0","0", "Resources/BodyPlans/Human", lm);
        }
        c.cb.listAllPersons(lm);
    }

    @Override
    public void eElement() {
        for (int i = 0; i < 100; i++)
        {
            c.cb.spawnPerson(i+"th Clone","0","0", "Resources/BodyPlans/Human", lm);
        }
        c.cb.listAllPersons(lm);
    }

    @Override
    public void fElement() {
        c.cb.spawnPerson("Minor Mutant","0","50", "Resources/BodyPlans/Human", lm);
        c.cb.listAllPersons(lm);
    }

    @Override
    public void gElement() {
        c.cb.spawnPerson("Human Spider","0","0", "Resources/BodyPlans/HumanSpider", lm);
        c.cb.listAllPersons(lm);
    }

    @Override
    public void hElement() {
        c.cb.spawnPerson("Slime","0","0", "Resources/BodyPlans/Slime", lm);
        c.cb.listAllPersons(lm);
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
        c.cm.openWorldMenu(lm.getMyWorld());
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
