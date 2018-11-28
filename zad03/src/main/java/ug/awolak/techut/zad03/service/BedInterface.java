package ug.awolak.techut.zad03.service;

import java.util.List;
import ug.awolak.techut.zad03.domain.Bed;

public interface BedInterface {
	public void addBed(Bed bed);
	public List<Bed> getallBeds();
	public Bed getBedById(int id);
	public void deleteAllBeds();
	public void deleteBedById(int id);
	public boolean addBeds(List<Bed> beds);
	public List<Bed> getBedsHeavierThan(double width);
}
