package Main.RenderLogic.UI_Painter;

import Main.AbilityLogic.Ability;
import Main.MathHelper;
import Main.ObjectLogic.BodyLogic.BodyPart;
import Main.ObjectLogic.BodyLogic.BodyPartStat;
import Main.ObjectLogic.BodyLogic.Person;
import Main.ObjectLogic.ObjectTag;
import Main.ObjectLogic.Thing;
import Main.RenderLogic.ConsolePainter;
import Main.RenderLogic.Logic.ColouredString;
import Main.Settings;

import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.Objects;

public class UIPainter
{
    private static int printString(Graphics g, int xPos, int yPos, Color c, String s)
    {
        g.setFont(new Font("Courier New", Font.PLAIN, Settings.menuFontSize));
        Color current = g.getColor();
        g.setColor(c);
        g.drawString(s, xPos, yPos);
        g.setColor(current);
        return xPos + Math.round(s.length()*Settings.menuFontWidth);
    }

    private static void printString(Graphics g, int xPos, int yPos, Color c, String s, int fontSize)
    {
        Color currentColour = g.getColor();
        g.setColor(c);

        Font currentFont = g.getFont();
        g.setFont(new Font("Courier New", Font.PLAIN, fontSize));
        g.drawString(s, xPos, yPos);
        g.setFont(currentFont);

        g.setColor(currentColour);
    }

    private static void printCentredString(Graphics g, int yPos, Color c, String s)
    {
        float stringWidth = ((s.length()-1)*Settings.menuFontWidth);
        int xPos = Math.round((Settings.windowWidth-stringWidth)/2);

        printString(g, xPos,yPos,c,s);
    }

    private static int printColouredStringHorizontal(Graphics g, int xPos, int yPos, ColouredString[] content)
    {
        int x = xPos;
        int y = yPos;
        for (int i = 0; i < content.length; i++)
        {
            printString(g, x,y,content[i].getColor(), content[i].getString());
            x += Math.round(Settings.menuFontSize*content[i].getString().length());
        }
        return x;
    }

    public static void drawMainMenu(ConsolePainter cp)
    {
        Graphics g = cp.g;

        char[][] mat = cp.c.ls.getLogoScreen();

        Font currentFont = g.getFont();
        Color current = g.getColor();
        g.setColor(Color.green);
        g.setFont(new Font("Courier New", Font.PLAIN, Settings.menuFontSize+1));
        for (int x = 0; x < mat.length; x++) {
            for (int y = 0; y < mat[x].length; y++) {
                cp.g.drawString(mat[x][y]+"", Math.round(x*Settings.menuFontWidth), Math.round(y*Settings.menuFontHeight));
            }
        }
        g.setFont(currentFont);


        //TODO: Add some easter eggs in light gray so they are only visible when the slime fills up

        printString(g, Math.round(Settings.menuFontWidth*4) ,Math.round(Settings.menuFontHeight*24), cp.backgroundColour, "Thank Memk!");

        String s1 = "Strange creatures in a strange land";
        String s2 = "a: Generate a new World";

        int width = Math.round(s1.length() * Settings.menuFontWidth);
        int height = Math.round(3.5f * Settings.menuFontHeight);
        int centered = Math.round((Settings.windowWidth-width) / 2f) - Math.round(Settings.menuFontWidth*1.5f);

        g.setColor(cp.backgroundColour);
        g.fillRoundRect(centered,
                Math.round(Settings.menuFontHeight*(24)),
                width, height, 5, 5);
        g.setColor(Color.LIGHT_GRAY);
        g.drawRoundRect(centered,
                Math.round(Settings.menuFontHeight*(24)),
                width, height, 5, 5);

        g.setColor(current);

        printCentredString(g, Math.round(Settings.menuFontHeight*(25)), Color.green, s1);
        printCentredString(g, Math.round(Settings.menuFontHeight*(27)), Color.lightGray, s2);
    }

    public static void drawLocalMapMenu(ConsolePainter cp)
    {
        Graphics g = cp.g;
        printCentredString(g,10, Color.lightGray, cp.c.wc.getActiveWorld().getActiveLocalMap().getMapName());

        printString(g, 10, 70, Color.LIGHT_GRAY, "a: Open map view");
        printString(g, 10, Math.round(70+(1*Settings.menuFontHeight)), Color.LIGHT_GRAY, "b: List all local characters");
        List<File> bodyPlans = cp.c.getSortedBodyPlans();

        for (int i = 0; i < bodyPlans.size(); i++)
        {
            String name = MathHelper.indexToLetter(i+2) + ": " + bodyPlans.get(i).getName();
            printString(g,10, Math.round(70+((i+2)*Settings.menuFontHeight)), Color.LIGHT_GRAY, name);
        }
    }

    public static void drawListOfLocalPeople(ConsolePainter cp)
    {
        List<Person> people = cp.c.wc.getActiveWorld().getActiveLocalMap().getLocalPeople();

        for (int i = 0; i < people.size(); i++)
        {
            printString(cp.g,10, 80+Math.round((i)*Settings.menuFontHeight), Color.LIGHT_GRAY,
                    MathHelper.indexToLetter(i) + ": " + people.get(i).getName());
        }
    }

    public static void drawThingInspector(ConsolePainter cp)
    {
        Graphics g = cp.g;
        Thing inspectedThing = cp.getInspectedThing();

        if (inspectedThing == null) {throw new RuntimeException("The ConsolePainter is trying to draw a body but no person is focused");}

        int nameFontSize = Settings.menuFontSize*2;
        int nameOffset = 10 + Math.round(Settings.menuFontHeight);
        String title = inspectedThing.getName() + " (" + inspectedThing.getMapIcon().getSymbol() + ")";
        printString(g, 10, nameOffset, inspectedThing.getMapIcon().getIconColour(), title , nameFontSize);

        if (inspectedThing.getDescription() != null)
        {
            printString(g, 10, nameOffset+20, Color.lightGray, inspectedThing.getDescription());
        }

        int offset = 10;
        for (int i = 0; i < inspectedThing.getTags().length; i++)
        {
            String tag = "[" + inspectedThing.getTags()[i].name() + "] ";
            printString(g, offset, nameOffset+35, Color.lightGray, tag);

            int stringLength = Math.round(Settings.menuFontWidth*tag.length());
            offset += stringLength;
        }

        if (inspectedThing instanceof Person)
        {
            Person p = (Person) inspectedThing;
            if (p.isPlayer())
            {
                int xTitleOffset = Math.round(title.length()*nameFontSize*Settings.relativeMenuFontWidth);
                printString(g, 10+xTitleOffset, nameOffset, inspectedThing.getMapIcon().getIconColour(), "- PLAYER -" , nameFontSize);
            }

            int paintX = 10;
            int paintY = 50+nameOffset;
            int cellWidth = 10;

            String[][] stats = cp.c.cb.openPersonView((Person) inspectedThing);

            float bloodLevel = 0;
            float bloodCapacity = 0;

            for (BodyPart bp:((Person) inspectedThing).myBodyParts)
            {
                bloodLevel += bp.getBloodLevel();
                bloodCapacity += bp.getStats()[BodyPartStat.BLOOD_CAPACITY];
            }

            //printString(g, 10,paintY, Color.LIGHT_GRAY, "Blood Levels: (" +
            //        bloodLevel + " / " + bloodCapacity + ")");
            UIHelper.drawBar(g,10,paintY,50*Settings.menuFontWidth,Settings.menuFontHeight,
                    bloodLevel, 0, bloodCapacity, Color.red, Color.lightGray);

            paintY += 2*Math.round(Settings.menuFontHeight)*3;

            for (int i = 0; i < stats.length; i++) {
                printString(g, paintX,paintY, Color.LIGHT_GRAY, stats[i][0]);
                for (int j = 1; j < stats[i].length; j++) {
                    int x = paintX + Math.round(Settings.menuFontWidth*j*cellWidth)+Math.round(Settings.menuFontWidth*cellWidth);
                    printString(g, x,paintY, Color.LIGHT_GRAY, stats[i][j]);
                }
                paintY += Math.round(Settings.menuFontHeight);
            }

            ColouredString[][] body = cp.c.cb.getBodyView((Person)inspectedThing);
            for (int i = 0; i < body.length; i++)
            {
                int yLevel = paintY+nameOffset+Math.round((i)*Settings.menuFontHeight);

                int xLevel = printColouredStringHorizontal(g,10, yLevel, body[i]);

                BodyPart bp = ((Person) inspectedThing).myBodyParts.get(i);

                UIHelper.drawBarWithMarkers(g, xLevel, yLevel-(Settings.menuFontHeight/2),
                        20*Settings.menuFontWidth, Settings.menuFontHeight/2f, bp.getBloodLevel(), 0,
                        bp.getStats()[BodyPartStat.BLOOD_CAPACITY],
                        Color.red, Color.lightGray, null,
                        new float[]{bp.getStats()[BodyPartStat.BLOOD_NEED]}, new Color[]{Color.black});
            }
        }
    }

    public static void drawBodyPartMenu(ConsolePainter cp)
    {
        Graphics g = cp.g;
        BodyPart inspectedBodyPart = cp.getInspectedBodyPart();

        int nameFontSize = Settings.menuFontSize*2;
        int y = 10 + Math.round(Settings.menuFontHeight);
        printString(g,10,y,Color.LIGHT_GRAY, inspectedBodyPart.getName(),nameFontSize);
        y += Settings.menuFontHeight;


        ColouredString[] health = new ColouredString[4];
        health[0] = new ColouredString("Health", Color.LIGHT_GRAY);
        float fCurr = inspectedBodyPart.getCurrentHealth();
        float fMax = inspectedBodyPart.getStats()[BodyPartStat.MAX_HEALTH];
        float fNeed = inspectedBodyPart.getStats()[BodyPartStat.REGEN_LIMIT];
        health[1] = new ColouredString(fCurr+"", Color.lightGray);
        health[2] = new ColouredString(" / ", Color.lightGray);
        health[3] = new ColouredString(fMax+"", Color.lightGray);

        if (fCurr > (fMax/2))
        {
            health[1].setColor(Color.green);
        }
        else if (fCurr > (fMax/4))
        {
            health[1].setColor(Color.yellow);
        }
        else
        {
            health[1].setColor(Color.red);
        }

        UIHelper.drawBarWithMarkers(g,10,y,50*Settings.menuFontWidth, Settings.menuFontHeight,
                fCurr, 0, fMax, Color.GREEN, Color.lightGray, null,
                new float[]{fNeed}, new Color[]{Color.black});
        //printColouredStringHorizontal(g,10, y, health);
        y += 1.5f*Math.round(Settings.menuFontHeight);

        //printString(g, 10,y, Color.LIGHT_GRAY, "Blood Levels: " +
        //        inspectedBodyPart.getBloodLevel() + " / " +
        //        inspectedBodyPart.getStats()[BodyPartStat.BLOOD_CAPACITY]);

        UIHelper.drawBarWithMarkers(g,10,y,50*Settings.menuFontWidth, Settings.menuFontHeight,
                inspectedBodyPart.getBloodLevel(), 0, inspectedBodyPart.getStats()[BodyPartStat.BLOOD_CAPACITY],
                Color.RED, Color.lightGray, null,
                new float[]{inspectedBodyPart.getStats()[BodyPartStat.BLOOD_NEED]}, new Color[]{Color.black});

        y += 2f*Math.round(Settings.menuFontHeight);

        printString(g,10,y,Color.LIGHT_GRAY,"Attachment Capacity: " +
                inspectedBodyPart.getRemainingAttachmentCapacity() + " / " +
                inspectedBodyPart.getStats()[BodyPartStat.ATTACHMENT_CAPACITY]);
        y += Math.round(Settings.menuFontHeight)*2;

        y = drawBodyPartStats(cp, g, inspectedBodyPart, y);
        drawBodyPartAbilities(g, inspectedBodyPart, y);
    }

    private static int drawBodyPartStats(ConsolePainter cp, Graphics g, BodyPart inspectedBodyPart, int startPos)
    {
        int paintX = 10;
        int paintY = startPos;
        int cellWidth = 10;

        String[][] stats = cp.c.cb.displayBodyPartStats(inspectedBodyPart);
        String[][] statDesc = {{"","Final", "Gross", "Modifier", "Upstream", "Upstream", "Person"}, {"","", "", "", "Gross", "Modifier", "Modifier"}};
        //Add descriptors
        for (int j = 0; j < stats[0].length; j++)
        {
            int x = paintX + Math.round(Settings.menuFontWidth*j*cellWidth)+Math.round(Settings.menuFontWidth*cellWidth);
            printString(g,x,paintY, Color.LIGHT_GRAY, statDesc[0][j]);
            printString(g,x,paintY+Math.round(Settings.menuFontHeight), Color.LIGHT_GRAY, statDesc[1][j]);
        }
        paintY += Math.round(Settings.menuFontHeight*2.5f);

        //Add Stat Lines
        for (int i = 0; i < stats.length; i++) {
            //Don't display stat if it adds nothing
            if (Objects.equals(stats[i][1], "0.0") &&
                    Objects.equals(stats[i][2], "0.0") &&
                    Objects.equals(stats[i][3], "1.0") &&
                    Objects.equals(stats[i][4], "0.0") &&
                    Objects.equals(stats[i][5], "1.0") &&
                    Objects.equals(stats[i][6], "1.0"))
            {
                continue;
            }
            printString(g,paintX,paintY, Color.LIGHT_GRAY, stats[i][0]);
            for (int j = 1; j < stats[i].length; j++) {
                int x = paintX + Math.round(Settings.menuFontWidth*j*cellWidth)+Math.round(Settings.menuFontWidth*cellWidth);
                printString(g,x,paintY, Color.LIGHT_GRAY, stats[i][j]);
            }
            paintY += Math.round(Settings.menuFontHeight);
        }

        return paintY + Math.round(Settings.menuFontHeight*stats[0].length);
    }

    private static void drawBodyPartAbilities(Graphics g, BodyPart inspectedBodyPart, int startPos)
    {
        for (int i = 0; i < inspectedBodyPart.getAbilities().size(); i++)
        {
            Ability ability = inspectedBodyPart.getAbilities().get(i);
            ObjectTag[] obt = ability.getRelatedObjectTags();

            int xOffset = 10;

            xOffset = printString(g, xOffset, startPos, Color.LIGHT_GRAY, "["+ability.getAbilityName()+"]");

            xOffset = printString(g, xOffset, startPos, Color.LIGHT_GRAY, "("+ability.getAbilityTag()+"):");


            xOffset = printString(g, xOffset, startPos, Color.LIGHT_GRAY, "<");
            for (int j = 0; j < obt.length; j++)
            {
                xOffset = printString(g, xOffset, startPos, Color.LIGHT_GRAY, "["+obt[j]+"]");
            }
            xOffset = printString(g, xOffset, startPos, Color.LIGHT_GRAY, ">, ");

            if (ability.getCapacity() != 0)
            {
                xOffset = printString(g, xOffset, startPos, Color.LIGHT_GRAY, "Capacity:");
                xOffset = printString(g, xOffset, startPos, Color.LIGHT_GRAY, "(");
                xOffset = printString(g, xOffset, startPos, Color.LIGHT_GRAY, String.valueOf(ability.getCurrentFillLevel()));
                xOffset = printString(g, xOffset, startPos, Color.LIGHT_GRAY, "/");
                xOffset = printString(g, xOffset, startPos, Color.LIGHT_GRAY, String.valueOf(ability.getCapacity()));
                xOffset = printString(g, xOffset, startPos, Color.LIGHT_GRAY, ")");
                if (ability.getEfficiency() != 0)
                {
                    xOffset = printString(g, xOffset, startPos, Color.LIGHT_GRAY, ", ");
                }
            }
            if (ability.getEfficiency() != 0)
            {
                xOffset = printString(g, xOffset, startPos, Color.LIGHT_GRAY, "Efficiency:");
                xOffset = printString(g, xOffset, startPos, Color.LIGHT_GRAY, String.valueOf(ability.getEfficiency()));
            }
        }
    }

    public static void drawCursorUI(ConsolePainter cp)
    {
        Graphics g = cp.g;
        Thing focusedThing = cp.getFocusedThing();
        int[] cursorPosition = cp.getCursorPosition();

        if (focusedThing != null && focusedThing.getMyCell() != null)
        {
            cp.setCursorPosition(focusedThing.getMyCell().getCoordinates());
        }

        if (cp.isCursorEnabled())
        {
            int width = 250;
            int heigth =  Settings.windowHeight-50;
            int startPointX = Settings.windowWidth-250;
            int startPointY = 5;

            Color current = g.getColor();
            g.setColor(cp.backgroundColour);
            g.fillRoundRect(startPointX-20, startPointY,
                    width, heigth, 5, 5);
            g.setColor(Color.LIGHT_GRAY);
            g.drawRoundRect(startPointX-20, startPointY,
                    width, heigth, 5, 5);
            g.setColor(current);

            List<Thing> things = cp.c.wc.getActiveWorld().getActiveLocalMap().getCell(cursorPosition[0], cursorPosition[1]).getThings();
            cp.setListSelector(MathHelper.clamp(cp.getListSelector(), 0, things.size()-1));

            int i;
            if (cp.getFocusedThing() != null)
            {
                i = things.size() - things.indexOf(cp.getFocusedThing()) - 1;;
            }
            else
            {
                i = things.size() - cp.getListSelector() - 1;
            }
            printString(g, startPointX-10, 20+Math.round((i+1)*Settings.menuFontHeight),
                    Color.yellow, ">");

            for (int j = 0; j < things.size(); j++)
            {
                Thing thing = things.get(things.size()-(j+1));
                printString(g, startPointX, 20+Math.round((j+1)*Settings.menuFontHeight),
                        thing.getMapIcon().getIconColour(), thing.getName());
            }
        }
    }

    public static void drawPausedButton(ConsolePainter cp)
    {
        Graphics g = cp.getGraphics();

        if (!cp.c.wc.isClockRunning())
        {
            int width;
            int height;
            int startPointX;
            int startPointY;
            if (cp.isCursorEnabled())
            {
                width = 100;
                height =  Math.round(Settings.menuFontHeight*2);
                startPointX = Settings.windowWidth-270-width-2;
                startPointY = 5;
            }
            else
            {
                width = 100;
                height =  Math.round(Settings.menuFontHeight*2);
                startPointX = Settings.windowWidth-width-20;
                startPointY = 5;
            }

            Color current = g.getColor();
            g.setColor(cp.backgroundColour);
            g.fillRoundRect(startPointX,startPointY, width, height, 5,5);
            g.setColor(Color.LIGHT_GRAY);
            g.drawRoundRect(startPointX,startPointY, width, height, 5,5);
            g.setColor(current);

            String s = "PAUSED";
            int stringLength = Math.round(Settings.menuFontWidth*s.length());
            int stringHeight = Math.round(Settings.menuFontHeight);
            printString(g, startPointX+(width/2)-(stringLength/2),
                    startPointY+(height/2)+(stringHeight/3),
                    cp.c.errorColour,s);
        }
    }
}
