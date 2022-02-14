// FrontEnd Plus GUI for JAD
// DeCompiled : navdeluxe$MenuPanel.class

import java.awt.*;

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
