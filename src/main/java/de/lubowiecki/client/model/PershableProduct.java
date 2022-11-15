package de.lubowiecki.client.model;

import java.io.Serializable;
import java.time.LocalDate;

public class PershableProduct extends Product implements Serializable {

	private final LocalDate expiryDate;

	public PershableProduct(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PershableProduct [expiryDate=");
		builder.append(expiryDate);
		builder.append(", name=");
		builder.append(getName());
		builder.append(", description=");
		builder.append(getDescription());
		builder.append(", price=");
		builder.append(getPrice());
		builder.append(", createdAt=");
		builder.append(getCreatedAt());
		builder.append("]");
		return builder.toString();
	}
}
