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
        if(log)
        {
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String caller = elements[1].getClassName();
            System.out.println(caller + " -> " + s);
        }
    }
}
