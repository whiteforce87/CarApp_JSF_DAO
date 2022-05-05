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
public class RentedBean {

	private List<Inventory> fields = new ArrayList<Inventory>();

	private double total;

	@Inject
	private InventoryDao inventoryDao;
	@Inject
	private AppDaoImpl appDaoImpl;

	@PostConstruct
	public void init() {

		fields = inventoryDao.findInventoryByRentedStatus(1);

	}

	public void refreshData() {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();

		fields = inventoryDao.findAll();

	}

	public double calculateRentedOnes() {

		total = 0;

		for (Inventory cars : fields) {
			if (cars.getRented() == 1) {

				total += cars.getPrice();

			}
		}
		return total;
	}

	public String updateInventory(int rented) {
		fields = inventoryDao.findInventoryByRentedStatus(rented);
		refreshData();
		return null;

	}

	public List<Inventory> getFields() {
		return fields;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public InventoryDao getInventoryDao() {
		return inventoryDao;
	}

	public void setInventoryDao(InventoryDao inventoryDao) {
		this.inventoryDao = inventoryDao;
	}

	public void setFields(List<Inventory> fields) {
		this.fields = fields;
	}

	public AppDaoImpl getAppDaoImpl() {
		return appDaoImpl;
	}

	public void setAppDaoImpl(AppDaoImpl appDaoImpl) {
		this.appDaoImpl = appDaoImpl;
	}

}
