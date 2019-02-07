package ug.awolak.techut.zad05.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;



@NamedQueries({
		@NamedQuery(name = "pillow.findAll", query = "Select t from Pillow t"),
		@NamedQuery(name = "pillow.findById", query = "Select t from Pillow t where t.id = :id")
})
@Entity
public class Pillow {

    private Long id;
    private String name;
    @ManyToOne
    private Bed bed;
    public Pillow(String name) {
		this.name = name;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}