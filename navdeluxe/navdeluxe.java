// FrontEnd Plus GUI for JAD
// DeCompiled : navdeluxe.class

import java.applet.Applet;
import java.applet.AppletContext;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

public class navdeluxe extends Applet
    implements Runnable
{
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

    public class MenuPanel extends Panel
    {

        int level;

        public void paint(Graphics g)
        {
            g.setColor(nmenucolor.darker());
            g.draw3DRect(0, 0, super.size().width - 1, super.size().height - 1, true);
        }

        MenuPanel(int i)
        {
            (this$0 = navdeluxe.this).getClass();
            level = i;
            super.hide();
            super.setBackground(nmenucolor);
        }
    }


    private int size;
    private int spacing;
    private int fontstyle;
    private Image bgimage;
    private Font ft;
    FontMetrics fm;
    String target;
    private Color bgcolor;
    Color hbarcolor;
    Color nbarcolor;
    Color barhfontcolor;
    Color barnfontcolor;
    Color hmenucolor;
    Color nmenucolor;
    Color hfontcolor;
    Color nfontcolor;
    private int height;
    boolean start;
    boolean center;
    boolean top;
    private Vector mt;
    Vector mp;
    private Vector mlb;

    public void stop()
    {
    }

    public void paint(Graphics g)
    {
        if(bgimage != null)
            if(center)
                g.drawImage(bgimage, (size().width - bgimage.getWidth(null)) / 2, (size().height - bgimage.getHeight(null)) / 2, this);
            else
                g.drawImage(bgimage, 0, 0, this);
        g.setColor(nbarcolor);
        if(top)
            g.fillRect(0, 0, size().width, height);
        else
            g.fillRect(0, size().height - height, size().width, height);
        if(start)
        {
            showItems(true);
            start = false;
        }
    }

    public navdeluxe()
    {
        size = 10;
        spacing = 75;
        fontstyle = 1;
        bgimage = null;
        ft = new Font("TimesRoman", fontstyle, size);
        fm = getFontMetrics(ft);
        target = "_blank";
        bgcolor = new Color(0xffffff);
        hbarcolor = new Color(0);
        nbarcolor = new Color(0);
        barhfontcolor = new Color(0xffff00);
        barnfontcolor = new Color(0xffffff);
        hmenucolor = new Color(0);
        nmenucolor = new Color(0);
        hfontcolor = new Color(0xffff00);
        nfontcolor = new Color(0xffffff);
        height = 10;
        start = true;
        center = true;
        top = true;
        mt = new Vector();
        mp = new Vector();
        mlb = new Vector();
    }

    public boolean imageUpdate(Image image, int i, int j, int k, int l, int i1)
    {
        if((i & 0x20) != 0)
        {
            repaint();
            return false;
        } else
        {
            return true;
        }
    }

    void showItems(boolean flag)
    {
        for(int i = 0; i < mlb.size(); i++)
        {
            MenuLabel menulabel = (MenuLabel)mlb.elementAt(i);
            if(flag)
                menulabel.show();
            else
                menulabel.hide();
        }

        for(int j = 0; j < mt.size(); j++)
        {
            MenuTitle menutitle = (MenuTitle)mt.elementAt(j);
            if(flag)
                menutitle.show();
            else
                menutitle.hide();
        }

    }

    void showMenu(boolean flag)
    {
        for(int i = 0; i < mp.size(); i++)
        {
            MenuPanel menupanel = (MenuPanel)mp.elementAt(i);
            if(flag)
                menupanel.show();
            else
                menupanel.hide();
        }

    }

    public void start()
    {
        showItems(false);
        showMenu(false);
        start = true;
    }

    public MenuPanel createMenus(String s, int i, int j, int k)
    {
        MenuPanel menupanel = new MenuPanel(k);
        int l = 0;
        for(int i1 = 1; getParameter(s + i1) != null; i1++)
        {
            String s1 = "      " + getParameter(s + i1);
            if(fm.stringWidth(s1) > l)
                l = fm.stringWidth(s1);
        }

        int j1 = 1;
        try
        {
            while(getParameter(s + j1) != null) 
            {
                MenuLabel menulabel;
                if(getParameter(s + j1 + "url") != null)
                {
                    menulabel = new MenuLabel(s + j1, new URL(getDocumentBase(), getParameter(s + j1 + "url")));
                } else
                {
                    MenuPanel menupanel1 = createMenus(s + j1 + "i", i + l + 2, j + height * (j1 - 1), k + 1);
                    menulabel = new MenuLabel(s + j1, menupanel1);
                }
                menulabel.reshape(2, height * (j1 - 1) + 2, l, height);
                menupanel.add(menulabel);
                mlb.addElement(menulabel);
                j1++;
            }
        }
        catch(MalformedURLException _ex) { }
        menupanel.reshape(i, j, l + 4, height * (j1 - 1) + 4);
        mp.addElement(menupanel);
        add(menupanel);
        return menupanel;
    }

    public void run()
    {
    }

    public void init()
    {
        String s = getParameter("size");
        if(s != null)
            size = Integer.parseInt(s);
        s = getParameter("fontstyle");
        if(s != null)
            fontstyle = Integer.parseInt(s);
        if(fontstyle > 3)
            fontstyle = 3;
        s = getParameter("font");
        if(s != null)
            ft = new Font(s, fontstyle, size);
        s = getParameter("spacing");
        if(s != null)
            spacing = Integer.parseInt(s);
        s = getParameter("target");
        if(s != null)
            target = s;
        s = getParameter("bgcolor");
        if(s != null)
            bgcolor = new Color(Integer.parseInt(s, 16));
        s = getParameter("hbarcolor");
        if(s != null)
            hbarcolor = new Color(Integer.parseInt(s, 16));
        s = getParameter("nbarcolor");
        if(s != null)
            nbarcolor = new Color(Integer.parseInt(s, 16));
        s = getParameter("barhfontcolor");
        if(s != null)
            barhfontcolor = new Color(Integer.parseInt(s, 16));
        s = getParameter("barnfontcolor");
        if(s != null)
            barnfontcolor = new Color(Integer.parseInt(s, 16));
        s = getParameter("hmenucolor");
        if(s != null)
            hmenucolor = new Color(Integer.parseInt(s, 16));
        s = getParameter("nmenucolor");
        if(s != null)
            nmenucolor = new Color(Integer.parseInt(s, 16));
        s = getParameter("hfontcolor");
        if(s != null)
            hfontcolor = new Color(Integer.parseInt(s, 16));
        s = getParameter("nfontcolor");
        if(s != null)
            nfontcolor = new Color(Integer.parseInt(s, 16));
        s = getParameter("bgimage");
        if(s != null)
        {
            bgimage = getImage(getDocumentBase(), s);
            prepareImage(bgimage, this);
        }
        s = getParameter("center");
        if(s != null && s.equals("no"))
            center = false;
        s = getParameter("barposition");
        if(s != null && s.equals("bottom"))
            top = false;
        setBackground(bgcolor);
        setFont(ft);
        fm = getFontMetrics(ft);
        height = fm.getAscent() + fm.getDescent() + fm.getLeading();
        int i = 1;
        int j = 0;
        for(; getParameter("Menu" + i) != null; i++)
        {
            int l = mp.size();
            try
            {
                if(getParameter("menu" + i + "position") != null)
                    j = Integer.parseInt(getParameter("menu" + i + "position"));
                MenuTitle menutitle1;
                if(getParameter("menu" + i + "url") != null)
                {
                    menutitle1 = new MenuTitle(getParameter("menu" + i), new URL(getDocumentBase(), getParameter("menu" + i + "url")), i);
                } else
                {
                    MenuPanel menupanel1 = createMenus("menu" + i + "i", j, height, 0);
                    menutitle1 = new MenuTitle(getParameter("menu" + i), menupanel1);
                }
                if(top)
                    menutitle1.reshape(j, 0, fm.stringWidth("    " + getParameter("menu" + i)), height);
                else
                    menutitle1.reshape(j, size().height - height, fm.stringWidth("    " + getParameter("menu" + i)), height);
                mt.addElement(menutitle1);
                add(menutitle1);
                j += fm.stringWidth(getParameter("menu" + i)) + spacing;
            }
            catch(MalformedURLException _ex) { }
            if(!top)
            {
                MenuPanel menupanel = (MenuPanel)mp.elementAt(mp.size() - 1);
                int k1 = size().height - 2 * height - height * menupanel.countComponents() - 4;
                for(int i2 = l; i2 < mp.size(); i2++)
                {
                    MenuPanel menupanel3 = (MenuPanel)mp.elementAt(i2);
                    menupanel3.move(menupanel3.location().x, menupanel3.location().y + k1);
                }

            }
        }

        int i1 = 0;
        int j1 = 0;
        for(int l1 = mp.size() - 1; l1 >= 0; l1--)
        {
            MenuPanel menupanel2 = (MenuPanel)mp.elementAt(l1);
            if(menupanel2.level < j1)
                i1 = 0;
            j1 = menupanel2.level;
            if(menupanel2.level != 0)
            {
                menupanel2.move(menupanel2.location().x, menupanel2.location().y - i1);
                int j2;
                if(top)
                {
                    j2 = ((menupanel2.location().y + height * menupanel2.countComponents()) - size().height) + 4;
                    if(menupanel2.location().y + height * menupanel2.countComponents() <= height + 1 && j2 > 0)
                        j2 = menupanel2.location().y - height - 2;
                } else
                {
                    j2 = ((menupanel2.location().y + height * menupanel2.countComponents()) - size().height) + height + 6;
                }
                if(j2 > 0)
                {
                    menupanel2.move(menupanel2.location().x, menupanel2.location().y - j2);
                    i1 += j2;
                }
            } else
            {
                i1 = 0;
            }
        }

    }

    public boolean mouseMove(Event event, int i, int j)
    {
        if(event.target instanceof navdeluxe)
            showMenu(false);
        return true;
    }
}
