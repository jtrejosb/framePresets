/**
* Developed by Jhonny Trejos Barrios.
* Technology: Java.
* Version: Java Development Kit 11.0.4 LTS, Standard Edition.
* Development Environment: Sublime Text 3 [build 3211].
* Date: 05/04/20, Time: 20:15:47.
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
package com.java.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Component;

import java.util.Arrays;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JRadioButtonMenuItem;

import com.java.view.CustomBar;
import com.java.view.Tool;

public class ToolController {
  private List<Component> L;
  private CustomBar CB;
  private Tool T;
  public ToolController(CustomBar CB,Tool T) {
    this.CB=CB;
    this.T=T;
    L=Arrays.asList(CB.getOptions());
    this.CB.addMenuListener(e->actions(e));
    this.T.setMenuBar(CB.getBarInstance());
    this.T.addPosListener(e->move(e));
    this.T.addSizeListener(e->resize(e));
    this.T.addOpenListener(new WindowAdapter() {
      @Override
      public void windowOpened(WindowEvent evt) {
        int id=L.size()-1;
        ((JRadioButtonMenuItem)L.get(id)).setSelected(true);
        setupComponents(id-1);
      }
    });
    this.T.addMouseSizeListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent evt) {
        int W=((Tool)evt.getSource()).getWidth();
        int H=((Tool)evt.getSource()).getHeight();
        T.updateLabels(W,H,true);
      }
      @Override
      public void componentMoved(ComponentEvent evt) {
        int X=((Tool)evt.getSource()).getX();
        int Y=((Tool)evt.getSource()).getY();
        T.updateLabels(X,Y,false);
      }
    });
  }

  private void resize(ActionEvent evt) {
    String cmd=evt.getActionCommand();
    if(cmd.equals("W+"))
      T.setMoreWidth();
    if(cmd.equals("W-"))
      T.setLessWidth();
    if(cmd.equals("H+"))
      T.setMoreHeight();
    if(cmd.equals("H-"))
      T.setLessHeight();
  }

  private void move(ActionEvent evt) {
    String cmd=evt.getActionCommand();
    if(cmd.equals("UP"))
      T.setMotionUp();
    if(cmd.equals("DOWN"))
      T.setMotionDown();
    if(cmd.equals("LEFT"))
      T.setMotionLeft();
    if(cmd.equals("RIGHT"))
      T.setMotionRight();
  }

  private void actions(ActionEvent evt) {
    int index=Integer.parseInt(((JRadioButtonMenuItem)evt.getSource()).getName());
    for(int i=0;i<L.size();i++) {
      if(L.get(i)instanceof javax.swing.JSeparator) {
        i++;
        ((JRadioButtonMenuItem)L.get(i)).setSelected(i==index+1);
      } else {
        ((JRadioButtonMenuItem)L.get(i)).setSelected(i==index);
      }
    }
    setupComponents(index);
  }

  private void setupComponents(int i) {
    T.setToolTitle(CB.getFrameTitle(i));
    T.setToolBounds(CB.getFrameGeometry(i));
    T.setPosInfo(CB.getFrameXY(i));
    T.setSizeInfo(CB.getFrameSize(i));
  }
}
