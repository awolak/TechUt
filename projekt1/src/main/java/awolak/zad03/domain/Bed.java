package awolak.zad03.domain;

public class Bed {

	@Override
	public String toString() {
		return "Bed [name=" + name + ", isSoft=" + isSoft + ", weight=" + weight + ", dateOfBirth=" + dateOfBirth
				+ ", countOfSheets=" + countOfSheets + ", id=" + id + "]";
	}

	private String name;
	private boolean isSoft;
	private double weight;
	private String  dateOfBirth;
	private int countOfSheets;
	
	
	public Bed() {
		super();
	}

	private int id; 
	public Bed(String name, String dateOfBirth, boolean isSoft, double weight, int countOfSheets) {
		super();
		this.name = name;
		this.isSoft = isSoft;
		this.weight = weight;
		this.dateOfBirth = dateOfBirth;
		this.countOfSheets = countOfSheets;
	}

	public Bed(int id, String name, String dateOfBirth, boolean isSoft, double weight, int countOfSheets) {
		super();
		this.id = id;
		this.name = name;
		this.isSoft = isSoft;
		this.weight = weight;
		this.dateOfBirth = dateOfBirth;
		this.countOfSheets = countOfSheets;
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
	public boolean isSoft() {
		return isSoft;
	}
	public void setSoft(boolean isSoft) {
		this.isSoft = isSoft;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCountOfSheets() {
		return countOfSheets;
	}

	public void setCountOfSheets(int countOfSheets) {
		this.countOfSheets = countOfSheets;
	}
	
}
