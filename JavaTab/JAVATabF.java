// FrontEnd Plus GUI for JAD
// DeCompiled : JAVATabF.class

import java.applet.Applet;
import java.applet.AppletContext;
import java.awt.*;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import TabPanel;

public class JAVATabF extends Applet
    implements Runnable
{
  String sDefMO;
  String sCopyright;
  int xSwitch;
  String sLoading;
  Thread thJAVATab;
  String aHref[];
  String aFrame[];
  String aHref2[];
  String aFrame2[];
  String aMsg[];
  String tempString[];
  String sText;
  String sHref;
  String sFrame;
  String sHref2;
  String sFrame2;
  String sMsg;
  String sMsgOld;
  int iTabIdx;
  int iLoading;
  int iTabCnt;
  int oldx;
  int iTabDefault;
  int iBGRed;
  int iFGRed;
  int iBHRed;
  int iFHRed;
  int iBGGreen;
  int iFGGreen;
  int iBHGreen;
  int iFHGreen;
  int iBGBlue;
  int iFGBlue;
  int iBHBlue;
  int iFHBlue;
  Font fFont;
  private boolean bCopyright;
  private boolean allLoaded;
  Image imgBG;
  Graphics g1;
  boolean fAuscomp;
  String sMOUSEOVERSOUND;
  String sMOUSECLICKSOUND;
  int oldTabPanel;
  TabPanel tabPanel1;
  Label label1;

  void tabPanel1_MouseMove(Event event)
  {
    int x = 0;
    try
      {
      x = tabPanel1.setTabLabelColor(event);
      try
        {
        if(oldTabPanel != x && !sMOUSEOVERSOUND.equals("none"))
          play(getCodeBase(), sMOUSEOVERSOUND);
        oldTabPanel = x;
        }
      catch(Exception _ex) { }
      if(x == 999 && oldx != 999)
        {
        tabPanel1.setMOLabelColor(tabPanel1.getMOColor(), x);
        sMsg = sDefMO;
        } 
      else
        {
        sMsg = aMsg[x];
        }
      oldx = x;
      sMsg = sMsg.trim();
      if(sMsg != sMsgOld)
        {
        if(sMsg.toLowerCase().equals("none"))
          showStatus(" ");
        else
          showStatus(sMsg);
        sMsgOld = sMsg;
        return;
        }
      }
    catch(ArrayIndexOutOfBoundsException _ex) { }
  }

  void tabPanel1_MouseUp(Event event)
  {
    try
      {
      if(!sMOUSECLICKSOUND.equals("none"))
        play(getCodeBase(), sMOUSECLICKSOUND);
      }
    catch(Exception _ex) { }
    try
      {
      int iTabIx = tabPanel1.getCurrentPanelNdx();
      sHref = aHref[iTabIx];
      sFrame = aFrame[iTabIx];
      sHref2 = aHref2[iTabIx];
      sFrame2 = aFrame2[iTabIx];
      sHref = sHref.trim();
      sFrame = sFrame.trim();
      sHref2 = sHref2.trim();
      sFrame2 = sFrame2.trim();
      System.out.println(sHref);
      if(!sHref.toLowerCase().equals("none"))
        try
          {
          getAppletContext().showDocument(new URL(getCodeBase(), sHref), sFrame);
          }
        catch(MalformedURLException _ex) { }
      if(!sHref2.toLowerCase().equals("none"))
        try
          {
          getAppletContext().showDocument(new URL(getCodeBase(), sHref2), sFrame2);
          return;
          }
        catch(MalformedURLException _ex)
          {
          return;
          }
      }
    catch(Exception _ex) { }
  }

  public void start()
  {
    g1 = getGraphics();
    paint(g1);
    thJAVATab = new Thread(this);
    thJAVATab.start();
  }

  public void stop()
  {
    thJAVATab.stop();
    thJAVATab = null;
  }

  public void paint(Graphics g)
  {
    iLoading++;
    g.drawString(sLoading, 10, 15);
    g.drawString("Loading resource No." + iLoading + " - Please wait", 10, 35);
  }

  public void run()
  {
    if(!allLoaded)
      {
      constructTab();
      tabPanel1.show(true);
      if(iTabDefault < 999)
        {
        tabPanel1.setCurrentPanelNdx(iTabDefault);
        tabPanel1_MouseUp(null);
        return;
        }
      tabPanel1.setCurrentPanelNdx(iTabIdx);
      }
  }

  public void init()
  {
    super.init();
    setLayout(null);
    addNotify();
    tabPanel1 = new TabPanel();
    tabPanel1.setLayout(null);
    switch(xSwitch)
      {
    case 0: // '\0'
      sLoading = "eNavigator Suite V6.5 [Tab] - Evaluation Copy";
    break;

    case 1: // '\001'
      sLoading = "eNavigator Suite V6.5 [Tab] - Registered";
    // fall through

    case 2: // '\002'
      sLoading = "eNavigator Suite V6.5 [Tab] - Registered";
    // fall through

    case 3: // '\003'
      sLoading = "eNavigator Suite V6.5 [Tab] - Registered";
    break;
    }
    setLayout(null);
    addNotify();
    resize(430, 270);
  }

  private void constructTab()
  {
    String strBG = "";
    try
      {
      strBG = getParameter("COPYRIGHT");
      if(strBG.indexOf(sCopyright) > 0)
        bCopyright = true;
      else
        bCopyright = false;
      }
    catch(Exception _ex) { }
    try
      {
      sMOUSEOVERSOUND = "none";
      strBG = getParameter("MOUSEOVERSOUND");
      if(strBG != null)
        sMOUSEOVERSOUND = strBG;
      }
    catch(Exception _ex) { }
    try
    {
      sMOUSECLICKSOUND = "none";
      strBG = getParameter("MOUSECLICKSOUND");
      if(strBG != null)
        sMOUSECLICKSOUND = strBG;
    }
    catch(Exception _ex) { }
    try
      {
      strBG = getParameter("TAB_BG");
      if(strBG == null)
        {
        iBGRed = 192;
        iBGGreen = 192;
        iBGBlue = 192;
        } 
      else
        {
        iBGRed = getCOLOR(0, "TAB_BG", strBG);
        iBGGreen = getCOLOR(1, "TAB_BG", strBG);
        iBGBlue = getCOLOR(2, "TAB_BG", strBG);
        }
      }
    catch(Exception _ex) { }
    try
      {
      strBG = getParameter("TAB_IDX");
      if(strBG != null)
        iTabIdx = Integer.parseInt(strBG);
      }   
    catch(Exception _ex) { }
    Dimension s = size();
    tabPanel1.reshape(0, 0, s.width, s.height);
    tabPanel1.setBackground(new Color(iBGRed, iBGGreen, iBGBlue));
    try
      {
      strBG = getParameter("TAB_FG");
      if(strBG == null)
        {
        iBGRed = 0;
        iBGGreen = 0;
        iBGBlue = 0;
        } 
      else
        {
        iBGRed = getCOLOR(0, "TAB_FG", strBG);
        iBGGreen = getCOLOR(1, "TAB_FG", strBG);
        iBGBlue = getCOLOR(2, "TAB_FG", strBG);
        }
      }
    catch(Exception _ex) { }
    tabPanel1.setLabelColor(new Color(iBGRed, iBGGreen, iBGBlue));
    setForeground(new Color(iBGRed, iBGGreen, iBGBlue));
    try
      {
      strBG = getParameter("TAB_DEFAULT");
      if(strBG == null)
          iTabDefault = 999;
      else
          iTabDefault = Integer.parseInt(strBG);
      }
    catch(Exception _ex) { }
    try
      {
      strBG = getParameter("TABONBOTTOM");
      if(strBG == null)
        {
        tabPanel1.setTabsOnBottom(false);
        } 
      else
        {
        tabPanel1.setTabsOnBottom(true);
        if(strBG.toLowerCase().equals("no"))
          tabPanel1.setTabsOnBottom(false);
        }
      }
    catch(Exception _ex) { }
    try
      {
      strBG = getParameter("TAB_FONT");
      String fName = "";
      String fStyle = "";
      String fSize = "";
      int iStyle = 0;
      if(strBG != null)
        {
        StringTokenizer parser = new StringTokenizer(strBG, ";");
        try
          {
          fName = parser.nextToken();
          fStyle = parser.nextToken();
          fSize = parser.nextToken();
          if(fStyle.trim().toLowerCase().equals("bold"))
            iStyle = 1;
          if(fStyle.trim().toLowerCase().equals("italic"))
            iStyle = 2;
          fFont = new Font(fName, iStyle, Integer.parseInt(fSize.trim()));
          }
        catch(NoSuchElementException _ex)
          {
          System.out.println(strBG + " Parameter Error(1)");
          }
        catch(Exception _ex)
          {
          System.out.println(strBG + " Parameter Error(2)");
          }
        }
      tabPanel1.setFont(fFont);
      }
    catch(Exception _ex) { }
    try
      {
      strBG = getParameter("TAB_MOUSEOVER");
      if(strBG == null)
        {
        iBGRed = 255;
        iBGGreen = 0;
        iBGBlue = 0;
        } 
      else
        {
        iBGRed = getCOLOR(0, "TAB_MOUSEOVER", strBG);
        iBGGreen = getCOLOR(1, "TAB_MOUSEOVER", strBG);
        iBGBlue = getCOLOR(2, "TAB_MOUSEOVER", strBG);
        }
      tabPanel1.setMOColor(new Color(iBGRed, iBGGreen, iBGBlue));
    }
    catch(Exception _ex) { }
    try
      {
      imgBG = null;
      strBG = getParameter("TAB_BG_IMAGE");
      if(strBG != null)
        {
        imgBG = getImage(getCodeBase(), strBG);
        MediaTracker m = new MediaTracker(this);
        try
          {
          m.addImage(imgBG, 0);
          m.waitForAll();
          }
        catch(InterruptedException _ex)
          {
          return;
          }
        if(imgBG.getHeight(this) > 0)
          tabPanel1.setBGImage(imgBG);
        }
      }
    catch(Exception _ex) { }
    add(tabPanel1);
    tabPanel1.show(false);
    int i;
    for(i = 0; getParameter("TAB" + i) != null; i++);
    iTabCnt = i + 1;
    tempString = new String[iTabCnt];
    aHref = new String[iTabCnt];
    aFrame = new String[iTabCnt];
    aHref2 = new String[iTabCnt];
    aFrame2 = new String[iTabCnt];
    aMsg = new String[iTabCnt];
    for(i = 0; getParameter("TAB" + i) != null; i++)
      {
      workParam(getParameter("TAB" + i), i);
      tabPanel1.add(label1);
      if(i == 0 && xSwitch == 0)
        tabPanel1.add(label1);
      }
    tabPanel1.setPanelLabels(tempString);
    tabPanel1.show();
    allLoaded = true;
  }

  public boolean handleEvent(Event event)
  {
    if(event.target == tabPanel1 && event.id == 503)
      {
      tabPanel1_MouseMove(event);
      return true;
      }
    if(event.target == tabPanel1 && event.id == 502)
      {
      tabPanel1_MouseUp(event);
      return true;
      } 
    else
      {
      return super.handleEvent(event);
      }
  }

  private int getCOLOR(int iColor, String sWhat, String sPBG)
  {
    int iRed = 255;
    int iBlue = 255;
    int iGreen = 255;
    StringTokenizer parser = new StringTokenizer(sPBG, ",");
    try
      {
      String sRed = parser.nextToken();
      String sGreen = parser.nextToken();
      String sBlue = parser.nextToken();
      iRed = Integer.parseInt(sRed);
      iGreen = Integer.parseInt(sGreen);
      iBlue = Integer.parseInt(sBlue);
      }
    catch(NoSuchElementException _ex)
      {
      System.out.println(sWhat + " Parameter Error(1)");
      }
    catch(Exception _ex)
      {
      System.out.println(sWhat + " Parameter Error(2)");
      }
    switch(iColor)
      {
      case 0: // '\0'
      return iRed;

      case 1: // '\001'
      return iGreen;

      case 2: // '\002'
      return iBlue;
      }
    return 255;
  }

  private void workParam(String sItem, int iTCnt)
  {
    sHref = "None";
    sFrame = "None";
    sHref2 = "None";
    sFrame2 = "None";
    sMsg = "None";
    if(sItem.substring(0, 3).equals("***"))
      {
      sHref = "None";
      sFrame = "None";
      sHref2 = "None";
      sFrame2 = "None";
      sMsg = "None";
      return;
      }
    StringTokenizer parser = new StringTokenizer(sItem, ";");
    parser.countTokens();
    try
      {
      sText = parser.nextToken();
      sHref = parser.nextToken();
      sFrame = parser.nextToken();
      sMsg = parser.nextToken();
      sHref2 = parser.nextToken();
      sFrame2 = parser.nextToken();
      }
    catch(NoSuchElementException _ex) { }
    catch(Exception _ex)
      {
      System.out.println(sItem + " Parameter Error(3)");
      }
    if(xSwitch == 0 || xSwitch == 0)
        iTCnt++;
    if(xSwitch == 3 && !fAuscomp)
        iTCnt++;
    tempString[iTCnt] = sText;
    aHref[iTCnt] = sHref;
    aFrame[iTCnt] = sFrame;
    aHref2[iTCnt] = sHref2;
    aFrame2[iTCnt] = sFrame2;
    aMsg[iTCnt] = sMsg;
  }

  public JAVATabF()
  {
    sDefMO = "";
    sCopyright = "AUSCOMP (www.auscomp.com)";
    xSwitch = 2;
    sLoading = "eNavigator Suite V6.5";
    sText = "";
    sHref = "";
    sFrame = "";
    sHref2 = "";
    sFrame2 = "";
    sMsg = "";
    sMsgOld = "";
    iTabDefault = 999;
    iBGRed = 255;
    iFHRed = 255;
    iBGGreen = 255;
    iFHGreen = 255;
    iBGBlue = 255;
    fFont = new Font("Dialog", 0, 12);
    bCopyright = false;
    allLoaded = false;
    fAuscomp = true;
    sMOUSEOVERSOUND = "none";
    sMOUSECLICKSOUND = "none";
    oldTabPanel = -1;
  }
  
  public void setTabIndex(int indX)
  {
    iTabIdx = indX;
    tabPanel1.showTabPanel(indX);
  }

  public int getTabIndex()
  {
    return iTabIdx;
  }
}
