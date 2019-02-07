package ug.awolak.techut.zad05.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name = "producer.findAll", query = "Select o from Producer o"),
	@NamedQuery(name = "producer.findById", query = "Select o from Producer o where o.id = :id")
})
public class Producer {

    private Long id;
    private String name;

    private List<Bed> bedList = new ArrayList<Bed>();

    public Producer() {
		super();
	}
    
    public Producer(String name) {
		super();
		this.name = name;
	}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getFirstName() {
        return name;
    }

    public void setFirstName(String firstName) {
        this.name = firstName;
    }

    //@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ManyToMany
    public List<Bed> getBedList() {
        return bedList;
    }

    public void setBedList(List<Bed> bedList) {
        this.bedList = bedList;
    }

	
    
}