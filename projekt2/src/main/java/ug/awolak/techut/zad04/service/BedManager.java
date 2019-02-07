package ug.awolak.techut.zad04.service;

import java.util.List;

import ug.awolak.techut.zad04.domain.Bed;
import ug.awolak.techut.zad04.domain.Customer;

public interface BedManager {

	// Bed
	void addBed(Bed bed);
	List<Bed> getAllBeds();
	Bed findById(long id);
	void updateBed(Bed bed);
	void deleteBed(Bed bed);
	
	// Relations
	List<Customer> getBedCustomers(Bed bed);
	void relateBedAndCustomer(long bedId, long customerId);
	void giveSerialNumber(long bedId, long serialnumberId);
}
