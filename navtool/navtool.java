// FrontEnd Plus GUI for JAD
// DeCompiled : navtool.class

import java.applet.Applet;
import java.applet.AppletContext;
import java.awt.*;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

public class navtool extends Applet
    implements Runnable
{
    public class MenuTitle extends Canvas
    {

        int id;
        String text;
        boolean highlight;
        Color f;

        public boolean mouseEnter(Event event, int i, int j)
        {
            highlight = true;
            cur_Y = 0;
            cur_X = 0;
            cmenu = id;
            f = hfontcolor;
            repaint();
            getParent().repaint();
            return true;
        }

        public boolean mouseExit(Event event, int i, int j)
        {
            highlight = false;
            f = nfontcolor;
            repaint();
            return true;
        }

        public void paint(Graphics g)
        {
            g.setColor(bgcolor);
            g.fillRect(0, 0, super.size().width, super.size().height);
            if(bgimage != null)
            {
                boolean flag = false;
                boolean flag1 = false;
                boolean flag2 = false;
                int k1 = (getParent().size().width - bgimage.getWidth(this)) / 2;
                int l1 = (getParent().size().width + bgimage.getWidth(this)) / 2;
                int i2 = (getParent().size().height - bgimage.getHeight(this)) / 2;
                if(isEven(id))
                {
                    if(xorigin + titlewidth >= k1)
                    {
                        int i = (xorigin + titlewidth) - k1;
                        if(yorigin + (theight * (id + 2)) / 2 >= i2)
                        {
                            int k = Math.min((yorigin + (theight * (id + 2)) / 2) - i2, super.size().height);
                            int i1 = Math.max(0, (yorigin + (theight * id) / 2) - i2);
                            g.drawImage(bgimage, super.size().width - i, super.size().height - k, super.size().width, super.size().height, 0, i1, (xorigin + titlewidth) - k1, i1 + k, bgcolor, this);
                        }
                    }
                } else
                if(getParent().size().width - xorigin - titlewidth <= l1)
                {
                    int j = l1 - (getParent().size().width - xorigin - titlewidth);
                    if(yorigin + (theight * (id + 1)) / 2 >= i2)
                    {
                        int l = Math.min((yorigin + (theight * (id + 1)) / 2) - i2, super.size().height);
                        int j1 = Math.max(0, (yorigin + (theight * (id - 1)) / 2) - i2);
                        g.drawImage(bgimage, 0, super.size().height - l, j, super.size().height, bgimage.getWidth(this) - j, j1, bgimage.getWidth(this), j1 + l, bgcolor, this);
                    }
                }
            }
            if(highlight)
            {
                g.setColor(hcolor);
                g.fillRect(0, 0, super.size().width, super.size().height);
            }
            g.setColor(f);
            if(isEven(id))
                g.drawString(text, titlewidth - metrics.stringWidth(text), (size().height / 2 + metrics.getAscent() / 2) - menumetrics.getLeading() - metrics.getDescent() / 2);
            else
                g.drawString(text, 0, (size().height / 2 + metrics.getAscent() / 2) - metrics.getLeading() - metrics.getDescent() / 2);
        }

        MenuTitle(String s, int i)
        {
            (this$0 = navtool.this).getClass();
            id = i;
            super.setFont(tfont);
            f = nfontcolor;
            text = "  " + s + "  ";
        }
    }


    private boolean select;
    private boolean scrolling;
    private int size;
    private int fontstyle;
    int xorigin;
    int yorigin;
    int titlewidth;
    private int msize;
    private int mfontstyle;
    Image bgimage;
    Font tfont;
    FontMetrics metrics;
    private Font mfont;
    FontMetrics menumetrics;
    private String target;
    Color bgcolor;
    Color hcolor;
    Color hfontcolor;
    Color nfontcolor;
    private Color hmfontcolor;
    private Color nmfontcolor;
    private boolean check;
    int cmenu;
    private Vector menu;
    Image dbImage;
    Graphics dbGraphics;
    Thread scroller;
    int theight;
    private int sbheight;
    int cur_Y;
    int cur_X;
    private int scroll;
    private int cdisplay;

    public boolean mouseEnter(Event event, int i, int j)
    {
        if(!(locate(event.x, event.y) instanceof MenuTitle))
        {
            cur_X = event.x;
            cur_Y = event.y;
            repaint();
        }
        return true;
    }

    public void stop()
    {
        if(scroller != null)
        {
            scroller.stop();
            scroller = null;
        }
    }

    boolean isEven(int i)
    {
        boolean flag = false;
        int j = i & 0x1;
        if(j == 0)
            flag = true;
        return flag;
    }

    public void paint(Graphics g)
    {
        if(check)
        {
            g.setClip(0, 0, size().width, size().height);
            g.setColor(bgcolor);
            g.fillRect(0, 0, size().width, size().height);
            if(bgimage != null)
                g.drawImage(bgimage, (size().width - bgimage.getWidth(this)) / 2, (size().height - bgimage.getHeight(this)) / 2, bgcolor, this);
            if(cmenu >= 0)
                displayItems(g);
        } else
        {
            g.setColor(Color.black);
            g.drawString("Please do not remove the credits!", 15, 15);
        }
    }

    public navtool()
    {
        select = true;
        scrolling = true;
        size = 12;
        fontstyle = 0;
        xorigin = 0;
        yorigin = 0;
        titlewidth = 0;
        msize = 10;
        mfontstyle = 0;
        bgimage = null;
        tfont = new Font("TimesRoman", fontstyle, size);
        metrics = getFontMetrics(tfont);
        mfont = new Font("TimesRoman", mfontstyle, msize);
        menumetrics = getFontMetrics(mfont);
        target = "_blank";
        bgcolor = new Color(0xffffff);
        hcolor = new Color(0);
        hfontcolor = new Color(0xffff00);
        nfontcolor = new Color(0xffffff);
        hmfontcolor = new Color(0xffff00);
        nmfontcolor = new Color(0xffffff);
        check = false;
        cmenu = -1;
        menu = new Vector();
        theight = 0;
        sbheight = 0;
        cur_Y = 0;
        cur_X = 0;
        scroll = 0;
        cdisplay = -1;
    }

    private void displayItems(Graphics g)
    {
        g.setColor(Color.black);
        int i = 0;
        int j = getMenuWidth();
        if(isEven(cmenu))
        {
            int k = xorigin + titlewidth + 3;
            int i1 = yorigin + (theight * cmenu) / 2 + 3;
            int ai[] = {
                k, k, (k + theight) - 8
            };
            int ai2[] = {
                i1, (i1 + theight) - 6, (i1 + theight / 2) - 3
            };
            g.fillPolygon(ai, ai2, 3);
            g.clipRect(xorigin + titlewidth + 15, 0, size().width - 2 * (xorigin + titlewidth + 15), size().width);
            if(getParameter("menu" + cmenu) == null)
            {
//                j = menumetrics.stringWidth("About the applet");
//                if(!scrolling)
//                    scroll = j + 20;
//                if(cur_X > xorigin + titlewidth + 20 && cur_X < xorigin + titlewidth + 20 + j)
//                {
//                    if(cur_Y > yorigin + sbheight * i && cur_Y < yorigin + sbheight * (i + 1))
//                        g.setColor(hmfontcolor);
//                    else
//                        g.setColor(nmfontcolor);
//                } else
//                {
//                    g.setColor(nmfontcolor);
//                }
//                g.drawString("About the applet", ((xorigin + titlewidth) - j) + scroll, yorigin + sbheight * (i + 1));
            } else
            {
                if(!scrolling)
                    scroll = j + 20;
                for(; getParameter("menu" + cmenu + "item" + i) != null; i++)
                {
                    if(cur_X > xorigin + titlewidth + 20 && cur_X < xorigin + titlewidth + 20 + j)
                    {
                        if(cur_Y > yorigin + sbheight * i && cur_Y < yorigin + sbheight * (i + 1))
                            g.setColor(hmfontcolor);
                        else
                            g.setColor(nmfontcolor);
                    } else
                    {
                        g.setColor(nmfontcolor);
                    }
                    g.drawString(getParameter("menu" + cmenu + "item" + i), ((xorigin + titlewidth) - j) + scroll, yorigin + sbheight * (i + 1));
                }

            }
        } else
        {
            int l = size().width - (xorigin + titlewidth + 3);
            int j1 = yorigin + (theight * (cmenu - 1)) / 2 + 3;
            int ai1[] = {
                l, l, (l - theight) + 8
            };
            int ai3[] = {
                j1, (j1 + theight) - 6, (j1 + theight / 2) - 3
            };
            g.fillPolygon(ai1, ai3, 3);
            g.setClip(xorigin + titlewidth + 15, 0, size().width - 2 * (xorigin + titlewidth + 15), size().width);
            if(getParameter("menu" + cmenu) == null)
            {
                if(!scrolling)
                    scroll = j + 20;
//                j = menumetrics.stringWidth("About the applet");
//                if(cur_X > size().width - (xorigin + titlewidth + 20) - j && cur_X < size().width - (xorigin + titlewidth + 20))
//                {
//                    if(cur_Y > yorigin + sbheight * i && cur_Y < yorigin + sbheight * (i + 1))
//                        g.setColor(hmfontcolor);
//                    else
//                        g.setColor(nmfontcolor);
//                } else
//                {
//                    g.setColor(nmfontcolor);
//               }
//                g.drawString("About the applet", size().width - (xorigin + titlewidth + scroll), yorigin + sbheight * (i + 1));
            } else
            {
                if(!scrolling)
                    scroll = j + 20;
                for(; getParameter("menu" + cmenu + "item" + i) != null; i++)
                {
                    String s = getParameter("menu" + cmenu + "item" + i);
                    if(cur_X > size().width - (xorigin + titlewidth + 20) - j && cur_X < size().width - (xorigin + titlewidth + 20))
                    {
                        if(cur_Y > yorigin + sbheight * i && cur_Y < yorigin + sbheight * (i + 1))
                            g.setColor(hmfontcolor);
                        else
                            g.setColor(nmfontcolor);
                    } else
                    {
                        g.setColor(nmfontcolor);
                    }
                    g.drawString(s, size().width - (((xorigin + titlewidth) - j) + menumetrics.stringWidth(s) + scroll), yorigin + sbheight * (i + 1));
                }

            }
        }
    }

    public void update(Graphics g)
    {
        paint(dbGraphics);
        g.drawImage(dbImage, 0, 0, this);
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

    public void start()
    {
        if(scroller == null)
        {
            scroller = new Thread(this);
            scroller.start();
        }
    }

    public boolean mouseDown(Event event, int i, int j)
    {
        if(!(locate(event.x, event.y) instanceof MenuTitle))
        {
            int k = 0;
            int j1 = getMenuWidth();
//            if(getParameter("menu" + cmenu) == null)
//                j1 = menumetrics.stringWidth("About the applet");
            int l;
            int i1;
            if(isEven(cmenu))
            {
                l = xorigin + titlewidth + 15;
                i1 = xorigin + titlewidth + j1 + 15;
            } else
            {
                l = size().width - (xorigin + titlewidth + 15) - j1;
                i1 = size().width - (xorigin + titlewidth + 15);
            }
            if(getParameter("menu" + cmenu) == null)
            {
//                if(event.x > l && event.x < i1 && event.y > yorigin + sbheight * k && cur_Y < yorigin + sbheight * (k + 1))
//                    try
//                    {
//                        getAppletContext().showDocument(new URL("http://www.navsurf.com"), "_blank");
//                    }
//                    catch(MalformedURLException _ex) { }
            } else
            {
                for(; getParameter("menu" + cmenu + "item" + k) != null; k++)
                    if(event.x > l && event.x < i1 && event.y > yorigin + sbheight * k && cur_Y < yorigin + sbheight * (k + 1) && getParameter("menu" + cmenu + "url" + k) != null)
                        try
                        {
                            URL url = new URL(getDocumentBase(), getParameter("menu" + cmenu + "url" + k));
                            if(getParameter("menu" + cmenu + "target" + k) != null)
                                getAppletContext().showDocument(url, getParameter("menu" + cmenu + "target" + k));
                            else
                            if(getParameter("menu" + cmenu + "target") != null)
                                getAppletContext().showDocument(url, getParameter("menu" + cmenu + "target"));
                            else
                                getAppletContext().showDocument(url, target);
                            System.out.println(url);
                        }
                        catch(MalformedURLException _ex) { }

            }
        }
        return true;
    }

    public void run()
    {
        do
        {
            try
            {
                Thread.currentThread();
                Thread.sleep(5L);
            }
            catch(InterruptedException _ex) { }
            int i;
//            if(getParameter("menu" + cmenu) == null)
//                i = menumetrics.stringWidth("About the applet");
//            else
                i = getMenuWidth();
            if(scrolling)
            {
                if(scroll < i + 20)
                {
                    scroll++;
                    repaint();
                }
                if(cdisplay != cmenu)
                {
                    scroll = 0;
                    repaint();
                }
            }
            cdisplay = cmenu;
        } while(true);
    }

    public void init()
    {
        String s = getParameter("size");
        if(s != null)
            size = Integer.parseInt(s);
        s = getParameter("msize");
        if(s != null)
            msize = Integer.parseInt(s);
        s = getParameter("fontstyle");
        if(s != null)
            fontstyle = Integer.parseInt(s);
        if(fontstyle > 3)
            fontstyle = 3;
        s = getParameter("mfontstyle");
        if(s != null)
            mfontstyle = Integer.parseInt(s);
        if(mfontstyle > 3)
            mfontstyle = 3;
        s = getParameter("font");
        if(s != null)
        {
            tfont = new Font(s, fontstyle, size);
            mfont = new Font(s, mfontstyle, msize);
        }
        s = getParameter("target");
        if(s != null)
            target = s;
        s = getParameter("xorigin");
        if(s != null)
            xorigin = Integer.parseInt(s);
        s = getParameter("yorigin");
        if(s != null)
            yorigin = Integer.parseInt(s);
        s = getParameter("titlewidth");
        if(s != null)
            titlewidth = Integer.parseInt(s);
        s = getParameter("bgcolor");
        if(s != null)
            bgcolor = new Color(Integer.parseInt(s, 16));
        s = getParameter("hcolor");
        if(s != null)
            hcolor = new Color(Integer.parseInt(s, 16));
        s = getParameter("hfontcolor");
        if(s != null)
            hfontcolor = new Color(Integer.parseInt(s, 16));
        s = getParameter("nfontcolor");
        if(s != null)
            nfontcolor = new Color(Integer.parseInt(s, 16));
        s = getParameter("hmfontcolor");
        if(s != null)
            hmfontcolor = new Color(Integer.parseInt(s, 16));
        s = getParameter("nmfontcolor");
        if(s != null)
            nmfontcolor = new Color(Integer.parseInt(s, 16));
        s = getParameter("bgimage");
        if(s != null)
        {
            bgimage = getImage(getDocumentBase(), s);
            prepareImage(bgimage, this);
        }
        s = getParameter("scrolling");
        if(s != null && s.equals("no"))
            scrolling = false;
        if(getParameter("NavTool") != null)
        {
            String s1 = getParameter("NavTool");
            if(s1.equals("Free for non-commercial use") && getParameter("http://www.navsurf.com") != null)
                check = true;
        }
        if(getParameter("properties") != null)
        {
            System.out.println("Version : 2.4");
            System.out.println("Author : Toh Lik Khoong");
            System.out.println("Copyright : NavSurf.com");
        }
        setFont(mfont);
        setLayout(null);
        metrics = getFontMetrics(tfont);
        menumetrics = getFontMetrics(mfont);
        sbheight = menumetrics.getAscent();
        theight = metrics.getAscent() + metrics.getDescent() + metrics.getLeading();
        dbImage = createImage(size().width, size().height);
        dbGraphics = dbImage.getGraphics();
        int i;
        for(i = 0; getParameter("menu" + i) != null; i++)
        {
            MenuTitle menutitle = new MenuTitle(getParameter("menu" + i), i);
            if(isEven(i))
                menutitle.reshape(xorigin, yorigin + (theight * i) / 2, titlewidth, theight);
            else
                menutitle.reshape(size().width - (titlewidth + xorigin), yorigin + (theight * (i - 1)) / 2, titlewidth, theight);
            menu.addElement(menutitle);
            add(menutitle);
        }

//        MenuTitle menutitle1 = new MenuTitle("About the applet", i);
//        if(isEven(i))
//            menutitle1.reshape(xorigin, yorigin + (theight * i) / 2, titlewidth, theight);
//        else
//            menutitle1.reshape(size().width - (titlewidth + xorigin), yorigin + (theight * (i - 1)) / 2, titlewidth, theight);
//        menu.addElement(menutitle1);
//        add(menutitle1);
    }

    public int getMenuWidth()
    {
        int i = 0;
        for(int j = 0; getParameter("menu" + cmenu + "item" + j) != null; j++)
        {
            String s = getParameter("menu" + cmenu + "item" + j);
            if(menumetrics.stringWidth(s) > i)
                i = menumetrics.stringWidth(s);
        }

        return i;
    }

    public boolean mouseMove(Event event, int i, int j)
    {
        if(!(locate(event.x, event.y) instanceof MenuTitle))
        {
            cur_X = event.x;
            cur_Y = event.y;
            if(event.x < xorigin + titlewidth || event.x > size().width - (xorigin + titlewidth))
                cmenu = -1;
            repaint();
        }
        return true;
    }
}
