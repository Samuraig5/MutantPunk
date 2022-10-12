package Main.RenderLogic.Menus;

import Main.RenderLogic.Console;
import Test.Main;

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
        c.cb.spawnHuman("Greg","0","0");
        c.cb.listAllPersons();
    }

    @Override
    public void bElement()
    {
        for (int i = 0; i < 10; i++)
        {
            c.cb.spawnHuman("Bob","0","0");
        }
        c.cb.listAllPersons();
    }

    @Override
    public void cElement()
    {
        for (int i = 0; i < 100; i++)
        {
            c.cb.spawnHuman("Crow","0","0");
        }
        c.cb.listAllPersons();
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
        System.out.println("pageUp");
        c.clir.pageUp();
    }

    @Override
    public void commaElement() {
        System.out.println("pageDown");
        c.clir.pageDown();
    }

    @Override
    public void escapeElement() {

    }
}
