package br.feevale.views.components;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class Column extends JPanel{
   public Column() {
      super();    
      this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
   } 
}
