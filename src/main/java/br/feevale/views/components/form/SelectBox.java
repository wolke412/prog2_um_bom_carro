package br.feevale.views.components.form;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 * SelectBox
 */
public class SelectBox<K, T> extends JComboBox<K> implements FieldInterface<T> {

    // Pair usado pra Chave string -> o que deve ser retornado.

    // Nesta aplicação o Select será de Marcas;
    // O Select deve listar os nomes e na seleção deve retornar a devida 
    // marca.
    // Ex.: Para melhor comprensão veja as implementações
    ArrayList<Pair<K, T>> values;

    public SelectBox( ArrayList<Pair<K, T>> values ) {
        super();
       
        this.setValues(values);
    }

    public void setValues( ArrayList<Pair<K,T>> values ) {
        this.values = values;
        DefaultComboBoxModel<K> cbm = (DefaultComboBoxModel<K>) this.getModel();

        cbm.removeAllElements();

        for (Pair<K, T> value : values) {
            cbm.addElement(value.getL());
        }
    }

    @Override
    public T getValue() {
        return this.values.get(this.getSelectedIndex()).getR();
    }

    @Override
    public void setValue(T value) {
        int i = 0;
        for (i = 0; i < this.values.size(); i++) {
            if (this.values.get(i).getR() == value) {
                break;
            }
        }

        setSelectedIndex(i);
    }
    
}