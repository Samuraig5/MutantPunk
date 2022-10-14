package Main;

public class ErrorHandler
{
    static public void PrintAndTraceError(Exception e)
    {
        StackTraceElement[] elements = e.getStackTrace();
        String caller = elements[0].getClassName();
        String errorMessage = caller + " -> " + e.getMessage();
        System.out.println(errorMessage);
    }
}
