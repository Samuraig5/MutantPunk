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
        blocks.add(generateBlock("0: " + input.get(0), 20));

        for (String[] s: blocks)
        {
            c.cc.println(s[0]);
            c.cc.println(s[1]);
            c.cc.println(s[2]);
        }

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
        String line = getHorizontalLines(length, '+', '-');
        String m = pruneMessage(message, length-2);

        s[0] = line;
        s[1] = "+ " + rightpad(m,length-1) + "+";
        s[2] = line;

        return s;
    }
    private String getHorizontalLines(int length, Character edge, Character filler)
    {
        String s = "" + edge;
        for (int i = 0; i < length; i++)
        {
            s = s + filler;
        }
        s = s + edge;
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
