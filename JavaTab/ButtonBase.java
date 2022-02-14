// FrontEnd Plus GUI for JAD
// DeCompiled : ButtonBase.class

import java.awt.*;
import java.io.PrintStream;
import Timer;
import Beans;

public abstract class ButtonBase extends Canvas
{

    protected boolean pressed;
    protected boolean released;
    protected boolean inButton;
    protected boolean notifyWhilePressed;
    protected boolean running;
    protected boolean notified;
    protected boolean showFocus;
    protected int bevel;
    protected int notifyDelay;
    protected int pressedAdjustment;
    protected Timer notifyTimer;
    protected Image imgBG;

    protected ButtonBase()
    {
        pressed = false;
        released = true;
        notifyWhilePressed = false;
        running = false;
        notified = false;
        notifyTimer = null;
        notifyDelay = 1000;
        bevel = 1;
        pressedAdjustment = 0;
        resize(10, 10);
    }

    public void setBevelHeight(int i)
    {
        try
        {
            checkBevelSize(i);
        }
        catch(AWTException _ex)
        {
            System.err.println("Invalid Bevel Size " + i);
        }
        bevel = i;
        invalidate();
    }

    public int getBevelHeight()
    {
        return bevel;
    }

    public void setNotifyWhilePressed(boolean flag)
    {
        notifyWhilePressed = flag;
        if(notifyWhilePressed)
        {
            notifyTimer = new Timer(this, notifyDelay, true, 1001);
            return;
        }
        if(notifyTimer != null)
            notifyTimer = null;
    }

    public boolean getNotifyWhilePressed()
    {
        return notifyWhilePressed;
    }

    public void setNotifyDelay(int i)
    {
        notifyDelay = i;
    }

    public int getNotifyDelay()
    {
        return notifyDelay;
    }

    public void setShowFocus(boolean flag)
    {
        showFocus = flag;
    }

    public boolean getShowFocus()
    {
        return showFocus;
    }

    public void setBGImage(Image image)
    {
        imgBG = image;
        invalidate();
    }

    public boolean mouseUp(Event event, int i, int j)
    {
        if(running)
        {
            running = false;
            notifyTimer.stop();
        }
        if(pressed)
        {
            pressed = false;
            pressedAdjustment = 0;
            if(!notifyWhilePressed || !notified)
                postEvent(new Event(this, 1001, null));
        }
        released = true;
        repaint();
        return true;
    }

    public boolean mouseDown(Event event, int i, int j)
    {
        if(notifyWhilePressed && !running)
        {
            running = true;
            notifyTimer.start();
        }
        pressed = true;
        released = false;
        pressedAdjustment = bevel;
        repaint();
        return true;
    }

    public boolean mouseEnter(Event event, int i, int j)
    {
        inButton = true;
        if(!released)
            mouseDown(event, i, j);
        return true;
    }

    public boolean mouseExit(Event event, int i, int j)
    {
        inButton = false;
        if(pressed)
        {
            pressed = false;
            pressedAdjustment = 0;
        }
        return true;
    }

    public boolean action(Event event, Object obj)
    {
        if(notifyWhilePressed && event.target == notifyTimer && !Beans.isDesignTime())
        {
            postEvent(new Event(this, 1001, null));
            return true;
        } else
        {
            return super.action(event, obj);
        }
    }

    public void enable()
    {
        if(!isEnabled())
        {
            super.enable();
            pressed = false;
            pressedAdjustment = 0;
        }
        repaint();
    }

    public void disable()
    {
        if(isEnabled())
        {
            super.disable();
            if(notifyTimer != null)
                notifyTimer.stop();
            pressed = false;
            pressedAdjustment = 0;
        }
        repaint();
    }

    public void update(Graphics g)
    {
        paint(g);
    }

    public void paint(Graphics g)
    {
        Dimension dimension = size();
        int i = dimension.width;
        int j = dimension.height;
        int k = bevel + 1;
        int j1 = i - 1;
        int k1 = j - 1;
        if(pressed)
        {
            k += bevel <= 0 ? 1 : 2;
            g.setColor(Color.lightGray);
            for(int l1 = 1; l1 < bevel + 1; l1++)
            {
                g.drawLine(l1, k1 - l1, j1 - l1, k1 - l1);
                g.drawLine(j1 - l1, k1 - l1, j1 - l1, l1);
            }

            g.setColor(Color.gray);
            for(int i2 = 1; i2 < bevel + 1; i2++)
            {
                g.drawLine(i2, k1, i2, i2);
                g.drawLine(i2, i2, j1, i2);
            }

            return;
        }
        g.setColor(Color.white);
        for(int j2 = 1; j2 < bevel + 1; j2++)
        {
            g.drawLine(j2, k1 - j2, j2, j2);
            g.drawLine(j2, j2, j1 - j2, j2);
        }

        g.setColor(Color.gray);
        for(int k2 = 1; k2 < bevel + 2; k2++)
        {
            g.drawLine(k2, k1 - k2, j1 - k2, k1 - k2);
            g.drawLine(j1 - k2, k1 - k2, j1 - k2, k2);
        }

    }

    private void checkBevelSize(int i)
        throws AWTException
    {
        Dimension dimension = size();
        if(i < 0 || i >= dimension.width / 2 || i >= dimension.height / 2)
            throw new AWTException("invalid bevel size");
        else
            return;
    }
}
