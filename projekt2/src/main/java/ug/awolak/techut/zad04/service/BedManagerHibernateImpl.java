package ug.awolak.techut.zad04.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ug.awolak.techut.zad04.domain.Bed;
import ug.awolak.techut.zad04.domain.Customer;
import ug.awolak.techut.zad04.domain.SerialNumber;

@Component
@Transactional
public class BedManagerHibernateImpl implements BedManager {

	@Autowired
	SessionFactory hsf;
	
	@Override
	public void addBed(Bed bed) {
		hsf.getCurrentSession().save(bed);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bed> getAllBeds() {
		return hsf.getCurrentSession().getNamedQuery("beds.all").list();
	}

	@Override
	public Bed findById(long id) {
		return (Bed) hsf.getCurrentSession().get(Bed.class, id);
	}

	@Override
	public void updateBed(Bed bed) {
		hsf.getCurrentSession().update(bed);
	}

	@Override
	public void deleteBed(Bed bed) {
		 hsf.getCurrentSession().delete(bed);
	}

	@Override
	public List<Customer> getBedCustomers(Bed bed) {
        bed = (Bed) hsf.getCurrentSession().get(Bed.class, bed.getId());

        List<Customer> customers = new ArrayList<Customer>(bed.getCustomers());

        return customers;
	}

	@Override
	public void relateBedAndCustomer(long bedId, long customersId) {
        Bed bed = findById(bedId);
        Customer customer = (Customer) hsf.getCurrentSession().get(Customer.class, customersId);

        bed.getCustomers().add(customer);
		
	}

	@Override
	public void giveSerialNumber(long bedId, long serialnumberId) {
		Bed bed = (Bed) hsf.getCurrentSession().get(Bed.class, bedId);
		SerialNumber serialnumber = (SerialNumber) hsf.getCurrentSession().get(SerialNumber.class, serialnumberId);

        bed.setSerialNumber(serialnumber);
		
	}
}
