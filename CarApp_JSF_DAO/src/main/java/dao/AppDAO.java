package dao;

import java.util.List;

public interface AppDAO<T> {

	public List<T> findAll();
	public T findOneById();
	public List<T> findInventoryByRentedStatus(int rented);
	public void update(int id, int rented);
	public void save(T item);
	public void updateInventory(int id, int rented);
	public void saveInventory(String name, String brand, double price,int rented);
	
}
