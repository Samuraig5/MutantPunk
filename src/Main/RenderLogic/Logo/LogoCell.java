package Main.RenderLogic.Logo;

import Main.MathHelper;

public class LogoCell
{
    LogoScreen ls;
    LogoCell UPPER;
    LogoCell LOWER;
    LogoCell LEFT;
    LogoCell RIGHT;

    static final int MAX_FILL_LEVEL = 4;

    char sourceBlock;
    private int fillLevel = 0;
    int[] xy;

    public LogoCell(LogoScreen ls, char c, int x, int y)
    {
        this.ls = ls;
        sourceBlock = c;
        xy = new int[]{x, y};
    }

    public void findNeighbours()
    {
        UPPER = ls.screen
                [MathHelper.clamp(xy[0], 0, ls.screen.length-1)]
                [MathHelper.clamp(xy[1]-1, 0, ls.screen[0].length-1)];
        LOWER = ls.screen
                [MathHelper.clamp(xy[0], 0, ls.screen.length-1)]
                [MathHelper.clamp(xy[1]+1, 0, ls.screen[0].length-1)];
        LEFT = ls.screen
                [MathHelper.clamp(xy[0]-1, 0, ls.screen.length-1)]
                [MathHelper.clamp(xy[1], 0, ls.screen[0].length-1)];
        RIGHT = ls.screen
                [MathHelper.clamp(xy[0]+1, 0, ls.screen.length-1)]
                [MathHelper.clamp(xy[1], 0, ls.screen[0].length-1)];
    }

    public int changeFillLevel(int i)
    {
        int overFlow = i - (MAX_FILL_LEVEL-fillLevel);
        fillLevel = MathHelper.clamp(fillLevel+i, 0, MAX_FILL_LEVEL);
        return overFlow;
    }

    public int getFillLevel()
    {
        return fillLevel;
    }

    public boolean isSource()
    {
        return sourceBlock != '\u0000';
    }

    public boolean isEmpty()
    {
        if (fillLevel == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean isFull()
    {
        if (fillLevel == MAX_FILL_LEVEL)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public char getSymbol()
    {
        if (sourceBlock != '\u0000') {return sourceBlock;}
        switch (fillLevel)
        {
            case 0:
                return ' ';
            case 1:
                return '░';
            case 2:
                return '▒';
            case 3:
                return '▓';
            case 4:
                return '█';
        }
        return '?';
    }

    public void update()
    {

        if (sourceBlock != '\u0000') //SOURCE BLOCK
        {
            if (MathHelper.randomDecider(0.1f))
            {
                int direction = MathHelper.randomRangeInt(2,4); //2 to 4 because 1 is spawning up which we don't want
                LogoCell target;

                switch (direction)
                {
                    case 1:
                        UPPER.changeFillLevel(+1);
                        break;
                    case 2:
                        RIGHT.changeFillLevel(+1);
                        break;
                    case 3:
                        LOWER.changeFillLevel(+1);
                        break;
                    case 4:
                        LEFT.changeFillLevel(+1);
                        break;
                }
            }
        }

        if (isEmpty()) {return;}
        if (!UPPER.isEmpty() || UPPER.isSource())
        {
            if (fillLevel == 1 && MathHelper.randomDecider(0.9f)) {return;}
            if (fillLevel == 2 && MathHelper.randomDecider(0.8f)) {return;}
            if (fillLevel == 3 && MathHelper.randomDecider(0.7f)) {return;}
            if (fillLevel == 4 && MathHelper.randomDecider(0.6f)) {return;}
        }
        if (!LEFT.isEmpty() || LEFT.isSource() || !RIGHT.isEmpty() || RIGHT.isSource())
        {
            if (fillLevel == 1 && MathHelper.randomDecider(0.3f)) {return;}
            if (fillLevel == 2 && MathHelper.randomDecider(0.2f)) {return;}
            if (fillLevel == 3 && MathHelper.randomDecider(0.1f)) {return;}
        }

        LogoCell target;
        if (LOWER.isFull() || LOWER == this)
        {
            if (MathHelper.randomDecider(0.5f))
            {
                target = LEFT;
            }
            else
            {
                target = RIGHT;
            }
        }
        else
        {
            target = LOWER;
        }

        int flowOver = 1;
        int overflow = target.changeFillLevel(flowOver);
        changeFillLevel(-(flowOver-overflow));
    }
}
