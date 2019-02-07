package ug.awolak.techut.zad05.service;

import java.util.List;



import ug.awolak.techut.zad05.domain.Bed;
import ug.awolak.techut.zad05.domain.Producer;
import ug.awolak.techut.zad05.domain.Type;
import ug.awolak.techut.zad05.domain.Pillow;

public interface BedManager {
	void addBed(Bed bed);
	List<Bed> getAllBeds();
	void deleteBed(Bed bed);
	Bed findBedByName(String name);
	Bed findBedById(Long id);
	
	void addProducer(Producer producer);
    void deleteProducer(Producer producer);
    Producer findProducerById(Long id);
	List<Producer> getAllProducers();
	List <Bed> getProducerBeds(Producer producer);

    void addPillow(Pillow pillow);
    void deletePillow(Pillow pillow);
    Pillow findPillowById(Long id);
    List<Pillow> getAllPillows();
    List<Pillow> findBedPillows(Bed bed);

    void addType(Type type);
    void deleteType(Type type);
    Type findTypeById(Long id);
    List<Type> getAllType();
	

	void addBedToProducer(Bed b, Producer o);
	void removeBedFromProducer(Bed b, Producer o);
	void addPillowToBed(Pillow t1, Bed b);
}

