package br.feevale.views.components.forms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.function.Consumer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


import br.feevale.entities.Brand;
import br.feevale.entities.Vehicle;
import br.feevale.views.components.Button;
import br.feevale.views.components.form.Field;
import br.feevale.views.components.form.IForm;
import br.feevale.views.components.form.NumberInput;
import br.feevale.views.components.form.Pair;
import br.feevale.views.components.form.SelectBox;
import br.feevale.views.components.form.TextInput;
import br.feevale.views.pages.MainPage;

public class VehicleForm extends JDialog implements IForm<Vehicle> {

    private TextInput nameField;
    private NumberInput yearField;
    private NumberInput kmField;
    private NumberInput valuInCentsField;
    private SelectBox<String, Integer> brandSelectField;

    private JLabel title;

    private Button saveButton;
    
    public VehicleForm() {
        super();

        this.nameField = new TextInput();
        this.yearField = new NumberInput();
        this.kmField = new NumberInput();
        this.valuInCentsField = new NumberInput();
        this.brandSelectField = new SelectBox<String, Integer>(new ArrayList<>());
        
        this.setSize(300, 680);
        this.setResizable(true);
        this.setTitle("Adicionar um veículo");
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        this.setLocation(200, 200);

        JPanel formBody = new JPanel();

        // this.setLayout(new GridLayout(3, 1));
        formBody.setLayout(new BoxLayout(formBody, BoxLayout.Y_AXIS));
        formBody.setBorder(new EmptyBorder(new Insets(20, 20, 20, 20)));
        formBody.setBackground(MainPage.BACKGROUND_COLOR);

        JLabel title = new JLabel("Adicionar um veículo");
        Font titleFont = new Font("Fira Code", Font.BOLD, 24);

        title.setForeground(Color.white);
        title.setFont(titleFont);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        this.title = title;

        Box boxTitle = Box.createHorizontalBox();
        Box boxForm = Box.createHorizontalBox();
        Box boxButton = Box.createHorizontalBox();
        JPanel actualForm = new JPanel();

        boxTitle.setPreferredSize(new Dimension(400, 60));
        boxForm.setPreferredSize(new Dimension(400, 460));
        boxButton.setPreferredSize(new Dimension(400, 100));

        actualForm.setBackground(MainPage.BACKGROUND_COLOR);
        actualForm.add(new Field<>("Nome d veículo", nameField));
        actualForm.add(new Field<>("Ano de fabricação", yearField));
        actualForm.add(new Field<>("Kilometragem", kmField));
        actualForm.add(new Field<>("Valor (em cents)", valuInCentsField));
        actualForm.add(new Field<>("Marca", brandSelectField));
        
        // actualForm.add(new Field<>("País de origem", countryField));

        boxTitle.add(title);
        
        boxForm.add(actualForm);

        Button b = new Button("Salvar", "#44bb55");
        boxButton.add(b);

        formBody.add(boxTitle);
        formBody.add(actualForm);
        formBody.add(boxButton);

        this.getContentPane().add(formBody);

        this.saveButton = b;
    }

    public void onSave(Consumer<Vehicle> fn) {
        this.saveButton.onClick(() -> {
            fn.accept(this.getValues());

            this.dispose();
        });
    }

    @Override
    public Vehicle getValues() {

        return new Vehicle(
            -1,  // Id -1 pra identificar a invalidez - não deve ter ID nesta etapa
            nameField.getValue(), 
            yearField.getValue(), 
            kmField.getValue(), 
            valuInCentsField.getValue(), 
            brandSelectField.getValue()
        );
    }

    @Override
    public void setValues(Vehicle t) {
        this.nameField.setValue(t.getName());
        this.yearField.setValue(t.getYear());
        this.kmField.setValue(t.getKm());
        this.valuInCentsField.setValue(t.getValue_in_cents());
        this.brandSelectField.setValue(t.getId_brand());
    }

    @Override
    public void clear() {
        this.nameField.setValue("");
    }

    public void setFormTitle(String formTitle) {
        this.setTitle(formTitle);
        this.title.setText(formTitle);
    }

    public void updateBrands(ArrayList<Brand> brands) {
        ArrayList<Pair<String, Brand>> names = new ArrayList<>();

        brands.forEach((Brand b) -> {
            names.add(new Pair<String, Brand>(b.getName(), b));
        });
        
        this.brandSelectField.setValues(names);
    }
}


        