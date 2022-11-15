package de.lubowiecki.client.model;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Model
public class ProductRepository {
	
	private static final String FILE_NAME = "data.ser";
	
	private List<Product> products;
	
	public ProductRepository() {
		readFromFile();
	}
	
	public void add(Product product) {
		products.add(product);
		saveToFile();
	}
	
	public List<Product> getAll() {
		//return products;
		return Collections.unmodifiableList(products); // Es wird nicht die Originalliste, sondern eine nuveränderbare Kopie wietergegeben
	}
	
	// IO, NIO
	// IO: Strings als Dateinamen oder File-Objekte
	// NIO: Strings als Dateinamen oder Path-Objekte
	
	private void readFromFile() {
		
		// Einheitlichen Standard wie z.B. XML, JSON oder CSV verwenden
		
		// Resource muss das Interface AutoClosable implementieren
		try(ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(FILE_NAME)))) { ;
			products = (List<Product>) in.readObject();
			// in wird automatisch geschlossen
		}
		catch(Exception e) {
			// ...oder eine neue leere Liste gebaut
			products = new ArrayList<>();
		}
	}
	
	private void saveToFile() {
		
		// Einheitlichen Standard wie z.B. XML, JSON oder CSV verwenden
		
		try(ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(FILE_NAME)))) {;
			out.writeObject(products);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(Product p) {
		products.remove(p);
		saveToFile();
	}
	
}
