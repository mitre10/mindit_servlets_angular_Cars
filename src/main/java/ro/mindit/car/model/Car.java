package ro.mindit.car.model;


import java.io.Serializable;

public class Car implements Serializable {
	private int id;
	private String name;
	private String type;
	private String fuel;
	private int price;

	public Car() {

	}

	public Car(int id, String name, String type, String fuel, int price) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.fuel = fuel;
		this.price = price;
	}

	public Car(String name, String type, String fuel, int price) {
		this.name = name;
		this.type = type;
		this.fuel = fuel;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
