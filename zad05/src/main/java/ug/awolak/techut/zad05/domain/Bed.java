package ug.awolak.techut.zad05.domain;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;



@Entity
@NamedQueries({
	@NamedQuery(name = "bed.findAll", query = "Select b from Bed b"),
	@NamedQuery(name = "bed.findById", query = "Select b from Bed b where b.id = :id"),
	@NamedQuery(name = "bed.findByName", query = "Select b from Bed b where b.name = :name"),
	@NamedQuery(name= "bed.findBedPillows", query= "Select b.pillowList from Bed b where b.id = :id")
})

public class Bed {

	private String name;
	private boolean isSoft;
	private double weight;
	private Date  dateOfRelease;
	private int numberOfBeds;
    private List<Pillow> pillowList = new ArrayList<Pillow>();
    private List<Producer> producers = new ArrayList<Producer>();
    private Type type;
	
	
	public Bed() {
		super();
	}

	private Long id; 
	public Bed(String name, Date dateOfRelease, boolean isSoft, double weight, int numberOfBeds) {
		super();
		this.name = name;
		this.isSoft = isSoft;
		this.weight = weight;
		this.dateOfRelease = dateOfRelease;
		this.numberOfBeds = numberOfBeds;
	}

	public Bed(Long id, String name, Date dateOfRelease, boolean isSoft, double weight, int numberOfBeds) {
		super();
		this.id = id;
		this.name = name;
		this.isSoft = isSoft;
		this.weight = weight;
		this.dateOfRelease = dateOfRelease;
		this.numberOfBeds = numberOfBeds;
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Bed(String name) {
		super();
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getIsSoft() {
		return isSoft;
	}
	public void setIsSoft(boolean isSoft) {
		this.isSoft = isSoft;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public Date getDateOfRelease() {
		return dateOfRelease;
	}
	public void setDateOfRelease(Date dateOfRelease) {
		this.dateOfRelease = dateOfRelease;
	}
	
	public int getNumberOfBeds() {
		return numberOfBeds;
	}

	public void setNumberOfBeds(int numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}
	
	//@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Pillow> getPillowList() {
        return pillowList;
    }

    public void setPillowList(List<Pillow> pillowList) {
        this.pillowList = pillowList;
    }
	
    @OneToOne(fetch = FetchType.LAZY)
    public Type getType() {
        return type;
    }
    
    public void setType(Type type) {
        this.type = type;
    }
    
    @ManyToMany
	public List<Producer> getProducers() {
		return producers;
	}
    
    public void setProducers(List<Producer> producers) {
		this.producers = producers;
	}
	

	@Override
	public String toString() {
		return "Bed [name=" + name + ", isSoft=" + isSoft + ", weight=" + weight + ", dateOfRelease=" + dateOfRelease
				+ ", numberOfBeds=" + numberOfBeds + ", id=" + id + "]";
	}
	
}