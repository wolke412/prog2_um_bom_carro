package br.feevale.views.components.forms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


import br.feevale.entities.Brand;
import br.feevale.views.components.Button;
import br.feevale.views.components.form.Field;
import br.feevale.views.components.form.IForm;
import br.feevale.views.components.form.TextInput;
import br.feevale.views.pages.MainPage;

public class BrandForm extends JDialog implements IForm<Brand> {

    private TextInput nameField;
    private TextInput countryField;

    private JLabel title;

    private String buttonText; // unused

    private Button saveButton;
    
    public BrandForm() {
        super();

        this.nameField = new TextInput();
        this.countryField = new TextInput();

        this.setSize(300, 400);
        this.setResizable(true);
        this.setTitle("Adicionar uma marca");
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        this.setLocation(200, 200);

        JPanel formBody = new JPanel();

        // this.setLayout(new GridLayout(3, 1));
        formBody.setLayout(new BoxLayout(formBody, BoxLayout.Y_AXIS));
        formBody.setBorder(new EmptyBorder(new Insets(20, 20, 20, 20)));
        formBody.setBackground(MainPage.BACKGROUND_COLOR);

        JLabel title = new JLabel("Adicionar uma Marca");
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
        boxForm.setPreferredSize(new Dimension(400, 400));
        boxButton.setPreferredSize(new Dimension(400, 100));

        actualForm.setBackground(MainPage.BACKGROUND_COLOR);
        actualForm.add(new Field<>("Nome da marca", nameField));
        actualForm.add(new Field<>("País de origem", countryField));

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

    public void onSave(Consumer<Brand> fn) {
        this.saveButton.onClick(() -> {
            fn.accept(this.getValues());

            this.dispose();
        });
    }

    @Override
    public Brand getValues() {
        return new Brand( this.nameField.getValue(), this.countryField.getValue() );
    }

    @Override
    public void setValues(Brand t) {
        this.nameField.setValue(t.getName());
        this.countryField.setValue(t.getCountry());
    }

    @Override
    public void clear() {
        this.nameField.setValue("");
        this.countryField.setValue("");
    }

    public void setFormTitle(String formTitle) {
        this.setTitle(formTitle);
        this.title.setText(formTitle);
    }
}
