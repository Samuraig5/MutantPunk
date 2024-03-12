package Main.RenderLogic.Menus;

import Main.ObjectLogic.BodyLogic.Person;
import Main.RenderLogic.Console;
import Main.WorldLogic.GameWorld;

public class AllCharactersInWorldMenu implements MenuLogic
{
    Console c;
    GameWorld gw;
    public AllCharactersInWorldMenu(Console console, GameWorld gameWorld)
    {
        c = console;
        //c.setInMainMenu(false);
        gw = gameWorld;
    }

    private void openPersonView(int i)
    {
        int currentPage = c.clir.getCurrentPage();
        Person p = gw.getAllCharacters().get((26*currentPage-26)+i);
        //c.cb.LEGACY_openPersonView(p);
    }

    @Override
    public void aElement() {
        openPersonView(0);
    }

    @Override
    public void bElement() {
        openPersonView(1);
    }

    @Override
    public void cElement() {
        openPersonView(2);
    }

    @Override
    public void dElement() {
        openPersonView(3);
    }

    @Override
    public void eElement() {
        openPersonView(4);
    }

    @Override
    public void fElement() {
        openPersonView(5);
    }

    @Override
    public void gElement() {
        openPersonView(6);
    }

    @Override
    public void hElement() {
        openPersonView(7);
    }

    @Override
    public void iElement() {
        openPersonView(8);
    }

    @Override
    public void jElement() {
        openPersonView(9);
    }

    @Override
    public void kElement() {
        openPersonView(10);
    }

    @Override
    public void lElement() {
        openPersonView(11);
    }

    @Override
    public void mElement() {
        openPersonView(12);
    }

    @Override
    public void nElement() {
        openPersonView(13);
    }

    @Override
    public void oElement() {
        openPersonView(14);
    }

    @Override
    public void pElement() {
        openPersonView(15);
    }

    @Override
    public void qElement() {
        openPersonView(16);
    }

    @Override
    public void rElement() {
        openPersonView(17);
    }

    @Override
    public void sElement() {
        openPersonView(18);
    }

    @Override
    public void tElement() {
        openPersonView(19);
    }

    @Override
    public void uElement() {
        openPersonView(20);
    }

    @Override
    public void vElement() {
        openPersonView(21);
    }

    @Override
    public void wElement() {
        openPersonView(22);
    }

    @Override
    public void xElement() {
        openPersonView(23);
    }

    @Override
    public void yElement() {
        openPersonView(24);
    }

    @Override
    public void zElement() {
        openPersonView(25);
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
        c.cm.openWorldMenu(gw);
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
