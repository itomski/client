package de.lubowiecki.client.model;

import java.io.Serializable;
import java.time.LocalDate;

import de.lubowiecki.client.utils.ViewHelper;

// Model
public class Product implements Serializable {
	
	private static final long serialVersionUID = 7273045516007784747L;
	
	private String name;
	private String description;
	private int amount;
	private double price;
	private LocalDate createdAt; // YYYY-MM-dd

	public Product() {
		createdAt = LocalDate.now();
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}
	
	public String getPriceDE() {
		return String.format("%.2f", price);
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}
	
	public String getCreatedAtDE() {
		return createdAt.format(ViewHelper.DATE_FMT);
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Product [name=")
			.append(name)
			.append(", description=")
			.append(description)
			.append(", amount=")
			.append(amount)
			.append(", price=")
			.append(price)
			.append(", createdAt=")
			.append(createdAt)
			.append("]");
		
		return builder.toString();
	}	
}
