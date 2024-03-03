package Main.RenderLogic.Menus.LEGACY;

import Main.RenderLogic.Console;
import Main.RenderLogic.Menus.MenuLogic;
import Main.Settings;
import Main.WorldLogic.GameWorld;
import Main.WorldLogic.LocalMap;

public class LEGACY_WorldMenu implements MenuLogic
{
    Console c;
    GameWorld gw;
    public LEGACY_WorldMenu(Console console, GameWorld gameWorld)
    {
        c = console;
        c.setInMainMenu(false);
        gw = gameWorld;
    }

    private void openLocalMapView(int i)
    {
        int currentPage = c.clir.getCurrentPage();
        LocalMap lm = gw.getLocalMaps().get((26*currentPage-26)+i);
        c.cm.openLocalMapMenu(lm);
    }

    @Override
    public void aElement()
    {
        if (c.clir.getCurrentPage() == 1)
        {
            int[] size = {Settings.mapSizeX, Settings.mapSizeY};
            c.cm.openLocalMapMenu(c.cm.GenerateLocalMapWithWalls(size, gw, "LocalMap: " + gw.getLocalMaps().size(), 0.01f));
        }
        else
        {
            openLocalMapView(-1);
        }

        //c.cm.RenderLocalMap(c.cm.GenerateEmptyLocalMap(size, gw, "LocalMap: " + gw.getLocalMaps().size()));
    }

    @Override
    public void bElement()
    {
        int i = 0;
        if (c.clir.getCurrentPage() < 1) {i = i + 1;}
        openLocalMapView(i);
    }

    @Override
    public void cElement()
    {
        int i = 1;
        if (c.clir.getCurrentPage() < 1) {i = i + 1;}
        openLocalMapView(i);
    }

    @Override
    public void dElement() {
        int i = 2;
        if (c.clir.getCurrentPage() < 1) {i = i + 1;}
        openLocalMapView(i);
    }

    @Override
    public void eElement() {
        int i = 3;
        if (c.clir.getCurrentPage() < 1) {i = i + 1;}
        openLocalMapView(i);
    }

    @Override
    public void fElement() {
        int i = 4;
        if (c.clir.getCurrentPage() < 1) {i = i + 1;}
        openLocalMapView(i);
    }

    @Override
    public void gElement() {
        int i = 5;
        if (c.clir.getCurrentPage() < 1) {i = i + 1;}
        openLocalMapView(i);
    }

    @Override
    public void hElement() {
        int i = 6;
        if (c.clir.getCurrentPage() < 1) {i = i + 1;}
        openLocalMapView(i);
    }

    @Override
    public void iElement() {
        int i = 7;
        if (c.clir.getCurrentPage() < 1) {i = i + 1;}
        openLocalMapView(i);
    }

    @Override
    public void jElement() {
        int i = 8;
        if (c.clir.getCurrentPage() < 1) {i = i + 1;}
        openLocalMapView(i);
    }

    @Override
    public void kElement() {
        int i = 9;
        if (c.clir.getCurrentPage() < 1) {i = i + 1;}
        openLocalMapView(i);
    }

    @Override
    public void lElement() {
        int i = 10;
        if (c.clir.getCurrentPage() < 1) {i = i + 1;}
        openLocalMapView(i);
    }

    @Override
    public void mElement() {
        int i = 11;
        if (c.clir.getCurrentPage() < 1) {i = i + 1;}
        openLocalMapView(i);
    }

    @Override
    public void nElement() {
        int i = 12;
        if (c.clir.getCurrentPage() < 1) {i = i + 1;}
        openLocalMapView(i);
    }

    @Override
    public void oElement() {
        int i = 13;
        if (c.clir.getCurrentPage() < 1) {i = i + 1;}
        openLocalMapView(i);
    }

    @Override
    public void pElement() {
        int i = 14;
        if (c.clir.getCurrentPage() < 1) {i = i + 1;}
        openLocalMapView(i);
    }

    @Override
    public void qElement() {
        int i = 15;
        if (c.clir.getCurrentPage() < 1) {i = i + 1;}
        openLocalMapView(i);
    }

    @Override
    public void rElement() {
        int i = 16;
        if (c.clir.getCurrentPage() < 1) {i = i + 1;}
        openLocalMapView(i);
    }

    @Override
    public void sElement() {
        int i = 17;
        if (c.clir.getCurrentPage() < 1) {i = i + 1;}
        openLocalMapView(i);
    }

    @Override
    public void tElement() {
        int i = 18;
        if (c.clir.getCurrentPage() < 1) {i = i + 1;}
        openLocalMapView(i);
    }
    @Override
    public void uElement() {
        int i = 19;
        if (c.clir.getCurrentPage() < 1) {i = i + 1;}
        openLocalMapView(i);
    }

    @Override
    public void vElement() {
        int i = 20;
        if (c.clir.getCurrentPage() < 1) {i = i + 1;}
        openLocalMapView(i);
    }

    @Override
    public void wElement() {
        int i = 21;
        if (c.clir.getCurrentPage() < 1) {i = i + 1;}
        openLocalMapView(i);
    }

    @Override
    public void xElement() {
        int i = 22;
        if (c.clir.getCurrentPage() < 1) {i = i + 1;}
        openLocalMapView(i);
    }

    @Override
    public void yElement() {
        int i = 23;
        if (c.clir.getCurrentPage() < 1) {i = i + 1;}
        openLocalMapView(i);
    }

    @Override
    public void zElement() {
        int i = 24;
        if (c.clir.getCurrentPage() < 1) {i = i + 1;}
        openLocalMapView(i);
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
