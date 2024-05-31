package br.feevale.views.components;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class Row extends JPanel{
   public Row() {
      super();    
      this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
   } 
}
