// FrontEnd Plus GUI for JAD
// DeCompiled : DirectionButton.class

import java.awt.*;
import ButtonBase;


public class DirectionButton extends ButtonBase
{

    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;
    private int direction;
    private int left;
    private int right;
    private int top;
    private int bottom;
    private int indent;
    private Polygon poly;
    Color cForeColor;

    public DirectionButton()
    {
        this(0);
    }

    public DirectionButton(int i)
    {
        cForeColor = Color.black;
        direction = i;
        left = 0;
        right = 0;
        bottom = 0;
        indent = 0;
        poly = null;
    }

    public void setDirection(int i)
    {
        direction = i;
    }

    public int getDirection()
    {
        return direction;
    }

    public void setForeColor(Color color)
    {
        cForeColor = color;
        invalidate();
    }

    public void setArrowIndent(int i)
    {
        indent = i;
        invalidate();
    }

    public int getArrowIndent()
    {
        return indent;
    }

    public void shrinkTriangle(int i, int j, int k, int l)
    {
        left = i;
        right = j;
        top = k;
        bottom = l;
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        updatePolygon();
        if(isEnabled())
            g.setColor(cForeColor);
        else
            g.setColor(Color.gray);
        g.fillPolygon(poly);
    }

    public Dimension preferredSize()
    {
        Dimension dimension = size();
        return new Dimension(Math.max(dimension.width, minimumSize().width), Math.max(dimension.height, minimumSize().height));
    }

    void updatePolygon()
    {
        Dimension dimension = size();
        poly = new Polygon();
        int i = dimension.width / 2 + pressedAdjustment;
        int j = dimension.height / 2 + pressedAdjustment;
        int k = top + bevel * 2 + pressedAdjustment + indent;
        int l = ((dimension.height - bottom - bevel * 2) + pressedAdjustment) - indent;
        int i1 = left + bevel * 2 + pressedAdjustment + indent;
        int j1 = ((dimension.width - right - bevel * 2) + pressedAdjustment) - indent;
        switch(direction)
        {
        case 2: // '\002'
            poly.addPoint(i, k);
            poly.addPoint(i1, l);
            poly.addPoint(j1, l);
            return;

        case 3: // '\003'
            poly.addPoint(i, l);
            poly.addPoint(i1, k);
            poly.addPoint(j1, k);
            return;

        case 0: // '\0'
            poly.addPoint(i1, j);
            poly.addPoint(j1, k);
            poly.addPoint(j1, l);
            return;

        case 1: // '\001'
            poly.addPoint(j1, j);
            poly.addPoint(i1, k);
            poly.addPoint(i1, l);
            return;
        }
    }
}
