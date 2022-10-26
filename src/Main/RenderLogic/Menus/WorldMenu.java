package Main.RenderLogic.Menus;

import Main.RenderLogic.Console;
import Main.WorldLogic.GameWorld;

public class WorldMenu implements MenuLogic
{
    Console c;
    GameWorld gw;
    public WorldMenu(Console console, GameWorld gameWorld)
    {
        c = console;
        c.setInMainMenu(false);
        gw = gameWorld;
    }

    @Override
    public void aElement()
    {
        int[] size = {50,50};
        c.cm.RenderLocalMap(c.cm.GenerateEmptyLocalMap(size, gw));
    }

    @Override
    public void bElement()
    {
        c.cb.spawnPerson("Greg","0","0", "Resources/BodyPlans/Human", gw);
        c.cb.listAllPersons(gw);
    }

    @Override
    public void cElement()
    {
        for (int i = 0; i < 10; i++)
        {
            c.cb.spawnPerson("Bob","0","0", "Resources/BodyPlans/Human", gw);
        }
        c.cb.listAllPersons(gw);
    }

    @Override
    public void dElement() {
        for (int i = 0; i < 100; i++)
        {
            c.cb.spawnPerson(i+"th Clone","0","0", "Resources/BodyPlans/Human", gw);
        }
        c.cb.listAllPersons(gw);
    }

    @Override
    public void eElement() {
        c.cb.spawnPerson("Minor Mutant","0","50", "Resources/BodyPlans/Human", gw);
        c.cb.listAllPersons(gw);
    }

    @Override
    public void fElement() {
        c.cb.spawnPerson("Human Spider","0","0", "Resources/BodyPlans/HumanSpider", gw);
        c.cb.listAllPersons(gw);
    }

    @Override
    public void gElement() {
        c.cb.spawnPerson("Slime","0","0", "Resources/BodyPlans/Slime", gw);
        c.cb.listAllPersons(gw);
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
        c.clir.pageUp();
    }

    @Override
    public void commaElement() {
        c.clir.pageDown();
    }

    @Override
    public void escapeElement() {
        c.cc.openMainMenu();
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
