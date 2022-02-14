// FrontEnd Plus GUI for JAD
// DeCompiled : TabPanel.class

import java.awt.*;
import java.util.Vector;
import BaseTabbedPanel;

public class TabPanel extends BaseTabbedPanel
{
    Vector vPanels;
    String labels[];
    boolean bOsHack;
    Label label1;

    public TabPanel()
    {
        this(0, 0);
    }

    public TabPanel(boolean bTabsOnTop)
    {
        this(bTabsOnTop ? 0 : 1, bTabsOnTop ? 0 : 1);
    }

    public TabPanel(int tabsPostion, int tabsStyle)
    {
        super(tabsPostion, tabsStyle);
        vPanels = new Vector();
        String sOS = System.getProperty("os.name");
        if(sOS.equals("Windows 95"))
        {
            bOsHack = true;
            return;
        } else
        {
            bOsHack = false;
            return;
        }
    }

    public int addTabPanel(String sLabel, boolean bEnabled, Component panel)
    {
        vPanels.addElement(panel);
        return addTab(sLabel, bEnabled);
    }

    public int getCurrentPanelNdx()
    {
        return curIndex;
    }

    public void setCurrentPanelNdx(int index)
    {
        showTabPanel(index);
    }

    public Component add(Component comp)
    {
        return add(comp, -1);
    }

    private String createDefaultLabel(int i)
    {
        String name = "tab - ";
        name += String.valueOf(i);
        return name;
    }

    public synchronized Component add(Component comp, int pos)
    {
        showTabPanel(addTabPanel(createDefaultLabel(vPanels.size()), true, comp));
        updatePanelLabels();
        return comp;
    }

    public synchronized Component add(String name, Component comp)
    {
        return comp;
    }

    public void setPanelLabels(String sLabels[])
    {
        labels = sLabels;
        updatePanelLabels();
    }

    public String[] getPanelLabels()
    {
        return labels;
    }

    public void updatePanelLabels()
    {
        try
        {
            for(int i = 0; i < vPanels.size(); i++)
            {
                String newlabel;
                if(labels != null)
                    try
                    {
                        newlabel = labels[i];
                    }
                    catch(ArrayIndexOutOfBoundsException _ex)
                    {
                        newlabel = createDefaultLabel(i);
                    }
                else
                    newlabel = createDefaultLabel(i);
                setLabel(newlabel, i);
            }

            return;
        }
        catch(Throwable _ex)
        {
            return;
        }
    }

    public void setTabsOnBottom(boolean bTabsOnBottom)
    {
        setTabsInfo(bTabsOnBottom ? 1 : 0, bTabsOnBottom ? 1 : 0);
        layout();
    }

    public boolean getTabsOnBottom()
    {
        return getTabsPosition() != 0;
    }

    public synchronized void setTabPanel(String sLabel, boolean bEnabled, Component panel, int index)
    {
        if(index < 0 || index >= vPanels.size())
            return;
        if(index == currentTabIndex() && !bEnabled)
            return;
        try
        {
            vPanels.setElementAt(panel, index);
            setTab(sLabel, bEnabled, index);
            return;
        }
        catch(ArrayIndexOutOfBoundsException _ex)
        {
            return;
        }
    }

    public synchronized Component getTabPanel(int index)
    {
        if(index < 0 || index >= vPanels.size())
            return null;
        Component p = null;
        try
        {
            p = (Component)vPanels.elementAt(index);
        }
        catch(ArrayIndexOutOfBoundsException _ex) { }
        return p;
    }

    public synchronized int getPanelTabIndex(Component panel)
    {
        return vPanels.indexOf(panel);
    }

    public synchronized void showTabPanel(int index)
    {
        if(tabIsEnabled(index))
            try
            {
                Component p = (Component)vPanels.elementAt(index);
                showTab(index);
                showPanel(p);
                return;
            }
            catch(ArrayIndexOutOfBoundsException _ex)
            {
                return;
            }
        else
            return;
    }

    public synchronized void enableTabPanel(boolean bEnable, int index)
    {
        if(index < 0 || index >= vPanels.size() || index == curIndex)
        {
            return;
        } else
        {
            enableTab(bEnable, index);
            return;
        }
    }

    public synchronized void removeTabPanel(int index)
    {
        if(index < 0 || index >= vPanels.size() || index == curIndex)
            return;
        try
        {
            Component p = (Component)vPanels.elementAt(index);
            super.remove(p);
            vPanels.removeElementAt(index);
        }
        catch(ArrayIndexOutOfBoundsException _ex) { }
        removeTab(index);
    }

    public synchronized void removeAllTabPanels()
    {
        vPanels = new Vector();
        curIndex = -1;
        removeAllTabs();
    }

    public int countTabs()
    {
        return vPanels.size();
    }

    public boolean handleEvent(Event evt)
    {
        switch(evt.id)
        {
        default:
            break;

        case 1001: // Event.ACTION_EVENT
            if((evt.target instanceof TabPanel) && evt.target == this)
                showTabPanel(currentTabIndex());
            break;
        }
        return super.handleEvent(evt);
    }

    public Dimension preferredSize()
    {
        Component pan = null;
        Dimension d = null;
        Dimension p = size();
        int s = vPanels.size();
        Insets insets = insets();
        p.width -= insets.left + insets.right;
        p.height -= insets.top + insets.bottom;
        if(p.width < 0)
            p.width = 0;
        if(p.height < 0)
            p.height = 0;
        for(int x = 0; x < s; x++)
        {
            pan = (Component)vPanels.elementAt(x);
            if(pan != null)
            {
                d = pan.minimumSize();
                if(d.width > p.width)
                    p.width = d.width;
                if(d.height > p.height)
                    p.height = d.height;
                d = pan.preferredSize();
                if(d.width > p.width)
                    p.width = d.width;
                if(d.height > p.height)
                    p.height = d.height;
            }
        }

        p.width += insets.left + insets.right;
        p.height += insets.top + insets.bottom;
        return p;
    }

    public Dimension minimumSize()
    {
        Component pan = null;
        Dimension d = null;
        Dimension m = new Dimension(0, 0);
        int s = vPanels.size();
        for(int x = 0; x < s; x++)
        {
            pan = (Component)vPanels.elementAt(x);
            if(pan != null)
            {
                d = pan.minimumSize();
                if(d.width > m.width)
                    m.width = d.width;
                if(d.height > m.height)
                    m.height = d.height;
            }
        }

        Insets insets = insets();
        m.width += insets.left + insets.right;
        m.height += insets.top + insets.bottom;
        return m;
    }
}
