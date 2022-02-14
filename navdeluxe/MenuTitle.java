// FrontEnd Plus GUI for JAD
// DeCompiled : navdeluxe$MenuTitle.class

import java.applet.Applet;
import java.applet.AppletContext;
import java.awt.*;
import java.net.URL;

public class MenuTitle extends Canvas
{

    boolean raised;
    boolean current;
    Color f;
    String text;
    URL u;
    MenuPanel m;
    int i;

    public boolean mouseEnter(Event event, int j, int k)
    {
        current = true;
        raised = true;
        f = barhfontcolor;
        setBackground(hbarcolor);
        if(m == null)
        {
            showMenu(false);
        } else
        {
            String s = "no";
            if(getParameter("hover") != null)
                s = getParameter("hover");
            if(s.equals("yes"))
            {
                raised = false;
                showMenu(false);
                m.show();
            }
        }
        repaint();
        return true;
    }

    public boolean mouseExit(Event event, int j, int k)
    {
        current = false;
        setBackground(nbarcolor);
        f = barnfontcolor;
        repaint();
        return true;
    }

    public void paint(Graphics g)
    {
        if(current)
        {
            Color color = nbarcolor.brighter();
            if(getParameter("barbutton") != null)
                color = new Color(Integer.parseInt(getParameter("barbutton"), 16));
            g.setColor(color);
            g.draw3DRect(0, 0, super.size().width - 1, super.size().height - 1, raised);
        }
        g.setColor(f);
        g.drawString(text, 0, fm.getAscent());
    }

    public boolean mouseDown(Event event, int j, int k)
    {
        current = true;
        if(m == null)
        {
            raised = false;
            setBackground(hbarcolor);
            f = barhfontcolor;
            if(getParameter("menu" + i + "target") != null)
                getAppletContext().showDocument(u, getParameter("menu" + i + "target"));
            else
                getAppletContext().showDocument(u, target);
        } else
        {
            current = true;
            if(m.isVisible())
            {
                setBackground(nbarcolor);
                m.hide();
                raised = true;
                f = barnfontcolor;
            } else
            {
                setBackground(hbarcolor);
                m.show();
                raised = false;
                f = barhfontcolor;
            }
        }
        repaint();
        return true;
    }

    MenuTitle(String s, URL url, int j)
    {
        (this$0 = navdeluxe.this).getClass();
        current = false;
        super.hide();
        super.setBackground(nbarcolor);
        f = barnfontcolor;
        text = "  " + s + "  ";
        u = url;
        m = null;
        i = j;
    }

    MenuTitle(String s, MenuPanel menupanel)
    {
        (this$0 = navdeluxe.this).getClass();
        current = false;
        super.hide();
        super.setBackground(nbarcolor);
        f = barnfontcolor;
        text = "  " + s + "  ";
        m = menupanel;
        u = null;
        i = 0;
    }
}
