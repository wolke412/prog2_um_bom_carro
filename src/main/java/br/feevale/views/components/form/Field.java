package br.feevale.views.components.form;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.sound.sampled.Line;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import br.feevale.views.components.Column;
import br.feevale.views.pages.MainPage;

/**
 * Field
 */
public class Field< T extends Component & FieldInterface<K>, K> extends Column {

    JLabel label;
    T field; 

    public Field( String label, T fieldComponent ) {
        this.label = new JLabel( label );
        this.field = fieldComponent;

        this.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setBackground(MainPage.BG_500);

        this.label.setForeground(Color.decode("#CED0DF"));
        this.label.setFont(new Font( "Fira Code", Font.PLAIN, 14 ) );

        this.label.setHorizontalAlignment(SwingConstants.LEFT);

        Box labelBox = Box.createHorizontalBox();
        Box inputBox = Box.createHorizontalBox();
        
        labelBox.setAlignmentX(LEFT_ALIGNMENT);
        labelBox.setPreferredSize(new Dimension(160, 20));
        inputBox.setPreferredSize(new Dimension(160, 20));
        
        labelBox.add(this.label); 
        inputBox.add(this.field); 
        
        this.add(labelBox);
        this.add(Box.createRigidArea( new Dimension(0, 10) ));
        this.add(inputBox);

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Field.this.field.requestFocus();
            }
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
        });
        this.field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                Field.this.setBackground(MainPage.BG_300);
            }

            @Override
            public void focusLost(FocusEvent e) {
                Field.this.setBackground(MainPage.BG_500);
            }
        });
    }   
    
}