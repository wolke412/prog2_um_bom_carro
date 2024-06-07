package br.feevale.views.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;


public class Button extends JButton {
    
    private Color defaultColor;
    private Color activeColor;

    private MouseAdapter mouseAdapter;

    public Button(String name, String color) {
        super(name);
        this.defaultColor = Color.decode(color);
        this.activeColor = Color.decode(color).darker();

        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // padding
        this.setBorder(new EmptyBorder(new Insets(8, 16, 8 ,16)));
        this.setOpaque(true);
        this.setBackground(UIManager.getColor("Button.background")); // Set default background color
        this.setBackground(defaultColor);
        this.setForeground(Color.white);
    }

    public void onClick(Runnable onClick) {
        if ( this.mouseAdapter != null) {
            this.removeMouseListener(this.mouseAdapter); 
        }

        this.mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent arg0) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                onClick.run();
            }
            public void mouseEntered(MouseEvent e) {
            }
            public void mouseExited(MouseEvent e) {}
        };

        this.addMouseListener(this.mouseAdapter);
    }
}