package model;

public class SystemData extends BaseEntity{

	private int status;
	
	public SystemData() {
		// TODO Auto-generated constructor stub
	}

	public SystemData(int status) {
		
		this.status = status;
	}
	

	@Override
	public String toString() {
		return "" + status;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
