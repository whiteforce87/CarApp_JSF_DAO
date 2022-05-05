package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.SystemData;


public class systemdataDAO implements AppDAO<SystemData>, Serializable{

	@Override
	public List<SystemData> findAll() {
		List<SystemData> systemData = new ArrayList<>();
		try (Connection conn = ConnectionManager.getConnection()){
		
		String query = "select * from systemdata";
		
		
			ResultSet rs = conn.prepareStatement(query).executeQuery();
			
			while(rs.next()){
				systemData.add(new SystemData(rs.getInt("status")));
			}
			
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return systemData;
	}

	@Override
	public SystemData findOneById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(SystemData item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SystemData> findInventoryByRentedStatus(int rented) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(int id, int rented) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateInventory(int id, int rented) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveInventory(String name, String brand, double price, int rented) {
		// TODO Auto-generated method stub
		
	}


	
	
}
