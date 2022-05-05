package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;

import dao.AppDAO;
import model.Inventory;

@SessionScoped
public class AppDaoImpl implements AppDAO<Inventory>, Serializable {

	
	
	@Override
	public List<Inventory> findInventoryByRentedStatus(int rented) {
	
		List<Inventory> cars = new ArrayList<>();
		
		try (Connection conn = ConnectionManager.getConnection()){
			
			String query = "select * from inventory where rented=?";
			PreparedStatement psmt = conn.prepareStatement(query);
			psmt.setInt(1, rented);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				cars.add(new Inventory(rs.getInt("id"),rs.getString("name"), 
						rs.getString("brand"), 
						rs.getDouble("price"),rs.getInt("rented")));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return cars;
		
		
	}

	@Override
	public void updateInventory(int id, int rented) {
			try (Connection conn = ConnectionManager.getConnection()){
			
			String query = "update inventory set rented=? where id=?";
			PreparedStatement psmt = conn.prepareStatement(query);
			psmt.setInt(1, rented);
			psmt.setInt(2, id);
			psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

	
	@Override
	public void saveInventory(String name, String brand, double price, int rented) {
			try (Connection conn = ConnectionManager.getConnection()){
			
			String query = "insert into inventory (name,brand,price,rented) values(?,?,?,?)";
			PreparedStatement psmt = conn.prepareStatement(query);
			psmt.setString(1, name);
			psmt.setString(2, brand);
			psmt.setDouble(3, price);
			psmt.setInt(4, rented);
			psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

	@Override
	public List<Inventory> findAll() {
		List<Inventory> inventory = new ArrayList<>();

		try (Connection conn = ConnectionManager.getConnection()) {

			String query = "select * from inventory";

			PreparedStatement psmt = conn.prepareStatement(query);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				inventory.add(new Inventory(rs.getInt("id"), rs.getString("name"), rs.getString("brand"),
						rs.getDouble("price"), rs.getInt("rented")));
			}

			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return inventory;
	}

	@Override
	public Inventory findOneById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(int id, int rented) {
		try (Connection conn = ConnectionManager.getConnection()) {

			if (rented == 0) {
				String query = "update inventory set  rented=1 where id=?";
				PreparedStatement psmt = conn.prepareStatement(query);
				psmt.setInt(1, id);
				psmt.executeUpdate();
			} else if (rented == 1) {
				String query = "update inventory set  rented=0 where id=?";
				PreparedStatement psmt = conn.prepareStatement(query);
				psmt.setInt(1, id);
				psmt.executeUpdate();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void save(Inventory item) {
		try (Connection conn = ConnectionManager.getConnection()) {
			String query = "insert into inventory (name,brand,price,rented) values(?,?,?,?)";

			PreparedStatement psmt = conn.prepareStatement(query);

			// psmt.setInt(1, item.getId());
			psmt.setString(1, item.getName());
			psmt.setString(2, item.getBrand());
			psmt.setDouble(3, item.getPrice());
			psmt.setInt(4, item.getRented());
			psmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
