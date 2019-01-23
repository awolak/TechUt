package ug.awolak.techut.zad03;

import java.sql.*;
import java.text.ParseException;
import java.util.List;
import java.util.ArrayList;

import ug.awolak.techut.zad03.domain.Bed;
import ug.awolak.techut.zad03.service.BedService;

public class Main {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws SQLException, ParseException {

			Bed b1 = new Bed("Bed1", Double.parseDouble("200.85"), new Date(2018, 12, 18), true);
	    	Bed b2 = new Bed("Bed2", Double.parseDouble("450.90"), new Date(2018, 12, 30), false);
	    	
	        BedService bs = new BedService();
	
	        bs.addBed(b1);
	        bs.addBed(b2);
	        
	        List<Bed> beds = new ArrayList<Bed>();
	        beds.add(b1);
	        beds.add(b2);
	        bs.addBeds(beds);
	        
	        Bed bed = bs.getBedById(2);
	}

}
