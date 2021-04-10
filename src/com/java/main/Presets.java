/**
* Developed by Jhonny Trejos Barrios.
* Technology: Java.
* Version: Java Development Kit 11.0.4 LTS, Standard Edition.
* Development Environment: Sublime Text 3 [build 3211].
* Date: 05/04/20, Time: 20:23:30.
*
* Additional Info.
*
* Source Code Target Or Details:
*
*     [Main class, application launcher]
*
* Licenses: GNU GPL v3.0, Eclipse Public License 1.0, personal use only.
* Developer Contact: jtrejosb@live.com
* GitHub.com/jtrejosb
*/
package com.java.main;

import com.java.controller.ToolController;
import com.java.view.CustomBar;
import com.java.view.Tool;

public class ToolMain {
  public static void main(String[] args) {
    CustomBar CB=new CustomBar();
    Tool T=new Tool();
    new ToolController(CB,T);
    T.setVisible(true);
  }
}
