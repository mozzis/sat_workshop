// FrontEnd Plus GUI for JAD
// DeCompiled : BaseTabbedPanel.class

import java.awt.*;
import java.util.Vector;
import DirectionButton;

public abstract class BaseTabbedPanel extends Panel
{

    public static final int TOP = 0;
    public static final int BOTTOM = 1;
    public static final int ROUNDED = 0;
    public static final int SQUARE = 1;
    private int TF_LEFT;
    private int TF_RIGHT;
    private int TF_TOP;
    private int TF_BOTTOM;
    private int TF_BTN_HEIGHT;
    private Vector vLabels;
    private Vector vEnabled;
    private Vector vPolys;
    protected int curIndex;
    private Font fReg;
    private Font fSel;
    private Font fSReg;
    private Font fSSel;
    private Font fFont;
    private Image imgBG;
    private Component userPanel;
    private int iTabsPosition;
    private int iTabsStyle;
    private int osAdjustment;
    private int firstVisibleTab;
    private DirectionButton dbLeft;
    private DirectionButton dbRight;
    private Polygon nullPoly;
    private int lastWidth;
    Color cTempColor;
    Color cLabelColor;
    Color cMOLabelColor;
    Color oldColor;
    private int iMOLabel;
    private int iiMOLabel;
    private boolean bShowTabsOnly;
    int oldx;
    private Insets btpInsets;

    public BaseTabbedPanel()
    {
        this(0, 0);
    }

    public BaseTabbedPanel(boolean bTabsOnTop)
    {
        this(bTabsOnTop ? 0 : 1, bTabsOnTop ? 0 : 1);
    }

    public BaseTabbedPanel(int tabsPostion, int tabsStyle)
    {
        TF_TOP = 23;
        TF_BTN_HEIGHT = 20;
        curIndex = -1;
        lastWidth = -1;
        cTempColor = Color.black;
        cLabelColor = Color.black;
        cMOLabelColor = new Color(255, 0, 0);
        bShowTabsOnly = false;
        vLabels = new Vector();
        vEnabled = new Vector();
        vPolys = new Vector();
        btpInsets = new Insets(0, 0, 0, 0);
        setTabsInfo(tabsPostion, tabsStyle);
        fReg = new Font("Helvetica", 0, 12);
        fSel = new Font("Helvetica", 1, 12);
        if(System.getProperty("os.name").startsWith("S"))
            osAdjustment = -1;
        else
            osAdjustment = 0;
        super.setLayout(null);
        dbLeft = new DirectionButton(0);
        dbRight = new DirectionButton(1);
        if(imgBG != null)
        {
            dbRight.setBGImage(imgBG);
            dbLeft.setBGImage(imgBG);
        }
        dbLeft.setShowFocus(false);
        dbRight.setShowFocus(false);
        dbLeft.shrinkTriangle(1, 1, 0, 1);
        dbRight.shrinkTriangle(1, 1, 0, 1);
        super.add(dbLeft, -1);
        super.add(dbRight, -1);
        nullPoly = new Polygon();
        nullPoly.addPoint(0, 0);
        nullPoly.addPoint(1, 1);
        nullPoly.addPoint(0, 0);
    }

    public void setLabelColor(Color c)
    {
        cLabelColor = c;
        dbRight.setForeColor(cLabelColor);
        dbLeft.setForeColor(cLabelColor);
        invalidate();
    }

    public void setBGImage(Image img)
    {
        imgBG = img;
        invalidate();
    }

    public void showTabsOnly(boolean bShowTabsOnly1)
    {
        bShowTabsOnly = bShowTabsOnly1;
        invalidate();
    }

    public void setMOColor(Color c)
    {
        cMOLabelColor = c;
        iMOLabel = 999;
        invalidate();
    }

    public void setMOLabelColor(Color c, int index)
    {
        if(oldColor == null)
            oldColor = c;
        if(index == 999)
            cMOLabelColor = cLabelColor;
        else
            cMOLabelColor = c;
        iMOLabel = index;
        repaint();
        invalidate();
    }

    public void setFont(Font f)
    {
        if(f != null)
            fFont = f;
        invalidate();
        try
        {
            fReg = new Font(fFont.getName(), 0, fFont.getSize());
            fSel = new Font(fFont.getName(), 1, fFont.getSize());
            return;
        }
        catch(Exception _ex)
        {
            fReg = fSReg;
        }
        fSel = fSSel;
    }

    public void setTabsPosition(int tabsPosition)
    {
        if(iTabsPosition != tabsPosition)
            setTabsInfo(tabsPosition, iTabsStyle);
    }

    public int getTabsPosition()
    {
        return iTabsPosition;
    }

    public Color getMOColor()
    {
        return cMOLabelColor;
    }

    public void setTabsStyle(int tabsStyle)
    {
        if(iTabsStyle != tabsStyle)
            setTabsInfo(iTabsPosition, tabsStyle);
    }

    public int getTabsStyle()
    {
        return iTabsStyle;
    }

    public void setTabsInfo(int tabsPosition, int tabsStyle)
    {
        iTabsPosition = tabsPosition;
        if(iTabsPosition == 0)
            iTabsStyle = 0;
        else
            iTabsStyle = tabsStyle;
        if(iTabsStyle == 0)
        {
            TF_BTN_HEIGHT = 20;
            return;
        } else
        {
            TF_BTN_HEIGHT = 17;
            return;
        }
    }

    public void setPanel(Component p)
    {
        removeAll();
        userPanel = p;
        if(userPanel != null)
        {
            super.add(userPanel, -1);
            userPanel.requestFocus();
        }
        repaint();
    }

    public int setTabLabelColor(Event evt)
    {
        int sizeR = vPolys.size();
        for(int x = 0; x < sizeR; x++)
            try
            {
                Polygon p = (Polygon)vPolys.elementAt(x);
                if(p != nullPoly && p.inside(evt.x, evt.y))
                {
                    if(iMOLabel != x)
                    {
                        if(cMOLabelColor.equals(getForeground()))
                            cMOLabelColor = oldColor;
                        setMOLabelColor(cMOLabelColor, x);
                        iiMOLabel = x;
                        iMOLabel = x;
                        oldx = x;
                    }
                    iiMOLabel = iMOLabel;
                    return iiMOLabel;
                }
            }
            catch(ArrayIndexOutOfBoundsException _ex) { }

        return 999;
    }

    public void showPanel(Component p)
    {
        if(userPanel != null)
            userPanel.hide();
        userPanel = p;
        if(userPanel != null)
        {
            Component comps[] = getComponents();
            int l = comps.length;
            int x;
            for(x = 0; x < l; x++)
                if(comps[x] == userPanel)
                    break;

            if(x == l)
                super.add(userPanel, -1);
            userPanel.show();
            userPanel.requestFocus();
            validate();
            repaint();
        }
    }

    public int addTab(String sLabel, boolean bEnabled)
    {
        vLabels.addElement(sLabel);
        vEnabled.addElement(new Boolean(bEnabled));
        int index = vLabels.size() - 1;
        if(curIndex == -1 && bEnabled)
            showTab(index);
        return index;
    }

    public synchronized void setTab(String sLabel, boolean bEnabled, int index)
    {
        if(index < 0 || index >= vLabels.size())
            return;
        if(index == curIndex && !bEnabled)
            return;
        try
        {
            vLabels.setElementAt(sLabel, index);
            vEnabled.setElementAt(new Boolean(bEnabled), index);
            repaint();
            return;
        }
        catch(ArrayIndexOutOfBoundsException _ex)
        {
            return;
        }
    }

    public synchronized void setLabel(String sLabel, int index)
    {
        if(index < 0 || index >= vLabels.size())
            return;
        try
        {
            vLabels.setElementAt(sLabel, index);
            repaint();
            return;
        }
        catch(ArrayIndexOutOfBoundsException _ex)
        {
            return;
        }
    }

    public synchronized String getLabel(int index)
    {
        if(index < 0 || index >= vLabels.size())
            return "";
        try
        {
            return (String)vLabels.elementAt(index);
        }
        catch(ArrayIndexOutOfBoundsException _ex)
        {
            return "";
        }
    }

    public synchronized void setEnabled(boolean bEnabled, int index)
    {
        if(index < 0 || index >= vLabels.size())
            return;
        if(index == curIndex && !bEnabled)
            return;
        try
        {
            vEnabled.setElementAt(new Boolean(bEnabled), index);
            repaint();
            return;
        }
        catch(ArrayIndexOutOfBoundsException _ex)
        {
            return;
        }
    }

    public void showTab(int index)
    {
        if(index < 0 || index >= vLabels.size() || index == curIndex)
            return;
        if(tabIsEnabled(index))
        {
            curIndex = index;
            invalidate();
            validate();
            repaint();
            postEvent(new Event(this, 1001, null));
        }
    }

    public boolean tabIsEnabled(int index)
    {
        if(index < 0 || index >= vLabels.size())
            return false;
        try
        {
            Boolean bool = (Boolean)vEnabled.elementAt(index);
            if(bool.booleanValue())
                return true;
        }
        catch(ArrayIndexOutOfBoundsException _ex) { }
        return false;
    }

    public int currentTabIndex()
    {
        return curIndex;
    }

    public void enableTab(boolean bEnable, int index)
    {
        if(index < 0 || index >= vEnabled.size() || index == curIndex)
            return;
        try
        {
            vEnabled.setElementAt(new Boolean(bEnable), index);
            repaint();
            return;
        }
        catch(ArrayIndexOutOfBoundsException _ex)
        {
            return;
        }
    }

    public void removeTab(int index)
    {
        if(index < 0 || index >= vEnabled.size() || index == curIndex)
            return;
        try
        {
            vLabels.removeElementAt(index);
            vEnabled.removeElementAt(index);
            repaint();
            return;
        }
        catch(ArrayIndexOutOfBoundsException _ex)
        {
            return;
        }
    }

    public void removeAllTabs()
    {
        vLabels = new Vector();
        vEnabled = new Vector();
        vPolys = new Vector();
        curIndex = -1;
        firstVisibleTab = 0;
        lastWidth = -1;
        removeAll();
        repaint();
    }

    public void layout()
    {
        Rectangle r = bounds();
        int width = (r.width - TF_LEFT) + TF_RIGHT;
        if(width < 0)
            return;
        int height = (r.height - TF_TOP) + TF_BOTTOM;
        if(height < 0)
            return;
        int col = TF_LEFT;
        int row = 0;
        if(iTabsPosition == 0)
            row = TF_TOP;
        else
            row = TF_TOP - TF_BTN_HEIGHT - 5;
        if(userPanel != null)
        {
            userPanel.reshape(col + 3, row + 3, width - 6, height - 5);
            userPanel.invalidate();
            userPanel.validate();
            if((userPanel instanceof Canvas) || (userPanel instanceof Panel))
            {
                userPanel.repaint();
                return;
            }
            repaint();
        }
    }

    public synchronized void paint(Graphics g)
    {
        Rectangle r = bounds();
        int width = (r.width - TF_LEFT) + TF_RIGHT;
        if(width < 0)
            return;
        int height = (r.height - TF_TOP) + TF_BOTTOM;
        if(height < 0)
            return;
        if(r.width > lastWidth)
            firstVisibleTab = 0;
        lastWidth = r.width;
        int col = TF_LEFT;
        Color c = g.getColor();
        g.setColor(getBackground());
        int row;
        if(iTabsPosition == 0)
            row = TF_TOP;
        else
            row = TF_TOP - TF_BTN_HEIGHT - 5;
        if(imgBG != null)
        {
            for(int x = 0; x < r.height; x += imgBG.getHeight(this))
            {
                for(int i = 0; i < r.width; i += imgBG.getWidth(this))
                    g.drawImage(imgBG, i, x, imgBG.getWidth(this), imgBG.getHeight(this), this);

            }

        } else
        {
            g.clearRect(0, 0, r.width, r.height);
        }
        if(!bShowTabsOnly)
        {
            if(getBackground().equals(Color.white))
                g.setColor(Color.black);
            else
                g.setColor(Color.white);
            if(imgBG == null)
                g.drawLine(col, row, (col + width) - 1, row);
            g.drawLine(col, row, col, (row + height) - 1);
            g.setColor(Color.gray);
            if(iTabsPosition == 0)
                g.drawLine(col + 2, (row + height) - 2, (col + width) - 2, (row + height) - 2);
            else
            if(imgBG == null)
                g.drawLine(col + 2, (row + height) - 2, (col + width) - 2, (row + height) - 2);
            g.drawLine((col + width) - 2, row + 2, (col + width) - 2, (row + height) - 2);
            if(getBackground().equals(Color.black))
                g.setColor(Color.white);
            else
                g.setColor(Color.black);
            if(imgBG == null)
                g.drawLine(col + 1, (row + height) - 1, (col + width) - 1, (row + height) - 1);
            g.drawLine((col + width) - 1, row + 1, (col + width) - 1, (row + height) - 1);
        }
        int x2 = TF_LEFT + 8;
        int x3 = 0;
        int x4 = TF_LEFT;
        int sze = vLabels.size();
        vPolys.removeAllElements();
        Font f = g.getFont();
        FontMetrics fm = getFontMetrics(fReg);
        FontMetrics fms = getFontMetrics(fSel);
        int labelWidth = 0;
        int w;
        for(w = 0; w < firstVisibleTab; w++)
            vPolys.addElement(nullPoly);

        if(w > 0)
            x4 += 2;
        for(; w < sze; w++)
        {
            Polygon p = new Polygon();
            try
            {
                String sLabel = (String)vLabels.elementAt(w);
                if(w == curIndex)
                    labelWidth = fms.stringWidth(sLabel);
                else
                    labelWidth = fm.stringWidth(sLabel);
                int y1;
                int y2;
                if(iTabsPosition == 0)
                {
                    y1 = TF_TOP - TF_BTN_HEIGHT;
                    y2 = TF_TOP - 1;
                } else
                {
                    y1 = r.height + TF_BOTTOM + 1;
                    y2 = (r.height + TF_BOTTOM) - TF_BTN_HEIGHT - 5;
                }
                int x1;
                if(iTabsStyle == 0)
                {
                    x1 = x4 + 2;
                    x2 = x1 + labelWidth + 13;
                } else
                {
                    x1 = x2 - 7;
                    x2 = x1 + labelWidth + 28;
                }
                if((x2 + 36) - TF_RIGHT > r.width)
                    break;
                if(iTabsPosition == 0)
                {
                    if(w == curIndex)
                    {
                        y1 -= 3;
                        x1 -= 2;
                    }
                    if(getBackground().equals(Color.white))
                        g.setColor(Color.gray);
                    else
                        g.setColor(Color.white);
                    if(curIndex == w + 1)
                        g.drawLine(x1 + 2, y1, x2 - 2, y1);
                    else
                        g.drawLine(x1 + 2, y1, x2, y1);
                    if(curIndex != w - 1)
                    {
                        g.drawLine(x1, y1 + 2, x1, y2);
                        x3 = x1;
                    } else
                    {
                        x3 = x1 + 1;
                    }
                    g.drawLine(x1 + 1, y1 + 1, x1 + 1, y1 + 1);
                    if(curIndex != w + 1)
                    {
                        g.setColor(Color.gray);
                        g.drawLine(x2, y1, x2, y2);
                        if(getBackground().equals(Color.black))
                            g.setColor(Color.white);
                        else
                            g.setColor(Color.black);
                        g.drawLine(x2 + 1, y1 + 2, x2 + 1, y2);
                        x4 = x2;
                    } else
                    {
                        x4 = x2 - 1;
                    }
                } else
                if(iTabsStyle == 1)
                {
                    g.setColor(Color.gray);
                    g.drawLine(x1 + 9, y1 - 2, x2 - 9, y1 - 2);
                    if(getBackground().equals(Color.black))
                        g.setColor(Color.white);
                    else
                        g.setColor(Color.black);
                    if(w == 0 || w == curIndex)
                    {
                        g.drawLine(x1, y2, x1 + 9, y1);
                        p.addPoint(x1, y2);
                    } else
                    {
                        g.drawLine(col, row, (col + width) - 1, row);
                        g.drawLine(x1 + 4, y1 - 9, x1 + 9, y1);
                        p.addPoint(x1 + 9, y2);
                        p.addPoint(x1 + 4, y1 - 9);
                    }
                    p.addPoint(x1 + 9, y1);
                    p.addPoint(x2 - 9, y1);
                    if(w + 1 == curIndex)
                    {
                        p.addPoint(x2 - 5, y1);
                        p.addPoint(x2 - 9, y2);
                    } else
                    {
                        g.drawLine(x2, y2, x2 - 9, y1);
                        p.addPoint(x2, y2);
                    }
                    if(w == 1 || w == curIndex)
                        p.addPoint(x1, y2);
                    else
                        p.addPoint(x1 + 9, y2);
                } else
                {
                    if(w == curIndex)
                    {
                        y1 += 3;
                        x1 -= 2;
                    }
                    if(getBackground().equals(Color.white))
                        g.setColor(Color.black);
                    else
                        g.setColor(Color.white);
                    if(curIndex == w + 1)
                        g.drawLine(x1 + 2, y1, x2 - 2, y1);
                    else
                        g.drawLine(x1 + 2, y1, x2, y1);
                    if(curIndex != w - 1)
                    {
                        g.drawLine(x1, y1 - 2, x1, y2);
                        x3 = x1;
                    } else
                    {
                        x3 = x1 + 1;
                    }
                    g.drawLine(x1 + 1, y1 - 1, x1 + 1, y1 - 1);
                    if(curIndex != w + 1)
                    {
                        g.setColor(Color.gray);
                        g.drawLine(x2, y1, x2, y2);
                        if(getBackground().equals(Color.black))
                            g.setColor(Color.white);
                        else
                            g.setColor(Color.black);
                        g.drawLine(x2 + 1, y1 - 2, x2 + 1, y2);
                        x4 = x2;
                    } else
                    {
                        x4 = x2 - 1;
                    }
                }
                if(w == curIndex)
                {
                    if(iTabsPosition == 0)
                        y2++;
                    else
                        y2--;
                    g.setColor(getBackground());
                    if(imgBG == null)
                    {
                        g.drawLine(x1 + 1, y2, x2, y2);
                        if(iTabsPosition == 1)
                            g.drawLine(x1 + 1, y2 - 1, x2, y2 - 1);
                    } else
                    {
                        if(getBackground().equals(Color.white))
                            g.setColor(Color.black);
                        else
                            g.setColor(Color.white);
                        if(iTabsPosition == 0)
                        {
                            g.drawLine(col + 1, row, x1 + 1, y2);
                            g.drawLine(x2, y2, (col + width) - 1, row);
                        } else
                        {
                            g.setColor(Color.black);
                            g.drawLine(col + 1, y2, x1, y2);
                            g.drawLine(x2, y2, (col + width) - 1, y2);
                            g.setColor(Color.gray);
                            g.drawLine(col + 1, y2 - 1, x1, y2 - 1);
                            g.drawLine(x2, y2 - 1, (col + width) - 1, y2 - 1);
                        }
                    }
                    g.setFont(fSel);
                } else
                {
                    g.setFont(fReg);
                }
                if(iTabsStyle == 0)
                {
                    p.addPoint(x3, y2);
                    p.addPoint(x4, y2);
                    p.addPoint(x4, y1);
                    p.addPoint(x3, y1);
                    p.addPoint(x3, y2);
                }
                vPolys.addElement(p);
                if(w == iMOLabel)
                {
                    cTempColor = cMOLabelColor;
                } else
                {
                    cTempColor = Color.gray;
                    Boolean bool = (Boolean)vEnabled.elementAt(w);
                    if(bool.booleanValue())
                        cTempColor = cLabelColor;
                }
                if(iTabsPosition == 0)
                {
                    g.setColor(cTempColor);
                    g.drawString(sLabel, x1 + 8, y1 + 15 + osAdjustment);
                } else
                if(iTabsStyle == 0)
                    g.drawString(sLabel, x1 + 8, (y1 - 6) + osAdjustment);
                else
                    g.drawString(sLabel, x1 + 14, (y1 - 4) + osAdjustment);
            }
            catch(ArrayIndexOutOfBoundsException _ex) { }
        }

        if(firstVisibleTab > 0 || w < sze)
        {
            dbLeft.show();
            dbRight.show();
            if(firstVisibleTab > 0)
                dbLeft.enable();
            else
                dbLeft.disable();
            if(w < sze)
                dbRight.enable();
            else
                dbRight.disable();
            if(iTabsPosition == 0)
            {
                dbLeft.reshape((r.width - 33) + TF_RIGHT, TF_TOP - 16, 16, 15);
                dbRight.reshape((r.width - 16) + TF_RIGHT, TF_TOP - 16, 16, 15);
            } else
            {
                dbLeft.reshape((r.width - 33) + TF_RIGHT, (r.height + TF_BOTTOM) - TF_BTN_HEIGHT, 16, 15);
                dbRight.reshape((r.width - 16) + TF_RIGHT, (r.height + TF_BOTTOM) - TF_BTN_HEIGHT, 16, 15);
            }
        } else
        {
            dbLeft.hide();
            dbRight.hide();
        }
        for(; w < sze; w++)
            vPolys.addElement(nullPoly);

        g.setFont(f);
        g.setColor(c);
    }

    public boolean handleEvent(Event evt)
    {
        switch(evt.id)
        {
        default:
            break;

        case 501: // Event.MOUSE_DOWN
            int sizeR = vPolys.size();
            for(int x = 0; x < sizeR; x++)
                try
                {
                    Polygon p = (Polygon)vPolys.elementAt(x);
                    if(p != nullPoly && p.inside(evt.x, evt.y))
                    {
                        showTab(x);
                        return true;
                    }
                }
                catch(ArrayIndexOutOfBoundsException _ex) { }

            break;

        case 1001: // Event.ACTION_EVENT
            if(evt.target == dbLeft)
            {
                if(--firstVisibleTab < 0)
                    firstVisibleTab = 0;
                else
                    repaint();
                return true;
            }
            if(evt.target != dbRight)
                break;
            int sze = vLabels.size();
            if(++firstVisibleTab == sze)
                firstVisibleTab--;
            else
                repaint();
            return true;
        }
        return super.handleEvent(evt);
    }

    public Component add(Component comp)
    {
        return comp;
    }

    public synchronized Component add(Component comp, int pos)
    {
        return comp;
    }

    public synchronized Component add(String name, Component comp)
    {
        return comp;
    }

    public synchronized void remove(Component comp)
    {
        if(comp == dbLeft || comp == dbRight)
            return;
        super.remove(comp);
        if(comp == userPanel)
            userPanel = null;
    }

    public synchronized void removeAll()
    {
        super.removeAll();
        super.add(dbLeft, -1);
        super.add(dbRight, -1);
        userPanel = null;
    }

    public void setLayout(LayoutManager layoutmanager)
    {
    }

    public Insets insets()
    {
        btpInsets = super.insets();
//        btpInsets.left += TF_LEFT + 1;
//        btpInsets.right += 2 - TF_RIGHT;
//        if(iTabsPosition == 0)
//        {
//            btpInsets.top += TF_TOP + 1;
//            btpInsets.bottom += 2 - TF_BOTTOM;
//        } else
//        {
//            btpInsets.top += (TF_TOP - TF_BTN_HEIGHT) + 1;
//            btpInsets.bottom += (TF_BTN_HEIGHT + 2) - TF_BOTTOM;
//        }
        return btpInsets;
    }

    public Dimension preferredSize()
    {
        Dimension s = size();
        Dimension m = minimumSize();
        return new Dimension(Math.max(s.width, m.width), Math.max(s.height, m.height));
    }

    public Dimension minimumSize()
    {
        if(userPanel != null)
        {
            Dimension s = userPanel.minimumSize();
            return new Dimension(s.width + btpInsets.left + btpInsets.right, s.height + btpInsets.top + btpInsets.bottom);
        } else
        {
            return new Dimension(100, 100);
        }
    }
}
