package model;

public class Inventory extends BaseEntity{
	
	private int rented;
	private double price;
	private String brand, name;

	public Inventory() {
		// TODO Auto-generated constructor stub
	}


	public Inventory(int id, String name, String brand, double price,  int rented) {
		super(id);
		this.name = name;
		this.rented = rented;
		this.price = price;
		this.brand = brand;
		
	}

	@Override
	public String toString() {
		return brand + " " + price +" ";
	}


	public int getRented() {
		return rented;
	}

	public void setRented(int rented) {
		this.rented = rented;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}




	
}