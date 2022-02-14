// FrontEnd Plus GUI for JAD
// DeCompiled : navdeluxe$MenuLabel.class

import java.applet.Applet;
import java.applet.AppletContext;
import java.awt.*;
import java.net.URL;
import java.util.Vector;

public class MenuLabel extends Canvas
{

    Color f;
    String text;
    URL u;
    MenuPanel m;

    public boolean mouseEnter(Event event, int i, int j)
    {
        setBackground(hmenucolor);
        f = hfontcolor;
        MenuPanel menupanel = (MenuPanel)getParent();
        for(int k = 0; k < mp.size(); k++)
        {
            MenuPanel menupanel1 = (MenuPanel)mp.elementAt(k);
            if(menupanel.level < menupanel1.level)
                menupanel1.hide();
        }

        if(m != null)
            m.show();
        repaint();
        return true;
    }

    public boolean mouseExit(Event event, int i, int j)
    {
        setBackground(nmenucolor);
        f = nfontcolor;
        repaint();
        return true;
    }

    public void paint(Graphics g)
    {
        g.setColor(f);
        if(m != null)
        {
            int i = size().width - 5;
            int ai[] = {
                i, i, i + 4
            };
            int ai1[] = {
                4, size().height - 4, size().height / 2
            };
            g.fillPolygon(ai, ai1, 3);
        }
        g.drawString("  " + getParameter(text) + "  ", 0, fm.getAscent());
    }

    public boolean mouseDown(Event event, int i, int j)
    {
        if(m == null)
        {
            if(getParameter(text + "target") != null)
            {
                getAppletContext().showDocument(u, getParameter(text + "target"));
            } else
            {
                String s = text.substring(0, text.length() - 2);
                if(getParameter(s + "target") != null)
                    getAppletContext().showDocument(u, getParameter(s + "target"));
                else
                    getAppletContext().showDocument(u, target);
            }
            showMenu(false);
        } else
        if(m.isVisible())
            m.hide();
        else
            m.show();
        return true;
    }

    MenuLabel(String s, URL url)
    {
        (this$0 = navdeluxe.this).getClass();
        super.hide();
        super.setBackground(nmenucolor);
        f = nfontcolor;
        text = s;
        u = url;
        m = null;
    }

    MenuLabel(String s, MenuPanel menupanel)
    {
        (this$0 = navdeluxe.this).getClass();
        super.hide();
        super.setBackground(nmenucolor);
        f = nfontcolor;
        text = s;
        u = null;
        m = menupanel;
    }
}
