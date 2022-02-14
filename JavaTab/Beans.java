// FrontEnd Plus GUI for JAD
// DeCompiled : Beans.class


public class Beans
{

    private static boolean designTime;
    private static boolean guiAvailable;

    public static boolean isDesignTime()
    {
        return designTime;
    }

    public static boolean isGuiAvailable()
    {
        return guiAvailable;
    }

    public static void setDesignTime(boolean isDesignTime)
    {
        designTime = isDesignTime;
    }

    public static void setGuiAvailable(boolean isGuiAvailable)
    {
        guiAvailable = isGuiAvailable;
    }

    public Beans()
    {
    }
}
