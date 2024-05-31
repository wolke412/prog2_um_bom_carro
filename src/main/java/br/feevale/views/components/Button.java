package br.feevale.views.components;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;


public class Button extends JButton {
    
    private Color defaultColor;
    private Color activeColor;

    public Button(String name, String color) {
        super(name);
        this.defaultColor = Color.decode(color);
        this.activeColor = Color.decode(color).darker();

        
        // padding
        this.setBorder(new EmptyBorder(new Insets(8, 16, 8 ,16)));
        this.setOpaque(true);
        this.setBackground(UIManager.getColor("Button.background")); // Set default background color
        this.setBackground(defaultColor);
}

    public void onClick(Runnable onClick) {

        this.addMouseListener(new MouseAdapter() {

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
        });

        /**
        this.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
            }
            
        } );
        **/
    }


    
}