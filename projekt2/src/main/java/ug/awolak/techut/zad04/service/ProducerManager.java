package ug.awolak.techut.zad04.service;

import java.util.List;

import ug.awolak.techut.zad04.domain.Address;
import ug.awolak.techut.zad04.domain.Producer;

public interface ProducerManager {
	
	// Producer
	void addProducer(Producer producer);
    List<Producer> getAllProducers();
    Producer findById(long id);
    void updateProducer(Producer producer);
    void deleteProducer(Producer producer);
    
    // Relations
    void assignBed(Long bedId, Long producerId);
    void assignAddress(Long addressId, Long producerId);
    void deleteAddress(Long addressId, Long producerId);
    Producer findByName(String name);
    List<Address> getProducerAddresses(Producer producer);
}
