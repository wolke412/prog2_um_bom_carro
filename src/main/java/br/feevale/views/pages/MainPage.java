package br.feevale.views.pages;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import br.feevale.entities.Brand;
import br.feevale.entities.Country;
import br.feevale.entities.Vehicle;
import br.feevale.views.Viewport;
import br.feevale.views.components.Row;
import br.feevale.views.components.Button;
import br.feevale.views.components.Table;

public class MainPage extends Viewport {

    private Table TableMarcas;

    private JDialog FormEdit;
    private JDialog FormNovo;

    private ArrayList<Country> countries;
    private ArrayList<Brand> brands;
    private ArrayList<Vehicle> vehicles;

    private final Color backgroundColor = Color.decode("#2A2C3C");

    public MainPage() {

        super( 800, 800, "Sistema de gerenciamento de Veículos" );

        Container c = this.getContentPane();

        c.setBackground(this.backgroundColor);
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));

        JTabbedPane jtp = new JTabbedPane();

        jtp.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel marcas   = this.setMarcasPane();
        JComponent veiculos = new JPanel();

        jtp.addTab("Marcas", marcas);
        jtp.addTab("Veículos", veiculos);

        jtp.setMnemonicAt(0, KeyEvent.VK_1);
        jtp.setMnemonicAt(1, KeyEvent.VK_2);

        // c.add();
        c.add(jtp);
    }

    private JPanel setMarcasPane () {
        JPanel panel = new JPanel();
        
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.setBackground(backgroundColor);

        Row buttons = new Row();

        buttons.setAlignmentX(LEFT_ALIGNMENT);
        buttons.setBorder(new EmptyBorder(0,0,0,0));

        Button addButton  = new Button("Adicioanar marca", "#44bb55");
        Button editButton = new Button("Editar marca", "#FFA500");
        Button delButton  = new Button("Remover  marca", "#ca4544");

        addButton.onClick(() -> { System.out.println("te amoa mor"); });

        addButton.setForeground(Color.WHITE);
        editButton.setForeground(Color.WHITE);
        delButton.setForeground(Color.WHITE);

        buttons.add(addButton);
        buttons.add(editButton);
        buttons.add(delButton);

        editButton.setVisible(false);
        delButton.setVisible(false);

        this.TableMarcas = new Table("Nome", "País de origem");
        this.TableMarcas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {

                int srow = TableMarcas.getSelectedRow();
                
                editButton.setVisible(srow != -1);
                delButton.setVisible(srow != -1);
                // System.out.println(TableMarcas.getValueAt(TableMarcas.getSelectedRow(), 0).toString()); 
            }
        });

        JScrollPane s = new JScrollPane(this.TableMarcas);
        s.setBorder(new LineBorder(Color.RED, 0));
        s.getViewport().setBackground(backgroundColor);

        JLabel header = new JLabel("Marcas");
        header.setFont(new Font( "Fira Code", Font.BOLD, 24));
        header.setForeground(Color.WHITE);
        panel.add(header);

        // panel.add(new Field("Name", new SelectBox<>( (ArrayList)List.of("Oi", "Tchau") )));
        // panel.add(new SelectBox<String>(null));

        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(buttons);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(s);

        DefaultTableModel dm = (DefaultTableModel) TableMarcas.getModel();
        dm.addRow(new Object[]{ "BMW", "Alemanha" });
        dm.addRow(new Object[]{ "Mercedes", "Alemanha" });
        dm.addRow(new Object[]{ "BYD", "China" });

        return panel;
    }

    private JPanel setVeiculosPanel() {
        JPanel panel = new JPanel();
        return panel; 
    }
}