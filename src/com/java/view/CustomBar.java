/**
* Developed by Jhonny Trejos Barrios.
* Technology: Java.
* Version: Java Development Kit 11.0.4 LTS, Standard Edition.
* Development Environment: Sublime Text 3 [build 3211].
* Date: 05/04/20, Time: 18:58:42.
*
* Additional Info.
*
* Source Code Target Or Details:
*
*     []
*
* Licenses: GNU GPL v3.0, Eclipse Public License 1.0, personal use only.
* Developer Contact: jtrejosb@live.com
* GitHub.com/jtrejosb
*/
package com.java.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.Dimension;
import java.awt.Rectangle;

import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class CustomBar extends javax.swing.JMenuBar {
  private JMenu menu;
  private JMenu sub1;
  private JMenuItem itQ;
  private ArrayList<Preset> P=new ArrayList<>();
  public CustomBar() {
    P.add(new Preset("Safari [no tabs]",new Rectangle(18,22,1240,736)));
    P.add(new Preset("iTunes",new Rectangle(92,49,1100,699)));
    P.add(new Preset("iTunes MiniPlayer",new Rectangle(92,49,281,281)));
    P.add(new Preset("Finder",new Rectangle(257,108,870,552)));
    P.add(new Preset("iTerm",new Rectangle(18,20,850,744)));
    P.add(new Preset("iTerm [centered]",new Rectangle(165,20,950,747)));
    P.add(new Preset("Sublime Text",new Rectangle(165,22,950,747)));
    P.add(new Preset("Default Java Frame",Preset.DEFAULT_RECT));
    menu=new JMenu("View");
    sub1=new JMenu("Window Preset");
    for(int i=0;i<P.size();i++) {
      JRadioButtonMenuItem MI=new JRadioButtonMenuItem(P.get(i).toString());
      MI.setName(String.valueOf(i));
      sub1.add(MI);
      if(i==P.size()-2)
        sub1.addSeparator();
    }
    menu.add(sub1);
    menu.addSeparator();
    if(!System.getProperty("os.name").contains("Mac")) {
      itQ=new JMenuItem("Quit");
      itQ.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,ActionEvent.META_MASK));
      menu.add(itQ);
    }
    add(menu);
  }

  public JMenuBar getBarInstance() {
    return this;
  }

  public java.awt.Component[] getOptions() {
    return sub1.getMenuComponents();
  }

  public Rectangle getFrameGeometry(int ind) {
    return P.get(ind).getRect();
  }

  public String getFrameTitle(int ind) {
    return "pwl Tool - "+P.get(ind).toString();
  }

  public String getFrameXY(int ind) {
    return "Frame Position: "+P.get(ind).getRect().x+" - "+P.get(ind).getRect().y;
  }

  public String getFrameSize(int ind) {
    return "Frame Dimension: "+P.get(ind).getRect().width+" - "+P.get(ind).getRect().height;
  }

  public void addMenuListener(ActionListener l) {
    for(int i=0;i<sub1.getItemCount();i++) {
      if(sub1.getItem(i)==null) {
        i++;
      }
      sub1.getItem(i).addActionListener(l);
    }
  }
}

class Preset {
  private java.awt.Rectangle r;
  private String info;
  public static final Rectangle DEFAULT_RECT=getDefaultRect();
  public Preset(String info,Rectangle r) {
    this.r=r;
    this.info=info;
  }

  public Rectangle getRect() {
    return r;
  }

  private static Rectangle getDefaultRect() {
    Dimension D1=java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    Dimension D2=new Dimension(350,300);
    int x=D1.width/2-D2.width/2;
    int y=D1.height/2-D2.height/2;
    return new Rectangle(x,y,D2.width,D2.height);
  }

  @Override
  public String toString() {
    return info;
  }
}
