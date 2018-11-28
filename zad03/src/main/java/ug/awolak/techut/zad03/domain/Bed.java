package ug.awolak.techut.zad03.domain;

import java.sql.Date;

public class Bed {
	
	private String name;
	private double width;
	private Date productionDate;
	private boolean isSoft;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

	public boolean isSoft() {
		return isSoft;
	}

	public void setSoft(boolean isSoft) {
		this.isSoft = isSoft;
	}

	@Override
	public String toString() {
		return "Bed [name=" + name + ", width=" + width + ", productionDate=" + productionDate
				+ ", isSoft=" + isSoft + "]";
	}
	
	public Bed(String name, double width, Date productionDate, boolean isSoft) {
		super();
		this.name = name;
		this.width = width;
		this.productionDate = productionDate;
		this.isSoft = isSoft;
	}
	public Bed() {
		super();
	}
	
}
