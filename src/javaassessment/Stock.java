package javaassessment;

import java.util.ArrayList;

public class Stock {
	int id, quant;
	float price;
	String name;

	Stock(int id, String name, String price, int quant) {
		this.id = id;
		this.name = name;
		this.price = Float.parseFloat(price);
		this.quant = quant;
	}

}
