package de.lubowiecki.client.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import de.lubowiecki.client.App;
import de.lubowiecki.client.model.Product;
import de.lubowiecki.client.model.ProductDbRepository;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
	private TableView<Product> tblProducts;
	
	private ProductDbRepository management;

    @FXML
    private void save() {
    	Product product = new Product();
    	product.setName(name.getText());
    	product.setDescription(description.getText());
    	product.setAmount(Integer.parseInt(amount.getText()));
    	product.setPrice(Double.parseDouble(price.getText()));
    	management.save(product);
    	clearForm();
    	show();
    }
    
    private void show() {
		try {
			tblProducts.setItems(FXCollections.observableList(management.find()));
		} catch (SQLException e) {
			// TODO: Ausgabe in der Oberfläche
			throw new RuntimeException(e);
		}
	}
    
    @FXML
    private void switchToNext() throws IOException {
    	App.setRoot("next");
    }
    
    @FXML
    private void delete() {
    	// TODO: Exception fangen
    	Product p = tblProducts.getSelectionModel().getSelectedItem();
    	management.delete(p);
    	show();
    }
    
    private void clearForm() {
    	name.clear();
    	description.clear();
    	amount.clear();
    	price.clear();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			management = new ProductDbRepository();
		} catch (SQLException e) {
			// TODO: Ausgabe in der Oberfläche
			throw new RuntimeException(e);
		}

		show();
	}
}
