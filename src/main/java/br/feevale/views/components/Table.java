package br.feevale.views.components;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Table extends JTable {

    public static DefaultTableModel Model(String ...cols) {
        DefaultTableModel dtm = new DefaultTableModel();
        
        for (String col : cols) {
            dtm.addColumn(col);
        }
    
       return dtm;
    }

    public Table (String ...cols) {
        
        super(Table.Model(cols));

        this.setBorder(null);
        this.setBackground(Color.decode("#222434"));
        this.setForeground(Color.decode("#FFFFFF"));

        this.setCellsDefaultStyle();
        
        this.setIntercellSpacing(new Dimension(10, 10));
        this.setRowHeight(36);
        this.setShowGrid(false);
        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        this.setHeaderDefaultStyles();

        // List selection behaviour
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setRowSelectionAllowed(true);
        this.setCellSelectionEnabled(false);

        this.setSelectionBackground(Color.BLUE);
    }
    
    private void setHeaderDefaultStyles() {
        JTableHeader header = this.getTableHeader();
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
       
        renderer.setForeground(Color.decode("#FFFFFF"));
        renderer.setBackground(Color.decode("#121424"));
        renderer.setPreferredSize(new Dimension(0, 36));
        renderer.setHorizontalAlignment(JLabel.CENTER);

        // renderer.setBorder(new EmptyBorder(10,10, 10, 10));
        header.setDefaultRenderer(renderer);
    }

    private void setCellsDefaultStyle() {

    }


}
