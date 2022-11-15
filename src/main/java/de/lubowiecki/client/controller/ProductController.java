package de.lubowiecki.client.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import de.lubowiecki.client.App;
import de.lubowiecki.client.model.Product;
import de.lubowiecki.client.model.ProductRepository;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
	
	private ProductRepository management = new ProductRepository();

    @FXML
    private void save() throws IOException {
    	Product product = new Product();
    	product.setName(name.getText());
    	product.setDescription(description.getText());
    	product.setAmount(Integer.parseInt(amount.getText()));
    	product.setPrice(Double.parseDouble(price.getText()));
    	management.add(product);
    	clearForm();
    	show();
    }
    
    private void show() {
    	tblProducts.setItems(FXCollections.observableList(management.getAll()));
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
		show();
	}
}
