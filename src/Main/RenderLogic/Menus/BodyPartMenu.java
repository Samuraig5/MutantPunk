package Main.RenderLogic.Menus;

import Main.BodyLogic.BodyPart;
import Main.BodyLogic.Person;
import Main.ErrorHandler;
import Main.RenderLogic.Console;
import Main.WorldLogic.GameWorld;

public class BodyPartMenu implements MenuLogic
{
    Console c;
    BodyPart bp;
    Person p;
    GameWorld gw;
    public BodyPartMenu(Console console, BodyPart bodyPart)
    {
        c = console;
        c.setInMainMenu(false);
        bp = bodyPart;
        p = bp.getMyPerson();
    }

    private void openBodyPartView(int i)
    {
        int currentPage = c.clir.getCurrentPage();
        BodyPart child = bp.getAttachedBodyParts().get((26*currentPage-26)+i);
        c.cb.openBodyPartView(child);
    }


    @Override
    public void aElement()
    {
        if (bp.getMyPerson() != null)
        {
            c.cb.openPersonView(bp.getMyPerson());
        }
        else
        {
            ErrorHandler.LogData(true,"No Person!");
        }
    }

    @Override
    public void bElement()
    {
        if (bp.getParentBodyPart() != null)
        {
            c.cb.openBodyPartView(bp.getParentBodyPart());
        }
    }

    @Override
    public void cElement()
    {
        openBodyPartView(0);
    }

    @Override
    public void dElement() {
        openBodyPartView(1);
    }

    @Override
    public void eElement() {
        openBodyPartView(2);
    }

    @Override
    public void fElement() {
        openBodyPartView(3);
    }

    @Override
    public void gElement() {
        openBodyPartView(4);
    }

    @Override
    public void hElement() {
        openBodyPartView(5);
    }

    @Override
    public void iElement() {
        openBodyPartView(6);
    }

    @Override
    public void jElement() {
        openBodyPartView(7);
    }

    @Override
    public void kElement() {
        openBodyPartView(8);
    }

    @Override
    public void lElement() {
        openBodyPartView(9);
    }

    @Override
    public void mElement() {
        openBodyPartView(10);
    }

    @Override
    public void nElement() {
        openBodyPartView(11);
    }

    @Override
    public void oElement() {
        openBodyPartView(12);
    }

    @Override
    public void pElement() {
        openBodyPartView(13);
    }

    @Override
    public void qElement() {
        openBodyPartView(14);
    }

    @Override
    public void rElement() {
        openBodyPartView(15);
    }

    @Override
    public void sElement() {
        openBodyPartView(16);
    }

    @Override
    public void tElement() {
        openBodyPartView(17);
    }

    @Override
    public void uElement() {
        openBodyPartView(18);
    }

    @Override
    public void vElement() {
        openBodyPartView(19);
    }

    @Override
    public void wElement() {
        openBodyPartView(20);
    }

    @Override
    public void xElement() {
        openBodyPartView(21);
    }

    @Override
    public void yElement() {
        openBodyPartView(22);
    }

    @Override
    public void zElement() {
        openBodyPartView(23);
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
        c.cb.openBodyView(bp.getMyPerson());
    }

    @Override
    public void num0Element() {
        bp.doDamage(1);
        if(bp.getCurrentHealth() > 0)
        {
            c.cb.openBodyPartView(bp);
        }
        else
        {
            if (p != null)
            {
                c.cb.openBodyView(p);
            }
            else
            {
                c.cb.listAllPersons(gw);
            }
        }
    }

    @Override
    public void num1Element()
    {
        bp.regenerateDamage();
        if(bp.getCurrentHealth() > 0)
        {
            c.cb.openBodyPartView(bp);
        }
        else
        {
            if (p != null)
            {
                c.cb.openBodyView(p);
            }
            else
            {
                c.cb.listAllPersons(gw);
            }
        }
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
