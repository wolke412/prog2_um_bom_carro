package br.feevale.views.components.form;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.feevale.views.pages.MainPage;

public class NumberInput extends JTextField implements FieldInterface<Integer> {


    public NumberInput() {
        super();

        // setBorder(new LineBorder(Color.black, 1));

        this.setColumns(10);
        this.setBorder(new EmptyBorder(new Insets(0, 0, 0, 0))); // this.setBackground(Color.BLACK);
        
        this.setForeground(Color.WHITE);
        this.setBackground(MainPage.BG_300);
        this.setFont(new Font("Fira Code", Font.BOLD, 18));

        this.setSize(400, 100);

        super.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
            @Override
            public void keyTyped(KeyEvent e) {
                // strips charcter if is not digit
                if ( ! Character.isDigit(e.getKeyChar())) {
                    String t = NumberInput.this.getText();
                    NumberInput.this.setText(
                        t.substring(0, t.length() - 1)
                    );  
                }
            }
        });
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 24);
    }
    @Override
    public Integer getValue() {
        int r = 0;
        try {
            r = Integer.parseInt(this.getText());   
        }
        catch(NumberFormatException e) {
            System.out.println("Erro parsificando :" + this.getText() + ". " + e);
        }

        return r;
    }

    @Override
    public void setValue(Integer value) {
        this.setText(Integer.toString(value));
    }
}