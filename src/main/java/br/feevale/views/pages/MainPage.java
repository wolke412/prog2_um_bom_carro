package br.feevale.views.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import br.feevale.PostgresClient;
import br.feevale.models.BrandsModel;
import br.feevale.models.VehiclesModel;
import br.feevale.views.Viewport;
import br.feevale.views.components.lists.BrandsList;
import br.feevale.views.components.lists.VehiclesList;

public class MainPage extends Viewport {

    public static final Color BG_500 = Color.decode("#121424");
    public static final Color BG_400 = Color.decode("#2A2C3C");
    public static final Color BG_300 = Color.decode("#222434");
    public static final Color BG_100 = Color.decode("#2A2C3C");

    public static final Color BACKGROUND_COLOR = MainPage.BG_100;

    public MainPage(PostgresClient client) {

        super( 800, 800, "Sistema de gerenciamento de Veículos" );

        Container c = this.getContentPane();

        c.setBackground(BG_300);
        c.setLayout(new BorderLayout());
        
        JTabbedPane jtp = new JTabbedPane();

        jtp.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        BrandsModel bm   = new BrandsModel(client.getConnection());
        VehiclesModel vm = new VehiclesModel(client.getConnection());

        jtp.addTab("Marcas",   new BrandsList(bm));
        jtp.addTab("Veículos", new VehiclesList(bm, vm));

        JLabel title = new JLabel("Sistema de gerenciamento de veículos");
        
        title.setFont(new Font("Fira Code", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(CENTER_ALIGNMENT);

        c.add(jtp, BorderLayout.CENTER);

        this.init();
    }

}