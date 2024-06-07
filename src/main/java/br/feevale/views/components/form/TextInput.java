package br.feevale.views.components.form;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.feevale.views.pages.MainPage;

public class TextInput extends JTextField implements FieldInterface<String> {


    public TextInput() {
        super();

        // setBorder(new LineBorder(Color.black, 1));

        this.setColumns(10);
        this.setBorder(new EmptyBorder(new Insets(0, 0, 0, 0))); // this.setBackground(Color.BLACK);
        
        this.setForeground(Color.WHITE);
        this.setBackground(MainPage.BG_300);
        this.setFont(new Font("Fira Code", Font.BOLD, 18));

        this.setSize(400, 100);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 24);
    }
    @Override
    public String getValue() {
        return this.getText().trim();
    }

    @Override
    public void setValue(String value) {
        this.setText(value.trim());
    }
}