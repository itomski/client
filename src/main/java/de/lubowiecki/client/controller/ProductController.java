package de.lubowiecki.client.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.Locale;
import java.util.ResourceBundle;

import de.lubowiecki.client.App;
import de.lubowiecki.client.model.Product;
import de.lubowiecki.client.model.ProductDbRepository;

import de.lubowiecki.client.utils.ValueUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;

public class ProductController implements Initializable { // Initializable bietet die Möglichkeit Tätigkeiten beim Starten der GUI auszuführen
	
	@FXML
	private TextField name;
	
	@FXML
	private TextArea description;
	
	@FXML
	private TextField amount;

	@FXML
	private TextField price;

	@FXML
	private ComboBox<Locale> langSwitch;
	
	@FXML
	private TableView<Product> tblProducts;

	@FXML
	private TableColumn<Product, String> colPrice;

	@FXML
	private TableColumn<Product, Integer> colCount;

	private ProductDbRepository management;

	private int count = 0;

    @FXML
    private void insert() {
    	save(true);
	}

	@FXML
	private void update() {
		save(false);
	}

	private void save(boolean insert) {
		Product product = null;
		if(insert) {
			product = new Product();
		}
		else {
			product = tblProducts.getSelectionModel().getSelectedItem();
		}
		product.setName(name.getText());
		product.setDescription(description.getText());
		product.setAmount(Integer.parseInt(amount.getText()));
		product.setPrice(Double.parseDouble(price.getText()));

		try {
			management.save(product);
			clearForm();
			show();
		}
		catch (SQLException e) {
			// TODO: Ausgabe in der Oberfläche
			e.printStackTrace();
		}
	}

    private void show() {
		count = 0;
		try {
			tblProducts.setItems(FXCollections.observableList(management.find()));
		}
		catch (SQLException e) {
			// TODO: Ausgabe in der Oberfläche
			e.printStackTrace();
		}
	}

	@FXML
	public void showInForm() {
		Product p = tblProducts.getSelectionModel().getSelectedItem();
		if(p != null) {
			name.setText(p.getName());
			description.setText(p.getDescription());
			amount.setText(p.getAmount() + "");
			price.setText(p.getPrice() + "");
		}
	}

	@FXML
	public void changeLang() throws IOException {
		Locale selection = langSwitch.getSelectionModel().getSelectedItem();
		Locale.setDefault(selection); // Default Sprache ändern
		App.setRoot("controller/standard"); // Fenster neuladen
	}
    
    @FXML
    private void switchToNext() throws IOException {
    	App.setRoot("next");
    }
    
    @FXML
    private void delete() {
    	Product p = tblProducts.getSelectionModel().getSelectedItem();
    	try {
			management.delete(p);
			show();
		}
		catch (SQLException e) {
			// TODO: Ausgabe in der Oberfläche
			e.printStackTrace();
		}
    }
    
    private void clearForm() {
    	name.clear();
    	description.clear();
    	amount.clear();
    	price.clear();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		colPrice.setComparator(ValueUtils.DOUBLE_COMP);

		ObservableList<Locale> languages = FXCollections.observableArrayList(Locale.ENGLISH, Locale.GERMAN, Locale.FRENCH);
		langSwitch.setItems(languages);

		StringConverter<Locale> converter = new StringConverter<Locale>() {

			@Override
			public String toString(Locale loc) {
				return loc.getDisplayLanguage();
			}

			@Override
			public Locale fromString(String string) {
				return null;
			}
		};

		langSwitch.setConverter(converter);
		langSwitch.getSelectionModel().select(Locale.getDefault());


		try {
			management = ProductDbRepository.get();
		} catch (SQLException e) {
			// TODO: Ausgabe in der Oberfläche
			throw new RuntimeException(e);
		}

		show();
	}
}
