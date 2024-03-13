package Main.RenderLogic.Logo;

public class LogoCell
{
    char sourceBlock;
    int fillLevel = 0;

    public LogoCell(char c)
    {
        sourceBlock = c;
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
        return ' ';
    }
}
