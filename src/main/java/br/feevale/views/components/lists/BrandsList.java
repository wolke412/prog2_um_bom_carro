package br.feevale.views.components.lists;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import br.feevale.entities.Brand;
import br.feevale.models.BrandsModel;
import br.feevale.views.components.Button;
import br.feevale.views.components.Table;
import br.feevale.views.components.forms.BrandForm;
import br.feevale.views.pages.MainPage;

public class BrandsList extends JPanel {

    private BrandsModel model; 
    private ArrayList<Brand> brands;

    private Table TableMarcas;

    private BrandForm brandForm;

    /**
     * Selected BrandId
     */
    private int selectedIndex;

    public BrandsList( BrandsModel m ) {

        this.model = m;

        this.brands = new ArrayList<>();
       
        this.brandForm = new BrandForm();

        
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(20, 20, 20, 20));

        this.setBackground(MainPage.BACKGROUND_COLOR);

        Box buttons = Box.createHorizontalBox();

        buttons.setAlignmentX(LEFT_ALIGNMENT);
        buttons.setBorder(new EmptyBorder(0,0,0,0));

        Button addButton  = new Button("Adicioanar marca", "#44bb55");
        Button editButton = new Button("Editar marca", "#FFA500");
        Button delButton  = new Button("Remover marca", "#ca4544");

        addButton.onClick(() -> { 
            if ( this.brandForm.isVisible() ) {
                System.out.println("O modal já está aberto.");
                return;
            }
            this.brandForm.clear();
            this.brandForm.onSave((Brand b) -> {this.create(b);} );

            this.brandForm.setFormTitle("Adicionar marca");
            this.brandForm.setVisible(true);
        });

        editButton.onClick(() -> {
            if ( this.brandForm.isVisible() ) {
                System.out.println("O modal já está aberto.");
                return;
            }
            
            if ( this.selectedIndex == -1 ) {
                System.out.println("Uma linha deve ser selecionada.");
                return; 
            }

            this.brandForm.setFormTitle("Editar marca");
            this.brandForm.setValues(this.brands.get(selectedIndex));
            this.brandForm.onSave( (Brand b) -> { this.edit(b); } );

            this.brandForm.setVisible(true);
        });

        delButton.onClick(() -> {
            if ( selectedIndex == -1 ) {
                return;
            }
            
            Object[] options = { "Confirmar", "Cancelar" };
            int res = JOptionPane.showOptionDialog(
                null, 
                "Você realmente deseja remover " + this.brands.get(selectedIndex).getName(), 
                "Confirmação", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]
            );
            
            if (res == 0) {
                this.removeBrand();
            }
        });

        buttons.setBackground(MainPage.BACKGROUND_COLOR);
        buttons.add(addButton);
        buttons.add(Box.createRigidArea(new Dimension(10,0)));
        buttons.add(editButton);
        buttons.add(Box.createRigidArea(new Dimension(10,0)));
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

                BrandsList.this.selectedIndex = srow;
            }
        });

        JScrollPane s = new JScrollPane(this.TableMarcas);

        // Maracutaia pra criar espaçamento entre cabeçalho e tabela
        // usando BorderBox como layout manger.
        s.setBorder(BorderFactory.createMatteBorder(20, 0, 0, 0, MainPage.BACKGROUND_COLOR));
        s.getViewport().setBackground(MainPage.BACKGROUND_COLOR);

        Box titleBox = Box.createVerticalBox();

        JLabel header = new JLabel("Marcas");
        header.setFont(new Font( "Fira Code", Font.BOLD, 24 ) );
        header.setForeground(Color.WHITE);

        titleBox.add(header);
        titleBox.add(Box.createRigidArea(new Dimension(0, 10)));
        titleBox.add(buttons);

        titleBox.setPreferredSize(new Dimension(200, 80));
        titleBox.setAlignmentX(LEFT_ALIGNMENT);
        // titleBox.add(Box.createRigidArea(new Dimension(0, 10)));

        this.add(titleBox, BorderLayout.NORTH);
        this.add(s, BorderLayout.CENTER);

        updateRows();
    }
    
    public void create(Brand b) {
        
        if ( model.insertOne(b) ) {
            this.updateRows();
            return;
        };

        System.out.println("Erro adicionando marca.");
    }

    public void edit(Brand b) {
        // A Marca vinda do form não contém Id
        b.setId(brands.get(selectedIndex).getId());

        if ( model.updateOne(b) ) {
            this.updateRows();
            return;
        };

        System.out.println("Erro adicionando marca.");
    }

    public void removeBrand() {
        this.model.deleteOne( brands.get(selectedIndex) );
        this.updateRows();
    }

    public void updateRows() {
        
        // Query rows;
        this.brands = this.model.selectAll(); 

        /**
         * update graphics
         */
        DefaultTableModel dm = (DefaultTableModel) TableMarcas.getModel();

        dm.setRowCount(0);

        this.brands.forEach((Brand b) -> {
            dm.addRow(new Object[]{b.getName(), b.getCountry()});
        }); 
    }


}
