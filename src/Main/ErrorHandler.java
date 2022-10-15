package Main;

import java.awt.*;

public class ErrorHandler
{
    static public void PrintAndTraceError(Exception e)
    {
        StackTraceElement[] elements = e.getStackTrace();
        String caller = elements[0].getClassName();
        String errorMessage = caller + " -> " + e.getMessage();
        System.out.println(errorMessage);
    }

    static public void LogData(boolean log, String s)
    {
        LogData(log,s,1);
    }
    static public void LogData(boolean log, String s, int depth)
    {
        if(log)
        {
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            for (int i = 0; i < depth; i++)
            {
                String caller = elements[i+1].getClassName();
                s = caller + " -> " + s;
            }
            System.out.println(s);
        }
    }

    public static void LogData(String s) {
    }
}
