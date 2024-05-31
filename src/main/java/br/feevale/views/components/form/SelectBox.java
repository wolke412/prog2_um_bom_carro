package br.feevale.views.components.form;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 * SelectBox
 */
public class SelectBox<T> extends JComboBox<T> implements FieldInterface<T> {

    ArrayList<T> values;

    public SelectBox( ArrayList<T> values ) {
        super();
       
        this.setValues(values);
    }

    public void setValues( ArrayList<T> values ) {
        this.values = values;
        DefaultComboBoxModel<T> cbm = (DefaultComboBoxModel<T>) this.getModel();

        cbm.removeAllElements();

        for (T value : values) {
            cbm.addElement(value);
        }
    }

    @Override
    public T getValue() {
        return this.values.get(this.getSelectedIndex());
    }

    @Override
    public void setValue(T value) {
        int i = 0;
        for (i = 0; i < this.values.size(); i++) {
            if (this.values.get(i) == value) {
                break;
            }
        }

        setSelectedIndex(i);
    }
    
}