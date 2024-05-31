package br.feevale.views.components.form;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JLabel;

import br.feevale.views.components.Column;

/**
 * Field
 */
public class Field< T extends Component & FieldInterface<K>, K > extends Column {

    JLabel label;
    T field; 

    public Field( String label, T fieldComponent ) {
        this.label = new JLabel( label );
        this.field = fieldComponent;

        this.add(this.label);
        this.add(Box.createRigidArea( new Dimension(0, 10) ));
        this.add(this.field);
    }   
    
}