// FrontEnd Plus GUI for JAD
// DeCompiled : navtool$MenuTitle.class

import java.awt.*;

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
