package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import dao.AppDaoImpl;
import dao.InventoryDao;
import model.Inventory;

@Named
@RequestScoped
public class AllFieldsBean {

	private List<Inventory> fields = new ArrayList<Inventory>();
	private double total;

	@Inject
	private InventoryDao inventoryDao;
	

	@PostConstruct
	public void init() {

		fields = inventoryDao.findAll();

	}

	public void refreshData() {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();

		fields = inventoryDao.findAll();

	}

	public double calculate() {

		total = 0;

		for (Inventory cars : fields) {

			total += cars.getPrice();
		}
		return total;
	}


	public String updateInventory(int id, int rented) {
		inventoryDao.update(id, rented);
		fields = inventoryDao.findAll();
		refreshData();
		return null;

	}

	public List<Inventory> getFields() {
		return fields;
	}
}
