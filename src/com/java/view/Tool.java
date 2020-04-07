/**
* Developed by Jhonny Trejos Barrios.
* Technology: Java.
* Version: Java Development Kit 11.0.4 LTS, Standard Edition.
* Development Environment: Sublime Text 3 [build 3211].
* Date: 05/04/20, Time: 10:21:04.
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

import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.WindowListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Tool extends javax.swing.JFrame {
  private JPanel contentPane;
  private JPanel wrapperKeys;
  private JPanel global;
  private Output sizeInfo;
  private Output posInfo;
  private KeyPane keypaneSize;
  private KeyPane keypaneLocation;
  public Tool() {
    verifyOS();
    setSize(350,300);
    setBackground(new Color(18,16,24));
    setLocationRelativeTo(null);
    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    ((JPanel)getContentPane()).setOpaque(false);

    contentPane=new JPanel();
    contentPane.setOpaque(false);
    add(contentPane);

    sizeInfo=new Output();
    posInfo=new Output();

    keypaneSize=new KeyPane();
    keypaneLocation=new KeyPane();
    wrapperKeys=new JPanel();
    wrapperKeys.setOpaque(false);
    wrapperKeys.add(keypaneLocation);
    wrapperKeys.add(keypaneSize);
    global=new JPanel();
    global.setOpaque(false);
    global.setPreferredSize(new Dimension(getWidth(),250));
    global.add(posInfo);
    global.add(sizeInfo);
    global.add(wrapperKeys);
    contentPane.add(global);

    setupKeysPos();
    setupKeysSize();
  }

  public void setMenuBar(javax.swing.JMenuBar mBar) {
    setJMenuBar(mBar);
  }

  public void setToolTitle(String title) {
    setTitle(title);
  }

  public void setToolBounds(java.awt.Rectangle rec) {
    setBounds(rec);
    global.setPreferredSize(new Dimension(getWidth(),250));
    sizeInfo.setPreferredSize(new Dimension(getWidth(),60));
    posInfo.setPreferredSize(new Dimension(getWidth(),60));
  }

  public void setSizeInfo(String info) {
    sizeInfo.setText(info);
  }

  public void setPosInfo(String info) {
    posInfo.setText(info);
  }

  public void updateLabels(int a,int b,boolean alt) {
    if(alt)
      setSizeInfo(update(sizeInfo.getText(),a,b));
    else
      setPosInfo(update(posInfo.getText(),a,b));
  }

  public void setMoreWidth() {
    setSize(getSize().width+1,getSize().height);
    setSizeInfo(update(sizeInfo.getText(),getSize().width,getSize().height));
  }

  public void setLessWidth() {
    setSize(getSize().width-1,getSize().height);
    setSizeInfo(update(sizeInfo.getText(),getSize().width,getSize().height));
  }

  public void setMoreHeight() {
    setSize(getSize().width,getSize().height+1);
    setSizeInfo(update(sizeInfo.getText(),getSize().width,getSize().height));
  }

  public void setLessHeight() {
    setSize(getSize().width,getSize().height-1);
    setSizeInfo(update(sizeInfo.getText(),getSize().width,getSize().height));
  }

  public void setMotionUp() {
    setLocation(getLocation().x,getLocation().y-1);
    setPosInfo(update(posInfo.getText(),getLocation().x,getLocation().y));
  }

  public void setMotionDown() {
    setLocation(getLocation().x,getLocation().y+1);
    setPosInfo(update(posInfo.getText(),getLocation().x,getLocation().y));
  }

  public void setMotionLeft() {
    setLocation(getLocation().x-1,getLocation().y);
    setPosInfo(update(posInfo.getText(),getLocation().x,getLocation().y));
  }

  public void setMotionRight() {
    setLocation(getLocation().x+1,getLocation().y);
    setPosInfo(update(posInfo.getText(),getLocation().x,getLocation().y));
  }

  public void addOpenListener(WindowListener l) {
    addWindowListener(l);
  }

  public void addPosListener(ActionListener l) {
    for(int i=0;i<keypaneLocation.getComponentCount();i++) {
      if(keypaneLocation.getComponent(i)instanceof Button)
        ((Button)keypaneLocation.getComponent(i)).addActionListener(l);
    }
  }

  public void addSizeListener(ActionListener l) {
    for(int i=0;i<keypaneSize.getComponentCount();i++) {
      if(keypaneSize.getComponent(i)instanceof Button)
        ((Button)keypaneSize.getComponent(i)).addActionListener(l);
    }
  }

  public void addMouseSizeListener(ComponentListener l) {
    addComponentListener(l);
  }

  private String update(String d,int x,int y) {
    return d.replaceAll("\\d+ - \\d+",x+" - "+y);
  }

  private void setupKeysPos() {
    String[] values={"UP","DOWN","LEFT","RIGHT"};
    for(String S:values)
      keypaneLocation.add(new Button(S));
  }

  private void setupKeysSize() {
    String[] values={"W+","W-","H+","H-"};
    for(String S:values)
      keypaneSize.add(new Button(S));
  }

  private void verifyOS() {
    String osName=System.getProperty("os.name");
    System.setProperty("apple.laf.useScreenMenuBar",osName.contains("Mac")+"");
  }
}

@SuppressWarnings("serial")
class Button extends javax.swing.JButton {
  public Button(String txt) {
    setText(txt);
    setPreferredSize(new Dimension(60,28));
    putClientProperty("JComponent.sizeVariant","small");
    setName(txt);
  }
}

@SuppressWarnings("serial")
class Output extends javax.swing.JLabel {
  public Output() {
    setPreferredSize(new Dimension(350,60));
    setFont(new Font("Helvetica Neue",Font.BOLD,20));
    setForeground(new Color(255,145,100));
    setHorizontalAlignment(this.CENTER);
  }
}

@SuppressWarnings("serial")
class KeyPane extends javax.swing.JPanel {
  public KeyPane() {
    int W=135;
    int H=65;
    setPreferredSize(new Dimension(W,H));
    setOpaque(false);
  }
}
