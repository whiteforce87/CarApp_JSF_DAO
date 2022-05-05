package beans;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import dao.AppDaoImpl;
import dao.InventoryDao;
import model.Inventory;

@Named
@RequestScoped
public class SaveCarBean {

	@Inject
	private InventoryDao inventoryDao;
	@Inject
	private AppDaoImpl appDaoImpl;

	private Inventory newCar = new Inventory();
	private List<Inventory> fields = new ArrayList<>();

	@PostConstruct
	public void init() {
		newCar.setId(-1);

		fields = inventoryDao.findAll();

	}

	public String saveInventory() {

		inventoryDao.save(newCar);

		return "index";
	}

	public InventoryDao getInventoryDao() {
		return inventoryDao;
	}

	public void setInventoryDao(InventoryDao inventoryDao) {
		this.inventoryDao = inventoryDao;
	}

	public Inventory getNewCar() {
		return newCar;
	}

	public void setNewCar(Inventory newCar) {
		this.newCar = newCar;
	}

	public List<Inventory> getFields() {
		return fields;
	}

	public void setFields(List<Inventory> fields) {
		this.fields = fields;
	}

}
