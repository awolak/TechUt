package awolak.zad03.service;

import java.util.List;

import awolak.zad03.domain.Bed;

public interface BedManager {

	public int addBed(Bed bed);
	public List<Bed> getAllBedds();
	
	public void addAllBirds(List<Bed> beds);

}
