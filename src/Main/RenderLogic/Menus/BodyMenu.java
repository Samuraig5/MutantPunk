package Main.RenderLogic.Menus;

import Main.BodyLogic.BodyPart;
import Main.BodyLogic.Person;
import Main.RenderLogic.Console;
import Main.WorldLogic.GameWorld;

public class BodyMenu implements MenuLogic
{
    Console c;
    Person p;
    public BodyMenu(Console console, Person person)
    {
        c = console;
        p = person;
    }

    private void openBodyPartView(int i)
    {
        int currentPage = c.clir.getCurrentPage();
        BodyPart bp = p.myBodyParts.get((26*currentPage-26)+i);
        c.cb.openBodyPartView(bp);
    }

    @Override
    public void aElement() {
        openBodyPartView(0);
    }

    @Override
    public void bElement() {
        openBodyPartView(1);
    }

    @Override
    public void cElement() {
        openBodyPartView(2);
    }

    @Override
    public void dElement() {
        openBodyPartView(3);
    }

    @Override
    public void eElement() {
        openBodyPartView(4);
    }

    @Override
    public void fElement() {
        openBodyPartView(5);
    }

    @Override
    public void gElement() {
        openBodyPartView(6);
    }

    @Override
    public void hElement() {
        openBodyPartView(7);
    }

    @Override
    public void iElement() {
        openBodyPartView(8);
    }

    @Override
    public void jElement() {
        openBodyPartView(9);
    }

    @Override
    public void kElement() {
        openBodyPartView(10);
    }

    @Override
    public void lElement() {
        openBodyPartView(11);
    }

    @Override
    public void mElement() {
        openBodyPartView(12);
    }

    @Override
    public void nElement() {
        openBodyPartView(13);
    }

    @Override
    public void oElement() {
        openBodyPartView(14);
    }

    @Override
    public void pElement() {
        openBodyPartView(15);
    }

    @Override
    public void qElement() {
        openBodyPartView(16);
    }

    @Override
    public void rElement() {
        openBodyPartView(17);
    }

    @Override
    public void sElement() {
        openBodyPartView(18);
    }

    @Override
    public void tElement() {
        openBodyPartView(19);
    }

    @Override
    public void uElement() {
        openBodyPartView(20);
    }

    @Override
    public void vElement() {
        openBodyPartView(21);
    }

    @Override
    public void wElement() {
        openBodyPartView(22);
    }

    @Override
    public void xElement() {
        openBodyPartView(23);
    }

    @Override
    public void yElement() {
        openBodyPartView(24);
    }

    @Override
    public void zElement() {
        openBodyPartView(25);
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
        c.cb.openPersonView(p);
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
