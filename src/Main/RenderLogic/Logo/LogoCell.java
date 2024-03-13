package Main.RenderLogic.Logo;

import Main.MathHelper;

public class LogoCell
{
    LogoScreen ls;
    char sourceBlock;
    private int fillLevel = 0;
    int[] xy;

    public LogoCell(LogoScreen ls, char c, int x, int y)
    {
        this.ls = ls;
        sourceBlock = c;
        xy = new int[]{x, y};
    }

    public void changeFillLevel(int i)
    {
        fillLevel = MathHelper.clamp(fillLevel+i, 0, 4);
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
        if (sourceBlock != '\u0000')
        {
            int direction = MathHelper.randomRangeInt(1,4);
            LogoCell target;
            if (MathHelper.randomDecider(0.99f)) {return;}

            switch (direction)
            {
                case 1:
                    target = ls.screen
                            [MathHelper.clamp(xy[0], 0, ls.screen.length-1)]
                            [MathHelper.clamp(xy[1]-1, 0, ls.screen[0].length-1)];
                    target.changeFillLevel(+1);
                    break;
                case 2:
                    target = ls.screen
                            [MathHelper.clamp(xy[0]+1, 0, ls.screen.length-1)]
                            [MathHelper.clamp(xy[1], 0, ls.screen[0].length-1)];
                    target.changeFillLevel(+1);
                    break;
                case 3:
                    target = ls.screen
                            [MathHelper.clamp(xy[0], 0, ls.screen.length-1)]
                            [MathHelper.clamp(xy[1]+1, 0, ls.screen[0].length-1)];
                    target.changeFillLevel(+1);
                    break;
                case 4:
                    target = ls.screen
                            [MathHelper.clamp(xy[0]-1, 0, ls.screen.length-1)]
                            [MathHelper.clamp(xy[1]-1, 0, ls.screen[0].length-1)];
                    target.changeFillLevel(+1);
                    break;
            }
        }
        else
        {

        }
    }
}
