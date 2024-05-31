package br.feevale.views;

import javax.swing.JFrame;
import javax.swing.JComponent;

public class Viewport extends JFrame {

    public Viewport(int width, int height, String title) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(width, height);
        setTitle(title);

        setResizable(true);
        setVisible(true);
    }

    public void add( JComponent c, Object cs) {
        getContentPane().add(c, cs);
    }
}
