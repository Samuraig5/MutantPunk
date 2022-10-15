package Main.RenderLogic.Menus;

import Main.RenderLogic.Console;

public class MainMenu implements MenuLogic
{
    Console c;
    public MainMenu(Console console)
    {
        c = console;
    }

    @Override
    public void aElement()
    {
        c.cb.spawnPerson("Greg","0","0", "Resources/BodyPlans/Human");
        c.cb.listAllPersons();
    }

    @Override
    public void bElement()
    {
        for (int i = 0; i < 10; i++)
        {
            c.cb.spawnPerson("Bob","0","0", "Resources/BodyPlans/Human");
        }
        c.cb.listAllPersons();
    }

    @Override
    public void cElement()
    {
        for (int i = 0; i < 100; i++)
        {
            c.cb.spawnPerson(i+"th Clone","0","0", "Resources/BodyPlans/Human");
        }
        c.cb.listAllPersons();
    }

    @Override
    public void dElement() {
        c.cb.spawnPerson("Minor Mutant","0","50", "Resources/BodyPlans/Human");
        c.cb.listAllPersons();
    }

    @Override
    public void eElement() {
        c.cb.spawnPerson("Human Spider","0","0", "Resources/BodyPlans/HumanSpider");
        c.cb.listAllPersons();
    }

    @Override
    public void fElement() {
        c.cb.spawnPerson("Slime","0","0", "Resources/BodyPlans/Slime");
        c.cb.listAllPersons();
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
        c.clir.pageUp();
    }

    @Override
    public void commaElement() {
        c.clir.pageDown();
    }

    @Override
    public void escapeElement() {

    }
}
