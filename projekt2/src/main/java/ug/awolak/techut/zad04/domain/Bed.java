package ug.awolak.techut.zad04.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@NamedQueries({
    @NamedQuery(name = "beds.all", query = "Select b from Bed b"),
})
public class Bed {
	
	private long id;
	private double width;
	private Date productionDate;
	private boolean isSoft;
	private SerialNumber serialnumber;
	private Producer producer;
	private List<Customer> customers = new ArrayList<Customer>();
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
		return "Bed [producer=" + producer + ", width=" + width + ", productionDate=" + productionDate
				+ ", isSoft=" + isSoft + "]";
	}
	
	public Bed(double width, Date productionDate, boolean isSoft, Producer producer, SerialNumber serialnumber, List<Customer> customers) {
		super();
		this.width = width;
		this.productionDate = productionDate;
		this.isSoft = isSoft;
		this.producer = producer;
		this.serialnumber = serialnumber;
		this.customers = customers;
	}
	
	public Bed(double width, Date productionDate, boolean isSoft) {
		super();
		this.width = width;
		this.productionDate = productionDate;
		this.isSoft = isSoft;
	}
	public Bed() {
		super();
	}
	
	// Bed has unique SerialNumber, SerialNumber has unique bed
	@OneToOne
	public SerialNumber getSerialNumber() {
		return serialnumber;
	}
	public void setSerialNumber(SerialNumber serialnumber) {
		this.serialnumber = serialnumber;
	}
	
	// Producer has many beds
    @ManyToOne
    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }
    
    // Bed has many customers, Customer has many beds
    @ManyToMany
    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
