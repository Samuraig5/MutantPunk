package Main.RenderLogic;
import java.util.ArrayList;
import java.util.List;

public class ConsoleTopMenuRenderer
{
    Console c;
    public ConsoleTopMenuRenderer(Console console)
    {
        c = console;
    }

    public void renderTopMenuList(List<String> input)
    {
        List<String[]> blocks = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            blocks.add(generateBlock(i + ": " + input.get(i), 20));
        }

        String top = "+";
        String middle = "|";
        String bottom = "+";

        for (String[] s: blocks)
        {
            top = top + s[0] + "+";
            middle = middle + s[1] + "|";
            bottom = bottom + s[2] + "+";
        }
        c.cc.println(top);
        c.cc.println(middle);
        c.cc.println(bottom);
        c.cc.println("");
    }

    private String rightpad(String message, int length)
    {
        if (message.length() >= length) {
            return message;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(message);
        while (sb.length() < length)
        {
            sb.append(' ');
        }

        return sb.toString();
    }

    private String[] generateBlock(String message, int length)
    {
        String[] s = new String[3];
        String line = getHorizontalLines(length, '-');
        String m = pruneMessage(message, length-2);

        s[0] = line;
        s[1] = " " + rightpad(m,length-2) + " ";
        s[2] = line;

        return s;
    }
    private String getHorizontalLines(int length, Character filler)
    {
        String s = "";
        for (int i = 0; i < length; i++)
        {
            s = s + filler;
        }
        return s;
    }
    private String pruneMessage(String message, int maxLength)
    {
        String s = message;
        if(s.length()>maxLength)
        {
            char[] c = s.toCharArray();
            s = "";
            for (int i = 0; i < maxLength-1; i++) {
                s = s + c[i];
            }
            s = s + ".";
        }
        return s;
    }
}
