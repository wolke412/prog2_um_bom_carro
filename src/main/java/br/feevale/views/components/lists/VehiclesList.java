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

import br.feevale.entities.Vehicle;
import br.feevale.models.BrandsModel;
import br.feevale.models.VehiclesModel;
import br.feevale.views.components.Button;
import br.feevale.views.components.Table;
import br.feevale.views.components.forms.VehicleForm;
import br.feevale.views.pages.MainPage;

public class VehiclesList extends JPanel {

    private BrandsModel brandsModel; 
    private VehiclesModel vehiclesModel;

    private ArrayList<Vehicle> vehicles;

    private Table TableVeiculos;

    private VehicleForm vehicleForm;

    /**
     * Selected BrandId
     */
    private int selectedIndex;

    public VehiclesList( BrandsModel bm, VehiclesModel vm ) {

        this.brandsModel   = bm;
        this.vehiclesModel = vm;

        this.vehicles = new ArrayList<>();
       
        this.vehicleForm = new VehicleForm();

        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(20, 20, 20, 20));

        this.setBackground(MainPage.BACKGROUND_COLOR);

        Box buttons = Box.createHorizontalBox();

        buttons.setAlignmentX(LEFT_ALIGNMENT);
        buttons.setBorder(new EmptyBorder(0,0,0,0));

        Button addButton  = new Button("Adicionar veículo", "#44bb55");
        Button editButton = new Button("Editar veículo", "#FFA500");
        Button delButton  = new Button("Remover veículo", "#ca4544");

        addButton.onClick(() -> { 
            if ( this.vehicleForm.isVisible() ) {
                System.out.println("O modal já está aberto.");
                return;
            }
            this.vehicleForm.clear();
            this.vehicleForm.onSave((Vehicle v) -> {this.create(v);} );

            this.vehicleForm.setFormTitle("Adicionar veículo");
            this.vehicleForm.updateBrands( this.brandsModel.selectAll() );
            this.vehicleForm.setVisible(true);
        });

        editButton.onClick(() -> {
            if ( this.vehicleForm.isVisible() ) {
                System.out.println("O modal já está aberto.");
                return;
            }
            
            if ( this.selectedIndex == -1 ) {
                System.out.println("Uma linha deve ser selecionada.");
                return; 
            }

            this.vehicleForm.setFormTitle("Editar veículo");
            this.vehicleForm.setValues(this.vehicles.get(selectedIndex));
            this.vehicleForm.onSave( (Vehicle v) -> { this.edit(v); } );
            
            this.vehicleForm.updateBrands( this.brandsModel.selectAll() );

            this.vehicleForm.setVisible(true);
        });

        delButton.onClick(() -> {
            if ( selectedIndex == -1 ) {
                return;
            }
            
            Object[] options = { "Confirmar", "Cancelar" };
            int res = JOptionPane.showOptionDialog(
                null, 
                "Você realmente deseja remover " + this.vehicles.get(selectedIndex).getName(), 
                "Confirmação", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]
            );
            
            if (res == 0) {
                this.removeVehicle();
            }
        });

        buttons.setBackground(MainPage.BACKGROUND_COLOR);
        buttons.add(addButton);
        buttons.add(Box.createRigidArea(new Dimension(10,0)));
        buttons.add(editButton);
        buttons.add(Box.createRigidArea(new Dimension(10,0)));
        buttons.add(delButton);

        // os botoes editar e deletar são ocultos inicilamente
        editButton.setVisible(false);
        delButton.setVisible(false);

        this.TableVeiculos = new Table("Nome", "Marca", "Ano", "Kilometragem", "Valor");
        this.TableVeiculos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                int srow = TableVeiculos.getSelectedRow();

                editButton.setVisible(srow != -1);
                delButton.setVisible(srow != -1);

                VehiclesList.this.selectedIndex = srow;
            }
        });

        JScrollPane s = new JScrollPane(this.TableVeiculos);

        // Maracutaia pra criar espaçamento entre cabeçalho e tabela
        // usando BorderBox como layout manger.
        s.setBorder(BorderFactory.createMatteBorder(20, 0, 0, 0, MainPage.BACKGROUND_COLOR));
        s.getViewport().setBackground(MainPage.BACKGROUND_COLOR);

        Box titleBox = Box.createVerticalBox();

        JLabel header = new JLabel("Veículos");
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
    
    public void create(Vehicle v) {
        
        if ( vehiclesModel.insertOne(v) ) {
            this.updateRows();
            return;
        };

        System.out.println("Erro adicionando marca.");
    }

    public void edit(Vehicle v) {
        // A Marca vinda do form não contém Id
        v.setId(vehicles.get(selectedIndex).getId());

        if ( vehiclesModel.updateOne(v) ) {
            this.updateRows();
            return;
        };

        System.out.println("Erro adicionando marca.");
    }

    public void removeVehicle() {
        this.vehiclesModel.deleteOne( vehicles.get(selectedIndex) );
        this.updateRows();
    }

    public void updateRows() {
        
        // Query rows;
        this.vehicles = this.vehiclesModel.selectAll(); 

        /**
         * update graphics
         */
        DefaultTableModel dm = (DefaultTableModel) TableVeiculos.getModel();

        dm.setRowCount(0);

        this.vehicles.forEach((Vehicle v) -> {
            dm.addRow(new Object[]{
                v.getName(), 
                v.getJoinNameMarca(),
                v.getYear(),
                v.getKm() + " km",
                "R$ " + ( v.getValue_in_cents() / 100 ),
            });
        }); 
    }


}
