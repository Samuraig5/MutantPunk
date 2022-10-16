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
                String caller = elements[i+2].getClassName();
                s = caller + " -> " + s;
            }
            System.out.println(s);
        }
    }

    static public void PrintArray(float[] a, String name)
    {
        LogData(true,"Printing Array: " + name);
        for (int i = 0; i < a.length; i++) {
            System.out.println("["+i+"] - " + a[i]);
        }
    }
    static public void PrintArray(boolean print, String[] a, String name)
    {
        if(print)
        {
            LogData(true,"Printing Array: " + name,1);
            for (int i = 0; i < a.length; i++)
            {
                System.out.println("["+i+"] - " + a[i]);
            }
        }
    }
}
