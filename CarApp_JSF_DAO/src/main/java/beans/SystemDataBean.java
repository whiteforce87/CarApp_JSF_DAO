package beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import dao.InventoryDao;
import dao.systemdataDAO;
import model.SystemData;


@Named
@RequestScoped
public class SystemDataBean {
	
	public List<SystemData> getAllPeople() {
		return new systemdataDAO().findAll();
	}

}
